package com.example.user.backoffice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.RandomAccess;

public class controlObservaciones extends AppCompatActivity {

    TextView mensaje;
    public static RadioButton radioBtnCambio,radioBtnPerdida;
    public static EditText bpCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_observaciones);
        Bundle  extras = getIntent().getExtras();
        String d1= extras.getString("elemento");


        mensaje = (TextView) findViewById(R.id.txtMensaje);
        mensaje.setText("El Elemento "+d1+" sufrio :");

        radioBtnCambio = (RadioButton) findViewById(R.id.radioButtonCambio);
        radioBtnPerdida = (RadioButton)findViewById(R.id.radioButtonPerdido);
        bpCambio = (EditText)findViewById(R.id.editTextBP);



    }

    public void volverApopUpRecibo(View v){


        Bundle  extras = getIntent().getExtras();
        String d1= extras.getString("elemento");

        Intent i = new Intent(this,popUpRecibo.class);

        if(radioBtnCambio.isChecked() && bpCambio.getText().toString().equals("")){

            Toast.makeText(this,"falta BP Cambio",Toast.LENGTH_SHORT).show();
        }
        if(radioBtnCambio.isChecked() && !bpCambio.getText().toString().equals("")){

            i.putExtra("cambio",bpCambio.getText().toString());
            i.putExtra("elementoCambio",d1);
            startActivity(i);
            finish();
        }

        if(radioBtnPerdida.isChecked()){

            i.putExtra("perdida","PERDIDA");
            i.putExtra("elementoCambio",d1);
            startActivity(i);
            finish();
        }


    }

    public void cerrar(View v1){

        Intent i = new Intent(this,popUpRecibo.class);
        startActivity(i);
        finish();
    }
}
