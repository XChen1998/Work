import java.util.Comparator;

public class DogLauncher {
    public static void main(String[] args) {
        Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
        Comparator<Dog> compareSize = Dog.getSizeComparator();
        Comparator<Dog> compareName = Dog.getNameComparator();

        if (compareSize.compare(dogs[1], dogs[2]) > 0){
            dogs[1].bark();
        } else {
            dogs[2].bark();
        }

        if (compareName.compare(dogs[1], dogs[2]) > 0){
            dogs[1].bark();
        } else {
            dogs[2].bark();
        }

        if (compareSize.compare(dogs[1], dogs[0]) > 0){
            dogs[1].bark();
        } else {
            dogs[0].bark();
        }

        if (compareName.compare(dogs[1], dogs[0]) > 0){
            dogs[1].bark();
        } else {
            dogs[0].bark();
        }

    }
}