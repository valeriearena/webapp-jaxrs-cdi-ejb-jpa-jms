package com.tomee.helloworld.ejb;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmbeddedContainerTest {

	private EJBContainer ejbContainer;
	private Context context;
	private String jndiPrefix;

	public void setUp() throws Exception {
		
		Properties p = getProperties();
		setUp(p);
		
	}
	public void setUp(Properties p) throws Exception {

	
//        ClassLoader cl = ClassLoader.getSystemClassLoader();
//        URL[] urls = ((URLClassLoader)cl).getURLs();
//        for(URL url: urls){
//        	System.out.println(ur		

		ejbContainer = EJBContainer.createEJBContainer(p);
		context = ejbContainer.getContext();

		NamingEnumeration bindings = context.listBindings("java:global");
		List<String> bindingList = new ArrayList<String>();
		while (bindings.hasMore()) {
			Binding bd = (Binding) bindings.next();
			bindingList.add(bd.getName());
		}
		if (bindingList.contains("ABBWebServices")) {
			jndiPrefix = "java:global/tomee-hello-world/";
		}
	}
	
	public Properties getProperties(){
		
		Properties p = new Properties();
		
		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.openejb.client.LocalInitialContextFactory");
		
//		p.put("abbmwdb.hibernate.connection.driver_class","org.hsqldb.jdbc.JDBCDriver");
//		p.put("abbmwdb.hibernate.dialect","org.hibernate.dialect.H2Dialect");
//		p.put("abbmwdb.hibernate.connection.url", "jdbc:hsqldb:mem:test");
//		p.put("abbmwdb.hibernate.connection.password", "");
//		p.put("abbmwdb.hibernate.archive.autodetection", "");
//		p.put("abbmwdb.hibernate.hbm2ddl.auto", "create-drop");		
//		p.put("abbmwdb.hibernate.show_sql", true);		
//		p.put("abbmwdb.hibernate.format_sql", true);
//		p.put("abbmwdb.hibernate.use_sql_comments", true);
		
//		p.put("openjpa.ConnectionDriverName","org.h2.Driver");
//		p.put("openjpa.ConnectionURL", "jdbc:h2:mem:helloworldDB");
//		p.put("openjpa.jdbc.SynchronizeMappings", "buildSchema(SchemaAction='drop,add')");
//		p.put("openjpa.Log", "DefaultLevel=TRACE,SQL=TRACE");
//		p.put("openjpa.ConnectionFactoryProperties", "PrintParameters=true");
//		p.put("openjpa.RuntimeUnenhancedClasses", "warn");
//
		//p.put("java:/ConnectionFactory", "new://Resource?type=javax.jms.ConnectionFactory");
		//p.put("ABBWebServices.Comp", "new://Resource?type=javax.jms.Destination");
				
		p.put("log4j.category.OpenEJB.options", " debug");
		p.put("log4j.category.OpenEJB.startup", " debug");
		p.put("log4j.category.OpenEJB.startup.config", " debug");
		p.put("log4j.category.OpenEJB.startup.service", "debug");
		
		return p;
		
//		p.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.openejb.client.LocalInitialContextFactory");
//		p.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//		//p.put("hibernate.default_schema", "abbmwschem");
//		p.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//		p.put("hibernate.auto-import", "false");
//		p.put("hibernate.connection.username", "root");
//		p.put("hibernate.archive.autodetection", "");
//		p.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/abbmwschem?transformedBitIsBoolean=true&verifyServerCertificate=false&sessionVariables=storage_engine=InnoDB");
//		p.put("hibernate.show_sql", "true");
//		p.put("hibernate.connection.password", "");


//*********************** DEBUG PROPERTIES *******************************************
//		p.put("log4j.category.OpenEJB.options", " debug");
//		p.put("log4j.category.OpenEJB.startup", " debug");
//		p.put("log4j.category.OpenEJB.startup.config", " debug");
//		p.put("log4j.category.OpenEJB.startup.service", "debug");

//*********************** CLASSPATH RELATED PROPERTIES *******************************************
//		p.setProperty("openejb.altdd.prefix", "test");		
//		p.put("openejb.deployments.classpath.ear", false);
		
//		p.put("openejb.exclude-include.order", "exclude-include");
//		p.put("openejb.deployments.classpath ", false);
//		p.put("openejb.deployments.classpath.filter.descriptors", true);	
//		p.put("openejb.deployments.classpath.include","file:///C:/Users/varena/GitHub/ABBWebServices/target/classes/com/abb/persistence/");
		
//		p.put("openejb.deployments.package.include",".*persistence.*");
//		p.put("openejb.deployments.package.exclude",".*business.*");
//
//		p.put("openejb.classloader.forced-skip.forced-skip","target/test-classes/META-INF/persistence.xml");
//		p.put("openejb.classloader.forced-load","com.abb.business.");
		
//		p.put(EJBContainer.MODULES, new File("target/classes"));
//		p.put(EJBContainer.MODULES, new File[]{new File("target/classes/com/abb/business"), new File("target/classes/com/abb/persistence"), new File("target/classes/com/abb/camel")});  
		
	}

	public void close() throws Exception {
		ejbContainer.close();
	}

	public String getJndiPrefix() {
		return jndiPrefix;
	}

	public Context getContext() {
		return context;
	}
}
