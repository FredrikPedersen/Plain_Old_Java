# Java SE 11 Developer 1Z0-819 Course

Notes taken from [Tim Buchalka's Java SE 11 Developer Exam course](https://tietoevry.udemy.com/course/java-se-11-developer-1z0-819-ocp-course-part-1/learn/lecture/23038174#overview)

## Packages and Imports

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