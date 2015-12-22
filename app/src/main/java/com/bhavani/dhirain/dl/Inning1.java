package com.bhavani.dhirain.dl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;


public class Inning1 extends ActionBarActivity implements  Inning {

    Bundle interruptionsData = null;
    static int count;
    public static DuckworthMethod inning1 = new DuckworthMethod();
    EditText inningSartOver;
    TextView resoure;
    EditText iso;
    final Context context = this;
    RelativeLayout l1;
    String srtingResult;
    static float i1_overAvailabe;
    static boolean isIntialised;
    EditText scoreAtEnd;
    boolean isstrtOversSet;
    public static int exitcount = 0;
    float totalOversPlayed_inBracket;
    TextView textView_finalScore1;
    private static boolean Exceded_input = false;


    //
    static String oversPlayed[] = new String[10], wicketsLost[] = new String[10], oversLost[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.ThemeOverlay_Material_Dark);
        setContentView(R.layout.activity_inning1);


        iso = (EditText) findViewById(R.id.id_inningSartOver);
        String iso1 = iso.getText().toString().trim();

        if (i1_overAvailabe != 0.0 && count > 0) {
            // Toast t =Toast.makeText(Inning1.this, "Value Already Added", Toast.LENGTH_LONG);
            //  t.show();
            iso.setText(i1_overAvailabe + "");
            iso.setEnabled(false);
            //editText.setEnabled(false);
            iso.setKeyListener(null);
            iso.setInputType(InputType.TYPE_NULL);
            //return false;
        }

        Bundle interruptionsData = getIntent().getExtras();

        l1 = (RelativeLayout) findViewById(R.id.id_innings1_rl);
        if (interruptionsData == null) {
            return;
        }
        if (getIntent().getExtras().getBoolean("isExit")) {
            Toast.makeText(getApplicationContext(), "Back press disabled",
                    Toast.LENGTH_SHORT).show();

            this.finish();

        }


        oversPlayed[count] = interruptionsData.getString("oversPlayed");

        wicketsLost[count] = interruptionsData.getString("wicketsLost");
        oversLost[count] = interruptionsData.getString("oversLost");
        totalOversPlayed_inBracket=interruptionsData.getFloat("totalOversPlayed_fromInt_forDisplay");

        Log.i("totalOversPlayed", String.valueOf(totalOversPlayed_inBracket));

        textView_finalScore1=(TextView) findViewById(R.id.id_finalScore1);

        Log.i("totalOversPlayed1", String.valueOf(totalOversPlayed_inBracket));
        if(totalOversPlayed_inBracket!=0F)
        {
            textView_finalScore1.setText("Team 1 Score ("+totalOversPlayed_inBracket+" overs)");
        }

        if (!(oversPlayed[count] == null && wicketsLost[count] == null && oversLost[count] == null)) {




            displayInteruptionDetails();
        }
    }

    public void displayInteruptionDetails() {

        TextView tv3[] = new TextView[count + 1];
        Button b1 = (Button) findViewById(R.id.addInteruptionButton);

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
                //params.topMargin = 300;// count*70;
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
        inning1 = dm;
    }

    public DuckworthMethod getObject() {
        return inning1;
    }

    public boolean initialiseIt() {
        iso = (EditText) findViewById(R.id.id_inningSartOver);
        String iso1 = iso.getText().toString().trim();
        if (iso1.equals("") || iso1 == null) {//bhavani added iso1 == null condition
            Toast t = Toast.makeText(Inning1.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
            t.show();
            iso.setText("50.0");

        }
        iso1 = iso.getText().toString().trim();
        if (!validate(iso1)) {
            if (Exceded_input) {
                Toast t = Toast.makeText(Inning1.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
                t.show();
                iso.setText("50.0");
                Exceded_input = false;
                //return true;//validation has failed and input has been set to true
            } else {
                iso.setError("Not proper values");
                return false;
            }

        }
        // else bhavani
        //{


        inning1.initilizeAllovres();
        inning1.overAvailabe.setOver(i1_overAvailabe);
        inning1.intilizeStart();
        // count++;

        Intent i = new Intent(Inning1.this, Interruptions.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("innings", "Innings1");
        i.putExtra("count", count);
        i.putExtra("iso", i1_overAvailabe);
        startActivity(i);
        this.finish();


        iso.setText(String.valueOf(i1_overAvailabe));
        iso.setEnabled(false);
        //editText.setEnabled(false);
        iso.setKeyListener(null);
        iso.setInputType(InputType.TYPE_NULL);
        return true;
        //  }
    }


    public void onClick(View view)//3048178
    {
        exitcount=0;

        if (!isIntialised) {

            isIntialised = initialiseIt();
        }
        if (isIntialised) {
            count++;

            Intent i = new Intent(Inning1.this, Interruptions.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("innings", "Innings1");
            i.putExtra("count", count);
            i.putExtra("iso", i1_overAvailabe);
            startActivity(i);
            this.finish();
        }

    }


    public boolean initialiseItonInn2() {
        iso = (EditText) findViewById(R.id.id_inningSartOver);

        String iso1 = iso.getText().toString().trim();
        if (iso1.equals("")) {
            Toast t = Toast.makeText(Inning1.this, "Overs has been set to default value", Toast.LENGTH_SHORT);
            t.show();
            iso.setText("50.0");
            // return false;
        }
        iso1 = iso.getText().toString().trim();
        if (!validate(iso1)) {
            if (Exceded_input) {
                Toast t = Toast.makeText(Inning1.this, "Default 50 overs has been Set", Toast.LENGTH_SHORT);
                t.show();
                iso.setText("50.0");
                Exceded_input = false;
                //return true;//validation has failed and input has been set to true
            } else {
                iso.setError("Not proper values");
                return false;
            }

        }


/*        else {*/

        inning1.initilizeAllovres();
        inning1.overAvailabe.setOver(i1_overAvailabe);
        inning1.intilizeStart();
        //  count++;


        iso.setText(String.valueOf(i1_overAvailabe));
        iso.setEnabled(false);
        iso.setFocusable(false);
        return true;
    }

//}


    public void onClickInnings2(View view)
    {
        exitcount=0;
        if(!isIntialised){

            isIntialised = initialiseItonInn2();
        }
         if (isIntialised) {

            scoreAtEnd = (EditText) findViewById(R.id.id_team1FS);
            String string_scoreAtEnd = scoreAtEnd.getText().toString().trim();

            int int_scoreAtEnd = 0;
            if (string_scoreAtEnd != null && !(string_scoreAtEnd.equals(""))) {
                int_scoreAtEnd = Integer.valueOf(string_scoreAtEnd);
            }

            if (int_scoreAtEnd == 0 || string_scoreAtEnd == "") {
                //callAlert();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("Score Please!!!");
                alertDialogBuilder
                        .setMessage("Enter the score at end of innings")//bhavani
                        .setCancelable(false)
                        .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                //OveratEnd.setText("50.0");
            } else {
                inning1.finalScore = int_scoreAtEnd;
                inning1.enterScore();
                Intent i = new Intent(Inning1.this, Innings2.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("inn1Finalcore", inning1.finalScore);
                i.putExtra("resource1", inning1.Resource);
                startActivity(i);
                this.finish();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inning1, menu);
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
                Intent i = new Intent(Inning1.this, aboutApp.class);
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

                    Intent i1 = new Intent(Inning1.this, dl.class);
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




    boolean validate(String iso)
    {

            float input = Float.valueOf(iso);

            float f1 = (input % 1);
            String str1 = String.format("%.01f", f1);
            float floatValue = Float.valueOf(str1);

        if (iso != null)
            i1_overAvailabe = input;


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
