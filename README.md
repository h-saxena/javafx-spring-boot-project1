# javafx-spring-boot-project1
javafx spring boot project

- Download Java Development kit 8
- Download Maven 3

and on the command prompt go to the root of the Application folder to follow the steps below:

To Run:
1)
$mvn clean install -DskipTests
$java -jar target/AdvDBProjectSpringBootJavaFX-0.0.1-SNAPSHOT.jar --username=<username> --password=<pswd> --host=<host>

or

$mvn spring-boot:run -Drun.arguments="--username=<username>,--password=<password>"

