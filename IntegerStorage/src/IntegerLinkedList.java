/**
 * <h1>Integer Linked List Class</h1>
 * This is a class extending the IntegerStorageImplementation to create an Integer [doubly] Linked List.
 *
 * @author Brian Zarzuela
 * @version 1.0, 2019-02-17
 * @since 2019-02-11
 */

class IntegerLinkedList extends IntegerStorageImplementation{
    //data members
    private Node head;
    private Node tail;
    /**
     * Constructor for class IntegerArrayList
     */
    public IntegerLinkedList() {
        super();
    }

    /**
     * adds a new node to the end of the linked list
     * @param o the integer value of the node to be added
     * @return boolean
     */
    public boolean add(int o) {
        if (this.size==0){
            head=new Node(o, null, null);
            tail=head;
            size++;
            return true;
        }
        else {
            Node newNode = new Node(o, tail, null);
            tail.setNext(newNode);
            tail=newNode;
            size++;
            return true;
        }

    }

    /**
     * adds a new node at a given index. Index count begins from 0 as the head node index to size-1 as the tail node index
     * @param index
     * @param element
     */
    public void add(int index, int element) {
        Node cursor=head;
        if (index>=size){
            Node newNode=new Node(element, tail, null);
            tail.setNext(newNode);
            tail=newNode;
        }
        if (index==0){
            Node newNode=new Node(element, null, this.head);
            head=newNode;
        }
        else {
            for (int i = 0; i <index-1; i++) {
                cursor = cursor.getNext();
            }
            Node newNode = new Node(element, cursor, cursor.getNext());
            cursor.setNext(newNode);
            newNode.getNext().setPrevious(newNode);
        }
        size++;
    }

    /**
     * erases the linked list. sets the link of the head node to null so the rest of the list can no longer be accessed
     * then removes the int data value from the head node
     */
    public void clear() {
        head=null;
        tail=null;
        size=0;
    }

    /**
     * checks to see if the linked list contains a given integer
     * @param o the integer to be checked for
     * @return true if the integer was found, else false
     */
    public boolean contains(int o) {
        Node cursor=head;
        while (cursor != null){
            if (cursor.getData()==o){
                return true;
            }
            cursor=cursor.getNext();
        }
        return false;
    }

    /**
     * returns the element at a given index. Index count starts from head node index = 0 to tail node index=size-1
     * @param index
     * @return the int value at the given index
     */
    public int get(int index) {
        if (index > size){
            System.out.println("No such index!");
            throw new IllegalArgumentException("No such index!");
        }
        else{
            Node cursor=head;
            for (int i=0; i<index; i++){
                cursor=cursor.getNext();
            }
            return cursor.getData();
        }
    }

    /**
     * returns the index of a given integer. returns -1 if the integer is not found
     * @param o, the integer to be searched for
     * @return the index of o in the linked list. returns -1 if o was not found in the linked list
     */
    public int indexOf(int o) {
        Node cursor = head;
        int index = 0;
        while (cursor != null) {
            if (cursor.getData() == o) {
                return index;
            }
            index++;
            cursor = cursor.getNext();
        }
        return -1;
    }

    /**
     * returns the index of a given integer. If the integer exists multiple times in the list, the last index is returned
     * @param o, the integer to be searched for
     * @return the index of the last occurence of integer o in the linked list. returns -1 if the integer was not found in the list
     */
    public int lastIndexOf(int o) {
        int index=0;
        Node cursor=head;
        int tempIndex=-1;
        while (cursor != null){
            if (cursor.getData() == o){
                tempIndex=index;
            }
            index++;
            cursor=cursor.getNext();
        }
        return tempIndex;
    }

    /**
     * removes the node at the specified index
     * @param index, the index of the node to remove
     * @returns the data from the node that was removed
     */
    public int remove(int index) {
        if (index>size){
            throw new IllegalArgumentException("No such index!");
        }
        else if (index==0){
            if(size==1){
                int data=head.getData();
                this.clear();
                size=0;
                return data;
            }
            int data=head.getData();
            head=head.getNext();
            head.setPrevious(null);
            size--;
            return data;
        }
        else if (index==size-1){
            int data=tail.getData();
            tail=tail.getPrevious();
            tail.setNext(null);
            size--;
            return data;
        }

        Node cursor=head;
        for (int i=0; i!=index; i++){
            cursor=cursor.getNext();
        }
        int data=cursor.getData();
        cursor.getPrevious().setNext(cursor.getNext());
        cursor.getNext().setPrevious(cursor.getPrevious());
        size--;
        return data;
    }

    /**
     * removes the first occurence of the specified integer
     * @param o
     * @returns true if the node was removed. returns false otherwise
     */
    public boolean remove(Integer o) {
        Node cursor=head;
        if (head.getData()==(int)o){
            head=head.getNext();
            head.setPrevious(null);
            size--;
            return true;
        }
        else {
            while (cursor != null) {
                if (cursor.getData()==(int)o) {
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
     * sets the node at the given index to the given element
     * @param index
     * @param element
     * @return the integer that was overwritten
     */
    public int set(int index, int element) {
        Node cursor=head;
        for (int i=0; i<=index; i++){
            cursor=cursor.getNext();
        }
        int data=cursor.getData();
        cursor.setData(element);
        return data;
    }

}