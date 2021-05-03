import elevator.ElevatorList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorListTest {
    private ElevatorList el;

    @BeforeEach
    public void setUp(){
        el = new ElevatorList();
    }

    @Test
    @DisplayName("[ 1 elem ] C NT T")
    public void addNewTargetC_NT_T(){
        el.add(0, 4);
        el.add(0, 2);
        assertEquals(Arrays.asList(2,4), el.getList());
    }
    @Test
    @DisplayName("[ 1 elem ] C T NT")
    public void addNewTargetC_T_NT(){
        el.add(0, 2);
        el.add(0, 4);
        assertEquals(Arrays.asList(2, 4), el.getList());
    }
    @Test
    @DisplayName("[ 1 elem ] T C NT")
    public void addNewTargetT_C_NT(){
        el.add(0, 2);
        el.add(3, 4);
        assertEquals(Arrays.asList(2, 4), el.getList());
    }
    @Test
    @DisplayName("[ 1 elem ] NT C T")
    public void addNewTargetNT_C_T(){
        el.add(0, 4);
        el.add(3, 2);
        assertEquals(Arrays.asList(4,2), el.getList());
    }
    @Test
    @DisplayName("[ 1 elem ] NT T C")
    public void addNewTargetNT_T_C(){
        el.add(4, 3);
        el.add(4, 2);
        assertEquals(Arrays.asList(3, 2), el.getList());
    }
    @Test
    @DisplayName("[ 1 elem ] T NT C")
    public void addNewTargetT_NT_C(){
        el.add(4, 2);
        el.add(4, 3);
        assertEquals(Arrays.asList(3, 2), el.getList());
    }

//////////////////////////////////////////////////////////////////////////
    @Test
    @DisplayName("[ >1 elem ] C NT M")
    public void addNewTargetC_NT_M(){
        el.add(0, 4);
        el.add(0, 2);
        el.add(0, 3);
        assertEquals(Arrays.asList(2,3,4), el.getList());
    }
    @Test
    @DisplayName("[ >1 elem ] C M NT")
    public void addNewTargetC_M_NT(){
        el.add(0, 4);
        el.add(0, 2);
        el.add(0, 6);
        assertEquals(Arrays.asList(2, 4, 6), el.getList());
    }
    @Test
    @DisplayName("[ >1 elem ] M C NT")
    public void addNewTargetM_C_NT(){
        el.add(4, 2);
        el.add(4, 1);
        el.add(4, 6);
        assertEquals(Arrays.asList(2,1,6), el.getList());
    }
    @Test
    @DisplayName("[ >1 elem ] NT C M")
    public void addNewTargetNT_C_M(){
        el.add(4, 6);
        el.add(4, 5);
        el.add(4, 2);
        assertEquals(Arrays.asList(5,6,2), el.getList());
    }
    @Test
    @DisplayName("[ >1 elem ] NT M C")
    public void addNewTargetNT_M_C(){
        el.add(6, 5);
        el.add(6, 1);
        el.add(6, 3);
        assertEquals(Arrays.asList(5,3,1), el.getList());
    }
    @Test
    @DisplayName("[ >1 elem ] M NT C")
    public void addNewTargetM_NT_C(){
        el.add(6, 3);
        el.add(6, 4);
        el.add(6, 5);
        assertEquals(Arrays.asList(5,4,3), el.getList());
    }

//////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("[ 4 elem ]")
    public void addNewTarget4Elem(){
        el.add(0, 3);
        el.add(0, 4);
        el.add(0, 3);
        el.add(0, 7);
        assertEquals(Arrays.asList(3,4,7), el.getList());
    }
}
