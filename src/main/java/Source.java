import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class Source {

    enum MyEnum {A, B}

    public static void main(String[] args) throws ClassNotFoundException {

        // -- Object.getClass() --
        System.out.println("\nObject.getClass()");

        Class stringClass = "foo".getClass();
        System.out.println("stringClass name: " + stringClass.getName());

//        Class consoleClass = System.console().getClass();
//        System.out.println("consoleClass name: " + consoleClass.getSimpleName());

        Class myEnumAClass = MyEnum.A.getClass();
        System.out.println("MyEnum.A name: " + myEnumAClass.getName());
        Class myEnumBClass = MyEnum.B.getClass();
        System.out.println("MyEnum.B class name: " + myEnumBClass.getName());
        if (myEnumAClass.equals(myEnumBClass)) {
            System.out.println("MyEnum.A and MyEnum.B classes are equal!");
        }
        if (myEnumAClass == myEnumBClass) {
            // true, since for every object type JVM instantiates it's instance of Class
            System.out.println("MyEnum.A and MyEnum.B classes are the same instance!");
        }

        byte[] bytes = new byte[1024];
        Class byteArrayClass = bytes.getClass();
        System.out.println("Byte array class name: " + byteArrayClass.getSimpleName());

        int x = 43;
        // Impossible, int is primitive!
        // Class primitiveIntClass = x.getClass();

        // In this case Set is an interface to an object HashSet
        // getClass() returns HashSet
        Set<String> s = new HashSet<String>();
        Class hashSetClass = s.getClass();
        System.out.println("Set/HashSet class name: " + hashSetClass.getName());


        // -- The .class Syntax --
        System.out.println("\nThe .class Syntax:");
        // If the type is available but there is no instance, then it is possible to obtain a
        // Class by appending *.class to the name of that type. This is also the easiest way to
        // obtain the Class for a primitive type:
        boolean b;
//        Class c = b.getClass // compile-time error, b is primitive
        Class booleanClass=boolean.class; // correct
        System.out.println("boolean class name: " + booleanClass.getName());

        Class printStreamClass=PrintStream.class;
        System.out.println("PrintStream class name: " + printStreamClass.getName());

        Class multiDimArrayClass = int[][][].class;
        System.out.println("Multi-dimensional array class name: " + multiDimArrayClass.getSimpleName());


        // -- Class.forName() --
        System.out.println("\nClass.forName():");
        // If the fully-qualified name of a class is available, it is possible to get the corresponding Class
        // using the static method Class.forName(). This cannot be used for primitive types. The syntax for
        // names of array classes is described by Class.getName(). This syntax is applicable to references
        // and primitive types.

        // may throw ClassNotFoundException
        Class arrayListClass = Class.forName("java.util.ArrayList");
        System.out.println("ArrayList class name: " + arrayListClass.getName());
        Class doubleArrayClass=Class.forName("[D");
        System.out.println("Double array class name: " + doubleArrayClass.getSimpleName());
        System.out.println("real name: " + doubleArrayClass.getName());
        Class stringArrayClass=Class.forName("[[Ljava.lang.String;");
        System.out.println("String array class name: " + stringArrayClass.getSimpleName());
        System.out.println("real name: " + stringArrayClass.getName());


        // -- TYPE Field for Primitive Type Wrappers --
        System.out.println("\nTYPE Field for Primitive Type Wrappers:");
        // The .class syntax is a more convenient and the preferred way to obtain the Class for a primitive type;
        // however there is another way to acquire the Class. Each of the primitive types and void has a wrapper
        // class in java.lang that is used for boxing of primitive types to reference types. Each wrapper class
        // contains a field named TYPE which is equal to the Class for the primitive type being wrapped.

        Class doubleWrapperClass = Double.TYPE;
        System.out.println("Double class name (from wrapper): " + doubleWrapperClass.getSimpleName());
        Class voidClassName = Void.TYPE;
        System.out.println("Void class name (from wrapper): " + voidClassName.getSimpleName());


        // -- Methods that Return Classes --
        System.out.println("\nMethods that Return Classes:");
        // There are several Reflection APIs which return classes but these may only be accessed
        // if a Class has already been obtained either directly or indirectly

        // Class.getSuperclass() - returns the super class for the given class
        Class arrayListParentClass=arrayListClass.getSuperclass();
        System.out.println("ArrayList's parent is " + arrayListParentClass.getName());

        // Get the interfaces that given class implements
        Class[] arrayListInterfaces = arrayListClass.getInterfaces();
        for(Class arrayListInterface:arrayListInterfaces){
            System.out.println("ArrayList implements " + arrayListInterface.getName());
        }

        // Class.getClasses() - Returns all the public classes, interfaces, and enums that are members of the class including inherited members.
        Class[] personMembers = Person.class.getClasses();
        System.out.println("Person members:");
        for(Class personMember:personMembers){
            System.out.println("\t"+personMember.getName());
        }
        System.out.println("end.");
    }
}
