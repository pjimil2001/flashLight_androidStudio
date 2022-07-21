package com.example.flash_light;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton flashbtn;


    Boolean hascameralight =false;
    Boolean flashon =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashbtn=findViewById(R.id.flashbtn);


        hascameralight=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        flashbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (hascameralight){
                    if(flashon){
                        flashon=false;
                        flashbtn.setImageResource(R.drawable.img22);
                        try {
                            lightoff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                    }
                    else {

                        flashon=true;
                        flashbtn.setImageResource(R.drawable.img33);
                        try {
                            lighton();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }


                    }}

                    else
                    {

                        Toast.makeText(MainActivity.this, "device not supoort flash light... ", Toast.LENGTH_SHORT).show();
                    }

            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void lighton() throws CameraAccessException {
        CameraManager cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String camid=cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(camid,true);
        Toast.makeText(MainActivity.this, "flash is on  ", Toast.LENGTH_SHORT).show();


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void lightoff() throws CameraAccessException {
        CameraManager cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String camid=cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(camid,false);
        Toast.makeText(MainActivity.this, "flash is off ", Toast.LENGTH_SHORT).show();


    }

}