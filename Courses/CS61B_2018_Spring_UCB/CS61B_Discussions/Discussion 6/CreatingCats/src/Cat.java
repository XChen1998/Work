public class Cat extends Animal {

    public Cat(String Noise, String Name, int Age) {
        super(Name, Age);
        noise =Noise;
    }

    @Override
    public void greet(){
        System.out.println("Cat " + name + " says: " + makeNoise());
    }

    public static void main(String[] args) {
        Cat younger = new Cat("Meow!", "Kiki", 3);
        Cat elder = new Cat("Meow!", "Popo", 5);
        younger.greet();
        elder.greet();
    }
}
