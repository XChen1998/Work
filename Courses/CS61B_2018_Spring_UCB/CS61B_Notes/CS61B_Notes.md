  

# The Notes of CS61B Spring 2018@UC, Berkeley 

**Author**: Ziggy (Ruofan) Chen, MSCS@Columbia University in the City of New York '23, xinghe.c at columbia.edu

Nov. 2021

***Statement**: This note is largely based on [Hug61B book](https://joshhug.gitbooks.io/hug61b/content/chap1/chap11.html). And it is for **personal use only, i.e. Any form of distribution for non-personal usage is strictly prohibited. Any quotes without specific declaration are from [Hug61B book](https://joshhug.gitbooks.io/hug61b/content/chap1/chap11.html)  by default. Starting from section X, many materials are based on the Spring 2019 version of CS61B ([course page](https://sp19.datastructur.es)) and the [Algorithms 4th Edition book](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf)**. The notes only include some key pieces of code. For complete programs, please consult  the Github repositories of **[XChen1998](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB)**. Any title in italic means that they contains important summative information. The original course page can be found in this [link](https://sp18.datastructur.es).*



## I. Introduction to Java

### 1. Esesentials 

#### A. Hello World

The very first Java program, Hello World.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

From this very basic program, we can see that:

+ Any Java program should be a class which is declared using `public class`.

+ The code starts from the main method `public static void main(Strings[] args)`.

+ `{}` is used to denote the beginning and the end of a code section.

+ `; ` is used to end any statement.

  

#### B. Running a Java program

The following flow chart indicates how Java program works.

![Image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Java%20Work%20Flow.png?raw=true)

Java programs have to be compiled before running. So, to run the Hello World java program, the following commands are required:

```
$ javac HelloWorld.java
$ java HelloWorld
Hello World!
```

The last line is the output of the program.



#### C. Variables and Loops

An example of while loop in Java:

```Java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
    }
}
```

This program print integers from 0 to 9.



#### D. Static Typing

Any variables in Java must have a `Type` and only this specific `Type`. Once a variable is created, its type can never be changed. For example the following code generates a compling error with respect to the type of `x`:

```Java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
        x = "horse";
    }
}
```

And the error message is:

```
$ javac HelloNumbers.java 
HelloNumbers.java:9: error: incompatible types: String cannot be converted to int
        x = "horse";
                ^
1 error
```

So the compiler always checks type errors before runnning the program. This lead to huge advantage for developers.

To summarize, static typing has the following advantages:

- The compiler ensures that all types are compatible, making it easier for the programr to debug their code.
- Since the code is guaranteed to be free of type errors, users of your compiled programs will never run into type errors. For example, Android apps are written in Java, and are typically distributed only as .class files, i.e. in a compiled format. As a result, such applications should never crash due to a type error since they have already been checked by the compiler.
- Every variable, parameter, and function has a declared type, making it easier for a programr to understand and reason about code.



#### E. Defining Functions in Java

The difference of functions in Python/Java is shown below:

Python

```python
def larger(x, y):
    if x > y:
        return x
    return y

print(larger(8, 10))
```

 Java

```Java
public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```

Although fuctions in Java look like more redundant, its ability to avoid bugs makes it easier to build large project. i.e. Java programs are generally more delicate and well-written. And it is therefore more robust than Python.



#### F. Code Style, Comments, Javadoc

In summary, CS61B requires the following coding style:

- Consistent style (spacing, variable naming, brace style, etc)
- Size (lines that are not too wide, source files that are not too long)
- Descriptive naming (variables, functions, classes), e.g. variables or functions with names like `year`or `getUserName` instead of `x` or `f`.
- Avoidance of repetitive code: You should almost never have two significant blocks of code that are nearly identical except for a few changes.
- Comments where appropriate. Line comments in Java use the `//` delimiter. Block (a.k.a. multi-line comments) comments use `/*` and `*/`.

**The author found that the auto format in IntelliJ is very useful, that is `command + option + L`, which usually eliminates most of the misformats**

An example of appropriate comments is shown below:

```java
public class LargerDemo {
    /** Returns the larger of x and y. */           
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```



### 2. Objects

#### A. Static vs. Non-Static Methods

***<font color = red size = 5>!!!The author notes that a static method cannot be called without the class prefix from another class. i.e. you need to use dot notation to call static method in a separate file. !!! </font>***

The most significant difference between *static method* and *non-static* method is that **a *non-static method* cannot be called without an instance of the class.** The main method is therefore static, as it is the entry point, where no instance can be initialised.

For more details about class, please consult https://joshhug.gitbooks.io/hug61b/content/chap1/chap12.html. Class in Java is very much like that in C++.



## II. List

### 1. Java always Pass by Value (Pre-requsite for List)

***<font color = red size = 5>!!!Sometimes, the author may use the term 'pass by value' for non-primitive type variables, but please keep in mind, this is just the superfacial hehaviour. They actually 'pass by address', where address is the value !!! </font>***

#### A. The Behaviour of Assignment `=` Operator

**Golden Rule of Equals (GRoE)** : The assignment operator, `=`, always copy the bits in Java.

The mystery of the walrus example:

```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;
System.out.println(a);
System.out.println(b);
```

In this example, the change to b affecd a. Because the `b = a` statement let both `a` and `b` point to the same object. **Please note that this does not indicates that Java can pass by reference, the fact is the value of `a` is an addresss, which leads to illusion that Java may copy by reference**. The figure below shows the data structure of a class in detail

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Walrus%20Data.png?raw=true)

i.e. the `someWalrus` variables is like a pointer in C++. It stores a **64 bits address** that points to the instance. The walrus instance itself has two variables (two boxes), which stores the **value** of its weight and tuskSize, respectively. The assignment `b = a`  let `b` points to the same instance as `a`.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/AB.png?raw=true)

```java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);
System.out.println("y is: " + y);
```

By contrast, the chagne to `x` does not affect `y` at all. Because all primitive type variables do not store the address but the true value. **(This also apply to String type variables, which is why sometimes it is called the ninth special primitive type.)**



#### B. Bits

All information in your computer is stored in *memory* as a sequence of ones and zeros. Some examples:

- 72 is often stored as 01001000
- 205.75 is often stored as 01000011 01001101 11000000 00000000
- The letter H is often stored as 01001000 (same as 72)
- The true value is often stored as 00000001

Despite both 72 and H are stored as 01001000, the Java interpreter knows the difference according to the corresponding type. So if we run 

```java
char c = 'H';
int x = c;
System.out.println(c);
System.out.println(x);
```

This gives 

```
H
72
```

**There are 8 primitive types in Java: byte, short, int, long, float, boolean and char, and sometimes people regard String as the ninth special primitive type (because String variables do not store address but the true value!).**



#### C. Declaring a Variable

When you do

```java
int x;
double y;
```

in Java. You get two boxes of size 32 and 64 respectively. 

![Image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Boexes.png?raw=true)

There is no way to know the exact address of these variables unlike C++. The exact memory address is below hte level of abstracton. This feature of Java is a tradeoff. We lose the ability to manage memory usage but at the same time, this avoid some annoying memory management issues.

Then we assign `x` and `y` with some values.

```java
x = -1431195969;
y = 567213.112;
```

 Then the memory boxes are filled with some binary digits,

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Binary%20digits.png?raw=true)

which represent the corresponding value of `x` and `y`.



#### D. Simplified Box Notation

The previous box notation indeed shows the data storage in Java, but it is too vague. We therefore introduce a simplified box notation. So after you do the same things,

```java
int x;
double y;
x = -1431195969;
y = 567213.112;
```

we use 

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Simplified%20Box%20Notation.png?raw=true)

to show the data storage.



#### E. Parameter Passing

When you pass parameters to a function, the Golden Rule of Equal still applies. That is, you simply copy the bits, and there is no copy by reference options in Java. The special case of class copying is only because the bits itself is an address. So when you do the following,

```java
public static double average(double a, double b) {
    return (a + b) / 2;
}

public static void main(String[] args) {
    double x = 5.5;
    double y = 10.5;
    double avg = average(x, y);
}
```

 when `x` and `y` enter the function, they becomes entirely different variables. Any changes to `a` and `b` will not affect `x` and `y`. 

This following code demonstrates the same thins. When you run this piece of code, the function changes `walrus ` but not changes `x`. And again, this is because Java always passes by value not by reference and walrus stores address as its value. 

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```



#### F. Instantiation of Arrays

Arrays can be instantiate as follows.

```java
int[] x;
Planet[] planets;
```

When you do this, Java creates memory boxes of 64 bits. The box for `x` hold the address of an `int`, though it does not exist yet, whereas the box of `planets` holds the addresss of an `Planet`.

Another way to instantiate arrays is 

```java
x = new int[]{0, 1, 2, 95, 4};
```

Since the input has 5 elements, the `new` keyword creates 5 boxes of 32 bits each and returns the addresss of the overall object for assignment to `x`.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/arrays.png?raw=true)



###  2. IntLists

#### A. IntLists Basic Class

The very basic IntLists looks like 

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}
```

The second class variable `rest` makes it possible to link IntList in a recursive manner. And the constructor allows us to initialise IntList through the following two ways, though it is ugly.

Method 1: Set the class variables recursively.

```java
IntList L = new IntList(5, null);
L.rest = new IntList(10, null);
L.rest.rest = new IntList(15, null);
```

Method 2: Set the IntList using the parameterised constructor.

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
```

In Java, array has a static size that cannot be changed once it is initialised. But the data structure of the IntList we have just created allows us to expand or shrink the its size.



#### B. IntLists Size and IterativeSize

It is very easy to know the size of an IntList using recursive algorithm.

```java
/** Return the size of the list using... recursion! */
public int size() {
    if (rest == null) {
        return 1;
    }
    return 1 + this.rest.size();
}
```

Alternatively, a non-recursive method is also available. That is the iteration method.

```java
/** Return the size of the list using no recursion! */
public int iterativeSize() {
    IntList p = this; 
    int totalSize = 0;
    while (p != null) {
        totalSize += 1;
        p = p.rest;
    }
    return totalSize;
}
```

Please note that, a new pointer has to be used to perform the iteration, otherwise the pass by address (value) nature of a class instance will destory the instance itself.



#### C. IntLists Get Function

To get a desired element in the list, a helper method called `get(int i)` is introducted to the class. This method use iteration method to return the ith element of the list.

```java
public int get(int i) {
    int element = 0;
    IntList p = this;
    while (element < i) {
        p = p.rest;
        element++;
    }
    return p.first;
}
```

Alternatively, a recursive method is also available.

```java
public int getR(int i) {
    if (i == 0) {
        return this.first;
    }
    return this.rest.getR(i - 1);
}
```



### 3. SLLists

Previously, we created IntList class, which has a **naked recursive** data structure. This feature leads to a lot of confusion. Now, a slight change to the IntList can make it more human readable and user friendly.



#### A. Rebranding

In step one, we just rename everything and discard all the helper method.

**Old**

```java
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
...
```

**New**

```java
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}
```



#### B. Bureaucracy

In step two, we create a new class called SLList (Singly Linked List).

```java
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }
}
```

By doing so, we hide the **naked recursive** nature of IntList. The parameterised constructor of SLList hide the `null`. We can now initialise an instance in a more reasonable way (Line 2).

```java
IntList L1 = new IntList(5, null);
SLList L2  = new SLList(5); // This looks tidy and clean.
```



#### C. addFirst and getFirst

The addFirst method is straightforward. We just assign a new `IntNode` to the class variable `first`. 

```java
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
}
```

And getFirst method is even more straightforward. Simply return the `item` of `first`.

```java
/** Retrieves the front item from the list. */
public int getFirst() {
    return first.item;
}
```

Thanks to these helper methods, we can create a `SLList` easily.

```java
SLList L = new SLList(15);
L.addFirst(10);
L.addFirst(5);
int x = L.getFirst();
```

This is the counterpart of the following piece of code in `IntList`

```java
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
int x = L.first;
```

The following two figures indicates the difference between the two data structure.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/IntListvsSLList.png?raw=true)

Basically, the `Sllist` class is a middleman that hide all the awkward features of `IntList`. Also, the `addFirst` method in `IntList` is rather tricky. You have to set rest before setting first, otherwise the information of the original first is discarded.

```java
public void addFirst(int i){
    rest = new IntList(first, rest);
    first = i;
}
```



#### D. Public vs Private

Although the data structure of `SLList` can hide the **naked recursive** natrue of `IntList`, it can be simply bypasssed as demonstrated below,

```java
SLList L = new SLList(15);
L.addFirst(10);
L.first.next.next = L.first.next;
```

which voids our effort to make the class elegant and reasonable. Also, it may lead to very dangerous operations like malformed list with an infinite loop.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Infinite%20loop.png?raw=true)

Fortunately, this issue can be easily solved by changing the **access modifier** before the class variable `first`.

```java
public class SLList {
    private IntNode first;
...
```

**Private** variables and methods cannot be accessed by code outside the same `.java` file, which avoid any mismanipulation like the infinite loop mentioned above. In this case, the following code will generate a complile error `first has private access in SLList`

```java
public class SLLTroubleMaker {
    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.first.next.next = L.first.next;
    }
}
```



#### E. Nested Class

We now have two separate `.java` files: `IntNode` and `SLList`. The `IntNode.java`, however, seem to be unnecessary to be an independent file as it is just a supporting class. Thus, we can make it nested (i.e. makes it a part of `SLList`).

```java
public class SLList {
       public class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first; 

       public SLList(int x) {
           first = new IntNode(x, null);
       } 
...
```

This change does not have any meaningful effect, but it of course makes the project well-organised. Moreover, if the nested class does not use any of the instance method or variables of `SSlist`,  we can make it static. Declaring a nested class as `static` means that methods inside the static class can not access any of the members of the enclosing class. In this case, it means that no method in `IntNode` would be able to access `first`, `addFirst`, or `getFirst`.

```java
public class SLList {
       public static class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first;
...
```

This change, however, does not have significant advantages rather than saving a bit of memory, as the `IntNode` class no longer need to track any members of its enclosing `SLList`.



#### F. addLast() and size()

Unlike the `addFirst` method, `addLast` is rather tricky, due to the fact that we do not have a pointer pointing to the last of the list. Thus, we have to use recursive or iterative method to add an element to the last of the list. The following code shows an iterative exmaple. Again, a pointer `p` is used to locate the last position.

```java
/** Adds an item to the end of the list. */
public void addLast(int x) {
    IntNode p = first;

    /* Advance p to the end of the list. */
    while (p.next != null) {
        p = p.next;
    }
    p.next = new IntNode(x, null);
}
```

The `size` method is very similar to the `addLast` method. The example is written in recursive method.

```java
/** Returns the size of the list starting at IntNode p. */
private static int size(IntNode p) {
    if (p.next == null) {
        return 1;
    }

    return 1 + size(p.next);
}

public int size() {
    return size(first);
}
```

Please note that, the `SLList` does not have `next`. Therefore, we create two methods to solve the issue. The first one is a private helper method to calculate the size of an `IntNode`, whereas the second one is an interface for users to call. One important thing is that both method named `size`. This is allowe in Java as they have different input parameters.



#### G. Caching

Indeed, the `size` method can return the size of a list, but the efficiency is not satisfactory. Generally speaking, the time complexity is proportional to the size of the list ($O(h)$). To solve this problem, an extra variable, `size`,  should be added to the `SLList` class to track the size of the list. Also, we need to slightly change the parameterised constructor and `addFirst` method as follows

```java
public class SLList {
    ... /* IntNode declaration omitted. */
    private IntNode first;
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int size() {
        return size;
    }
    ...// addLast method should also have the size += 1 statement.
}
```

Now, the `size` method is trivial, as it simply return an integer. So no matter how large a list can be, the `size ` method always consumes constant time.



#### H. The Empty List

Alreeady, we have known a number of advantages of hiding the naked recursive nature of `IntList`.

- Users of a `SLList`never see the `IntList` class.
  - Simpler to use.
  - More efficient `addFirst` method (exercise 2.2.1).
  - Avoids errors or malfeasance by `IntList` users.
- Faster `size` method than possible with `IntList`.

In addition, it is also easier to create an empty list using the `SLList` class.

```java
public SLList() {
    first = null;
    size = 0;
}
```

Unfortunately, an empty list is not compatible with the `addLast` method. The following code solves the issue by adding first instead of adding last when the size of a list is zero (i.e. empty array).

```Java
/** Adds an item to the end of the list. */
public void addLast(int x) {
  if (size == 0){
    addFirst(x);
    return;
  }
  IntNode p = first;

  /* Advance p to the end of the list. */
  while (p.next != null) {
    p = p.next;
  }
  p.next = new IntNode(x, null);
}
```

Alternatively, this piece of code can also make it possible to add an element to the last of an empty list.

```java
public void addLast(int x) {
    size += 1;

    if (first == null) {
        first = new IntNode(x, null);
        return;
    }

    IntNode p = first;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```



#### I. Sentinel Nodes

Anyway, both methods added a special case for empty lists. But this makes the code difficult to read and manage. The ideal case is that we do not have so many special cases for a simple class like `SLList`. As a choice, we can add an `sentinel` to `SLList`. That is an abstract node before the real first ndoe.

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/Sentinel.png?raw=true)

We now do not need consider a special case for empty list. The code looks more compact and elegant.

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

 

#### J. Invariants

The key to avoid special cases in a function is to grap some key features that never change, the invariants. For our class, 

A `SLList` with a sentinel node has at least the following invariants:

- The `sentinel` reference always points to a sentinel node. (`sentinel ` is a class variable, the sentinel node is just an `IntNode` )
- The front item (if it exists), is always at `sentinel.next.item`.
- The `size` variable is always the total number of items that have been added.



### 4. DDLists

#### A. addLast Performance Issues

The `addLast` method is shown as below

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

It works, but the performance is terrible. For a long list, it has to go through all the elements to the last node and add a new element.

Luckily, the issue can be solved by adding a `Last` intoned that track the position of the end of the list.

```java
public class SLList {
    private IntNode sentinel;
    private IntNode last;
    private int size;    

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }
    ...
}
```

Therefore, `getLast`, `addLast` always consumes a constant time. But how about `removeLast`?  A naive idea is that, we can simply remove the last item using the `Last` tracker. But after doing so, what is the value of `Last`? Now you may think about also tracking the second last `IntNode` by adding one more class variables. However, when you remove the last item, you can reassign the value of `last` but you do not know the thrid last item, which result in difficulity in reassigning `secondlast`.



#### B. Looking Back

The way out is DLL (doubly linked list). The data structure is shown below

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/DLLDiagram.png?raw=true)

In the code, we add one more variable, `prev`. As shown in the figure, the nodes are doubly linked, which makes the `removeLast` method very fast. 

```java
public class IntNode {
    public IntNode prev;
    public int item;
    public IntNode next;
}
```



#### C. Sentinel Upgrade

Adding an extra back pointer significantly improves the efficiency of `removeLast`,  but as shown in the figure above, it may sometimes points to the sentinel node. This will result in a special case in a number of our method with respect to the `sentB`. Inspired by the `sentinel` node in the front of the array, we can also creat one at the back. 

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/DoubleSentinel.png?raw=true)

This two sentinel data structure simplify the complexity of the `DLList` class, as it avoid many special cases. The key point is that the two sentinel guarantee that `sentF` and `sentB` always points to two sentinel nodes. This is what we called invariant, programrs' best friends.



Actually, there is another data structure that could avoid any annoying stuffs we have encountered. That is a circular data structure as indicated below

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/CircularSentinel.png?raw=true)

Both of the two methods are great. But I personally prefer *the two sentinel* method rather than *the circular sentinel* method, purely due to its aesthetically symmetry. The CS61B instructor, however, prefer the *circular sentinel* method. But they are all good data structure anyway.



#### D. Generic DLLists 

Our `DDList` class can now only store integers, as `items` is of `int` type. Thus, we have to create other class that `items` variable is declared as different types. No, this is not ture! Luckily, in 2004, the creators of Java added **generics** to the language, which will allow you to, among other things, create data structures that hold any reference type. A **generic class** can be declare as

```java
public class DLList<BleepBlorp> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public BleepBlorp item;
        public IntNode next;
        ...
    }
    ...
}
```

 Here, `BleepBlorp` can be any type in Java. The instantiation of such a class is a litter bit more complicated, like the two examples below.

```java
DLList<String> d2 = new DLList<>("hello");
d2.addLast("world");
```

```java
DLList<Integer> d1 = new DLList<>(5);
d1.insertFront(10);
```

Please note that you have to use `Integer` but not `int`, and you do not need to input the type in the `new` section again. The whole story is

- In the .java file **implementing** a data structure, specify your generic type name only once at the very top of the file after the class name.

- In other .java files, which use your data structure, specify the specific desired type during declaration, and use the empty diamond operator during instantiation. **Although you can declare it in the `new` statement, this is redundant**.

  ```java
  DLList<Integer> d1 = new DLList<Integer>(5);
  ```

- If you need to instantiate a generic over a primitive type, use `Integer`, `Double`, `Character`, `Boolean`, `Long`, `Short`, `Byte`, or `Float` instead of their primitive equivalents.



### 5. Arrays

By far, we have created an mutable data structure, list. But actually, we delicate design, we can bypass the immutable nature of array.



#### A. Array Basics

Unlike other data structure, arrays consists of a numbered sequence of memory boxes, this allows us to get the ith elemnt using `A[i]`. The position of an element is inherently stored in the array. In summary, arrays consists of 

- A fixed integer length, N
- A sequence of N memory boxes (N = length) where **all boxes are of the same type**, and are numbered 0 through N - 1.

 

#### B. Array Creation

The three valid ways to create an array are shown below

- `x = new int[3];`
- `y = new int[]{1, 2, 3, 4, 5};`
- `int[] z = {9, 10, 11, 12, 13};`

The first one create three boxes with zero filled in each box.

The second one create an array of [1, 2, 3, 4, 5];

The third one is very similar to the second one, but it can **only be used when a variable is declared**.

They are all good ways to create an array. No one is better than any other.



#### C. Array Access and Modification

This section is essentially for getting familiar with array manipulation.

```java
int[] z = null;
int[] x, y;

x = new int[]{1, 2, 3, 4, 5};
y = x;
x = new int[]{-1, 2, 5, 4, 99};
y = new int[3];
z = new int[0];
int xL = x.length;

String[] s = new String[6];
s[4] = "ketchup";
s[x[3] - x[1]] = "muffins";

int[] b = {9, 10, 11};
System.arraycopy(b, 0, x, 3, 2);
```

All the manipulation is straightforward, except the `System.arraycopy`. The statement copies `2` elements starting from the `0` position from `b` to `x` starting from the `3` position. This work can also be done using iteration, such as a `for` loop. (**Please note that this piece of code has a bug with respect to the length of y!**)

```java
int[] x = {9, 10, 11, 12, 13};
int[] y = new int[2];
int i = 0;
while (i < x.length) {
    y[i] = x[i];
    i += 1;
}
```

But `System.arraycopy` is more efficient. In summary, the arguments in `System.arraycopy` are 

- The array to use as a source
- Where to start in the source array
- The array to use as a destination
- Where to start in the destination array
- How many items to copy



#### D. 2D Arrays in Java

2D arrays is just an array of array. This is very similar to that in Python and Matlab. An example is shown below.

```java
int[][] pascalsTriangle;
pascalsTriangle = new int[4][];
int[] rowZero = pascalsTriangle[0];

pascalsTriangle[0] = new int[]{1};
pascalsTriangle[1] = new int[]{1, 1};
pascalsTriangle[2] = new int[]{1, 2, 1};
pascalsTriangle[3] = new int[]{1, 3, 3, 1};
int[] rowTwo = pascalsTriangle[2];
rowTwo[1] = -5;

int[][] matrix;
matrix = new int[4][];
matrix = new int[4][4];

int[][] pascalAgain = new int[][]{{1}, {1, 1},
                                 {1, 2, 1}, {1, 3, 3, 1}};
```

This piece of code use an array of int arrays to create a pascals triangle. 

The following code demonstrate an important feature of arrays. Arrays are not primitive types, the values stored in the variables are address, which means that their behaviour is very much like  **pass by reference** (Again, Java never pass by reference, but when the address is the value itself, this will result in the same behaviour as that of pass by reference).

```java
int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

int[][] z = new int[3][];
/* The following three lines copy the address */
z[0] = x[0]; 
z[1] = x[1];
z[2] = x[2];
z[0][0] = -z[0][0]; //This also changes x

int[][] w = new int[3][3];
/* The following three lines copy the values */
System.arraycopy(x[0], 0, w[0], 0, 3);
System.arraycopy(x[1], 0, w[1], 0, 3);
System.arraycopy(x[2], 0, w[2], 0, 3);
w[0][0] = -w[0][0]; // This only changes w
```

The result should be `x[0][0] = -1` ,  `z[0][0] = -1`,  `w[0][0] = 1`.



#### E. Arrays vs. Classes

The key differences between memory boxes in arrays and classes:

- Array boxes are numbered and accessed using `[]` notation, and class boxes are named and accessed using dot notation.
- Array boxes must all be the same type. Class boxes can be different types.

Besides, one critical difference is that **the `[]` notation allows us to enable runtime indexing**, whereas this is not possible for dot notation. For example, we can output the user desired element of an array with the folloing code.

```java
int indexOfInterest = askUserForInteger();
int[] x = {100, 101, 102, 103};
int k = x[indexOfInterest];
System.out.println(k);
```

By contrast, we can see that both of the two following attemps to enable runtime indexing for class failed.

Naively trying to use the `[]` notation for a class.

```java
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p[fieldOfInterest];
```

```
$ javac classDemo
FieldDemo.java:5: error: array required, but Planet found
        double mass = earth[fieldOfInterest];        
                               ^
```

Trying to use the runtime indexing with dot notation.

```java
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p.fieldOfInterest;
```

```
$ javac classDemo
FieldDemo.java:5: error: cannot find symbol
        double mass = earth.fieldOfInterest;        
                           ^
  symbol:   variable fieldOfInterest
  location: variable earth of type Planet
```



#### F. Appendix: Java Arrays vs. Other Languages

Compared to arrays in other languages, Java arrays:

- Have no special syntax for "slicing" (such as in Python).
- Cannot be shrunk or expanded (such as in Ruby).
- Do not have member methods (such as in Javascript).
- Must contain values only of the same type (unlike Python).



### 6. The Alist

#### A. Linked List Performance Puzzle

No matter how a `Linked List` is well-designed, its `get` method is always inefficient. Its nature that only adjacent elements are linked makes it impossible to get a value in the middle of the list without go through very many elements. In the worst case, half of the elements may be traversed to access the very middle element.



#### B. A Solution Using Array

Here is a naive solution using Java. The data structure of array speed up the `get` function in a natural way. Please carefully identify invariants in this class, as they are critical to elegant coding.

```Java
/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class AList {
    private int[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int x = getLast();
        size = size - 1;
        return x;
    }
} 
```

Please note that, in the `removeFirst` and `removeLast`, they do not actually void the box (i.e make the value `null`). Changing the size already let the user lose the access to those elements, which is equivalent to delete them.



#### C. Resizing Arrays

When the array is full, we have no choice other than creating a larger one and copying all the value into the new one.

```java
private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

public void addLast(int x) {
    if (size == items.length) {
    resize(size + 1);
    }

    items[size] = x;
    size = size + 1;
}
```

However, copying array is time consuming, especially when the array is large.  Generally speaking, the time complexity of such manipulation is $O(h^2)$. A tiny change can be of great help, that is change the statment `resize(size + 1)` to `resize(size * 2)`, which is called **geometric resizing**.

```Java
private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

public void addLast(int x) {
    if (size == items.length) {
    resize(size * 2); // This line is the key to make the time complexity of approximately O(h).
    }

    items[size] = x;
    size = size + 1;
}
```

This saves a huge amount of time, because it almost makes the time complexity $O(h)$. The figure below demonstrate the difference between `SLList` and the naive  `AList`. The modified `AList` has a similar performance as `SLList`.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/TimeComplexity.png?raw=true)

#### D. Memory Performance

Despite the geometric resizing significantly lower the time complexity. It, at the same time, create much more boxes than we really need, especially when a lot of the elements are deleted in a later time.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/MemoryPerformance.png?raw=true)

In this case, we need to shrink our array. For example, the typical implementation is to halve the size of the array when R falls to less than 0.25, as shown in the following pseudo code.

```
if (size < length of array / 4){
	half the array length.
}
```



#### E. Generic ALists

This part is trival. We have discussed it in [Section II.4.D](# D. Generic DLLists). But something awkward happens here. The following initialisation is not available in Java.

```java
Glorp[] items = new Glorp[8];
```

And we have to use a rather bizarre syntax to initilise the array of arbitary type.

 ```Java
 Glorp[] items = (Glorp []) new Object[8];
 ```

It looks like we define an `Object[]` and cast it to `Glorp []`. Although the philosophy behind is unclear at the moment, please just regard it as black magic for now. Details will be discussed in a later chapter,



## III. Testing

***<font color = red size = 5>!!!The author notes that `@Test` cannot be part of  a real class, i.e. class with member variables. So please create a separate file to perform testing!!! </font>***

### 1. Selection Sort--A Wifndow to the Power of Testing

#### A. Introduction to Testing

No matter how experienced you are in coding, bugs are always annoying. The built-in debugger in IDEs may be of great help, but they usually only check a specific circumstance, rather than random testing the code. Therefore, we need to write a generic Testing method to check if our program is robust. We will use the selection sort algorithm as an example to show how to write a testing.



#### B. Ad Hoc Test

In the following code, we write a `testSort` method to check if the `sort` function works. 

```java
public class TestSort {
    /**
     * Sorts strings destructively.
     */
    public static void sort(String[] x) {
    }

    /**
     * Tests the sort method of the Sort class.
     */
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        sort(input);
        for (int i = 0; i < input.length; i += 1) {
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
                break;
            }
        }
    }

    public static void main(String[] args) {
        testSort();
    }
}
```

Obviously, the `sort` method is empty, and running the code gives us

```
Mismatch in position 0, expected: an, but got: i.
```

So we now can modify the `sort` method until it passes the `testSort` function. This working manner minimises the stuff we need to consider. It is just like a game! We only need to play with it.

**Important note:** We did not use the `==` operator to check if two entire strings are equal (i.e. we did not do `!(input == expected)`). This is because `==` compares the bits (i.e. the value of the a variable). Unfortunately, `input` and `expected` are both arrays of string, **their value are therefore addresses**. So even if all the elements of them are the same, the addresses are different. So this way is not possible.



#### C . The JUnit Testing

The original redundant testing method, `testSort` , can be easily written in just one line using `JUnit`.  

```java
public static void testSort() {
    String[] input = {"i", "have", "an", "egg"};
    String[] expected = {"an", "egg", "have", "i"};
    Sort.sort(input);
    org.junit.Assert.assertArrayEquals(expected, input);
}
```

The returned message is also developer-friendly, despite not as compact as that in the ad hoc test.

```sh
Exception in thread "main" arrays first differed at element [0]; expected:<[an]> but was:<[i]>
    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
    at org.junit.Assert.internalArrayEquals(Assert.java:532)
    ...
```



#### D. Selection Sort

The philosophy of selection sort is as follows:

- Find the smallest item.
- Move it to the front.
- Selection sort the remaining N-1 items (without touching the front item).

It is intrinsically recursive algorithms. When we try to move the smallest item to the front, there are two methods:

* Making `{6, 3, 7, 2, 8, 1}` to `{1, 6, 3, 7, 2, 8}`.
* Making `{6, 3, 7, 2, 8, 1}` to `{1, 3, 7, 2, 8, 6}`. (i.e. swap the two items)

The second one is obviously more efficient. Now let us start building the algorithm.



#### E. findSmallest

Since the selection sorting has a recursion nature, the `findSmallest` function has to have an input argument `start` to determine  which part of the `String` should be inspected.

```java
public static int findSmallest(String[] x, int start) {
    int smallest = start;
    for (int i = start; i < x.length; i += 1) {
        int cmp = x[i].compareTo(x[smallest]);
        if (cmp < 0) { // cmp < 0 means x[i] is smaller
            smallest = i;
        }
    }
  // we return the index instead of the value, this is typically better in array related programming 
    return smallest; 
}
```

Then we write a corresponding testing method to test the correctness of the `findSmallest` function. 

```java
@Test
public void testFindSmallest() {
    String[] input = {"i", "have", "an", "egg"};
    String expected = "an";

    String actual = input[Sort.findSmallest(input, 0)];
    org.junit.Assert.assertEquals(expected, actual);

    String[] input2 = {"there", "are", "many", "pigs"};
    String expected2 = "are";

    String actual2 = input2[Sort.findSmallest(input2, 0)];
    org.junit.Assert.assertEquals(expected2, actual2);
}
```



#### F. Swap

A naive way to swap two elements is shown below.

```java
public static void swap(String[] x, int a, int b) {    
    x[a] = x[b];
    x[b] = x[a];
}
```

This is a classic mistake that beginner may encounter. The first statment killed the value of `x[a]`, and failed the `swap` fucntion. The solution is an extra temporary container.

```java
public static void swap(String[] x, int a, int b) {
    String temp = x[a];
    x[a] = x[b];
    x[b] = temp;
}
```

Please keep in mind, you always need three boxes to swap two items, otherwise you lose one of the original item.

The `testSwap` function is as follows.

```java
/** Test the Sort.swap method. */
public static void testSwap() {
    String[] input = {"i", "have", "an", "egg"};
    int a = 0;
    int b = 2;
    String[] expected = {"an", "have", "i", "egg"};

    Sort.swap(input, a, b);
    org.junit.Assert.assertArrayEquals(expected, input);
}
```



#### G. Recursive Helper Method

An elegant way to write the `sort` function is by adding an extra helper method, so that we do not need to ask the user to input an ambiguous argument (i.e. we do not want the user to write `sort(x, 0)`, where the `0` makes no sense). The introduction of a private helper method hides this argument in the main `sort` method. User can now simply write `sort(x)`, which is more reasonable. 

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
   int smallestIndex = findSmallest(x);
   swap(x, start, smallestIndex);
   sort(x, start + 1);
}
```

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   sort(x, 0);
}
```

**Please note that Java does not support array slicing.** So you cannot write the recursive `sort` method in the following manner

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
   sort(x[1:])  // This line is not allowed in Java!!!
}
```



