/**
 * This class contains the code needed to compute a path from the entrance of the pyramid to all the treasure chambers
 * @author Jawaad Ahmar
 */
public class FindPath {

    // instance variable is a reference to an object of the class Map
    private Map pyramidMap;

    /**
     * constructor creates an object of class Map
     * @param fileName is the input file, used by the constructor in class Map
     */
    public FindPath (String fileName) {

        try {
            Map map = new Map(fileName);
            pyramidMap = map;
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    /**
     * method finds a path from the entrance to all the treasure chambers
     * @return the path taken to get to the chambers, a stack object of class DLStack
     */
    public DLStack<Chamber> path () {
        
        DLStack<Chamber> stack = new DLStack<>();

        stack.push(getMap().getEntrance());

        getMap().getEntrance().markPushed();

        int numTreasures = getMap().getNumTreasures();
        int numTreasures_found = 0;

        while (!(stack.isEmpty())) {
            
            Chamber currentChamber = stack.peek();

            if ((currentChamber.isTreasure()) && (numTreasures == numTreasures_found)) {
                break;
            }

            Chamber c = bestChamber(currentChamber);

            if (c != null) {
                if (c.isTreasure()) {
                    stack.push(c);
                    c.markPushed();
                    numTreasures_found++;
                } else {
                    stack.push(c);
                    c.markPushed();
                }   
            } else {
                Chamber top = stack.pop();
                top.markPopped();
            }
        }

        return stack;
    }

    /**
     * accessor method gets pyramidMap
     * @return the value of pyramidMap
     */
    public Map getMap () {
        return pyramidMap;
    }

    /**
     * checks if the current chamber is dim or not 
     * @param currentChamber the chamber being checked
     * @return true if currentChamber is dim, false otherwise
     */
    public boolean isDim (Chamber currentChamber) {

        if ((currentChamber != null) && !(currentChamber.isSealed()) && !(currentChamber.isLighted())) {
            for (int i = 0; 6 > i; ++i) {
                try {
                    if ((currentChamber.getNeighbour(i).isLighted()) || (currentChamber.getNeighbour(i).isTreasure())) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
                
            }
            return false;
        } else {
            return false;
        }  
    }

    /**
     * method selects the best chamber to move to
     * @param currentChamber is the current chamber
     * @return a neighbhour of currentChamber, the best chamber to move to
     */
    public Chamber bestChamber (Chamber currentChamber) {
        
        for (int i = 0; 6 > i; ++i) {
            try {
                if ((currentChamber.getNeighbour(i).isTreasure()) && !(currentChamber.getNeighbour(i).isMarked())) {
                    return currentChamber.getNeighbour(i);
                }
            } catch (Exception e){
                continue;
            }
        }
        
        for (int i = 0; 6 > i; ++i) {
            try {
                if ((currentChamber.getNeighbour(i).isLighted()) && !(currentChamber.getNeighbour(i).isMarked())) {
                    return currentChamber.getNeighbour(i);
                }
            } catch (Exception e) {
                continue;
            }     
        }
        
        for (int i = 0; 6 > i; ++i) {
            try {
                if ((isDim(currentChamber.getNeighbour(i))) && !(currentChamber.getNeighbour(i).isMarked())) {
                    return currentChamber.getNeighbour(i);
                }  
            } catch (Exception e) {
                continue;
            }
        }

        return null;
    }
}
