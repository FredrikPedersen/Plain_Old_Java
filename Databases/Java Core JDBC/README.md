# Java Core JDBC

 - Code from Pluralsight Course Java Core Libraries: JDBC.

## Module 3: Connecting JDBC to the Relational Database


 - Connection to a database is done by creating a Connection object and passing in a connection URI which points to which
driver should be utilized. This URI is usually found in the documentation for the specific database (googling for JDBC connection URI and the database name usually gives decent results)  
   
   
- For most databses, the format of the connection URI is given on the format:
```Text
jdbc:<driver>:<host>/<database-name>?user=<username>&password=<password>
```

 - Using a JDBC driver at runtime requires an executable JAR containing the driver.
    - First off, the driver needs to be added as a Maven or Gradle dependency
    - Then an executable JAR has to generated, preferably using Maven. See [JetBrain's guide on how to compile and build applications in IntelliJ](https://www.jetbrains.com/help/idea/compiling-applications.html).
    - Per the course instructor, using the plugins defined in this project's Maven dependencies and then running Maven Install should suffice to genereate the executable JAR. 
         - However, this did not work consistently in my case, with Maven throwing an error when running the script, sometimes managing to build the JAR before failing.
         ```Bash
        # Maven error upon running Maven Install
        Failed to execute goal org.skife.maven:really-executable-jar-maven-plugin:1.1.0:really-executable-jar (default) on project corejdbc: FAILURE!
         ```           

         - Also, the dependencies and configured plugins for this project are never explained, while I suspect Maven-Skife and Maven-Shade plugins are the ones responsible for JAR-packaging. If there are problems with packing in later projects, take some time to learn how to set it up properly from scratch.
````Java
//Connecting to and testing the connection to a MySQL database
public boolean tryConnection() throws Exception {	
		
    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/lotsofdata?user=suchlogin&password=muchsecret);			
		
    boolean isValid = connection.isValid(2);
    connection.close();
    return isValid;
}
````
