package entity;

import enums.Gender;

public class NhanVien extends Officer {
    private String congViec;

    public NhanVien() {
    }

    public NhanVien(String name, int age, Gender gender, String address, String congViec) {
        super(name, age, gender, address);
        this.congViec = congViec;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Công việc: " + congViec;
    }
}

