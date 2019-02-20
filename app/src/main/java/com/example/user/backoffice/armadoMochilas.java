package com.example.user.backoffice;

import android.content.Intent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class armadoMochilas extends AppCompatActivity {

    public static EditText mochilaC,cableC,interfoniaC,tabletC, senaA,senaB;
    Button btnMoc,btnCable,btnInter,btnTablet,btnSenaA,btnSenaB;
    TextView funcionario;
    Date datec = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        String usuario= extras.getString("usuario").toUpperCase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armado_mochilas);
        mochilaC = (EditText) findViewById(R.id.txtMochilaCreacion);
        cableC = (EditText) findViewById(R.id.txtCableCreacion);
        interfoniaC = (EditText) findViewById(R.id.txtInterCreacion);
        tabletC = (EditText) findViewById(R.id.txtTabletCreacion);
        senaA = (EditText) findViewById(R.id.txtSenaleraACreacion);
        senaB = (EditText) findViewById(R.id.txtSenaleraBCreacion);
        funcionario = (TextView) findViewById(R.id.txtFuncionarioBO);
        funcionario.setText(usuario);

        btnMoc = (Button) findViewById(R.id.btnMochilaCreacion);
        btnCable = (Button) findViewById(R.id.btnCableCreacion);
        btnInter = (Button) findViewById(R.id.btnInterCreacion);
        btnTablet = (Button) findViewById(R.id.btnTabletCreacion);
        btnSenaA = (Button) findViewById(R.id.btnSenaleraACreacion);
        btnSenaB = (Button) findViewById(R.id.btnSenaleraBCreacion);
    }

    public Connection conexionBD(){

        Connection conexion = null;
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://fernandez.database.windows.net;databaseName=cargoMobility;user=fernandez05;password=Pg37973797;");
        }catch (Exception e ){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this,"funciona la conexion",Toast.LENGTH_SHORT).show();


        return conexion;
    }

    public void atras(View view1){

        String funcionario1 = funcionario.getText().toString();
        Intent i = new Intent(this,menuPrincipal.class);
        i.putExtra("usuario",funcionario1);
        startActivity(i);
        finish();

    }

    public void enviarABD(View view){

        conexionBD();

        String mochh = mochilaC.getText().toString().trim();
        String cabll = cableC.getText().toString().trim();
        String interr = interfoniaC.getText().toString().trim();
        String sennna = senaA.getText().toString().trim();
        String sennnb = senaB.getText().toString().trim();
        String fecha = dateFormat.format(datec);
        String hora = hourFormat.format(datec);


        try{
                Statement st = conexionBD().createStatement();
                ResultSet rs = st.executeQuery("UPDATE [dbo].[mochilasArmadas2] SET [id_cotActual] ='0',[id_interfonia] ='"+interr+"',[id_cable] ='"+cabll+"',[id_tablet]='POR ASIGNAR',[id_senaleraA]='"+sennna+"',[id_senaleraB]='"+sennnb+"',[fechaEntrega]='POR ASIGNAR',[horaEntrega]='POR ASIGNAR',[horaDevolucion]='POR ASIGNAR',[fechaDevolucion]='POR ASIGNAR',[estado]='DISPONIBLE',[observacion]='SIN OBSERVACION' WHERE [id_mochila]='"+mochh+"';");

            }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        try{
            Statement sti = conexionBD().createStatement();
            ResultSet rsi = sti.executeQuery("UPDATE [dbo].[interfoniaCot] SET [observaciones] ='"+mochh+"'  WHERE [estado]='"+interr+"';");

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try{
            Statement stc = conexionBD().createStatement();
            ResultSet rsc = stc.executeQuery("UPDATE [dbo].[cablesCot] SET [observaciones] ='"+mochh+"'  WHERE [estado]='"+cabll+"';");

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{
            Statement stsa = conexionBD().createStatement();
            ResultSet rssa = stsa.executeQuery("UPDATE [dbo].[senalerasCot] SET [observaciones] ='"+mochh+"'  WHERE [estado]='"+sennna+"';");

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{
            Statement stsb = conexionBD().createStatement();
            ResultSet rssb = stsb.executeQuery("UPDATE [dbo].[senalerasCot] SET [observaciones] ='"+mochh+"'  WHERE [estado]='"+sennnb+"';");

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }



        String funcionario1 = funcionario.getText().toString();
        Intent i = new Intent(this,menuPrincipal.class);
        i.putExtra("usuario",funcionario1);
        startActivity(i);
        finish();


    }

    public void scannerMochilaCreacion (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","MochilaCreacion");
        startActivity(i1);
    }
    public void scannerInterfonia (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","InterfoniaCreacion");
        startActivity(i1);
    }
    public void scannerCable (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","CableCreacion");
        startActivity(i1);
    }
    public void scannerTablet (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","TabletCreacion");
        startActivity(i1);
    }
    public void scannerSenaleticaA (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","SenaleraACreacion");
        startActivity(i1);
    }
    public void scannerSenaleticaB (View view) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","SenaleraBCreacion");
        startActivity(i1);
    }






}
