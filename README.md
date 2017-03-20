checkstyle-requirejavadoctag
======================
A simple example of a [checkstyle] extension to check if a javadoc tag is missing

Build status
========= 
Codeship [ ![Codeship Status for bmaggi/checkstyle-requirejavadoctag](https://codeship.com/projects/9279d8f0-ed64-0134-39d1-4e689037b335/status?branch=master)](https://codeship.com/projects/208589)

Licensing
=========
[![][license img]][license]

Documentation
=========
# Usage :
Create a checkstyle.xml file
 
```xml
<?xml version="1.0" ?>
 
<!DOCTYPE module PUBLIC
  "-//Puppy Crawl//DTD Check Configuration 1.2//EN"
  "http://www.puppycrawl.com/dtds/configuration_1_2.dtd">
<module name="Checker">
	<module name="TreeWalker">
		<module name="com.github.bmaggi.checks.RequiredJavadocTagCheck">
			<property name="customtag" value="@Requirement"/>
		</module>
	</module>
</module>
```  		

Configure the pom.xml
					
```xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-checkstyle-plugin</artifactId>
				<version>2.17</version>
				<dependencies>
					<dependency>
						<groupId>com.github.bmaggi</groupId>
						<artifactId>checkstyle-requirejavadoctag</artifactId>
						<version>0.1.1</version>
					</dependency>
				</dependencies>
				<executions>
					<execution>
						<id>checkstyle</id>
						<phase>validate</phase>
						<goals>
							<goal>check</goal>
						</goals>
						<configuration>
							<configLocation>requirejavadoctag/checkstyle.xml</configLocation>
							<failOnViolation>true</failOnViolation>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
	<reporting>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-checkstyle-plugin</artifactId>
				<version>2.17</version>
				<configuration>
					<configLocation>requirejavadoctag/checkstyle.xml</configLocation>
				</configuration>
			</plugin>
		</plugins>
	</reporting>
```  

Find version in [GitHub releases tab](https://github.com/bmaggi/checkstyle-requirejavadoctag/releases)

# How to make a release
## Check that you are using latest version
```  
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```  

## To release on maven central.
```  
mvn release:clean release:prepare 
```  
follow by
```  
mvn release:perform
```  

[checkstyle]:http://checkstyle.sourceforge.net/
[license]:LICENSE
[license img]:https://img.shields.io/badge/license-GNU%20LGPL%20v2.1-blue.svg