#### H. Finalise the Selection Sort Algorithm

By adding all the stuffs we have introduced above, we finally complete the `Sort` algorithm.

```Java
public class Sort {
    /**
     * Sorts strings destructively.
     */
    public static void sort(String[] x) {
        // find the smallest item
        // move it to the front
        // selection sort the rest (using recursion?)
        sort(x, 0);

    }

    //* Sorts x stating at position start */
    private static void sort(String[]x, int start){
        if (start == x.length){
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }

    /**
     * Returns the smallest string index in x.
     */
    public static int findSmallest(String[] x, int start) {
        int smallest = start;
        for (int i = start; i < x.length; i += 1) {
            int cmp = x[i].compareTo(x[smallest]);
            if (cmp < 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    public static void swap(String[] x, int a, int b){
        String cur = x[a];
        x[a] = x[b];
        x[b] = cur;
    }
}
```

The coressponding `TestSort` class is shown below. 

```java
import org.junit.Test;


public class TestSort {
    @Test
    public void testsort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);

        org.junit.Assert.assertArrayEquals(expected, input);
    }


    @Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        String actual = input[Sort.findSmallest(input, 0)];
        org.junit.Assert.assertEquals(expected, actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        String expected2 = "are";

        String actual2 = input2[Sort.findSmallest(input2, 0)];
        org.junit.Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void testswap() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "have", "i", "egg"};
        int a = 0;
        int b = 2;
        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);

    }
}
```

If we run the `TestSort ` code, the IDE will generate a message *Tests passed: 3*, which means we now pass all the tests. One more glimpse at the code, you notice that there is no main method in the `TestSort` code, and you may think there must be something wrong. But it is indeed correct. We firstly `import org.junit.Test;` , then we added the `@Test` keyword before each test. By doing so, the program can now run like *code block* in Python.



#### I. *Testing Philosophy*

***Test-Driven Development (TDD)***

TDD is a development process in which we write tests for code before writing the code itself. The steps are as follows:

1. Identify a new feature. 
2. Write a unit test for that feature. 
3. Run the test. It should fail. 
4. Write code that passes the test. Yay!
5. Optional: refactor code to make it faster, cleaner, etc. Except now we have a reference to tests that should pass.

Test-Driven Development is not required in this class and may not be your style but unit testing in general is most definitely a good idea.



## IV. Inheritance & Implements

### 1. Introduction and Interfaces

#### A. The Overloading problem

If we have a generic `ALList` class, we can create a method that returns the longest string in an `ALList`.

```java
public static String longest(SLList<String> list) {
    int maxDex = 0;
    for (int i = 0; i < list.size(); i += 1) {
        String longestString = list.get(maxDex);
        String thisString = list.get(i);
        if (thisString.length() > longestString.length()) {
            maxDex = i;
        }
    }
    return list.get(maxDex);
}
```

***It looks like the author of Hug61B book did not notice that there was not a generic `SLList` class, but creating one is straightforward. So this is just a tiny issue.***

How about make it compatible with `DLList`? The answer is quite subtle. All we need to do is to change `SLList<String>` to `DLList<String>`.  Now we have two functions with the same name but different input arguments.

```java
public static String longest(SLList<String> list)
```

and

```java
public static String longest(AList<String> list)
```

This is allowed in Java, just like the private helper method we previously created to solve the `get()` fucntion problem. The exact terminology for this feature is *method overloading*. Java knows which function to use according to the input. But there are several annoying points to blame

- It's super repetitive and ugly, because you now have two virtually identical blocks of code.
- It's more code to maintain, meaning if you want to make a small change to the `longest` method such as correcting a bug, you need to change it in the method for each type of list.
- If we want to make more list types, we would have to copy the method for every new list class.



#### B. Hypernyms, Hyponyms, and Interface Inheritance

In the English language and life in general, there exist logical hierarchies to words and objects.

Dog is what is called a *hypernym of* poodle, malamute, husky, etc. In the reverse direction, poodle, malamute, and husky, are *hyponyms* of dog. 

These words form a hierarchy of "is-a" relationships:

- a poodle "is-a" dog
- a dog "is-a" canine
- a canine "is-a" carnivore
- a carnivore "is-an" animal

So we can create our class hierarchy 

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Subclass.png?raw=true)

The `List61B` is the a interface. And we add some methods in this superclass

```Java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void add Last(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
}
```

New we create a relationship betweeen the superclass and `AList`, by adding a magical relationship-defining word: `implements`. i.e. `public class AList<Item> implements List61B<Item>{...}`  the `implements ` promise that `AList` will have and define all the attributes and bbehaviours specificed in the superclass interface.



#### C. Overriding

In the interface class, we have a `public Item get(int i);` function, but we did not specifiy the content. So we override it in the `AList` file.

```Java
@Override
public Item get(int index) {
    return items[realIndex2itemIndex(index)];
}
```



To make it clear, the `@Override` signature is not necessary to let the subclass funcion override the superclass function, but it is useful. It always checks if the name and structures of the superclass and the subclass functions are the same. This makes our program safe.



#### D. Interface Inheritance

The interface inheritance refers to the ability of using a superclass variable to store subclass bits as following. This piece of code works perfectly. When it runs, and `SLList` is created and its address is stored in the `someList` variable. Then the string "elk" is inserted into the `SLList` referred to by `addFirst`.

```java
public static void main(String[] args) {
    List61B<String> someList = new SLList<String>();
    someList.addFirst("elk");
}
```

The [GRoE](# A. The Behaviour of Assignment `=` Operator) still applies, the bits of `SLList` is stored in a `List61B` variable, `somelist`. That is to say, a superclass variable is an interface for all its subclass. **This enables run-time polymorphism.**



#### E. Implementation Inheritance

Of the most interest, we can not only override functions in the superclass, we can also write a function in the superclass and let all subclasses use it. The keyword `default` should be included in the method as a signature to tell the Java complier that this function is a generic function.

```Java
default public void print() {
    for (int i = 0; i < size(); i += 1) {
        System.out.print(get(i) + " ");
    }
    System.out.println();
}
```



Now, both  `AList` and `SLList` can use the `print()` function to print out their items. But is this economic? For the `AList`, the `get(int i)` function is superfast, but it is not true for `SLList`. In this case, we can write a new function with the same name in `SLList` to override the default function in the superclass.

```Java
@Override
public void print() {
    for (Node p = sentinel.next; p != null; p = p.next) {
        System.out.print(p.item + " ");
    }
}
```



But how is that possible? How does Java.know which function to call? A new concept, **dynamic method selection**, should therefore be introduced. In addition to the **static type** of a variable, there is also a run-time **dynamic type**. So the Java compiler knows that the variable has a `List61B` **static type** and a run-time `SLList` **dynamic type**. This is the key to implement run-time polymorphism. 

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/DynamicType.png?raw=true)

**IMPORTANT: This does not work for overloaded methods!** This statment looks ambiguous, so let us demonstrate this fact with an example.



Suppose we have the following two functions defined in the same class:

```java
public static void peek(List61B<String> list) {
    System.out.println(list.getLast());
}
public static void peek(SLList<String> list) {
    System.out.println(list.getFirst());
}
```

And in the main method, we want to run:

```Java
SLList<String> SP = new SLList<String>();
List61B<String> LP = SP;
SP.addLast("elk");
SP.addLast("are");
SP.addLast("cool");
peek(SP);
peek(LP);
```

At this point, `SP` and `LP` are `SLList` and `List61B` in terms of static type, respectively. But of course,  `LP` has a `SLList` dynamic type. The Java complier, however, does not behave as we have mentioned earlier. The `peek(SP)` statement generates `elk`, whereas the `peek(LP)` statement outputs `cool`. Now we can say with 100% confidence, **dynamic method selection does not work for overloaded methods.** For more about *implementation Inheritances*, this [YouTube video](https://www.youtube.com/watch?v=9KuVnIje2Ys&t=279s) may be helpful.



#### F. The Conclusion of Interface Inheritance and Implementation Inheritance

- Interface inheritance (what): Simply tells what the subclasses should be able to do.
  -  all lists should be able to print themselves, how they do it is up to them.
- Implementation inheritance (how): Tells the subclasses how they should behave.
  -  Lists should print themselves exactly this way: by getting each element in order and then printing them.

Please note that, the relatioship between superclass and subclass is **is-a**, instead of **has-a**. For examples, `ALList` **is a** `List61B`, but we cannot do `cat` **has a** `claw`.



Finally, Implementation inheritance may sound nice and all but there are some drawbacks:

- We are fallible humans, and we can't keep track of everything, so it's possible that you overrode a method but forgot you did.
- It may be hard to resolve conflicts in case two interfaces give conflicting default methods.
- It encourages overly complex code



### 2. Extends, Casting, Higher Order Functions

#### A. Extends

In Java, we have two different keywords, `implement`  and `extend`. We have seen that we can implement `List61B` by creating two subclasses, `AList`  and `SLList`. So how about if we want to add  an additional method to `SSList` and keep the the functions of `SLList` at the same time? The answer is `extend`. The following piece of code demonstrate how to create a `RotatingSLList` class that extends to function of `SLList` class.

```Java
public class RotatingSLList<Item> extends SLList<Item> {

    /*Rotate SLList*/
    public void rotateRight() {
        this.addFirst(this.removeLast());
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rsl = new RotatingSLList();
        rsl.addLast(10);
        rsl.addLast(11);
        rsl.addLast(12);
        rsl.addLast(13);
        rsl.print();
        rsl.rotateRight();
        rsl.print();
    }
}
```



The class hierarchy looks like this ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Class%20Hierarchy.png?raw=true)

So basically, `AList`  and `SLList` implement `List61B` and `RotatingSLList` class extends the `SLList` class. In the new subclass, all the functions fo `SLList` is preserved and new method may be introduced. 

```java
    public void rotateRight() {
        this.addFirst(this.removeLast());
    }
```

This  `rotateRight()` method uses the `addFirst` and `removeLast` methods of the super class. Calling `rotateRight()` on [5, 9, 15, 22] should return [22, 5, 9, 15].



In summary:

- All instance and static variables
- All methods
- All nested classes

**Note that constructors are not inherited, and private members cannot be directly accessed by subclasses.**



#### B. VengefulSLList

Now we want to create a class extended from `SLList` that can record the removed items. Thus, we write

```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public void printLostItems() {
        deletedItems.print();
    }
}
```

But this class does not automatically record the deleted elements. So we override the `removeLast()` method in the super class.

```java
    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }
```

Also, we add a default constructor to the `VengefulSLList` class.

```java
    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }
```

Now if we do

```Java
public static void main(String[] args) {
    VengefulSSList<Integer> vs1 = new VengefulSSList();
    vs1.addLast(1);
    vs1.addLast(5);
    vs1.addLast(10);
    vs1.addLast(13);
    vs1.print();

    vs1.removeLast();
    vs1.removeLast();
    vs1.print();
    vs1.deletedItems.print();
}
```

We will have the following output:

```Java
1 5 10 13 /* The original list */
1 5 /* The remaining elements */
13 10 /* The deleted elements */
```



#### C. Constructors Are Not Inherited

In [Section IV.2.A](# A. Extends), we declared that constructors of the super class are not inherited. While constructors are not inherited, Java requires that all constructors **must start with a call to one of its superclass's constructors**. The [Hug61B book](https://joshhug.gitbooks.io/hug61b/content/chap4/chap42.html) used *TAs* and *human* as an example to explain the philosophy behind. Here, we just demonstrate how `VengefulSLList` behaves.   



The following two constructors do the same job. The keyword `super()` means that the super class constructor is called (the default constructor in this case). **Java alway automatically calls the default constructor of the super class.**

```Java
public VengefulSLList() {
    deletedItems = new SLList<Item>();
}
```

```java
public VengefulSLList() {
    super();
    deletedItems = new SLList<Item>();
}
```



How about parameterised constructor? The following two constructors behave differently.

```Java
public VengefulSLList(Item x) {
    deletedItems = new SLList<Item>();
}
```

```java
public VengefulSLList(Item x) {
    super(x);
    deletedItems = new SLList<Item>();
}
```

If you do not specify which constructor of the super class should be called, the compiler will only call the default one. So the first piece of code cannot call the parameterised constructor of `SLList`, and the input `x` is therefore void.



#### D. The Object Class

Every class in Java is a descendant of the Object class, or extends the Object class. Even classes that do not have an explicit extends in their class still *implicitly* extend the Object class.

For example,

- VengefulSLList extends SLList explicitly in its class declaration
- SLList extends Object implicitly

As seen in the [documentation for the Object class](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html), the Object class provides operations that every Object should be able to do - like `.equals(Object obj)`, `.hashCode()`, and `toString()`.



In our case, the structure is shown in the following figure.![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/ObjectClassModified.png?raw=true)

The author wants to emphasie again that both keywords `extends` and `implements` defines *is-a* relationship rather than *has-a*.

- `Shower` is a `Bathroom`? No!
- `VengefulSLList` is a `SLList`? Yes!



#### E. Encapsulation

The idea of encapsulation is make your class like a pre-defined tool. A user does not need to know any knowledge of the information inside the class. ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Encapsulation.png?raw=true)

Take our `ArrayDeque` as an example, the hidden `Item` variable is almost not readable, but with our helper method, the user can manipulate an `ArrayDeque` easily. In the author's opinion, **the philosophy of encapsulation is to make our class easy to use (since we have helper methods for users), at the same time, hard to break (since we do not give users access to the hidden information. i.e. we add many `private` access modifier)**.



#### F. Inheritance May Break Encapsulation

The following two pieces of code do the same job in a `Dog` class. They print the desired number of barks.

```java
public void bark() {
    System.out.println("bark");
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

```java
public void bark() {
    barkMany(1);
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        System.out.println("bark");
    }
}
```



A programr, however, write a new class extended from the `Dog` class and override the `barkMany` method. This is absolutely fine for the first `Dog` class, by not true for the second `Dog` class. The `barkMany` and the `bark` methods will interact with each other and form an infinite loop. This is how inheritance may break encapsulation, since `@Override` somehow modifies the hidden information of the original class.

```java
@Override
public void barkMany(int N) {
    System.out.println("As a dog, I say: ");
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```



#### G. Type Checking and Casting

Now, let us review the **dynamic type selection** rules with the following method.

```Java
public class TypeChecking {
    public static void main(String[] args) {

        VengefulSLList<Integer> vsl = new VengefulSLList<Integer>(9);
        SLList<Integer> sl = vsl;

        sl.addLast(50);
        sl.removeLast();
        sl.printLostItems();
        VengefulSLList<Integer>vsl2 = sl;
    }
}
```

`vsl`: Static type and dynamic type are both `VengefulSLList`.

```Java
VengefulSLList<Integer> vsl = new VengefulSLList<Integer>(9);
```

`sl`: Static type is `SLList`, dynamic type is `VengefulSLList`.

```java
SLList<Integer> sl = vsl;
```

`addLast` is not overrided in `VengefulSLList`, so the function in `SLList` is called. By contrast, `removeLast` is overridden by `VengefulSLList`. So the function in the `VengefulSLList` is called.

```java
sl.addLast(50);
sl.removeLast();
```

Complie-time error. The problem is, there is no such a method called `printLostItems` in the `SLList`. So even the dynamic type of `sl` is `VengefulSLList`, the operation is still not valid. So how to fix this issue? Simply put an empty method called `printLostItems` in `SLList` will solve the issue.

```java
sl.printLostItems();
```

This statement is nonsense, a `VengefulSLList` container cannot hold a variable with static type `SLList`.

```java
VengefulSLList<Integer>vsl2 = sl;
```



Furthermore, if we have a method:

```java
public static Dog maxDog(Dog d1, Dog d2) { ... }
```

Whenever we call this method, the compile-time type of the returned value is `Dog`.

```Java
Poodle frank = new Poodle("Frank", 5);
Poodle frankJr = new Poodle("Frank Jr.", 15);

Dog largerDog = maxDog(frank, frankJr);
Poodle largerPoodle = maxDog(frank, frankJr); //does not compile! RHS has compile-time type Dog
```

So if `Poodle` is a subclass of `Dog`, the final line will make a compile-time error. However, we know that `frank` and `frankJr` are 100% `Poodle`s, so is there a way out? Yes, we may force the compiler to regard the compile-time `Dog` as `Poodle` as demonstrated in the following code.

```java
// compiles! Right hand side has compile-time type Poodle after casting
Poodle largerPoodle = (Poodle) maxDog(frank, frankJr); 
```



Casting is powerful but dangerous. Since it bypasses the compiler checking, it may lead to very dangerous run-time error. i.e. the user may experience bugs. For example:

```java
Poodle frank = new Poodle("Frank", 5);
Malamute frankSr = new Malamute("Frank Sr.", 100);
Poodle largerPoodle = (Poodle) maxDog(frank, frankSr); // runtime exception!
```

`Frank Sr.` is not a `Poodle`, but we told the compiler it is. Therefore, the runtime exception occured.



#### F. Higher Order Functions

The following functions in Python defines a `tenX` normal function and a `do_twice` higher order function. 

```python
def tenX(x):
    return 10*x

def do_twice(f, x):
    return f(f(x))
```

For example, if we call `print(do_twice(tenX, 2))`, the output will be 200 since `tenX` is called twice.



In Java, we cannot use functions as input, but we can pass instance to a funcion. One way out is to create an interface:

```java
public interface IntUnaryFunction {
    int apply(int x);
}
```

Then we implements the interface

```Java
public class TenX implements IntUnaryFunction {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }

    public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(do_twice(new TenX(), 2));
    }
}
```

So we pass variables of type `TenX` into the `do_twice` function. And the statement, `System.out.println(do_twice(new TenX(), 2)); `, outputs 200 as expected.



But the author finds another way out. Without an interface, we just define the input of `do_twice` as `TenX f, int x` will do the same job (Here we change the name to `TenX_test`). 

```Java
public class TenX_test {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }

    public static int do_twice(TenX_test f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(do_twice(new TenX_test(), 2));
    }
}
```

**So the key point is to pass an instance to the function rather than the function itself.**



#### H. *Summary*

> `VengefulSLList extends SLList` means VengefulSLList "is-an" SLList, and inherits all of SLList's members:
>
> - Variables, methods nested classes
> - Not constructors Subclass constructors must invoke superclass constructor first. The `super`keyword can be used to invoke overridden superclass methods and constructors.
>
> Invocation of overridden methods follows two simple rules:
>
> - Compiler plays it safe and only allows us to do things according to the static type.
> - For overridden methods (*not overloaded methods*), the actual method invoked is based on the dynamic type of the invoking expression
> - Can use casting to overrule compiler type checking.



### 3. Subtype Polymorphism vs. Higher Order Functions (HoFs)

#### A. Subtype Polymorphism

> We've seen how inheritance lets us reuse existing code in a superclass while implementing small modifications by overriding a superclass's methods or writing brand new methods in the subclass. Inheritance also makes it possible to design general data structures and methods using *polymorphism*. 
>
> Polymorphism, at its core, means 'many forms'. In Java, polymorphism refers to how objects can have many forms or types. In object-oriented programming, polymorphism relates to how an object can be regarded as an instance of its own class, an instance of its superclass, an instance of its superclass's superclass, and so on. 
>
> Consider a variable `deque` of static type Deque. A call to `deque.addFirst()` will be determined at the time of execution, depending on the run-time type, or dynamic type, of `deque` when `addFirst` is called. As we saw in the last chapter, Java picks which method to call using dynamic method selection.

The following examples shows explicitly how the two approaches do the same job, which is to prints a string representation of the largero one of two objects.

1. Higher order function approach:

   ```Python
   def print_larger(x, y, compare, stringify):
       if compare(x, y):
           return stringify(x)
       return stringify(y)
   ```

2. Subtype polymorphism approach:

   ```python
   def print_larger(x, y):
       if x.largerThan(y):
           return x.str()
       return y.str()
   ```

The higher order function, the `compare` and the `stringify` function are determined by the programr. By contrast, the subtype polymorphism approach let the variables themselves to chose which `largerThan` and `str` function to use, according to the type of `x` and `y`.



#### B. Max Function

The following function is a very good example of a naive idea of generic `max` fucntion:

```java
public static Object max(Object[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        if (items[i] > items[maxDex]) {
            maxDex = i;
        }
    }
    return items[maxDex];
}

public static void main(String[] args) {
    Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
    Dog maxDog = (Dog) max(dogs);
    maxDog.bark();
}
```

This function looks great, but the `items[i] > items[maxDex]` is ambiguous, since the `>` comparator does not know how to compare two arbiary objects. Yes, you can write a class function to compare two instances:

```java
public static Dog maxDog(Dog[] dogs) {
    if (dogs == null || dogs.length == 0) {
        return null;
    }
    Dog maxDog = dogs[0];
    for (Dog d : dogs) {
        if (d.size > maxDog.size) {
            maxDog = d;
        }
    }
    return maxDog;
}
```

 But this is just a specific function for the `Dog` class, if we have a `Cat` class, then we will have one more `maxCat`  function. What we eagerly want is a generalised `max` function that can compare all objects. 



The essential reason why we cannot write a generic method to compare objects is that we cannot overload operator, unlike C++  (This is a major feature of C++). So the `>` operation can only be apply to some specific types of variables, which makes the generic `>` operator impossible to implement. We, however, can bypass the `>` operator by using interface as following ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/compareTo.png?raw=true)

We first create our interface :

```java
public interface OurComparable {
    public int compareTo(Object o);
}
```

and define its behaviour as follows:

- Return -1 if `this` < o.
- Return 0 if `this` equals o.
- Return 1 if `this` > o.

Now, we are able to implement the `compareTo` method in the `Dog` class:

```java
public class Dog implements OurComparable {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
}
```

**Notice that since `compareTo` takes in any arbitrary Object, we have to cast `o` to `uddaDog` to make our comparison using the `size` instance variable.**



Now, we can generalise the `max` function we previously defined. We use `cmp` as a flag to mark which instance is larger.

```java
public static OurComparable max(OurComparable[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        int cmp = items[i].compareTo(items[maxDex]);
        if (cmp > 0) {
            maxDex = i;
        }
    }
    return items[maxDex];
}
```



Actually, the program can be even more elegant, we can simplify the `compareTo` function in the `Dog` class to:

```java
public int compareTo(Object o) {
    Dog uddaDog = (Dog) o;
    return this.size - uddaDog.size;
}
```

Through this approach, we can define the `compareTo` method in each class, which makes the `Ourcomparable` method generic.

-  No need for maximization code in every class(i.e. no `Dog.maxDog(Dog[])` function required
- We have code that operates on multiple types (mostly) gracefully



#### C. Interface Mystery

> Given the `Dog` class, `DogLauncher` class, `OurComparable` interface, and the `Maximizer` class, if we omit the compareTo() method from the Dog class, which file will fail to compile?

```java
public class DogLauncher {
    public static void main(String[] args) {
        ...
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));
    }
}

public class Dog implements OurComparable {
    ...
    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
    ...
}

public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        ...
        int cmp = items[i].compareTo(items[maxDex]);
        ...
    }
}
```



The answer is that the `Dog` class fails to compile. It implements the `OurComparable` class, but does not provide a well-defined `compareTo` method. This is very much like the *abstract class* behaviour in C++.



So what if we were to omit the `implemnts ` from the header of the `Dog` class. The answer is that, the system now does not consider `Dog` as a `OurComparable`, so the statment  `System.out.println(Maximizer.max(dogs));` will lead to compile error.



#### D. Comparables

*Cast* is a very dangerous manipulation in Java, it may lead to significant run-time error. Also, no existing classes implemnt `OurComparable`(e.g. String). So our code is yet not elegant. So what is the way out? There is an interface that already exists called `Comparable` in Java. It is well-defined and appears in many libraries.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Comparable.png?raw=true)

There is only a small difference between the two pieces of code, but it has a significant impact. The `Comparable<T>` declaration avoids an casting. So the `Dog` class becomes:

```java
public class Dog implements Comparable<Dog> {
    ...
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }
}
```

And the `Comparable` method becomes:

```Java
public static Comparable max(Comparable[] items) {
    int maxDex = 0;
    for (int i = 0; i < items.length; i += 1) {
        int cmp = items[i].compareTo(items[maxDex]);
        if (cmp > 0) {
            maxDex = i;
        }
    }wojued
    return items[maxDex];
}
```



The following figures shows the structure difference between `Comparable` and `OurComparable`:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/DifferenceOCC.png?raw=true)



The last thing remains unsolve is the ugly statment in the `main` program `Dog maxDog = (Dog) max(dogs);`, if we delete the `(Dog)` casting, an type error is produced. We will talk about this in the next section.



#### E. Comparator

In addition to `Comparable`, Java has another interface called `Comparator`:

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

If we carefully compare the difference between `Comparable` and `Comparator`, the difference is that `Comparable` wants to compare something with itself, whereas `Comparator` wants to compare to different objects. 



In our case, if we want to compare the size of two dogs, it is ok to use `Comparable`, but if we also want to compare their name, we then do not have the space to write one more `compareTo` method. By contrast, we can write many nested classes to implement `Comparator`, and set different rules accordingly. The code is shown as follows:

```Java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }

    public int compareTo(Dog a){
        return this.size - a.size;
    }

    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.size - b.size;
        }
    }

    public static Comparator<Dog> getSizeComparator(){
        return new SizeComparator();
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }
}
```

Now you may ask, why cannot we write classes to implement many `Comparable`. Good question! The reason is that it compares something else with itself, and we do not intend to compare a `Dog` with a `Comparable` subclass (there should not be any `Dog` instances in the `Comparable` subclass). 



This finally lead to the following code to compare `Dogs` in a generic way:

```Java
import java.util.Comparator;

public class DogLauncher {
    public static void main(String[] args) {
        Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
        Comparator<Dog> compareSize = Dog.getSizeComparator();
        Comparator<Dog> compareName = Dog.getNameComparator();

        if (compareSize.compare(dogs[1], dogs[2]) > 0){
            dogs[1].bark();
        } else {
            dogs[2].bark();
        }

        if (compareName.compare(dogs[1], dogs[2]) > 0){
            dogs[1].bark();
        } else {
            dogs[2].bark();
    }
}
```

Please note how we use `Comparator` father class container to store `SizeComparator` and `NameComparator` subclass. The class hierarchy now looks like:![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/ComparatorDiagram.png?raw=true)



Please note that the `Dog` class is not part of the class hierarchy. It is a subclass of `Comparable`.



>**To summarize, interfaces in Java provide us with the ability to make *callbacks*. Sometimes, a function needs the help of another function that might not have been written yet (e.g. `max` needs `compareTo`). A callback function is the helping function (in the scenario, `compareTo`). In some languages, this is accomplished using explicit function passing; in Java, we wrap the needed function in an interface.**
>
>**A Comparable says, "I want to compare myself to another object". It is imbedded within the object itself, and it defines the *natural ordering* of a type. A Comparator, on the other hand, is more like a third party machine that compares two objects to each other. Since there's only room for one `compareTo`method, if we want multiple ways to compare, we must turn to Comparator.**



### 4. Java Libraries and Packages

#### A. Abstract Data Types (ADTS)

For now, we have seen some *abstract data type* like [`List61B`](#B. Hypernyms, Hyponyms, and Interface Inheritance) or `Deque`. ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Abstract.png?raw=true)

We have the following methods without defnitions in the `Deque` interface:

```java
public void addFirst(T item);
public void addLast(T item);
public boolean isEmpty();
public int size();
public void printDeque();
public T removeFirst();
public T removeLast();
public T get(int index);
```

They are actually implemented in the two implemented classes. Conceptually, we call deque an *abstract data type*. It only contains some declarations of some desired behaviours but not definition of how to exhibit those behaviours. Thus, it is abstract, which is very similalr to the *abstract class* in C++.



#### B. Java Libraries

>Java has certain built-in Abstract data types that you can use. These are packaged in Java Libraries.The three most important ADTs come in the java.util library:
>
>* List: an ordered collection of items -> A popular implementation is the [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
>* Set: an unordered collection of strictly unique items (no repeats) -> A popular implementation is the [HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)
>* Map: a collection of key/value pairs. You access the value via the key. -> A popular implementation is the [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)

Some exercises:

> 1. Write a method `getWords` that takes in a `String inputFileName` and puts every word from the input file into a list. Recall how we read words from a file in proj0. Hint: use `In`.
> 2. Write a method `countUniqueWords` that takes in a `List<String>` and counts how many **unique** words there are in the file.

The answer to the first problem can be solved by using the built-in interface `List` and the `In` class to read the file.

```java
public static String cleanString(String s) {
    String a = s.toLowerCase().replaceAll("[^a-z]]", "");
    return a.replaceAll(",", "");
}

public static List<String> getWordsFunc(String filename) {
    List<String> words = new ArrayList<String>();
    In in = new In(filename);
    while (!in.isEmpty()) {
        String nextWord = cleanString(in.readString());
        words.add(nextWord);
    }
    return words;
}
```



As for word couting, the `Set` interface may be of the great help. 

```Java
    public static int countUniqueWords(List<String> words) {
        Set<String> wordSet = new HashSet<String>();
/*        for (int i =0 ; i< words.size(); i++){
            String ithWord = words.get(i);
        }*/

        for (String ithWord : words) { /*This syntax is very much like *in*  in Python*/
            wordSet.add(ithWord);
        }
        return wordSet.size();
    }
```

The `for (String ithWord : words)` is an advanced `for` statament, which is very similar to the`for(x in y){}` in  Python. This avoids some unnecessary variables, such as `i`.



The last exercise is:

> Write a method `collectWordCount` that takes in a `List<String> targets` and a `List<String> words` and finds the number of times each target word appears in the word list.

```Java
public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    /*We have not seen any of the words*/
    for (String t : targets) {
        counts.put(t, 0);
    }

    for (String s : words) {
        if (counts.containsKey(s)) {
            int oldCount = counts.get(s);
            /*Cannot use oldCount++, cuz this happens last*/
            counts.put(s, oldCount + 1);
        }
    }
    return counts;
}
```

Again, we can easily solve the problem by using the built-in interface `Map`. A very tricky point is this program is that we cannot use the statement `oldCount++`, because this happens after it goes into the `counts`. The only way out is `oldCount + 1`, which is regarded as a whole variable by Java compiler.



Now, let's review the structur of these classs and interfaces:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/interfaceandclass.png?raw=true)

This hierarchy looks fantastic and reasonable. The `Collection` interface is a rather vague. The official statement is that: 

> Collections represent a gour of objects, known as its elements.



#### C. Java vs. Python

Basically, the equivallent Python code for the `collectWordCount` looks net and tidy, whereas the Java code looks very cumbersome.![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/javaandpython.png?raw=true)



However, Java is also a great programming language because of the following reasons:

>- Arguably, takes less time to write programs, due to features like:
>  - Static types (provides type checking and helps guide programr).
>  - Bias towards interface inheritance leading to cleaner subtype polymorphism.
>  - Access control modifiers make abstraction barriers more solid.
>- More efficient code, due to features like:
>  - Ability to have more control over engineering tradeoffs.
>  - Single valued arrays lead to better performance.
>
>- Basic data structures more closely resemble underlying hardware:
>  - Would be weird to do ArrayDeque in Python, since there is no need for array resizing. However, in hardware (see 61C), variable length arrays don’t exist.



#### D. Abstract Classes

Please consult this [website](https://joshhug.gitbooks.io/hug61b/content/chap4/chap44.html) for detailed information. In one word, the abstract classes in Java is very similar to that in C++. The main difference between Java interface and Java abstract class is shown as folloing:

Interface:

> - All methods must be public.
> - All variables must be public static final.
> - Cannot be instantiated
> - All methods are by default abstract unless specified to be `default`
> - Can implement more than one interface per class

Abstract class:

> - Methods can be public or private
> - Can have any types of variables
> - Cannot be instantiated
> - Methods are by default concrete unless specified to be `abstract`
> - Can only implement one per class

 

#### E. Packages

Package names give a canonical name for everything. (Perhaps this is very similar to namespace in C++?). Packnames can avoid conflict. For example, if we have multiple classes with the same name, we can use package name to differentiate them like `ug.joshh.animal.Dog d = new ug.joshh.animal.Dog()`. Just like C++, where we declare the namespace at the beginning of a file, we can use `import ug.joshh.animal.Dog` to avoid the long statment, making it `Dog d = new Dog()`. 



##  V. Generics and AutoBoxing

### 1. Autoboxing

#### A. Autoboxing and Unboxing

We have defined a number of generic classes that can use the `<>` syntax to implment different types of `Array`, `IntList`, `ArrayDeque` and so on. Something looks ambiguous, however. Inside the `<>` syntax, we cannot use something like `int`, `short`, `long` etc. For example, the `ArrayDeque<int>` will produce a compile-time error. We have to use `Integer`, `Short`, `Long`, respectively. These are called wrapper classes. The following chart shows the primitive type names and its corrresponding wrapper classes names.![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/WrapperClasses.png?raw=true)

So it looks like we have to do the following in order to avoid any syntax error:

```Java
public class BasicArrayList {
    public static void main(String[] args) {
      ArrayList<Integer> L = new ArrayList<Integer>();
      L.add(new Integer(5));
      L.add(new Integer(6));

      /* Use the Integer.valueOf method to convert to int */
      int first = L.get(0).valueOf();
    }
}
```

But this  is not true, we do not necessarily need to do the conversion stuff. Java is smart enough to let us write codes like:

```Java
public class BasicArrayList {
    public static void main(String[] args) {
      ArrayList<Integer> L = new ArrayList<Integer>();
      L.add(5);
      L.add(6);
      int first = L.get(0);
    }
}
```



Yes, Java can automatically "box" and "unbox" values between a primitive type and its reference type (i.e. wrapper classs). So the following piece of code works fine:

```Java
public class IntAndIntger {

    public static void blah(Integer x) {
        System.out.println(x);
    }

