package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

/**
 * Класс SimpleSchoolBookService для выполнения бизнес логики при работе с книгами и авторами и взаимодействием с
 * репозиторием для книг BookRepository и сервисом для авторов AuthorService.
 * <p>
 * Необходимо:
 * 1) Имплементировать интерфейс BookService
 * 2) Добавить все методы (пока можно не писать реализацию)
 * 3) Добавить приватное поле "BookRepository<SchoolBook> schoolBookBookRepository" - это репозиторий
 * книг к которому вы будете обращаться в методах
 * 4) Добавить приватное поле "AuthorService authorService" - это сервис для работы с авторами, к которому
 * вы будете обращаться в методах
 * 5) Создать дефолтный конструтор (без параметров)
 * 6) Создать конструтор с параметрами "BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService"
 * (который будет устанвливать в поле schoolBookBookRepository и в поле authorService значения)
 * 7) Написать реализацию для всех методов
 */
public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    /**
     * Метод должен сохранять книгу.
     *
     * Перед сохранением книги нужно проверить, сохранен ли такой автор в базе авторов.
     * То есть вы должны взять имя и фамилию автора из книги и обратиться к сервису авторов и узнать о наличии такого автора.
     * Напомню, что мы считаем, что двух авторов с одинаковыми именем и фамилией быть не может.
     *
     * Если такой автор сущесвует (сохранен) - значит можно сохранять и книгу.
     * Если же такого автора в базе нет, значит книгу сохранять нельзя.
     *
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    @Override
    public boolean save(SchoolBook book) {

        boolean isAuthorInRep;

        // Getting author's full name from the given book
        String aName = book.getAuthorName();
        String aLastName = book.getAuthorLastName();

        // Trying to find author in authors repository
        isAuthorInRep = authorService.findByFullName(aName, aLastName) != null;

        if (isAuthorInRep) { // Author is in authors repository --> save the book in books repository and return true
            schoolBookBookRepository.save(book);
            return true;
        } else  { // If the author is not registered, return false
            return false;
        }
    }

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     *
     */
    @Override
    public SchoolBook[] findByName(String name) {
        return this.schoolBookBookRepository.findByName(name);
    }

    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length; // Size of the array of found books
    }

    /**
     * Метод должен удалять все книги по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean removeByName(String name) {
        return this.schoolBookBookRepository.removeByName(name);
    }

    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public int count() {
        return this.schoolBookBookRepository.count();
    }

    /**
     * Метод должен возвращать автора книги по названию книги.
     *
     * То есть придётся сходить и в репозиторий с книгами, и в сервис авторов.
     *
     * Если такой книги не найдено, метод должен вернуть null.
     */
    @Override
    public Author findAuthorByBookName(String name) {

        // Trying to find books with the given name
        SchoolBook[] booksByName = schoolBookBookRepository.findByName(name);

        if (booksByName.length == 0) { // If there are no books with the given title, return null
            return null;
        } else  { // If we've got books with the given title

            // Getting author's full name from the 1st found book
            String xName = booksByName[0].getAuthorName();
            String xLastName = booksByName[0].getAuthorLastName();

            // Now trying to find author of the book in authors repository and return the result (Author || null)
            return authorService.findByFullName(xName, xLastName);
        }
    }
}
