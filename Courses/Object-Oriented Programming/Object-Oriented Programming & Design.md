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



In the main function, we create an `Testing` object and call the `test` method, which returns `Oh my!`.

```Java
package Exception;

public class Main {
    public static void main(String[] args) {
        Testing t = new Testing();
        t.test();
    }
}
```



In general, we may define that a function may throw exceptions as follows:

```Java
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
```



We may handle the exception in any method that call that method or just add the same `throws MyException`. Both of them works fine.



### 2. `enum` Classes

This is just a superficial example of `enum` classes (枚举变量)

```Java
package Enum;

public class ColumbiaCommunity {

    public enum People {
        Staff, Student, Alumni
    }

    public static void main(String[] args) {
        People alu = People.Alumni;
        People stu = People.Student;
        People sta = People.Staff;
    }
}
```

If we know that there are certain instances that may happen, we can pre-define them in the `enum` function.



## III. Who should learn about OOP?

The main difference between OOP and system design is listed below:

|                 |              OOP               |             System Design              |
| :-------------: | :----------------------------: | :------------------------------------: |
| The interviewee | SDE I or below, new graduates  | above SDE I, experienced interviewees  |
|     Objects     | General Capability Examination |   Examination for specific services    |
|    Companies    |       Amazon, Uber, etc.       | Meta, Twitter (social media companies) |
|   Philosophy    |           Viability            |              Scalability               |
|     Example     |   Design an elevator system    |             Design Twitter             |



In general, beginners should learn more about OOP as their first step to SDE job hunting. It is not very likely that you will see OOP questions during a phone interview, whereas it is more popular during an onsite interview. 



OOP interviews are sometimes much more important compared with algorithm interviews as some companies may grant OOP questions veto power. Companies including Amazon, Bloomberg, TripAdvisor, EMC, Uber, etc. usually have OOP questions during an onsite interview.



## IV. The Basic Philoshophy of OOP (S.O.L.I.D Principles)



### 1. Single Responsibility Principle

A class should only be changed when there is a certain appropriate reason. That is, a class should be responsible for a work exactlly, and there is not other reasons to change the class.



For example, if there is a `ColumbiaGPACalculator` class shown as follows: 

```Java
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
```

If we want to add a function called `printGPAInJson`, then we should not make it inside the GPA calculator. Because the calculator itself is only responsible for calculating GPA. Nothing more!



What we should do instead is to add a `Printer` class that is responsible for visulisation stuffs.

```Java
package OOP_Principles;

public class Printer {
    public void printInJson(float number) {
        /*Some code here.*/
    }

    public void printInHTML(float number) {
        /*Some code here.*/
    }

}
```



### 2. Open Close Principle

The *Open Close Principle* can be explained as **open to extension and close to modification**. In the above-mentioned example, we make a `Student` variable as input argument so that the GPA calculator is very easy to be extended. The class hierarchy is shown below:



An interface namely `Student`:

```Java
package OOP_Principles;

public interface Student {
    float getGPA();
    void printName();
}
```



Two specific student classes that implments the interface:

```Java
package OOP_Principles;

public class PhysicsStudent implements Student{
    private float physicsGPA;

    @Override
    public float getGPA() {
        return physicsGPA;
    }

    @Override
    public void printName() {
        /*Some code.*/
    }
}
```

```Java
package OOP_Principles;

public class CSStudent implements Student {
    private float CSGPA;

    @Override
    public float getGPA() {
        return CSGPA;
    }

    @Override
    public void printName() {
        /*Some code here.*/
    }
}
```



We can add as many major-specific student class as we wish to extend the function of our GPA calculator, but we do not need to modify the `ColumbiaGPACalculator` itself.



### 3. Liskow Substitution Principle

> *[Reference link.](https://blog.knoldus.com/what-is-liskov-substitution-principle-lsp-with-real-world-examples/#:~:text=Simply%20put%2C%20the%20Liskov%20Substitution,the%20objects%20of%20our%20superclass.)*
>
> Simply put, the **Liskov Substitution Principle (LSP)** states that objects of a superclass should be replaceable with objects of its subclasses without breaking the application. 
>
> In other words, what we want is to have the objects of our subclasses behaving the same way as the objects of our superclass.



This is rather abstract and hard to understand, but we make it easier by using some examples:



**Good Exmaples:**

* You make the `PhysicsStudent` class as a sub-class of the `Student` class;
* You make the `CSStudent` class as a sub-class of the `Student` class.



**Bad Examples**:

* You make a `ColumbiaAlumni` class a sub-class of the `Student` class;
* You mkae a `ColumbiaStaff` class a sub-class of the `Student` class.



### 4. Interface Segregation Principle

That is, we should never let a class to implement an interface that does not belong to it. Very simple, we can make `ColumbiaEmployee extends Employee implements Research, Teaching` but we can not let it implemnt `Student` interface. Because Columbia employees do not have a `getGPA` method in general, as they do not have GPA at all.



Again, interface is a contract, you should be aware of what job you can find for for your pathetic class, or equivalently, employee.



### 5. Dependency Inversion Principle

> [*Reference link.*](https://dev.to/tamerlang/understanding-solid-principles-dependency-inversion-1b0f#:~:text=The%20Dependency%20Inversion%20Principle%20(DIP,Details%20should%20depend%20upon%20abstractions.))
>
>  The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules; both should depend on abstractions. Abstractions should not depend on details. Details should depend upon abstractions.



Again, rahter abstract to understand, let us make it human readable: 



For example we have a `ColumbiaCommunity` interface, and we make `ColumbiaEmployee` and `Student` implemnt the interface so that we can realise `createConnection`, `goToColumbiaBuilding`, etc. functions. And we make `PhysicsStudent` and `CSStudent` as sub-class of `Student`. Looks good! But how we design the details of the low-level classes should be depents purely on the abstractions of high-level stuffs. We should make sure that `ColumbiaCommunity` works exactlly the same way no matter how we change the `CSStudent` class, because `CSStudent` class is low-level details.



**The key philosophy of this principle is to lower the coupling of our program.**









