public class Pokemon {
    public String name;
    public int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        Pokemon poke = new Pokemon("Pikachu", 17);
        int level = 100;
        change(poke, level);
        System.out.println("Name: " + poke.name + ", Level: " + poke.level);
    }

    public static void change(Pokemon poke, int level) {
        poke.level = level;
        level = 50;
        poke = new Pokemon("Gengar", 1);
    }
}
