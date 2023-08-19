/*
Resources:
[1] https://www.youtube.com/watch?v=b2uFL4BFDYg
*/

class App {
    public static void main(String[] args) throws CloneNotSupportedException {
        Name name = new Name("Ter", "Stegen");
        Address address = new Address("Some place in Germany", 999);
        Person person = new Person(name, address);

        //Shallow copy. Just points to the same space in memory. If you change person object, those changes will be reflected in person2.
        Person person2 = person;
        System.out.println(person);
        System.out.println(person2);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        address.setNumber(777);
        System.out.println(person);
        System.out.println(person2);

        //Deep copy using the clone() method from Object class. Remember to override the clone() method at member variables classes as well.
        Person person3 = (Person) person2.clone();
        System.out.println(person2);
        System.out.println(person3);
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        person2.getAddress().setNumber(715);
        System.out.println(person2);
        System.out.println(person3);

    }
}

final class Person implements Cloneable {
    private Name name;
    private Address address;

    public Person(Name name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Name getName() { return name; }
    public Address getAddress() { return address; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person clonedPerson = (Person) super.clone();
        clonedPerson.name = (Name) name.clone();
        clonedPerson.address = (Address) address.clone();
        return clonedPerson;
    }

    @Override
    public String toString() {
        return "[name=" + name  + ", address=" + address  + "]";
    }
}

final class Name implements Cloneable {
    private String name;
    private String lastname;

    public Name(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() { return name; }
    public String getLastname() { return lastname; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[name=" + name  + ", lastname=" + lastname  + "]";
    }
}

final class Address implements Cloneable {
    private String street;
    private volatile int number;

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    public String getStreet() { return street; }
    public int getNumber() { return number; }

    public synchronized void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[street=" + street  + ", number=" + number  + "]";
    }
}
