package elevator;

import java.util.ArrayList;

public interface IElevatorSystem {
    ArrayList<ElevatorStatus> status();
    void step();
    int pickup(int notifFloor, int direction);
}
