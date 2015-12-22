package com.bhavani.dhirain.dl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ParTableGenerator extends Activity {

    float overAvailabePercentile2;
    float oversPlayedSoFar;
    float totalOversPlayed;
    int wicketLostSoFar;
    float Resource1;
    int Team1FinalScore;
    int parScore;
    public static int exitcount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overAvailabePercentile2 = getIntent().getExtras().getFloat("overAvailabePercentile");
        oversPlayedSoFar = getIntent().getExtras().getFloat("oversPlayedSoFar");
        totalOversPlayed = getIntent().getExtras().getFloat("totalOversPlayed");
        wicketLostSoFar = getIntent().getExtras().getInt("wicketLostSoFar");
        Resource1 = getIntent().getExtras().getFloat("Resource1");
        Team1FinalScore = getIntent().getExtras().getInt("Team1FinalScore");


        ScrollView sv = new ScrollView(this);
        TableLayout tableLayout = createTableLayout(52, 12);
        HorizontalScrollView hsv = new HorizontalScrollView(this);

        hsv.addView(tableLayout);
        sv.addView(hsv);
        setContentView(sv);

    }

    public void makeCellEmpty(TableLayout tableLayout, int rowIndex, int columnIndex) {
        // get row from table with rowIndex
        TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

        // get cell from row with columnIndex
        TextView textView = (TextView) tableRow.getChildAt(columnIndex);

        // make it black
        textView.setBackgroundColor(Color.BLACK);
    }

    public void setHeaderTitle(TableLayout tableLayout, int rowIndex, int columnIndex) {

        // get row from table with rowIndex
        TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

        // get cell from row with columnIndex
        TextView textView = (TextView) tableRow.getChildAt(columnIndex);

        textView.setText("Hello");
    }

    private TableLayout createTableLayout(int rowCount, int columnCount) {
        // 1) Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setBackgroundColor(Color.BLACK);

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(2, 2, 2, 2);
        tableRowParams.weight = 2;

        // 3) create tableRow
        TableRow tableRow2 = new TableRow(this);
        tableRow2.setBackgroundColor(Color.BLACK);

        // 4) create textView
        TextView textView4 = new TextView(this);
        //  textView.setText(String.valueOf(j));
        textView4.setBackgroundColor(Color.BLACK);
        textView4.setGravity(Gravity.CENTER);

        textView4.setText("   ");

        // 5) add textView to tableRow
        tableRow2.addView(textView4, tableRowParams);

        for (int tempWicket = wicketLostSoFar; tempWicket <= 10; tempWicket++) {

            // 4) create textView
            TextView textView2 = new TextView(this);
            //  textView.setText(String.valueOf(j));
            textView2.setBackgroundColor(Color.YELLOW);
            textView2.setGravity(Gravity.CENTER);

            textView2.setText("  wicket" + tempWicket + "  ");

            // 5) add textView to tableRow
            tableRow2.addView(textView2, tableRowParams);
        }
        // 6) add tableRow to tableLayout
        tableLayout.addView(tableRow2, tableLayoutParams);


        float tempResource2;

        if (oversPlayedSoFar==0)
        {
            oversPlayedSoFar=1;
        }
        int i = (int) oversPlayedSoFar;
        int int_totalOversPlayed = (int) totalOversPlayed;

        for (; i <= int_totalOversPlayed; i++) {

            // 3) create tableRow
            TableRow tableRow = new TableRow(this);
            tableRow.setBackgroundColor(Color.BLACK);

            // 4) create textView3
            TextView textView3 = new TextView(this);

            textView3.setBackgroundColor(Color.GRAY);
            textView3.setGravity(Gravity.CENTER);

            textView3.setText("  overs" + i + "  ");
            // 5) add textView to tableRow
            tableRow.addView(textView3, tableRowParams);

            int j;


            for (j = wicketLostSoFar; j <= 10; j++) {
                //4) create textView
                TextView textView = new TextView(this);
                textView.setText(String.valueOf(j));
                textView.setBackgroundColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);

                Log.i("Negative", "overAvailabePercentile2" + overAvailabePercentile2);
                tempResource2 = overAvailabePercentile2;
                Log.i("Negative", "tempResource2" + tempResource2);
                int k1;
                k1 =  int_totalOversPlayed - i;
                Log.i("Negative", "k1" + k1);
                float k1per = PercentileCoversion.getPercentile(k1 * 6, j);
                Log.i("Negative", "k1per" + k1per);
                tempResource2 = tempResource2 - k1per;

                Log.i("Negative", "tempResource2" + tempResource2);
                Log.i("Negative", "Resource1" + Resource1);



                if (tempResource2 <= Resource1) {
                    parScore = (int) (Team1FinalScore * (tempResource2 / Resource1));
                    Log.i("Negative1", "parScore" + parScore);
                    Log.i("Negative1", "<=");
                }

                if (tempResource2 > Resource1) {
                    //S + (R2 – R1) x G50/100 + 1
                    parScore = (int) (Team1FinalScore + ((tempResource2 - Resource1) * (2.45)));
                    Log.i("Negative1", "parScore" + parScore);
                    Log.i("Negative1", ">");
                }

                Log.i("Negative1", "parScore" + parScore);
                textView.setText("   " + parScore + "   ");

                // 5) add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }


            // 6) add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_par_table_generator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //
        switch (item.getItemId()) {
            case R.id.about:
                if (item.isChecked())
                    item.setChecked(false);
                else {
                    //Intent i = new Intent()
                    Intent i = new Intent(ParTableGenerator.this, aboutApp.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    //this.finish();
                }
                return true;
            case R.id.dl:
                if (item.isChecked())
                    item.setChecked(false);
                else {

                    Intent i1 = new Intent(ParTableGenerator.this, dl.class);
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i1);
                    //this.finish();
                }
                return true;
           /* case R.id.exit:
                if (item.isChecked())
                    item.setChecked(false);
                else {

                    finish();
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                return true;*/
           /* case R.id.exit1:
                if (item.isChecked())
                    item.setChecked(false);
                else {

                    finish();
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                return true;*/
            default:
                return super.onOptionsItemSelected(item);


        }

        /*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }
}
