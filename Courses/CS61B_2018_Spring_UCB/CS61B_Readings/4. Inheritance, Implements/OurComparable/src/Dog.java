public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
/*This method is redundant, but we have a better way.*/
/*    public int compareTo(Object o) {
        *//*Cast the object to dog.*//*
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }*/

    public int compareTo(Object o){
        return this.size - ((Dog) o).size;
    }
}