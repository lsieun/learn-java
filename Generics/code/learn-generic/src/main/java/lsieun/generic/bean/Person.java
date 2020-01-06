package lsieun.generic.bean;

public class Person implements Comparable<Person> {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person another) {
        return this.age - another.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + "'" +
                ", age=" + age +
                '}';
    }
}
