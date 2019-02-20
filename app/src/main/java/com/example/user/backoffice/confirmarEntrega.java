package com.example.user.backoffice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class confirmarEntrega extends AppCompatActivity {

    TextView BP1, Moc, inter, tablet1,fec,hor,nomFun,sena,senb,cable;
    Button a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_entrega);

        Bundle  extras = getIntent().getExtras();

        String bp= extras.getString("BP");
        String mochila= extras.getString("Mochila");
        String interfonia= extras.getString("Interfonia");
        String tablet = extras.getString("Tablet");
        String fecha = extras.getString("Fecha");
        String hora = extras.getString("Hora");
        String senaa = extras.getString("SenaleraA");
        String senab= extras.getString("SenaleraB");
        String nomFuncio = extras.getString("usuario");
        String cab = extras.getString("Cable");

        Toast.makeText(this,hora, Toast.LENGTH_SHORT).show();

        BP1 = (TextView) findViewById(R.id.txtConfEntregaBP);
        Moc = (TextView) findViewById(R.id.txtConfEntregaMochila);
        inter = (TextView) findViewById(R.id.txtConfEntregaInterfonia);
        tablet1 = (TextView) findViewById(R.id.txtConfEntregaTablet);
        fec = (TextView)findViewById(R.id.txtConfEntregaFecha);
        hor = (TextView) findViewById(R.id.txtHora);
        nomFun = (TextView) findViewById(R.id.txtNombre);
        sena = (TextView) findViewById(R.id.txtConfEntregasena);
        senb = (TextView) findViewById(R.id.txtConfEntregasenb);
        cable = (TextView) findViewById(R.id.txtCableConfirmar);
        a = (Button) findViewById(R.id.btniraMenuPrincipal);
        BP1.setText(bp);
        Moc.setText(mochila);
        inter.setText(interfonia);
        tablet1.setText(tablet);
        fec.setText(fecha);
        hor.setText(hora);
        nomFun.setText(nomFuncio);
        sena.setText(senaa);
        senb.setText(senab);
        cable.setText(cab);
    }
    public void btnMenuPrincipal(View view){
        a.setEnabled(false);
        String funcionario = nomFun.getText().toString();
        Intent i = new Intent(this,menuPrincipal.class);
        i.putExtra("usuario",funcionario);
        startActivity(i);
        finish();
    }
}
