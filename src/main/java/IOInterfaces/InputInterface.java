package IOInterfaces;

import persons.Person;
import persons.UserPerson;

import java.util.Scanner;

public class InputInterface extends InputInterfaceWithOneUser {

    public InputInterface(){
        super();
    }

    public void input(Person p, int maxFloor){
        System.out.println("Provide the next target floor [q for quit]: ");
        Scanner in = new Scanner(System.in);
        boolean targetGot = false;
        while (!targetGot){
            String input = in.nextLine();
            if (input.equals("q")){
                System.exit(0);
            }
            try{
                int newTarget = Integer.parseInt(input);
                if (newTarget < 0 || newTarget > maxFloor){
                    printError(maxFloor);
                }
                else {
                    targetGot = true;
                    ((UserPerson) p).newTarget(newTarget);
                }
            } catch (Exception e){
                printError(maxFloor);
            }
        }
    }

    private void printError(int maxFloor){
        System.out.printf("Please provide a number in the range [%d, %d]:\n", 0, maxFloor);
    }
}
