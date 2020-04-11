import java.util.Objects;

// Python classes are not final
public class StudentVersion1 {

    // All public and mutable
    public String name;
    public int idNumber;
    public int year;

    public StudentVersion1(String name, int idNumber, int year) {
        this.name = name;
        this.idNumber = idNumber;
        this.year = year;
    }

}



// Python classes are not final
public class StudentVersion3 {

    // All public and mutable
    public String name;
    public int idNumber;
    public int year;

    public StudentVersion3(String name, int idNumber, int year) {
        this.name = name;
        this.idNumber = idNumber;
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.idNumber, this.year);
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass() == this.getClass()) {
            final StudentVersion3 o = (StudentVersion3) other;
            return this.name.equals(o.name) &&
                    this.idNumber == o.idNumber &&
                    this.year == o.year;
        } else {
            return false;
        }
    }
}