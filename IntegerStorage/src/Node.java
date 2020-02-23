/**
 * <h1>Node Class</h1>
 *
 * @author Brian Zarzuela
 * @version 1.0, 2019-02-17
 * @since 2019-02-15
 */

public class Node {
    int val;
    Node previous;
    Node next;

    Node(int val, Node previous, Node next) {
        this.val = val;
        this.previous = previous;
        this.next = next;
    }

    public Node getNext() { return next; }

    public Node getPrevious() { return previous; }

    public int getData() { return val; }

    public void setData(int val) { this.val = val; }

    public void setNext(Node next) { this.next = next; }

    public void setPrevious(Node previous) {this.previous = previous; }
}
