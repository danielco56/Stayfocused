package com.example.danielco56.stayfocused;



public class Calcularea_alcolemiei {

    private Controller controller = new Controller();
    public static double rezultat = 0;


    public double alcolemie() {


        for (Alcool alcool : controller.bauturi) {
            rezultat += ((alcool.getCantitate() * 0.03381402) * alcool.getConcentratie() * 0.075 / (70 * 2.2046244210837774)) - (1 * 0.015);
        }

        return rezultat;
    }





}




