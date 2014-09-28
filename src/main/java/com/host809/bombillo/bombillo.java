package com.host809.bombillo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;

public class bombillo extends ActionBarActivity {
    private Camera camera;
    private boolean isLighOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bombillo);
        final ImageButton botonON = (ImageButton) findViewById(R.id.btm_on);
        final ImageButton botonOFF = (ImageButton) findViewById(R.id.btn_off);
        botonOFF.setVisibility(View.GONE);
        botonON.setOnClickListener(new OnClickListener()
                                   {

                                       @Override
                                       public void onClick(View v) {
                                           if(!isLighOn){
                                               camera = Camera.open();
                                               Parameters p = camera.getParameters();
                                               p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                                               camera.setParameters(p);
                                               camera.startPreview();
                                               isLighOn=true;
                                           }
                                           botonON.setVisibility(View.GONE);
                                           botonOFF.setVisibility(View.VISIBLE);
                                       }
                                   }
        );

        botonOFF.setOnClickListener(new OnClickListener()
                                    {

                                        @Override
                                        public void onClick(View v) {

                                            //camera = Camera.open();
                                            if(isLighOn){
                                                Parameters p = camera.getParameters();
                                                p.setFlashMode(Parameters.FLASH_MODE_OFF);
                                                camera.setParameters(p);
                                                camera.stopPreview();
                                                camera.release();
                                                isLighOn=false;
                                            }
                                            botonOFF.setVisibility(View.GONE);
                                            botonON.setVisibility(View.VISIBLE);
                                        }
                                    }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bombillo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
