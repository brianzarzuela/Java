/**
 * <h1>Abstract Integer Storage Implementation Class</h1>
 *
 * @author Brian Zarzuela
 * @version 1.0, 2019-02-17
 * @since 2019-02-11
 */

public abstract class IntegerStorageImplementation implements IntegerStorage{

    protected int size;

    /**
     * Constructor for IntegerStorageImplementation
     */
    IntegerStorageImplementation() {
        this.size = 0;
    }

    /**
     * Appends all elements in specified array to the end of the storage list
     * @param os Integer list to append
     * @return
     */
    public boolean addAll(int[] os) {
        for (int i : os)
            add(i);
        return true;
    }

    /**
     * Inserts all elements in specified array into the storage list at the specified position
     * @param index Specified index
     * @param os Integer list to insert
     * @return
     */
    public boolean addAll(int index, int[] os) {
        for (int i : os){
            add(index, i);
            index++;
        }
        return true;
    }

    /**
     * Returns true if storage list contains all of the elements within the specified list, otherwise returns false.
     * @param os Specified list of elements
     * @return boolean True if all elements exist in storage list, False otherwise
     */
    public boolean containsAll(int[] os) {
        for (int i : os)
            if (!contains(i))
                return false;
        return true;
    }

    /**
     * <i>(optional operator)</i>
     * Removes from storage list all elements within the specified list.
     * Reads elements form specified list as Integer value to utilize remove(Integer o) method.
     * @param os Specified list of elements to remove
     * @return
     */
    public boolean removeAll(int[] os) {
        for (Integer i : os)
            remove(i);
        return true;
    }

    /**
     * Returns the number of elements in the storage list.
     * @return int Storage list size
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the storage list contains no elements, otherwise false.
     * @return boolean True if size of list is 0, False otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the hash code value for the storage list.
     * Hash code value is defined as the sum of all elements in the storage list.
     * @return int Sum of elements in storage list
     */
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++)
            hash += get(i);
        return hash;
    }

    /**
     * Overrides method in Object
     * @return string This returns string of all integers in storage ex. {1, 2, 3}
     */
    public String toString() {
        String s = "{";
        if (size >= 1) {
            s += get(0);
            for (int i = 1; i < size; i++)
                s += ", " + get(i);
        }
        s += "}";
        return s;
    }

    /**
     * Compares the specified object with this storage list for equality.
     * Checks for equality in class and content.
     * @param o Specified object for comparison
     * @return boolean True if the objects are equivalent, otherwise false
     */
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass())
            if (this.contentEquals((IntegerStorage)o))
                return true;
        return false;
    }

    /**
     * Compares the specified IntegerStorage with this storage list for content equality,
     * both in terms of values and order.
     * @param o IntegerStorage object for comparison
     * @return boolean True if the contents are equivalent, otherwise false2
     */
    public boolean contentEquals(IntegerStorage o) {
        if (this.size() != o.size())
            return false;

        for (int i = 0; i < this.size; i++)
            if (this.get(i) != o.get(i))
                return false;

        return true;
    }

    /**
     * Sorts the storage list into ascending order.
     */
    public void sort() {
        int initialSize=this.size();
        int[] tempArray=new int[initialSize];
        for (int index=0; index<initialSize; index++){
            int min=this.get(0);
            int minIndex=0;
            for (int i=0; i<this.size; i++){
                if (this.get(i)<min){
                    min=this.get(i);
                    minIndex=i;
                }
            }
            tempArray[index]=this.get(minIndex);
            this.remove(minIndex);
        }
        this.clear();
        this.addAll(tempArray);
    }
}
