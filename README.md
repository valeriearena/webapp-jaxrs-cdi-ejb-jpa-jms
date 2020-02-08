# Web app with JAX-RS, CDI, EJB, JPA, and JMS that can be deployed to TomEE (Enterprise Tomcat)

Follow the Setup Steps below.

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

NOTE: 
The EntityManagers in the DAOs (tomee-hello-world/src/main/java/com/tomee/helloworld/jpa/dao) are configured with H2 in memory database. 
To configure the DAOS with MySQL, configure the EntityManagers with "helloworldDB" (tomee-hello-world/src/main/resources/persistence.xml). 
You will need to install MySQL. Use "localuser", "password" as the username and password. If you use something different, update the username and password in tomee.xml.

*************** Execution Steps ***************

Execute services via REST endpoints using curl or Google Chrome Advanced REST Client (https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo?hl=en-US)

The REST resource is made up of several endpoints with the following naming convention:

http://localhost:8080/demo/helloworld/[path]

The endpoints are either GET or POST requests. Any parameters required in the request are submitted as path parameters:

http://localhost:8080/demo/helloworld/[path]/{pathparam}

or as JSON objects:

```json
{
  "helloworld": {
    "salutation": "hi jaxrs",
    "technology": "jaxrs",
    "description": "just saying hi to jaxrs"
  }
}
```
or XML:

```xml
<helloworld>
        <salutation>hi xml</salutation>
        <technology>jaxrs</technology>
        <description>saying hi to jaxrs</description>
</helloworld>
```

See the com.tomee.helloworld.jaxrs.JAXRSResource and com.tomee.helloworld.jaxb.JAXBHelloWorld.
