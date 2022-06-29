package Employee;

public class CodingMonkeyUniEmployee extends Employee implements Teaching{

    private String company;
    private int solvedLeetCodeProblem;

    public CodingMonkeyUniEmployee(String _name, float _salary, int _level, String _company, int _solvedLeetCodeProblem) {
        super(_name, _salary, _level);
        company = _company;
        solvedLeetCodeProblem = _solvedLeetCodeProblem;
    }

    @Override
    public void printInfo() {
        System.out.println("This CMU staff is also works at " + company + " and he have solved " + solvedLeetCodeProblem + " LeetCode problems.");
    }

    @Override
    public void teach() {
        System.out.println("You guys solve LeetCode problems now!");
    }
}
