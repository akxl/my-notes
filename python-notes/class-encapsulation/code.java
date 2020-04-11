// Python classes are not final
public class StudentVersion0 {

    // Public and mutable
    String name;

    public StudentVersion0(String name) {
        this.name = name;
    }

}


public class StudentVersion1 {

    // Python attributes are public and mutable
    // even if they have an underscore in front of them
    public String _name;

    public StudentVersion1(String name) {
        this._name = name;
    }

    public String getName() {
        return "Called from name property: " + this._name;
    }

}


public class StudentVersion2 {

    // Python attributes are public and mutable
    // even if they have an underscore in front of them
    public String _name;

    public StudentVersion2(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = "Comrade " + name;
    }

}