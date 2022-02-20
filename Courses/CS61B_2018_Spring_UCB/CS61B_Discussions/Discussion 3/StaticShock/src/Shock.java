public class Shock {
    public static int bang;
    public static Shock baby;

    public Shock() {
        this.bang = 100;
    }

    public Shock(int num) {
        this.bang = num;
        baby = starter();
        this.bang += num;
    }

    public static Shock starter() {
        Shock gear = new Shock();
        return gear;
    }

    public static void shrink(Shock statik) {
        statik.bang -= 1;
    }

    public static void main(String[] args) {
        Shock gear = new Shock(200);  // gear.bang = 200 -> baby.bang = 100, gear.bang = 100 -> gear.bang = 300
        System.out.println(gear.bang); // print 300
        shrink(gear); // bang = 299
        shrink(starter()); // bang = 99
        System.out.println(gear.bang); // print 99
    }
}