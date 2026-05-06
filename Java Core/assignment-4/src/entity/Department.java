package entity;

public class Department {
    private int id;
    private String name;

    public Department() {
    }

    public Department(String nameDepartment) {
        this.id = 0;
        this.name = nameDepartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}

