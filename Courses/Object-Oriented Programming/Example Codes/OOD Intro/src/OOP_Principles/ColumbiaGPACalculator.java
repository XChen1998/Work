package OOP_Principles;

public class ColumbiaGPACalculator {
    private float GPA;

    public float getGPA() {
        return this.GPA;
    }

    public void calculateGPA(Student s) {
        this.GPA = s.getGPA();
    }

    /*This is wrong*/
//    public void printGPAInJson() {
//        // Some code here
//    }
}


