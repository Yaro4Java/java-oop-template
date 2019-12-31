package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

/**
 * Класс SimpleAuthorRepository репозитория для хранения данных об авторах.
 *
 * Необходимо:
 * 1) Имплементировать в данном классе интерфейс AuthorRepository
 * 2) Добавить все методы интерфейса (пока можно не писать реализацию)
 * 3) Добавить приватное поле "Author[] authors" для хранения авторов
 * 4) Инициалазировать его пустым массивом
 * 5) Написать реализацию для всех методов (коллекции не используем, работаем только с массивами)
 */
public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = {};
    private int counter = 0;

    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    @Override
    public boolean save(Author author) {

        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {

            Author[] temp;
            temp = authors;
            authors = new Author[counter + 1];

            for (int i = 0; i < counter; i++) {
                authors[i] = temp[i];
            }

            authors[counter++] = author;
            return true;
        }
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    @Override
    public Author findByFullName(String name, String lastname) {

        if (authors.length == 0) {
            return null;
        }

        for(Author a : authors) {
            if ((a.getName() == name) && (a.getLastName() == lastname)) {
                return a;
            }
        }

        return null;
    }

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    @Override
    public boolean remove(Author author) {

        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        } else {

            Author[] temp;
            temp = authors;
            authors = new Author[counter - 1];

            for (int i = 0, k = 0; i < counter; i++) {
                if(temp[i] == author) {
                    continue;
                }
                authors[k++] = temp[i];
            }

            counter--;
            return true;
        }
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        return counter;
    }
}
