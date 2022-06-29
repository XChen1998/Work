package Employee;

public class Main {

    public static void main(String[] args) {
        Employee ColumbiaStaff = new ColumbiaEmployee("Lion", 100000, 5, "li0000", "Columbia University");
        Employee CMUStaff = new CodingMonkeyUniEmployee("Coding Monster", 1000000, Integer.MAX_VALUE, "Google", Integer.MAX_VALUE);
        ColumbiaStaff.printInfo();
        CMUStaff.printInfo();

    }
}
