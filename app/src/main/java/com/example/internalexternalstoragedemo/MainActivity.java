package com.example.internalexternalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_text;
    Button btn_create, btn_read, btn_delete, show_all,btn_create_dir;
    public static final String FILE_NAME = "mytextfile";
    public static final String DIR_NAME = "my_dir";
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
        btn_delete = findViewById(R.id.btn_delete);
        btn_create_dir = findViewById(R.id.btn_create_dir);
        show_all = findViewById(R.id.show_all);
        btn_create.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_create_dir.setOnClickListener(this);
        show_all.setOnClickListener(this);
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
                    while ((read = inputStream.read()) != -1) {
                        Log.d("inputStream", "" + read);
                        stringBuffer.append((char) read);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv_moutputtxt.setText(stringBuffer.toString());

            case R.id.btn_delete:
                deleteFile(FILE_NAME);
            case R.id.show_all:
                String[] filesNames = fileList();

                for (String fileName : filesNames) {
                    tv_moutputtxt.append(fileName + "\n");
                    Log.d("Filenames<<<",fileName);
                }
            case R.id.btn_create_dir:
                File path = getDir(DIR_NAME,MODE_PRIVATE);
                File file = new File(path,"abc.txt");
                String dataHi = "Hi";

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(dataHi.getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }


}
