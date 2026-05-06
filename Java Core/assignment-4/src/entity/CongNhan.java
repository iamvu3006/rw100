package entity;

import enums.Gender;

public class CongNhan extends Officer {
    private int bac;

    public CongNhan() {
    }

    public CongNhan(String name, int age, Gender gender, String address, int bac) {
        super(name, age, gender, address);
        this.bac = bac;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        if (bac >= 1 && bac <= 10) {
            this.bac = bac;
        }
    }

    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Bậc: " + bac;
    }
}

