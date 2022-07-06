package Exception;

public class Testing2 {

    public void test() throws MyException {
        boolean flag = true;
        if (flag) {
            throw new MyException("Oh my 2!");
        }
    }

    public void test1() throws MyException {
        this.test();
    }

    public void testWrong() {
        // this.test(); does not work!
    }

    public void testHandle() {
        try {
            this.test();
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
