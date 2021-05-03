package IOInterfaces;

import elevator.ElevatorSystem;
import persons.UserPerson;

public class ConsoleInterface implements IOutputInterfaces {
    @Override
    public void showElevatorSystem(ElevatorSystem es, UserPerson up){
        printFrame(es.getElevatorsQuantity());
        for (int j = 0; j < es.getElevatorsQuantity(); j++) {
            System.out.print("| ID:  " + es.getElevators().get(j).getID() + "\t");
        }
        System.out.println('|');
        for (int j = 0; j < es.getElevatorsQuantity(); j++) {
            System.out.print("| Floor: " + es.getElevators().get(j).status().getCurrentFloor() + "\t");
        }
        System.out.println('|');
        for (int j = 0; j < es.getElevatorsQuantity(); j++) {
            System.out.print("| Targets: \t");
        }
        System.out.println('|');
        for (int j = 0; j < es.getElevatorsQuantity(); j++) {
            int len = es.getElevators().get(j).status().quantityOfTargets();
            System.out.print("| " + es.getElevators().get(j).status().getTargets() + tabForTargets(len));
        }
        System.out.println('|');
        printFrame(es.getElevatorsQuantity());
        printYou(up);
        printFrame(es.getElevatorsQuantity());
        System.out.println("\n");
        wait(1000);
        System.out.flush();
    }

    private void printYou(UserPerson up){
        if(up != null) System.out.println(up);
    }

    private static String tabForTargets(int lengthOfTarget){
        for (int i = 0; i < lengthOfTarget; i++) {
            if(lengthOfTarget == 1) return "\t\t";
            if(lengthOfTarget == 2) return "\t";
            if(lengthOfTarget == 3) return "\t";
            if(lengthOfTarget == 4) return "";
            if(lengthOfTarget == 5) return "";
            return "";
        }
        return "\t\t";
    }

    private static void printFrame(int elevatorsQuantity){
        for (int i = 0; i < elevatorsQuantity; i++) {
            System.out.print("+---------------");
        }
        System.out.println('+');
    }

    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
