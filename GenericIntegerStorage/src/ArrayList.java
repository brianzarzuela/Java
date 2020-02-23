/**
 * <h1>Generic Array List Class</h1>
 * This is a class extending the abstract class StorageAbstract to create a generic array list.
 *
 * @author Brian Zarzuela
 * @param <T>
 * @version 1.1, 2019-02-21
 * @since 2019-02-18
 */

class ArrayList<T extends Comparable> extends StorageAbstract<T>{

    private T[] storage;

    /**
     * Constructor for class ArrayList
     */
    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public ArrayList(){
        super();
        storage = (T[]) new Comparable[0];
    }

    /**
     * Appends the specified element to the end of the list
     * @param o Specified element
     * @return boolean True if element was successfully added
     */
    @SuppressWarnings("unchecked")
    public boolean add(T o) {
        T[] add = (T[]) new Comparable[size + 1];
        System.arraycopy(storage, 0, add, 0, size);
        add[size] = o;
        storage = add;
        size = storage.length;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index Specified position
     * @param element Specified element
     */
    @SuppressWarnings("unchecked")
    public void add(int index, T element) {
        if (index < 0 || index >= size)
            return;
        T[] add = (T[]) new Comparable[size + 1];
        System.arraycopy(storage, 0, add, 0, index);
        add[index] = element;
        System.arraycopy(storage, index, add, index + 1, size - index);
        storage = add;
        size = storage.length;
    }

    /**
     * Removes all of the elements from this list.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        storage = (T[]) new Comparable[0];
        size = storage.length;
    }

    /**
     * Checks if this list contains the specified element.
     * @param o Specified element
     * @return boolean True if element is contained within storage, otherwise False
     */
    public boolean contains(T o) {
        for (T el : storage)
            if (el == o)
                return true;
        return false;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param index Specified position
     * @return T Element found in position index
     */
    public T get(int index) {
        if (index < 0 || index >= size)
            return null;
        return storage[index];
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * Returns -1 if this list does not contain the element.
     * @param o Specified element
     * @return int Index of first occurrence of specified element or -1 if it is not found
     */
    public int indexOf(T o) {
        for (int i = 0; i < size; i++)
            if (storage[i] == o)
                return i;
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * Returns -1 if this list does not contain the element.
     * @param o Specified element
     * @return int Index of last occurrence of specified element or -1 if it is not found
     */
    public int lastIndexOf(T o) {
        if (size == 0)
            return -1;
        for (int i = size - 1; i >= 0; i--)
            if (storage[i] == o)
                return i;
        return -1;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index Specified position
     * @return T Element being removed
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size)
            return null;
        T removed = storage[index];
        T[] remove = (T[]) new Comparable[size - 1];
        System.arraycopy(storage, 0, remove, 0, index);
        System.arraycopy(storage, index + 1, remove, index, size - index - 1);
        storage = remove;
        size = storage.length;
        return removed;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o Specified element
     * @return boolean True if element was present and removed, otherwise False
     */
    public boolean remove(T o) {
        int index = indexOf(o);
        if (index == -1)
            return false;
        else {
            remove(index);
            return true;
        }
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     * @param index Specified position
     * @param element Specified element
     * @return T Element replaced
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index < 0 || index >= size)
            return null;
        T replaced = storage[index];
        T[] set = (T[]) new Comparable[size];
        System.arraycopy(storage, 0, set, 0, index);
        System.arraycopy(storage, index + 1, set, index + 1, size - index - 1);
        set[index] = element;
        storage = set;
        size = storage.length;
        return replaced;
    }
}
