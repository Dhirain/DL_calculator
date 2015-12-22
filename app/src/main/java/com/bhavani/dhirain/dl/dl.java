package com.bhavani.dhirain.dl;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class dl extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dl, menu);
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
                    Intent i = new Intent(dl.this, aboutApp.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    //this.finish();
                }
                return true;
            case R.id.dl:
                if (item.isChecked())
                    item.setChecked(false);
                else {

                    Intent i1 = new Intent(dl.this, dl.class);
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i1);
                    //this.finish();
                }
                return true;
            /*case R.id.exit:
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