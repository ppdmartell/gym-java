/*
There are three (+1 custom) class loaders in Java:

1. Bootstrap class loader: A bootstrap or primordial class loader is the parent of all the others
and contains JDK internal classes, typically rt.jar and other core libraries located in the
$JAVA_HOME/jre/lib directory. For example, the ArrayList class belongs here along other classes
in common and built-in packages.

2. Extension class loader: Extension class loaders load classes that are an extension of the
standard core Java classes. These extensions lie under the directory $JAVA_HOME/lib/ext.

3. System class loader: An application or system class loader loads our own files in the classpath.
It's the code (classes and resources) we write.

The Java class-loading mechanism follows the order 1-2-3, being the boostrap class loader
the first one. The class loaders belong to the JVM (Java Virtual Machine) and they are responsible
for loading classes dynamically to the JVM during runtime. Due to this, when the JVM is about to
execute bytecode (.class files) doesn't need to know about the underlying files or file systems
because the class loaders do this for it.
Furthermore, these Java classes aren't loaded into memory all at once, but rather when they're
required by an application. Some of the text here is based on [1].

Resources:
[1] https://www.baeldung.com/java-classloaders

*/

import java.util.ArrayList;

class App implements Contract {
	public static void main(String[] args) {

		System.out.println("------------------------BOOTSTRAP CLASS LOADER-------------------------------------------");
		System.out.printf("Class loader for ArrayList class: %s.%n", ArrayList.class.getClassLoader());
		System.out.printf("Class loader for Object class: %s.%n", Object.class.getClassLoader());

		System.out.println("------------------------EXTENSION CLASS LOADER-------------------------------------------");
		//I COULDN'T FIND ANY EXAMPLE FOR AN EXTENSIO, MY /lib/ext/ FOLDER WAS MISSING. SO I AM HARD-CODING A FAKE ONE FROM [1]
		System.out.println("Class loader for MyExtension class: jdk.internal.loader.ClassLoaders$PlatformClassLoader@1fb700ee");

		System.out.println("------------------------SYSTEM (APPLICATION) CLASS LOADER--------------------------------");
		System.out.printf("Class loader for this(App) class: %s.%n", App.class.getClassLoader());
		//System.out.printf("Class loader for interface Contract: %s.%n", Contract.interface.getClassLoader()); YOU CAN'T GET THE CLASS LOADER FOR AN INTERFACE :(
		//However, you do can get the class loader for a class that implements an interface.

	}
}

interface Contract {}
