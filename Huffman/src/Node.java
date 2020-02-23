import java.util.Comparator;

/**
 * Node class to be implemented into the huffman tree.
 * Each node consist of:
 *  1. An ascii code represented character
 *  2. The frequency of that character
 *  3. Two links to the nodes it is comprised of (if any)
 *
 * @author Brian Zarzuela
 *
 * @version 0.6, 2019-02-28
 *
 * @since 2019-02-26
 */
class Node {
    int ascii;
    int frequency;
    String binaryCode;
    Node left;
    Node right;

    Node(int ascii, int frequency){
        this.ascii = ascii;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return "(" + ascii + ", " + frequency + ")";
    }
}

/**
 * Custom Comparator class used for sorting Node objects in order of decreasing frequencies.
 *
 * @author Brian Zarzuela
 *
 * @version 1.0, 2019-02-28
 *
 * @since 2019-02-28
 */
class CustomComparator implements Comparator<Node> {
    @Override
    public int compare(Node instance1, Node instance2){
        return instance2.frequency - instance1.frequency;
    }
}
