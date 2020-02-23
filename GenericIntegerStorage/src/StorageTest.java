/**
 * <h1>Storage Test Class</h1>
 * This is a class to test the implementation of StorageInterface.
 * Tests both applications of the interface, Array List and Linked List, with three different Comparable types:
 *  1. Integer
 *  2. Character
 *  3. String
 * Then evaluates equivalencies between both classes given Type Character.
 *
 * @author Brian Zarzuela
 * @version 1.0, 2019-02-24
 * @since 2019-02-18
 */

public class StorageTest
{
    @SuppressWarnings("unchecked")
    private static void integerTest(StorageAbstract sa)
    {
        //Test Header
        System.out.println("\nNow testing: " + sa.getClass() + "<Integer>");

        //Test add(T o) & toString()
        System.out.println("sa: " + sa);
        sa.add(1);
        System.out.println("Should be \n\t{1}, is \n\t" + sa);

        //Test addAll(T[] os)
        sa.addAll(new Comparable[]{1,2,3});
        System.out.println("Should be \n\t{1, 1, 2, 3}, is \n\t" + sa);
        //Test addAll(int index, T[] os)
        sa.addAll(0,new Comparable[]{2, 102, 33});
        System.out.println("Should be \n\t{2, 102, 33, 1, 1, 2, 3}, is \n\t" + sa);

        //Test add(int index, T element)
        sa.add(1, 999);
        System.out.println("Should be \n\t{2, 999, 102, 33, 1, 1, 2, 3}, is \n\t" + sa);

        //Test size
        System.out.println("Should be 8, is " + sa.size());

        //Test contains(T)
        System.out.println("Should be false, is " + sa.contains(4));
        System.out.println("Should be true, is " + sa.contains(999));

        //Test get(int index)
        System.out.println("Should be 102, is " + sa.get(2));

        //Test indexOf(T o)
        System.out.println("Should be 0, is " + sa.indexOf(2));
        //Test lastIndexOf(T o)
        System.out.println("Should be 6, is " + sa.lastIndexOf(2));

        //Test remove(int index)
        sa.remove(0);
        System.out.println("Should be \n\t{999, 102, 33, 1, 1, 2, 3}, is \n\t" + sa);
        //Test remove(T o)
        sa.remove((Comparable) 3);
        System.out.println("Should be \n\t{999, 102, 33, 1, 1, 2}, is \n\t" + sa);

        //Test set(int index, T element)
        sa.set(2, 4);
        System.out.println("Should be \n\t{999, 102, 4, 1, 1, 2}, is \n\t" + sa);

        //Test containsAll(T[] os)
        System.out.println("Should be false, is " + sa.containsAll(new Comparable[]{1,2,3}));
        System.out.println("Should be true, is " + sa.containsAll(new Comparable[]{4, 1, 999}));

        //Test removeAll(T[] os)
        sa.removeAll(new Comparable[]{1, 4});
        System.out.println("Should be \n\t{999, 102, 1, 2}, is \n\t" + sa);

        //Test sort()
        sa.sort();
        System.out.println("Should be \n\t{1, 2, 102, 999}, is \n\t" + sa);

        //Test hashCode()
        System.out.println("Hash code should be 1+2+102+999 = \n\t1104, is \n\t" + sa.hashCode());

        //Test isEmpty()
        System.out.println("Should be false, is " + sa.isEmpty());

        //Test clear()
        System.out.println("Clearing storage...");
        sa.clear();
        System.out.println(sa + "\nsize: " + sa.size());

        //Test isEmpty()
        System.out.println("Should be true, is " + sa.isEmpty());
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private static void characterTest(StorageAbstract sa)
    {
        //Test Header
        System.out.println("\nNow testing: " + sa.getClass() + "<Character>");

        //Test add(T o) & toString()
        System.out.println("sa: " + sa);
        sa.add('a');
        System.out.println("Should be \n\t{a}, is \n\t" + sa);

        //Test addAll(T[] os)
        sa.addAll(new Comparable[]{'c','d','g'});
        System.out.println("Should be \n\t{a, c, d, g}, is \n\t" + sa);
        //Test addAll(int index, T[] os)
        sa.addAll(0,new Comparable[]{'x', 't'});
        System.out.println("Should be \n\t{x, t, a, c, d, g}, is \n\t" + sa);

        //Test add(int index, T element)
        sa.add(1, 'g');
        System.out.println("Should be \n\t{x, g, t, a, c, d, g}, is \n\t" + sa);

        //Test size
        System.out.println("Should be 7, is " + sa.size());

        //Test contains(T)
        System.out.println("Should be false, is " + sa.contains('b'));
        System.out.println("Should be true, is " + sa.contains('g'));

        //Test get(int index)
        System.out.println("Should be t, is " + sa.get(2));

        //Test indexOf(T o)
        System.out.println("Should be 1, is " + sa.indexOf('g'));
        //Test lastIndexOf(T o)
        System.out.println("Should be 6, is " + sa.lastIndexOf('g'));

        //Test remove(int index)
        sa.remove(0);
        System.out.println("Should be \n\t{g, t, a, c, d, g}, is \n\t" + sa);
        //Test remove(T o)
        sa.remove((Comparable) 'c');
        System.out.println("Should be \n\t{g, t, a, d, g}, is \n\t" + sa);

        //Test set(int index, T element)
        sa.set(2, 'w');
        System.out.println("Should be \n\t{g, t, w, d, g}, is \n\t" + sa);

        //Test containsAll(T[] os)
        System.out.println("Should be false, is " + sa.containsAll(new Comparable[]{1,2,3}));
        System.out.println("Should be true, is " + sa.containsAll(new Comparable[]{4, 1, 999}));

        //Test removeAll(T[] os)
        sa.removeAll(new Comparable[]{'g', 'h'});
        System.out.println("Should be \n\t{t, w, d, g}, is \n\t" + sa);

        //Test sort()
        sa.sort();
        System.out.println("Should be \n\t{d, g, t, w}, is \n\t" + sa);

        //Test hashCode()
        System.out.println("Hash code, should be 100+103+116+119 = \n\t438 is \n\t" + sa.hashCode());

        //Test isEmpty()
        System.out.println("Should be false, is " + sa.isEmpty());

        //Test clear()
        System.out.println("Clearing storage...");
        sa.clear();
        System.out.println(sa + "\nsize: " + sa.size());

        //Test isEmpty()
        System.out.println("Should be true, is " + sa.isEmpty());
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private static void stringTest(StorageAbstract sa)
    {
        //Test Header
        System.out.println("\nNow testing: " + sa.getClass() + "<String>");

        //Test add(T o) & toString()
        System.out.println("sa: " + sa);
        sa.add("one");
        System.out.println("Should be \n\t{one}, is \n\t" + sa);

        //Test addAll(T[] os)
        sa.addAll(new Comparable[]{"fish", "fish"});
        System.out.println("Should be \n\t{one, fish, fish}, is \n\t" + sa);
        //Test addAll(int index, T[] os)
        sa.addAll(2,new Comparable[]{"or", "two"});
        System.out.println("Should be \n\t{one, fish, or, two, fish}, is \n\t" + sa);

        //Test add(int index, T element)
        sa.add(0, "hi!");
        System.out.println("Should be \n\t{hi!, one, fish, or, two, fish}, is \n\t" + sa);

        //Test size
        System.out.println("Should be 6, is " + sa.size());

        //Test contains(T)
        System.out.println("Should be false, is " + sa.contains("blue"));
        System.out.println("Should be true, is " + sa.contains("two"));

        //Test get(int index)
        System.out.println("Should be one, is " + sa.get(1));

        //Test indexOf(T o)
        System.out.println("Should be 2, is " + sa.indexOf("fish"));
        //Test lastIndexOf(T o)
        System.out.println("Should be 5, is " + sa.lastIndexOf("fish"));

        //Test remove(int index)
        sa.remove(0);
        System.out.println("Should be \n\t{one, fish, or, two, fish}, is \n\t" + sa);
        //Test remove(T o)
        sa.remove( "or");
        System.out.println("Should be \n\t{one, fish, two, fish}, is \n\t" + sa);

        //Test set(int index, T element)
        sa.set(2, "blue");
        System.out.println("Should be \n\t{one, fish, blue, fish}, is \n\t" + sa);

        //Test containsAll(T[] os)
        System.out.println("Should be false, is " + sa.containsAll(new Comparable[]{"two", "fish"}));
        System.out.println("Should be true, is " + sa.containsAll(new Comparable[]{"blue", "fish"}));

        //Test removeAll(T[] os)
        sa.removeAll(new Comparable[]{"fish", "fish"});
        System.out.println("Should be \n\t{one, blue}, is \n\t" + sa);

        //Test sort()
        sa.sort();
        System.out.println("Should be \n\t{blue, one}, is \n\t" + sa);

        //Test hashCode()
        System.out.println("Hash code is " + sa.hashCode());

        //Test isEmpty()
        System.out.println("Should be false, is " + sa.isEmpty());

        //Test clear()
        System.out.println("Clearing storage...");
        sa.clear();
        System.out.println(sa + "\nsize: " + sa.size());

        //Test isEmpty()
        System.out.println("Should be true, is " + sa.isEmpty());
        System.out.println();
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public static void main(String[] args)
    {
        // Integer Tests
        ArrayList<Integer> ali = new ArrayList<>();
        integerTest(ali);
        LinkedList<Integer> ili = new LinkedList<>();
        integerTest(ili);

        // Character Tests
        ArrayList<Character> alc = new ArrayList<>();
        characterTest(alc);
        LinkedList<Character> ilc = new LinkedList<>();
        characterTest(ilc);

        // String Tests
        ArrayList<String> als = new ArrayList<>();
        stringTest(als);
        LinkedList<String> ils = new LinkedList<>();
        stringTest(ils);

        //Equivalency Test
        alc.addAll(new Character[]{'a','b','b'});
        ilc.addAll(new Character[]{'a','b'});
        System.out.println("Array : " + alc + "\n" + "Linked: " + ilc);
        System.out.println("Contents Equal ? " + alc.contentEquals(ilc));
        System.out.println("Equal ? " + alc.equals(ilc));
        ilc.add('b');
        System.out.println("Array : " + alc + "\n" + "Linked: " + ilc);
        System.out.println("Contents Equal ? " + alc.contentEquals(ilc));
        System.out.println("Equal ? " + alc.equals(ilc));
        ArrayList<Character> alc2 = new ArrayList<>();
        alc2.addAll(new Character[]{'a','b','b'});
        System.out.println("Array1 : " + alc + "\n" + "Array2 : " + alc2);
        System.out.println("Contents Equal ? " + alc.contentEquals(ilc));
        System.out.println("Equal ? " + alc.equals(alc2));
    }


}