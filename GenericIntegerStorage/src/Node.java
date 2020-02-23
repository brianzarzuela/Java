/**
 * <h1>Node Class</h1>
 * Creates a Generic Node for implementing into a Generic Linked List
 *
 * @author Brian Zarzuela
 * @param <T>
 * @version 1.0, 2019-02-20
 * @since 2019-02-19
 */

class Node<T> {
    private T val;
    private Node previous;
    private Node next;

    Node(T val, Node previous, Node next){
        this.val=val;
        this.next=next;
        this.previous=previous;
    }
    Node getNext(){
        return next;
    }
    void setNext(Node link){
        this.next=link;
    }
    Node getPrevious(){
        return previous;
    }
    void setPrevious(Node previous){
        this.previous=previous;
    }
    T getData(){
        return val;
    }
    void setData(T data){
        this.val=data;
    }
}
