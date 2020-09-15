package com.eli.springfinalexample.repository;

import com.eli.springfinalexample.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByBirthdayLessThan(Date date);

}
