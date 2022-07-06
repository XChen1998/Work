# Object-Oriented Programming & Design

**Author**: Ziggy (Ruofan) Chen, MSCS@Columbia University in the City of New York '23, xinghe.c at columbia.edu

Jun. 2022

**Statement**: Please note that this work is for reference only. It briefly introduce the philosophy of object-oriented programming and design (OOP/OOD), which is an essential part of an entry level SDE interview. In this work, we will mainly use *OOP*, as there is no significant differece between OOP and OOD.



## I. What is OOP?

The key philosophy of OOP includes *encapsulation, inheritance and polymorphism*. We will now introduce each one of them.



### 1. Encapsulation (封装)

The very basic idea of encapsulation is very much like what we have already introduced in my [CS61B notes](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Notes/CS61B_Notes.md) at section IV.2.E: 

> #### E. Encapsulation
>
> The idea of encapsulation is make your class like a pre-defined tool. A user does not need to know any knowledge of the information inside the class. ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Encapsulation.png?raw=true)
>
> Take our `ArrayDeque` as an example, the hidden `Item` variable is almost not readable, but with our helper method, the user can manipulate an `ArrayDeque` easily. In the author's opinion, **the philosophy of encapsulation is to make our class easy to use (since we have helper methods for users), at the same time, hard to break (since we do not give users access to the hidden information. i.e. we add many `private` access modifier)**.



So basically, we do not want to deal with specific functions, what we want is a series of objects that have exactlly the same functions. We encapsulate them to be a class. As an example, we can have an `Employee` class with *attributes* including `String name`, `float salary` and `int level`. These *attributes* may be private variables, which means they are not visible to the world. However, in each class, we may have *member functions* to get or set them attributes. See example class below for reference:

```Java
public class Employee {
    
    /*Attributes*/
    private String name;
    private float salary;
    private int level;
    
    /*Member functions to interact with an object*/
    public void raiseSalary(float number) {
        salary += number;
    }

    public void printName() {
        System.out.println(name);
    }

    public void promoteLevel(int promotionLevel) {
        level += promotionLevel;
    }
}

```



### 2. Inheritance (继承)

The philosophy of inhertance is that we want to create a class hierarchy. For example, we may create a sub-class of the `Employee` class as follows:

```Java
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

```

```java
public class ColumbiaEmployee extends Employee{
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

    public static void main(String[] args) {
        Employee staff = new ColumbiaEmployee("Lion", 100000, 5, "li0000", "Columbia University");
        staff.printInfo();
    }
}

```



We may notice that an employee from Columbia University has two more attributes, `String uni` and `String department`. We may override the `printInfo()` function in the super-class. We do not need to define any existed attributes in the `Employee` class, there are inherited naturally. This feature avoid repeated codes in an efficient way.



Another way to implement inheritance is implment an `interface`. **An interface is very much like a job contract and a father-class is your affliction.** Therefore, we may have as many job contracts as we wish but we may only have one afflication or, namely, father. In the Columbia University employee example, we may ask all Columbia employees to sign a teaching contract and a research contract, respectively. Be be aware that an interface cannot be initialised and all methods are abstract methods--no implmentations.

```Java
public interface Teaching {
    public void teach();
}

```

```Java
public interface Research {
    public void research();
}

```



However, we ask Columbia staff to sign both of the contracts, and therefore they have to implements both of the interfaces.

```Java
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
  
    public static void main(String[] args) {
        Employee staff = new ColumbiaEmployee("Lion", 100000, 5, "li0000", "Columbia University");
        staff.printInfo();
    }
}



```



### 3. Polymorphism (多态)

Actually, in the declaration of an `ColumbiaEmployee`, we have already exploited the polymorphism features, as we did `Employee staff = new ColumbiaEmployee("Lion", 100000, 5, "li0000", "Columbia University");`. That says, we may want to create more employee classes of many institutions. We may initialise an `Employee` with any specific `Employee`. See example as follows:



***Statement**: No offence, take it easy xD. CMU staffs may have ten times salarys compared with a Columbia staff.*

```Java
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

```

```Java
public class Main {

    public static void main(String[] args) {
        Employee ColumbiaStaff = new ColumbiaEmployee("Lion", 100000, 5, "li0000", "Columbia University");
        Employee CMUStaff = new CodingMonkeyUniEmployee("Coding Monster", 1000000, Integer.MAX_VALUE, "Google", Integer.MAX_VALUE);
        ColumbiaStaff.printInfo();
        CMUStaff.printInfo();

    }
}

```



## II. Exceptions & `enum` Classes

### 1. Exceptions

There are two types of exceptions in Java:

* Checked exceptions, which usually are compile-time exceptions or IO exceptions
* Unchecked exceptions, which are runtime exceptions (e.g. null pointer exceptions)

 

So how do we handel exceptions in OOP? See the following example for your reference:



We can create a very basic exception class that extends the Java `Excpetion` class and define its constructor as follows:

```Java
package Exception;

public class MyException extends Exception{
    public MyException(String s) {
        super(s);
    }
}

```



Than we write a `Testing` class which always throw a new `MyException` with the error message `Oh my!`:

```Java
package Exception;

public class Testing {
    public void test() {
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
```



