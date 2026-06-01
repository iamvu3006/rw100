package com.vti.dto;

import java.util.List;

public class ImportError<T> {
    private T csv;// dữ lieju trong row tren file csv
    private List<String> message;// ds loi lien quan

    public ImportError() {
    }

    public ImportError(T csv, List<String> message) {
        this.csv = csv;
        this.message = message;
    }

    public T getCsv() {
        return csv;
    }

    public void setCsv(T csv) {
        this.csv = csv;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