    public static void blahPrimitive(int x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        int x = 20;
        blah(x);

        Integer y = new Integer(20);
        blahPrimitive(y);

    }
}
```

Java automatically does the conversion. So there is not need to use any `new Integer` or `valueOf` stuffs.



> **There are a few things to keep in mind when it comes to autoboxing and unboxing:**
>
> - Arrays are never autoboxes or auto-unboxed, e.g. if you have an array of integers `int[] x`, and try to put its address into a variable of type `Integer[]`, the compiler will not allow your program to compile.
> - Autoboxing and unboxing also has a measurable performance impact. That is, code that relies on autoboxing and unboxing will be slower than code that eschews such automatic conversions.
> - Additionally, wrapper types use much more memory than primitive types. On most modern comptuers, not only must your code hold a 64 bit reference to the object, but every object also requires 64 bits of overhead used to store things like the dynamic type of the object (The variables `x` uses 160 bits of memory rather than 32 bits).![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/MemoryUsageInt.png?raw=true)



#### B. Widenning

In terms of mathematics, `double` type is more general than `int` type. That is, `double` is wider than `int`. Java is smart, it knows that an integer can be expressed as a double for 100% sure. So the following code works. But Java cannot automatically conver a `double` variable with a value of 20 to `int`, despite 20 is an integer. we have to manually cast the `double ` to `int`. The following piece of code demonstrate these behaviours.

```Java
public class Widenning {

    public static void blahDouble(double x) {
        System.out.println("double: " + x);
    }

    public static void blahInt(int x) {
        System.out.println("int: " + x);
    }

    public static void main(String[] args) {
        int x = 20;
        blahDouble(x);

        double y = 20;
        blahInt((int) y);
    }
}
```



This is just a superfacial demonstration. For more details, please consult the [offical link](https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html).



### 2. Immutability

#### A. Immutability

> The notion of immutability is one of the things you might never have known existed, but that can greatly simplify your life once you realize it's a thing (sort of like the realization you get as an adult that nobody *really* knows what they're doing, at least when they first start doing something new).
>
> An immutable data type is a data type whose instances cannot change in any observable way after instantiation.
>
> For example, `String` objects in Java are immutable. No matter what, if you have an instance of `String`, you can call any method on that `String`, but it will remain completely unchanged. This means that when `String` objects are concatenated, neither of the original Strings are modified -- instead, a totally new `String` object is returned. 
>
> Mutable datatypes include objects like `ArrayDeque` and `Planet`. We can add or remove items from an `ArrayDeque`, which are observable changes. Similarly, the velocity and position of a `Planet` may change over time.
>
> Any data type with non-private variables is mutable, unless those variables are declared `final` (this is not the only condition for mutability -- there are many other ways of defining a data type so that it is mutable). This is because an outside method can change the value of non-private variables, leading to observable change.

The following code provides an example for using the keyword `final`:

```Java
public class Date {
    public final int month;
    public final int day;
    public final int year;
    private boolean contrived = true;
    public Date(int m, int d, int y) {
        month = m; day = d; year = y;
    }
}
```

Once an instance of the `Date` class is instantiated, it is not possible to change its memeber variables. `moth`, `day`, `year` are declared as `final` variables, whereas `contrived` is  a private variable. Thus, the whole class can not be modified in any aspect.



> **Advantages of immutable data types:**
>
> - Prevents bugs and makes debugging easier because properties cannot change ever
> - You can count on objects to have a certain behavior/trait
>
> Disadvantages:
>
> - You need to create a new object in order to change a property



**Important notes:**

If you write a statement like the following, you actually makes `deque`'s reference  `final`. That is, you cannot make `deque` point to another address, but obvioursly, you can `addFirst`, `addLast`, etc. Please remember, changing the member variable inside  `deque` does not have any impact on the reference address. Therefore, it does not violate the `final` declaration.

```java
public final ArrayDeque<String>() deque = new ArrayDeque<String>();
```



### 3. Generics 

#### A. Creating Another Generic Class

We have already created a number of generic classs, such as `DDLists`, `ALists`. Let us now create a new data type: `Maps` which is equivallent to`Dictionary` in Python. Here, we want to create an `ArrayMap`. So we need to use arrays to implement functions of `Map`. First of all, we create a `Map61B` interface as follows:

```java
import java.util.List;
public interface Map61B<K, V> {
    public void put(K key, V value); // Associate key with value.
    public boolean containsKey(K key); // Checks if map contains the key.
    public V get(K key); // Returns value, assuming key exists.
    public List keys(); // Returns a list of all keys.
    public int size(); // Returns number of keys.
}
```

And then we implent all these functions in our `ArrayMap` class (The author's solution):

```Java
import java.util.List;
import java.util.ArrayList;

public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        size = 0;
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
    }

    private void expand() {
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);
        keys = newKeys;
        values = newValues;
    }

    public void put(K key, V value) {
        if (size == keys.length) {
            expand();
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public List<K> keys() {
        List<K> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(keys[i]);
        }
        return list;
    }

    public int size() {
        return size;
    }
}
```

The implement was not tricky, but there was one thing pretty annoying. That is how to compare two objects with unkown types. The `==` operation just compares the value, so when it is quite useless for generic circumstances. As we can see, the **`.equals` **method is used instead, to make the method generic. The author's code used a lot of `for` loops, which is quite not elegant. The Hug's solution, however, makes the program elegant by adding a helper method as follows;

```java
  /**
    * Returns the index of the key, if it exists. Otherwise returns -1.
    **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
            return i;
        }
        return -1;
    }
```

This method can avoid repetitive `for` loops in nearly all methods. Here is the Hug's solution:

```Java
package Map61B;

import java.util.List;
import java.util.ArrayList;

/***
 * An array-based implementation of Map61B.
 ***/
public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
    * Returns the index of the key, if it exists. Otherwise returns -1.
    **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
            return i;
        }
        return -1;
    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }
}
```

Please note that the original version had a mistake as an exercise for student to figure out. In the `keys` method, the `for` iterating used `i < keys.length` instead of `i < size`. 



The modified author's solution:

```Java
import java.util.List;
import java.util.ArrayList;

public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        size = 0;
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
    }

    private void expand() {
        K[] newKeys = (K[]) new Object[keys.length * 2];
        V[] newValues = (V[]) new Object[values.length * 2];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);
        keys = newKeys;
        values = newValues;
    }

    public int indexFinder(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void put(K key, V value) {
        if (size == keys.length) {
            expand();
        }
        if (indexFinder(key) != -1) {
            values[indexFinder(key)] = value;
        } else {
            keys[size] = key;
            values[size] = value;
            size++;
        }
    }

    public boolean containsKey(K key) {
        return !(indexFinder(key) == -1);
    }

    public V get(K key) {
        if (indexFinder(key) == -1) {
            return null;
        } else {
            return values[indexFinder(key)];
        }
    }

    public List<K> keys() {
        List<K> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(keys[i]);
        }
        return list;
    }

    public int size() {
        return size;
    }
}
```

Actually, the behaviour of `put` is slightly changed. That is, if a `key` already existes, the `put` function will just modify its value. 



When we test the class, we find it very subtle:

```java
@Test
public void test() { 
    ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
    am.put(2, 5);
    int expected = 5;
    assertEquals(expected, am.get(2));
}
```

The following code does not compile because `am.get(2)` returns an `Intger` object, whereas expected is a pure `int`. So the Java compiler get confusing, it does not know whether to compare two object or to primitivie type variables, error message is like:

```Java
$ javac ArrayMapTest.java
ArrayMapTest.java:11: error: reference to assertEquals is ambiguous
    assertEquals(expected, am.get(2));
    ^
    both method assertEquals(long, long) in Assert and method assertEquals(Object, Object) in Assert match
```

 There are at least the following solutions to avoid this issue:

```java
   @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<>();
        am.put(2, 5);
        int expected = 5;
        assertEquals(expected, (int) am.get(2));
        assertEquals((Integer) expected, am.get(2));
        assertEquals(expected, (long) am.get(2));
    }
```

To unbox `am.get(2)` to a primitive type variable or to make `expected` as an `Integer`.



#### B. Generic Methods

Now lets move to the next phase:

> The goal for the next section is to create a class `MapHelper` which will have two methods:
>
> - `get(Map61B, key)`: Returns the value corresponding to the given key in the map if it exists, otherwise null.
>   - This is useful because `ArrayMap` currently has a bug where the get method throws an ArrayIndexOutOfBoundsException if we try to get a key that doesn't exist in the `ArrayMap`.
> - `maxKey(Map61B)`: Returns the maximum of all keys in the given `ArrayMap`. Works only if keys can be compared.

Now, we want to create a new class called `MapHelper` that has two extra functions for the `Map61B` class. In order to make it generic, we may want to  add the `<K, V>` within the class declaration, but this makes no sense. No one want to instantiate an instance of `MapHelper`. **The better way is to just make the methods themselves generic by adding the `<K, V>` declaration.**



The author's solution for the `get` method is shown below:

```Java
    public static <K, V> V get(Map61B<K, V> m, K k) {
        if (m.containsKey(k)) {
            return m.get(k);
        }
        return null;
    }
```

The `<K, V>` declaration makes it a generic method, and therefore can handle any `Map61B` instances. Please note that if this delcaretion is missing, Java compiler will produce an error saying that it does not know what the `K` and `V` mean, despite the `Map61B` is a generic class. 



The author's test code is as follows:

```Java
   @Test
    public void testGet() {
        Map61B<String, Integer> m = new ArrayMap<>();
        m.put("A", 1);
        m.put("B", 2);
        m.put("C", 3);
        int expected = 3;
        int actual = MapHelper.get(m, "C");
        assertEquals(expected, actual);
    }
```



Now, how about `maxKey` method? We can still make the `public static <K, V> K maxKey(Map61B<K, V> map) ` declaration but this is dangerous. Because `K` may be of any type, so we cannot use the `>` operator, we have to use the `compareTo` method. Again, not all types of variables contain the `compareTo` method. Fortunately, a tiny change may be of great help! **We just use the `public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {...}` declaration to specify that this method only accept comparable `K` as argument.** The author's solution and test are shown as following:

```Java
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> m) {
        List<K> keys = m.keys();
        K max = keys.get(0);
        for (int i = 0; i < keys.size(); i++){
            if ((max.compareTo(keys.get(i)) < 0)){
                max = keys.get(i);
            }
        }
        return max;
    }
```

```Java
    @Test
    public void testMax() {
        Map61B<Integer, Integer> m = new ArrayMap<>();
        m.put(3, 1);
        m.put(2, 2);
        m.put(1, 3);
        m.put(0, 3);
        int expected = 3;
        int actual = MapHelper.maxKey(m);
        assertEquals( expected, actual);
    }
```



#### C. Type Upper Bound

> You might be wondering, why does it "extend" comparable and not "implement"? Comparable is an interface after all.
>
> Well, it turns out, "extends" in this context has a different meaning than in the polymorphism context. 
>
> When we say that the Dog class extends the Animal class, we are saying that Dogs can do anything that animals can do and more! We are **giving** Dog the abilities of an animal. When we say that K extends Comparable, we are simply stating a fact. We aren't **giving** K the abilities of a Comparable, we are just saying that K **must be** Comparable. This different use of `extends` is called type upper bounding. Confusing? That's okay, it *is* confusing. Just remember, in the context of inheritance, the `extends` keyword is active in giving the subclass the abilities of the superclass. You can think of it as a fairy Godmother: she sees your needs and helps you out with some of her fairy magic. On the other hand, in the context of generics, `extends` simply states a fact: You must be a subclass of whatever you're extending. **When used with generics (like in generic method headers), `extends` imposes a constraint rather than grants new abilities.** It's akin to a fortune teller, who just tells you something without doing much about it.



#### D. *Summary*

> ## Summary
>
> We’ve seen four new features of Java that make generics more powerful: 
>
> - Autoboxing and auto-unboxing of primitive wrapper types.
> - Promotion/widening between primitive types.
> - Specification of generic types for methods (before return type).
> - Type upper bounds in generic methods (e.g. `K extends Comparable<K>`).



## VI. Exceptions, Iterators, Iterables, Object Methods

### 1. Lists. Sets, and ArraySet

#### A. Lists in Real Java Code

In real world, Java has well-defined `List` and `ArrayList`. We can create an `ArrayList` use the `java.util.List<Integer> L = new java.util.ArrayList<>();` statement. But this is too verbose. We can actually import the libraries to avoid using canonical name as follows:

```java
import java.util.List;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();
        L.add(5);
        L.add(10);
        System.out.println(L);
    }
}
```



#### B. Sets

> Sets are a collection of unique elements - you can only have one copy of each element. There is also no sense of order.

In Java, we have a `Set` interface and one of its implementation is `HashSet`. The following piece of code demonstrate its behaviour:

```java
Set<String> s = new HashSet<>();
s.add("Tokyo");
s.add("Lagos");
System.out.println(S.contains("Tokyo")); // true
```



This is equivallent to the Python program:

```python
s = set()
s.add("Tokyo")
s.add("Lagos")
print("Tokyo" in s) // True
```



#### C. ArraySet

> Our goal is to make our own set, `ArraySet`, with the following methods:
>
> - `add(value)`: add the value to the set if not already present
> - `contains(value)`: check to see if ArraySet contains the key
> - `size()`: return number of values

The author's solution:

```Java
public class ArraySet<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i] == null){
                continue;
            }
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    private void expand(){
        T[] newItems = (T[]) new Object[size * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if(!this.contains(x)) {
            if (size == items.length) {
                expand();
            }
            items[size] = x;
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
```

The author noticed that the Hug's solution cannot pass the main program test, because a `null` value is added to `s`. This will ultimately lead to an error, `Cannot invoke "Object.equals(Object)" because "this.items[i]" is null`. So the author let the program skip any `null` values in the `ArraySet`.



### 2. Throwing Exceptions

Oh, the author just found that the bug in Hug's code was for the next chpater examples. The `NullPointerException` error is an *exception*. It cause normal flow of control to stop. But we can actually choose to throw our own exceptions: `throw new ExceptionObject(parameter1, ...)`. So, when a user try to add a `null` value to the `ArraySet`, we can throw an exception as following:

```java
/* Associates the specified value with the specified key in this map.
   Throws an IllegalArgumentException if the key is null. */
public void add(T x) {
    if (x == null) {
        throw new IllegalArgumentException("can't add null");
    }
    if (contains(x)) {
        return;
    }
    items[size] = x;
    size += 1;
}
```

Thereafter, we can get an error message `Exception in thread "main" java.lang.IllegalArgumentException: can't add null`, this is useful because:

* We have control of our code: we consciously decide at what point to stop the flow of our program

* More useful Exception type and helpful error message for those using our code

There are other ways to avoid the `NullPointerException` error. **Approach 1**: Don't add `null` to the array if it is passed into `add` **Approach 2**: Change the `contains` method to account for the case if `items[i] == null`. The implementation of these method can be found in the author's Github repository, [CS61B_reading](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings). Of the most important, this program should be user friendly, that is to let them know that adding a `null` value is not reasonable and can casue severe run-time error.



### 3. Iteration 

The enhanced `for` loop can be used to print elements in Java's `HashSet` as follows:

```java
Set<String> s = new HashSet<>();
s.add("Tokyo");
s.add("Lagos");
for (String city : s) {
    System.out.println(city);
}
```

But we cannot do this with our `ArraySet`. Is there a way out?



#### A. Enhanced `for` loop

Let us first start from understanding the underlying nature of the enhanced `for` loop. The following code:

```java
Set<String> s = new HashSet<>();
...
for (String city : s) {
    ...
}
```

can be translate to:

```java
Set<String> s = new HashSet<>();
...
Iterator<String> seer = s.iterator();
while (seer.hasNext()) {
    String city = seer.next();
    ...
}
```

 In the piece of code above, we see an ambiguous `iterator`, this is the key of how enhanced `for` loop works.  First, we created an `iterator`. Thereafter, we loop through the list with the while loop, and the `seer.hashNext` checks if there are items remaiing in the `iterator`.  Then, the `next()` method of `seer` returns the current value and advance to the next value.



#### B. Implementing Iterators

In order to let our `Arrayset` support `iterator`, we need to add the following code to the `ArraySet` class:

```Java
public Iterator<T> iterator() {
    return new ArraySetIterator();
}

private class ArraySetIterator implements Iterator<T> {
    private int wizPos;

    public ArraySetIterator() {
        wizPos = 0;
    }

    public boolean hasNext() {
        return (wizPos < size);
    }

    public T next() {
        T returnItem = items[wizPos];
        wizPos++;
        return returnItem;
    }
}
```

The `Iterator` interface has to abstract functions: `hasNext` and `next`. With this well defined newe class, we can not implemnt the redudant version of loop in `ArraySet`:

```Java
Iterator<Integer> aseer = aset.iterator();

while(aseer.hasNext()){
    int x = aseer.next();
    System.out.println(x);
}
```

But we still cannot use the enhanced `for` loop to make everything easier:

```Java
for (int i : aset){
    System.out.println(i);
}
```

This is because Java does not know if `ArraySet` support `iterator` functions. Thus, we have to declare that the our class implement the `Iterable` interface by using the `public class ArraySet<T> implements Iterable<T>` statement. With eveything done, we can now use the enhanced `for` loop to iterator our `ArraySet.`



Please note that, when using iterators, Java compiler always checks it the class contains a `interator` method? And does the `iterator` method has `next` and `hasNext` methods? 



### 4. Object Methods

All classes has the following method because the fact that they are inherited from the general `Object` class.

- `String toString()`
- `boolean equals(Object obj)`
- `Class <?> getClass()`
- `int hashCode()`
- `protected Objectclone()`
- `protected void finalize()`
- `void notify()`
- `void notifyAll()`
- `void wait()`
- `void wait(long timeout)`
- `void wait(long timeout, int nanos)`

We will now focus on the first two method and use inheritance to override these two methods.



#### A. toString()

The default behaviour of the `toString` method is that it will print the location of the object in memory in hexidecimal string.

```java
String s = dog.toString()
System.out.println(s)
```

Therefore, the above code will output an useless address. The most built-in data type like `ArryList` or `Arrays` have their own `toString` method. So we always see well-formatted ouput. Now let us try to write a `toString` method for our `ArraySet` class.



The author's solution:

```Java
@Override
public String toString() {
    String aset = "(";
    for (int i = 0; i < size - 1; i++) {
        aset += items[i].toString();
        aset += ", ";
    }
    aset += items[size - 1];
    aset += ")";
    return aset;
}
```

This looks nice, and indeed it works for our `ArraySet` class. But we know that `String` is immutable. Therefore, everytime we call the `+` operator, we are actually creating a new `String`, which slow down the program. For example, 

> Let's say concatenating one character to a string takes 1 second. If we have an ArraySet of size 5: `{1, 2, 3, 4, 5}`, how long would it take to run the `toString()` method?

The answer is :

> We set `returnString` to the left bracket which takes one second because this involves adding `{` to the empty string `""`. Adding the first element will involve creating an entirely new string, adding } and 1 which would take 2 seconds. Adding the second element takes 3 seconds because we need to add `{`, `1`, `2`. This process continues, so for the entire array set the total time is `1 + 2 + 3 + 4 + 5 + 6 + 7.`



Fortunately, in Java, there is a type called `StringBuilder`. It avoids the creation of new strings when we append new elements. The solution is:

```java
public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
}
```

This significantly minimises the running time of our progarmme.



#### B. equals()

The `==` operator and the `equal` method are different in Java. The `==` operator can only check if the two boxes hold the exactly same thing. For example:

```java
public class Doge {

    public int age;
    public String name;

    public Doge(int age, String name){
        this.age = age;
        this.name = name;
    }
    public static void main(String[] args) {

        int x = 5;
        int y = 5;
        int z = 6;

        Doge fido = new Doge(5, "Fido");
        Doge doggo = new Doge(6, "Doggo");
        Doge fidoTwin = new Doge(5, "Fido");
        Doge fidoRealTwin = fido;

        System.out.println(x == y);
        System.out.println(x == z);
        System.out.println(fido == doggo);
        System.out.println(fido == fidoTwin);
        System.out.println(fido == fidoRealTwin);
    }
}
```

 the above code prints out:

- `True`
- `False`
- `False`
- `False`
- `True`

The only tricky thing is that `fido` and `fidoTwin` has different addresss, though they have the same contents. Thus, there are different for `==` operator.  The details can be found in the following figure:![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Doge.png?raw=true)



So we need to redefine the behaviour of `equalsTo` to actually let it work as we wish. As an example, we add the following code to the `ArraySet` class.

```Java
@Override
public boolean equals(Object other) {
    if (this == other) {
        return true; 
    }

    if (other == null) {
        return false;
    }

    if (this.getClass() != other.getClass()) {
        return false;
    }

    ArraySet<T> o = (ArraySet<T>) other;

    for (T i : this) {
        if (o.contains(i)) {
            return false;
        }
    }
    return true;
}
```

with the main method being:

```Java
public static void main(String[] args) {
    ArraySet<Integer> aset = new ArraySet<>();
    aset.add(5);
    aset.add(23);
    aset.add(42);

    //iteration
    for (int i : aset) {
        System.out.println(i);
    }

    //toString
    System.out.println(aset);
    ArraySet<Integer> aset2 = new ArraySet<>();
    aset2.add(5);
    aset2.add(23);
    aset2.add(42);

    System.out.println(aset.equals(aset2));
    System.out.println(aset.equals(null));
    System.out.println(aset.equals("fish"));
    System.out.println(aset.equals(aset));

    //equals
}
```

Now the `equals` method output the right thing: `false false false true`.  In the `equals` method, the first `if` statement compares if the two object points to the same addresss, and return `true` is so. The second `if` statement checks if the input argument is a `null`, and return `false` if so. The third `if` statement compares if the two objects are of the same types, return `false` if not. The last `if` statement compares the rael contents of the two object. Please note that, we use an `Object` as the input argument, so we need to cast it to `ArraySet`. 



>**Rules for Equals in Java:** When overriding a `.equals()` method, it may sometimes be trickier than it seems. A couple of rules to adhere to while implementing your `.equals()` method are as follows:
>
>1.) `equals` must be an equivalence relation
>
>- **reflexive**: `x.equals(x)` is true
>- **symmetric**: `x.equals(y)` if and only if `y.equals(x)`
>- **transitive**: `x.equals(y)` and `y.equals(z)` implies `x.equals(z)`
>
>2.) It must take an Object argument, in order to override the original `.equals()` method
>
>3.) It must be consistent if `x.equals(y)`, then as long as `x` and `y` remain unchanged: `x` must continue to equal `y`
>
>4.) It is never true for null `x.equals(null)` must be false



## VII. Packages and Access Control

### 1. Packages

Packages is very much like the so called namespace in C++. It avoid any conflict for large project in real world where two classes with the same class name exist. Yes, they can be in different packages (namespaces). For example, the package name of an animal class can be  `ug.joshh.animal`. The convention is

```Java
ug.joshh.animal; // note: his website is joshh.ug
```

if your code is intended for distribution.



#### A. Using Packages

For a `Dog`  class in `ug.joshh.animal` If you are currently working in the package, it is fine to just write:

```java
Dog d = new Dog(...);
```

But you need to use the following ways to access the `Dog` class if you are not in the package workspace:

1. Use the full name:

```java
ug.joshh.animal.Dog d = new ug.joshh.animal.Dog(...);
```

2. Import the package:

```java
import ug.joshh.animal.Dog;
...
Dog d = new Dog(...);
```



#### B. Creating a Package

> Creating a package takes the following two steps:
>
> 1.) Put the package name at the top of every file in this package
>
> ```java
> package ug.joshh.animal;
> 
> public class Dog {
>     private String name;
>     private String breed;
>     …
> }
> ```
>
> 2.) Store the file in a folder that has the appropriate folder name. The folder should have a name that matches your package:
>
> i.e. `ug.joshh.animal` package is in ug/joshh/animal folder
>
> **Creating a Package, in IntelliJ**
>
> 1.) File → New Package
>
> 1.) Choose package name (i.e. “ug.joshh.animal”)
>
> **Adding (new) Java Files to a Package, in IntelliJ**
>
> 1.) Right-click package name
>
> 2.) Select New → Java Class
>
> 3.) Name your class, and IntelliJ will automatically put it in the correct folder + add the “package ug.joshh.animal” declaration for you.
>
> **Adding (old) Java Files to a Package, in IntelliJ**
>
> 1.) Add “package [packagename]” to the top of the file.
>
> 2.) Move the .java file into the corresponding folder.



#### C. Default Packages

Actually, when you create classes without any specific package declaration, you are using the Java default package. This process is automatically completed by Java iteself. So if you are in the `ug.joshh.animal` class, you will not be able to access any classes in the default package as following:

```java
DogLauncher.launch(); // won’t work
default.DogLauncher.launch(); // doesn’t exist
```

Also, it is not possible to use `import default` as a solution. **Therefore, your Java files should generally start with an explicit package declaration, except for tiny programs that is for demonstration use only.**



### 2. Accesss Control

#### A. Accesss Control

There are **four** accesss modifiers in the Java language, including `private`, `package private`, `protected`, `public`. The difference between these modifiers can be found in the chart below:

|              Modifier               | Class | Package | Subclass | World |
| :---------------------------------: | :---: | :-----: | :------: | :---: |
|              `public`               |   Y   |    Y    |    Y     |   Y   |
|             `protected`             |   Y   |    Y    |    Y     |   N   |
| `package private` (default setting) |   Y   |    Y    |    N     |   N   |
|              `private`              |   Y   |    N    |    N     |   N   |

1. `public`: Open and promised. to the world;

2. `protected`: Subtypes might need it, but rest of the world will not;

3. `package private` (default setting): Classes that are in the same package might need it;

4. `private`: Only the class needs this piece of code.

   

**Please note that you do not to declare `package private` under any circumstances, as it is the default setting of the Java language. Therefore, if you are writting codes in the default package, everything is declare as `package private`, and thus behave as `public` does due to the fact that they are all in the default package.**



#### B. Access Control Subtleties

Assume we have the [following code](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/7.%20Packages%20and%20Access%20Control/Access%20Control%20Subtleties). What will the compiler do with the `Clinet` class?

```java
package universe;
public interface BlackHole {
    void add(Object x); // this method is public, not package-private!
}
```

```Java
package universe;
public class CreationUtils {
    public static BlackHole hirsute() {
         return new HasHair();
    }
}
```

```Java
package universe;
class HasHair implements BlackHole {
    Object[] items;
    public void add(Object o) { ... }
    public Object get(int k) { ... }
}
```

```Java
import universe.*;
import static universe.CreationUtils.hirsute;

class Client {
    void demoAccess() {
        // Fine, BlackHole is public, hirsute is public, HasHair is a subclass of BlackHole
        BlackHole b = hirsute(); 
        // Fine, the BlackHole interface has an add method, the dyanmic type HasHair has an implementation
        b.add("horse"); 
        // Compile error, the BlackHolee interface does not have an add method
        b.get(0);
      	// Compile error, HasHair is package private, cannot be accessed outside of the package
        HasHair hb = (HasHair) b;
    }
}
```

The author's answer is shown as comments. Hugs' original answer is:

> **ANSWER**
>
> - b.get(0); This line errors because `b` is of static type `BlackHole`, but the `BlackHole` interface does not define a `get` method! Even though you and I both know that `b` is dynamically a `HasHair`, and thus has the `get` method, the compiler bases its checks off the static type.
> - HasHair hb = (HasHair) b; This one is tricky, but notice that the `HasHair` class is not a public class - it's package-private. This means that `Client`, a class outside of the `universe` package, can't see that the `HasHair` class exists.

The  behaviour of the `hirsute` line is very much like that in the iterators in [homework 1](# 1. Packages, Interfaces, Generics, Exceptions, Iteration). Although the `BufferIterator` is private, we can use a public method to return such an instance. Also, despite the `HasHair` class is package private, its static type is `BlackHole` in this piece of code, so we can use it anywhere, as `BlackHole` is public. In summary, **Access is Based Only on Static Types**.



Please note that, method declaration in an interface is `public` by default, i.e. the `add` method in the `BlackHole` interface is public by default. So the following code works perfectly fine outside the package.

```Java
import universe.*;

public class SubBlackHole implements BlackHole {
    public void add(Object x) {
        return;
    }
}
```



## VIII. Efficinet Programming

> “An engineer will do for a dime what any fool will do for a dollar” -- Paul Hilfinger
>
> Efficiency comes in two flavors:
>
> 1.) Programming cost.
>
> - How long does it take to develop your programs?
> - How easy is it to read, modify, and maintain your code?
>
> 2.) Execution cost (starting next week).
>
> - How much time does your program take to execute?
> - How much memory does your program require?
>
> Today, we will be focusing on how to reduce programming cost. Of course, want to keep programming costs low, both so we can write code faster and so we can have less frustrated people which will also help us write code faster (people don't code very fast when they are frustrated).
>
> Some helpful Java features discussed in 61B:
>
> - Packages.
>   - Good: Organizing, making things package private 
>   - Bad: Specific
> - Static type checking.
>   - Good: Checks for errors early , reads more like a story 
>   - Bad: Not too flexible, (casting) 
> - Inheritance.
>   - Good: Reuse of code 
>   - Bad: “Is a”, the path of debugging gets annoying, can’t instantiate, implement every method of an interface 
>
> We will explore some new ways in this chapter!



### 1. Encapsulation, API's, ADT's

#### A. Encapsulation

> We will first define a few terms:
>
> - **Module:** A set of methods that work together as a whole to perform some task or set of related tasks. 
> - **Encapsulated:** A module is said to be encapsulated if its implementation is completely hidden, and it can be accessed only through a documented interface.



#### B. API's

> An API(Application Programming Interface) of an ADT is the list of constructors and methods and a short description of each.
>
> API consists of syntactic and semantic specification.
>
> - Compiler verifies that syntax is met.
>   - AKA, everything specified in the API is present.
> - Tests help verify that semantic are correct.
>   - AKA everything actually works the way it should. 
>   - Semantic specification usually written out in English (possibly including usage examples). Mathematically precise formal specifications are somewhat possible but not widespread.



#### C. ADT's

> ADT's (Abstract Data Structures) are high-level types that are defined by their **behaviours**, not their implementations.
>
> i.e.) Deque in [Proj1a](1. DLList & Array (Project 1a)) was an ADT that had certain behaviors (addFirst, addLast, etc.). But, the data structures we actually used to implement it was ArrayDeque and LinkedListDeque
>
> Some ADT's are actually special cases of other ADT's. For example, Stacks and Queues are just lists that have even more specific behavior.



> Write a Stack class using a Linked List as its underlying data structure. You only need to implement a single function: push(Item x). Make sure to make the class generic with "Item" being the generic type!



Method one and its test:

```Java
import org.junit.Test;

import java.util.LinkedList;

public class StackLinkedList_one<Item> extends LinkedList<Item> {
    public void push(Item x) {
        add(x);
    }

    @Test
    public void testStackLinkedList_one() {
        StackLinkedList_one<Integer> test = new StackLinkedList_one<>();
        for (int i = 0; i < 100; i++) {
            test.push(i);
        }
    }
}
```

This solution simply uses extension to borrow the `add` method from the well-established `LinkedList` class. Please note that, this class also borrows the member variables of `LinkedList`.



Method two and its test:

```Java
import org.junit.Test;

import java.util.LinkedList;

public class StackLinkedList_two<Item> {
    private LinkedList<Item> items = new LinkedList<>();
    public void push(Item x){
        items.add(x);
    }

    @Test
    public void testStackLinkedList_two() {
        StackLinkedList_two<Integer> test = new StackLinkedList_two<>();
        for (int i = 0; i < 100; i++) {
            test.push(i);
        }
    }
}
```

This solution uses ``Delegation``(替代), it creates a `LinkedList`, `items`, as its member variables and uses the `add` method of `LinkedList` directly. 



Method three and its test:

```Java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StackLinkedList_three<Item> {
    private List<Item> L;
    public StackLinkedList_three(List<Item> worker) {
        L = worker;
    }

    public void push(Item x) {
        L.add(x);
    }

    public void clean(){
        L.clear();
    }

    public void removeElement(Item o){
        L.remove(o);
    }

    public static void main(String[] args) {
        List<Integer> stuff = new ArrayList<>();
        StackLinkedList_three<Integer> test = new StackLinkedList_three(stuff);
        for (int i = 0; i< 5; i++){
            test.push(i);
        }

        List<Integer> stuff_2 = new LinkedList<>();
        StackLinkedList_three<Integer> test_2 = new StackLinkedList_three(stuff_2);
        for (int i = 0; i< 5; i++){
            test_2.push(i);
        }
        test_2.removeElement(3);
        test_2.clean();
    }
}
```

This approach is pretty much like the previous one. But you can use `ArrayList`, `LinkedList`, or any other `List` as the udnerlying data structure. Please note that, in the debugging mode, you will see that `test` and `test_2` stores the integers with the same address, but they do not behave like classes or arrays. The last two statments do nothign to the `test` variable.



> **Delegation vs Extension:** Right now it may seem that Delegation and Extension are pretty much interchangeable; however, there are some important differences that must be remembered when using them.
>
> Extension tends to be used when you know what is going on in the parent class. In other words, you know how the methods are implemented. Additionally, with extension, you are basically saying that the class you are extending from acts similarly to the one that is doing the extending. On the other hand, Delegation is when you do not want to consider your current class to be a version of the class that you are pulling the method from.



> **Views**: Views are an alternative representation of an existed object. Views essentially limit the access that the user has to the underlying object. However, changes done through the views will affect the actual object.



The following code demonstrate how to create a sublist from an original list. The statment, `List<String> SL = L.subList(1, 4);`, does the stuff. Please note that the last statment, `SL.set(0, "jug")` changes `L` simultaneously as it following the pass by reference rules.

```Java
import java.util.ArrayList;
import java.util.List;

public class SubList {

    public static void main(String[] args) {
        /** Create an ArrayList. */
        List<String> L = new ArrayList<>();
        /** Add some items. */
        L.add("at");
        L.add("ax");
        L.add("ban");
        L.add("bat");
        L.add("cat");
        /** subList me up fam. */
        List<String> SL = L.subList(1, 4);
        /** Mutate that thing. */
        SL.set(0, "jug");
    }
}
```

