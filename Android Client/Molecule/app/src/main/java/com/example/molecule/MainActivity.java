package com.example.molecule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.util.EncodingUtils;

import com.example.lib.Storage;
import com.example.lib.newDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    public static final String ENCODING = "UTF-8";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        Button buttonadd = (Button) findViewById(R.id.buttonadd);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage st = new Storage();
                String pathcsv = copyAssetAndWrite("writers.csv");

                newDatabase database = st.readCSV(pathcsv);
                EditText filepath = (EditText) findViewById(R.id.edittextadd);

                String pathtxt = copyAssetAndWrite(filepath.getText().toString()+".txt");
                String result =  database.addMolecule(pathtxt);
                TextView text2= (TextView) findViewById(R.id.textViewadd);
                text2.setText(result);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                Storage st = new Storage();
                String pathcsv = copyAssetAndWrite("writers.csv");

                newDatabase database = st.readCSV(pathcsv);
                EditText filepath = (EditText) findViewById(R.id.edittext);

                String pathtxt = copyAssetAndWrite(filepath.getText().toString()+".txt");
                String result =  database.findMolecule(pathtxt);
                TextView text= (TextView) findViewById(R.id.textView2);
                text.setText(result);

            }

        });

    }
    private String copyAssetAndWrite(String fileName){
        try {
        File cacheDir=getCacheDir(); if (!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        File outFile =new File(cacheDir,fileName);
        if (!outFile.exists()){ boolean res=outFile.createNewFile();
        if (!res){ return null; }
        }else { if (outFile.length()>10){//表示已经写入一次
            return outFile.getPath();
        }
        }
        InputStream is=getAssets().open(fileName);
        FileOutputStream fos = new FileOutputStream(outFile);
        byte[] buffer = new byte[1024];
        int byteCount;
        while ((byteCount = is.read(buffer)) != -1) {
            fos.write(buffer, 0, byteCount);
        }
        fos.flush();
        is.close();
        fos.close(); return outFile.getPath();
    } catch (IOException e) {
        e.printStackTrace();
    } return null;
    }



}
