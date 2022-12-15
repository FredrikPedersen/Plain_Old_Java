# Java SE 11 Developer 1Z0-819 Course

Notes taken from [Tim Buchalka's Java SE 11 Developer Exam course](https://tietoevry.udemy.com/course/java-se-11-developer-1z0-819-ocp-course-part-1/learn/lecture/23038174#overview)

[1 Packages and Imports](#1-packages-and-imports)  
[2 Data Types and Strings](#2-data-types-and-strings)  
 - [2.1 Declaring Primitive Types](#declaring-primitive-types)  
 - [2.2 Local Variable Initialization](#local-variable-initialization)  
 - [2.3 Narrowing and Widening](#narrowing-and-widening)
 - [2.4 Casting](#casting)
 - [2.5 Scope](#scope)
 - [2.6 Local Variable Type Inference](#local-variable-type-inference)
 - [2.7 Strings](#strings)
 - [2.8 String Concatenation and Manipulation](#string-concatenation-and-manipulation)
 - [2.9 StringBuilder](#stringbuilder)  

[3 Operators and Decision Constructs](#3-operators-and-decision-constructs)
 - [3.1 Pre- and Postfix Increment and Decrement Operators](#pre--and-postfix-increment-and-decrement-operators)
 - [3.2 Unary Plus and Minus](#unary-plus-and-minus)
 - [3.3 Bitwise Complement Operator](#bitwise-complement-operator)
 - [3.4 Binary Operators Overview](#binary-operators-overview)
 - [3.5 If-Else Decision Construct](#if-else-decision-construct)
 - [3.6 Switch Decision Construct](#switch-decision-construct)
 - [3.7 Loop Structures](#loop-structures)

[4 Arrays](#4-arrays)
 - [4.1 Array Declaration and Initialization](#array-declaration-and-initialization)



## 1 Packages and Imports

Referencing a type from another package

| **Reference Method** | **Example Code** | **When To Use** |
| --- | --- | --- |
|A Fully Qualified Class Name (FQCN) | java.util.ArrayList = new java.util.ArrayList(); | If you are only referencing a class once in source, or you are referencing two classes with the same name but from different packages, you will still need to use the FQCN. It can be very verbose. |
|A single-type-import declaration | import java.util.Arraylist </br> ... </br> ArrayList list = new ArrayList(); | This is the most common way for both readability of code and information for future developers. |
|A type-import-on-demand declaration | import java.util.* </br> ... </br> ArrayList list = new ArrayList(); | Discouraged but still needed to be recognized as valid for the exam. If you use this for all your import statements, anyone reading the code may not know where the class referenced actually resides |
|A single-static-import declaration | import static java.lang.Math.PI; | This is a way to pull in a single static member of a class in and reference the member's name without including a reference to the declared type |
|A static-import-on-demand declaration | import static java.lang.Math.*; | This is a way to pull in ALL static members of a class without referencing the declared type |

If trying to import to classes of the same name, the most specific import will take presidence. 
In the case where both import are equally specific, there will be a compile error due to imports being ambiguous. 

## 2 Data Types and Strings

Java supports two types of data, reference data types and primitive data types.

The data value of a primitive data type is stored in memory and is not a location reference to the data.
This makes accessing primitive data types faster and leads to more efficient performance when it really matters.

Because Java allows primitive data types which do not inherit from "Object" like the every other instance, Java is not 
considered to be a 'pure' object oriented language.

There are eight primitive data types in Java.
The first five are represented in two's complement format, which is important, especially for the 4 signed primitives
(only the char is unsigned), where the left most bit represents the sign (negative/posivite).

Float and Double are floating point representations and approximations, and will not overflow. These are used when precision
after the decimal point is critical.

| **Primitive Data Type** | **Storage in bits (width)** | **Range of Values** | Wrapper |
| --- | --- | --- | --- |
|byte | 8 | -128:127 | Byte |
|char | 16 | \u0000:\uFFFF | Character |
|short | 16 | -32768:32767 or -2^15 -> 2^15 -1 | Short |
|int | 32 | -2^31 -> 2^31 - 1 | Integer |
|long | 64 | -2^63 -> 2^63 - 1 | Long |
|float | 32 | approx 3.4028235E38 | Float |
|double | 64 | approx 1.7976931348623157E308 | Double |
|boolean | 1 | true, false | Boolean |

### Declaring Primitive Types

Data can be assigned to data variables in the form of literals.
The exam explores some implications of setting literal data to typed variables where there is a type mismatch.

Valid literals are numeric values in base ten, hexadecimal or octal forms.

An unmodified integer value is assumed to be 32-bit int primitive.

A value containing a decimal point is assumed to be a 64-bit double.

In Java, single character value in single quotes is considered a valid char literal, but any characters surrounded by double
quotes is considered to be a string literal.

### Local Variable Initialization

To use a primitive data type variable in code, you must initialize it. 
This is not true for class static variables and instance variables which will have default values assigned to them.

This initialization or lack thereof is a common theme in the exam questions.

### Narrowing and Widening

Narrowing is when you assign a larger primitive data literal or variable to a smaller one.
Widening is when you are putting something small in a larger variable.

The compiler is more forgiving with widening attempts, and has more constraints on narrowing. 

### Casting

There are times when you want to force the compiler to overlook its narrowing and widening checks, because you have
knowledge of the actual values occurring in the program during execution. You can do this by casting.

You cast by referencing the type you want to be widened, or narrowed to in parenthesis preceding the variable or value 
that is to be converted.

The problem with casting is that if your value does not fall into the valid value range, your data may underflow or overflow
 - Underflow is defining or casting a value less than the minimum value for the datatype.
 - Overflow is defining or casting a value greater thant the maximum value for the datatype.
 
### Scope

Oracle's Java Specification states "The scope of a declaration is the region of the program within which the entity declared by the declaration can be referred
to using a simple name, provided it is visible".

A local variable, formal parameter, exception parameter, and local class acan only be reffered to using a simple name, not a qualified name.
Because of this, some declarations are not permitted within the scope of these elements.

This can be re-stated - If you cannot qualify a variable in its current scope, you cannot create another local variable name in a more limited scope.

| **Scope** | **Qualifier** |
| --- | --- |
| Class | {DefinedClassType} |
| Instance | this |
| Method | none |
| Loop | none |
| Loop Block | none |
| Block Including Exception Block | none |


### Local Variable Type Inference

Local Variable Type Inference (LVTI) is also known as var declaration.  
This is Java 10 feature introduced to reduce the verbosity of code.  
This feature is only available for local variables inside a method body.  

**What Local Variable Type Inference is and is NOT**

What it is:  
- A shortcut to reduce verbosity of code.

What it is not:  
- A data type, the data type must be able to be inferred by the compiler when using it.
- A keyword, you can actually use the text 'var' as an identifier.


````Java
public class VarDemo {
    
    //Non-LVTI way of instantiating object, gives a very verbose line of code in this case
    final AClassWithAVeryLongName aClassWithAVeryLongName = new AClassWithAVeryLongName();
    
    //Utilizing var to shorten the amount of code
    final var aClassWithAVeryLongName2 = new AClassWithAVeryLongName();
}
````
**VALID uses of Local Variable Type Inference are for local variables only**  


| **Statement**                       | **Explanation**                                               |
|-------------------------------------|---------------------------------------------------------------|
| var someClass = new SomeClass();    | Can be used for reference objects                             |
| var i = 1;                          | i is inferred to be an int, since it's assigned a literal int |
| var anArray = new int[3];           | An array can be assigned to an LVTI variable                  |
| var methodVal = someClass.getName() | Valid to assign a method return value to an LVTI variable     |  


**INVALID uses of Local Variable Type Inferrence**  


| **Statement**               | **Explanation**                                               |
|-----------------------------|---------------------------------------------------------------|
| final var j = 0, k = 0;     | Cannot be used in compund statements                          |
| final var m, n = 0;         | Cannot be used in compund statements                          |
| var someObject;             | Cannot declare a var variable without also initializing it    |
| var newVar = null;          | Cannot assign null to var, type cannot be inferred            |
| var myArray = {"A", "B"};   | Cannot us array initializer in var declaration/initialization |
| var[] varArray = new int[2];| Cannot have an array of var                                   |

### Strings

A String in Java is an object of class java.lang.String and represents an array or series of characters, but is NOT an
array of the primitive data type char.  

When a String is created without a constructor, aka not using new, the String is stored in a special area of the heap 
called the String-pool, whose purpose is to maintain a set of unique Strings - this is called interning.

You can manually intern, using the intern method on a String object.  

When you assign two variables to the same String literal, these string are considered equal and the comparator == evaluates to true.

````Java
public class StringDemo {
    //String literals automatically interned so all "hello" literals point to the same reference in memory, in the string pool 
    final String s1 = "Hello";
    final String s2 = "Hello";
    
    //Non-literal Strings do not point to the same reference, and are not stored in the String pool
    final String s3 = new String("Hello");
    final String s4 = new String("Hello");
    
    //Using the intern-method checks the String pool for "Hello" and returns the String pool reference to s1
    final String s5 = new String("Hello").intern();
    final String s6 = new String("Hello").intern();
    
    public static void main(final String[] args) {
        System.out.println(s1 == s2); //True
        System.out.println(s3 == s4); //False
        System.out.println(s5 == s6); //True
    }
}
````

Strings in Java are immutable, meaning that once you crate a String literal, e.g. "Hello", that String remains "Hello"
on the String-pool with a single reference to it.
If you do a String concatenation, you are not changing the current String, but creating a new String object with the
concatenated Strings.

````Java
public class ImmutableString {

    public static void main(final String[] args) {
        //Hello is added to the String-pool, s1 gets a reference to it
        String s1 = "Hello";
        
        /*
        This does not change "Hello" in the String-pool, it merely adds a new String "Hello World" to the String pool
        and passes the reference to s1. 
        */
        s1 = s1 + " World";
    }
}
````

### String Concatenation and Manipulation

The concatenation operator for Strings in java is the "+"-operator.

When you concatenate a string to a reference variable, if the variable is a reference type, the toString() method on the
object is called. If the variable is a primitive data type, the variable is boxed to a wrapper and its toString() method is called.

NOTE: Skipping a lot of chapters regarding Strings due to most of it being very basic stuff I use every day.

### StringBuilder

Remember: Strings are immutable, StringBuilder objects are not.  

String should always be used unless StringBuilders offer an advantage in terms of simpler code or better performance.  

For example, if you need to concatenate a large number of Strings, such as a temporary error message, or dynamic XML,
then appending to a StringBuilder object is more efficient.  

Every StringBuilder has a capacity of which is the number of character spaces allotted to it, 
capacity is automatically extended as additions are made to the StringBuilder object.  

When creating StringBuilder objects, you have the following options:

| Constructor    | Description | Capacity |
|-----------|-------------|-------------|
| StringBuilder() | Empty StringBuilder object with capacity of 16 empty elements | 16 |
| StringBuilder(CharSequence cs) | StringBuilder object with same characters as the specified CharSequence, plus an extra 16 empty trailing elements | cs.length() + 16 |
| StringBuilder(int initCapacity) | Empty StringBuilder object with specified initial capacity | initCapacity |
| StringBuilder(String s) | StringBuilder object with same characters as the specified String, plus an extra 16 empty trailing elements | s.length() + 16 |

When manipulating StringBuilder objects we use the append() and insert()-methods, where append adds the passed in parameter value at the end of the resulting String, and
insert takes in an offset argument to place the passed in value at the given index.

````Java
public void stringBuilding() {
    final StringBuilder sb = new StringBuilder();
    sb.append("There");
    sb.insert(0, "Hello "); //"Hello There"
}
````

StringBuilder can also be used to delete data from a String, using the delete()-method.
Delete takes in two arguments, the start and end indices of the delete-operation.

You also have the deleteCharAt()-method which removes the character at the given index.

````Java
public void stringDeleting() {
    final StringBuilder sb = new StringBuilder("Hello There");
    sb.delete(0, 6); //"There"
    sb.deleteCharAt(0); //"here"
}
````

## 3 Operators and Decision Constructs

Java operators are symbols that are used to perform mathematical or logical manipulations and have two types of classifications.
The first classification is based on the number of operans the operator has (unary, binary, ternary), and the second classification is the type of operation it performs.

When utilising operators, it is important to know:
 - Precedence of an operator (unary operators are evaluated before binary operators).
 - Among the unary operators, the postfix increment and decrement have the highest precedence.
 - Unary operators except the prefix/postfix operators promote the variable to an int if it is smaller than an int.
 - If all operators have the same precedence, the expression will be evaluated from the left to right, except the simple and compound assignment operators which are evaluated right to left.

| Symbol | Name                        | Examples    | Description                                                                       | Notes                                                                                |
|------|-----------------------------|-------------|-----------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| --   | Pre- and Postfix decrement  | --a<br/>a-- | Decrements value of a before or after expression is evalutated                    | It is possible that a variable will go out of scope before the post decrement can occur |
| ++   | Pre- and Postfix Increment  | ++a<br/>a++ | Increments value of a before or after expression is evalutated                    | It is possible that a variable will go out of scope before the post increment can occur |
| -    | Unary Minus                 | -a          | Returns negated value of a whithout changing the value of a                       | If a is a data type with size less than an int, a will get promoted to an int        |
| +    | Unary Plus                  | +a          | Does nothing to the value of a, but is allowed                                    | If a is a data type with size less than an int, a will get promoted to an int                                                                                       |
| ! | Logical Complement Operator | !a          | Returns a complement of a boolean value                                           |                                                                                      |
| ~ | Bitwise Complement Operator | ~a          | Bitwise complement, turns a 1 to 0, or a 0 to a 1. In all cases, ~x equals (-x)-1 |                                                                                      |
|  | Cast Operator               | (int)a      |                                                                                   |                                                                                      |

### Pre- and Postfix Increment and Decrement Operators

It is very important to remember that the postfix operators do not change the value of is unary operand until the expression it is operating in is consideres completed
OR the operand is used again the same statement.

The unary variable being operated on does get operated on (the value at the end is changed), but any assignments or expresions 
this statement is part of will be using the unchanged value until the expression statement is evaluated successfully.

If the statement is interrupted somehow, then the postfix may not actually be applied. It needs to be noted that the operators are
changing the value contained in the reference itself, meaning if you make the statement a++, you are incrementing the value in the variable a.
These operands can be standalone statements. This is different, for example, from the unary minus (-a), which has no impact on the value
in the operand itself, and cannot be a statement of its own and must have it's output assigned or used in an expression.

````Java
public class PrePostFixing {

    //The postfix does not do any work on a before the method it is called in is done executing, hence the behaviour in the second print
    public void prePostFixing() {
        int a = 1;
        System.out.println("a after ++a = " + ++a); //2

        a = 1;
        System.out.println("a after a++ = " + a++); //1
        System.out.println("And now the value of a is:"a); //2
    }

    public void prePostFixingV2() {
        final int a = 1;
        final int a2 = a++;
        System.out.println("The value of a is " + a); //2
        System.out.println("The value of a2 is " + a2); //1
    }

    public void prePostFixingInLoopsPrefix() {
        int a = 5;
        int loopIterations = 0;

        while (--a > 0) {
            loopIterations++;
        }

        //loopIterations = 4, a = 0
        System.out.println("Prefix decrement, loopIterations = " + loopIterations + ", a = " + a);
    }

    public void prePostFixingInLoopsPostfix() {
        int a = 5;
        int loopIterations = 0;

        while (a++ > 0) {
            loopIterations++;
        }

        //loopIterations = 5, a = -1
        System.out.println("Prefix decrement, loopIterations = " + loopIterations + ", a = " + a);
    }
}
````

### Unary Plus and Minus

Refer to the table in [3 Operators and Decision Constructs](#3-operators-and-decision-constructs)

### Bitwise Complement Operator

The bitwise complement operator flips the bit for the entire value of for the entire value of the variable.
The binary literal value of the integer 0, gets every bit flipped to 1, making it's integer value -1.

The logical complement operator only operates on a boolean, and changes false to not false (true).

### Binary Operators Overview

With the exception of the conditional (or the ternary) operator, the remaining operators in Java are binary.
In the following table they are grouped by the type of operation they perform, and listed by their precedence level.

| Category                      | Symbol                                     | Simple Description                                                          | Notes                                                                                                                                                                                  |
|-------------------------------|--------------------------------------------|-----------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Multiplicative Operators      | *<br/>/<br/>%                              | Multiplication<br/>Division<br/>Modulus                                     | Precedence equal among them, group left-to-right                                                                                                                                       |
| Additive Operators            | +<br/>-                                    | Addition<br/>Subtraction                                                    | Precedence equal among them, group left-to-right                                                                                                                                       |
| Shift Operators               | <<</br>>></br>>>>                          | Left Shift<br/>Signed Right Shift<br/>Unsigned Right Shift                  | The left-hand operand of a shift-operator is the value to be shifted; the right-hand operand specifies the shift distance. <br/><br/> Precedence equal among them, group left-to-right |
| Relational Operators          | <<br/><=<br/>><br/>>=                      | Less than<br/>Lest than or equal<br/>Greater than<br/>Greater than or equal | Precedence equal among them, group left-to-rigth                                                                                                                                       | 
| Equality Operators            | ==<br/>!=                                  | Equals<br/>Not Equals                                                       |                                                                                                                                                                                        |
| Bitwise and Logical Operators | &<br/>^</br> (pipe)                        | AND<br/>XOR<br/>OR                                                          | These operators have differenc precedence, with & having the highest precendence and (pipe) the lowest.                                                                                |
| Conditional AND Operator      | &&                                         |                                                                             | The conditional AND operator is like &, but evaluates its right-hand operand only if the value of its left-hand operand is true                                                        |
| Condotional OR Operator       | (pipe)(pipe)                               |                                                                             | The conditional OR operator is like (pipe), but evaluates its right-hand operand only if the value of its left-hand operand is false                                                   |
| Conditional Operator          | ?:                                         |                                                                             |                                                                                                                                                                                        | 
| Assignment Operators          | =<br/>*= /= += -= <<=<br/> >>= >>>= &= ^=  | Simple assignment<br/> Compound Assignment                                  |                                                                                                                                                                                        |
| Lambda Operator               | ->                                         |                                                                             |                                                                                                                                                                                        |

Note that for a unary operator, the is not the pre- or post decrement/increment operator, if the type of the operand is smaller than an int, the operand will automatically be promoted to an int.

For a binary operator, both operand are promoted to int, if they are smaller than an int, but if any of the operands are larger than an int, then it is promoted to the larger type. Not that this is NOT true for the compound assignment operators.

What this means is that any operations on numeric values will never result in a value that is smaller than an int.

````Java
public class BinaryOperatorsExamples {
    
    public void precedenceInArithmetic() {
        final int a = 1;
        final int b = 2;
        final int c = 3;
        final int d = 4;
        final int e = 5;
        final int f = 6;
        
        //Follows standard mathematical rules for precedence in arithmetic
        //Could also be written a + (e * b) - (f / c) % b;
        final int result = a + e * b - f / c % b; //11
    }
    
    public void shiftOperators() {
        final String leftShift = Integer.toBinaryString(0b00000001 << 2); //0b00000100
        final String rightShift = Integer.toBinaryString(0b10001000 >> 3); //0b00010001
        final String unsignedRightShift = Integer.toBinaryString(0b10000010_00000010_00000010_00000001 >>> 1); //1000001000000010000000100000000
    }
    
    //Bah, won't bother adding all the examples from the course, most of this is pretty rudimentary. 
    //Add some examples of your own if there happens to be some mind-bending cases popping up in the trial exams
}
````

### If-Else Decision Construct

The if-statement allows conditional execution of a statement, a block of statements, or a choice of two statements or statement blocks.

If you do not have a bracket after the if or else, then only one line of code is contained within the conditional block.
You can have an empty statement block after the if or else as long as semicolon follows.
Dangling elses are assumed to go with the innermost if statement

````Java
public class IfElseExamples {
    final boolean flag = false;
    boolean subFlag = true;
    
    public void danglingElse() {
        if (flag); //Empty statement is valid, need the semicolon
        else System.out.println("1. flag is false");
        
        if (flag)
            if (subFlag) System.out.println("2. subflag is true");
            else 
                System.out.println("2. subflag is false"); //Dangling else
        else System.out.println("2. flag is false");
    }
    
    //Typical exam question where the formatting is wrong in order to mislead
    public void formattedToMislead() {
        if (flag)
            if (subFlag) System.out.println("1. subflag is true");
        else System.out.println("1. flag is false"); //Dangling else
        
        subFlag = false;
        if (flag)
            if (subFlag) System.out.println("2. subflag is true");
        else System.out.println("2. subflag is false");
        else System.out.println("2. flag is false");
    }
}
````

### Switch Decision Construct

A switch statement can only work with byte, short, char and int primitive data types.  
It also works with enumerated types, the String class and the aforementioned primitive wrapper classes.  
The default case label only matches an argument if all the other labels do not, regardless of its position.

### Loop Structures

Java supports three types of loops, the while loop, the do-while loop and the for loop.  

The while loop exexutes one or more statements after testing the loop continuation expression at the start of each iteration. 
The do-while loop however tests the loop continuation expression after the first iteration has completed.
````Java
while (expression) {
    statements
}

do {
    statements
} while (expression)
````

The traditional for loop has built-in mechanisms to control the initialization, and updating of the loop variable, as well as the comparison condition (termination section).  
When using this version of the for statement, keep in mind:
 - The initialization expression initialixzes the loop; it is executed once as the loop begins. This expression is optional.
 - When the termination expression evaluates to false, the loop terminates. The termination expression is optional, and the default value is true.
 - The increment expression is invoked after each iteration through the loop; it is perfectly acceptable for this expression to increment or decrement a value. The increment expression is optional.

````Java
for (initialization; termination; increment) {
    statements    
}
````

The enhanced for loop is used to loop through an Iterable set of data and do something.  
The type of the Expression must be Iterable, or an Array-type.
````Java
for (FormalParameter: Expression) {
    statements    
}
````

### For Loop - Out of the Ordinary Concepts

You cannot reference a label with the continue and break states that is not in the loop scope, a compiler error occurs.  
An outer loop cannot reference the inner loop's labels, but an inner loop can reference the outer loop's labels.  
You can break out of a nested loop from a nested loop.  
You can also completely break out of the parent loop from the nested loop.  

## 4 Arrays

An array can be one dimensional, two dimensional or multi-dimensional representing a matrix of data.  
Note that "true support" of multi-dimensional arrays is not supported, but Java offers an array of arrays to support a
multi-dimensional structure.  

You can store a subclass object in an array, declare dto be the type of the superclass or interface, supporting polymorphism.  


### Array Declaration and Initialization

**Valid declarations**

| Declaration      | Notes                                                                                                                                                     |
|------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| int[] array;     | Brackets can be associated to the type, indicating that variable is an array                                                                              |
| short array[];   | Brackets can be associtated to the variable name, indicating that the variable is an array                                                                |
| int a, b[], c;   | You can define multiple variable of a type on one line, including arrays of that type.                                                                    |
| int[] d, e, f[]; | You can define multiple array values in a single declaration variable. Note that variable f in this statement is a declaration of a two dimensional array |
| int[] array[];   | This is valid, but might be unintended, as it declares a two dimensional array                                                                            | 

**Invalid declarations**

| Declaration       | Notes                                                                     |
|-------------------|---------------------------------------------------------------------------|
| int[2] array;     | Size is not part of the declaration                                       |
| int array[2];     | Size is not part of the declaration                                       |
| int a, float b[]; | You can NOT define multiple variables of different types on the same line |

**Initializing Arrays**

You must define the size of the array in brackets.  
Once a size is defined, you cannot change the size of an array.  
When you create an array this way, the JVM automatically assigns defaults:
 - Numeric primitives are set to 0
 - Boolean primitives are set to false
 - References, including primitive data type wrappers, are set to null

````Java
int[] array = new int[10]; //creates an array of int with 10 elements initialized to 0
String[] array = new String[10]; //creates an array of String with 10 elements initialized to null
````

There are also array initializers, which are shortcuts allowing you to create and initialize each element in an array in one statement.  
These can only be used in the same statement as the declaration.

````Java
final int[] array = {1, 2, 3 ,4, 5};
final String[] array = {"one", "two", null, "three"};
````

### Manipulating Arrays

Arrays are a useful data structure, but comes with limited methods for manipulating the data.  
Hence, manipulating arrays usually includes utilizing the java.util.Arrays class.  

The Javadoc definition of the java.util.Arrays class is:  
"The class contains various methods for manipulating arrays (such as sorting and searching). This class also contains a static factory that allows arrays to be viewed as lists"  

| Type of Functionality | Array Class Methods                                                                                                                                                        | List Interface Methods                                                                                                                    |
|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| Comparison            | compare (Java 9)<br/>compareUnsigned (Java 9) <br/> deepEquals <br/> equals                                                                                                | equals <br/> isEmpty                                                                                                                      |
| Searches              | binarySearch <br/> mismatch (Java 9)                                                                                                                                       | contains <br/> containsAll <br/> indexOf <br/> lastIndexOf                                                                                | 
| Data Manipulation     | deepHashCode <br/>deepToString <br/> fill <br/> hashCode <br/> parallelPrefix (Java 8) <br/> parallelSort (Java 8) <br/> parallelSetAll (Java 8) <br/> sort <br/> toString | add <br/> addAll <br/>clear <br/>get <br/>hashCode <br/>remove <br/>removeAll <br/>replaceAll<br/> retainAll <br/>set <br/>size <br/>sort |
| Data Transformation   | asList <br/> copyOf <br/> copyOfRange <br/> spliterator (Java 8) <br/> stream (Java 8)                                                                                     | copyOf (Java 10) <br/> iterator <br/> listIterator <br/> of (Java 9) <br/> spliterator (Java 8) <br/> subList <br/> toArray               |

### Comparing Arrays

To understand the compare method, we need to understand what an array prefix is.  
 - A prefix is the set of elements in common, starting at the element on index 0.
 - Alternatively, an index we define, this will come up later.
 - If we compare two String arrays that have the exact same elements, the prefix is the entire set of elements.
 - If we compare the arrays in the below example, the prefix is the entire partialArray, since all elements in partialArray exists in the defaultArray, starting at offset index 0, and in the same order.
 - If we compare the defaultArray with unsortedArray, there is no prefix as there is no common set of elements starting at offset index 0.

````Java
final String[] defaultArray = {"a", "b", "c", "d", "e"};
final String[] partialArray = {"a", "b", "c"};
final String[] unsortedArray = {"d", "a", "e", "b", "c"};
````

The Arrays.compare method then follows these rules: 
 - If Arrays.equals is true, return 0.
 - If first array passed to parameter is null, return -1, else if second array is null, return 1.
 - If length of first array is 0, return (0 - length of second array).
 - If length of second array is 0, return (length of first array - 0).
 - If one array represents the entire prefix of another, the difference in lengths of the arrays is returned.
   - This number will be negative if you are comparing the smaller array to a larger array.
 - If no prefix is identified, then the first element of each array is compared, lexicographically.
   - This number will be negative if the first element of the first array is less than the first element of the second array.
 - If a prefix is identified, but neither arrays represents a full subset of the other, the index where the prefix stops is used to then compare the elements at that index.

| Array a      | Array b      | Arrays.compare(a, b)   | Notes                                                             |
|--------------|--------------|------------------------|-------------------------------------------------------------------|
| {e1, e2}     | {e1, e2}     | 0                      | Arrays are equal                                                  |
| null         | {e1, e2}     | -1                     | Note that this is a different result than empty array             |
| {e1, e2}     | null         | 1                      | Note that this is a different result than empty array             |
| {}           | {e1, e2}     | a.length - b.length    | a.length == 0                                                     |
| {e1, e2}     | {}           | a.length - b.length    | b.length == 0                                                     |
| {e1, e2}     | {e1, e2, e3} | a.length - b.length    | prefix is {e1, e2} which is complete set of one of the arrays     |
| {e1, e2}     | {e3, e4}     | a[0].compareTo(b[0])   | no prefix identified                                              |
| {e1, e2, e3} | {e1, e2, e4} | a[2].compareTo(b[2])   | prefix is {e1, e2} but this is not a complete set of either array |

