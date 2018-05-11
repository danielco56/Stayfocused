package com.example.danielco56.stayfocused;

public class Person {
    private String nume;
    private int inaltime;
    private int greutate;
    private int varsta;
    private String gen;

    public Person(String nume, int inaltime, int greutate, int varsta, String gen) {
        this.nume = nume;
        this.inaltime = inaltime;
        this.greutate = greutate;
        this.varsta = varsta;
        this.gen = gen;
    }

    public String getNume() {
        return nume;
    }

    public int getInaltime() {
        return inaltime;
    }

    public int getGreutate() {
        return greutate;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setInaltime(int inaltime) {
        this.inaltime = inaltime;
    }

    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
