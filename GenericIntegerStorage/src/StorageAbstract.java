/**
 * <h1>Abstract Storage Class</h1>
 * Implements Storage Interface to create a generic storage
 *
 * @author Brian Zarzuela
 * @param <T>
 * @version 1.0, 2019-02-19
 * @since 2019-02-18
 */

public abstract class StorageAbstract<T extends Comparable> implements StorageInterface<T>{

    int size;

    /**
     * Constructor for generic storage.
     * Sets size to 0.
     */
    StorageAbstract(){
        this.size = 0;
    }

    /**
     * Appends all of the elements in the specified array to the end of this list,
     * in the same order that they appear in the array.
     * @param os Specified array of elements
     * @return boolean Always true
     */
    public boolean addAll(T[] os) {
        for (T el : os)
            add(el);
        return false;
    }

    /**
     * Inserts all of the elements in the specified array into this list,
     * at the specified position and in the same order that they appear in the array.
     * @param index Specified position
     * @param os Specified array of elements
     * @return boolean Always true
     */
    public boolean addAll(int index, T[] os) {
        for (T el : os) {
            add(index, el);
            index++;
        }
        return true;
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param os Specified collection
     * @return boolean True if all elements of specified collection are within this list, otherwise False
     */
    public boolean containsAll(T[] os) {
        for (T el: os)
            if (!contains(el))
                return false;
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param os Specified collection
     * @return boolean Always true
     */
    public boolean removeAll(T[] os) {
        for (T el: os)
            remove(el);
        return true;
    }

    /**
     * Returns the number of elements in this storage.
     * @return int Storage size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if the storage is empty.
     * @return boolean True if the storage size is 0, otherwise False
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the hash code value for this storage.
     * Defined as the sum of the element hash codes in the storage.
     * @return int Hash code value for this storage
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++)
            hash += get(i).hashCode();
        return hash;
    }

    /**
     * Pretty print the storage
     * @return String All elements in the storage ex: {1, a, 2, d}
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        if (size >= 1) {
            s.append(get(0));
            for (int i = 1; i < size; i++)
                s.append(", ").append(get(i));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares the specified object with this storage for equality
     * @param o Specified object
     * @return boolean True if the object and this storage are equal, otherwise False
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass())
            return (contentEquals((StorageInterface) o));
        return false;
    }

    /**
     * Compares the specified StorageInterface for content equality both in terms of values and order.
     * @param o Specified StorageInterface
     * @return boolean True if the contents are equal, otherwise False
     */
    public boolean contentEquals(StorageInterface o) {
        if (size != o.size())
            return false;
        for (int i = 0; i < size; i++)
            if (get(i) != o.get(i))
                return false;
        return true;
    }

    /**
     * Sorts this storage into ascending order.
     * Sorts by hash code value because compareTo is not possible between base types.
     */
    public void sort() {
        T temp;
        for (int outer = 0; outer < size - 1; outer++)
            for (int inner = 0; inner < size - outer - 1; inner++) {
                if (get(inner).hashCode() > get(inner + 1).hashCode()) {
                    temp = get(inner);
                    set(inner, get(inner + 1));
                    set(inner + 1, temp);
                }
            }
    }
}
