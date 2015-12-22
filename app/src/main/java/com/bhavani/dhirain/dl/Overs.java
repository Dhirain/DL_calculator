package com.bhavani.dhirain.dl;


public class Overs{

    float input;
    int balls;
    int wholePart;
    float floatValue;
    public void setOver(float input){

        int wholePart=(int)input;
        float f1=(input % 1);
        String str1 = String.format("%.01f", f1);
        float floatValue= Float.valueOf(str1);
        this.input=input;


        if  (floatValue > .5)
        {
            wholePart++;
            this.input=(int)input+1;
            floatValue=0;
            System.err.println("incorrect cricket values Resetting values to"+this.input);
        }


        this.balls=(int) ((wholePart*6)+(floatValue*10));
        this.wholePart=wholePart;
        this.floatValue=floatValue;

    }

    public Overs subtact(Overs o2){

        int resultBall=this.balls-o2.balls;
        int r1=resultBall/6;
        int r2=resultBall%6;
        float r3=(float) ((float)r1+(.1*r2));
        Overs result=new Overs();
        result.setOver(r3);
        return result;

    }

    public Overs add(Overs o2){

        int resultBall=this.balls+o2.balls;
        int r1=resultBall/6;
        int r2=resultBall%6;
        float r3=(float) ((float)r1+(.1*r2));
        Overs result=new Overs();
        result.setOver(r3);
        return result;

    }

}