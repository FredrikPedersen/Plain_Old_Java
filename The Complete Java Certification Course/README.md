# [The Complete Java Certification Course](https://www.udemy.com/course/master-practical-java-development/)

Completing this course a refresher on Java basics and to properly learn some of the more complex topics.
I will not be writing notes and code from the very basics, that is just redundant. 

The course itself is very focused on being for Java beginners, and does not provide very technical explanations.
Additional sources will be utilized to better explain some concepts.

## Lecture 12: Java Stack + Heap with Reference Variables

There are two memory locations which becomes reserved for the Java application to run successfully, the stack and
the heap.

### Stack

Stack Memory in Java is used for memory allocation and the execution of a thread, containing primitive values that are
specific to a method and references to objects that are in a heap referred from the method.

Access to this memoer is in Last-In-First-Out (LIFO) order. Whenever a new method is called a new block on top the stack 
is created, which contains values specific to that method, like primitive variables and references to objects.

When the method finished execution, it's corresponding stack frame is flushed, it's corresponding stack frame is flushed,
and the flow goes back to the calling method and space becomes available for the next method.

**Some Key Features of Stack Memory:**
 - It grows and shrinks as new methods are called and returned respectively.
 - Variables inside the stack exist only as long as the method that created them is running.
 - It's automatically allocated and deallocated when method finishes execution.
 - If this memory is full, Java throw a StackOverFlowError.
 - Access to this memory is fast compared to heap memory.
 - This memory is threadsafe as each thread operates within its own stack.

[source - Baeldung](https://www.baeldung.com/java-stack-heap)

### Heap

Heap space in Java is used for dynamic memory allocation for Java objects and JRE classes at runtime. New objects are
always created in heap space, and the references to these objects are stored in stack memory.

The heap-stored objects have global access and van be accessed form anywhere in the application.

This memory model is further broken into smaller parts called generations, these are:
1. **Young Generation** - This is where all new objects are allocated and aged. A minor Garbage collection occurs when this
fills up.
2. **Old or Tenured Generation** - This is where long surviving objects are stored. When objects are stored in the Young 
Generation, a threshold for the object's age is set and when that threshold is reached, the object is moved to the old
generation.
3. **Permanent Generation** - This consists of JVM metadata for the runtime classes and application methods.

**Key Features of Java Heap Memory**
 - It is accessed via complex memory management techniques that includes the usage of generations.
 - If heap space is full, Java throws an OutOfMemoryError.
 - Access to this memory is relatively slow compared to stack memory.
 - This memory, in contrast to stack, isn't automatically deallocated. It needs the Garbage Collector to free up unused
objects to keep the efficiency of the memory usage.
 - Unlike stack, a heap isn't threadsafe and needs to be guarded by properly synchronizing the code.

[source - Baeldung](https://www.baeldung.com/java-stack-heap)


<img alt="Java Call Stack and Heap Illustration" src="https://www.baeldung.com/wp-content/uploads/2018/07/java-heap-stack-diagram.png" width="1000px"/>

[source - Baeldung](https://www.baeldung.com/java-stack-heap)