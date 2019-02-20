package com.example.user.backoffice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class menuPrincipal extends AppCompatActivity {

    Button entrega, recibo, logout;
    TextView usuarioBienvenida;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Bundle extras = getIntent().getExtras();
        String usuario= extras.getString("usuario").toUpperCase();
        usuarioBienvenida = (TextView) findViewById(R.id.txtUsuarioBienvenida);
        entrega = (Button) findViewById(R.id.btnMenuPrinEntrega);
        recibo = (Button) findViewById(R.id.btnMenuPrinRecibo);
        logout = (Button) findViewById(R.id.btnMenuPrinLogout);
        usuarioBienvenida.setText(usuario);



    }

    public void entrega (View view){

        String funcionario = usuarioBienvenida.getText().toString();

        Intent i = new Intent(getApplicationContext(), menuEntrega.class);
        i.putExtra("usuario",funcionario);
        startActivity(i);
        finish();

    }
    public void recibo (View view){

        String funcionario = usuarioBienvenida.getText().toString();

        Intent i = new Intent(getApplicationContext(), menuRecibo.class);
        i.putExtra("usuario",funcionario);
        startActivity(i);
        finish();
    }

    public void armarMochilas (View view){
        String funcionario = usuarioBienvenida.getText().toString();

        Intent i = new Intent(getApplicationContext(), armadoMochilas.class);
        i.putExtra("usuario",funcionario);
        startActivity(i);
        finish();

    }

    public void aMain (View view){

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
