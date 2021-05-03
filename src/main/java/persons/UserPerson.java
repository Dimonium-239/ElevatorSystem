package persons;

public class UserPerson extends Person {

    private int elevatorID = -1;


    public UserPerson(int target) {
        super(0, target);
        setWaiting(false);
    }

    public void newTarget(int newTarget){
        setStartFloor(getTargetFloor());
        setTargetFloor(newTarget);
    }

    public int getElevatorID() {
        return elevatorID;
    }

    public void setElevatorID(int elevatorID) {
        this.elevatorID = elevatorID;
    }

    public int getStartFloor() {
        return super.getStartFloor();
    }

    public void setStartFloor(int startFloor) {
        super.setStartFloor(startFloor);
    }

    public int getTargetFloor() {
        return super.getTargetFloor();
    }

    public void setTargetFloor(int targetFloor) {
        super.setTargetFloor(targetFloor);
    }





    @Override
    public String toString() {
        return "| User: " +
                "elevID " + elevatorID + "\t" +
                " start " + super.getStartFloor() + "\t" +
                " target " + super.getTargetFloor() + "\t" +
                " isWait " + super.isWaiting() +
                " isRiding " + super.isRiding() +
                "|";
    }
}
