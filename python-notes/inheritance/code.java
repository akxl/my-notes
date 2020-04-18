public class Person {

    // attributes in Python are public and mutable
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public void introduceSelf() {
        System.out.println("My name is " + this.name + ".");
    }

}

public class Student extends Person {

    public int year;

    public Student(String name, int year) {
        super(name);
        this.year = year;
    }
}

public class PoliteStudent extends Student {

    public PoliteStudent(String name, int year) {
        super(name, year);
    }

    @Override
    public void introduceSelf() {
        super.introduceSelf();
        System.out.println("It's a pleasure to meet you.");
    }
}

// Arguably, this contrived example can be an `Interface`
public abstract class AbstractAnimal {
    abstract public void makeSound();
}

public class LabRat extends AbstractAnimal {

    // attributes in Python are public and mutable
    public String sound;

    public LabRat(String sound) {
        this.sound = sound;
    }

    @Override
    public void makeSound() {
        System.out.println(sound);
    }
}

public final class Main {

    public static void main(String[] args) {

        final Student student1 = new Student("Ashley", 6);
        System.out.println(student1.name); // output: 'Ashley'
        System.out.println(student1.year); // output: 6
        // call the inherited method
        student1.introduceSelf(); // output: 'My name is Ashley.'

        final Student student2 = new PoliteStudent("Bob", 5);
        // call the overridden method
        student2.introduceSelf(); // output: 'My name is Bob.\nIt's a pleasure to meet you.'

        final AbstractAnimal labRat = new LabRat("Squeak!");
        // call the implemented method
        labRat.makeSound(); // output: Squeak!

    }

}