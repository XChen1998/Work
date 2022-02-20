public class Horse {
    Horse same;
    String jimmy;

    public Horse(String lee) {
        jimmy = lee;
    }

    public Horse same(Horse horse) {
        if (same != null) { // this same refers to cult, same = cult -> if statement true
            Horse same = horse; // same = horse -> jimmy = youve been, same = null
            same.same = horse;  // same.same (horse.same) = horse
            same = horse.same; // same = horse.same = horse -> same = horse -> rudundant
        }
        return same.same;
    }

    public static void main(String[] args) {
        Horse horse = new Horse("youve been"); // horse: jimmy = youve been, same = null
        Horse cult = new Horse("horsed"); //cult: jimmy = horsed
        cult.same = cult; // cult: jimmy = horsed, same = cult
        cult = cult.same(horse);
        System.out.println(cult.jimmy); // horsed
        System.out.println(horse.jimmy); // youve been
    }
}