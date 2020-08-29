package com.example.internalexternalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageInBinary extends AppCompatActivity {

    Button btn_assets, btn_drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_in_binary);

        btn_drawable = findViewById(R.id.btn_drawable);
        btn_assets = findViewById(R.id.btn_assets);

        btn_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap data = getImage();
                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = openFileOutput("mypm.jpeg",MODE_PRIVATE);
                    data.compress(Bitmap.CompressFormat.JPEG,1,fileOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fileOutputStream!=null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        });

        btn_drawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pm);

                FileOutputStream fileOutputStream = null;

                try {
                    fileOutputStream = openFileOutput("drawablePm.jpeg",MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fileOutputStream!=null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }


        });
    }


    private Bitmap getImage() {
        Bitmap image = null;
        try {
            InputStream inputStream = getAssets().open("pm.jpg");
            image = BitmapFactory.decodeStream(inputStream);

            Log.d("bitmap<<<",""+image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
