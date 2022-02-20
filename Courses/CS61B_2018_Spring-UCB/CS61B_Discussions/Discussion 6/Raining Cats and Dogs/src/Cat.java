public class Cat extends Animal {

    public Cat(String Name, int Age) {
        super(Name, Age);
        noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
    }
}
