package com.example.user.backoffice;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText usuario, password;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ingresar(View view){
        Intent i = new Intent(getApplicationContext(), menuPrincipal.class);
            i.putExtra("usuario","Ejecutivo BackOffice");
            startActivity(i);
            finish();
    }
    // public boolean verificar(String usuario, String pas){
        //String user = usuario.toLowerCase();
       // String pass = pas.trim();
       // String usBD,pasBD;

       // boolean resultado=false;
       // try{
         //   Statement st = conexionBD().createStatement();
          //  ResultSet rs = st.executeQuery("SELECT [user]\n" +
          //          "      ,[password]\n" +
               //     "  FROM [dbo].[usuarios] \n" +
               //     "  WHERE[user] = '"+user+"'");

           // if(rs.next()){
              //  usBD = rs.getString("user");
              //  pasBD = rs.getString("password");
              //  if(user.equals(usBD)&&pass.equals(pasBD)){
               //     resultado = true;
              //      return  resultado;
               //}
               // else{
               //     return  resultado;
              //  }

           // }else{

              //  Toast.makeText(this,"no hay registro",Toast.LENGTH_SHORT).show();
          //  }
      //  }catch (Exception e){
         //   Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
       // }
      //  return resultado;
  //  }
//


}
