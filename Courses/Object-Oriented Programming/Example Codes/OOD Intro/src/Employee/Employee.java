package Employee;

public class Employee {

    /*Attributes*/
    protected String name;
    protected float salary;
    protected int level;

    /*Default constructor*/
    public Employee() {}

    /*Parameterised constructor*/
    public Employee(String _name, float _salary, int _level) {
        name = _name;
        salary = _salary;
        level = _level;
    }

    /*Member functions to interact with an object*/
    public void raiseSalary(float number) {
        salary += number;
    }

    public void printInfo() {
        System.out.println(name);
    }

    public void promoteLevel(int promotionLevel) {
        level += promotionLevel;
    }
}
