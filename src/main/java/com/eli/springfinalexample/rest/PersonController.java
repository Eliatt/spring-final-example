package com.eli.springfinalexample.rest;

import com.eli.springfinalexample.Exceptions.InvalidEntryException;
import com.eli.springfinalexample.beans.Person;
import com.eli.springfinalexample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Person person) throws InvalidEntryException {
        try {
            service.add(person);
            return new ResponseEntity<>("Added Successfully", HttpStatus.CREATED);
        } catch (InvalidEntryException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Person person) {
        service.update(person);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete") // localhost:8080/person/delete
    public ResponseEntity<?> delete(@RequestBody Person person) {
        service.delete(person);
        return new ResponseEntity<String>("Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("get-all") // localhost:8080/person/get-all
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("get-single") // localhost:8080/person/get-single
    public ResponseEntity<?> getOne(@RequestParam(name = "id") Long id) {
        return new ResponseEntity<Person>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("get-all-over") // localhost:8080/person/get-all-over
    public ResponseEntity<?> getAllOver() {
        return new ResponseEntity<>(service.getAllOver(LocalDate.of(1990, 9, 14)), HttpStatus.OK);
    }
}