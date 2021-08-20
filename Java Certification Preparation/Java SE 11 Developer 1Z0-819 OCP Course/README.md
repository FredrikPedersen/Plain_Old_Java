# Java SE 11 Developer 1Z0-819 Course

Notes taken from [Tim Buchalka's Java SE 11 Developer Exam course](https://tietoevry.udemy.com/course/java-se-11-developer-1z0-819-ocp-course-part-1/learn/lecture/23038174#overview)

[1 Packages and Imports](#1-packages-and-imports)  
[2 Data Types and Strings](#2-data-types-and-strings)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2.1 Declaring Primitive Types](#declaring-primitive-types)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2.2 Local Variable Initialization](#local-variable-initialization)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2.3 Narrowing and Widening](#narrowing-and-widening)


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







