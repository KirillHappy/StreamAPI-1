import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long min18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(min18);


        List<String> listFamily = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(listFamily);

        List<Person> readyList = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(age18 -> age18.getAge() >= 18)
                .filter(person -> ( person.getSex() == Sex.MAN && person.getAge() <= 65 )
                        || ( person.getSex() == Sex.WOMAN && person.getAge() <= 60) )
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(readyList);


    }
}
