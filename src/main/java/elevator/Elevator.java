package elevator;

import java.util.HashMap;

public class Elevator implements IElevator{

    private ElevatorStatus status;
    private final HashMap<Integer, Integer> targetFloorPeoples;
    private final int MAX_LOAD = 14;

    public Elevator(int elevatorID) {
        status = new ElevatorStatus(elevatorID,0);
        targetFloorPeoples = new HashMap<>();
    }


    @Override
    public void run() {
        if (status.quantityOfTargets() > 0 && status.getFirstFromEl() != -1) {
            if (status.getCurrentFloor() >= status.getFirstFromEl())
                update(status.down());
            else update(status.up());
        }
    }

    @Override
    public void pickup(int notifFloor){
        status.addTargetFloor(notifFloor);
    }

    public boolean isFull(){
        return MAX_LOAD <= elevatorLoad();
    }

    public int elevatorLoad(){
        return targetFloorPeoples.values().stream().reduce(Integer::sum).orElse(0);
    }

    @Override
    public ElevatorStatus status() {
        return status;
    }

    @Override
    public int getID() {
        return status.getElevatorID();
    }

    @Override
    public void update(ElevatorStatus updatedStatus) {
        status = updatedStatus;
    }

    public void decrementTargetFloorPeople(int floor){
        targetFloorPeoples.put(floor, targetFloorPeoples.getOrDefault(floor, 0) - 1);
    }

    public void incrementTargetFloorPeople(int floor){
        targetFloorPeoples.put(floor, targetFloorPeoples.getOrDefault(floor, 0) + 1);
    }

}
