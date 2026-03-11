package co.edu.uptc;

import co.edu.uptc.entity.Person;
import co.edu.uptc.model.util.DoubleList;
import co.edu.uptc.presenter.Runner;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //new Runner().start();

        //TODO ESTO ES PARA PROBAR
        Person p = new Person();
        DoubleList<Person> listPerson = new DoubleList<>();

        listPerson.addFirst(new Person(1,"Alexis","Tobar","M",new Date()));
        listPerson.addLast(new Person(2,"Maria","Sosa","F",new Date()));

        for (Person person : listPerson.getAllList()) {
            System.out.println(person.toString());
        }
    }
}
