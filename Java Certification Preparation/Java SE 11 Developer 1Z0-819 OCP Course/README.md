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

Flot and Double are floating point representations and approximations, and will not overflow. These are used when precision
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

Oracle's Java Specification states "The scope of a declaration is the region of the program within which the entity devalred by the decalration can be referred
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
This is Java 10 frautre introduced to reduce the verbosity of code.  
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


| **Statement**                       | **Explanation**                                                 |
|-------------------------------------|-----------------------------------------------------------------|
| var someClass = new SomeClass();    | Can be used for objects                                         |
| var i = 1;                          | i is inferred to be an int, since it's assigned a literal int   |
| var anArray = new int[3];           | An array can be assigned to an LVTI variable                    |
| var methodVal = someClass.getName() | Valid to assign a method return value to an LVTI variable       |  


**INVALID uses of Local Variable Type Inferrence**  


| **Statement**               | **Explanation**                                               |
|-----------------------------|---------------------------------------------------------------|
| final var j = 0, k = 0;     | Cannot be used in compund statements                          |
| final var m, n = 0;         | Cannot be used in compund statements                          |
| var someObject;             | Cannot declare a var variable without also initializing it    |
| var newVar = null;          | Cannot assign null to var, type cannot be inferred            |
| var myArray = {"A", "B"};   | Cannot us array initializer in var declaration/initialization |
| var[] varArray = new int[2];| Cannot have an array of var                                   |