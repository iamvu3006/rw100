package entity;

import enums.Gender;

public class Officer {
    private String name;
    private int age;
    private Gender gender;
    private String address;

    public Officer() {
    }

    public Officer(String name, int age, Gender gender, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisplayInfo() {
        return "Tên: " + name + " | Tuổi: " + age + " | Giới tính: "
                + (gender != null ? gender.getVietnameseName() : "Không xác định")
                + " | Địa chỉ: " + address;
    }
}
