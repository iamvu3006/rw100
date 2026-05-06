package entity;

import enums.TypeName;

public class TypeQuestion {
    private int id;
    private TypeName typeName;

    public TypeQuestion() {
    }

    public TypeQuestion(int id, TypeName typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }
}

