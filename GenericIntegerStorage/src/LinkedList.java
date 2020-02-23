/**
 * <h1>Generic Linked List Class</h1>
 * This is a class extending the abstract class StorageAbstract to create a generic [doubly] linked list.
 *
 * @author Brian Zarzuela
 * @param <T>
 * @version 1.0, 2019-02-21
 * @since 2019-02-19
 */
public class LinkedList<T extends Comparable> extends StorageAbstract<T>{
    //data members
    private Node<T> head;
    private Node<T> tail;

    @SuppressWarnings("WeakerAccess")
    public LinkedList(){
        super();
        this.size=0;
    }
    /**
     * Appends the specified element to the end of the list.
     * @param o Specified element
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean add(T o) {
        if (this.size==0){
            head=new Node(o, null, null);
            tail=head;
            size++;
            return true;
        }
        else {
            Node<T> newNode = new Node(o, tail, null);
            tail.setNext(newNode);
            tail=newNode;
            size++;
            return true;
        }

    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index Specified position
     * @param element Specified element
     */
    @SuppressWarnings("unchecked")
    public void add(int index, T element){
        Node cursor=head;
        Node newNode;
        if (index>=size){
            newNode = new Node(element, tail, null);
            tail.setNext(newNode);
            tail=newNode;
        }
        if (index==0){
            newNode=new Node(element, null, this.head);
            head=newNode;
        }
        else {
            for (int i = 0; i <index-1; i++) {
                cursor = cursor.getNext();
            }
            newNode = new Node(element, cursor, cursor.getNext());
            cursor.setNext(newNode);
            newNode.getNext().setPrevious(newNode);
        }
        size++;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear(){
        head=null;
        tail=null;
        size=0;
    }

    /**
     * Checks if this list contains the specified element.
     * @param o Specified element
     * @return boolean True if the element is contained within this list, otherwise False
     */
    public boolean	contains(T o){
        Node cursor=head;
        while (cursor != null){
            if (cursor.getData().equals(o)){
                return true;
            }
            cursor=cursor.getNext();
        }
        return false;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param index Specified position
     * @return T Element found in position index
     */
    @SuppressWarnings("unchecked")
    public T get(int index){
        if (index > size){
            System.out.println("No such index!");
            throw new IllegalArgumentException("No such index!");
        }
        else{
            Node<T> cursor=head;
            for (int i=0; i<index; i++){
                cursor=cursor.getNext();
            }
            return cursor.getData();
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * Returns -1 if this list does not contain the element.
     * @param o Specified element
     * @return int Index of first occurrence of specified element or -1 if it is not found
     */
    public int	indexOf(T o){
        Node cursor = head;
        int index = 0;
        while (cursor != null) {
            if (cursor.getData().equals(o)) {
                return index;
            }
            index++;
            cursor = cursor.getNext();
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * Returns -1 if this list does not contain the element.
     * @param o Specified element
     * @return int Index of last occurrence of specified element or -1 if it is not found
     */
    public int	lastIndexOf(T o){
        int index=0;
        Node cursor=head;
        int tempIndex=-1;
        while (cursor != null){
            if (cursor.getData().equals(o)){
                tempIndex=index;
            }
            index++;
            cursor=cursor.getNext();
        }
        return tempIndex;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index Specified position
     * @return T Element being removed
     */
    @SuppressWarnings("unchecked")
    public T remove(int index){
        if (index>size){
            throw new IllegalArgumentException("No such index!");
        }
        else if (index==0){
            if(size==1){
                T data=head.getData();
                this.clear();
                size=0;
                return data;
            }
            T data=head.getData();
            head=head.getNext();
            head.setPrevious(null);
            size--;
            return data;
        }
        else if (index==size-1){
            T data=tail.getData();
            tail=tail.getPrevious();
            tail.setNext(null);
            size--;
            return data;
        }

        Node<T> cursor=head;
        for (int i=0; i!=index; i++){
            cursor=cursor.getNext();
        }
        T data=cursor.getData();
        cursor.getPrevious().setNext(cursor.getNext());
        cursor.getNext().setPrevious(cursor.getPrevious());
        size--;
        return data;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o Specified element
     * @return boolean True if element was present and removed, otherwise False
     */
    @SuppressWarnings("unchecked")
    public boolean	remove(T o){
        Node<T> cursor=head;
        if (head.getData().equals(o)){
            head=head.getNext();
            head.setPrevious(null);
            size--;
            return true;
        }
        else {
            while (cursor != null) {
                if (cursor.getData().equals(o)) {
                    if (cursor==tail){
                        tail=cursor.getPrevious();
                        tail.setNext(null);
                        size--;
                        return true;
                    }
                    else {
                        cursor.getPrevious().setNext(cursor.getNext());
                        cursor.getNext().setPrevious(cursor.getPrevious());
                        size--;
                        return true;
                    }
                }
                cursor = cursor.getNext();
            }

        }
        return false;
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     * @param index Specified position
     * @param element Specified element
     * @return T Element replaced
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element){
        Node<T> cursor=head;
        for (int i=0; i<index; i++){
            cursor=cursor.getNext();
        }
        T data=cursor.getData();
        cursor.setData(element);
        return data;
    }

}
