// Python classes are not final
public class StudentVersion0 {

    // Public and mutable
    public String name;

    public StudentVersion0(String name) {
        this.name = name;
    }

    public void introduceSelf() {
        System.out.println(String.format("Hi, my name is %s.", this.name));
    }

    public static void worryAboutStudentLoans() {
        System.out.println("Pls halp. Gib job.");
    }

}