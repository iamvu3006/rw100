package entity;

public class Student {
    private int id;
    private String name;
    private String hometown;
    private float gradePoint;

    public Student() {
    }

    public Student(String name, String hometown) {
        this.id = 0;
        this.name = name;
        this.hometown = hometown;
        this.gradePoint = 0;
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

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public float getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(float gradePoint) {
        this.gradePoint = gradePoint;
    }

    public void addGradePoint(float additionalPoint) {
        this.gradePoint += additionalPoint;
    }

    public String getAcademicRank() {
        if (gradePoint < 4.0f) {
            return "Yếu";
        }
        if (gradePoint < 6.0f) {
            return "Trung bình";
        }
        if (gradePoint < 8.0f) {
            return "Khá";
        }
        return "Giỏi";
    }

    public String getDisplayInfo() {
        return "Tên: " + name + " | Quê quán: " + hometown + " | Điểm học lực: " + gradePoint + " (" + getAcademicRank() + ")";
    }
}

