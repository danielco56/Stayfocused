package com.example.danielco56.stayfocused;

public class Calcularea_alcolemiei {
        //formula de calcul
        private int bautura_consumata;
        private int alcool_procent;
        private int greutate;
        private int timp;

        public Calcularea_alcolemiei(int bautura_consumata, int alcool_procent, int greutate, int timp){
            this.bautura_consumata=bautura_consumata;
            this.alcool_procent=alcool_procent;
            this.greutate=greutate;
            this.timp=timp;
        }

        public double alcolemie()
        {
            double rezultat=0;
            rezultat = ((bautura_consumata * 0.03381402) * alcool_procent * 0.075 / (greutate * 2.2046244210837774)) - (timp * 0.015);
            return rezultat;
        }

    public double deshidratare()
    {
        //askjdakj
        double rez=0;
        if(alcool_procent==5)   //500ml=500ml
            rez=bautura_consumata;
        if(alcool_procent==13)  //500ml=800ml
            rez=bautura_consumata*1.6;
        if(alcool_procent==40)  //500ml=2100ml
            rez=bautura_consumata*4.2;
        return rez;

    }


}




