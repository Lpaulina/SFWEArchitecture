package edu.baylor.cs.junit.tag;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("edu.baylor.cs.junit.tag")
@IncludeTags("debug")
@IncludeClassNamePatterns({"^.*Tests?$"})
public class Ex8_2RunTagDebug
{
}