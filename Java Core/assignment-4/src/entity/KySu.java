package entity;

import enums.Gender;

public class KySu extends Officer {
    private String nganh;

    public KySu() {
    }

    public KySu(String name, int age, Gender gender, String address, String nganh) {
        super(name, age, gender, address);
        this.nganh = nganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getDisplayInfo() {
        return super.getDisplayInfo() + " | Ngành đào tạo: " + nganh;
    }
}

