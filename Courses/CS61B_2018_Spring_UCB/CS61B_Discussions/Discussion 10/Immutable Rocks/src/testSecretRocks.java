import org.junit.Test;

public class testSecretRocks {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] rox = {testOne, testTwo, testThree};
        SecretRocks test = new SecretRocks(rox);
        // System.out.println(test.rocks[0].weight); not visible
        rox[0] = new Rock(4);
        rox[1] = new Rock(5);
        rox[2] = new Rock(6);

    }
}
