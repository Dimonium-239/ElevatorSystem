package persons;

public class Person implements IPerson {
    private int startFloor;
    private int targetFloor;
    private boolean isWaiting;
    private boolean isRiding;

    public Person(int startFloor, int targetFloor) {
        this.startFloor = startFloor;
        this.targetFloor = targetFloor;
        this.isWaiting = true;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }

    public boolean isRiding() {
        return isRiding;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }
}
