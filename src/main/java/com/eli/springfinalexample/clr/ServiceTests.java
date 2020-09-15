package com.eli.springfinalexample.clr;

import com.eli.springfinalexample.beans.Person;
import com.eli.springfinalexample.service.PersonService;
import com.eli.springfinalexample.utils.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;


@Component
@Order(1)
public class ServiceTests implements CommandLineRunner {

    @Autowired
    private PersonService service;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(HeadersUtil.SERVICE_TESTING);
        System.out.println(" ================ Adding - Normal ==========================");

        Person p1 = new Person(
                "Shulla",
                "Hochman",
                Date.valueOf(LocalDate.of(1980, 2, 24)));
        Person p2 = new Person(
                "Danit",
                "Berger",
                Date.valueOf(LocalDate.of(1965, 8, 12)));
        Person p3 = new Person(
                "Michal",
                "Bat Adam",
                Date.valueOf(LocalDate.of(1998, 12, 24)));
        Person p4 = new Person(
                "David",
                "Shoshani",
                Date.valueOf(LocalDate.of(1954, 10, 17)));
        service.add(p1);
        service.add(p2);
        service.add(p3);
        service.add(p4);

        System.out.println("All Listed Personal: " + service.getAll());

//        System.out.println(" ======== Adding - Conditional: Excluding Moshe! ===================");
//
//        Person p5 = new Person("Moshe", "Vaqnin", Date.valueOf(LocalDate.of(2000,6,17)));
//        service.add(p5);

        System.out.println(" ========= Updating : Danit Berger, Id #2,  will get a new Last name .. =================");
        p2 = service.getOne(2L);
        p2.setLastName("Shimron");
        service.update(p2);
        System.out.println(p2);

        System.out.println(" =========================== Deleting ==========================");
        p4 = service.getOne(4l);
        service.delete(p4);
        System.out.println("David Shoshani, With id #4 successfully deleted: " + service.getAll());

        System.out.println(" =========================== Getting one ==========================");
        System.out.println(service.getOne(2L));

        System.out.println(" ======================== Getting all people with age over 30  ==========================");
        LocalDate localDate = LocalDate.now().minusYears(30);
        System.out.println(service.getAllOver(localDate));


    }

}









