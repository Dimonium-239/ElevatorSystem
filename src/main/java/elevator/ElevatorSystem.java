package elevator;

import IOInterfaces.IInputInterface;
import IOInterfaces.IOutputInterfaces;
import IOInterfaces.InputInterfaceWithOneUser;
import IOInterfaces.NonInput;
import persons.Person;
import persons.UserPerson;

import java.util.*;
import java.util.stream.Collectors;

public class ElevatorSystem implements IElevatorSystem {
    private final ArrayList<Elevator> elevators;
    private final ArrayList<Person> persons;
    private final int MAX_FLOORS;

    public ElevatorSystem(int elevNumber, int maxFloors) {
        MAX_FLOORS = maxFloors;
        persons = new ArrayList<>();
        elevators = new ArrayList<>(elevNumber);
        for (int i = 0; i < elevNumber; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public ArrayList<Elevator> getElevators(){
        return elevators;
    }

    public int getElevatorsQuantity(){
        return elevators.size();
    }

    public void run(IOutputInterfaces outputInterface, IInputInterface inputInterface){
        UserPerson user = null;
        if (!(inputInterface instanceof NonInput)) {
            user = ((InputInterfaceWithOneUser) inputInterface).user;
            persons.add(user);
        }
        while (true){
            generatePersons();
            redistributePersons(inputInterface);
            step();
            outputInterface.showElevatorSystem(this, user);
        }
    }

    public void redistributePersons(IInputInterface inputInterface){
        List<Person> personsCopy = new ArrayList<>(persons);
        for (Person p : personsCopy) {
            if(!(p instanceof UserPerson) ) {
                int elNum = pickup(p.getStartFloor(), p.getTargetFloor());
                int myElevatorCurrentFloor = findElevatorById(elNum).status().getCurrentFloor();
                if (myElevatorCurrentFloor == p.getStartFloor()) {
                    p.setWaiting(false);
                }
                if (myElevatorCurrentFloor == p.getTargetFloor() && !p.isWaiting()) {
                    persons.remove(p);
                }
            }
            else {
                UserPerson person = (UserPerson) p;

                // the first input or rogue
                if (p.getTargetFloor() == p.getStartFloor()){
                    p.setRiding(false);
                    person.setElevatorID(-1);
                    inputInterface.input(p, MAX_FLOORS);
                }

                Elevator myElevator = findElevatorById(person.getElevatorID());

                // the elevator assignment and the start of waiting for an elevator
                if (myElevator == null) {
                    int elNum = pickup(p.getStartFloor(), p.getTargetFloor());
                    person.setElevatorID(elNum);
                    person.setWaiting(true);

                    myElevator = findElevatorById(elNum);
                    myElevator.status().addTargetFloor(person.getStartFloor());
                }

                int myElevatorCurrentFloor = myElevator.status().getCurrentFloor();

                // debarkation of the elevator
                if ((p.isRiding() && p.getTargetFloor() == myElevatorCurrentFloor) ||
                        p.getTargetFloor() == p.getStartFloor()){
                    p.setRiding(false);
                    person.setElevatorID(-1);
                    inputInterface.input(p, MAX_FLOORS);
                }
                // entering the elevator
                else if (p.isWaiting() && p.getStartFloor() == myElevatorCurrentFloor){
                    p.setWaiting(false);
                    p.setRiding(true);

                    myElevator.status().addTargetFloor(person.getTargetFloor());
                }
            }
        }
    }

    private Elevator findElevatorById(int elevatorId){
        for (Elevator elevator : elevators){
            if (elevator.getID() == elevatorId){
                return elevator;
            }
        }
        return null;
    }

    public void generatePersons(){
        if(persons.size() < 5) {
            for (int i = 0; i < randomGen(0, this.getElevatorsQuantity()); i++) {
                persons.add(new Person(randomGen(0, MAX_FLOORS), randomGen(0, MAX_FLOORS)));
            }
        }
    }

    @Override
    public ArrayList<ElevatorStatus> status() {
        return (ArrayList<ElevatorStatus>) elevators
                .stream()
                .map(Elevator::status)
                .collect(Collectors.toList());
    }

    @Override
    public void step() {
        elevators.parallelStream().forEach(Elevator::run);
    }

    @Override
    public int pickup(int notifFloor, int direction) {
        if(elevators.size() == 1){
            elevators.get(0).pickup(notifFloor);
            return 0;
        }
        else {
            ArrayList<Elevator> freeElevators = (ArrayList<Elevator>) elevators.parallelStream()
                    .filter(elevator -> elevator.status().getCurrentFloor() == notifFloor)
                    .collect(Collectors.toList());

            if (!freeElevators.isEmpty()) {
                Collections.shuffle(freeElevators);
                freeElevators.get(0).pickup(notifFloor);
                return freeElevators.get(0).getID();
            } else {
                HashMap<Integer, ElevatorList> elevatorAndListMap = new HashMap<>();
                for (Elevator e : elevators) {
                    ElevatorList el = new ElevatorList(e.status().getEl());
                    el.add(e.status().getCurrentFloor(), notifFloor);
                    elevatorAndListMap.put(e.getID(), el);
                }
                HashMap<Integer, Integer> elevIDAndDistToFinishMap = new HashMap<>();

                for (Elevator e : elevators) {
                    ElevatorListShape elevatorListShape = findShapeOfList(elevatorAndListMap.get(e.getID()).getList());
                    int currentFloor = e.status().getCurrentFloor();

                    if(elevatorListShape == ElevatorListShape.MONOTONIC)
                        elevIDAndDistToFinishMap.put(e.getID(), Math.abs(currentFloor - direction));

                    else if(elevatorListShape == ElevatorListShape.HILL){
                        int maxElem = Collections.max(e.status().getTargets());
                        int lenForHill = Math.abs(maxElem - e.status().getCurrentFloor()) +
                                Math.abs(maxElem - direction);
                        elevIDAndDistToFinishMap.put(e.getID(), lenForHill);
                    }

                    else if(elevatorListShape == ElevatorListShape.VALLEY){
                        int minElem = Collections.max(e.status().getTargets());
                        int lenForVally = Math.abs(minElem - e.status().getCurrentFloor()) +
                                Math.abs(minElem - direction);
                        elevIDAndDistToFinishMap.put(e.getID(), lenForVally);
                    }

                }

                int minDistToPerson = Collections.min(elevIDAndDistToFinishMap.values());
                ArrayList<Integer> bestElevs = (ArrayList<Integer>) elevIDAndDistToFinishMap.entrySet().stream().filter(k -> k.getValue() == minDistToPerson)
                        .map(Map.Entry::getKey).collect(Collectors.toList());
                Collections.shuffle(bestElevs);
                int bestElevId = bestElevs.get(0);
                for (Elevator e : elevators) {
                    if (e.getID() == bestElevId) {
                        e.pickup(notifFloor);
                        return e.getID();
                    }
                }
            }
        }
        return -1;
    }

    private ElevatorListShape findShapeOfList(LinkedList<Integer> elList){
        if(elList.size() > 1) {
            int signFront = Integer.compare(elList.get(1) - elList.get(0), 0);
            int signBack = Integer.compare(elList.get(elList.size() - 1) - elList.get(elList.size() - 2), 0);
            if (signFront != signBack) {
                if (signFront > 0) return ElevatorListShape.HILL;
                if (signFront < 0) return ElevatorListShape.VALLEY;
            }
        }
        return ElevatorListShape.MONOTONIC;
    }

    public static int randomGen(int min, int max){
        max = max - min;
        Random r = new Random();
        return r.nextInt(max) + min;
    }
}