> The most intuitive way is to create a method that takes in a list object and the indices which should be reversed. However, this can be a bit painful because we add some extraneous logic.

This sublist method, however, is very useful because we can use it to reverse just a part of a list in a very generic method. Just take the desired sublist, reverse it, and return the whole. The following figures shows the logic behind:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/SubList1.png?raw=true)

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/SubList2.png?raw=true)

> However, it lends itself to an issue. You are claiming that you can give a list object that when manipulated, can affect the original list object- that’s a bit weird. Just thinking “How do you return an actual List but still have it affect another List?” is a bit confusing. Well the answer is **access methods**.



The following parts in the [Hug61B](https://joshhug.gitbooks.io/hug61b/content/chap8/chap81.html_) book is rather confusing. In summary, he wants to explain how to return a new `List` rather than return by reference. Anyway, please consult this [link](https://joshhug.gitbooks.io/hug61b/content/chap8/chap81.html) to access the original materials if you wish.



### 2. Asymptotics I

We have now considered the programming cost of the programmer:

> 1. How long does it take for you to develop your programs?
> 2. How easy is it to read or modify your code?
> 3. How maintainable is your code? (very important — much of the cost comes from maintenance and scalability, not development!)



We now want to consider more about the execution cost:

> 1. **Time complexity**: How much time does it take for your program to execute?
> 2. **Space complexity**: How much memory does your program require?



#### A. Example of Algorithm Cost

A simple example is to **determine if  a sorted array constains any duplicates elements**. The silly way is to compare every possible pairs. The better way, however, just compares the negibhours. Here comes a problem--How do we compare how much the silly method more work? How do we actually quantify or determine how efficient a program is?



#### B. Runtime Characterisation

Now, we want to characterise the efficiency of the two methods in a mathematical way.

> Things to keep in mind about our characterizations:
>
> - They should be simple and mathematically rigorous.
> - They should also clearly demonstrate the superiority of dup2 over dup1.

```Java
//Silly Duplicate: compare everything
public static boolean dup1(int[] A) {  
  for (int i = 0; i < A.length; i += 1) {
    for (int j = i + 1; j < A.length; j += 1) {
      if (A[i] == A[j]) {
         return true;
      }
    }
  }
  return false;
}

//Better Duplicate: compare only neighbors
public static boolean dup2(int[] A) {
  for (int i = 0; i < A.length - 1; i += 1) {
    if (A[i] == A[i + 1]) { 
      return true; 
    }
  }
  return false;
}
```

> **Technique 1**: Measure execution time in seconds using a client program (i.e. actually seeing how quick our program runs in physical seconds)
>
> *Procedure*
>
> - Use a physical stopwatch
> - Or, Unix has a built in `time` command that measures execution time.
> - Or, Princeton Standard library has a `stopwatch` class
>
> *Observations*
>
> - As our input size increases, we can see that `dup1` takes a longer time to complete, whereas `dup2`completes at relatively around the same rate.
>
> *Pros vs. Cons*
>
> - Pros: Very easy to measure (just run a stopwatch). Meaning is clear (look at the actual length of time it takes to complete).
> - Cons: May take a lot of time to test. Results may also differ based on what kind of machine, compiler, input data, etc. you’re running your program with.
>
> So how does this method match our goals? It's simple, so that's good, but not mathematically rigorous. Moreover, the differences based on machine, compiler, input, etc. mean that the results may not clearly demonstrate the relationship between dup1 and dup2.
>
> How about another method?

Technique 2:

1. Technique 2A: Count possible operations for an array of size N = 10,000.

   ```java
   for (int i = 0; i < A.length; i += 1) {
     for (int j = i+1; j < A.length; j += 1) {
       if (A[i] == A[j]) {
          return true;
       }
     }
   }
   return false;
   ```

> *Procedure*
>
> - Look at your code and the various operations that it uses (i.e. assignments, incrementations, etc.)
> - Count the number of times each operation is performed.
>
> *Observations*
>
> - Some counts get tricky to count.
> - How did we get some of these numbers? It can be complicated and tedious.
>
> *Pros vs. Cons*
>
> - Pros: Machine independent (for the most part). Input dependence captured in model.
> - Cons: Tedious to compute. Array size was arbitrary (we counted for N = 10,000 — but what about for larger N? For a smaller N? How many counts for those?). Number of operations doesn’t tell you the actual time it takes for a certain operation to execute (some might be quicker to execute than others).
>
> So maybe this one has solved some of our cons from the timing simulation above, but it has problems of its own.

2. Technique 2B: Count possible operations in terms of input array size N (symbolic counts).

> *Pros vs. Cons*
>
> - Pros: Still machine independent (just counting the number of operations still). Input dependence still captured in model. But now, it tells us how our algorithm scales as a function of the size of our input.
> - Cons: Even more tedious to compute. Still doesn’t tell us the actual time it takes!

```java
for (int i = 0; i < A.length - 1; i += 1){
  if (A[i] == A[i + 1]) { 
    return true; 
  }
}
return false;
```

|    **operation**    | **symbolic count** | **count, N=10000** |
| :-----------------: | :----------------: | :----------------: |
|      **i = 0**      |         1          |         1          |
|  **less than (<)**  |       1 to N       |     1 to 10000     |
| **increment (+=1)** |     1 to N - 1     |     1 to 9999      |
|   **equals (==)**   |     1 to N - 1     |     1 to 9999      |
| **array accesses**  |    2 to 2N - 2     |     2 to 19998     |



Now, considering the following two filled out tables, which algorithm seems better to you and why?

 `dup1`

|    **operation**    |  **symbolic count**  | **count, N=10000** |
| :-----------------: | :------------------: | :----------------: |
|      **i = 0**      |          1           |         1          |
|    **j = i + 1**    |        1 to N        |     1 to 10000     |
|  **less than (<)**  | 2 to $(N^2+3N+2N)/2$ |  2 to 50,015,001   |
| **increment (+=1)** |   0 to $(N^2+N)/2$   |  0 to 50,005,000   |
|   **equals (==)**   |  1 to *$(N^2−N)/2$*  |  1 to 49,995,000   |
| **array accesses**  |    2 to *$N^2−N$*    |  2 to 99,990,000   |

`dup2`

|    **operation**    | **symbolic count** | **count, N=10000** |
| :-----------------: | :----------------: | :----------------: |
|      **i = 0**      |         1          |         1          |
|  **less than (<)**  |      0 to $N$      |     0 to 10000     |
| **increment (+=1)** |    0 to $N - 1$    |     0 to 9999      |
|   **equals (==)**   |    1 to $N - 1$    |     1 to 9999      |
| **array accesses**  |   2 to $2N - 2$    |     2 to 19998     |

`dup2` is better! But why?

> - An answer: It takes fewer operations to accomplish the same goal.
>
> - Better answer: Algorithm scales better in the worst case $(N^2+3N+2)/2$ vs. *$N$*
>
> - Even better answer: Parabolas *$N^2$* grow faster than lines *$N$*
>
>   Note: This is the same idea as our “better” answer, but it provides a more general geometric intuition.



#### C. Asymptotic Behaviour

> In most cases, we only care about what happens for very large N (asymptotic behavior). We want to consider what types of algorithms would best handle big amounts of data, such as in the examples listed below:
>
> - Simulation of billions of interacting particles
> - Social network with billions of users
> - Encoding billions of bytes of video data
>
> Algorithms that scale well (i.e. look like lines) have better asymptotic runtime behavior than algorithms that scale relatively poorly (i.e. looks like parabolas).



> What about constants? If we had functions that took $2N^2$ operations vs. *$500N$* operations, wouldn’t the one that only takes $2N^2$ operations be faster in certain cases, like if $N$ = 4 (32 vs. 20,000 operations).
>
> * Yes! For some small $N$, $2N^2$ may be smaller than $500N$. 
>
> * However, as $N$ grows, the $2N^2$ will dominate. 
>
> * i.e. $N$ = 10,000 → 2*100000000 vs. 5 * 1000000



>  The important thing is the “shape” of our graph (i.e. parabolic vs. linear) Let us (for now) informally refer to the shape of our graph as the “orders of growth”.



#### D. Returning to Duplicate Finding

> Returning to our original goals of characterizing the runtimes of `dup1` vs. `dup2`
>
> - They should be **simple** and **mathematically rigorous.**
> - They should also clearly demonstrate the **superiority of dup2 over dup1.**
>
> We’ve accomplished the second task! We were able to clearly see that `dup2` performed better than `dup1`. However, we didn’t do it in a very simple or mathematically rigorous way.
>
> We did however talk about how `dup1` performed “like” a parabola, and `dup2` performed “like” a line. Now, we’ll be more formal about what we meant by those statements by applying the four simplifications.



#### E. Intuitive Simplification 1: Consider Only the Worst Case

> When comparing algorithms, we often only care about the worst case (though we'll see some exceptions later in this course).



> Consider the counts for the algorithm below. What do you expect will be the order of growth of the runtime for the algorithm?

- $N$ [linear]
- $N^2$ [quadratic]
- $N^3$ [cubic]
- $N^6$ [sextic]

|    **operation**     |  **count**  |
| :------------------: | :---------: |
|  **less than (<)**   | $100N^2+3N$ |
| **greater than (>)** |   $N^3+1$   |
|     **and (&&)**     |  $5, 000$   |

The answer is cubic ($N^3$), the total runtime can be written as 
$$
\alpha (100N^2+3N) + \beta (N^3 + 1) + \gamma*5, 000,
$$
which is dominated by the $N^3$ term when $N\to\infin$ .



#### F. Intuitive Simplification 2: Restrict Attention to One Operation

> Pick some representative operation to act as a proxy for overall runtime.
>
> - Good choice: `increment`, or **less than** or **equals** or **array accesses**
> - Bad choice: **assignment of** `j = i + 1`, or `i = 0`
>
> The operation we choose can be called the “**cost model**.”



#### G. Intuitive Simplification 3: Eliminate Low Order Terms

> Ignore lower order terms!
>
> **Sanity check**: Why does this make sense? (Related to the checkpoint above!)



#### H. Intuitive Simplification 4: Eliminate Multiplicative Constants

> Ignore multiplicative constants.
>
> - Why? No real meaning!
> - Remember that by choosing a single representative operation, we already “threw away” some information 
> - Some operations had counts of $3N^2$, $N^2/2$, etc. In general, they are all in the family/shape of $N^2$!
>
> This step is also related to the example earlier of $500N$ vs. $2N^2$.



#### I. Simplification Summary

> - Only consider the worst case.
> - Pick a representative operation (aka: cost model)
> - Ignore lower order terms
> - Ignore multiplicative constants.



|    **operation**    |   **count**   |
| :-----------------: | :-----------: |
|      **i = 0**      |       1       |
|  **less than (<)**  |   0 to $N$    |
| **increment (+=1)** | 0 to $N - 1$  |
|   **equals (==)**   | 1 to $N - 1$  |
| **array accesses**  | 2 to $2N - 2$ |

|   **operation**    | **worst case orders of growth** |
| :----------------: | :-----------------------------: |
| **Array accesses** |               $N$               |

**Sample Answer:** `Array accesses` | $N$, or `less than/increment/equals` |$N$.



#### J. Simpified Analysis Process

> Rather than building the entire table, we can instead:
>
> - Choose our cost model (representative operation we want to count).
> - Figure out the order of growth for the count of our representative operation by either:
>   - Making an exact count, and discarding unnecessary pieces
>   - Or, using intuition/inspection to determine orders of growth (comes with practice!)



*** <font color = red size = 5>Fore more details about big $\Theta$  or big $O$ notation, please consult the [Hug61B](https://joshhug.gitbooks.io/hug61b/content/chap8/chap82.html) book. The author has already been familiar with this part, so this section is skipped intendedly.</font>***



#### H. *Summary*

>Given a piece of code, we can express its runtime as a function R(N)
>
>- N is some **property** of the input of the function 
>- i.e. oftentimes, N represents the **size** of the input
>
>Rather than finding R(N) exactly, we instead usually only care about the **order of growth** of R(N).
>
>One approach (not universal):
>
>- Choose a representative operation
>- Let C(N) = count of how many times that operation occurs, as a function of N.
>- Determine order of growth *f*(*N*) for *C*(*N*), i.e. C*(*N*)∈Θ(*f*(*N))
>- Often (but not always) we consider the worst case count.
>- If operation takes constant time, then *R*(*N*)∈Θ(*f*(*N*))



### 3. Asymptotics II

#### A. A First Example with Loops 

Let us start with a simple nested for loop:

```java
int N = A.length;
for (int i = 0; i < N; i += 1)
   for (int j = i + 1; j < N; j += 1)
      if (A[i] == A[j])
         return true;
return false;
```

It is lear that the number of comparision is $N(N - 1)/2$, which is a family of $\Theta (N^2)$. We have two methods to reach this answer:

**Method 1**: $C = 1 + 2 + 3 + 4 + ... + (N - 1) = N(N - 1)/2$;

**Method 2**: A more intuitive geometirc method, from which we can easily see the area of the triangle is $N(N - 1)$.![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Geometric%20Method.png?raw=true)



#### B. A Linear Loop Example

Now, we want to determine the complexity of the following nested loop:

```java
public static void printParty(int N) {
   for (int i = 1; i <= N; i = i * 2) {
      for (int j = 0; j < i; j += 1) {
         System.out.println("hello");   
         int ZUG = 1 + 1;
      }
   }
}
```

Please note that we have to count the number of execution directly, without any simple method. So we write the table below to investigate its complexity behaviour as a function of $N$.![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Linear%20Loop.png?raw=true)

So we can see that, despite there are two loops in this piece of code, the number of exectuion of the `print` statement is proportional to $N$. The exact relation is as follows:
$$
C(N) = 1 + 2 + 4 + ... + N = 2N - 1.
$$
This is slightly counter-intuitive, as nested for loops ''should'' have a complexity of order $\Theta (N^2)$. With the figure below, we can tell the story in a more intuitive way: ![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/Linear%20Loop%20Figure.png?raw=true)

From this examle, we can tell that there is no magic shortcut to simplify the processes of complexity analysis. The techiniques may involve **finding exact sums, writing out examples and drawing pictures**. But anyway, there is no magic. We need to remember clearly that 
$$
1 + 2 + 3 + ... + N = N(N-1)=\theta (N^2),
$$

$$
1 + 2 + 4 + ... + N = 2N-1 = \theta (N).
$$


#### C. The Complexity of Recursion Algorithms

Let us consider the function below:

```java
public static int f3(int n) {
   if (n <= 1) 
      return 1;
   return f3(n-1) + f3(n-1);
}
```

 We should investigate the realationship between the number of function `f3` beeing executed and the number of $N$. The intuitive way is to draw a tree diagram as follows: ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Intuitive%20Tree.png?raw=true)

Clearly, the number of execution goes up exponentially as $2^N$. And its time complexity is therefore $\Theta (2^N)$. There is an alternative way to calculate the number of exectuion using the recursive relationship as follows:
$$
C(N) = 2C(N-1)+1 = 4C(N-2)+1+2=...= 2^{N-1}C(1)+1+2+4+...+2^{N-2}= 2^{N-1} + 2^{N-1}-1 = 2^N-1.
$$
This proof is more robust but not necessary in the CS61B course. 



#### D. Binary Search

Please consult this [link](https://docs.google.com/presentation/d/1P4HKmsO3Aaugv7_U16jJN0UbfTEJi1uZUdi_WbIIGe0/edit#slide=id.g2c365a1f09_0_467) for the demonstration of binary search. The count results are shown in the table below.

| N         | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   |
| :-------- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Count** | 1    | 2    | 2    | 3    | 3    | 3    | 3    | 4    | 4    | 4    | 4    | 4    | 4    |

 The exact execution time is $C(N)=⌊log_2N⌋ + 1$, where the strange bracket means rounding down the number to the nearest integer. Thus, the time complexity of binary search is  $\Theta (log_2N)$. An important propertiy of the binary search method is that it runs superfast. To show this concretely: 

| N                   | log2Nlog2*N* | Typical runtime (nanoseconds) |
| :------------------ | :----------- | :---------------------------- |
| 100                 | 6.6          | 1                             |
| 100,000             | 16.6         | 2.5                           |
| 100,000,000         | 26.5         | 4                             |
| 100,000,000,000     | 36.5         | 5.5                           |
| 100,000,000,000,000 | 46.5         | 7                             |



#### E. Merge Sort

In selection sort, which we have introrudced in [section III.1](# 1. Selection Sort--A Wifndow to the Power of Testing), its time complexity is of $\Theta (N^2)$. This complexity is extremely terrible when $N$ gets larger and larger. But we have a variety of alternative sorting method, one of those method is the *merge sort* method. We now introduce a new concept called *arbitary units of time (AU)*. It is just one arbitary unit time, without specific physical definition. For example,  it can be one second or one millisecond, which makes no difference in terms of being a unit. 



Now let us suppose we have an array of random 64 integers and we want sort it. Since the complexity of selection sort is $\Theta (N^2)$, it takes a run time of $64^2 AU$, which is $4096AU$. How about two arrays with each has 32 intergers? The run time will be $2096AU$. And for four arrays each witfh 16 integers, we will have a run time of $1024AU$. It looks like if we divide the original problem to small pieces,  we can make the processes easier. Unfortunately, if we do so, we will have to take the time of merging different arrays into consideration. The merging time, however, is proportional to the length of the two arrays that we want to merge, i.e. its time complexity is $\Theta (N)$. Here comes an idea called *divide and conquer*. We divide the original array into a series of small pieces, sort those sub-arrays, merge them, then everything is done, as shown in the figures below 

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Merge%20Sort%20Diagram.png?raw=true)

 For the mergeing processes , we have linear run time, and each layer has the same number of operations, which is $N$, the length of the array. The number of merging layers, however, depends on the length of array in another way, which is $log_2N$. The total time complexity of merge sort is therefore $\Theta (Nlog_2N)$, which is far better than $\Theta (N^2)$. The follwing figure demonstrate how time complexity can have a significant difference when $n$ gets larger.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Time%20Complexity%20Example.png?raw=true)

The author's implementation of merge sort can be found in his [CS61B_Readings repository](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings) (the code is enclosed in the section 8 file). 



#### F. *Summary*

> - There are no magic shortcuts for analyzing code runtime.
> - In our course, it’s OK to do exact counting or intuitive analysis.
> - Know how to sum $1 + 2 + 3 + ... + N=\Theta (N^2)$ and $1 + 2 + 4 + ... + N=\Theta(N)$.
> - We won’t be writing mathematical proofs in this class.
> - Many runtime problems you’ll do in this class resemble one of the five problems from today. See textbook, study guide, and discussion for more practice.
> - This topic has one of the highest skill ceilings of all topics in the course. All the tools are here, but **practice** is your friend!
> - Different solutions to the same problem, e.g. sorting, may have different runtimes (with big enough differences for the runtime to go from impractical to practical!).
> - $N^2$ vs. $NlogN$ is an enormous difference.
> - Going from $NlogN$ to $N$ is nice, but not a radical change.



### 4. Omega and Amortised Analysis

#### A. Runtime Analysis Subtleties

Let us consider the runtime complexity of the two function below:

```java
public boolean dup3(int[] a) {
    int N = a.length;
    for (int i = 0; i < N; i += 1) {
        for (int j = 0; j < N; j += 1) {
            if (a[i] == a[j]) {
                return true;
            }
        }
    }
    return false;
}
```

```java
public boolean dup4(int[] a) {
    int N = a.length;
    for (int i = 0; i < N; i += 1) {
        for (int j = i + 1; j < N; j += 1) {
            if (a[i] == a[j]) {
                return true;
            }
        }
    }
    return false;
}
```

The first one is a litte bit tricky, since both `i` and `j` starts from 0, the complexity is independent of length of the inputt array. It is always $\Theta (1)$. The second one is, however, even more tricky, as it highly depends on the input. The exact answer is: in the best case, $R(N)\in\Theta(1)$, when the first and the second elements are equal; in the worst case, $R(N)\in\Theta(N^2)$, when the last and the second last elements are equal. **This characteristic highlights that *big theta* notation is sometimes not useful, when the runtime depends on not only the input size but also the contents of the input.** Therefore, we should find an alternative method to solve the issue.



#### B. *Big O* Abuse

> Consider the following statements:
>
> 1. The most expensive room in the hotel is $639 per night.
> 2. Every room in the hotel is less than or equal to $639 per night.

Which statement gives you more information about a hotel? The answer is the first one, as we can exactlly know that there is a room with $639 per night, whereas we cannot tell such information from the second statement cannot. This example reveals the difference between *big theta* and *big O* notation. 



In terms of *big O* notation, both of `dup3` and `dup4` have runtime a complexity of $O(N^2)$. But in realworld, the concept of *big O* notation has beee abused for long time. It almost always refers to the worst case fo an algorithm. 

> To summarize the usefulness of  *big O*:
>
> - It allows us to make simple statements without case qualifications, in cases where the runtime is different for different inputs.
> - Sometimes, for particularly tricky problems, we (the computer science community) don't know the exact runtime, so we may only state an upper bound.
> - It's a lot easier to write proofs for *big O* than *big theta*, like we saw in finding the runtime of mergesort in the previous chapter. This is beyond the scope of this course.



#### C. *Big Omega* Notation

For details of *Big omega* notation, please consult the [corresponding section](https://joshhug.gitbooks.io/hug61b/content/chap8/chap84.html) of the Hug61B book. Here we just list a table of examples of the three different notations.

|                 |                 Informal Meaning                 | Example Family | Example Family Members  |
| :-------------: | :----------------------------------------------: | :------------: | :---------------------: |
| $\Theta (f(n))$ |            Order of growth is $f(N)$             | $\Theta (N^2)$ | $N^2,\ 2N^2,\ N^2+N+12$ |
|   $O (f(n))$    | Order of growth is less than or equal to $f(N)$  |    $O(N^2)$    |    $N^2,\ logN,\ N$     |
| $\Omega (f(n))$ | Order of growth is larger than or equal to$f(N)$ | $\Omega (N^2)$ |   $N^2,\ N^3,\ 2^N $    |

 

#### D. Amortised Analysis (Intuitive Explanation)

Please consult the [corresponding section](https://joshhug.gitbooks.io/hug61b/content/chap8/chap84.html) of the Hug61B book for details. In summary, he starts with the Grigomeths's Urn, which introduce an example of the fact that in some conditions, exponential increases with an exponential gap may actually be better than constant growth. Then he analyses the ArrayList resizing problem with the same method of Grigomeths's Urn. Then he successfully proves that the resizing processses is actually of a linear time consuming. The *amortised analysis* for the Grigometh's Urn is shown below for reference. 

| Day (i)              | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   |
| :------------------- | :--- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| Actual cost $c_i$    | 1    | 2    | 0    | 4    | 0    | 0    | 0    | 0    | 8    | 0    | 0    | 0    | 0    | 0    |
| Amortized cost $a_i$ | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    | 3    |
| Change in potential  | 2    | 1    | 3    | -1   | 3    | 3    | 3    | 3    | -5   | 3    | 3    | 3    | 3    | 3    |
| Potential $\Phi_i$   | 2    | 3    | 6    | 5    | 8    | 11   | 14   | 17   | 12   | 15   | 18   | 21   | 24   | 27   |





## IX. Disjoint Set

### 1. Introduction to Disjoint Sets

> Two sets are named *disjoint sets* if they have no elements in common. A Disjoint-Sets (or Union-Find) data structure keeps track of a fixed number of elements partitioned into a number of *disjoint sets*. The data structure has two operations:
>
> 1. `connect(x, y)`: connect `x` and `y`. Also known as `union`
> 2. `isConnected(x, y)`: returns true if `x` and `y` are connected (i.e. part of the same set).



Therefore, the interface of an intuitive disjoint set should be as follows:

```java
public interface DisjointSets {
    /** connects two items P and Q */
    void connect(int p, int q);

    /** checks to see if two items are connected */
    boolean isConnected(int p, int q); 
}
```



### 2. Quick Find

#### A. List of Sets

> Intuitively, we might first consider representing Disjoint Sets as a list of sets, e.g, `List<Set<Integer>>`.
>
> For instance, if we have N=6 elements and nothing has been connected yet, our list of sets looks like: `[{0}, {1}, {2}, {3}, {4}, {5}, {6}]`. Looks good. However, consider how to complete an operation like `connect(5, 6)`. We'd have to iterate through up to `N` sets to find 5 and `N` sets to find 6. Our runtime becomes $O(N)$. And, if you were to try and implement this, the code would be quite complex.



#### B. Quick Find

> Let's consider another approach using a *single array of integers*. 
>
> - The **indices of the array** represent the elements of our set. 
> - The **value at an index** is the set number it belongs to. 
>
> For example, we represent `{0, 1, 3}, {2}, {4}` as:

|    ID    |  4   |  4   |  3   |  4   |  5   |
| :------: | :--: | :--: | :--: | :--: | :--: |
| Elements |  0   |  1   |  2   |  3   |  4   |



`connect(x, y)`

If we connet `2` and `4`., we set their ID as:

|    ID    |  4   |  4   |  3   |  4   |  3   |
| :------: | :--: | :--: | :--: | :--: | :--: |
| Elements |  0   |  1   |  2   |  3   |  4   |



`isConnect(x, y)`

To check if two elements are connected to each other, we simply check if ID of `x` and `ID` of `y` are equal, i.e. `id[x] == id[y]`, which results in a constant runtime complexity.



In summary, the *Quick Find* data structure improve the runtime complexity of the function `isConnected` to constant time. However, we still need to iterate the whole `int[] id` to perform `connect` operation.

| Implementation | Constructor | `connect` | `isConnected` |
| :------------: | :---------: | :-------: | :-----------: |
|  List of Sets  |    Θ(N)     |   O(N)    |     O(N)      |
|   Quick Find   |    Θ(N)     |   Θ(N)    |     Θ(1)      |

```java
public class QuickFindDS implements DisjointSets {

    private int[] id;

    /* Θ(N) */
    public QuickFindDS(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    /* need to iterate through the array => Θ(N) */
    public void connect(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    /* Θ(1) */
    public boolean isConnected(int p, int q){
        return (id[p] == id[q]);
    }
}
```



### 3. Quick Union

To further improve the efficiency of our data structure, we can now set the ID of an element to be its parent, which actually forms a data structure like trees. For example, a disjoint set made of `{{0, 1, 2, 4}, {3, 5}, {6}}` can be represented as:![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Quick%20Union%20Parent.png?raw=true)where `-1` indicates the root of a tree. Actually, we still represent the sets as an array. But a helper method is required to find the root of an element in order to implement the `isConnect` and the `connect` method. 

```java
    private int find(int p) {
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }
```



As the tree may be as long as the length of the sets, the `find` method has a runtime complexity of $O(N)$ in the worst case. The two key methods are shown below for reference

```Java
   @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j= find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
```

Since the `connect` and `isConnect` methods are largely based on the `find` method, they also have a runtime complexity of $O(N)$ as follows:

| Implementation | Constructor | `connect` | `isConnected` |
| -------------- | ----------- | --------- | ------------- |
| List of Sets   | Θ(N)        | O(N)      | O(N)          |
| Quick Find     | Θ(N)        | Θ(N)      | Θ(1)          |
| Quick Union    | Θ(N)        | O(N)      | O(N)          |

**It looks like that the *Quick Union* data structure does not make any improvement. But basically it provides an intuition of that if only we somehow avoid the tree to be too long, the `connect` and `isConnect` operation can be as easy as changing a single ID. The rest of the job is to achieve this goal, i.e. avoid any unnecessarily high tress.**



### 4. Weighted Quick Union (WQU)

When we try to connect two sets `T1` and `T2`:![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/WQU%201.png?raw=true)



There are two possible options as follows:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/WQU%202.png?raw=true)



In terms of avoiding unnecessarily tall tress, the second option is preferable. Intuitively, here comes an idea: **always connect the short tree to the tall tree.** However, this implementation can be too complicated. A similar but far easier option is to connect the smaller tree to the larger tree, where the size (number of elements) of a tree characterise its weight. The following figure indicates the huge advantage of weighted quick-union compared with quick union.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/WQU%20Advantage.png?raw=true)

It it possible to prove that the maximum depth of the second setting is $\log_2 N$, which means that it can significantly reduce the runtime complexity of the `find` function to $O(\log N)$. 



In summary, we finally achieve

|    Implementation    | Constructor | `connect` | `isConnected` |
| :------------------: | :---------: | :-------: | :-----------: |
|     List of Set      |    Θ(N)     |   O(N)    |     O(N)      |
|      Quick Find      |    Θ(N)     |   Θ(N)    |     Θ(1)      |
|     Quick Union      |    Θ(N)     |   O(N)    |     O(N)      |
| Weighted Quick Union |    Θ(N)     | O(log N)  |   O(log N)    |



### 5. Weighted Quick Union with Path Compression

> The clever insight is realizing that whenever we call `find(x)` we have to traverse the path from `x` to root. So, along the way we can connect all the items we visit to their root at no extra asymptotic cost.
>
> Connecting all the items along the way to the root will help make our tree shorter with each call to `find`.

Through this improvement, we can make sure that the runtime complexity is minimised to $O(\alpha(N))$, where $\alpha(N)$ is less than five for any possible input in the real-world. More details can be found in this [video](https://www.youtube.com/watch?v=DZKzDebT4gU).

|       Implementation       | `isConnected` | `connect` |
| :------------------------: | :-----------: | :-------: |
|         Quick Find         |     Θ(N)      |   Θ(1)    |
|        Quick Union         |     O(N)      |   O(N)    |
| Weighted Quick Union (WQU) |   O(log N)    | O(log N)  |
| WQU with Path Compression  |   O(α(N))*    | O(α(N))*  |



## X. Abstract Data Types (ADTs)

### 1. Introduction to ADTs

There are significant overlapping between this subsection and [Discussion 8.A](# A. Assorted ADTS). We will see more interesting implementations of ADTs in the next subsection.



### 2. Binary Search Trees (BSTs)

Please consult the pages 396-406 of the [algorithm book](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) (we will use *Algs* as its a.k.a in the rest of the note) for details. The full implement of such a tree can be found in the author's [*CS61B_Readings, section 11*](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/11.%20Balanced%20Trees) repository  (This code is modified from the [*Algs website*](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html)). The figure below shows the difference between runtime complexity of various data search methods.  

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/BSTs%20runtime.png?raw=true)

**Summary**

> Abstract data types (ADTs) are defined in terms of operations, not implementation.
>
> Several useful ADTs:
>
> - Disjoint Sets, Map, Set, List.
> - Java provides Map, Set, List interfaces, along with several implementations.
>
> We’ve seen two ways to implement a Set (or Map):
>
> - ArraySet: $\Theta (N)$ operations in the worst case.
> - BST: $\Theta (\log(N))$ operations if tree is balanced.
>
> BST Implementations:
>
> - Search and insert are straightforward (but insert is a little tricky).
> - Deletion is more challenging. Typical approach is “Hibbard deletion”.



## XI. Balanced Trees

### 1. Balanced Tree Operations

Please consult the [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 424-431 for details. The followings figures demonstrate the search and insert operation in different circumstances.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/B%20Tree%20Operations.png?raw=true)



### 2. Global Properties

All the operations above preserve the global properties that the tree is still balanced, i.e. all null links have the same path length to the roof. The following figure shows two examples:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/2%203%20Tree%20Example.png?raw=true)

**Summary**

> BSTs have best case height $\Theta (\log N)$, and worst case height $\Theta N)$.
>
> - Big O is not the same thing as worst case!
>
> B-Trees are a modification of the binary search tree that avoids $\Theta (N)$ worst case.
>
> - Nodes may contain between $1$ and $L$ items.
> - contains works almost exactly like a normal BST.
> - add works by adding items to existing leaf nodes.
>   - If nodes are too full, they split.
> - Resulting tree has perfect balance. Runtime for operations is $O (\log N)$.
> - Have not discussed deletion. See extra slides if you’re curious.
> - Have not discussed how splitting works if $L>3$ (see some other class).
> - B-trees are more complex, but they can efficiently handle ANY insertion order.



### 3. Basic Implementaton of Balanced Tree--Red-Black BSTs

Although the *B Trees* mentioned above has an excellent runtime performance, which maintain the balanced structure, the implementation of such a data structure is quite tricky and beyond the scope of this course. We now introduce *Red-Black BSTs (RBBSTs)*, which is a data structure that combines the straighfoward implementation of an ordinary BST and the efficiency of *B Trees*.



#### A. Definition and Equivalency of RBBSTs

A RB-BST have treee key features as follows:

* Red links lean left.

* No node has two red links connected to it.

* The tree has perfect black balance : every path from the root to a null link has the same number of black links.

  


From these properties, we can build the one-to-one correspondence between *B Trees* and RB-BSTs as shown below:

 ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/RBBST%20Correspondence.png?raw=true)



The highlight of this tree is the horizontal red links, which represent the joint nodes in *B Trees*, which enables the simple implementation fo a complicated data structure. Thus, the basic node of a RBBST should looks like:

```Java
		private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node
    {
        Key key; // key
        Value val; // associated data
        Node left, right; // subtrees
        int N; // # nodes in this subtree
        boolean color; // color of link from
        // parent to this node
        Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node x)
    {
        if (x == null) return false;
        return x.color == RED;
    }
```

In additon to the `key`, `value`, `left`, `right` and `N` member variables, an extra boolean variable named `color` is included to denote the colour of the node's link to its parent. We predefine `RED` to be `true` and `BLACK` to be `false` to better write our class.



#### B. Tree Rotation--the key of the Implementation of  RBBSTs I

From our intuition, the most important part of this program should be how to maintain the three features mentioned above. Among them, issues on how to keep red links left leaning are quite tricky. 



Here, we introduce the idea of always adding a new ndoe with a red link to its parent and then use some rotation magic to keep red links left leaning possible as shown in the figures below:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/RBBST%20Rotation.png?raw=true)



The corresponding codes are shown below:

