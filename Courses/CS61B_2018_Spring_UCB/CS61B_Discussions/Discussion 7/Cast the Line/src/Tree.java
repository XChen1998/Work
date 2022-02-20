/* Tree Class.*/
public class Tree {

    /* Default constructor.*/
    public Tree() {
    }

    /* Main programme.*/
    public static void main(String[] args) {
        /* Cat c = new Animal(); Compile error.*/
        Animal a = new Cat();
        /* Dog d = new Cat(); Compile error.*/
        /* Tree t = new Animal(); Compile error.*/

        Animal aa = (Cat) new Cat();
        Animal aaa = (Animal) new Cat();
        /*Run time error, cannot cast a subclass into its super class.*/
        Dog d = (Dog) new Animal();
        /*Cat c = (Cat) new Dog(); Compile error.*/
        /*Animal a = (Animal) new Tree(); Compile error.*/
    }
}

