package com.eli.springfinalexample.clr;

import com.eli.springfinalexample.beans.Person;
import com.eli.springfinalexample.utils.HeadersUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(2)
public class ControllerTests implements CommandLineRunner {

    private static final String URL = "http://localhost:8080/person/";
    private RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> res;
    ResponseEntity<Person> resPerson = null;

    public ControllerTests() {
        res = null;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(HeadersUtil.CONTROLLER_TESTING);
        System.out.println(" ================ Adding - Normal ==========================");
        Person p10 = new Person("Oded", "Menashe", Date.valueOf(LocalDate.of(1983,12,4)));
        Person p11 = new Person("Eden", "Harel", Date.valueOf(LocalDate.of(1987,7,6)));
        res = restTemplate.postForEntity(URL +"add",p10,String.class);
        res = restTemplate.postForEntity(URL +"add",p11,String.class);
        System.out.println(res);

        System.out.println(" ======== Adding - Conditional: Excluding Moshe! ===================");
//        Person p5 = new Person("Moshe", "Vaqnin", Date.valueOf(LocalDate.of(2000,6,17)));
//        res = restTemplate.postForEntity(URL +"add",p5,String.class);

        System.out.println(" ========= Updating =================");
        ResponseEntity<Person> res2 = restTemplate.getForEntity(URL + "get-single?id=1", Person.class);
        p10 = res2.getBody();
        p10.setLastName("Greenfeld");
        p10.setBirthday(Date.valueOf(LocalDate.of(2000,6,28)));
        restTemplate.put(URL + "update", p10);
        res = restTemplate.getForEntity(URL + "get-all", String.class);
        System.out.println(res);

        System.out.println(" ========= Get All =================");
        res = restTemplate.getForEntity(URL + "get-all", String.class);
        System.out.println(res);

              System.out.println(" ========= Deleting =================");
        res2 = restTemplate.getForEntity(URL + "get-single?id=2", Person.class);
        p11 =res2.getBody();
        HttpEntity<Person> entity = new HttpEntity<>(p11);
        restTemplate.exchange(URL + "delete", HttpMethod.DELETE,entity,String.class);

        System.out.println(" ====== Getting one ==========================");
        res = restTemplate.getForEntity(URL + "get-single?id=6", String.class);
        System.out.println(res);

        System.out.println(" ========= Get All Over 30 =================");
        res = restTemplate.getForEntity(URL + "get-all-over", String.class);
        System.out.println(res);


    }
}