```Java
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        h.N = size(h.left) + size(h.left) + 1;
        x.N = h.N;
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        x.N = h.N;
        return x;
    }
```



Throw these rotating methods, we can implement basic insertion into a single 3-node:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Insertion%20into%20A%20Single%203%20Node.png?raw=true)



#### C. Flipping Colours--the key of the Implementation of RBBSTs II

As shown in the figure above, we sometimes encounter the circumstances where two red links are connected to the same node, and we mysteriously used an operation called *colours flipped to black*. This is because, following the rules of *2-3 B Trees*, no four nodes are allowed. Therefore, we need to push the middle element to the upper layer and set the colour of the middle element to be red, so that it is in the same layer as other upper element(s).

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Flipping%20Colours.png?raw=true) 

The code of this operation is straightforward:

```Java
    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
```

All the operations of passing a red link up a RBBST are listed as follows, which leads to our final implmentation of the `put` function in a RBBST.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/RBBST%20Passing%20Summary.png?raw=true)

#### D. Basic Implementation of RBBST

We have now discussed all the preliminary knowledge fo a basic RBBST. The codes below indicates the outlook of a basic implementation of a RBBST.

```Java
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key; // key
        Value val; // associated data
        Node left, right; // subtrees
        int N; // # nodes in this subtree
        boolean color; // color of link from

        // parent to this node
        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        h.N = size(h.left) + size(h.left) + 1;
        x.N = h.N;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        x.N = h.N;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.color = BLACK; // Always make sure that the root is a black node.
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) // Do standard insert, with red link to parent.
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
}
```



The two figures below demonstrate two typical insertation examples, if you can fully understand the processes, you understand how a RBBST works.

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Typical%20Insertion%20Operations%20of%20RBBST.png?raw=true)



### 4. A Complete Implementation of A RBBST 

#### A. A Typical RBBST Class

The following codes demonstrate a typical implementation fo a RBBST. It is modified from the [*Algs Website*](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/RedBlackBST.java.html). It includes many features like `delete`, `deleteMin`, `deleteMax`, etc. To better understand the philosophy behind these method, please consult [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 441-443. 

```Java
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {
    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }


    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     *
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }


    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
        // assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/

    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        // assert (h != null) && isRed(h.left) &&  !isRed(h.right);  // for insertion only
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        // assert (h != null) && isRed(h.right) && !isRed(h.left);  // for insertion only
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else return max(x.right);
    }


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too small");
        else return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Return the key in the symbol table of a given {@code rank}.
     * This key has the property that there are {@code rank} keys in
     * the symbol table that are smaller. In other words, this key is the
     * ({@code rank}+1)st smallest key in the symbol table.
     *
     * @param rank the order statistic
     * @return the key in the symbol table of given {@code rank}
     * @throws IllegalArgumentException unless {@code rank} is between 0 and
     *                                  <em>n</em>–1
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else return x.key;
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in the symbol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in the symbol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }


    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23()) StdOut.println("Not a 2-3 tree");
        if (!isBalanced()) StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }


    /**
     * Unit tests the {@code RedBlackBST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println();
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}
```



#### B. *Summary* 

The performance of RBBSTs are as follows, please consult [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 444-447  for detailed discussions. 

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/RBBST%20Performance.png?raw=true)

We can see that the RBBST implementation of *2-3 tree search* provides all logarithm runtime complexity in both search and insertion operations. It also efficiently support ordered operations.



The following figure shows a typicalRBBSt built from random insertion:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Random%20RBBST.png?raw=true)

## XII. Hashing

### 1. Why We Want Hashing

The RBBST implementation of *2-3 B Trees* has provided us with a very efficient data structure of logarithm runtime complexity, however, there are still there drawbacks:

> 1. They require that items be comparable. How do you decide where a new item goes in a BST? You have to answer the question "are you smaller than or bigger than the root"? For some objects, this question may make no sense. 
>
> 2. hey give a complexity of $\Theta(\log N)$. Is this good? Absolutely. But maybe we can do better.



Now we only foucus on the second issue, which means we want to improve our runtime complexity to $\Theta(1)$. So we want a data structure called hashing.



### 2. A Naive Attempt

#### A. Integers

Since we want $\Theta (1)$ runtime complexity, we can create the following class that may stores intergers in a quick way:

```java
public class DataIndexedIntegerSet {
    private boolean[] present;

    public DataIndexedIntegerSet() {
        present = new boolean[2000000000];
    }

    public void add(int x) {
        present[i] = true;
    }

    public boolean contains(int x) {
        return present[i];
    }
}
```

Here we create a `boolean` array of size 2,000,000,000, and represent the existence of an interger as `true` or `false` in the corresponding place. The `add` and `contains` method is all in constant runtime complexity, look like a sound approch! However, the initialisation of a `boolean` array of size two billion may use a huge amount of memory, whereas it can only handle integers that are small than two billion. Moreover, what if the user want to insert `String` instead of simple integers. We will move forward to solve these issues.



#### B. Strings

How about stirngs? The previous class can do nothing if we want to insert an element called *dog* or *cat*. It is true that we can use the first letter to distingush some of them, however, the limitation is obvious--How do we represent *dog* and *drum* in a different way? Here we encounter a concept of **collision**, which we do not have any knowledge about it. 



Therefore, we now introduce the a system just like decimal or binary two represent strings as follows:
$$
cat = 3\times 26^2+ 1\times 26^1 + 20\times26^0 = 2074,
$$
where we represent the alpabet with $1-26$. This repsentation gives a unique integer corresponding to a specific string. If we want to add more characters in differenet language or even some emoji, etc., we can change the base $26$ to the total number of different characters. 



The base $N$, where $N$ is the total number of unique characters, provides a rigorous mathematical system to convert system to integers.



The implmentaion and its test (`ab ` can be represented as 28) is shown as follows:

```Java
public class DataIndexedEnglishWordSet {
    private boolean[] present;

    public DataIndexedEnglishWordSet() {
        present = new boolean[2000000000];
    }

    public void add(String s) {
        present[englishToInt(s)] = true;
    }

    public boolean contains(String s) {
        return present[englishToInt(s)];
    }

    public static int letterNum(String s, int i) {
        /** Converts ith character of String to a letter number.
         * e.g. 'a' -> 1, 'b' -> 2, 'z' -> 26 */
        int ithChar = s.charAt(i);
        if ((ithChar < 'a') || (ithChar > 'z')) {
            throw new IllegalArgumentException();
        }
        return ithChar - 'a' + 1;
    }

    public static int englishToInt(String s) {
        int intRep = 0;
        for (int i = 0; i < s.length(); i += 1) {
            intRep = intRep * 26;
            intRep += letterNum(s, i);
        }

        return intRep;
    }

    public static void main(String[] args) {
        DataIndexedEnglishWordSet test = new DataIndexedEnglishWordSet();
        test.add("ab");
        System.out.println(DataIndexedEnglishWordSet.englishToInt("ab"));
    }
}
```



**Where are we?**

> Recall, we started with wanting to
>
> (a) Be better than $\Theta(\log N)$ We've now done this for integers and for single english words.
>
> (b) Allow for non-comparable items. We haven't touched this yet, although we are getting there. So far, we've only learnt how to add integers and English words, both of which *are* comparable, **but**, have we ever **used** the fact that they are comparable? I.e., have we ever tried to compare them (like we did in BSTs)? No. So we're getting there, but we haven't actually inserted anything non-comparable yet.
>
> (c) We have data structures that insert integers and English words. Let's make a quick visit to inserting arbitrary `String` objects, with spaces and all that. And maybe even insert other languages and emojis!
>
> (d) Further recall that our approach is still very wasteful of memory. We haven't solved that issue yet!



#### C. Inserting Strings beyond the Scope of English Words

Using a character format called **ASCII**, we can represent $126$ unique characters. The base therefore becomes $126$. This sounds still reasonable, however, if we are intersted in Chinese representation, there can be as many as $40959$ Chinese characters, which means we need an array of szie larger than **39 trillion** to store a single 3 character Chinese word, this is terrible for memory usage. Even in the case where memory usage is out of interest, the Java language has its largest integers, $4,294,967,296$, which means the `String` *omens* in a $126$-based system will beyond this limitation. So we have to find an alternative way to deal with these problems.



The truth is, we **must** handle with collisions. The *omens* returns $-1,867,853,901$ in the Java language system. Moreover,  `melt banana` and `subterresetrial anticosmetic` have to same integer representation according to our **ASCII** based system.



Please note that overflow is general very **bad**, as it can avoid the program from doing the correct things. But here, we somehow can find an approach to deal with the collison problem, which makes overflow not that bad.



So what is the way out? The answer is **hash codes**. For example, the hash code of `melt banana` is $839,099,497$. The fact is, in Java language:

> - Every Object in Java has a default `.hashcode()` method, which we can use. Java computes this by figuring out where the `Object` sits in memory (every section of the memory in your computer has an address!), and uses that memory's address to do something similar to what we did with `String`s. This methods gives a *unique* hashcode for every single Java object.
> - Sometimes, we write our own `hashcode` method. For example, given a `Dog`, we may use a combination of its `name`, `age` and `breed` to generate a `hashcode`.



Its properties include the following:

> 1. It must be an Integer
> 2. If we run `.hashCode()` on an object twice, it should return the **same** number
> 3. Two objects that are considered `.equal()` must have the same hash code.
>
> Not all hash codes are created equal, however. If you want your hash code to be considered a **good** hash code, it should:
>
> 1. Distribute items evenly

In the [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) book, there is one more requirement: *The hash code should be efficient to compute*.



Now, we have begun to include arbitary objects into consideration. The issues remain unsolved are that we still have not considered how to use less space and handle collisions.



### 3. Various Ways to Handle Collisions

#### A. Separate Chaining

The basic idea of separate chaining is to create a data structure as shown below:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Separate%20Chaining%20Data%20Structure.png?raw=true)

We acknowledge the fact that there are collisions in the data structure, but we create independent `SequentialSearchSt` objects in each hash value to store key and the corresponding value. Once we make sure that all the key are evenly spread to the hash values, the length of each sublist should be approximately of $N/M$, where $N$ is the number of key-value pairs and $N$ is the number of hash table boxes, i.e number of sublists. 



This soulds like we ultimately achieve a goal of runtime complexity of $\Theta (N/M)$, but if we set a constant threshold of $N/M$, i.e. expand the table size where appropriate, then we can reach $\Theta (1)$ complexity. An implementation of separate chaining is shown below (modified from the [*algs website*](https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SeparateChainingHashST.java.html)):

```Java
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.*;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables

    /**
     * Initializes an empty symbol table.
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     * @param m the initial number of chains
     */
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    // hash function for keys - returns value between 0 and m-1
    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // hash function for keys - returns value between 0 and m-1 (assumes m is a power of 2)
    // (from Java 7 implementation, protects against poor quality hashCode() implementations)
    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with {@code key} in the symbol table;
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10 * m) resize(2 * m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }


    /**
     * Unit tests the {@code SeparateChainingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
```

Please note that the behaviour of `SequentialSearchST` data type is very much like linked list. 



#### B. Linear Probing (Optional)

For details about linear probing, please consult [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 469-475. It is also efficient but less popular, so we will not discuss this approach in details. The following figure shows the data structure of linear probing:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Linear%20Probing%20Data%20Structure.png?raw=true)



An implementation is also shown as follows:

```Java
public class LinearProbingHashST<Key, Value> {
    private int N; // number of key-value pairs in the table
    private int M = 16; // size of linear-probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values

    public LinearProbingHashST(int M) {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M); // double M (see text)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
}
```



### 3. *Summary*

