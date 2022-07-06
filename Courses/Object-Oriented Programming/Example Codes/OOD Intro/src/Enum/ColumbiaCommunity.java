package Enum;

public class ColumbiaCommunity {

    public enum People {
        Staff, Student, Alumni
    }

    public static void main(String[] args) {
        People alu = People.Alumni;
        People stu = People.Student;
        People Sta = People.Staff;
    }
}


