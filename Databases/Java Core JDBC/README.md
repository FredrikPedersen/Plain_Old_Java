# Java Core JDBC

- Code from Pluralsight Course Java Core Libraries: JDBC.

## Table of Contents

1. [Module 3: Connecting JDBC to the Relational Database](#module-3-connecting-jdbc-to-the-relational-database)


2. [Module 4: Using JDBC to Query Databases](#module-4-using-jdbc-to-query-databases)

- 2.1 [Using Statement and ResultSet](#using-statement-and-resultset)

## Module 3: Connecting JDBC to the Relational Database

- Connection to a database is done by creating a Connection object and passing in a connection URI which points to which
  driver should be utilized. This URI is usually found in the documentation for the specific database (googling for JDBC
  connection URI and the database name usually gives decent results)


- For most databses, the format of the connection URI is given on the format:

```Text
jdbc:<driver>:<host>/<database-name>?user=<username>&password=<password>
```

- Using a JDBC driver at runtime requires an executable JAR containing the driver.
    - First off, the driver needs to be added as a Maven or Gradle dependency
    - Then an executable JAR has to generated, preferably using Maven.
      See [JetBrain's guide on how to compile and build applications in IntelliJ](https://www.jetbrains.com/help/idea/compiling-applications.html)
      .
    - Per the course instructor, using the plugins defined in this project's Maven dependencies and then running Maven
      Install should suffice to genereate the executable JAR.
        - However, this did not work consistently in my case, with Maven throwing an error when running the script,
          sometimes managing to build the JAR before failing.
         ```Bash
        # Maven error upon running Maven Install
        Failed to execute goal org.skife.maven:really-executable-jar-maven-plugin:1.1.0:really-executable-jar (default) on project corejdbc: FAILURE!
         ```           

        - Also, the dependencies and configured plugins for this project are never explained, while I suspect
          Maven-Skife and Maven-Shade plugins are the ones responsible for JAR-packaging. If there are problems with
          packing in later projects, take some time to learn how to set it up properly from scratch.

````Java
//Connecting to and testing the connection to a MySQL database
public class DatabaseConnector {
    public boolean tryConnection() throws Exception {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lotsofdata?user=suchlogin&password=muchsecret");

        boolean isValid = connection.isValid(2);
        connection.close();
        return isValid;
    }
}
````

- In all future examples we are going to assume that our program contains a String constant named ***DB_URI*** which is
  equal to the URI in the above example.

## Module 4: Using JDBC to Query Databases

### Using Statement and ResultSet

- To query a database, you first need a Connection object, which is then used to create a Statement object.
- You then create a ResultSet-object by calling the executeQuery method on the Statement object to get the retuned data.
    - Note that RestultSet, Statement and Connection objects must be closed when done executing to free up resources.
    - Resource closing should be executed in the reverse order of that they were created.
- To iterate over each retrieved record, do so by using a while-loop on resultSet.next().
- Data retrieved from resultSet is not automatically mapped to any Java-class like they are in ORMs like JPA, and it
  will have to be done manually, giving you as developer more control over what is retrieved and how in exchange for
  having to write more code.

````Java
/* 
For this example we assume that we have a database table named people, with columns named "firstname" and "lastname".
We also have a POJO named Person with identical String attributes and relevant constructors.
 */

public class PeopleRepository {

    public List<Person> getAllPeople() throws Exception {
        List<Person> people = new ArrayList<>();
        Connection connection = DriverManager.getConnection(DB_URI);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM people;");

        while (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            people.add(new Person(firstname, lastname));
        }

        resultSet.close();
        statement.close();
        connection.close();
        return people;
    }
}
````

### Handling Exceptions in JDBC

- Exceptions occurring while querying a database is quite frequent.
- All exceptions thrown by JDBC extend SQLException.
- Perform all operations related to connecting to or querying a database inside a try-catch block in case of exceptions.
    - Note the usage of ***try-with-resources***-syntax, which automatically closes all resources when the try-catch block is
      done executing, which makes a finally block where we close the resources manually redundant.
    - To keep the component as clean as possible, handle exceptions where the method is called, and not in the component
      itself.


- Note that Error Codes are database vendor specific codes, while the SQL State is an industry standard, the meaning of
  them all are found easily by googling.

`````Java
public class PeopleRepository {

    public void getAllPeople() throws Exception {
        List<Person> people = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(DB_URI);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM products;");
        ) {

            statement.executeQuery("SELECT * FROM people");

            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                people.add(new Person(firstname, lastname));
            }
        }

        return people;
    }
}

public class ExceptionHandler {

    public static void handleException(Exception exception) {
        if (exception instanceof SQLException) {
            SQLException sqlException = (SQLException) exception;
            System.out.println("Error Code: " + sqlException.getErrorCode());
            System.out.println("SQL State: " + sqlException.getSQLState());
        }

        System.out.println("SQLException message: " + exception.getMessage());
        System.out.print("Stacktrace: ");
        exception.printStackTrace();
    }
}

public class Main {

    public static void main(String[] args) {
        
        try {
            PeopleRepository repository = new PeopleRepository();
            repository.getAllPeople();
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }
}
`````
