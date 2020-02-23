/**
 * <h1>Integer Array List Class</h1>
 * This is a class extending the IntegerStorageImplementation to create an Integer Array List.
 *
 * @author Brian Zarzuela
 * @version 1.0, 2019-02-13
 * @since 2019-02-11
 */

class IntegerArrayList extends IntegerStorageImplementation{

    int initialCapacity;
    int capacity;
    int storage[];

    /**
     * Constructor for class IntegerArrayList
     */
    public IntegerArrayList(){
        super();
        this.storage = new int[0];
        initialCapacity = 0;
        capacity = 0;
    }

    /**
     * Appends specified element to the end of the storage list
     * Copies content of storage array into a new array of length storage.length + 1
     * Adds new integer to the end of the new array
     * Replaces storage array with new array
     * @param o Integer to be added to the end of the ArrayList
     * @return
     */
    public boolean add(int o) {
        int add[] = new int[capacity + 1];
        System.arraycopy(storage, 0, add, 0, capacity);
        add[size()] = o;
        storage = add;
        capacity = storage.length;
        size = capacity;
        return true;
    }

    /**
     * Inserts specified element at the specified position of the storage list.
     * @param index Specified position
     * @param element Specified element to insert
     */
    public void add(int index, int element) {
        int add[] = new int[capacity + 1];
        System.arraycopy(storage, 0, add, 0, index);
        add[index] = element;
        System.arraycopy(storage, index, add, index + 1, capacity - index);
        storage = add;
        capacity = storage.length;
        size = capacity;
    }

    /**
     * Removes all elements from the storage list.
     */
    public void clear() {
        storage = new int[0];
        capacity = storage.length;
        size = capacity;
    }

    /**
     * Returns true if the storage list contains the specified element.
     * @param o Specified element
     * @return boolean True if element is contained in list, otherwise false
     */
    public boolean contains(int o) {
        for (int i : storage)
            if (i == o)
                return true;
        return false;
    }

    /**
     * Returns the element at the specified position in the storage list.
     * @param index Specified position
     * @return int Element at specified position in list
     */
    public int get(int index) {
        return storage[index];
    }

    /**
     * Returns the index of the first occurrence of the specified element in the storage list,
     * or returns -1 by default if the element is not in the list.
     * @param o Specified element
     * @return int Index of first occurrence of specified element in list or -1 if element is not in list
     */
    public int indexOf(int o) {
        for (int i = 0; i < capacity; i++)
            if (storage[i] == o)
                return i;
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in the storage list,
     * or returns -1 by default if the element is not in the list.
     * @param o Specified element
     * @return int Index of last occurrence of specified element in list or -1 if element is not in list
     */
    public int lastIndexOf(int o) {
        int index = -1;
        for (int i = 0; i < capacity; i++)
            if (storage[i] == o)
                index = i;
        return index;
    }

    /**
     * <i>(optional operation)</i>
     * Removes the element at the specified position from the list.
     * @param index Specified position
     * @return int Element removed
     */
    public int remove(int index) {
        if (index < 0 || index > capacity)
            return -1;
        else {
            int remove[] = new int[capacity - 1];
            int removed = storage[index];
            System.arraycopy(storage, 0, remove, 0, index);
            System.arraycopy(storage, index + 1, remove, index, capacity - index - 1);
            storage = remove;
            capacity = storage.length;
            size = capacity;
            return removed;
        }
    }

    /**
     * Removes first occurrence of a specified Integer from the storage list, if it is present.
     * @param o Specified Integer to be removed
     * @return boolean True if Integer is successfully removed, false if the Integer was not in the list
     */
    public boolean remove(Integer o) {
        int index = indexOf(o);
        if (index == -1)
            return false;
        else {
            int remove[] = new int[capacity - 1];
            System.arraycopy(storage, 0, remove, 0, index);
            System.arraycopy(storage, index + 1, remove, index, capacity - index - 1);
            storage = remove;
            capacity = storage.length;
            size = capacity;
            return true;
        }
    }

    /**
     * Replaces the element at the specified position of the storage list with the specified element
     * @param index Specified position
     * @param element Specified element to place
     * @return int Element previously in specified position
     */
    public int set(int index, int element) {
        int set[] = new int[capacity];
        System.arraycopy(storage, 0, set, 0, index);
        set[index] = element;
        int temp = storage[index];
        System.arraycopy(storage, index + 1, set, index + 1, capacity - index - 1);
        storage = set;
        capacity = storage.length;
        size = capacity;
        return temp;
    }

}
