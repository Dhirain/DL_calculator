package com.bhavani.dhirain.dl;


public class DuckworthMethod {

    Overs overAvailabe;
    Overs overAvailabePermanent;
    Overs OverPlayed;
    Overs overLost;
    Overs totalOversPlayed;
    Overs totalOversAvailable_int;
    int   wicketLost = 0;
    int finalScore;
    int intruptionCount;
    float overAvailabePercentile;
    Overs overLostpeResource;
    float Resource;
    Overs oversPlayedSoFar;
    int WicketLostSoFar;
    int wicketLostByIntruption;

    Overs totalOversPlayed_forDisplay;
    int key;


    public boolean initilizeAllovres(){
        overAvailabe=new Overs();
        overAvailabePermanent=new Overs();
        OverPlayed=new Overs();
        overLost=new Overs();
        totalOversPlayed=new Overs();
        overLostpeResource=new Overs();
        oversPlayedSoFar=new Overs();
        totalOversAvailable_int = new Overs();
        totalOversPlayed_forDisplay = new Overs();
        return true;
    }

    public void intilizeStart() {
        //set overStart and its percentile
        //must be called immdeditely after overAvailabe.setOver(in.nextFloat());


        overAvailabePermanent = overAvailabe;

        overAvailabePercentile = PercentileCoversion.getPercentile(overAvailabePermanent.balls, 0);
        System.err.println(overAvailabePercentile);
    }



    public void intruptAdd() {
        overLostpeResource=overLostpeResource.add(overLost);
        Overs k1=new Overs();
        k1=overAvailabe.subtact(OverPlayed);
        totalOversAvailable_int = totalOversAvailable_int.add(OverPlayed);


        float k1per=PercentileCoversion.getPercentile(k1.balls, wicketLost);


        Overs k2=new Overs();
        k2=k1.subtact(overLost);
        //double k2per=toPercentile(k2, wicketLost,68.8);
        float k2per=PercentileCoversion.getPercentile(k2.balls, wicketLost);

        float loss=k1per-k2per;

        overAvailabe=k2;

        wicketLostByIntruption =  wicketLost;
        //Bhavani

        overAvailabePercentile=overAvailabePercentile-loss;

        totalOversPlayed_forDisplay = overAvailabePermanent.subtact(overLostpeResource);
    }


    public void enterScore(){
        Resource=overAvailabePercentile;
        totalOversPlayed = overAvailabePermanent.subtact(overLostpeResource);

//        finalScore=in.nextInt();


    }




}

