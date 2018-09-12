package com.example.azat.savefileinputoutput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editFile;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editFile = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
    }


    public void read(final View view) {
        try {
            final FileInputStream stream = openFileInput("example.txt");
            final InputStreamReader streamReader = new InputStreamReader(stream);
            final BufferedReader bufferedReader = new BufferedReader(streamReader);
            final StringBuilder stringBuffer = new StringBuilder();
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines).append("\n");
            }
            textView.setText(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(final View view) {
        final String value = editFile.getText().toString();
        try {
            final FileOutputStream fileOutputStream = openFileOutput("example.txt", MODE_PRIVATE);
            fileOutputStream.write(value.getBytes());
            fileOutputStream.close();
            editFile.setText("");
            Toast.makeText(MainActivity.this, "Text Save", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
