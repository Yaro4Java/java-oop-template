package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;
import org.springframework.util.Assert;
import org.springframework.util.SocketUtils;

public class Main {

    public static void main(String[] args) {

        {/* SECTION FOR DEBUGGING CLASS Author */

            Author author1 = new Author();

            author1.setName("Yaroslav");
            author1.setCountry("Russia");

            class FreakAuthor extends Author {
                public FreakAuthor(String name) {
                    this.setName(name);
                }

                @Override
                public int hashCode() {
                    return Object.class.hashCode();
                }

            }

            FreakAuthor freak = new FreakAuthor("John");

            System.out.println(author1.toString());
            System.out.println("\nHash code1 for author " + author1.getName() + " is " + author1.hashCode());
            System.out.println("Hash code2 for author " + author1.getName() + " is " + author1.hashCode());
            System.out.println("Hash code3 for author " + author1.getName() + " is " + author1.hashCode());

            System.out.println("\nHash code1 for author " + freak.getName() + " is " + freak.hashCode());
            System.out.println("Hash code2 for author " + freak.getName() + " is " + freak.hashCode());
            System.out.println("Hash code3 for author " + freak.getName() + " is " + freak.hashCode());

        }/* END OF DEBUGGING CLASS Author */


        {/* SECTION FOR PLAYING WITH ASSERTIONS */

            try {
                Assert.isTrue(1 == 2, "What? 1 == 2? That's not true!!!");
            } /*catch ( IllegalArgumentException e ) {
                System.out.println("What was that?!");
            }*/ catch ( Exception e ) {
                System.out.println("\nIt was just an exception. Don't worry, be happy.\n");
            }

        }/* END OF DEBUGGING ASSERTIONS */

    }

}
