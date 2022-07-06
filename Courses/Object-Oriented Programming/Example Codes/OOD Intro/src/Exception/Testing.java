package Exception;

public class Testing {
    public void test(){
        try {
            boolean flag = true;
            if (flag) {
                throw new MyException("Oh my!");
            }
        } catch (MyException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
