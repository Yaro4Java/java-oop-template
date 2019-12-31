package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;
import com.epam.izh.rd.online.service.SimpleAuthorService;
import com.epam.izh.rd.online.service.SimpleSchoolBookService;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.util.Assert;


import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

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


        {/* SECTION FOR DEBUGGING CLASSES */

            System.out.println("\n/* SECTION FOR DEBUGGING CLASSES */\n");

            System.out.println("********** Working with authors repository **********\n");

            Author author1 = new Author("Yaroslav", "Kozlov", LocalDate.of(1974,10,05), "Russia");
            Author author2 = new Author("James", "Bond", null, "UK");
            Author author3 = new Author("Wolfgang", "Mozart", LocalDate.of(1756,01,27), "Austria");

            SimpleAuthorRepository rep4Authors = new SimpleAuthorRepository();

            System.out.println("author1.toString(): " + author1.toString());
            System.out.println("author2.toString(): " + author2.toString());
            System.out.println("author3.toString(): " + author3.toString());

            System.out.println("\nNumber of authors saved in repository is " + rep4Authors.count());

            System.out.println("\nSaving author1 in repository");
            rep4Authors.save(author1);
            System.out.println("Number of authors saved in repository is " + rep4Authors.count());

            System.out.println("\nSaving author2 in repository");
            rep4Authors.save(author2);
            System.out.println("Number of authors saved in repository is " + rep4Authors.count());

            System.out.println("\nSaving author3 in repository");
            rep4Authors.save(author3);
            System.out.println("Number of authors saved in repository is " + rep4Authors.count());

            Author found;
            found = rep4Authors.findByFullName("James", "Bond");
            System.out.println("\nIs James Bond in repository? " + (found == null ? "No: " : "Yes: ") + found);
            found = rep4Authors.findByFullName("Vasya", "Pupkin");
            System.out.println("Is Vasya Pupkin in repository? " + (found == null ? "No: " : "Yes: ") + found);

            Author vasya = new Author("Vasya","Pupkin",null, null);
            System.out.println("\nSaving author Vasya Pupkin in repository");
            rep4Authors.save(vasya);
            System.out.println("Number of authors saved in repository is " + rep4Authors.count());

            found = rep4Authors.findByFullName("Vasya", "Pupkin");
            System.out.println("Is Vasya Pupkin in repository? " + (found == null ? "No: " : "Yes: ") + found);

            System.out.println("\nRemoving author Vasya Pupkin from repository");
            rep4Authors.remove(vasya);
            System.out.println("Number of authors saved in repository is " + rep4Authors.count());
            found = rep4Authors.findByFullName("Vasya", "Pupkin");
            System.out.println("Is Vasya Pupkin in repository? " + (found == null ? "No: " : "Yes: ") + found);

            System.out.println("\n********** Working with books repository **********\n");

            SchoolBook book1 = new SchoolBook(250, "The hound of the Baskervilles", "Arthur", "Conan Doyle", null);
            SchoolBook book2 = new SchoolBook(300, "The hound of the Baskervilles", "Arthur", "Doyle", null);
            SchoolBook book3 = new SchoolBook(1000, "Short stories", "James", "Bond", null);

            SimpleSchoolBookRepository rep4Books = new SimpleSchoolBookRepository();

            System.out.println("book1.toString(): " + book1.toString());
            System.out.println("book2.toString(): " + book2.toString());
            System.out.println("book3.toString(): " + book3.toString());

            System.out.println("\nNumber of books saved in repository is " + rep4Books.count());

            System.out.println("\nSaving book1 in repository");
            rep4Books.save(book1);
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            System.out.println("\nSaving book2 in repository");
            rep4Books.save(book2);
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            System.out.println("\nSaving book3 in repository");
            rep4Books.save(book3);
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            SchoolBook[] foundBooks;
            foundBooks = rep4Books.findByName("The hound of the Baskervilles");
            System.out.println("\nIs \"The hound of the Baskervilles\" in repository? " + (foundBooks.length == 0 ? "No books with such a title!" : "Yes: 1 of " + foundBooks.length + " is an " + foundBooks[0].toString() ));

            SchoolBook vasyasBook = new SchoolBook(500, "Memoirs", "Vasya", "Pupkin", null);

            foundBooks = rep4Books.findByName("Memoirs");
            System.out.println("\nIs \"Memoirs\" in repository? " + (foundBooks.length == 0 ? "No books with such a title!" : "Yes: 1 of " + foundBooks.length + " is an " + foundBooks[0].toString() ));

            System.out.println("\nSaving the book \"Memoirs\" by Vasya Pupkin in repository");
            rep4Books.save(vasyasBook);
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            foundBooks = rep4Books.findByName("Memoirs");
            System.out.println("\nIs \"Memoirs\" in repository? " + (foundBooks.length == 0 ? "No books with such a title!" : "Yes: 1 of " + foundBooks.length + " is an " + foundBooks[0].toString() ));

            System.out.println("\nRemoving \"Memoirs\" from repository");
            rep4Books.removeByName("Memoirs");
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            System.out.println("\nRemoving \"The hound of the Baskervilles\" from repository");
            rep4Books.removeByName("The hound of the Baskervilles");
            System.out.println("Number of books saved in repository is " + rep4Books.count());

            foundBooks = rep4Books.findByName("Memoirs");
            System.out.println("\nIs \"Memoirs\" in repository? " + (foundBooks.length == 0 ? "No books with such a title!" : "Yes: 1 of " + foundBooks.length + " is an " + foundBooks[0].toString() ));

            foundBooks = rep4Books.findByName("The hound of the Baskervilles");
            System.out.println("\nIs \"The hound of the Baskervilles\" in repository? " + (foundBooks.length == 0 ? "No books with such a title!" : "Yes: 1 of " + foundBooks.length + " is an " + foundBooks[0].toString() ));

            System.out.println("\n********** Using services ***********\n");

            // Creating service with methods for authors repository
            SimpleAuthorService ser4Authors = new SimpleAuthorService(rep4Authors);

            // Creating service with functionality to work with books taking in account given authors repository
            SimpleSchoolBookService ser4Books = new SimpleSchoolBookService(rep4Books, ser4Authors);

            System.out.println("Number of books in storage is: " + ser4Books.count());
            System.out.println("Number of authors registered is: " + ser4Authors.count());

            System.out.println("\nRecording author Vasya Pupkin into authors repository ...");
            rep4Authors.save(vasya);
            System.out.println("Now the number of registered authors is: " + ser4Authors.count());

            System.out.println("\nAdding 5 instances of the book \"Memoirs\" by Vasya Pupkin to book storage ...");
            for (int i=0; i < 5; i++) {
                rep4Books.save(vasyasBook);
            }
            System.out.println("Now total number of books in the storage is: " + ser4Books.count());
            System.out.println("Number of book \"Memoirs\" in the storage is: " + ser4Books.getNumberOfBooksByName("Memoirs") + " of " + ser4Books.count());
            System.out.println("1st found \"Memoirs\" is: " + ser4Books.findByName("Memoirs")[0]);
            System.out.println("The author of \"Memoirs\" is: " + ser4Books.findAuthorByBookName("Memoirs"));

            System.out.println("\nRemoving all (5) instances of the book \"Memoirs\" by Vasya Pupkin from the book storage ...");
            ser4Books.removeByName("Memoirs");
            System.out.println("Now total number of books in the storage is: " + ser4Books.count());
            System.out.println("Number of book \"Memoirs\" in the storage is: " + ser4Books.getNumberOfBooksByName("Memoirs") + " of " + ser4Books.count());

            System.out.println("\n/* END OF DEBUGGING CLASSES */\n");

        }/* END OF DEBUGGING CLASSES */

        System.exit(0);
    }

}
