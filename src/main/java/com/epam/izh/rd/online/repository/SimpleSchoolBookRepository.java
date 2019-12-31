package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

/**
 * Класс SimpleSchoolBookRepository репозитория для хранения данных о книгах
 * <p>
 * Необходимо:
 * 1) Имплементировать интерфейс BookRepository<SchoolBook> ( с указанием дженерика )
 * 2) Добавить все методы (пока можно не писать реализацию)
 * 3) Добавить приватное поле "SchoolBook[] schoolBooks" для хранения школьных книг
 * 4) Инициалазировать его пустым массивом
 * 5) Написать реализацию для всех методов (коллекции не используем, работаем только с массивами)
 */
public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = {};
    int counter = 0;

    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    @Override
    public boolean save(SchoolBook book) {

        if (book == null) {
            return false;
        } else {

            SchoolBook[] temp;
            temp = schoolBooks;
            schoolBooks = new SchoolBook[counter + 1];

            for (int i = 0; i < counter; i++) {
                schoolBooks[i] = temp[i];
            }

            schoolBooks[counter++] = book;
            return true;
        }
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    @Override
    public SchoolBook[] findByName(String name) {

        int match = 0;

        // Counting number of books in repository with the same given name
        for (SchoolBook b : schoolBooks) {
            if(b.getName() == name) {
                match++;
            }
        }

        if (match == 0) { // If no matching, return empty SchoolBook array
            return new SchoolBook[0];
        } else { // If there is something, add books with same name to special temp[] array and return it

            SchoolBook[] temp = new SchoolBook[match];

            for (int i = 0, k = 0; i < counter; i++) {
                if (schoolBooks[i].getName() == name) {
                    temp[k++] = schoolBooks[i];
                }
            }

            return temp;
        }
    }

    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */
    @Override
    public boolean removeByName(String name) {

        int match = 0;

        // Counting number of books in repository with the same given name
        for (SchoolBook b : schoolBooks) {
            if(b.getName() == name) {
                match++;
            }
        }

        if (match == 0) { // If no matching, return false
            return false;
        } else { // If there is something, create new schoolBooks[] array without books with the given name and return true

            SchoolBook[] temp = new SchoolBook[counter - match];

            for (int i = 0, k = 0; i < counter; i++) {
                if (schoolBooks[i].getName() != name) {
                    temp[k++] = schoolBooks[i];
                }
            }

            schoolBooks = temp;
            counter = schoolBooks.length;
            return true;
        }
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return counter;
    }
}
