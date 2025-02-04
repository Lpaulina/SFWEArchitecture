package edu.baylor.cs.junit.order;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;


/**
 * JUnit bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=512772
 * 
 * @author cerny
 *
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("edu.baylor.cs.junit.hooks")
//@SelectClasses( { Ex3MethodSourceExampleTest.class, 
//	Ex4CsvSourceExampleTest.class, 
//	Ex5CsvFileSourceExampleTest.class } )
@IncludeClassNamePatterns("^.*Tests?$")
public class Ex6_2Suite {
	
}