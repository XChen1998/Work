import org.junit.Test;

public class Pebble {
    public int weight;

    public Pebble() {
        weight = 1;
    }

    @Test
    public void testPebble() {
        Pebble test = new Pebble();
        System.out.println(test.weight);
        test.weight = 2;
        System.out.println(test.weight);
    }
}

