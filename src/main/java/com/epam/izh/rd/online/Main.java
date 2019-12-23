package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Author;

public class Main {

    public static void main(String[] args) {

        {/* SECTION FOR DEBUGGING CLASS Author */

            Author author1 = new Author();

            author1.setName("Yaroslav");
            author1.setCountry("Russia");

            System.out.println(author1.toString());
            System.out.println("\nHash code is " + author1.hashCode());

        }/* END OF DEBUGGING CLASS Author */

    }

}
