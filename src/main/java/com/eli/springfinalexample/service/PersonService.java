package com.eli.springfinalexample.service;

import com.eli.springfinalexample.Exceptions.InvalidEntryException;
import com.eli.springfinalexample.beans.Person;
import com.eli.springfinalexample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public void add(Person person) throws InvalidEntryException {
        if (person.getFirstName().trim().equalsIgnoreCase("Moshe")) {
            throw new InvalidEntryException("A Person by that name cannot be added");
        }
        repository.save(person);
    }

    public void update(Person person) {
        repository.saveAndFlush(person);
    }

    public void delete(Person person) {
        repository.delete(person);
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Person getOne(Long id) {
        return repository.getOne(id);
    }

    public List<Person> getAllOver(LocalDate date) {
        return repository.findByBirthdayLessThan(Date.valueOf(date));
    }
}



