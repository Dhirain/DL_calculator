package com.bhavani.dhirain.dl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

public class Interruptions extends ActionBarActivity {

    String fromInnings;
    final Context context = this;
    int count;
    EditText oversPlayed;
    EditText wicketsLost;
    EditText oversLost;
    float InningsStartOver;
    static String REQUIRED_MSG = "Please enter this fields properly ";
    boolean wicketsLostNotEmpty;
    boolean oversPlayedNotEmpty;
    boolean oversLostNotEmpty;
    DuckworthMethod i11;
    static int exitcount = 0;
    public static boolean overPlayedInvalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interruptions);
        fromInnings = getIntent().getExtras().getString("innings");
        count = getIntent().getExtras().getInt("count");
        InningsStartOver = getIntent().getExtras().getFloat("iso");
        System.out.println("innings------------------------------------------------------------------------" + fromInnings + "      " + count+"..............."+InningsStartOver);
        oversPlayed = (EditText) findViewById(R.id.oversPlayed);
        oversPlayed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (count == 1 || count == 0) {
                        Toast t = Toast.makeText(Interruptions.this, "Enter overs played between innings start and interruption " + count + "", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(Interruptions.this, "Enter overs played between interruption " + (count - 1) + " and interruption " + (count), Toast.LENGTH_SHORT);

                        t.show();
                    }
                }
            }
        });


        // oversPlayed.setSelection(0,2);
        wicketsLost = (EditText) findViewById(R.id.wicketsLost);
        wicketsLost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast t = Toast.makeText(Interruptions.this, "Enter Number of Wickets lost till interruption " + count + "", Toast.LENGTH_SHORT);

                    t.show();
                }

            }
        });

        oversLost = (EditText) findViewById(R.id.oversLost);
        oversLost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    if (count == 1 || count == 0) {
                        Toast t = Toast.makeText(Interruptions.this, "Enter overs Lost between innings start and interruption " + count + "", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(Interruptions.this, "Enter overs Lost between interruption " + (count - 1) + " and interruption " + (count), Toast.LENGTH_SHORT);

                        t.show();
                    }
                }

            }
        });

    }

    public void onButtonClick(View view) {
        exitcount=0;
        System.out.println("******************************* onButtonClick of Interruptions.java ************************************" + fromInnings);
        //Inning
        Inning inn =  null;
        i11 = new DuckworthMethod();
        if (fromInnings.equals("Innings1")) {
            //i = new Intent(this, Inning1.class);
             inn = new Inning1();
        }
        if (fromInnings.equals("Innings2")) {
            //i = new Intent(this, Innings2.class);
             inn = new Innings2();
        }

       // Inning1 inn = new Inning1();
        i11 = inn.getObject();
        Intent i = null;



        oversPlayed = (EditText) findViewById(R.id.oversPlayed);
        wicketsLost = (EditText) findViewById(R.id.wicketsLost);
        oversLost = (EditText) findViewById(R.id.oversLost);

        switch (validate(oversPlayed, wicketsLost, oversLost)) {
            case 1:
                /*oversPlayed.setError(REQUIRED_MSG);
                wicketsLost.setError(REQUIRED_MSG);
                oversLost.setError(REQUIRED_MSG);*/
                AlertDialog.Builder alertDialogBuilder_em = new AlertDialog.Builder(context);

                // set title
                //alertDialogBuilder_em.setTitle("Nooooooooo");

                // set dialog message
                alertDialogBuilder_em
                        .setMessage(" Please Enter all three fields ")
                        .setCancelable(false)
                        .setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  wicketsLost.setText(i11.wicketLost);
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder_em.create().show();
                break;
            case 2:
                    if(overPlayedInvalid)
                    {oversPlayed.setError("Invalid Cricketing Values");
                overPlayedInvalid=false;}
                   else{
                oversPlayed.setError("Overs Played cannot be greater than overs available "+i11.overAvailabe.input );
                //String m1="Overs Played cannot be greater than overs available "+i11.overAvailabe.input+" And nu";
                    }
                break;
            case 3:
                // oversLost.setError("overplayed is equal to Available Number of overs, overlost will be Zero");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("");
                // set dialog message
                alertDialogBuilder
                        .setMessage("overplayed is equal to Available Number of overs, overlost will be Zero")
                        .setCancelable(false)
                        .setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //oversLost.setText("0.0");
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
                break;

            case 4:
                //wicketsLost.setError("How can you decrease the number of wickets from"+i11.wicketLost);
                String msg = "";
                if (Float.valueOf(wicketsLost.getText().toString()) > 10)
                {            msg = "Wickets lost can't be Greater than 10";
        }
                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder1.setTitle("Error in wickets lost");

                // set dialog message
                alertDialogBuilder1
                        .setMessage((msg==""?"Wickets lost can't be less than " + i11.wicketLost:msg))
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                               // wicketsLost.setText(i11.wicketLost);
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder1.create().show();
                break;
            //alertDialog.show();
            case 5:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(context);

                // set title
                //alertDialogBuilder2.setTitle("Nooooooooo");

                // set dialog message
                alertDialogBuilder2
                        .setMessage("Overs lost can't be greater than " + (i11.overAvailabe.input - Float.valueOf(oversPlayed.getText().toString())))
                        .setCancelable(false)
                        .setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  wicketsLost.setText(i11.wicketLost);
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder2.create().show();
                break;
            case 6:

                oversLost.setError(" Invalid Cricketing Values  ");
                break;
            case 0:
                String oversPlayed1 = oversPlayed.getText().toString();
                String wicketsLost1 = wicketsLost.getText().toString();
                String oversLost1 = oversLost.getText().toString();
                System.out.println("oversPlayed1::" + oversPlayed1 + "wicketsLost1::" + wicketsLost1 + "oversLost1::" + oversLost1);
                // if()
                Float float_oversPlayed1 = Float.valueOf(oversPlayed1);
                int int_wicket1 = Integer.valueOf(wicketsLost1);
                Float float_oversLost1 = Float.valueOf(oversLost1);

                i11.OverPlayed.setOver(float_oversPlayed1);
                i11.wicketLost = int_wicket1;
                i11.overLost.setOver(float_oversLost1);
                System.out.println("^^^^^^^^switch^^^^^^^^^^^^^^^^^" + Float.valueOf(oversPlayed.getText().toString()) + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + i11.overAvailabe.input);
                i11.intruptAdd();
                inn.sendObject(i11);
                if (fromInnings.equals("Innings1")) {
                    i = new Intent(this, Inning1.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                if (fromInnings.equals("Innings2")) {
                    i = new Intent(this, Innings2.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }




                i.putExtra("totalOversPlayed_fromInt_forDisplay", i11.totalOversPlayed_forDisplay.input);
                i.putExtra("oversPlayed", oversPlayed1);
                i.putExtra("wicketsLost", wicketsLost1);
                i.putExtra("oversLost", oversLost1);

                startActivity(i);
                this.finish();
                break;
        }
    }

    public int validate(EditText oversPlayed, EditText wicketsLost, EditText oversLost) {
        oversPlayedNotEmpty = hasText(oversPlayed);
        wicketsLostNotEmpty = hasText(wicketsLost);
        oversLostNotEmpty = hasText(oversLost);
        if (!(oversPlayedNotEmpty && wicketsLostNotEmpty && oversLostNotEmpty)) {

            return 1;
        }

        float input = Float.valueOf(oversPlayed.getText().toString());
        float f1 = (input % 1);
        String str1 = String.format("%.01f", f1);
        float floatValue = Float.valueOf(str1);

        if (Float.valueOf(oversPlayed.getText().toString()) > i11.overAvailabe.input || floatValue > .5) {
            if(floatValue > .5)
                overPlayedInvalid = true;
            return 2;
/*
            if (floatValue > .5) {
                floatValue = 0;
                return 2;
                //  System.err.println("incorrect cricket values Resetting values to" + this.input);
            }
*/
        }
        if (Float.valueOf(oversPlayed.getText().toString()) == i11.overAvailabe.input)// || (Float.valueOf(oversPlayed.getText().toString())+i11.overAvailabe.input) ==)
        {
            if(!(oversLost.getText().toString().equals("0.0"))) {
                System.out.println(".....................................................Entered");
                oversLost.setText("0.0");
                return 3;
            }        }
        if (Float.valueOf(wicketsLost.getText().toString()) < i11.wicketLost || Float.valueOf(wicketsLost.getText().toString()) > 10) {
            return 4;
        }
        if (Float.valueOf(oversLost.getText().toString()) > (i11.overAvailabe.input - Float.valueOf(oversPlayed.getText().toString()))) {
            return 5;
        }
        //Testing overLost
        float inputL = Float.valueOf(oversLost.getText().toString());
        float f1L = (inputL % 1);
        String str1L = String.format("%.01f", f1L);
        float floatValueL = Float.valueOf(str1L);

        if ( floatValueL > .5) {
            return 6;
        }

        return 0;
    }


    public void callAlert()
    {

        System.out.println("()#()@$@)($*($*@)($*)))))$*(@#@*)$(*)@*(#*)!#()!*$(@*$(%*)(*$)@*$)(!#*)(!)(#@)$(*)#*)($#*@)*$)(@$@)");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(" ");

        // set dialog message
        alertDialogBuilder
                .setMessage("Empty Fields Not Allowed!!!")
                .setCancelable(false)
                .setPositiveButton("yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        //Inning1.this.finish();
                        //iso.setText("50.0");
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interruptions, menu);
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
                    Intent i = new Intent(Interruptions.this, aboutApp.class);
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

                    Intent i1 = new Intent(Interruptions.this, dl.class);
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

        /*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }

    @Override
    public void onBackPressed()

    {
        exitcount++;
        if(exitcount==1)
            //   this.finish();
            Toast.makeText(getApplicationContext(), "double click for to close",
                    Toast.LENGTH_SHORT).show();

        if(exitcount>1) {
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            this.finish();
            Toast.makeText(getApplicationContext(), "Back press disabled",
                    Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        return;


    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("ccccccccckeydownccccccccccc"+exitcount);
        if (keyCode == KeyEvent.KEYCODE_BACK)
            exitcount++;

       System.exit(0);
        this.finish();
        if(exitcount==1)
            System.exit(0);
            this.finish();
           // Toast.makeText(getApplicationContext(), "double click for to close",
             //       Toast.LENGTH_SHORT).show();
        if(exitcount>1)
            this.finish();

        return false;
    }*/


}
