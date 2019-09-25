package com.ibi.ibi_game.classes;

public class Quetionnaire1 {


    public String Q1;
    public String rep1;

    public String Q2;
    public String rep2;

    public String Q3;
    public String rep3;


    public Quetionnaire1()
    {
        Q1="yellow";
     rep1="jaune";

     Q2=" red";
     rep2="rouge";

     Q3="green";
     rep3="verte";
    }

    public boolean questionReponse(String quetion,String reponse)
    {
       if((quetion.equals(Q1) && reponse.equals(rep1))||(quetion.equals(Q2) && reponse.equals(rep2))||(quetion.equals(Q3) && reponse.equals(rep3)))
           return true;


       else
        return false;
    }

    public String getQ1() {
        return Q1;
    }

    public String getRep1() {
        return rep1;
    }

    public String getQ2() {
        return Q2;
    }

    public String getRep2() {
        return rep2;
    }

    public String getQ3() {
        return Q3;
    }

    public String getRep3() {
        return rep3;
    }
}
