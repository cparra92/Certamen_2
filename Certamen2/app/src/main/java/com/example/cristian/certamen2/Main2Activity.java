package com.example.cristian.certamen2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edit ;
    String dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        View boton = findViewById(R.id.button);
        boton.setOnClickListener(this);
        edit = (EditText)findViewById(R.id.editText);
        dato = edit.getText().toString();

        //System.out.println(dato);

    }

    public void onClick(View vista)
    {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("dato",edit.getText().toString());
        startActivity(intent);

    }


}
