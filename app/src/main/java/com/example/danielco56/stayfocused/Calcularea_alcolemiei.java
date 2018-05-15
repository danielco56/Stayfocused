package com.example.danielco56.stayfocused;

import static com.example.danielco56.stayfocused.FirstPage.greutateStatic;

public class Calcularea_alcolemiei {

    private Controller controller = new Controller();
    private FirstPage firstPage=new FirstPage();
    private int greutate;
    private int timp;
    public static double rezultat = 0;


    public double alcolemie() {



        for (Alcool alcool : controller.bauturi) {
            rezultat += ((alcool.getCantitate() * 0.03381402) * alcool.getConcentratie() * 0.075 / (70 * 2.2046244210837774)) - (1 * 0.015);
        }

        return rezultat;
    }

   /* public double deshidratare() {

       double rez = 0;
        if (alcool_procent == 5)   //500ml=500ml
            rez = bautura_consumata;
        if (alcool_procent == 13)  //500ml=800ml
            rez = bautura_consumata * 1.6;
        if (alcool_procent == 40)  //500ml=2100ml
            rez = bautura_consumata * 4.2;
        return rez;

    }    */



}




