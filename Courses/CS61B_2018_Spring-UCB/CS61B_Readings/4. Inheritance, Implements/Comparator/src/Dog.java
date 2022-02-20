import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Dog a){
        return this.size - a.size;
    }

    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.size - b.size;
        }
    }

    public static Comparator<Dog> getSizeComparator(){
        return new SizeComparator();
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }
}