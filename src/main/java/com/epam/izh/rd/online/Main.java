package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.springframework.util.Assert;
import org.springframework.util.SocketUtils;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        {/* SECTION FOR DEBUGGING CLASS Author */
//
//            Author author1 = new Author();
//
//            author1.setName("Yaroslav");
//            author1.setCountry("Russia");
//
//            class FreakAuthor extends Author {
//                public FreakAuthor(String name) {
//                    this.setName(name);
//                }
//
//                @Override
//                public int hashCode() {
//                    return Object.class.hashCode();
//                }
//
//            }
//
//            FreakAuthor freak = new FreakAuthor("John");
//
//            System.out.println("author1.objectToString(): " + author1.objectToString());
//            System.out.println("author1.toString(): " + author1.toString());
//            System.out.println("\n" + "author1 is the following " + author1.toString());
//
//            System.out.println("\nHash code1 for author " + author1.getName() + " is " + author1.hashCode());
//            System.out.println("Hash code2 for author " + author1.getName() + " is " + author1.hashCode());
//            System.out.println("Hash code3 for author " + author1.getName() + " is " + author1.hashCode());
//
//            System.out.println("\nHash code1 for author " + freak.getName() + " is " + freak.hashCode());
//            System.out.println("Hash code2 for author " + freak.getName() + " is " + freak.hashCode());
//            System.out.println("Hash code3 for author " + freak.getName() + " is " + freak.hashCode());
//            System.out.println("author1.ToStringHash(): " + author1.ToStringHash());

        }/* END OF DEBUGGING CLASS Author */


        {/* SECTION FOR PLAYING WITH ASSERTIONS */

            System.out.println("\n/* SECTION FOR PLAYING WITH ASSERTIONS */\n");

            try {
                Assert.isTrue(1 == 1, "What? 1 == 2? That's not true!!!");
                System.out.println("Assertion is successful. The requirement was met. Good job, man!");
            } /*catch ( IllegalArgumentException e ) {
                System.out.println("What was that?!");
            }*/ catch ( Exception e ) {
                System.out.println("It was just an exception. Don't worry, be happy.");
            }

            System.out.println("\n/* END OF PLAYING WITH ASSERTIONS */\n");

        }/* END OF DEBUGGING ASSERTIONS */


        {/* SECTION FOR PLAYING WITH CONSTRUCTORS */

            System.out.println("\n/* SECTION FOR PLAYING WITH CONSTRUCTORS */\n");

            class A {
                A() {
                    System.out.println("A() constructor is working ...");
                }

                A( String a ) {
                    System.out.println("A('" + a + "') constructor is working ...");
                }

                @Override
                public String toString() {
                    return "instance of class A = {}";
                }
            }

            class B extends A {
                String bField;
                B() {
                    System.out.println("B() constructor is working ...");
                }

                @Override
                public String toString() {
                    return "instance of class B = { " +
                            "bField='" + bField + '\'' +
                            " }";
                }
            }

            class C extends B {
                C() {
                    System.out.println("C() constructor is working ...");
                }

                C(String s) {
                    System.out.println("C('" + s + "') constructor is working ...");
                    bField = s;
                }

                @Override
                public String toString() {
                    return "instance of class C = { " +
                            "bField='" + bField + '\'' +
                            " }";
                }
            }

            System.out.println("Creating instance of class A with the A() constructor:");
            A aInstance1 = new A();
            System.out.println("aInstance1 was just born: " + aInstance1.toString());

            System.out.println("\nCreating instance of class A with the A(String) constructor:");
            A aInstance2 = new A("just a string");
            System.out.println("aInstance2 was just born: " + aInstance2.toString());

            System.out.println("\nCreating instance of class B with the B() constructor:");
            B bInstance = new B();
            System.out.println("bInstance was just born: " + bInstance.toString());

            System.out.println("\nCreating instance of class C with the C() constructor:");
            C cInstance1 = new C();
            System.out.println("cInstance1 was just born: " + cInstance1.toString());

            System.out.println("\nCreating instance of class C with the C(String) constructor:");
            C cInstance2 = new C("some string");
            System.out.println("cInstance2 was just born: " + cInstance2.toString());

            System.out.println("\n/* END OF DEBUGGING CONSTRUCTORS */\n");

        }/* END OF DEBUGGING CONSTRUCTORS */


        {/* SECTION FOR PLAYING WITH OVERLOADING METHODS */

            System.out.println("\n/* SECTION FOR PLAYING WITH OVERLOADING METHODS */\n");

            //Есть 3 перегруженных метода, которые принимают Object List и ArrayList.
            // Мы вызываем функцию с параметром null. Какой метод выполнится?

            class A {

                void method(ObjectList objList) {
                    System.out.println("Method(ObjectList) is running ...");
                }

                void method(ArrayList arrList) {
                    System.out.println("Method(ArrayList) is running ...");
                }

                void method(ObjectList objList, ArrayList arrList) {
                    System.out.println("Method(ObjectList,ArrayList) is running ...");
                }
            }

            A a = new A();
            ObjectList ol = null;
            ArrayList al = null;

            a.method(ol);
            a.method(al);
            a.method(ol, al);

            System.out.println("\n/* END OF DEBUGGING OVERLOADING METHODS */\n");

        }/* END OF DEBUGGING OVERLOADING METHODS */


        {/* SECTION FOR PLAYING WITH BINDING METHODS */

            System.out.println("\n/* SECTION FOR PLAYING WITH BINDING METHODS */\n");

            class Base {
                void method() {
                    System.out.println("The version of method() declared in class Base is running ...");
                }

                void subtleMethod( int par ) {
                    System.out.println("The version of subtleMethod(int par=" + par + ") declared in class Base is running ...");
                }
            }

            class Derived extends Base {
                void method() {
                    System.out.println("The version of method() declared in class Derived is running ...");
                }

                void subtleMethod( byte par ) {
                    System.out.println("The version of subtleMethod(byte par=0b" + Integer.toBinaryString(par) + "=" + par + ") declared in class Derived is running ...");
                }
            }

            // Type of reference variable instance1 is --> Base ( INFO FOR COMPILER = for early binding )
            // Type of the created referenced instance/object is --> Base ( INFO FOR JVM = for late binding )
            Base instanceBB = new Base();

            // Type of reference variable instance2 is --> Base! ( INFO FOR COMPILER = for early binding )
            // Type of the created referenced instance/object is --> Derived ( INFO FOR JVM = for late binding )
            Base instanceBD = new Derived();

            // Type of reference variable instance2 is --> Derived ( INFO FOR COMPILER = for early binding )
            // Type of the created referenced instance/object is --> Derived ( INFO FOR JVM = for late binding )
            Derived instanceDD = new Derived();

            System.out.println("************** Overriding method() ****************");

            System.out.println("\nBase instanceBB = new Base(); --> instanceBB.method():");
            instanceBB.method();

            System.out.println("\nBase instanceBD = new Derived(); --> instanceBD.method():");
            instanceBD.method();

            System.out.println("\nDerived instanceDD = new Derived(); --> instanceDD.method():");
            instanceDD.method();

            System.out.println("\n************** Overloading subtlemethod() ****************\n");

            int parII = 1;
            int parIB = 0b0000010;
            byte parBI = (byte) 3;
            byte parBB = 0b0000100;

            System.out.println("1) Base instanceBB = new Base();");

            System.out.println("\nint parII = 1; ( 0b" + Integer.toBinaryString(parII) + " )");
            System.out.println("Trying instanceBB.subtleMethod(parII):");
            instanceBB.subtleMethod(parII);

            System.out.println("\nint parIB = 0b0000010; ( " + Integer.toString(parIB) + " )");
            System.out.println("Trying instanceBB.subtleMethod(parIB):");
            instanceBB.subtleMethod(parIB);

            System.out.println("\nbyte parBI = (byte) 3; ( 0b" + Integer.toBinaryString(parBI) + " )");
            System.out.println("Trying instanceBB.subtleMethod(parBI):");
            instanceBB.subtleMethod(parBI);

            System.out.println("\nbyte parBB = 0b0000100; ( " + Integer.toString(parBB) + " )");
            System.out.println("Trying instanceBB.subtleMethod(parBB):");
            instanceBB.subtleMethod(parBB);

            System.out.println("\nRESUME:\n\tThe Base type of the reference instanceBB tells compiler that it has to look for the method subtleMethod() inside class Base.");
            System.out.println("\tCompiler sees the only one version of the method subtleMethod() in the class Base so it decides to bind it with the call places in the code - early binding takes place. ");
            System.out.println("\tThere is no late run-time binding because there is no other alternative of the body of the subtleMethod().");


            System.out.println("\n2) Base instanceBD = new Derived();");

            System.out.println("\nint parII = 1; ( 0b" + Integer.toBinaryString(parII) + " )");
            System.out.println("Trying instanceBD.subtleMethod(parII):");
            instanceBD.subtleMethod(parII);

            System.out.println("\nint parIB = 0b0000010; ( " + Integer.toString(parIB) + " )");
            System.out.println("Trying instanceBD.subtleMethod(parIB):");
            instanceBD.subtleMethod(parIB);

            System.out.println("\nbyte parBI = (byte) 3; ( 0b" + Integer.toBinaryString(parBI) + " )");
            System.out.println("Trying instanceBD.subtleMethod(parBI):");
            instanceBD.subtleMethod(parBI);

            System.out.println("\nbyte parBB = 0b0000100; ( " + Integer.toString(parBB) + " )");
            System.out.println("Trying instanceBD.subtleMethod(parBB):");
            instanceBD.subtleMethod(parBB);

            System.out.println("\nRESUME:\n\tThe Base type of the reference instanceBD tells compiler that it should search for the method subtleMethod() inside class Base.");
            System.out.println("\tCompiler sees the only one version of the method subtleMethod() in the class Base so it decides to bind it with the call places in the code - early binding takes place. ");
            System.out.println("\tThere is no late run-time binding because there is no other alternative of the body of the subtleMethod().");


            System.out.println("\n3) Derived instanceDD = new Derived();");

            System.out.println("\nint parII = 1; ( 0b" + Integer.toBinaryString(parII) + " )");
            System.out.println("Trying instanceBD.subtleMethod(parII):");
            instanceDD.subtleMethod(parII);

            System.out.println("\nint parIB = 0b0000010; ( " + Integer.toString(parIB) + " )");
            System.out.println("Trying instanceDD.subtleMethod(parIB):");
            instanceDD.subtleMethod(parIB);

            System.out.println("\nbyte parBI = (byte) 3; ( 0b" + Integer.toBinaryString(parBI) + " )");
            System.out.println("Trying instanceDD.subtleMethod(parBI):");
            instanceDD.subtleMethod(parBI);

            System.out.println("\nbyte parBB = 0b0000100; ( " + Integer.toString(parBB) + " )");
            System.out.println("Trying instanceDD.subtleMethod(parBB):");
            instanceDD.subtleMethod(parBB);

            System.out.println("\nRESUME:\n\tThe Derived type of the reference instanceDD tells compiler that it should search for the method subtleMethod() inside class Derived.");
            System.out.println("\tCompiler sees two alternative versions of the method subtleMethod() in the class Derived - inherited from class Base and reloaded version.");
            System.out.println("\tSo the compiler decides that it's not yet the time to bind the body of the subtleMethod() and so this time the early ( static ) binding is declined.");
            System.out.println("\tDuring run-time JVM chooses proper version of the subtleMethod() body and binds it with the call place - the late ( dynamic ) binding occurs.");

            System.out.println("\n/* END OF DEBUGGING BINDING METHODS */\n");

        }/* END OF DEBUGGING BINDING METHODS */

        System.exit(0);
    }

}
