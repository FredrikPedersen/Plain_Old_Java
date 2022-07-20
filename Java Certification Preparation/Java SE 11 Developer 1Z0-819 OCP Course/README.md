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