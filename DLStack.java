/**
 * This class represents an extended stack ADT implemented using a doubly linked list
 * @author Jawaad Ahmar
 */
public class DLStack<T> implements DLStackADT<T> {
    
    // instance variable references the node at the top of the stack
    private DoubleLinkedNode<T> top;

    // instance vairable holds the int value of the number of data items stored in the stack
    private int numItems;

    /**
     * constructor creates an empty stack
     * sets the number of items in the stack equal to 0
     */
    public DLStack () {
        top = null;
        numItems = 0;
    }

    /**
     * method adds the given data item to the top of the stack
     * @param dataItem is the item being added 
     */
    public void push (T dataItem) {

        DoubleLinkedNode<T> temp = new DoubleLinkedNode<T>(dataItem);
        
        if (top == null) {
            top = temp;
            top.setPrevious(null);
            top.setNext(null);
            numItems++;
        } else {
            temp.setPrevious(top);
            top.setNext(temp);
            temp.setNext(null);
            top = temp;
            numItems++;
        }
    }

    /**
     * method removes and returns the data item at the top of the stack
     * @return data item at the top of the stack
     * @throws EmptyStackException is stack is empty
     */
    public T pop () throws EmptyStackException {
        
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        } else if (numItems == 1) {
            T node = top.getElement();
            top = null;
            numItems--;
            return node;
        } else {
            T node = top.getElement();
            top = top.getPrevious();
            top.setNext(null);
            numItems--;
            return node;
        }
    }

    /**
     * method removes and returns the data item at a specified location
     * @param k is the data item to be removed
     * @return the data item that is removed
     * @throws InvalidItemException if index is out of range
     */
    public T pop (int k) throws InvalidItemException {

        DoubleLinkedNode<T> temp = top;
        int i = 1;

        if (k > numItems) {
            throw new InvalidItemException("Index is out of range.");
        }

        while (temp != null) {
            if (k == i) {
                if (i == 1) {
                    T node = top.getElement();
                    top = top.getPrevious();
                    top.setNext(null);;
                    numItems--;
                    return node;
                } else if (i == numItems) {
                    T node = temp.getElement();
                    temp.getNext().setPrevious(null);
                    numItems--;
                    return node;
                } else {
                    T node = temp.getElement();
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                    temp = null;
                    numItems--;
                    return node;
                }
            }
            i++;
            temp = temp.getPrevious();
        }

        return null;
    }

    /**
     * method returns the data item at the top of the stack without removing it
     * @return returns the value at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek () throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        } else {
            T node = top.getElement();
            return node;
        }
    }

    /**
     * method checks if stack is empty
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty () {
        if (numItems == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * method checks size of the stack
     * @return the number of items in the stack
     */
    public int size () {
        return numItems;
    }

    /**
     * accessor method gets top
     * @return the node at the top of the stack
     */
    public DoubleLinkedNode<T> getTop () {
        return top;
    }

    /**
     * returns a string representation of the stack
     */
    public String toString () {
        DoubleLinkedNode<T> temp = top;
        String toString = "[";
        while (temp != null) {
            if (temp.getPrevious() == null) {
                toString += temp.getElement();
            } else {
                toString += temp.getElement() + " ";
            }
            temp = temp.getPrevious();
        }
        toString += "]";
        
        return toString;
    }
}