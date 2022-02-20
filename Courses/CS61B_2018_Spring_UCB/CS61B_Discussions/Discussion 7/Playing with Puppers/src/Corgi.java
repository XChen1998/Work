public class Corgi extends Dog {
    public void bark(Corgi c) { /* Method B */
        System.out.println("B");
    }

    @Override
    public void bark(Dog d) { /* Method C */
        System.out.println("C");
    }

    public void play(Dog d) { /* Method D */
        System.out.println("D");
    }

    public void play(Corgi c) { /* Method E */
        System.out.println("E");
    }


    public static void main(String[] args) {
        Dog d = new Corgi(); // Static type Dog, dynamic type Corgi
        Corgi c = new Corgi(); // Static type Corgi, dynamic type Corgi

        //d.play(d); compile-time error
        //d.play(c); compile-time error

        c.play(d);  // D
        c.play(c);  // E
        c.bark(d);  // C
        c.bark(c);  // B
        d.bark(d);  // C
        d.bark(c);  // C Use static type to check so has to go to function that overrides bark() in Dog
    }
}
