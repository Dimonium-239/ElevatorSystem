package elevator;

import java.util.Collections;
import java.util.LinkedList;

public class ElevatorList {
    private final LinkedList<Integer> list;

    public ElevatorList(ElevatorList es) {
        this.list = (LinkedList<Integer>) es.list.clone();
    }

    public ElevatorList() {
        this.list = new LinkedList<>();
    }

    public void add(int currentFloor, int newTarget){
        if (list.contains(currentFloor)) remove(currentFloor);
        if (list.isEmpty()) list.addFirst(newTarget);
        else if (list.size() == 1 && !list.contains(newTarget)){
            int oneTarget = list.getFirst();
            if (currentFloor < oneTarget && newTarget < currentFloor) list.addLast(newTarget);
            else if(newTarget > currentFloor && newTarget < oneTarget) list.addFirst(newTarget);
            else if(currentFloor > oneTarget && oneTarget > newTarget) list.addLast(newTarget);
            else if(currentFloor > newTarget && oneTarget < newTarget) list.addFirst(newTarget);
            else if(newTarget > oneTarget && oneTarget > currentFloor) list.addLast(newTarget);
            else if(newTarget > currentFloor && currentFloor >oneTarget) list.addLast(newTarget);
        }
        else if (!list.contains(newTarget)){
            int maxElem = Collections.max(list);
            int maxInx = list.indexOf(maxElem);
            int indexOfNewElemFirst = 0;
            int indexOfNewElemSecond = list.size();
            for (int i = 0; i <= maxInx; i++) {
                if(list.get(i) >= newTarget){
                    indexOfNewElemFirst = i;
                    break;
                }else{
                    indexOfNewElemFirst = i;
                }
            }
            for (int i = maxInx; i < list.size(); i++) {
                if(list.get(i) <= newTarget) {
                    indexOfNewElemSecond = i;
                    break;
                }
            }
            if(currentFloor < maxElem && newTarget < currentFloor) list.add(indexOfNewElemSecond, newTarget);
            else if(newTarget > currentFloor && newTarget < maxElem)
                list.add(indexOfNewElemFirst, newTarget);

            else if(currentFloor >= maxElem && maxElem > newTarget) list.add(indexOfNewElemSecond, newTarget);
            else if(currentFloor > newTarget && maxElem < newTarget) list.add(maxInx, newTarget);
            else if(newTarget > maxElem && maxElem > currentFloor) list.add(maxInx+1,newTarget);
            else if(newTarget > currentFloor && currentFloor > maxElem) list.addLast(newTarget);
        }
    }

    public void remove(int target){
        list.remove(Integer.valueOf(target));
    }

    public LinkedList<Integer> getList(){
        return list;
    }
}
