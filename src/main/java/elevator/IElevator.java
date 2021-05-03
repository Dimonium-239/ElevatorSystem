package elevator;

public interface IElevator extends Runnable{
    ElevatorStatus status();
    int getID();
    void update(ElevatorStatus updatedStatus);
    void pickup(int notifFloor);
}
