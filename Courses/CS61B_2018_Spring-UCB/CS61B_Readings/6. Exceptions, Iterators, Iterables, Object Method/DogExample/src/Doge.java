public class Doge {

    public int age;
    public String name;

    public Doge(int age, String name){
        this.age = age;
        this.name = name;
    }
    public static void main(String[] args) {

        int x = 5;
        int y = 5;
        int z = 6;

        Doge fido = new Doge(5, "Fido");
        Doge doggo = new Doge(6, "Doggo");
        Doge fidoTwin = new Doge(5, "Fido");
        Doge fidoRealTwin = fido;

        System.out.println(x == y);
        System.out.println(x == z);
        System.out.println(fido == doggo);
        System.out.println(fido == fidoTwin);
        System.out.println(fido == fidoRealTwin);
    }
}