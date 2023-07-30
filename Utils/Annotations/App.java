/*
Annotations are a form of metadata in Java that provide additional information, markers, or
instructions to the Java compiler or runtime. They are represented by special Java language
constructs that start with the `@` symbol, followed by the annotation name. Annotations can be
applied to various program elements, including classes, methods, fields, parameters, and more.

Annotations do not directly affect the behavior of the annotated code; instead, they serve as a
means to communicate information to tools, frameworks, or other parts of the Java ecosystem.
Annotations are processed at compile-time or runtime by various tools and libraries to enable
specific behaviors, code generation, or configurations.

Here are some key points about annotations in Java:

1. Reflection: Annotations can be accessed and processed at runtime using Java's reflection API,
enabling tools and frameworks to read and interpret annotations dynamically.

2. Syntax: Annotations are represented by `@annotationName` and are placed immediately before the
element they annotate. For example, to annotate a class with an annotation called `MyAnnotation`,
you would write: `@MyAnnotation`.

3. Built-in Annotations: Java comes with several built-in annotations, such as `@Override`,
`@Deprecated`, and `@SuppressWarnings`, which are used to provide hints or instructions to the
compiler or to signify deprecation.

4. Custom Annotations: Java allows developers to define their own custom annotations. Custom
annotations can be used to convey specific information to custom tools, libraries, or frameworks
designed for particular use cases.

5. Retention Policy: Annotations can have different retention policies, determining whether they
are available during source code compilation, class file generation, or runtime. The three
retention policies are `SOURCE`, `CLASS`, and `RUNTIME`.

6. Annotation Processors: Annotation processors are tools that can read and process annotations
during compilation. They can generate additional code, perform validation, or generate other
resources based on the information provided by annotations.

Annotations are widely used in modern Java development to enable various functionalities, such as
dependency injection, object-relational mapping, RESTful web services, testing, documentation
generation, and more. They provide a flexible and extensible way to convey information about code
elements without adding direct code-level functionality.

Resources:
[1] https://www.youtube.com/watch?v=DkZr7_c9ry8
[2] https://www.youtube.com/watch?v=Vbc1YHVWFhg
[3] https://www.youtube.com/watch?v=ja4is9oq37k
[4] https://chat.openai.com/c/16f99044-4b80-44a9-b2c5-05a1bd71a56a [search phrase: "Annotations are a powerful feature"]
[5] https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
[6] https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html
*/

import java.util.Arrays;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class App {
	public static void main(String[] args) {
		Animal dog = new Dog("Firulais", 7, "Rikimbili");
		Animal cat = new Cat("Tom", 3);
		System.out.println("-----------------------------------------------------------");
		System.out.println(dog.getClass().getSimpleName());
		System.out.println(cat.getClass().getSimpleName());
		System.out.println("-----------------------------------------------------------");

		if (dog.getClass().isAnnotationPresent(Bark.class)) {
			System.out.println("Object dog, from class Dog, is annotated with @Bark"); //IMPORTANT, in this case annotations is about the class, not the object.
		}
		System.out.println("-----------------------------------------------------------");

		Arrays.stream(cat.getClass().getDeclaredMethods())
						 .filter(p -> p.isAnnotationPresent(RunImmediately.class))
						 .filter(p -> p.isAnnotationPresent(Times.class)) 
						 .forEach(p -> {
							try {
								Times annotation = p.getAnnotation(Times.class);
								for (int i = 0; i < annotation.times(); i++) p.invoke(cat);
							} catch (IllegalAccessException | InvocationTargetException e) {
								e.printStackTrace();
							}
		});
		System.out.println("-----------------------------------------------------------");

		for (Field field : dog.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(ImportantString.class)) {
				try {
					Object objectValue = field.get(dog);
					if (objectValue instanceof String) System.out.printf("The value of that important field is %s.%n", field.get(dog));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

@Bark
class Dog extends Animal {

	@ImportantString
	String nickname; //This field is not declared as private to not having to deal with the reflection access using the annotation. Otherwise, exception thing.

	public Dog(String name, int age, String nickname) {
		super(name, age);
		this.nickname = nickname;
	}
}

class Cat extends Animal {
	public Cat(String name, int age) {
		super(name, age);
	}

	@RunImmediately
	//@Times(times = 10)
	@Times //Will get the default value which is 5 (instead of 10).
	public void meow() {
		System.out.println("Meowwwww!");
	}
}

abstract class Animal {
	private final String name;
	private final int age;

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

@Target(ElementType.TYPE) //This means this annotation can only be used on classes (ElementType.METHOD is for methods only)
@Retention(RetentionPolicy.RUNTIME)
@interface Bark {}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RunImmediately {}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Times {
	int times() default 5; //The method can be of a primitive type (int in this case), a class type, a String, or an array of any of those.
						   //If you don't specify a value, it will take 5 as default.
}

@Target(ElementType.FIELD)  //FIELD indicates it's only for member variables (fields of a class)
@Retention(RetentionPolicy.RUNTIME)
@interface ImportantString {}
