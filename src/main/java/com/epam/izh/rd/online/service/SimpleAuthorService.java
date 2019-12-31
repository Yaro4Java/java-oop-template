package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;

/**
 * Класс SimpleAuthorService сервиса для выполнения бизнес логики при работе с авторами и взаимодействием с
 * репозиторием для авторов AuthorRepository.
 * <p>
 * Необходимо:
 * 1) Имплементировать интерфейс AuthorService
 * 2) Добавить все методы (пока можно не писать реализацию)
 * 3) Добавить в SimpleAuthorService приватное поле "AuthorRepository authorRepository" - это репозиторий к котормоу
 * вы будете обращаться в методах
 * 4) Создать дефолтный конструтор (без параметров)
 * 5) Создать конструтор с параметром AuthorRepository authorRepository (который будет устанвливать в поле authorRepository значение)
 * 6) Написать реализацию для всех методов
 */
public class SimpleAuthorService implements AuthorService {

    private AuthorRepository authorRepository;

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Метод должен сохранять автора.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public boolean save(Author author) {
        return this.authorRepository.save(author);
    }

    /**
     * Метод должен находить автора по имени и фамилии.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public Author findByFullName(String name, String lastname) {
        return this.authorRepository.findByFullName(name, lastname);
    }

    /**
     * Метод должен удалять автора.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат..
     */
    @Override
    public boolean remove(Author author) {
        return this.authorRepository.remove(author);
    }

    /**
     * Метод считать количество сохраненных авторов на текущий момент.
     * По факту, он просто обращается к репозиторию с авторами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    @Override
    public int count() {
        return this.authorRepository.count();
    }
}
