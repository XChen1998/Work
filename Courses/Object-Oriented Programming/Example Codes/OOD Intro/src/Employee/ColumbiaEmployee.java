package Employee;

public class ColumbiaEmployee extends Employee implements Research, Teaching{
    private String uni;
    private String department;

    public ColumbiaEmployee(String _name, float _salary, int _level, String _uni, String _department) {
        super(_name, _salary, _level);
        uni = _uni;
        department = _department;
    }

    @Override
    public void printInfo() {
        System.out.println(name + " (" + uni + ")" + ", " + department);
    }

    @Override
    public void teach() {
        System.out.println("Do something");
    }

    @Override
    public void research() {
        System.out.println("Do something");
    }
}
