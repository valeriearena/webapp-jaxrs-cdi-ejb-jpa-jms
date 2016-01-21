# tomee-hello-world
TomEE Hello Worlds with JAX-RS, JAXB, CDI, EJB, JPA, and JMS. 

Follow the Setup Steps and Execution Steps below.

*************** Setup Steps *************** 

1. Download and install TomEE Plus: http://tomee.apache.org/index.html
2. Clone the tomee-hello-world repo.
3. Copy tomee-hello-world/setup/tomee.xml to TOMEE_HOME/conf.
4. Copy tomee-hello-world/setup/logging.properties to TOMEE_HOME/conf.
5. Copy tomee-hello-world/setup/mysql-connector-java-5.1.29-bin.jar to TOMEE_HOME/lib.
6. Copy tomee-hello-world/setup/h2-1.3.176.jar to TOMEE_HOME/lib.
7. Run build.gradle.
8. Copy tomee-hello-world/build/libs/demo.war to TOMEE_HOME/webapps.
9. Tail TOMEE_HOME/logs/catalina.out.

NOTE: The EntityManagers in the DAOs (tomee-hello-world/src/main/java/com/tomee/helloworld/jpa/dao) are configured with H2 in memory database. 

To configure the DAOS with MySQL, configure the EntityManagers with "helloworld". 

You will need to install MySQL. Use "localuser", "password" as the username and password. If you use something different, update the username and password in tomee.xml.

