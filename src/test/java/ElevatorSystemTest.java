import elevator.ElevatorSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorSystemTest {

    private ElevatorSystem es;

    @BeforeEach
    public void setUp(){
        es = new ElevatorSystem(4,4);
    }

    @Test
    @DisplayName("All elevators on 0 floor, person is on 0 floor wants to 3")
    public void pickElevatorIfAllFree(){
        es.pickup(0, 3);
        assertTrue(es.getElevators().stream().anyMatch(elevator -> elevator.status().quantityOfTargets()>0));
        assertFalse(es.getElevators().stream().allMatch(elevator -> elevator.status().getFirstFromEl() == 3));
    }

    @Test
    @DisplayName("All elevators are not on 0 floor, person is on 0 floor wants to 3")
    public void pickElevatorIfNoOneOnYourFloor(){
        es.getElevators().get(0).pickup(3);
        es.getElevators().get(0).pickup(4);
        es.getElevators().get(0).pickup(7);
        for (int i = 0; i < 2; i++) es.getElevators().get(0).run();


        es.getElevators().get(1).pickup(5);
        es.getElevators().get(1).pickup(8);
        es.getElevators().get(1).pickup(6);
        for (int i = 0; i < 3; i++) es.getElevators().get(1).run();


        es.getElevators().get(2).pickup(4);
        for (int i = 0; i < 5; i++) es.getElevators().get(2).run();
        es.getElevators().get(2).pickup(8);
        es.getElevators().get(2).pickup(6);
        es.getElevators().get(2).pickup(2);

        es.getElevators().get(3).pickup(5);
        for (int i = 0; i < 5; i++) es.getElevators().get(3).run();
        es.getElevators().get(3).pickup(5);
        es.getElevators().get(3).pickup(4);
        es.getElevators().get(3).pickup(3);

        es.pickup(0, 3);
        assertTrue(es.getElevators().get(3).status().getTargets().contains(0));
    }
}
