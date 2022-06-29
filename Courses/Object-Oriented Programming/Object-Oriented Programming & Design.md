# Object-Oriented Programming & Design

**Author**: Ziggy (Ruofan) Chen, MSCS@Columbia University in the City of New York '23, xinghe.c at columbia.edu

Jun. 2022

**Statement**: Please note that this work is for reference only. It briefly introduce the philosophy of object-oriented programming and design (OOP/OOD), which is an essential part of an entry level SDE interview. In this work, we will mainly use *OOP*, as there is no significant differece between OOP and OOD.



## I. What is OOP?

The key philosophy of OOP includes *encapsulation, inheritance and polymorphism*. We will now introduce each one of them.



### 1. Encapsulation (封装)

The very basic idea of encapsulation is very much like what we have already introduced in my CS61B notes: 

> #### E. Encapsulation
>
> The idea of encapsulation is make your class like a pre-defined tool. A user does not need to know any knowledge of the information inside the class. ![image](https://github.com/XChen1998/Figure_Library/blob/main/Work/Courses/CS61B_2018_Spring_UCB/Encapsulation.png?raw=true)
>
> Take our `ArrayDeque` as an example, the hidden `Item` variable is almost not readable, but with our helper method, the user can manipulate an `ArrayDeque` easily. In the author's opinion, **the philosophy of encapsulation is to make our class easy to use (since we have helper methods for users), at the same time, hard to break (since we do not give users access to the hidden information. i.e. we add many `private` access modifier)**.