**For summative information about hashing, please consult  [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 478-479.**



## XIII. Heaps and Priority Queues

### 1. Definition of Priority Queues

A priority queue is where you can store elements with possible duplications, but you can only interact with the smallest elements.  What we call interaction here is very much like interactions in the data structure, queues and stacks, but we do not have definition of the terms, *fisrt* or *last*. We only care about the smallest (or sometimes largest) elements.



A typical interface of a priority queue is like  

```java
/** (Min) Priority Queue: Allowing tracking and removal of 
  * the smallest item in a priority queue. */
public interface MinPQ<Item> {
    /** Adds the item to the priority queue. */
    public void add(Item x);
    /** Returns the smallest item in the priority queue. */
    public Item getSmallest();
    /** Removes the smallest item from the priority queue. */
    public Item removeSmallest();
    /** Returns the size of the priority queue. */
    public int size();
}
```



### 2. Why We Want Heaps?

We can, of course, use the following three data structures to implement a priority queue, with time complexity:

**Ordered Array**

- `add`: $\Theta (N)$
- `getSmallest`: $\Theta (1)$
- `removeSmallest`: $\Theta (N)$

**Bushy BST**

- `add`: $\Theta (\log N)$
- `getSmallest`: $\Theta (\log N)$
- `removeSmallest`: $\Theta (\log N)$

**HashTable**

- `add`: $\Theta (1)$
- `getSmallest`: $\Theta (N)$
- `removeSmallest`: $\Theta (N)$

We can see that, only the *bushy BST* data structure performs well, however, it cannot deal with any duplication of elements. Therefore, we introduction the a new data sturcture called *heap*.



### 3. Definition of Heaps and the Implementation

> We will define our binary min-heap as being **complete** and obeying **min-heap** property:
>
> - Min-heap: Every node is less than or equal to both of its children
> - Complete: Missing items only at the bottom level (if any), all nodes are as far left as possible.



Some eamples are shown as follows:

![image](https://raw.githubusercontent.com/XChen1998/Figure_Library/3b2a6a9874b8f900f1504e9a39f208c57a797ec4/Work/Courses/CS61B_2018_Spring_UCB/Heap%20Examples.png)



The green trees are complete heaps. There are many possible implementation of heaps in Java, but the most common and easy approach is to use an array of `Key`.

```Java
public class TreeC<Key> {
  Key[] keys;
  ...
}
```

By defining the first item to be located in `keys[1]`, we have some invariants of 

* `leftChild(k)` = $k*2$;

* `rightChild(k)` = $k*2+1$;

* `parent(k)` =$k/2$.

The detailed implementation can be found in [lab 10](# 10. Priority Queues). This data structure allows duplicated elements. It also has a sound runtime complexity compared with other data structures.

|     Methods      | Ordered Array |     Bushy BST     |  Hash Table  |       Heap        |
| :--------------: | :-----------: | :---------------: | :----------: | :---------------: |
|      `add`       | $\Theta (N)$  | $\Theta (\log N)$ | $\Theta (1)$ | $\Theta (\log N)$ |
|  `getSmallest`   | $\Theta (1)$  | $\Theta (\log N)$ | $\Theta (N)$ |   $\Theta (1)$    |
| `removeSmallest` | $\Theta (N)$  | $\Theta (\log N)$ | $\Theta (N)$ | $\Theta (\log N)$ |





## *Extra*: Homeworks

*This section includes most of the homework questions. Homework 0 is skipped.*



### 1. Packages, Interfaces, Generics, Exceptions, Iteration

The original instruction can be found in this [link](https://sp18.datastructur.es/materials/hw/hw1/hw1). This homework demonstrates how interfaces and abstract classses works. In the interface file, we declare the following:

```Java
package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    int capacity();     // return size of the buffer

    int fillCount();    // return number of items currently in the buffer

    void enqueue(T x);  // add item x to the end

    T dequeue();        // delete and return item from the front

    T peek();           // return (but do not delete) item from the front

    default boolean isEmpty() { // is the buffer empty (fillCount equals zero)?
        return fillCount() == 0;
    }

    default boolean isFull() { // is the buffer full (fillCount is same as capacity)?
        return fillCount() == capacity();
    }

    Iterator<T> iterator();
}
```

Please note that no methods with body can be declared in interface, but default method is always an altervative option. The `Iterator<T> iterator` method enables all implementations of `BoundedQueue` to be iterable, i.e. support enhanced `for` loop. 



In this homework, the next level class hierarchy is: 

```java
package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }
}
```

This abstract class implements the `BoundedQueue<T>` interface, but does not implement all the methods. It also declares two `protected` variables. Such implementation is very much like the class hierarchy in C++. 



Finally, we comes the `public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>` class that really implements all the methods and enables iterables. The following codes showns an exmaple of iteraotr implementation, i.e. a method that can return a iterator and a classs that redefine the `hasNext` and `next` methods

```Java
@Override
public Iterator<T> iterator() {
    return new BufferIterator();
}

private class BufferIterator implements Iterator<T> {

    private int wizPos;
    private int number;

    private BufferIterator() {
        wizPos = first;
        number = 0;
    }

    private void changeWiz() {
        if (wizPos == capacity - 1) {
            wizPos = 0;
        } else {
            wizPos++;
        }
    }

    @Override
    public boolean hasNext() {
        number++;
        return !(wizPos == last) || (isFull() && number <= capacity);
    }

    @Override
    public T next() {
        T x = rb[wizPos];
        changeWiz();
        return x;
    }
}
```



Here, the author wants to mention a very elegant algorithm to change the `first` and `last` variables in this class. 

```Java
last = (last + 1) % capacity;
```

```Java
last = (last + 1) % capacity;
```

**This is very much like the `realIndex2itemIndex(int x)` method in [Project 1a](# 1. DLList & Array (Project 1a)), but much easier. Bascially, this remainder method is of very important in many circular data structure.**



We now have a very well-defined `ArrayRingBuffer`, and can therefore implements the `GuitarString` file. Please note the constructor of this class must initialise the `buffer` variable as following:

```Java
    public GuitarString(double frequency) {
        int size = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(size);
        while (!buffer.isFull()) {
            buffer.enqueue(0.0);
        }
    }
```

We need to replace the `null` default value in `buffer.rb` with `0.0` to avoid `NullPointerException` error. 



### 2. Percolation

The introduction of this homework can be found in this [link](https://sp18.datastructur.es/materials/hw/hw2/hw2). In summary, it simulates the physical percolation problem with Monte Carlo method using the disjoint set data structure. The final code mainly consists of two parts:



Part 1: `Percolation` class:

```Java
package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation {
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF setIsFull;
    private boolean[][] grid;
    private int source;
    private int sink;
    private int numberOfOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Cannot set grid with side less than 1.");
        }
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false; // 0 is blocked, 1 is open, 2 is full
            }
        }
        set = new WeightedQuickUnionUF(N * N + 2);
        setIsFull = new WeightedQuickUnionUF(N * N + 1);
        source = N * N;
        sink = N * N + 1;
        numberOfOpenSites = 0;
        for (int i = 0; i < N; i++) {
            setIsFull.union(source, index2number(0, i));
            set.union(source, index2number(0, i));
            set.union(sink, index2number(N - 1, i));
        }
    }                // create N-by-N grid, with all sites initially blocked

    private boolean checkInBound(int row, int col) {
        return !(row < 0 || col < 0 || row >= grid.length || col >= grid.length);
    }

    private void checkValidity(int row, int col) {
        if (!checkInBound(row, col)) {
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
    }

    private int index2number(int row, int col) {
        return row * grid.length + col;
    }

    private int number2row(int number) {
        return number / grid.length;
    }

    private int number2col(int number) {
        return number % grid.length;
    }

    private void unionNeighbours(int row, int col) {

        ArrayList<Integer> blocks = new ArrayList<>();
        boolean neighbourIsFull = false;
        if (checkInBound(row - 1, col)) {
            if (isFull(row - 1, col)) {
                neighbourIsFull = true;
            }
            if (isOpen(row - 1, col)) {
                blocks.add(index2number(row - 1, col));
            }
        }

        if (checkInBound(row + 1, col)) {
            if (isFull(row + 1, col)) {
                neighbourIsFull = true;
            }
            if (isOpen(row + 1, col)) {
                blocks.add(index2number(row + 1, col));
            }
        }

        if (checkInBound(row, col - 1)) {
            if (isFull(row, col - 1)) {
                neighbourIsFull = true;
            }
            if (isOpen(row, col - 1)) {
                blocks.add(index2number(row, col - 1));
            }
        }

        if (checkInBound(row, col + 1)) {
            if (isFull(row, col + 1)) {
                neighbourIsFull = true;
            }
            if (isOpen(row, col + 1)) {
                blocks.add(index2number(row, col + 1));
            }
        }

        if (neighbourIsFull) {
            setIsFull.union(index2number(row, col), source);
            set.union(index2number(row, col), source);
            for (int n : blocks) {
                setIsFull.union(n, source);
                set.union(n, source);
            }

        } else {
            for (int n : blocks) {
                setIsFull.union(n, index2number(row, col));
                set.union(n, index2number(row, col));
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        checkValidity(row, col);
        numberOfOpenSites++;
        unionNeighbours(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkValidity(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkValidity(row, col);
        if (row == 0 || row == grid.length - 1) {
            return isOpen(row, col) && setIsFull.connected(source, index2number(row, col));
        } else {
            return setIsFull.connected(source, index2number(row, col));
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) {
            return numberOfOpenSites == 1;
        }
        return set.connected(source, sink);
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(10, 10000, new PercolationFactory());
    }
}
```

This part of program mainly constructs the physical scenario and enables all relevant operations, including `open`, `isOpen`, `isFull`, `percolates`. All the methods except the constructor, which runs in quadratic complexity, requires constant runtime. The virtual sites, `source` and `sink` are designed to reduce runtime complexity of the `percolation` method. A number of private helper methods are designed to implement the functions. Among them, the `index2number` method convert a 2D coordinate to a one dimensional number, so that the input argument for `set` and `setIsFull` can be easily calculated. The *backwash* problem is solved by using two disjoint sets, where one of them does not have a `sink` site.  



Part 2: `percolationStats` class:

```Java
package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int sideLength;
    private int times;
    private double[] threshold;
    private PercolationFactory p;
    private double meanStat;
    private double stddevStat;
    private double confidenceLowStat;
    private double confidenceHighStat;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument!");
        }
        sideLength = N;
        times = T;
        threshold = new double[T];
        p = pf;
        makeSimulations();
    }  // perform T independent experiments on an N-by-N grid

    private void makeSimulations() {
        for (int i = 0; i < times; i++) {
            threshold[i] = makeSingleSimulation(p.make(sideLength));
        }
        calculateStatistics();
    }

    private double makeSingleSimulation(Percolation pInstance) {
        while (!pInstance.percolates()) {
            pInstance.open(StdRandom.uniform(sideLength), StdRandom.uniform(sideLength));
        }
        return (double) pInstance.numberOfOpenSites() / (double) sideLength / (double) sideLength;
    }

    private void calculateStatistics() {
        meanStat = StdStats.mean(threshold);
        stddevStat = StdStats.stddev(threshold);
        confidenceLowStat = meanStat - 1.96 * stddevStat / Math.sqrt(times);
        confidenceHighStat = meanStat + 1.96 * stddevStat / Math.sqrt(times);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanStat;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddevStat;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLowStat;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHighStat;
    }
}
```

This part is relativiely straightforward. Please follow the instruction to implement the required method.



And a trivial `PercolationFactory` class:

```Java
package hw2;

public class PercolationFactory {
    public Percolation make(int N) {
        return new Percolation(N);
    }
}
```



### 3. Hashing

This is a light-weighted homework, just follow the guidance in the website will be fine. Please consult my [reporsitory](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Assignments/hw3) for complete implementation. There is only one thing need to be mentioned--to break the `hash` method in `ComplexOomage`, just find a way to let the `total` variable be powers of $256$. One possible solution to improve the `hash` method is to add a random number each time the `total` is multipled by $256$. 



This homework provides a good example of the performance and behaviours of `hash` method. It would be a very good resource to deepen your understanding in hashing.



### 4. Puzzle Solver

This homework is a good example of the *best-first search A\* algorithm*. Please consult my [repository](https://github.com/XChen1998/Work/tree/main/Courses/CS61B_2018_Spring_UCB/CS61B_Assignments/hw4) for full implementation of the homework. 



The solver class is shown below. 

```Java
package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private MinPQ<SearchNodes> pq;
    private Stack<WorldState> path;

    private class SearchNodes implements Comparable<SearchNodes> {
        private int numOfMoves;
        private SearchNodes previous;
        private WorldState ws;
        private int distanceToGoal;

        SearchNodes(int numOfMoves, SearchNodes previous, WorldState ws) {
            this.numOfMoves = numOfMoves;
            this.previous = previous;
            this.ws = ws;
            this.distanceToGoal = ws.estimatedDistanceToGoal();
        }

        public int moves() {
            return numOfMoves;
        }

        public SearchNodes previousNode() {
            return previous;
        }

        public WorldState curWorldState() {
            return ws;
        }

        @Override
        public int compareTo(SearchNodes o) {
            int thisValue = this.moves() + this.distanceToGoal;
            int oValue = o.moves() + o.distanceToGoal;
            return thisValue - oValue;
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        SearchNodes initialSN = new SearchNodes(0, null, initial);
        pq.insert(initialSN);
        path = new Stack<>();
        while (!pq.isEmpty()) {
            if (pq.min().curWorldState().isGoal()) {
                createPath(pq.min());
                break;
            }
            relax(pq.delMin());
        }

    }

    private void relax(SearchNodes sn) {
        if (sn.curWorldState().isGoal()) {
            return;
        } else {
            for (WorldState ws : sn.curWorldState().neighbors()) {
                SearchNodes cur = new SearchNodes(sn.moves() + 1, sn, ws);
                if (sn.previous == null || !ws.equals(sn.previous.curWorldState())) {
                    pq.insert(cur);
                }
            }
        }

    }

    private void createPath(SearchNodes sn) {
        while (sn != null) {
            path.push(sn.curWorldState());
            sn = sn.previousNode();
        }
    }

    public int moves() {
        return path.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return path;
    }
}

```

The key of this homework is the implementation of the sub-class, `SearchNode`, which records the history of a search attempt and any relevant parameters. It also redefine the `compareTo` method which implement the `Comparable<SearchNodes>` interface. The `Solver` class makes use of the `SearchNode` sub-class and the `relax` method. In summary, this homework is not straightforward but can be done smoothly if you fully understand the concept and philosophy of the *best-first search A\* algorithm*. More details can be found in the [guidance page](https://sp18.datastructur.es/materials/hw/hw4/hw4).





## *Extra*: Discussions

*This section includes most of the discussion questions. Discussion 1 is skipped.*



### 2. Scope, Pass-by-Value, Static

#### A. The Pokemon Problem (Pass by what?)

```Java
public class Pokemon {
    public String name;
    public int level;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        Pokemon p = new Pokemon("Pikachu", 17);
        int level = 100;
        change(p, level);
        System.out.println("Name: " + p.name + ", Level: " + p.level);
    }

    public static void change(Pokemon poke, int level) {
        poke.level = level;
        level = 50;
        poke = new Pokemon("Gengar", 1);
    }
}
```

First of all, the result would surprisingly to be `Name: Pikachu, Level: 100`. This code works as if the following two lines are skipped.

```Java
level = 50;
poke = new Pokemon("Gengar", 1);
```

The official explaination is that **Static methods are methods that do not require an instance to call it, and thus do not access any instance**, ***but it can access the variables of a instane using dot notation***. This [website](https://www.cs.toronto.edu/~reid/web/javaparams.html#:~:text=Pass%2Dby%2Dvalue%20means%20that,value%20of%20an%20actual%20parameter.) declare that we can use a Java method to change an instance, but we cannot make the instance pointer point to a new instance. This is why `poke = new Pokemon("Gengar", 1)` is void.



#### B. The Cat Problem (Static Variables)

```java
public class Cat {
    public String name;
    public static String noise;

    public Cat(String name, String noise) {
        this.name = name;
        this.noise = noise;
    }

    public void play() {
        System.out.println(noise + " I'm " + name + " the cat!");
    }

    public static void anger() {
        noise = noise.toUpperCase();
    }

    public static void calm() {
        noise = noise.toLowerCase();
    }

    public static void main(String[] args) {
        Cat a = new Cat("Cream", "Meow!"); // create Cream
        Cat b = new Cat("Tubbs", "Nyan!"); // create Tubbs
        a.play(); // Nyan! I'm Cream the cat!
        b.play(); // Nyan! I'm Tubbs the cat!
        Cat.anger(); // change Nyan to NYAN
        a.calm(); // change NYAN to nyan
        a.play(); // nyan! I'm Cream the cat!
        b.play(); // nyan! I'm Cream the cat!
    }
}
```

**Static variables are shared by all instances of the class and should be accessed via dot notation with the class**, not the instance. So, the initialisation of `Cat b` changes the noise of `Cat a`. And the output is as follows

```
Nyan! I'm Cream the cat!
Nyan! I'm Tubbs the cat!
nyan! I'm Cream the cat!
nyan! I'm Tubbs the cat!
```



#### C. Squaring a List (Recursion and Iteration)

```Java
// No pointer points to L, so the code does not change L.
public static IntList squareR(IntList L) {
    if (L == null) {
        return null;
    }
    return new IntList(L.first * L.first, squareR(L.rest)); 
}


public static IntList squareI(IntList L) {
    if (L == null) {
        return null;
    }
   // p is a blank paper, but this pointer will point to the last node after the iteration
   // Result records the initial addresss of the p pointer
   // pl is a shallow copy of L, it has access to the items in L, and the pl = pl.next operation
   // will not modify L
    IntList p = new IntList(L.first * L.first, null);
    IntList Result = p; // Result points to the original address
    IntList pl = L.rest;
    while (pl != null) {
        p.rest = new IntList(pl.first * pl.first, null);
        p = p.rest; // Modifing P will modify Result at the same time.
        pl = pl.rest; // pl is a shallow copy of L, so we can access items in L without modifying L
    }
    // we cannot return L, because L is not modified
    return Result;
}

// The IntList p = L statement is *copy by reference*, so L will be changed simultaneously.
public static IntList squareMutativeI(IntList L) {
    if (L == null) {
        return null;
    }
    IntList p = L;
    while (p != null) {
        p.first = p.first * p.first;
        p = p.rest;
    }
    return L;
}

// All the manipulation is of L, so L will be changed.
public static IntList squareMutativeR(IntList L) {
    if (L == null) {
        return null;
    } else {
        L.first = L.first * L.first;
        squareMutativeR(L.rest);
    }
    return L;
}
```

Please note, special cases should always be considered firstly (i.e. `L = null`). Implementing square iteratively, and squareMutative recursively are rather tricky. The key, however, remains to be the GRoE. **Java always copy by value, so if we do not intend to modify an instance variable, we make a copy of it and use the copy to access the member variables. By doing so, the original instance variables wil still point to the original address.**



### 3. Scope, Pass-by-Value, Static (Exam Preparation)

#### A. Static Shock

 Since `bang` is static, all instances share the same variable. 

```Java
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
```

The output is `300`, `99`.



#### B. Horse and Scope

In the `same` method, the line `same = horse.same` is void, the philosphy behind is exitrely different from that in [The Pokemon Problem](# A. The Pokemon Problem (Pass by what?)). It is void because the assignment is already finished. The `return same.same` statement refers to the `same` of `cult`. **This is because the `same` in the `if` statement is a local variable, and therefore can not go outside the `if` statement**. 

```java
public class Horse {
    Horse same;
    String jimmy;

    public Horse(String lee) {
        jimmy = lee;
    }

    public Horse same(Horse horse) {
        if (same != null) { // this same refers to cult, same = cult -> if statement true
            Horse same = horse; // same = horse -> jimmy = youve been, same = null
            same.same = horse;  // same.same (horse.same) = horse
            same = horse.same; // same = horse.same = horse -> same = horse -> !rudundant!
        }
        return same.same;
    }

    public static void main(String[] args) {
        Horse horse = new Horse("youve been"); // horse: jimmy = youve been, same = null
        Horse cult = new Horse("horsed"); //cult: jimmy = horsed
        cult.same = cult; // cult: jimmy = horsed, same = cult
        cult = cult.same(horse);
        System.out.println(cult.jimmy); // horsed
        System.out.println(horse.jimmy); // youve been
    }
}
```

The output is `horsed`, `youve been`.



#### C. Value Swap Problem

This program shows how can we swap the values of two classes.

```Java
public class Foo {
    public int x, y;

    public Foo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void switcheroo(Foo a, Foo b) {
        Foo temp = a;
        a = b;
        b = temp;
    }

    public static void fliperoo(Foo a, Foo b) {
        Foo temp = new Foo(a.x, a.y);
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void swaperoo(Foo a, Foo b) {
        Foo temp = a;
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void main(String[] args) {
        Foo foobar = new Foo(10, 20); // foobar x = 10, y = 20
        Foo baz = new Foo(30, 40); // baz x = 30, y = 40
        switcheroo(foobar, baz); // unchanged (static method cannot access instance variables)
        fliperoo(foobar, baz); // foobar x = 30, y = 40, baz x = 10, y = 20
        swaperoo(foobar, baz); // foobar x = 10, y = 20, baz x = 10, y = 20

    }
}
```

1. The `switcheroo(Foo a, Foo b)` is void. The reason behind is same as  [The Pokemon Problem](# A. The Pokemon Problem (Pass by what?)). That is, you can modify the member variables of a class in a method, but you cannot make it point to a new instance variable. Output: `10, 20 & 30, 40`.

2. The `fliperoo(Foo a, Foo b)` swaps the value of a and b. Because it has a new instance variable that has the same value as `a`, **but different address**. Then the two instances are changed by modifying member variables. Output: `30, 40 & 10, 20`.

3.  The `Foo temp = a` statement makes `temp` points to `a`. So when `a` is modified, `temp` is modified simultaneously. This void the assignment to `b`.  Output: `10, 20 & 10, 20`.

   

#### D. Math Problems

This  program demonstrates the behaviour of primitive type variables in methods.

```Java
public class QuikMaths {
    public static void multiplyBy3(int[] A) {
        for (int x : A) {
            x = x * 3; // Here x is pass by value, so this statement cannot change the values in A
        }
    }

    public static void multiplyBy2(int[] A) {
        int[] B = A;
        for (int i = 0; i < B.length; i += 1) {
            B[i] *= 2;
        }
    }

    public static void swap(int A, int B) {
        int temp = B;
        B = A;
        A = temp;
    }

    public static void main(String[] args) {
        int[] arr;
        arr = new int[]{2, 3, 3, 4};
        multiplyBy3(arr); // This function is void.

        /*Valueofarr:{2,3,3,4}*/

        arr = new int[]{2, 3, 3, 4};
        multiplyBy2(arr); // arr -> {4, 6, 6, 8}

        /*Valueofarr:{4,6,6,8}*/

        int a = 6;
        int b = 7;
        swap(a, b); // a & b is pass by value, it cannot be changed.

        /*Valueofa:6 Valueofb:7*/
    }
}
```

The `multiplyBy3(int[] A)` function is useless, because it does not return anything or change the input array, because it iterates the elements in the `for` loop. The `multiplyBy2(int[] A)` function manipulates the boxes and can therefore modify the input array. Actually, the `int[] B = A;` statement is redundant. The following code does the same thing.

```java
    public static void multiplyBy22(int[] A) {
        for (int i = 0; i < A.length; i += 1) {
            A[i] *= 2;
        }
    }
```

 The last `swap(int A, int B)` is also useless, as it returns nothing and cannot really change the values of `A` and `B`. 

```Java
    static void swapValuesUsingThirdVariable(int m, int n)
    {
        // Swapping the values
        int temp = m;
        m = n;
        n = temp;
        System.out.println("Value of m is " + m
                + " and Value of n is " + n);
    }
```

But you can do this inside the class as shown below.



### 4. Linked List, Arrays

#### A. Linked Lists Practice

##### a. `insert` Method of `SLList`

Implement SLList.insert which takes in an integer x and inserts it at the given position. If the position is after the end of the list, insert the new node at the end. For example, if the SLList is 5→6→2, insert (10, 1) results in 5→10→6→2.



**The author's solution:**

```Java
   public void insert(int item, int position) {
        if (position == 0) {
            addFirst(item);
            return;
        }
        if (position >= size) {
            addLast(item);
            return;
        }

        IntNode added = new IntNode(item, getNode(position));
        getNode(position - 1).next = added;
        size++;
    }
```

```Java
    private IntNode getNode(int index) {
        IntNode p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }
```

We can write a ` getNode(int index)` helper method to quickly  find the two adjacent `IntNode`. Then create a new `IntNode` and link them to the two adjacent node. The time complexity of the ` getNode(int index)` is $O(h)$. But thsi function is called twice for each insertion operation. 



The standard solution, however, did not use a help method. But the philosophy is exactlly the same. Just go the the middle of the list, create a new node, and link it to the adjacent nodes. Since it only iterates the list once, the time complexity is about half of the author's solution. However it is not quite elegant as the code looks messy.

```java
    public void insertSolution(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        while (position > 1 && currentNode.next != null){
            position--;
            currentNode = currentNode.next;
        }
        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }
```



##### b. `reverse`  Method of `SLList`

The question:

> Add another method to the `SLList` class that reverses the elements. Do this using the existing `IntNodes` (you should not use **new**).



**The author's solution:**

```Java
    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode last = getNode(size - 1);
        for (int i = size - 1; i > 0; i--) {
            getNode(i).next = getNode(i - 1);
        }
        first = last;
        getNode(size - 1).next = null;
    }
```

Again, with the help of the helper method `getNode(int index)`, the program runs from back and revert the direction of the pointer. But please note that, we need to record the last node at first, or we lose access to this `IntNode`. Also, we need to null the link of the new last node (that is what the last line does).



The standard solution is rather tricky. It iterates the list from the beginning. This results in many variable storage problems. And since the loop condition is `rFront != null`, so when the loop breaks, `rFront == null`. This result to a conterintuitive assignment, `first = rBack`, because the `rFront` is already out of scope. 

```Java
public void reverseSolution() {
    IntNode rBack = null;
    IntNode rFront = first;
    while (rFront != null) {
        IntNode nextrFront = rFront.next;
        rFront.next = rBack;
        rBack = rFront;
        rFront = nextrFront;
    }
    first = rBack;
}
```

 

The recursive method is of the most difficult one. It looks clean and tidy. 

```Java
private IntNode reverseR(IntNode p) {
    if (p.next == null || p == null) {
        return p;
    } else {
        IntNode reversed = reverseR(p.next);
        p.next.next = p;
        p.next = null;
        return reversed;

    }
}
```

`p.next.next = p` statement reverses the pointer, the `reversed ` variable stores all list that has been reversed so far, setting `p.next = null` make sure that the final elements points to null. The box notation is shown below

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/SLListRecursive.png?raw=true)



#### B. Arrays Practice

##### a. `insert` Method of `Arrays`

The author's iteration method. Please note that we should traverse `newArray`. The `position = Math.min(arr.length, position);` avoids any too large index. 

```Java
    // Iteration method
    public static int[] insert(int[] arr, int item, int position) {
        int[] newArray = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        for (int i = 0; i < newArray.length; i++) {
            if (i == position) {
                newArray[i] = item;
            } else {
                if (i < position)
                    newArray[i] = arr[i];
                else {
                    if (i > position) {
                        newArray[i] = arr[i - 1];
                    }
                }
            }
        }
        return newArray;
    }
```



Similarly, we can also use `System.arraycopy` method to perform the similar operation.

```java
    //Stytem.arraycopymethod
    public static int[] insertSystem(int[] arr, int item, int position) {
        int[] newArray = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        System.arraycopy(arr, 0, newArray, 0, position);
        System.arraycopy(arr, position, newArray, position + 1, arr.length - position);
        newArray[position] = item;
        return newArray;
    }
```



The `testInsertMethod` randomly generate the items and positions as input to the two insertion functions. It also compares the result of the two methods.

```Java
    @Test
    public void testInsertMethod() {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {1, 3, 5, 7};
        for (int i = 0; i < 15; i++) {
            int position = (int) (Math.random() * array1.length * 2);
            int item = (int) ((Math.random()) * 100);
            System.out.print(item + ", ");
            array1 = insert(array1, item, position);
            array2 = insertSystem(array2, item, position);
        }
        System.out.println();
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        org.junit.Assert.assertArrayEquals(array1, array2);
    }
```



##### b. `reverse` Method of `Array`

The original question is 

> Consider a method that destructively reverses the items in arr. For example calling reverse on an array [1, 2, 3] should change the array to be [3, 2, 1]. What is the fewest number of iteration steps you need? What is the fewest number of additional variables you need?

The answer is, we need to iterates just half of the `arr.length` to swap items. And the number of  `temp` variables is also half of the `arr.length`.

```java
    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void swap(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length - 1 - i);
        }
    }
```



There was also a test method to check if the method can handle arrays with both odd and even number of elements.

```Java
@Test
    public void testSwap() {
        int[] test = new int[100];
        int[] expected = new int[100];
        for (int i = 0; i < 100; i++) {
            test[i] = i;
            expected[i] = 100 - i - 1;
        }

        int[] test2 = new int[101];
        int[] expected2 = new int[101];
        for (int i = 0; i < 101; i++) {
            test2[i] = i;
            expected2[i] = 101 - i - 1;
        }
        swap(test);
        swap(test2);
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(expected2));
        org.junit.Assert.assertArrayEquals(test, expected);
        org.junit.Assert.assertArrayEquals(test2, expected2);

    }
```



##### c. `replicate` Method of `Array`

The original question is:

>  Write a non-destructive method replicate(**int**[] arr) that replaces the number at index i with arr[i] copies of itself. For example, replicate([3, 2, 1]) would return [3, 3, 3, 2, 2, 1].

The author's solution:

```Java
    private static int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int[] replicate(int[] arr) {
        int[] replicated = new int[sum(arr)];
        int position = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                replicated[position] = arr[i];
                position += 1;
            }
        }
        return replicated;
    }
```

The key is that `array` in Java is immutable, so we need to pre-calculate the length of the new array. A private helper method was therefore created. The rest of the task is straightforward.



The `testReplicate` method is as following

```java
   @Test
    public void testReplicate() {
        int[] original = {2, 1, 3, 5, 4, 0};
        int[] expected = {2, 2, 1, 3, 3, 3, 5, 5, 5, 5, 5, 4, 4, 4, 4};
        int[] replicated = replicate(original);
        System.out.println(Arrays.toString(replicated));
        org.junit.Assert.assertArrayEquals(replicated, expected);
    }
```



### 5. Linked Lists, Arrays (Exam Preparation)

#### A. Flatten

Original Problem:

> Write a method flatten that takes in a 2-D array x and returns a 1-D array that contains all of the arrays in x concatenated together. For example, flatten({{1, 2, 3}, {}, {7, 8}}) should return {1, 2, 3, 7, 8}. (Summer 2016 MT1)

The author's solution with a test method.

```java
import org.junit.Test;

import java.util.*;

public class Flatten {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            totalLength += x[i].length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                a[aIndex] = x[i][j];
                aIndex++;
            }
        }
        return a;
    }

    @Test
    public void testFlatten() {
        int[][] x = {{1, 2, 3}, {}, {7, 8}};
        int[] expected = {1, 2, 3, 7, 8};
        int[] actual = flatten(x);
        System.out.println(Arrays.toString(actual));
        org.junit.Assert.assertArrayEquals(actual, expected);
    }

}

```



#### B. SKippify

Original problem:

>Suppose we have the IntList class, as defined in lecture and lab 2, with an added skippify function.
> Suppose that we define two IntLists as follows.
>
>`IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);`
>`IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);`
>
>Fill in the method skippify such that the result of calling skippify on A and B are as below:
>\- After calling A.skippify(), A: (1, 3, 6, 10)
>\- After calling B.skippify(), B: (9, 7, 4)

The author's solution:

```java
    public void skippify() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p;
            for (int i = 0; i <= n; i++) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = p.rest;
            n++;
        }
    }
```



The author's test code.

```Java
import org.junit.Test;

public class testSkippify {

    @Test
    public void testSkippify() {
        IntList test = new IntList(0, null);
        for (int i = 1; i < 11; i++) {
            test.addFirst(11 - i);
        }
        test.skippify();
    }
}

```

Please note that we cannot use `@Test` in the `IntList` class. You may want to look at a warning at [Section III. Testing ](# III. Testing) section.



#### C. Remove Duplicates

The original problem:

> Given a sorted linked list of items - remove duplicates. * For example given 1 -> 2 -> 2 -> 2 -> 3,
> Mutate it to become 1 -> 2 -> 3 (destructively)

The author's solution:

```Java
public static void removeDuplicates(IntList p) {
    if (p == null) {
        return;
    }
    IntList current = p.rest;
    IntList previous = p;
    while (current != null) {
        if (current.first == previous.first) {
            previous.rest = current.rest;
        } else {
            previous = previous.rest; // or previous = current; in the solution sheet
        }
        current = current.rest;
    }
}
```



The test method:

```Java
import org.junit.Test;

public class testRemoveDuplicates {

    @Test
    public void tetsRemoveDuplicatesFunc() {
        IntList test = new IntList(3, null);
        test = new IntList(3, test);
        test = new IntList(3, test);
        test = new IntList(2, test);
        test = new IntList(2, test);
        test = new IntList(1, test);
        test = new IntList(0, test);
        IntList.removeDuplicates(test); // Returns {3, 2, 1, 0}
    }
}

```

The `IntList.removeDuplicates(test);` is very tricky. You have to use dot notation to call a static method in another class. See [Section I.2.A](# A. Static vs. Non-static Methods) for more details.



### 6. Inheritance

#### A. Creating Cats

The original question:

> Given the Animal class, fill in the definition of the Cat class so that when greet() is called, the label “Cat” (instead of “Animal”) is printed to the screen. Assume that a Cat will make a “Meow!” noise if the cat is 5 years or older and “MEOW!” if the cat is less than 5 years old.

The author's solution:

```Java
public class Cat extends Animal {

    public Cat(String Noise, String Name, int Age) {
        super(Name, Age);
        noise =Noise;
    }

    @Overrid
    public void greet(){
        System.out.println("Cat " + name + " says: " + makeNoise());
    }

    public static void main(String[] args) {
        Cat younger = new Cat("Meow", "Kiki", 3);
        Cat elder = new Cat("Meow", "Popo", 5);
        younger.greet();
        elder.greet();
    }
}
```

The author found that the default constructor of the  `Animal` class was missing `public Animal(){}`.



#### B. Raining  Cats and Dogs

The original question:

> Assume that Animal and Cat are defined as above. What would Java print on each of the indicated lines?
>
> Consider what would happen if we added the following to the bottom of main under line 12:
>
> `a = new Dog("Spot", 10); `
>
> `d=a;`
>
> Why would this code produce a compiler error? How could we fix this error?

```Java
public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);
        a.greet();  // (A) Animal Pluto says: Huh?
        c.greet();  // (B) Cat Garfield says: Meow!
        d.greet();  // (C) Dog Fido says: WOOF!
        a = c; // now a points to c
        ((Cat) a).greet();  //(D) Cat Garfield saysL Meow!
        a.greet(); //(E) Cat Garfield saysL Meow!

        a = new Dog("Spot", 10); // Static type: Animal, but dynamic type: Dog
        d = (Dog) a; 
        d.greet(); // (F) Dog Spot says: Woof!
    }
}
```

The answer is included in the code itself. Hte compiler error comes from that a `Dog` container cannot store `Animal`. So we need casting.



#### D. *Inheritance Misery (Extra*)

>Cross out any lines that cause compile-time errors or cascading errors (failures that occur because of an error that happened earlier in the program), and put an X through runtime errors (if any). Don’t just limit your search to main, there could be errors in classes A, B, C. What does D.main output after removing these lines?

```Java
class A {
    public int x = 5;

    public void m1() {
        System.out.println("Am1-> " + x);
    }

    public void m2() {
        System.out.println("Am2-> " + this.x);
    }

    public void update() {
        x = 99;
    }
}

class B extends A {
    public void m2() {
        System.out.println("Bm2-> " + x);
    }

    public void m2(int y) {
        System.out.println("Bm2y-> " + y);
    }

    public void m3() {
        System.out.println("Bm3-> " + "called");
    }
}

class C extends B {
    public int y = x + 1;

    public void m2() {
        System.out.println("Cm2-> " + super.x);
    }

/*    public void m4() {
        System.out.println("Cm4-> " + super.super.x); // super.super violates the encapsulation of the super class
    }*/

    public void m5() {
        System.out.println("Cm5-> " + y);
    }
}

class D {
public static void main (String[] args) {
        // B a0 = new A();
        // a0.m1();
        // a0.m2(16);
         A b0 = new B();
         System.out.println(b0.x); // 5
         b0.m1(); // Am1-> 5
         b0.m2(); // Bm2-> 5
         //b0.m2(61); // ((B)b0).m2(61); will do the job
         B b1 = new B();
         b1.m2(61); // Bm2y-> 61
         b1.m3(); // Bm3-> called
    		 A c0 = new C();
         c0.m2(); //Cm2-> 5
        // C c1 = (A) new C();
         A a1 = (A) c0;
         C c2 = (C) a1;
         c2.m3(); // Bm3-> Called
        // c2.m4();
         c2.m5(); // Cm5-> 6
         ((C) c0).m3(); // Bm3-> Called
        // (C) c0.m3();
         b0.update(); // x = 99
         b0.m1(); // Am1-> 99
         }
}
```

The answer is included in the comments. **Some key points are the following:**

* Super class container can store subclass variables but not vice versa.
* `super.super` is not allowed to protect the encapsulation of the the first super class;
* Do not do `(C) c0.m3();`, the `(C)` will apply to the output of `c0.m3()` ;
* If the static type of a variables does not contains a desired method, even if its dynamic type does have such a method, it cannot be called. (See `//b0.m2(61); // ((B)b0).m2(61); will do the job` for reference);
* We cannot declare two public classes in one Java files, but we can define as many as possible non-public class in one Java file (please consult the [`Dog`](# B. Raining Cats and Dogs) class for reference).



### 7. Inheritance (Exam Preparation)

#### A. Playing with Puppers

The original question:

> Suppose we have the Dog and Corgi classes which are a defined below with a few methods but no implementation shown. (modified from Spring ’16, MT1)

The author's solution is included in the second piece of code. 

```java
public class Dog {
    public void bark(Dog d) { /* Method A */ }
}
```

```Java
public class Corgi extends Dog {
    public void bark(Corgi c) { /* Method B */
        System.out.println("B");
    }

    @Override
    public void bark(Dog d) { /* Method C */
        System.out.println("C");
    }

    public void play(Dog d) { /* Method D */
        System.out.println("D");
    }

    public void play(Corgi c) { /* Method E */
        System.out.println("E");
    }


    public static void main(String[] args) {
        Dog d = new Corgi(); // Static type Dog, dynamic type Corgi
        Corgi c = new Corgi(); // Static type Corgi, dynamic type Corgi

        //d.play(d); compile-time error
        //d.play(c); compile-time error

        c.play(d);  // D
        c.play(c);  // E
        c.bark(d);  // C
        c.bark(c);  // B
        d.bark(d);  // C
        d.bark(c);  // C Use static type to check so has to go to function that overrides bark() in Dog
    }
}
```

Please pay extra attention to the final statment `d.bark(c);` The reason why it calls the *C method* is that, the compiler checks that the `d`, which is an instance of `Dog` has a `public void bark(Dog d)` method, then it goes directly to th `public void bark(Dog d)` method. Because `c` is a `Dog`, in addition to `Corgi`.



#### B. Cast the Line

The original question:

> Suppose Cat and Dog are two subclasses of the Animal class and the Tree class is unrelated to the Animal hierarchy. All four classes have default constructors. For each line below, determine whether it causes a compilation error, runtime error, or runs successfully. Consider each line independently of all other lines. (extended from Summer ’17, MT1)

The author's solution:

```Java
/* Tree Class.*/
public class Tree {

    /* Default constructor.*/
    public Tree() {
    }

    /* Main program.*/
    public static void main(String[] args) {
        /* Cat c = new Animal(); Compile error.*/ 
        Animal a = new Cat();
        /* Dog d = new Cat(); Compile error.*/
        /* Tree t = new Animal(); Compile error.*/

        Animal aa = (Cat) new Cat();
        Animal aaa = (Animal) new Cat();
        /*Run time error, cannot cast a subclass into its super class.*/
        Dog d = (Dog) new Animal();
        /*Cat c = (Cat) new Dog(); Compile error.*/
        /*Animal a = (Animal) new Tree(); Compile error.*/
    }
}

```

The statement `Dog d = (Dog) new Animal();` is a very good example for the statment that casting is a rather dangerous operation. Yes, it bypassed the compiler check, but the bug would finally be exposed to users.



#### C. SLList Vista

The original question can be found in this [link](https://sp18.datastructur.es/materials/discussion/examprep04sol.pdf).

The author's solution:

```Java
import java.util.NoSuchElementException;

public class SLListVista<T> extends SLList<T>{
    @Override
    public int indexOf(T x) {
        if (super.indexOf(x) != -1) {
            return super.indexOf(x);
        }
        else {
            throw new NoSuchElementException();
        }
    }
}
```

Nothing tricky, just remember to use the `super` keyword.



#### D. Dynamic Method Selection

The original question can be found in this [link](https://sp18.datastructur.es/materials/discussion/examprep04.pdf). This question is really confusing, because the code provided does not contain any method for `DMSList` to insert an item. Therefore, the author added an `addFirst` method to enable items insertion. 

```Java
public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntNode());
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class LastIntNode extends IntNode {
        public LastIntNode() {
            super(0, null);
        }

        @Override
        public int max() {
            return 0;
        }

    }

    public int max() {
        return sentinel.next.max();
    }

    public static void main(String[] args) {
        DMSList test = new DMSList();
        for (int i = 0; i < 10; i++){
            test.addFirst(10 - i + (int)(Math.random()*15));
        }
        System.out.println(test.max());
    }
}
```



Nothing is challenging in this task except fully understanding the question itself. The key idea of the `DMSList` is that the last node is a `LastIntNode` rather than an `IntNode`. Therefore, the `max` function will be called accordingly.



### 8. Abstract Data Types (ADTs)

#### A. Assorted ADTS

The question one explains a number of ADTs. Please consult this [website](https://sp18.datastructur.es/materials/discussion/disc05.pdf) for more information.

1. The features of ADTs:

   |      ADTs       |                           Features                           |
   | :-------------: | :----------------------------------------------------------: |
   |     `List`      |       A `List` is an ordered collection, or sequence.        |
   |      `Set`      | A `Set` is a (usually ordered) collection of unique elemnts  |
   |      `Map`      | A `Map` is a collection of key-value mappings, like dictionary in Python. Like a set, the keys in a map are unique. |
   |     `Stack`     | A `Stack` is a last-in, first-out (from the end of the `Stack`) ADT: elements are always added or removed from one end of the data structure. A `Stack` is a linear collections. |
   |     `Queue`     | A `Queue` is a first-in, first-out ADT.  A `Queue` is a linear collections. |
   |     `Deque`     | A `Deque` (double ended `Queue`, pronounced “deck”) is a linear collection that supports element insertion and removal at both ends. |
   | `PriorityQueue` | A `PriorityQueue` is like a regular `Queue` except each element has a priority associated with it which determines in what order elements are removed from the queue. |



#### B. Solving Prblems with ADTs

The question:

> For each problem, which of the ADTs given in the previous section might you use to solve each problem? Which ones will make for a better or more efficient implementation?

> 2.1 (a) Given a news article, find the frequency of each word used in the article.

Answer: Map. The key is the word, and the value is the corresponding number of times that it appears in the article



> 2.1 (b) Given an unsorted array of integers, return the array sorted from least to greatest.

Answer: Priority queue, simply links the priority and its value, then add elements from `length - 1` to 0. And we get a sorted array.



> 2.1 (c) Implement the forward and back buttons for a web browser

> Answer: Use two stacks, one for each button. Each time you visit a new web page, add the previous page to the back button’s stack. When you click the back button, add the current page to the forward button stack, and pop a page from the back button stack. When you click the forward button, add the current page to the back button stack, and pop a page from the forward button stack. Finally, when you visit a new page, clear the forward button stack.



> 2.2 Java supports many built-in ADTs and data structures that implement these ADTs. But if we want something more complicated, we’ll have to build it ourselves.
>
> If you wish to use sorting as part of your design, assume that it will take Θ(N log N ) time where the length of the sequence is N.
>
> (a) Suppose we want an ADT called BiDividerMap that allows lookup in both directions: given a value, return its corresponding key, and vice versa. It should also support numLessThan which returns the number of mappings whose key is less than a given key.
>
> ```Java
> BiDividerMap
>   put(k, V); // put a key, value pair
>   getByKey(K); // get the value corresponding to a key
>   getByValue(V); // get the key corresponding to a value
>   numLessThan(K); // return number of keys in map less than K
> ```
>
> Describe how you could implement this ADT by using existing Java ADTs as building blocks. Come up with an idea that is correct first before trying to make it more efficient.

The answer:

Use two maps as the member variables, one stores `(k, V)` (`map1`) and the other one stores (V, k) (`map2`), whenever putting things into it, put the paris to these two maps, respectively. With theses maps, it will be very easy to implement `getByKey` and `getByValue`.  Then use the `get(key)` fucntion of `map1` to determine whoise key is less than a given key.



> (b) Next, Suppose we would like to invent a new ADT called MedianFinder which is a collection of integers and supports finding the median of the collection.
>
> ```java
> MedianFinder
>   add(x); // adds x to the collection of numbers
>   median(); // returns the median from a collection of numbers
> ```
>
> Describe how you could implement this ADT by using existing Java ADTs as building blocks. What’s the most efficient implementation you can come up with?

The answer

> Use a list. When you add, just insert to the back of the list. When computing the median, first sort the list. Then figure out the size of the list and get the middle item. For a faster solution, use an integer value for storing the current median and two priority queues, one for integers less than the median and one for integers greater than the median. When adding an integer, add it to the appropriate priority queue. Each time an integer is added, maintain balance between the priority queues by shifting integers over as needed.



> 2.3 Define a Queue class that implements the push and poll methods of a queue ADT using only a Stack class which implements the stack ADT. Hint: Consider using two stacks.
>
> The solution:

```Java
import java.util.Stack;

public class Queue<E> {
    private Stack<E> stack = new Stack<>();

    public void push(E element){
        stack.push(element);
    }

    public E pop(){
        Stack<E> temp = new Stack<>();
        while(!stack.isEmpty()){
            temp.push(stack.pop());
        }
        E toTop = temp.pop();
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return toTop;
    }
}
```

The `push` method is straightforward. But the `pop` method is not that easy. It is very much like a detour that find the first element and then re-construct the original `Stack` with a temporary variable, `temp`.



### 9. Abstract Data Types (ADTs) (Exam Preparation)

#### A. Assorted ADTs.

This section is pretty similar to those in [dicsussion 8.A](# A. Assorted ADTS). For full details, please consult this [website](https://sp18.datastructur.es/materials/discussion/examprep05.pdf).



#### B. Applications of ADTs

> (a) Given an array of integers A and an integer k, return true if any two numbers in the array sum up to k, and return false otherwise. How would you do this? Give the main idea and what ADT you would use.

Author's answer:

A list will be fine. Just create a list that contains those integers, and use iteration to check if there are any two number breaking the *up to k* restriction.



The Hug's answer:

> The fastest way to do this is with the help of a set (specifically, a HashSet, which has constant time add() and contains(). The key insight is that if a + b = x,then b = x - a. Thismeansthatwecanlooktoseewhetherornotx - (current element) has been seen already. We can store every element that we look through in a set, and do a single pass through the array.



The author's implmentaion and the corresponding test: 

```Java
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class twoSum {
    public boolean twoSumFunc(int[] A, int k) {
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (hashSet.contains(k - A[i])) {
                return true;
            } else hashSet.add(A[i]);
        }
        return false;
    }

    @Test
    public void testtwoSumFunc() {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = twoSumFunc(A, 18);
        boolean actual2 = twoSumFunc(A, 20);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}
```

**Please note that this is the well-known *two-sum* question in LeetCode.**



>  (b) Find the k least common words in a document. Assume that you can represent this as an array of Strings, where each word is an element in the array. You might find using multiple data structures useful.



The Hug's answer:

> Keep a count of all the words in the document using a HashMap <String, Inte- ger>. After we go through all of the words, each word will be mapped to how many times it’s appeared. What we can then do is put all the words into a MaxPriorityQueue<String>, using a custom comparator that compares words based on the counts in the HashMap. We can then pop off the k most common words by just calling poll() on the MaxPriorityQueue k times.



The author's implementation and its test:

```java
import org.junit.Test;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class kMost {

    public static void topkPopularWords(String[] words, int k) {
        Map<String, Integer> wordlist = new HashMap<>();
        for (String current : words) {
            if (wordlist.containsKey(current)) {
                wordlist.put(current, wordlist.get(current) + 1);
            } else {
                wordlist.put(current, 1);
            }
        }

        PriorityQueue<String> list = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -wordlist.get(o1) + wordlist.get(o2);
            }
        });

        for (String current : wordlist.keySet()) {
            list.add(current);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(list.poll());
        }
    }

    @Test
    public void testkMost() {
        String[] testlist = {"3", "3", "3", "3", "3", "1", "a",
                "b", "c", "d", "a", "1", "2", "2", "2", "b", "b" };
        kMost.topkPopularWords(testlist, 3);
    }
}
```

The middle part of the code defines the behaviour of our `PriorityQueue` by creating a new `Compararotr` and overrides its `compare` function. Actually, we have to do so because `Comparator` is just an interface. Then we put our `wordlist.keySet` into the `PriorityQueue`, and simply use its `poll` method to retrieve the `k` words with more counts.



#### C. Mutant ADTs

> (a)  Define a Queue class that implements the push and poll methods of a queue ADT using only a Stack class which implements the stack ADT. Hint: Try using two stacks



The Hug's answer:

> Have two stacks; one to hold all the items, and one to be a buffer for when we add something to our queue. Lets call the first stack A and the second stack B. Whenever we add something to our Queue, we pop everything off of A and push onto B. We then push our new item onto A, and then pop everything back off of B and onto A again. Thus, our most recent elements are on the bottom of A, and our oldest elements are on the top of A, mimicking the behavior of a queue.



The author's implementation and its test.

```Java
import org.junit.Test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import static org.junit.Assert.*;

public class QueueStack<Type> {
    private Stack<Type> s = new Stack<>();

    public void push(Type x) {
        s.push(x);
    }

    public Type poll(){
        Stack<Type> temp = new Stack<>();
        while(!s.isEmpty()){
            temp.push(s.pop());
        }
        Type value = temp.pop();
        while(!temp.isEmpty()){
            s.push(temp.pop());
        }
        return value;
    }

    @Test
    public void testQueueStack(){
        QueueStack<Integer> test = new QueueStack<>();
        Queue<Integer> expected = new LinkedList<>();

        for (int i = 0; i < 10; i++){
            test.push(i);
            expected.add(i);
        }

        for (int i = 0; i < 10; i++){
            assertEquals(expected.poll(), test.poll());
        }
    }
}
```

Please consult [section Discussion.8.B](# B. Solving Prblems with ADTs) for more details.



> (b) Suppose we wanted a data structure SortedStack that takes in integers, and maintains them in sorted order. SortedStack supports two operations: push(int i) and pop(). Pushing puts an int onto our SortedStack, and popping returns the next smallest item in the SortedStack. For example, if we inserted 10, 4, 8, 2, 14, and 3 into a SortedStack, and then popped everything off, we would get 2, 3, 4, 8, 10, 14.



Hug's solution:

> The solution to this is very similar to that of the question above. Once again, we will have two stacks, A and B. A will hold all the items, and B will be our buffer again. This time, when we add something to the queue, we continue to pop items off from A and push it onto B until the next item that will be popped (we can access this via peek()) is greater than or equal to the item we’re adding to it. At that point, we can push our item onto A, and then pop everything from B and push them back into A. Thus, we maintain the sorted-ness of our SortedStack.



The author's implementation and its test:

```Java
import org.junit.Test;
import java.util.Random;
import java.util.Stack;

public class SortedStack<Type extends Comparable<Type>> {
    private Stack<Type> items = new Stack<>();

    public void push(Type i){
        Stack<Type> temp = new Stack<>();
        while (!items.isEmpty() && (items.peek().compareTo(i) < 0)){
            temp.push(items.pop());
        }
        items.push(i);
        while(!temp.isEmpty()){
            items.push(temp.pop());
        }
    }

    public Type poll(){
        /*Stack<Type> temp = new Stack<>();

        while (!items.isEmpty()){
            temp.push(items.pop());
        }
        Type value = temp.pop();
        while(!temp.isEmpty()){
            items.push(temp.pop());
        }
        return value;*/
        return items.pop();
    }

    @Test
    public void testSortedStack(){
        SortedStack<Integer> test = new SortedStack<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++){
            test.push(r.nextInt(300));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(test.poll());
        }
    }
}
```

This question is very similar to the previous one. We create a `temp` to store all elements less than `i`, and insert `i`, finally push all elements back. The test method verifies that the class works as expected.



### 10. Selecting ADTS

#### A. Immutable Rocks

> Access control allows us to restrict the use of fields, methods, and classes. 
>
> • public: Accessible by everyone. 
>
> • protected: Accessible by the class itself, the package, and any subclasses. 
>
> • default (no modifier): Accessible by the class itself and the package. 
>
> • private: Accessible only by the class itself.



a. Pebble

```Java
import org.junit.Test;

public class Pebble {
    public int weight;

    public Pebble() {
        weight = 1;
    }

    @Test
    public void testPebble() {
        Pebble test = new Pebble();
        System.out.println(test.weight);
        test.weight = 2;
        System.out.println(test.weight);
    }
}
```

This class is mutablem, because a clinet has public access to the member variable, `weight`. The test is included in this piece of code. 



b. Rock

```Java
public class Rock {
    public final int weight;

    public Rock(int w) {
        weight = w;
    }
}
```

This class is immutable, since there is a final modifier in front of the `weight` variable.



c. Rocks

```Java
public class Rocks {
    public final Rock[] rocks;

    public Rocks(Rock[] rox) {
        rocks = rox;
    }
}
```

This class is mutable as shown in the following way:

```Java
import org.junit.Test;

public class testRocks {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] rox = {testOne, testTwo, testThree};
        Rocks test = new Rocks(rox);
        System.out.println(test.rocks[0].weight);
        test.rocks[0] = new Rock(4);
        System.out.println(test.rocks[0].weight);
    }
}
```

This is reasonble due to the fact that we only change the contents inside the `rocks` variable, its address is never changed.



d. SecretRocks

```Java
public class SecretRocks {
    private Rock[] rocks;

    public SecretRocks(Rock[] rox) {
        rocks = rox;
    }
}
```

This class is mutable, since we may change the input variable, `rox`, outside the class as follows

```Java
import org.junit.Test;

public class testSecretRocks {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] rox = {testOne, testTwo, testThree};
        SecretRocks test = new SecretRocks(rox);
        // System.out.println(test.rocks[0].weight); not visible
        rox[0] = new Rock(4);
        rox[1] = new Rock(5);
        rox[2] = new Rock(6);

    }
}
```



e. PunkRock

```Java
public class PunkRock {
    private final Rock[] band;

    public PunkRock(Rock[] yRoad) {
        band = yRoad;
    }

    public Rock[] myBand() {
        return band;
    }
}
```

There are two methdos to change its contents after instantiation, shown as follows:

```Java
import org.junit.Test;

public class testPunkRock {
    @Test
    public void testRocks() {
        Rock testOne = new Rock(1);
        Rock testTwo = new Rock(2);
        Rock testThree = new Rock(3);
        Rock[] merged = {testOne, testTwo, testThree};
        PunkRock test = new PunkRock(merged);
        merged[0] = testThree;
        // System.out.println(test.rocks[0].weight); not visible
        Rock[] external = test.myBand();
        external[0] = new Rock(4);
    }
}
```

The first method is very much like taht in question **d**. But we now have a `myBand` method to return the member variable, `band`. Thus, we have one more choice.



e. MomaRock

```Java
public class MommaRock {
    public static final Pebble baby = new Pebble();

    public static void main(String[] args) {
        MommaRock test = new MommaRock();
        MommaRock.baby.weight = 1;
        MommaRock.baby.weight = 2;
        MommaRock.baby.weight = 3;

    }
}
```

This class is obviously mutable. We cannot modify the address of `baby`, i.e. wa cannot change `baby` by making a different assignement. We can, however, modify its the value of `baby` directly as discussed in question **a**.



#### B. Breaking the System

The original questions can be found in this [link](https://sp18.datastructur.es/materials/discussion/disc06.pdf).

```Java
public class BadIntegerStack {
    public class Node {
        public Integer value;
        public Node prev;

        public Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    public Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        return top.value;
    }

    public static void main(String[] args) {
        try {
            BadIntegerStack test = new BadIntegerStack();
            test.pop();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }
        try {
            BadIntegerStack test = new BadIntegerStack();
            test.peek();
        }
        catch(NullPointerException error){
            System.out.println("Success!");
        }

        BadIntegerStack infiniteStack = new BadIntegerStack();
        infiniteStack.push(1);
        infiniteStack.top.prev = infiniteStack.top;
        int i = 0;
        while(!infiniteStack.isEmpty() && i < 100){
            i++;
            infiniteStack.pop();
            System.out.println("Limit reached");
        }
    }
}
```

The main method shows how to break this system. The `NullPointerException` error is due to the fact that the two methods, `pop` and `peek` did not consider the case where the stack is empty. Besides, please note that we can produce infinite long stacks only because we made everything `public`, which is not only unnecessary but also dangerous. This gives client opportunities to write statment like `infiniteStack.top.prev = infiniteStack.top;`, which ultimately break the system.



#### C. Design a Parking Lot

This question is rather confusing, please consult this [website](https://sp18.datastructur.es/materials/discussion/disc06sol.pdf) for the question and the corresponding solution.



A modified `GoodIntegerStack` is shown as follows:

```Java
package GoodIntegerStack;

public class GoodIntegerStack {
    private class Node {
        private Integer value;
        private Node prev;

        private Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    private Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        if (this.isEmpty()) {
            return null;
        }
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        if (this.isEmpty()) {
            return null;
        }
        return top.value;
    }
}
```



### 11. Exceptions and Iterators Exam Preparation

#### A. Exceptions

```Java
public class ExceptionsDemo {
    public static void checkIfZero(int x) throws Exception {
        if (x == 0) {
            throw new Exception("x was zero!");
        }
        System.out.println(x); // PRINT STATEMENT
    }

    public static int mystery(int x) {
        int counter = 0;
        try {
            while (true) {
                x = x / 2;
                checkIfZero(x);
                counter += 1;
                System.out.println("counter is " + counter); // PRINT STATEMENT
            }
        } catch (Exception e) {
            return counter;
        }
    }

    public static void main(String[] args) {
        System.out.println("mystery of 1 is " + mystery(1));
        System.out.println("mystery of 6 is " + mystery(6));
    }
}
```

Consider the code below. Recall that `x / 2` rounds down to the nearest integer. What will be the output when main is run?



The author's answer:

```Java
mystery of 1 is 0
3
counter is 1
1
counter is 2
myster of 6 is 2
```



#### B. AltList

Please consult this [link](https://sp18.datastructur.es/materials/discussion/examprep06.pdf) for the original question.



The author's solution:

```Java
public class AltList<X, Y> {
    private X item;
    private AltList<Y, X> next;

    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }


    public AltList pairsSwapped() {
        AltList newList = new AltList(next.item, new AltList<X, Y>(item, null));
        if(next.next != null){
            newList.next.next = next.next.pairsSwapped();
        }
        return newList;

    }
    public static void main(String args[]){
        AltList<Integer, String> list =
                new AltList<Integer, String>(5,
                        new AltList<String, Integer>("cat",
                                new AltList<Integer, String>(10,
                                        new AltList<String, Integer>("dog", null))));

        AltList<Integer, String> swapedList = list.pairsSwapped();
    }
}
```

Again, we ues recursive method in the `pairsSwapped` method. Please note that, non-destructive method generally requires a `new` container, otherwise it will be very hard to make the method non-destructive.



#### C. Every *k*th Element 

Please consult this [link](https://sp18.datastructur.es/materials/discussion/examprep06.pdf) for the original question. This question requires knowledge in `IntList`. Please consult [Lab 2](# 2. IntList (Lab 2)) for more details.



The author's solution (the class implements `Iterator`):

```Java
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public Integer next() {
        if (curList == null) {
            this.hasNext = false;
            throw new NoSuchElementException("No such element!");
        }
        int valueToReturn = curList.first;
        for (int i = 0; i < this.k && curList != null; i++) {
            curList = curList.rest;
        }
        return valueToReturn;
    }

    public static void main(String[] args) {
        IntList test = IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (Iterator<Integer> p = new KthIntList(test, 3); p.hasNext(); ) {
            System.out.println(p.next());
        }
    }
}
```


The author also wants to provide a solution that enables the `KthIntList` to use enhanced `for` loop, please consult section [VI.3.B](# B. Implementing Iterators) for more details.

```java
package Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KthIntList implements Iterable<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;

    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }

    public Iterator iterator() {
        return new KthIntListIterator();
    }

    private class KthIntListIterator implements Iterator<Integer>{
        public KthIntListIterator(){

        }

        public boolean hasNext() {
            return hasNext;
        }

        public Integer next() {
            if (curList == null) {
                hasNext = false;
                throw new NoSuchElementException("No such element!");
            }
            int valueToReturn = curList.first;
            for (int i = 0; i < k && curList != null; i++) {
                curList = curList.rest;
            }
            return valueToReturn;
        }
    }


    public static void main(String[] args) {
        KthIntList test = new KthIntList(IntList.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 2);
        for (int p :  test) {
            System.out.println(p);
        }
    }
}
```



### 12. Asymptotic Analysis I

#### A. Asymptotic Notation

1. Order the following *big O* runtimes runtimes from smallest to largest:

$O(1),\ O(\log n),\ O(n),\ O(n\log n),\ O(n^2),\ O(n^2\log n),\ O(n^3),\ O(2^n),\ O(n!),\ O(n^n)$



2. Are the statements in the right column true or false? If false, correct the asymptotic notation.

* $f(n)=20501,\ g(n)=1,\ f(n)\in \Theta (g(n)))$
* $f(n)=n^2+n,\ g(n)=0.000001n^3,\ f(n)\in O(g(n))$
* $f(n)=2^{2n}+1000,\ g(n)=4^n+n^{100},\ f(n)\in \Theta(g(n))$
* $f(n)=\log(n^{100}),\ g(n)=n\log n,\ f(n)\in O(g(n))$
* $f(n)=n\log n + 3^n + n,\ g(n)=n^2+n+\log n,\ f(n)\in \Omega(g(n))$
* $f(n)=n\log n+n^2,\ g(n)=\log n+n^2,\ f(n)\in \Theta(g(n))$
* $f(n)=n\log n,\ g(n)=(\log n)^2,\ f(n)\in \Omega (g(n))$

 

#### B. Analysing Runtime

2.1 The best case runtime is $\Theta (N)$,  the worst case runtime is $\Theta (M + N)$. Please note that `j` is initialised outside of the loop, which means that the nested loop only runs once.

```Java
public class Runtime1 {
    public static int N = 10;
    public static int M = 10;

    public static int ping(int i, int j) {
        return i + j;
    }

    public static void main(String[] args) {
        int j = 0;
        for (int i = N; i > 0; i--) {
            for (; j <= M; j++) {
                if (ping(i, j) > 64) {
                    break;
                }
            }
        }
    }
}
```



2.2 (a) The following code checks if all elements in an array have a duplicate. It retruns true if yes, false otherwise. The best case runtime is $\Theta (N\log N)$, the worst case runtime is $\Theta (N^2)$.

```Java
public class Runtime2 {
    public static boolean mystery(int[] array) {
        array = MergeSort.sort(array);
        int N = array.length;
        for (int i = 0; i < N; i += 1) {
            boolean x = false;
            for (int j = 0; j < N; j += 1) {
                if (i != j && array[i] == array[j])
                    x = true;
            }
            if (!x) {
                return false;
            }
        }
        return true;
    }
}
```

(b) Using an ADT, describe how to implement mystery() with a better runtime. Then, if we make the assumption an **int** can appear in the array at most twice, develop a solution using only constant memory

> A $\Theta (N)$ algorithm is to use a map and do key = element and value = number of appearances, then make sure all values are > 1. Uses $O(N)$ memory however. Can do constant space by sorting then going through, but sorting is generally in $O(N\log N)$ time.



2.3 The worst case, where `comeOn` always returns `true`, has a runtime complexity of $\Theta (NM)$. By contrast, if `comeOn` always returns `false`, the runtime complexity reduces to $\Theta (NlogM)$.

```Java
import java.util.Random;

public class Runtime3 {
    static int M = 10;
    static int N = 10;

    public static boolean comeOn() {
        Random seed = new Random();
        int randomnumber = seed.nextInt(10);
        if (randomnumber < 5) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j <= M; ) {
                if (comeOn()) {
                    j += 1;
                } else {
                    j *= 2;
                }
            }
        }
    }
}
```



#### C. Run Faster

> Given a target integer and a sorted array of distinct integers, design an algorithm to find if there are two integers in the array that sum up to the target integer.

```Java
public class OrderedTwoSum {
    public static boolean naiveFindSum(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] + A[j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean enhancedFindSum(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;
        boolean isRun = true;
        while (isRun == true) {
            if (A[left] + A[right] == x) {
                return true;
            } else if (A[left] + A[right] < x) {
                left++;
            } else {
                right--;
            }
            if (left > right) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test = {1, 3, 4, 7, 8, 9, 12, 14, 15, 19, 21, 24, 27, 28, 29, 36};
        System.out.println(naiveFindSum(test, 29));
        System.out.println(enhancedFindSum(test, 29));
    }
}
```

The `naiveFindSum` has a complexity of $\Theta (1)$ to $\Theta (N^2)$, whereas the `enhancedFindSum` has a complexity of $\Theta (1)$ to $\Theta (N)$. The latter method makes use of the fact that the array is sorted. Despite it deoes not traverse every possiblility, it is still guaranteed that the desired indices can be found. The rigorous mathematical proof is omitted here.



#### D. Using ADTs to Reduce Complexity

4.1 

> **Union**: Write the code that returns an array that is the union between two given arrays. The union of two arrays is a list that includes everything that is in both arrays, with no duplicates. Assume the given arrays do not contain duplicates. For example,theunionof {1, 2, 3, 4} and {3, 4, 5, 6} is {1, 2, 3, 4, 5, 6}.
>
> Hint: The method should run in $O(M+N)$ time where M and N is the size of each array.

```Java
import java.util.HashSet;

public class Union {
    public static int[] unionArray(int[] A, int[] B) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        for (int x : B) {
            set.add(x);
        }
        int[] returnedArray = new int[set.size()];
        int index = 0;
        for (int x : set){
            returnedArray[index] = x;
            index++;
        }
        return returnedArray;
    }
}
```

The answer makes use of the property that `set` only contains unique elements. A `set` variable of `HashSet` type is created fristly to store unique elements in `A` and `B`, then copy all the elements to a new array. The time complexity is of $\Theta (M+N)$.



4.2

> Intersect Now do the same as above, but find the intersection between both arrays. The intersection of two arrays is the list of all elements that are in both arrays. Again assume that neither array has duplicates. For example, the intersection of {1, 2, 3, 4} and {3, 4, 5, 6} is {3, 4}.

```Java
import java.util.HashSet;

public class Intersect {
    public static int[] intersectArray(int[] A, int[] B){
        HashSet<Integer> all = new HashSet<>();
        HashSet<Integer> intersect = new HashSet<>();
        for (int x : A){
            all.add(x);
        }
        for (int x : B){
            if(all.contains(x)){
                intersect.add(x);
            }
        }
        int index = 0;
        int[] returnedArray = new int[intersect.size()];
        for (int x : intersect){
            returnedArray[index] = x;
            index++;
        }
        return returnedArray;
    }
}
```

This question is quite similar to the previous one, with one more statement, `all.contains(x)`, to collect the interesection of the two arrays. Thus the time complexity is remained unchanged.



### 13. Asymptotic Analysis (Exam Preparation I)

For the original question in this discussion, please consult this [link](https://sp18.datastructur.es/materials/discussion/examprep07.pdf). Only answers will be included in this section.



#### A. A Simple Runtime Question

```java
public class SimpleRuntime {

    public static void f1(int N) { //Desired Runtime: Θ(N)
        for (int i = 1; i < N; i++) {
            System.out.println("hi");
        }
    }

    public static void f2(int N) { //Desired Runtime: Θ(logN)
        for (int i = 1; i < N; i = i * 2) {
            System.out.println("hi");
        }
    }

    public static void f3(int N) { //Desired Runtime: Θ(1)
        for (int i = 1; i < N; i = N) {
            System.out.println("hi");
        }
    }
}
```



#### B. A Slightly Harder Runtime Question

```Java
public class ASlightlyHarderRuntimeQuestion {
    public static void g(int N) {

    }
/*Of the order N^2logN*/
    public static void f4(int N) {
        if (N == 0) {
            return;
        }
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        g(N); // runs in Θ(N^2)
    }

    /*Of the order N*/
    public static void f5(int N, int M) {
        if (N < 10) {
            return;
        }
        for (int i = 0; i <= N % 10; i++) {
            f5(N / 10, M / 10);
            System.out.println(M);
        }
    }
}
```

According to [Hug's solution](https://sp18.datastructur.es/materials/discussion/examprep07sol.pdf), the solution should be $\Theta (N^2\log N)$. However, if we try the following as shown in the table. 

|                $N$                |  1   |  2   |  4   |  8   |  16  |
| :-------------------------------: | :--: | :--: | :--: | :--: | :--: |
| $C(N)$ of number of `g` is called |  0   |  1   |  5   |  21  |  85  |

$$
C(N)=(N^2-1)/3
$$

Therefore, the runtime complexity should be $C(N)*N^2\propto N^4$. The author believe there may be something wrong with Hug's solution.



The second question is far more tricky. If you consider the runtime complexity in terms of the *big theta* notation, you will finaly find that it largely depends on the input number. So we need to consider from the worst case circumstances, where `N%10 = 9`. In this worst case, $C(N) \propto N$. 

|                $N$                 |  9   |  99  |  99  | 9999 | 99999 |
| :--------------------------------: | :--: | :--: | :--: | :--: | :---: |
| $C(N)$ of number of `f5` is called |  1   |  11  | 111  | 1111 | 11111 |

By contrast, if you focus on $N=10^m$, you will only find this function to be of $\log N$ runtime complexity.

|                $N$                 |  10  | 100  | 1000 | 10000 | 100000 |
| :--------------------------------: | :--: | :--: | :--: | :---: | :----: |
| $C(N)$ of number of `f5` is called |  1   |  2   |  3   |   4   |   5    |

Therefore, in the wrost case, it has a runtime complexity of $O(N)$.



#### C. More Runtime Questions

```Java
public class MoreRunTimeQuestions {

    /*N^2.*/
    public static void p1(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j < N; j = j + 2) {
                System.out.println("hi !");
            }
        }
    }

    /*NlogN.*/
    public static void p2(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j < N; j = j * 2) {
                System.out.println("hi !");
            }
        }
    }

    /*N.*/
    public static void p3(int N) {
        if (N <= 1) return;
        p3(N / 2);
        p3(N / 2);
    }

    /*1*/
    public static void p4(int N) {
        int m = (int) ((15 + Math.round(3.2 / 2)) *
                (Math.floor(10 / 5.5) / 2.5) * Math.pow(2, 5));
        for (int i = 0; i < m; i++) {
            System.out.println("hi");
        }
    }

    /*N^2*/
    public static void p5(int N) {
        for (int i = 1; i <= N * N; i *= 2) {
            for (int j = 0; j < i; j++) {
                System.out.println("moo");
            }
        }
    }
}

```

Questions `p1`, `p2` and `p4` are easy to calculate the runtime complexity. For `p3`, we have 

|                $N$                 |  1   |  2   |  4   |  8   |  16  |
| :--------------------------------: | :--: | :--: | :--: | :--: | :--: |
| $C(N)$ of number of `p3` is called |  0   |  1   |  3   |  7   |  15  |

which is obviously a linear runtime complexity.



For the `p5` question, we have

|                 $N$                  |  1   |  2   |  4   |  8   |  16  |
| :----------------------------------: | :--: | :--: | :--: | :--: | :--: |
| $C(N)$ of number of `moo` is printed |  1   |  7   |  31  | 127  | 511  |

which has a quadratic runtime complexity.



#### D. Final Runtime Questions

```Java
public class FinalRuntimeQuestions {

    boolean h(int i) {
        return false;
    }
    
    /*p1 N^2*/
    int p1(int M) {
        return r(0, M);
    }

    int r(int i, int M) {
        if (i >= M) return 0;
        if (s(i) > 0) return i;
        return r(i + 1, M);
    }

    int s(int k) {
        if (k <= 0) return 0;
        if (h(k)) return k;
        return s(k - 1);
    }

    /*p2 N^2*/
    void p2(int M) {
        int L, U;
        for (L = U = 0; U < M; L += 1, U += 2) {
            for (int i = L; i < U; i += 1) {
                h(i);
            }
        }
    }
}
```

The answer for the two questions are the same, $\Theta (N^2)$. To create a worst case for `p1`, just let `h` function always return `false`, then nothing special. For the second question, it is a little bit the same as a general nested `for` loop, with a quadratic runtime complexity. For details, you may want to draw the $C(N)$ & $N$ tables for the two questions.



### 14. Asymptotic Analysis II

Please consult this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2014/Discussion%2014.pdf) for author's solutions of discussion 14. Hug's solution can be found [here](https://sp18.datastructur.es/materials/discussion/disc08sol.pdf).



### 15. Asymptotic Analysis (Exam Preparation II)

#### A. Warmup (Finding A Specific Element in An Array)

> Given the following method on a sorted array (`f1`), what is the worst-case runtime? There is an approach to make this algorithm faster. What is that approach and what is the worst-case runtime of the faster algorithm?

```Java
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class NumListQuestion {
    public static int[] removeDuplicates(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        final int len = arr.length;
        //changed end to len
        for(int i = 0; i < len; i++){
            set.add(arr[i]);
        }
        int[] whitelist = new int[set.size()];
        int i = 0;
        for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
            whitelist[i++] = it.next();
        }
        return whitelist;
    }

    /*The naive method of runtime complexity N.*/
    public static int f1(int i, int[] numList) {
        for (int j = 0; j < numList.length; j++) {
            // System.out.println(j);
            if (numList[j] == i) {
                return j;
            }
        }
        System.out.println("f1 Item not found.");
        return -1;
    }

    public static int f2(int i, int[] numList) {
        return f2(i, numList, 0, numList.length - 1);
    }

    /*Private helper method.*/
    private static int f2(int i, int[] numList, int start, int end) {
        /*The base case.*/
        if (end - start == 1) {
            if (numList[start] == i) {
                return start;
            } else if (numList[end] == i) {
                return end;
            } else {
                System.out.println("f2 Item not found.");
                return -1;
            }
        }
        /*Recursively find the position.*/
        int mid = start + (end - start) / 2;
        if (numList[mid] > i) {
            return f2(i, numList, start, end - (end - start) / 2);
        } else if (numList[mid] < i) {
            return f2(i, numList, start + (end - start) / 2, end);
        } else {
            return mid;
        }
    }

    /*The testing function.*/
    @Test
    public void testFindQuestion() {
        Random random = new Random();
        int[] numList = new int[200];
        for (int i = 0; i < numList.length; i++) {
            numList[i] = random.nextInt(200);
            ;
        }
        Arrays.sort(numList);
        numList = removeDuplicates(numList);
        for (int i = 0; i < numList.length; i++) {
            System.out.println("------------------");
            System.out.println("Round:" + i);
            System.out.println("Finding: " + numList[i]);
            org.junit.Assert.assertEquals(f1(numList[i], numList), f2(numList[i], numList));
            int randomNum = random.nextInt(200);
            System.out.println("Finding: " + randomNum);
            org.junit.Assert.assertEquals(f1(randomNum, numList), f2(randomNum, numList));
            System.out.println("------------------");
        }
    }
}

```

 For `f1`, the worst-case runtime is $\Theta (N)$, for the author's enhanced `f2` method, the worst-case runtime is $\Theta (\log N)$. Please note that `f2` is a recursive method with a private helper method having the same number but different input arguments.



**For the rest of the author's solutions for discussion 15, please consult this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2015/Discussion%2015.pdf).**



### 16. Disjoint Sets, Trees, Hashing

#### A. `isBSTGood`

```java
public class BSTTree {
    class TreeNode {
        int val;
        private TreeNode left;
        private TreeNode right;
    }

    private TreeNode root;

    public static boolean isBSTGood(TreeNode T) {
        return isBSTGood(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTGood(TreeNode T, int min, int max) {
        if (T == null) {
            return true;
        } else if (T.val > max || T.val < min) {
            return false;
        }
        return isBSTGood(T.left, min, T.val) && isBSTGood(T.right, T.val, max);
    }
}
```

This method use the parents value as a bound to recursively search for any invalid leaves. The original one fail to find out invalid leaves as shown in the example in the corresponding [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2016/Discussion%2016.pdf).



The rest solution of discussion 16 can be found in this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2016/Discussion%2016.pdf). Hug's soluton can be found [here](https://sp18.datastructur.es/materials/discussion/disc09sol.pdf).



### 17. Hashing (Exam Preparation)

Please consult this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2017/Discussion%2017.pdf) for author's solutions of discussion 17. Hug's solution can be found [here](https://sp18.datastructur.es/materials/discussion/examprep09sol.pdf).



### 18. Heaps, Traversals & Trees

Please consult this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2018/Discussion%2018.pdf) for author's solutions of discussion 18. Hug's solution can be found [here](https://sp18.datastructur.es/materials/discussion/disc10sol.pdf).



### 19. Heaps and Trees (Exam Preparation)

Please consult this [PDF](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Discussions/Discussion%2019/Discussion%2019.pdf) for author's solutions of discussion 19. Hug's solution can be found [here](https://sp18.datastructur.es/materials/discussion/examprep10sol.pdf).



## *Extra*: Labs

This section includes some key philosophy of the labs. Some environment-setting-based labs are skipped.



### 2. IntList (Lab 2)

The origianl tasks can be found in this link [Lab 2](https://sp18.datastructur.es/materials/lab/lab2/lab2). We will only introduce some tricky points in this lab.

#### A. Destructively Square the List

```java
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }
```

Since `L` is not a primitive type of Java, it is *pass by reference*, so we decstructively square the list.



#### B. Non-Destructive Square the List

```java
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }
```

We use a very delicate way by creating a new pointer `res`, so that the following operation does not change the value of `L`. We create a pointer `ptr` to perform the squaring so that we can return res correctly.



#### C. Non-Destructive Square (Recursive)

```java
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }
```

The recursive method uses `new` to create a new list, so `L` is never changed.



#### D. Destructively Catenate Lists

```java
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }

        IntList L;
        L = A;
        while (L.rest != null) { // XC: Why not L != null plus L = B ---> null is not an address, cannot pass by reference;
            L = L.rest;
        }
        L.rest = B;
        return A;
    }
```

This task is easy, the only tricky point is that we should set the while loop condition as `L.rest != null`, so that we can pass by reference. Otherwise, when `L = null`, the `null` value will be passed, which voids the function.



#### E. Non-Destructively Catenate Lists

```java
   public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        return new IntList(A.first, catenate(A.rest, B));
    }
```

Again, since we return a new `IntList`, the original variables cannot be changed. 



**In summary, use `new` and not using any statments with repect to `L.first` usually make sure that the method is non-destructive.**



### 3. Testing, Debugging (Lab 3)

Please refer this [website](https://sp18.datastructur.es/materials/lab/lab3/lab3) for general information. By the way, the Hug61B book did not mention how to use the *style checker*. The instruction is: Right click any files in IDEA and click *check style*, then the *CS61B Plugin* will do the job. 



The author wants to mention two very tricky points in this lab, that is the `reverse(IntList A)` function.

```Java
    public static IntList reverse(IntList A) {
        if (A == null || A.rest == null) {
            return null;
        }
        IntList current = A;
        IntList previous = null;
        while (current != null) {
            IntList nextCurrent = current.rest;
            current.rest = previous;
            previous = current;
            current = nextCurrent;
        }
        A = previous;
        return A;
    }
```

This function will destructively reverse the input. But it actually destory the input, not only reverse it. This point is rather tricky. This is because the statement `IntList current = A;` Let `current` points to the same address, and destories `A ` in the while loop. The statement `A = previous` cannot save our input, becauese this is actually a new `A`. This issue is very similar to [The Pokemon Problem](# A. The Pokemon Problem). In this lab, the author found that the only way out is to use deep copy at the statement `IntList current = A;`, but by doing so, the method will be non-destructive. Anyway, you can get full marks for this lab without all these hassle stuffs. The author just intends to demonstrate that the **GRoE** for classes in functions are far more complicated than we though.



The other one is that, `assertEquals` compares the value of two object. So the following method teachs the `assertEquals` how to compare two `IntList` by comparing elements, not comparing the object value (address).

```Java
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }
```



### 5. Drawing in Project 2 (Lab 5)

#### A. Drawing A Single Hexagon

The author's solution:

```Java
		private static void drawWorld(TETile[][] world, TERenderer ter) {
        ter.renderFrame(world);
    }

    private static TETile setTile(int tileNum) {
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.MOUNTAIN;
            case 3:
                return Tileset.TREE;
            case 4:
                return Tileset.SAND;
            case 5:
                return Tileset.GRASS;
            case 6:
                return Tileset.WATER;
            case 7:
                return Tileset.FLOOR;
            default:
                return Tileset.NOTHING;
        }
    }

    private static int[] calculatePosition(int length, int x, int y) {
        int[] startpoint = new int[2];
        startpoint[0] = x - length + 1;
        startpoint[1] = y + length;
        return startpoint;
    }

    public static void addHexagon(int length, int x, int y, TETile[][] world, TERenderer ter) {
        int tileNum = RANDOM.nextInt(8);
        int added = 0;
        int subtracted = 0;
        for (int i = y; i < y + length; i++) {
            for (int j = x + subtracted; j < x + length + added + subtracted; j++) {
                world[j][i] = setTile(tileNum);
            }
            added += 2;
            subtracted--;
        }
        int[] startpoint = calculatePosition(length, x, y);

        added = 0;
        subtracted = 0;
        for (int i = startpoint[1]; i < startpoint[1] + length; i++) {
            for (int j = startpoint[0] + subtracted; j < startpoint[0] + 3 * length - 2 + added + subtracted; j++) {
                world[j][i] = setTile(tileNum);
            }
            added -= 2;
            subtracted++;
        }
        drawWorld(world, ter);
    }
```

The `addHexagon` method firstly draws the bottom half and then draws the top half of a hexagon. To calculate the position of the start point of the top half hexagon, a private helper method, `calculatePosition`, was added. To enable random world, a `setTile` uses random number to decide the type of the region. The first helper method, `drawWorld` simply draw the world using a `TETile[][]` data type.



#### B. Drawing A Tesselation of Hexagons

Now, we want to draw a formatted pattern shown below, consisting of 19 hexagons:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/19.png?raw=true)

Since we already know how to draw one hexagons, we can just add some private helper methods to locate the start point (i.e. the input `x` and `y` in the `addHexagon` method). 

```Java
private static int[] topLeftPosition(int originalX, int originalY, int length) {
    int returnedValue[] = new int[2];
    returnedValue[0] = originalX - length * 2 + 1;
    returnedValue[1] = originalY + length;
    return returnedValue;
}

public static int[] topRightPosition(int originalX, int originalY, int length) {
    int returnedValue[] = new int[2];
    returnedValue[0] = originalX + length * 2 - 1;
    returnedValue[1] = originalY + length;
    return returnedValue;
}

public static int[] topPosition(int originalX, int originalY, int length) {
    int returnedValue[] = new int[2];
    returnedValue[0] = originalX;
    returnedValue[1] = originalY + 2 * length;
    return returnedValue;
}

public static void drawNHexagon(int length, int x, int y, TETile[][] world, TERenderer ter, int N) {
    for (int i = 0; i < N; i++) {
        addHexagon(length, x, y, world, ter);
        int[] newXY = topLeftPosition(x, y, length);
        x = newXY[0];
        y = newXY[1];
    }
}
```

Then we draw this pattern by group (3 + 4 + 5 + 4 + 3):

```Java
public static void main(String[] args) {
    int length = askForInput();
    TERenderer ter = new TERenderer();
    ter.initialize(WIDTH, HEIGHT);
    TETile[][] world = generateTETile(WIDTH, HEIGHT);
    /*Draw the 19 Hexagons*/
    drawNHexagon(length, startX, startY, world, ter, 3);
    int[] added = topRightPosition(startX, startY, length);
    drawNHexagon(length, added[0], added[1], world, ter, 4);
    added = topRightPosition(added[0], added[1], length);
    drawNHexagon(length, added[0], added[1], world, ter, 5);
    added = topPosition(added[0], added[1], length);
    drawNHexagon(length, added[0], added[1], world, ter, 4);
    added = topPosition(added[0], added[1], length);
    drawNHexagon(length, added[0], added[1], world, ter, 3);
}
```

Finally, a little bit interactivity is added to this program:

```Java
public static int askForInput() {
    Scanner reader = new Scanner(System.in);  // Reading from System.in
    System.out.print("Enter a number: ");
    int length = reader.nextInt(); // Scans the next token of the input as an int.
    reader.close();
    return length;
}
```

This method let the user to enter the length of the hexagon. An example output with length 5 is shown as follows:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Exmaple%2019.png?raw=true)



**Please note that we should always think about the math behind the program before we actually start to code.** For example, how to calculate the position of the start point? How to draw the bottom half and the top half of the hexagon? Then we just translate our math language into Java language. 



### 9. Tree Maps vs. Hash Maps

#### A. BSTMap

```Java
package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (root == null) {
            root = new Node(key, value);
            return root;
        }
        if (p == null) {
            p = new Node(key, value);
        } else {
            int cmp = key.compareTo(p.key);
            if (cmp < 0) {
                p.left = putHelper(key, value, p.left);
            } else if (cmp > 0) {
                p.right = putHelper(key, value, p.right);
            } else {
                size--;
                p.value = value;
            }
        }
        return p;


    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            return;
        }
        putHelper(key, value, root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        traverse(set, root);
        return set;
    }

    private void traverse(Set<K> set, Node n) {
        if (n == null) {
            return;
        }
        traverse(set, n.left);
        set.add(n.key);
        traverse(set, n.right);
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        V value = get(key);
        if (get(key) != null) {
            root = remove(key, root);
        }
        size--;
        return value;
    }

    private Node remove(K key, Node n) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(key, n.left);
        } else if (cmp > 0) {
            n.right = remove(key, n.right);
        } else {
            Node t = n;
            n = min(t.right);
            if (n != null) {
                n.right = deleteMin(t.right);
                n.left = t.left;
            }
            if(n == null){
                return t.left;
            }
        }
        return n;
    }

    private Node min(Node n) {
        if (n == null) {
            return null;
        }
        if (n.left != null) {
            return min(n.left);
        } else {
            return n;
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value){
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("S", 5);
        bstmap.put("E", 10);
        bstmap.put("A", 22);
        bstmap.put("R", 90);
        bstmap.put("C", 91);
        bstmap.put("H", 92);
        bstmap.put("M", 93);
        bstmap.remove("R");
    }
}

```

The `get`, `put`, `size` functions are straightforward. The `keySet` function, however, requires a bit more efforts to create a helper method called traverse, i.e. traverse all elements in the `BSTMap`.



The most tricky part is the `remove` method. We can somehow consult this figure as reference:

![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/BST%20Delete.png?raw=true)

For details, please consult [*Algs*](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Algorithms%204th%20Edition.pdf) 410.



#### B. MyHashMap

```Java
package lab9;

import java.util.*;

import edu.princeton.cs.algs4.*;
/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /**
     * Computes the hash function of the given key. Consists of
     * computing the hashcode, followed by modding by the number of buckets.
     * To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (!buckets[hash(key)].containsKey(key)) {
            size++;
        }
        buckets[hash(key)].put(key, value);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set returned = new HashSet();
        for (int i = 0; i < buckets.length; i++) {
            for (K key : buckets[i]) {
                returned.add(key);
            }
        }
        return returned;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (get(key) == null) {
            return null;
        } else {
            size--;
            return buckets[hash(key)].remove(key);
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) == null){
            throw new IllegalArgumentException();
        }
        if (get(key)!= value){
            return null;
        } else{
            size--;
            return buckets[hash(key)].remove(key);
        }
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
      /*  return new MyHashMapIterator();*/

    }

    private class MyHashMapIterator implements Iterator<K> {
        private int pos;
        private K[] keys = (K[]) new Objects[size];
        private Set<K> set;

        public MyHashMapIterator(){
            pos = 0;
            set = keySet();
            keys = (K[]) set.toArray();
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public K next() {
            K returned = keys[pos];
            pos++;
            return returned;
        }
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Double> test = new MyHashMap<>();
        for (int i = 0; i < 10; i++){
            test.put(i, i*2.0);
        }
        for (int i : test){
            System.out.println(i);
        }
     }
}
```

The `MyHashMap` is rather straightforward to implement as the `ArrayMap` has solved most of the issuse with respect to `get` and `put`. Please note that there are two ways to implement the `Iterator` method. The first one is to return an interator of `set`, whereas the second way is to create our own `MyHashMapIterator` method.



### 10. Priority Queues

This lab amis to use an array of type `T` to construct the data structure of  a priority queue. The abstract interface is:

```java
/**
 * Priority queue where objects have no intrinsic priority. Instead,
 * priorities are supplied as an argument during insertion and can be
 * changed.
 */
public interface ExtrinsicPQ<T> {
    /* Inserts an item with the given priority value. This is also known as "enqueue", or "offer". */
    public void insert(T item, double priority);
    /* Returns the minimum item. Also known as "min". */
    public T peek();
    /* Removes and returns the minimum item. Also known as "dequeue". */
    public T removeMin();
    /* Changes the priority of the given item. The behavior if the item doesn't exist is undefined. */
    public void changePriority(T item, double priority);
    /* Returns the number of items in the PQ. */
    public int size();
}

```



It is very likely that you find it easy to finish most part of this lab following the [guidance of the lab](https://sp18.datastructur.es/materials/lab/lab10/lab10). The solution of this lab is as follows:

```java
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value. Why do it this way? It
 * will be useful later on in the class...
 */
public class ArrayHeap<T> implements ExtrinsicPQ<T> {
    private Node[] contents;
    private int size;

    public ArrayHeap() {
        contents = new ArrayHeap.Node[16];

        /* Add a dummy item at the front of the ArrayHeap so that the getLeft,
         * getRight, and parent methods are nicer. */
        contents[0] = null;

        /* Even though there is an empty spot at the front, we still consider
         * the size to be 0 since nothing has been inserted yet. */
        size = 0;
    }

    /**
     * Returns the index of the node to the left of the node at i.
     */
    private static int leftIndex(int i) {
        return 2 * i;
    }

    /**
     * Returns the index of the node to the right of the node at i.
     */
    private static int rightIndex(int i) {
        return 2 * i + 1;
    }

    /**
     * Returns the index of the node that is the parent of the node at i.
     */
    private static int parentIndex(int i) {
        return i / 2;
    }

    /**
     * Gets the node at the ith index, or returns null if the index is out of
     * bounds.
     */
    private Node getNode(int index) {
        if (!inBounds(index)) {
            return null;
        }
        return contents[index];
    }

    /**
     * Returns true if the index corresponds to a valid item. For example, if
     * we have 5 items, then the valid indices are 1, 2, 3, 4, 5. Index 0 is
     * invalid because we leave the 0th entry blank.
     */
    private boolean inBounds(int index) {
        if ((index > size) || (index < 1)) {
            return false;
        }
        return true;
    }

    /**
     * Swap the nodes at the two indices.
     */
    private void swap(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        contents[index1] = node2;
        contents[index2] = node1;
    }


    /**
     * Returns the index of the node with smaller priority. Precondition: not
     * both nodes are null.
     */
    private int min(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        if (node1 == null) {
            return index2;
        } else if (node2 == null) {
            return index1;
        } else if (node1.myPriority < node2.myPriority) {
            return index1;
        } else {
            return index2;
        }
    }


    /**
     * Bubbles up the node currently at the given index.
     */
    private void swim(int index) {
        // Throws an exception if index is invalid. DON'T CHANGE THIS LINE.
        validateSinkSwimArg(index);
        if (!inBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (index != 1 && contents[index].priority() < contents[parentIndex(index)].priority()) {
            swap(index, parentIndex(index));
            swim(parentIndex(index));
        }
    }

    /**
     * Bubbles down the node currently at the given index.
     */
    private void sink(int index) {
        // Throws an exception if index is invalid. DON'T CHANGE THIS LINE.
        validateSinkSwimArg(index);
        if (!inBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (inBounds(leftIndex(index)) && inBounds(rightIndex(index))) {
            if (contents[index].priority() > contents[leftIndex(index)].priority()
                    || contents[index].priority() > contents[rightIndex(index)].priority()) {
                if (contents[leftIndex(index)].priority()
                        < contents[rightIndex(index)].priority()) {
                    swap(index, leftIndex(index));
                    sink(leftIndex(index));
                } else {
                    swap(index, rightIndex(index));
                    sink(rightIndex(index));
                }
            }
        }
        if (inBounds(leftIndex(index)) && contents[rightIndex(index)] == null) {
            if (contents[index].priority() > contents[leftIndex(index)].priority()) {
                swap(index, leftIndex(index));
                sink(leftIndex(index));
            }
        }

    }

    /**
     * Inserts an item with the given priority value. This is enqueue, or offer.
     * To implement this method, add it to the end of the ArrayList, then swim it.
     */
    @Override
    public void insert(T item, double priority) {
        /* If the array is totally full, resize. */
        if (size + 1 == contents.length) {
            resize(contents.length * 2);
        }
        Node newNode = new Node(item, priority);
        contents[size + 1] = newNode;
        size++;
        swim(size);

    }

    /**
     * Returns the Node with the smallest priority value, but does not remove it
     * from the heap. To implement this, return the item in the 1st position of the ArrayList.
     */
    @Override
    public T peek() {
        return contents[1].item();
    }

    /**
     * Returns the Node with the smallest priority value, and removes it from
     * the heap. This is dequeue, or poll. To implement this, swap the last
     * item from the heap into the root position, then sink the root. This is
     * equivalent to firing the president of the company, taking the last
     * person on the list on payroll, making them president, and then demoting
     * them repeatedly. Make sure to avoid loitering by nulling out the dead
     * item.
     */
    @Override
    public T removeMin() {
        if (size == 0) {
            return null;
        }
        T returnedItem = contents[1].item();
        if (size == 1) {
            contents[1] = null;
            size--;
            return returnedItem;
        }
        swap(1, size);
        contents[size] = null;
        size--;
        sink(1);
        return returnedItem;
    }

    /**
     * Returns the number of items in the PQ. This is one less than the size
     * of the backing ArrayList because we leave the 0th element empty. This
     * method has been implemented for you.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Change the node in this heap with the given item to have the given
     * priority. You can assume the heap will not have two nodes with the same
     * item. Check item equality with .equals(), not ==. This is a challenging
     * bonus problem, but shouldn't be too hard if you really understand heaps
     * and think about the algorithm before you start to code.
     */
    @Override
    public void changePriority(T item, double priority) {
        for (int i = 1; i < contents.length; i++) {
            if (contents[i].item().equals(item)) {
                double previousPriority = contents[i].myPriority;
                contents[i].myPriority = priority;
                if (previousPriority > priority) {
                    swim(i);
                } else {
                    sink(i);
                }
                break;
            }
        }

    }

    /**
     * Prints out the heap sideways. Provided for you.
     */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getNode(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = rightIndex(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getNode(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getNode(index) + "\n";
            int leftChild = leftIndex(index);
            if (getNode(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }


    /**
     * Throws an exception if the index is invalid for sinking or swimming.
     */
    private void validateSinkSwimArg(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Cannot sink or swim nodes with index 0 or less");
        }
        if (index > size) {
            throw new IllegalArgumentException("Cannot sink or swim "
                    + "nodes with index greater than current size.");
        }
        if (contents[index] == null) {
            throw new IllegalArgumentException("Cannot sink or swim a null node.");
        }
    }

    private class Node {
        private T myItem;
        private double myPriority;

        private Node(T item, double priority) {
            myItem = item;
            myPriority = priority;
        }

        public T item() {
            return myItem;
        }

        public double priority() {
            return myPriority;
        }

        @Override
        public String toString() {
            return myItem.toString() + ", " + myPriority;
        }
    }


    /**
     * Helper function to resize the backing array when necessary.
     */
    private void resize(int capacity) {
        Node[] temp = new ArrayHeap.Node[capacity];
        for (int i = 1; i < this.contents.length; i++) {
            temp[i] = this.contents[i];
        }
        this.contents = temp;
    }

    @Test
    public void testIndexing() {
        assertEquals(6, leftIndex(3));
        assertEquals(10, leftIndex(5));
        assertEquals(7, rightIndex(3));
        assertEquals(11, rightIndex(5));

        assertEquals(3, parentIndex(6));
        assertEquals(5, parentIndex(10));
        assertEquals(3, parentIndex(7));
        assertEquals(5, parentIndex(11));
    }

    @Test
    public void testSwim() {
        ArrayHeap<String> pq = new ArrayHeap<>();
        pq.size = 7;
        for (int i = 1; i <= 7; i += 1) {
            pq.contents[i] = new ArrayHeap<String>.Node("x" + i, i);
        }
        // Change item x6's priority to a low value.

        pq.contents[6].myPriority = 0;
        System.out.println("PQ before swimming:");
        System.out.println(pq);

        // Swim x6 upwards. It should reach the root.

        pq.swim(6);
        System.out.println("PQ after swimming:");
        System.out.println(pq);
        assertEquals("x6", pq.contents[1].myItem);
        assertEquals("x2", pq.contents[2].myItem);
        assertEquals("x1", pq.contents[3].myItem);
        assertEquals("x4", pq.contents[4].myItem);
        assertEquals("x5", pq.contents[5].myItem);
        assertEquals("x3", pq.contents[6].myItem);
        assertEquals("x7", pq.contents[7].myItem);
    }

    @Test
    public void testSink() {
        ArrayHeap<String> pq = new ArrayHeap<>();
        pq.size = 7;
        for (int i = 1; i <= 7; i += 1) {
            pq.contents[i] = new ArrayHeap<String>.Node("x" + i, i);
        }
        // Change root's priority to a large value.
        pq.contents[1].myPriority = 10;
        System.out.println("PQ before sinking:");
        System.out.println(pq);

        // Sink the root.
        pq.sink(1);
        System.out.println("PQ after sinking:");
        System.out.println(pq);
        assertEquals("x2", pq.contents[1].myItem);
        assertEquals("x4", pq.contents[2].myItem);
        assertEquals("x3", pq.contents[3].myItem);
        assertEquals("x1", pq.contents[4].myItem);
        assertEquals("x5", pq.contents[5].myItem);
        assertEquals("x6", pq.contents[6].myItem);
        assertEquals("x7", pq.contents[7].myItem);
    }


    @Test
    public void testInsert() {
        ArrayHeap<String> pq = new ArrayHeap<>();
        pq.insert("c", 3);
        assertEquals("c", pq.contents[1].myItem);

        pq.insert("i", 9);
        assertEquals("i", pq.contents[2].myItem);

        pq.insert("g", 7);
        pq.insert("d", 4);
        assertEquals("d", pq.contents[2].myItem);

        pq.insert("a", 1);
        assertEquals("a", pq.contents[1].myItem);

        pq.insert("h", 8);
        pq.insert("e", 5);
        pq.insert("b", 2);
        pq.insert("c", 3);
        pq.insert("d", 4);
        System.out.println("pq after inserting 10 items: ");
        System.out.println(pq);
        assertEquals(10, pq.size());
        assertEquals("a", pq.contents[1].myItem);
        assertEquals("b", pq.contents[2].myItem);
        assertEquals("e", pq.contents[3].myItem);
        assertEquals("c", pq.contents[4].myItem);
        assertEquals("d", pq.contents[5].myItem);
        assertEquals("h", pq.contents[6].myItem);
        assertEquals("g", pq.contents[7].myItem);
        assertEquals("i", pq.contents[8].myItem);
        assertEquals("c", pq.contents[9].myItem);
        assertEquals("d", pq.contents[10].myItem);
    }


    @Test
    public void testInsertAndRemoveOnce() {
        ArrayHeap<String> pq = new ArrayHeap<>();
        pq.insert("c", 3);
        pq.insert("i", 9);
        pq.insert("g", 7);
        pq.insert("d", 4);
        pq.insert("a", 1);
        pq.insert("h", 8);
        pq.insert("e", 5);
        pq.insert("b", 2);
        pq.insert("c", 3);
        pq.insert("d", 4);
        System.out.println(pq);
        String removed = pq.removeMin();
        System.out.println(pq);
        assertEquals("a", removed);
        assertEquals(9, pq.size());
        assertEquals("b", pq.contents[1].myItem);
        assertEquals("c", pq.contents[2].myItem);
        assertEquals("e", pq.contents[3].myItem);
        assertEquals("c", pq.contents[4].myItem);
        assertEquals("d", pq.contents[5].myItem);
        assertEquals("h", pq.contents[6].myItem);
        assertEquals("g", pq.contents[7].myItem);
        assertEquals("i", pq.contents[8].myItem);
        assertEquals("d", pq.contents[9].myItem);
    }

    @Test
    public void testInsertAndRemoveAllButLast() {
        ExtrinsicPQ<String> pq = new ArrayHeap<>();
        pq.insert("c", 3);
        pq.insert("i", 9);
        pq.insert("g", 7);
        pq.insert("d", 4);
        pq.insert("a", 1);
        pq.insert("h", 8);
        pq.insert("e", 5);
        pq.insert("b", 2);
        pq.insert("c", 3);
        pq.insert("d", 4);
        System.out.println(pq);
        int i = 0;
        String[] expected = {"a", "b", "c", "c", "d", "d", "e", "g", "h", "i"};
        while (pq.size() > 1) {
            assertEquals(expected[i], pq.removeMin());
            System.out.println(pq);
            i += 1;
        }
    }

    @Test
    public void testChangePriority() {
        ExtrinsicPQ<String> pq = new ArrayHeap<>();
        pq.insert("c", 3);
        pq.insert("i", 9);
        pq.insert("g", 7);
        pq.insert("d", 4);
        pq.insert("a", 1);
        pq.insert("h", 8);
        pq.insert("e", 5);
        pq.insert("b", 2);
        System.out.println(pq);
        pq.changePriority("c", 0);
        System.out.println(pq);
        pq.changePriority("c", 10);
        System.out.println(pq);
        pq.changePriority("c", 6);
        System.out.println(pq);
        pq.changePriority("h", 0);
        System.out.println(pq);
    }
}
```

Only the method, `changePriority`, requires some ingenuity. But if is not an hard task as soon as you realise that you only need to find the element and let it swim or sink in the heap. Nothng more, just traverse the array to find the key and let it go to the right place. 



## *Extra*: Projects

*This section includes some key philosophy of the projects. Project 0 is skipped.*



### 1. DLList & Array (Project 1a)

The original problem can be found in this link [Project 1a](https://sp18.datastructur.es/materials/proj/proj1a/proj1a). The following pieces of code demonstrates the key of this project.



Since the users can never access the real array,  we have to find the equivalent index in the real array of the desired index. 

```Java
   private int realIndex2itemIndex(int x) {
        return (x + nextFirst + 1) % items.length;
    }
```

The circular data structure makes it very tricky to perform such a operation. But the key is that the double ends of the linear data structure are sticked  to form a circular data structure. Therefore, the remainder of an index reveals its true nature. 



Once we have such a function, everything becomes straightforward. So everytime we expand or compress out array, we just move all the items to the very front. With the help of our `realIndex2itemIndex(int x)` function, everything goes smoothly.

```Java
    private void expandSize() {
        T[] expandedArray = (T[]) new Object[items.length * 2];
        for (int i = 0; i < size; i++) {
            expandedArray[i] = items[realIndex2itemIndex(i)];
        }
        items = expandedArray;
        nextFirst = expandedArray.length - 1;
        nextLast = size;
    }
```

```Java
    private void compressSize() {
        T[] compressedArray = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++) {
            compressedArray[i] = items[realIndex2itemIndex(i)];
        }
        items = compressedArray;
        nextFirst = compressedArray.length - 1;
        nextLast = size;
    }
```



The above three functions are the essence of project 1a. To be more specific, the `realIndex2itemIndex(int x)` function exploits the invariant in the circular data structure.



### 2. Applying Testing (Project 1b)

The original project can be found in this link [Project 1b](https://sp18.datastructur.es/materials/proj/proj1b/proj1b). This project is quite straightforward. The author only noticed one thing that worthwhile to mention.



So basically the interface, `CharacterComparator` looks like:

```Java
/**
 * This interface defines a method for determining equality of characters.
 */
public interface CharacterComparator {
    /**
     * Returns true if characters are equal by the rules of the implementing class.
     */
    boolean equalChars(char x, char y);
}
```

This interface does not have a constructor. Someone who has prior C++ experience may notice that, this is what we called *abstract class*. So unlike the [`Animal`](#A. Creating Cats) class, which the default constructor is missing, that leads to issue in the extended class, `Cat`. A class that implement an *abstract class* can have its own constructor even if the *abstract class* does not have one.



The remaining part of this project is straightforward. Just follow the instruction and you will get full marks.



### 3. Autograding

The original project can be found in this link [Project 1 Gold](https://sp18.datastructur.es/materials/proj/proj1gold/proj1gold). This project is not that challenging as described in the instruction.

```java
import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandomCall() {
        String test = "";
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        Integer expected = 0;
        Integer actual = 0;
        for (int i = 0; i < 200; i++) {
            /*System.out.println("Test: " + (1+i));*/
            double randomNumber = StdRandom.uniform();
            if (randomNumber >= 0 && randomNumber <= 0.25) {
                solution.addFirst(i);
                student.addFirst(i);
                expected = solution.get(0);
                actual = student.get(0);
                test += "addFirst(" + i + ")" + "\n";
            } else {
                if (randomNumber > 0.25 && randomNumber <= 0.5) {
                    solution.addLast(i);
                    student.addLast(i);
                    expected = solution.get(0);
                    actual = student.get(0);
                    test += "addLast(" + i + ")" + "\n";
                } else {
                    if (solution.size() > 0 && student.size() > 0) {
                        if (randomNumber > 0.5 && randomNumber <= 0.75) {
                            expected = solution.removeFirst();
                            actual = student.removeFirst();
                            test += "removeFirst()\n";
                        } else {
                            if (randomNumber > 0.75 && randomNumber <= 1) {
                                expected = solution.removeLast();
                                actual = student.removeLast();
                                test += "removeLast()\n";
                            }
                        }
                    }
                }
            }
            assertEquals(test, expected, actual);
        }
    }
}
```

Just try to be familiar with generating random number and concatenating `String`. Remember to initialise varibles before the `for` loop, i.e. it is not very often to define a variable inside loops.



## *Extra*: Examinations

*This section includes all exam questions.*

 

### I. [Midterm I](https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1.pdf)

#### 1. Static Data

The question:

> (a) Consider the class shown below. Next to the lines with blanks, write the result of the print statement. No syntax errors or runtime errors occur.

```Java
public class Dada {
    private static String[] rs;

    /**
     * Prints out the given array, i.e. if d contains two Strings
     * with names "alice" and "bob", this method will print "alice bob ".
     */
    private static void printStringArray(String[] s) {
        for (int i = 0; i < s.length; i += 1) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};
        String[] X = twod[1];
        printStringArray(X); 
        Dada.rs = X;
        String[] Y = Dada.rs;
        Y = new String[]{d, c};
        Dada.rs[1] = "eve";
        printStringArray(Dada.rs); 
        printStringArray(Y); 
        printStringArray(twod[0]);
        printStringArray(twod[1]);
    }
}
```

The answer:

```Java
carol dan 
carol eve 
dan carol 
alice bob 
carol eve 
```

Please note that `Array` is a data type that *pass-by-reference*. So the last statment `printStringArray(twod[1]);` outputs `carol eve `. This is because the value of `twod` is modified by the statement `Dada.rs[1] = "eve";`. The final box diagram is shown as follows (with `Data.rs = [carol, eve]`, and it has exactlly the same address as `X`):![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/DadaDiagram.png?raw=true)



> (b) Suppose we add new methods to Dada called fillOne and fillMany and replace main as shown below. Fill in the print statements. The Dada class is otherwise unchanged.

```Java
public class Dada {
    private static String[] rs;

    /**
     * Prints out the given array, i.e. if d contains two Strings
     * with names "alice" and "bob", this method will print "alice bob ".
     */
    private static void printStringArray(String[] s) {
        for (int i = 0; i < s.length; i += 1) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
    }

    private static void fillMany(String[] d) {
        System.arraycopy(Dada.rs, 0, d, 0, d.length);
    }

    private static void fillOne(String d) {
        d = Dada.rs[0];
    }

    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};
        Dada.rs = new String[]{"fritz", "gritz"};
        String[] X = twod[0];
        printStringArray(X);
        fillOne(X[0]);
        printStringArray(X);
        fillMany(X);
        printStringArray(X);
    }
}
```

The answer:

```
alice bob 
alice bob 
fritz gritz 
```

Please note that the statment, `fillOne(X[0]);`, changes nothing, as the input argument is an integer, which is pass by value. The other statment, `fillMany(X);`, however, changes the value of `X`. This is because `X` is an array, which *pass by reference* in Java. So the final output is `fritz gritz`.



#### 2. What it do

The question:

> (a) Describe as succinctly as possible what this method does when executed for all possible values of x. If the behavior is different depending on x, describe the behavior in every interesting case. Remember that integer division in Java rounds down, i.e. 3/2 yields 1.

```Java
public static int f(int x) {
 if (x == 1) {
 return 1;
 }
 return 2 * f(x / 2);
```

This is a recursive function. The correct answer is :

> For x >= 1, prints out the largest power of 2 that is smaller than x. For x < 1, goes into infinite loop.

Recursive functions is of the most trickiest method of all. Firstly, we should be able to identify whether a function is recursive or not. A golden rule for this is to check the `return` statement or the function body. If there is a self-call, then it is very likely to be. a recursive function. It is not that difficult to figure out the behaviour of this specific function, just try some values. But remember, for a recursive function, *the base case is always of the most important!* So if any input fails to go into the base class, this will result in a infinite loop. To delve into the behavirou of this function, try the following code with the Java debugger:

```Java
public class second_a {
    public static int f(int x) {
        if (x == 1) {
            return 1;
        }
        int a = 2 * f(x / 2);
        return a;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++){
            int x = f(i);
            System.out.println(x);
        }
    }
}
```



> (b) Draw a box and pointer diagram that shows the result of executing the following two lines of code. If any objects are not referenced by anything else (i.e. are garbage collected), you may omit drawing them if you prefer. If you need it, the IntList definition is on page 7. If g never finishes because it gets stuck in an infinite loop, write “Infinite Loop” instead of drawing a diagram.

```Java
public class second_b {
    public static void g(IntList x) {
        if (x == null) { return; }
        g(x.rest);
        x.rest = x;
    }

    public static void main(String[] args) {
        IntList L = IntList.of(3, 4, 5);
        g(L);
    }
}
```

Again, this is a recursive function. A self-calling can be easily found inside the function body. So the function goes to the end of the `IntList` and always makes the `node ` points to itself. So the final solution is:

![image](https://github.com/XChen1998/CS61B_Notes/blob/main/Figures/2_b.png?raw=true)

Please note that, the statement `x.rest = x` is called everytimes righ after the `g` function finished. So all the nodes will be changed to point themselves.



#### 3. KeyGate

The question and the solution can be found in this [link](https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1.pdf) (quesetion 3. KeyGate):

```Java
public class Fingerprint {...}
public class Key { ... }
public class SkeletonKey extends Key { ... }
public class StandardBox { public void unlock(Key k) { ... } } // UK
public class BioBox extends StandardBox {
 		public void unlock(SkeletonKey sk) { ... } // USK
 		public void unlock(Fingerprint f) { ... } // UF
}
```

```Java
public class main {

    public static void doStuff(Key k, SkeletonKey sk, Fingerprint f) {
        StandardBox sb = new StandardBox();
        StandardBox sbbb = new BioBox();
        BioBox bb = new BioBox();
        sb.unlock(k); // UK
        sbbb.unlock(k); // UK
        bb.unlock(k); // UK
        sb.unlock(sk); // UK, sk is a key
        sbbb.unlock(sk); // UK, sk is a key.
        bb.unlock(sk); // USK
        sb.unlock(f); // Compile error, StandardBox cannot take Fingerprint as an input argument
        sbbb.unlock(f); // Compile error, the father classs does not have such a method
        bb.unlock(f); // UF
        bb = (BioBox) sbbb; // No error, bb can be a container of sbbb
        ((StandardBox) bb).unlock(sk); // UK, sk is a key     
        ((StandardBox) sbbb).unlock(sk); // UK, sk is a key
        ((BioBox) sb).unlock(sk); // run-time error, the compiler does not know what type sb is
    }

    public static void main(String[] args) {
        Key k = new Key();
        SkeletonKey sk = new SkeletonKey();
        Fingerprint f = new Fingerprint();
        doStuff(k, sk, f);
    }
}
```



The aurthor's solution is included in the second pieces of code. The following table shows the *static type* and *dynamic type* of all variables. 

|              |     sb      |    sbbb     |   bb    |
| :----------: | :---------: | :---------: | :-----: |
| Static Type  | StandardBox | StandardBox | BioxBox |
| Dynamic Type | StandardBox |   BioBox    | BioxBox |

**Please note that `sk` is a key, so `StandardBox` can take it as an input argument!!!** The last statment `((BioBox) sb).unlock(sk);` produces a run-time error because the compiler does not know if `sb` container really contais `BioBox`, as this is theoretically possible. So when the code is running, Java finally realises that `sb` contains a `StandardBox`, which lead to `run-time` error. In summary, **Java compiler always uses static type to check variable types, and determines which fuhaahhction or its  to implement. But dynamic type will implements the correct `@Override` functions, for more details, please consult this [section](# D. *Inheritance Misery (Extra*)).



#### 4. Sans

The question and the solution can be found in this [link](https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1.pdf) (quesetion 4. Sans):

> (a) Non-destructively creates a copy of x that contains no y. (Array version)

```Java
    public static int[] sans(int[] x, int y) {
        int[] xclean = new int[x.length];
        int c = 0;
        for (int i = 0; i < x.length; i += 1) {
            if (x[i] != y)  {
                xclean[c] = x[i];
                c++;
            }
        }
        int[] r = new int[c];
        System.arraycopy(xclean, 0, r, 0, c);
        return r;
    }
```

This question is relevant straightforward, we create a `xclean` variable to store all non-x elements. We use `c` to records the number of non-x elements, which is also helpful for the assignment `xclean[c] = x[i]`.  Since `xclean` is already free of `x`, why we need a new array, `r`? The answer is that arrays are immutable, we cannot change its length to `c`. Therefore, we create a new array `c` to finalise our returned variable.



> (b) Non-destructively creates a copy of x that contains no y. (IntList version)

```Java
  public static IntList ilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        if (x.first == y) {
            return ilsans(x.rest, y);
        }
        return new IntList(x.first, ilsans(x.rest, y));
    }
```

The original question only has a few blank lines to be filled. So we have to write a recursive method. Please keep in mind, **recursive method always starts from the base case**. So if `x  null`,  we return `null`. How about we the node's item is `y`? Then we should return the next node. But how do we 100% sure that the next node is not `y`? Fine, let us just use the `ilsans` function again to check if the next node is `y`. Here we get the `return ilsans(x.rest, y);` statement. The last case, what if the the current node's item is not `y`? Let us just return a **new** `IntList` to non-destructively construct a new `IntList` with this item and check the rest of the `IntList`. In this method, we never use statment like `x.rest = someNode`, so it is non-destructive.



> (c) Destructively creates a copy of x that contains no y. You may not use **new**. (IntList version)

```java
    public static IntList dilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        x.rest = dilsans(x.rest, y);
        if (x.first == y) {
            return x.rest;
        }
        return x;
    }
```

Again, here we need to design a recursive method. The base case is `x  null` and we should return `null`. In this question, we are required to destructively perfrom the operation. The line `x.rest = dilsans(x.rest, y);` does this thing. This statment is called repeatedly untill we go to the end of the `IntList`. All the nodes are stored in a specific function, and we want to return `x.rest` if `x.firs == y`, whereas we should return `x` itself if `x.first != y`. The key point of this method is that we traverse every nodes and store them in every calls of functions. Then we are able to rebuild the link between theses element. This is rather tricky but somehow very similar to the [`reverse`](b. `reverse`  Method of `SLList`) problem.



#### 5. A Needle in ArrayStack

Please refere the [midterm paper](https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1.pdf) for the original question.



The solution for (b)

```Java
import java.util.Arrays;

public interface Stack<Item> {
    void push(Item x); // places an item on “top” of the stack
    Item pop();        // removes and returns “top” item of the stack
    int size();        // returns the number of items on the stack
    public default void purge(Item x) { 
        ArrayStack<Item> newArrayStack = new ArrayStack<>();

        while(this.size() > 0){
            Item current = this.pop();
            if (!current.equals(x))
                newArrayStack.push(current);
        }
        while(newArrayStack.size() >0){
            this.push(newArrayStack.pop());
        }
    }

    public default void purgeElegant(Item x) {
        if(this.size() == 0){
            return;
        }
        Item top = this.pop();
        this.purge(x);
        if(!top.equals(x)){
            this.push(top);
        }
    }
}
```

The interface with a completed `purge` and an elegant version of `purge`. The `purge` method uses a new `ArrayStack`, whereas the elegant version uses a recusive method. The key ideas, however, are the same, that is to remove elements from behind and fill in the array again from the front. 

 

The solution for (a)

```Java
public class ArrayStack<Item> implements Stack<Item> {
    private Item[] items;
    private int size;

    public ArrayStack() { // initial array size is 8 items = (Item[]) new Object[8];
        items = (Item[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) { // resizes array to given capacity
        Item[] newItems = (Item[]) new Object[capacity * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    public void push(Item x) {
        if (usageRatio() == 1) {
            resize(items.length * 2);
        }
        items[size] = x;
        size++;
    }

    public Item pop() { // returns null if stack is empty
        if (size == 0) {
            return null;
        }
        if (usageRatio() < 0.25 && items.length > 8) {
            resize(items.length / 2);
        }
        Item x = items[size - 1];
        items[size - 1] = null;
        size--;
        return x;

    }

    public int size() {
        return size;
    }

    private double usageRatio() {
        return ((double) size()) / items.length;
    }
}
```

Nothing special, this question is very similar to the `ArrayDeque` class that has been introduced in the [project 1a](# 1. DLList & Array (Project 1a)) , without the annoying circular data structure.



#### 6. Combine

> The Combine.combine method takes a ComFunc and an integer array x and uses the ComFunc to “combine” all the items in x. For example, if we have an implementation of ComFunc called Add that adds two integers, and we call combine using the Add class on the array {1, 2, 3, 4}, the result will be 10, since 1 + 2 + 3 + 4 is 10.

> (a) Fill in the combine method below. If the array is of length 0, the result should be 0, and if the array is of length 1, the result should be the number in the array. For full credit use recursion. For 75% credit, you may use iteration. **You may create a private helper function in the space provided.** 

The solution:

```Java
public class combine {
    public static int combine(ComFunc f, int[] x) {

        if (x.length == 0) {
            return 0;
        }
        if (x.length == 1) {
            return x[0];
        }
        int sum = f.apply(x[0], x[1]);
        return combine(f, x, 2, sum);
    }
// your private helper function cannot create new arrays (too slow)

    private static int combine(ComFunc f, int[] x, int position, int sum) {

        if (position == x.length) {
            return sum;
        }
        sum = f.apply(x[position], sum);
        return combine(f, x, position + 1, sum);

    }
}
```

```Java
public class Add implements ComFunc {
    public int apply(int a, int b) {

        return a + b;
    }
}
```

Again, we need a recursive function. Again, this question is rather ambiguous, because the `Add` class is provided in question (b).  The first `combine` function considers two essential cases, and let the helper method to deal with the normal cases in a recursive way. The input arguement `f` is entirely for calling `apply`. To be honest, the author cannot see any advantage of this recursive method campared with an iteration method. Perhaps it is more elegant? Anyway, do not foget that the helper method ends when `position == x.length` instead of `position + 1`. 



> (b) Suppose we have a method that adds two numbers, as shown below. 
>
> public class Add implements ComFunc { 
>
> ​		public int apply(int a, int b) { return a + b; } 
>
> } 
>
> Fill in the method below so that it prints out the correct result. You may use your answer from part a. Even if you left part a blank or think it be incorrect, you can assume that everything works as expected.

The solution:

```Java
public static int sumAll(int[] x) { // sumAll is not a member of Combine
    return combine.combine(new Add(), x);
}
```

Nothing tricky, just remember that `sumAll` is not a member of `combine`, so we need to use`combine.combine` to call the `combine` function, and we need to create a new `Add()` class, instead of a `ComFunc` interface. Yes, an interface cannot be initialised.



#### 7. The Downside of Default

Please consult this [link](https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1.pdf) for the original question.

For question (a), the solution is:

```Java
default public void plusEquals(ListOfInts x) { // assume x is non-null
    if (x.size() != this.size()) {
        return;
    }
    for (int i = 0; i < this.size(); i++) {
        this.set(this.get(i), x.get(i));
    }
}
```

Nothing difficult, but please do not forget to use `this.get(i)`, instead of stupidly using `i`. The author, unfortunately, made this mistake :(.



For question (b), the solution is:

```Java
public void plusEquals(DLListOfInts x) {
    if (this.size() != x.size() || x.size == 0) {
        return;
    }
    IntNode X = x.sentinel.next;
    for (IntNode p = sentinel.next; p != sentinel; p = p.next) {
        p.item += X.item;
        X = X.next;
    }
}
```

Again, nothing tricky. Please do consider the special case, which is `size == 0`. The author pathetically made this mistake :(.



> (c) The method sumOfLists given below is supposed to take an array of DLListOfInts and returns a DLListOfInts that is equal to the element-wise sum of all of the lists. For example if the array contains three lists that hold [2, 2, 2], [1, 2, 3], and [3, 3, 3], respectively, the method should return a DLListOfInts that contains [6, 7, 8]. The method should be non-destructive.

```Java
public class PartC {
    /**
     * Non-destructively computes the sum of the given lists. Assumes
     * that all lists are of the same length and that none are null.
     */
    public static DLListOfInts sumOfLists(DLListOfInts[] lists) {
        ListOfInts result = lists[0];
        for (int i = 1; i < lists.length; i += 1) {
            result.plusEquals(lists[i]);
        }
        return result;
    }
}
```

The answer is:

1. This method is destructive.
2. This method cannot compile, since `result` is a `List ofInts`, which is a father class of `DLListofInts`. So it cannot return an appropriate variable.
3. This method cannot deal with zero-length input. (zero-length input is not `null`, cunning hug)
4. This method uses the default `plusEquals` method. Actually, This can be avoid by declare `result` as `DLListofInts`.





### II. Midterm II

Please consult this [link](https://github.com/XChen1998/Work/blob/main/Courses/CS61B_2018_Spring_UCB/CS61B_Readings/Exams/Midterm%202/CS61B%20MT2.pdf) for midterm 2 solutions. Please note that the original test paper is missing for some reason in the main [website](https://sp18.datastructur.es) of this course. For detailed solutions, you may find this [YouTube video](https://www.youtube.com/watch?v=nMZn4EV0gGw) very useful.
