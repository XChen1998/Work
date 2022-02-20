import org.junit.Test;

public class testPunkRock {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] merged = {testOne, testTwo, testThree};
        PunkRock test = new PunkRock(merged);
        merged[0] = testThree;
        // System.out.println(test.rocks[0].weight); not visible
        Rock[] external = test.myBand();
        external[0] = new Rock(4);
    }
}
