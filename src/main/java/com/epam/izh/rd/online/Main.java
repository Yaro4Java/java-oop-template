package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.springframework.util.Assert;
import org.springframework.util.SocketUtils;

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

            try {
                Assert.isTrue(1 == 1, "What? 1 == 2? That's not true!!!");
                System.out.println("\nAssertion is successful. The requirement was met. Good job, man!");
            } /*catch ( IllegalArgumentException e ) {
                System.out.println("What was that?!");
            }*/ catch ( Exception e ) {
                System.out.println("\nIt was just an exception. Don't worry, be happy.");
            }

        }/* END OF DEBUGGING ASSERTIONS */


        {/* SECTION FOR PLAYING WITH CONSTRUCTORS */

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

            System.out.println("\nCreating instance of class A with the A() constructor:");
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

        }/* END OF DEBUGGING CONSTRUCTORS */

        System.exit(0);
    }

}
