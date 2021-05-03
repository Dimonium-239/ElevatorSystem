package elevator;

import java.util.LinkedList;

public class ElevatorStatus {
    private final int elevatorID;
    private int currentFloor;
    private final ElevatorList el;


    public ElevatorStatus(int elevatorID, int currentFloor) {
        this.elevatorID = elevatorID;
        this.currentFloor = currentFloor;
        this.el = new ElevatorList();
    }

    public ElevatorStatus up(){
        currentFloor++;
        if(currentFloor == getFirstFromEl()){
            el.remove(getFirstFromEl());
        }
        return this;
    }

    public ElevatorStatus down(){
        currentFloor--;
        if(currentFloor == getFirstFromEl()){
            el.remove(getFirstFromEl());
        }
        return this;
    }

    public int getElevatorID() {
        return elevatorID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFirstFromEl() {
        if(el.getList().isEmpty()) return -1;
        return el.getList().getFirst();
    }

    public void addTargetFloor(int targetFloor){
        this.el.add(currentFloor, targetFloor);
    }

    public LinkedList<Integer> getTargets(){
        return el.getList();
    }

    public ElevatorList getEl(){
        return el;
    }

    public int quantityOfTargets(){
        return el.getList().size();
    }

    @Override
    public String toString() {
        return "Elevator " + elevatorID + " is on " +
                currentFloor + " floor " +
                ", target floors are " + el.getList();
    }
}
