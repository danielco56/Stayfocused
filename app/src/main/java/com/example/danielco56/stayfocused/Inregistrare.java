package com.example.danielco56.stayfocused;

public class Inregistrare {
    private String alcolemie;
    private int nrBauturi;
    private String data;

    public Inregistrare(String alcolemie, int nrBauturi, String data) {
        this.alcolemie = alcolemie;
        this.nrBauturi = nrBauturi;
        this.data = data;
    }

    public String getAlcolemie() {
        return alcolemie;
    }

    public int getNrBauturi() {
        return nrBauturi;
    }

    public String getData() {
        return data;
    }
}
