# mvn-project
1. Project structure
Project name: devops-learning
devops-learning/
│
├── pom.xml
│
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── devops/
    │               └── App.java
    │
    └── test/
        └── java/
            └── com/
                └── devops/
                    └── AppTest.java


2. Create Maven project
If Maven is installed, execute:
mvn archetype:generate \
-DgroupId=com.devops \
-DartifactId=devops-learning \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DarchetypeVersion=1.5 \
-DinteractiveMode=false


Move into the project:
cd devops-learning


3. pom.xml
This is the Maven project configuration file.

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.devops</groupId>
    <artifactId>devops-learning</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>



4. Create Java application
File: src/main/java/com/devops/App.java

package com.devops;

public class App {

    public static void main(String[] args) {

        System.out.println("Welcome to DevOps Learning");

    }
}



5. Create unit test
File: src/test/java/com/devops/AppTest.java

package com.devops;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testWelcomeMessage() {

        String message = "Welcome to DevOps Learning";

        assertEquals("Welcome to DevOps Learning", message);

    }
}



6. All important Maven commands
Maven information commands
Command	Purpose
mvn -version	Check Maven version
mvn help	Display Maven help
mvn help:effective-pom	Display effective POM
mvn help:effective-settings	Display effective settings
mvn dependency:tree	Display dependency hierarchy
mvn dependency:list	List dependencies

Maven build lifecycle commands
Maven lifecycle
1. validate
   mvn validate
   
   
   Checks project structure and configuration.
2. compile
   mvn compile
   
   
   Compiles Java source code.
3. test
   mvn test
   
   
   Executes unit tests.
4. package
   mvn package
   
   
   Creates JAR file.
5. verify
   mvn verify
   
   
   Runs checks and verification.
6. install
   mvn install
   
   
   Installs the JAR into local Maven repository.
7. deploy
   mvn deploy
   
   
   Publishes the artifact to a remote Maven repository.

Other useful Maven commands
Command	Purpose
mvn clean	Deletes target directory
mvn clean compile	Clean and compile
mvn clean test	Clean and execute tests
mvn clean package	Clean and package
mvn clean install	Clean and install
mvn package -DskipTests	Package without executing tests
mvn test -Dtest=AppTest	Execute a specific test
mvn -X clean package	Debug Maven build
mvn -q package	Quiet build output
mvn -o package	Offline build

7. Execute the project
First build:
mvn clean package


Output:
[INFO] BUILD SUCCESS


Generated artifact:
target/devops-learning-1.0-SNAPSHOT.jar


Run the Java application:
java -cp target/devops-learning-1.0-SNAPSHOT.jar com.devops.App


Expected output:
Terminal output

Welcome to DevOps Learning



8. Maven commands in Jenkins pipeline
Create a Jenkinsfile in your project root.

pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
            }
        }

        stage('Validate') {
            steps {
                sh 'mvn validate'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Maven build successful!'
        }

        failure {
            echo 'Maven build failed!'
        }

        always {
            echo 'Pipeline execution completed'
        }
    }
}



Before running, configure JDK17 and Maven3 under Manage Jenkins → Tools. Add a Git checkout stage when connecting the project to a repository.
9. Practice these commands in sequence
Maven hands-on checklist
0/10 completed



[ ] 
mvn -version


[ ] 
mvn validate


[ ] 
mvn compile


[ ] 
mvn test


[ ] 
mvn package


[ ] 
mvn verify


[ ] 
mvn install


[ ] 
java -cp target/devops-learning-1.0-SNAPSHOT.jar com.devops.App


[ ] 
mvn clean


[ ] 
mvn clean package


Reset checklist
Recommended next step: Use this same project to learn a complete DevOps CI/CD workflow:

GitHub → Jenkins → Maven → JUnit → SonarQube → Docker → ECR → Kubernetes (EKS) → Argo CD.

That will give you a practical project covering the most important Maven commands and Jenkins integration.












You're using the free version of ChatGPT. Upgrade for advanced models, better memory, and higher-quality image creation.
Try Plus free
