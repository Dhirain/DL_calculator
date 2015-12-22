package com.bhavani.dhirain.dl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Innings2 extends ActionBarActivity implements Inning {
    Bundle interruptionsData = null;
    static int count;
    public static DuckworthMethod inning2 = new DuckworthMethod();
    DuckworthMethod inning1 = new DuckworthMethod();
    static TextView ing1_sc1;
    EditText inningSartOver;
    TextView resoure;
    EditText iso;
    final Context context = this;
    static boolean scoreCalculate;
    RelativeLayout l1;
    static int inn1Finalcore;
    float resource1;
    static boolean isIntialised = false;
    static int inngs1FinalScore;
    static float resouceFromInn1;
    static float i1_overAvailabe;
    static String oversPlayed[] = new String[10], wicketsLost[] = new String[10], oversLost[] = new String[10];
    static int parScore;
    //Dhirian
    TextView team2Target;
    TextView textView_parScore;
    int target2;
    EditText editText_oversPlayedSoFar;
    EditText editText_WicketLostSoFar;
    public static boolean isExit = false;
    public static int exitcount = 0;
    float totalOversPlayed_inBracket2;
    TextView textView_finalScore2;
    private static boolean Exceded_input = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innings2);
        iso = (EditText) findViewById(R.id.id_ovrsavblInn2);

        String iso1 = iso.getText().toString();


        if (i1_overAvailabe != 0.0 && count > 0) {
            //Toast t =Toast.makeText(Innings2.this, "Please do not enter Overs", Toast.LENGTH_LONG);
            // t.show();
            iso.setText(i1_overAvailabe + "");

            iso.setEnabled(false);
            //editText.setEnabled(false);
            iso.setKeyListener(null);
            iso.setInputType(InputType.TYPE_NULL);
//            return false;
        }
        Bundle interruptionsData = getIntent().getExtras();

        l1 = (RelativeLayout) findViewById(R.id.id_innings2_rl);
        //ings1_sc_end
        if (interruptionsData == null) {
            return;
        }
        inn1Finalcore = interruptionsData.getInt("inn1Finalcore");
        resource1 = interruptionsData.getFloat("resource1");

        if (inn1Finalcore != 0) {
            inngs1FinalScore = inn1Finalcore;
        }
        if (resource1 != 0.0) {
            resouceFromInn1 = resource1;
        }


        /*resoure=(TextView)findViewById(R.id.id_res);
        ing1_sc1=(TextView)findViewById(R.id.ings1_sc_end);



        resoure.setText(String.valueOf(resouceFromInn1));
        ing1_sc1.setText(String.valueOf(inngs1FinalScore));*/

        oversPlayed[count] = interruptionsData.getString("oversPlayed");

        wicketsLost[count] = interruptionsData.getString("wicketsLost");
        oversLost[count] = interruptionsData.getString("oversLost");
        totalOversPlayed_inBracket2=interruptionsData.getFloat("totalOversPlayed_fromInt_forDisplay");

        textView_finalScore2=(TextView) findViewById(R.id.id_target);

        Log.i("totalOversPlayed2", String.valueOf(totalOversPlayed_inBracket2));
        if(totalOversPlayed_inBracket2!=0F)
        {
            textView_finalScore2.setText("Targets (" + totalOversPlayed_inBracket2+" overs)       :");
        }



        if (!(oversPlayed[count] == null && wicketsLost[count] == null && oversLost[count] == null)) {

            //final TextView interuptionDetails = (TextView)findViewById(R.id.interuptionDetails);
            displayInteruptionDetails();
            // calcDuckInn1();
        }
    }

    public void displayInteruptionDetails() {
        TextView tv3[] = new TextView[count + 1];
        Button b1 = (Button) findViewById(R.id.id_addInteruptionInnings2);
        for (int i = 1; i < count + 1; i++) {
            tv3[i] = new TextView(this);
            tv3[i].setTextSize(16);
            tv3[i].setId(i);
            //tv3[i].setLayoutParams(new GridView.LayoutParams(count*85,count*85));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 1)
                params.addRule(RelativeLayout.BELOW, tv3[i - 1].getId());
            if (i == 1)
                params.addRule(RelativeLayout.BELOW, b1.getId());
            else
                params.topMargin = count * 10;
            params.leftMargin = count + 10;

            //tv3[i].setPadding(count * 25, count * 25, count * 25, count * 25);

            //tv3[i].setText("oversPlayed " + oversPlayed[i]);
            tv3[i].setText("overs played " + oversPlayed[i] + " , wickets lost " + wicketsLost[i] + " , overs lost " + oversLost[i]);

            l1.addView(tv3[i], params);
        }
    }

    public void sendObject(DuckworthMethod dm) {
        inning2 = dm;

    }

    public DuckworthMethod getObject() {
        return inning2;
    }

    public boolean initialiseIt() {


        iso = (EditText) findViewById(R.id.id_ovrsavblInn2);

        String iso1 = iso.getText().toString().trim();

        if (iso1.equals("") && i1_overAvailabe != 50.0) {
            Toast t = Toast.makeText(Innings2.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
            t.show();
            iso.setText("50.0");
            //  return false;
        }
        iso1 = iso.getText().toString().trim();
        if (!validate(iso1))
            if (Exceded_input) {
                Toast t = Toast.makeText(Innings2.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
                t.show();
                iso.setText("50.0");
                //return true;//validation has failed and input has been set to true
                Exceded_input=false;
            } else {
                iso.setError("Not proper values");
                return false;
            }


            inning2.initilizeAllovres();
            inning2.overAvailabe.setOver(i1_overAvailabe);
            inning2.intilizeStart();
         //     count++;

            Intent i = new Intent(Innings2.this, Interruptions.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("innings", "Innings2");
            i.putExtra("iso", i1_overAvailabe);
            startActivity(i);
            finish();

            //  System.out.println(count);
            iso.setText(String.valueOf(i1_overAvailabe));
            iso.setEnabled(false);
            iso.setFocusable(false);
            return true;
  //      }
    }

    public void onClickInt2(View view) {
        exitcount=0;


        if (!isIntialised) {

            isIntialised = initialiseIt();

        } if (isIntialised) {

            count++;


            Intent i = new Intent(Innings2.this, Interruptions.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("innings", "Innings2");
            i.putExtra("iso", i1_overAvailabe);
            startActivity(i);
            finish();
        }
    }

    public void callAlert() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("Is it test going on?");
        alertDialogBuilder
                .setMessage("Click yes to continue,it resets to 50 overs")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        iso.setText("50.0");
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public boolean initialiseItscore() {
        iso = (EditText) findViewById(R.id.id_ovrsavblInn2);

        String iso1 = iso.getText().toString().trim();
        if (iso1.equals("") && i1_overAvailabe != 50.0) {
            Toast t = Toast.makeText(Innings2.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
            t.show();
            iso.setText("50.0");
            //return false;
        }
        iso1 = iso.getText().toString().trim();
        if (!validate(iso1)) {
            if (Exceded_input) {
                Toast t = Toast.makeText(Innings2.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
                t.show();
                iso.setText("50.0");
                //return true;//validation has failed and input has been set to true
            } else {
                iso.setError("Not proper values");
                return false;
            }

        }



            inning2.initilizeAllovres();
            inning2.overAvailabe.setOver(i1_overAvailabe);
            inning2.intilizeStart();
            //  count++;

            //  System.out.println(count);
            iso.setText(String.valueOf(i1_overAvailabe));
            iso.setEnabled(false);
            iso.setFocusable(false);
            return true;
        //}
    }

    public void score(View v) {
        exitcount=0;
        if (!isIntialised) {

            isIntialised = initialiseItscore();

        }
        //Dhirain
         if (isIntialised) {

            inning2.enterScore();


//totalOversAvailable_int.input,wicketLostByIntruption,inning2.totalOversPlayed.input=totalovers
            compare();
            team2Target = (TextView) findViewById(R.id.Id_Target);
            team2Target.setText(String.valueOf(target2));

            parScoreGenerator();
            textView_parScore = (TextView) findViewById(R.id.id_parScore);
            textView_parScore.setText(String.valueOf(parScore));
            scoreCalculate = false;
            System.out.println("scoreCalculate" + scoreCalculate);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_innings2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //
        switch(item.getItemId())
        {
            case R.id.about:
                if(item.isChecked())
                    item.setChecked(false);
                else
                {
                    //Intent i = new Intent()
                    Intent i = new Intent(Innings2.this, aboutApp.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    //this.finish();
                }
                return true;
            case R.id.dl:
                if(item.isChecked())
                    item.setChecked(false);
                else
                {

                    Intent i1 = new Intent(Innings2.this, dl.class);
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i1);
                    //this.finish();
                }
                return true;
           /* case R.id.exit :
                if(item.isChecked())
                    item.setChecked(false);
                else {

                    finish();
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                return true;*/
            case R.id.exit1:
                if(item.isChecked())
                    item.setChecked(false);
                else {

                    finish();
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                return true;
            default:         return super.onOptionsItemSelected(item);


        }


    }

    void parScoreGenerator() {

        editText_oversPlayedSoFar = (EditText) findViewById(R.id.id_TOP_inng2);
        editText_WicketLostSoFar = (EditText) findViewById(R.id.id_WL_inn2);
        float float_oversPlayedSoFar = 0.0f;
        int float_WicketLostSoFar = 0;
        //validateWicketsnOverPlayed(editText_oversPlayedSoFar, editText_WicketLostSoFar);
        boolean wl = hasText(editText_oversPlayedSoFar);
        boolean op = hasText(editText_WicketLostSoFar);
        ////totalOversAvailable_int.input,wicketLostByIntruption,inning2.totalOversPlayed.input=totalovers

        if (!(wl && op)) {

            editText_oversPlayedSoFar.setText(inning2.totalOversAvailable_int.input + "");
            editText_WicketLostSoFar.setText(inning2.wicketLostByIntruption + "");
            Toast t = Toast.makeText(Innings2.this, "Wickets n Overs has been Adjusted", Toast.LENGTH_SHORT);
            t.show();

            String string_oversPlayedSoFar = editText_oversPlayedSoFar.getText().toString().trim();
            float_oversPlayedSoFar = Float.valueOf(string_oversPlayedSoFar);


            String string_WicketLostSoFar = editText_WicketLostSoFar.getText().toString().trim();
            float_WicketLostSoFar = Integer.valueOf(string_WicketLostSoFar);

            //oversLost.setError(REQUIRED_MSG);
            //return 1;
        } else {

            String string_oversPlayedSoFar = editText_oversPlayedSoFar.getText().toString().trim();
            float_oversPlayedSoFar = Float.valueOf(string_oversPlayedSoFar);


            String string_WicketLostSoFar = editText_WicketLostSoFar.getText().toString().trim();
            float_WicketLostSoFar = Integer.valueOf(string_WicketLostSoFar);


               if (float_WicketLostSoFar < inning2.wicketLostByIntruption || float_WicketLostSoFar>10) {//bhavani
                   editText_WicketLostSoFar.setText(inning2.wicketLostByIntruption + "");
                   string_WicketLostSoFar = editText_WicketLostSoFar.getText().toString().trim();
                   float_WicketLostSoFar = Integer.valueOf(string_WicketLostSoFar);
                   Toast t = Toast.makeText(Innings2.this, "Wickes has been adjusted", Toast.LENGTH_SHORT);
                   t.show();

               }

            if (float_oversPlayedSoFar > inning2.totalOversPlayed.input || float_oversPlayedSoFar < inning2.totalOversAvailable_int.input) {
                editText_oversPlayedSoFar.setText(inning2.totalOversAvailable_int.input + "");
                string_oversPlayedSoFar = editText_oversPlayedSoFar.getText().toString().trim();
                float_oversPlayedSoFar = Float.valueOf(string_oversPlayedSoFar);
                Toast t = Toast.makeText(Innings2.this, "Overs has been adjusted", Toast.LENGTH_SHORT);
                t.show();
            }

        }
        //Bhavani put validation here

        System.out.println("" + "");

        inning2.oversPlayedSoFar.setOver(float_oversPlayedSoFar);
        inning2.WicketLostSoFar = float_WicketLostSoFar;

        parScore();


    }


    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        // editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            // editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    void parScore() {

        float tempResource2 = inning2.overAvailabePercentile;

        Overs k1 = new Overs();
        k1 = inning2.totalOversPlayed.subtact(inning2.oversPlayedSoFar);


        float k1per = PercentileCoversion.getPercentile(k1.balls, inning2.WicketLostSoFar);


        tempResource2 = tempResource2 - k1per;



        parScore = 0;

        //if R2 is less than R1
        if (tempResource2 <= resouceFromInn1) {
            parScore = (int) (inngs1FinalScore * (tempResource2 / resouceFromInn1));
        }

        if (tempResource2 > resouceFromInn1) {
            //S + (R2 – R1) x G50/100 + 1
            parScore = (int) (inngs1FinalScore + ((tempResource2 - resouceFromInn1) * (2.45)));
        }

    }

    //Dhirain
    public void compare() {



        //if R2 is less than R1
        if (inning2.Resource <= resouceFromInn1) {
            target2 = (int) (inngs1FinalScore * (inning2.Resource / resouceFromInn1)) + 1;
        }

        if (inning2.Resource > resouceFromInn1) {
            //S + (R2 – R1) x G50/100 + 1
            target2 = (int) (inngs1FinalScore + ((inning2.Resource - resouceFromInn1) * (2.45)) + 1);
        }
        System.out.println("Target score for team 2:  " + target2);
    }

  /*  @Override
    public void onBackPressed()

    {


        this.finish();
        //super.onBackPressed();
        //return;
    }*/



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            System.out.println("cccccccccccccccccccccccccccccccccccc"+exitcount);
            exitcount++;
/*
        finish();
        System.exit(1);
*/
        //this.finish();
        //android.os.Process.killProcess(android.os.Process.myPid());
  //      this.finish();


        if(exitcount==1)
         //   this.finish();
            Toast.makeText(getApplicationContext(), "double click for to close",
                  Toast.LENGTH_SHORT).show();
        if(exitcount>1) {
//            finish();
            System.exit(1);

            this.finish();
        }

        return false;
    }
    public void onClickTableGenerator(View v)
    {
        exitcount=0;
        if(!scoreCalculate) {
            if (!isIntialised) {

                isIntialised = initialiseItscore();

            }
            //Dhirain
            if (isIntialised) {

                inning2.enterScore();


//totalOversAvailable_int.input,wicketLostByIntruption,inning2.totalOversPlayed.input=totalovers
                compare();
                team2Target = (TextView) findViewById(R.id.Id_Target);
                team2Target.setText(String.valueOf(target2));

                parScoreGenerator();
                textView_parScore = (TextView) findViewById(R.id.id_parScore);
                textView_parScore.setText(String.valueOf(parScore));
                scoreCalculate = false;
                System.out.println("scoreCalculate" + scoreCalculate);


            }


            /*
            System.out.println("Track 2 Bhavani - score in if condition of initialiseit");
            isIntialised = initialiseItscore();
            System.out.println("Track 2 in if condition of initialiseit---isIntialised"+isIntialised);
            inning2.oversPlayedSoFar.input=1;
           inning2.totalOversPlayed.input=50;
           */

        }

        Log.i("Negative","overAvailabePercentile"+inning2.overAvailabePercentile);
        Log.i("Negative", "oversPlayedSoFar"+ inning2.oversPlayedSoFar.input);
        Log.i("Negative","totalOversPlayed"+inning2.totalOversPlayed.input);
        Log.i("Negative", "wicketLostSoFar"+ inning2.WicketLostSoFar);
        Log.i("Negative", "Resource1"+resouceFromInn1);
        Log.i("Negative", "Team1FinalScore"+ inngs1FinalScore);

            Intent i = new Intent(Innings2.this, ParTableGenerator.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("overAvailabePercentile", inning2.overAvailabePercentile);
            i.putExtra("oversPlayedSoFar", inning2.oversPlayedSoFar.input);
            i.putExtra("totalOversPlayed", inning2.totalOversPlayed.input);
            i.putExtra("wicketLostSoFar", inning2.WicketLostSoFar);
            i.putExtra("Resource1", resouceFromInn1);
        i.putExtra("Team1FinalScore", inngs1FinalScore);


        startActivity(i);
//        finish();

    }

    boolean validate(String iso)
    {
        float input = Float.valueOf(iso);
        float f1 = (input % 1);
        String str1 = String.format("%.01f", f1);
        float floatValue = Float.valueOf(str1);

        if (iso != null)
            i1_overAvailabe = input;
        // System.out.println("i1_overAvailabeovrAvblovrAvblovrAvblovrAvblovrAvblovrAvbl" + i1_overAvailabe);

        if (i1_overAvailabe == 0.0f) {
            i1_overAvailabe = 50.0F;
        }

        if (i1_overAvailabe > 50.0) {
            i1_overAvailabe = 50.0F;
            Exceded_input = true;
            return false;
        }
        if ( floatValue > .5) {

            return false;
        }


        return true;
    }

}
