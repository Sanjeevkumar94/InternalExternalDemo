package com.example.internalexternalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_text;
    Button btn_create,btn_read;
    public static final String FILE_NAME = "mytextfile";
    FileOutputStream fileOutputStream = null;
    TextView tv_moutputtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_create = findViewById(R.id.btn_create);
        btn_read = findViewById(R.id.btn_read);
        et_text = findViewById(R.id.et_text);
        tv_moutputtxt = findViewById(R.id.tv_moutputtxt);
        btn_create.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        String path = getFilesDir().getAbsolutePath();
        tv_moutputtxt.setText(path);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                String data = et_text.getText().toString();
                try {
                    fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
              }
                break;
            case R.id.btn_read:
            StringBuffer stringBuffer = new StringBuffer();
                InputStream inputStream = null;
                try {
                    inputStream = openFileInput(FILE_NAME);
                    int read;
                    while ((read=inputStream.read())!=-1){
                        Log.d("inputStream",""+read);
                        stringBuffer.append((char)read);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv_moutputtxt.setText(stringBuffer.toString());
        }

    }
}
