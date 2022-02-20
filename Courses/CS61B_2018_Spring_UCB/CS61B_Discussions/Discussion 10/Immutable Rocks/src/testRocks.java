import org.junit.Test;

public class testRocks {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] rox = {testOne, testTwo, testThree};
        Rocks test = new Rocks(rox);
        System.out.println(test.rocks[0].weight);
        test.rocks[0] = new Rock(4);
        System.out.println(test.rocks[0].weight);
    }
}
