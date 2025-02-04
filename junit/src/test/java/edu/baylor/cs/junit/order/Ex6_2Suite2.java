package edu.baylor.cs.junit.order;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import edu.baylor.cs.junit.params.Ex3MethodSourceExampleTest;
import edu.baylor.cs.junit.params.Ex4CsvSourceExampleTest;
import edu.baylor.cs.junit.params.Ex5CsvFileSourceExampleTest;


/**
 * JUnit bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=512772
 * 
 * @author cerny
 *
 */
@RunWith(JUnitPlatform.class)
//@IncludeEngines("junit-jupiter")
@SelectClasses( { Ex3MethodSourceExampleTest.class, 
	Ex4CsvSourceExampleTest.class, 
	Ex5CsvFileSourceExampleTest.class } )
@IncludeClassNamePatterns({"^.*Tests?$"})
public class Ex6_2Suite2 {
	
}