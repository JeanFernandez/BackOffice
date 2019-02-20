package com.example.user.backoffice;

import android.content.Intent;
import android.os.AsyncTask;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

public class popUpRecibo extends AppCompatActivity {

    TextView bpC,mochilaC,interfoniaC,tabletC,senaC,senbC,cableC,m2,i2,t2,sa2,sb2,ca2,bo2,co2;
    Button btnAtras,btnComfirmarLista,botonConfirmarRecibo;
    RadioButton rbmc,rbmd,rbmp,rbic,rbid,rbip,rbcc,rbcd,rbcp,rbtc,rbtd,rbtp,rbsac,rbsad,rbsap,rbsbc,rbsbd,rbsbp;
    EditText moc,inc,tac,cac,sac,sbc;
    Date date = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ProgressBar prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_recibo);

        Bundle  extras = getIntent().getExtras();

        String nf = extras.getString("usuario");
        bo2 = (TextView) findViewById(R.id.fu2);
        bo2.setText(nf);



        String mBD= extras.getString("MochilaBD");
        String iBD= extras.getString("InterfoniaBD");
        String cBD = extras.getString("CableBD");
        String tBD = extras.getString("TabletBD");
        String saBD = extras.getString("SenaleraABD");
        String sbBD= extras.getString("SenaleraBBD");

        String mSC= extras.getString("MochilaSC");
        String iSC= extras.getString("InterfoniaSC");
        String cSC = extras.getString("CableSC");
        String tSC = extras.getString("TabletSC");
        String saSC = extras.getString("SenaleraASC");
        String sbSC= extras.getString("SenaleraBSC");

        String fecha3 = extras.getString("Fecha");
        String hora3 = extras.getString("Hora");
        String bp3= extras.getString("BP");

        bpC = (TextView) findViewById(R.id.txtestadoCot);
        bpC.setText("OK");
        mochilaC = (TextView) findViewById(R.id.txtestadoMochila);
        interfoniaC = (TextView) findViewById(R.id.txtestadoInter);
        tabletC = (TextView) findViewById(R.id.txtestadoTablet);
        senaC = (TextView) findViewById(R.id.txtestadoSenA);
        senbC = (TextView) findViewById(R.id.txtestadoSenB);
        cableC = (TextView) findViewById(R.id.txtestadoCable);

        m2 = (TextView) findViewById(R.id.m1);
        i2 = (TextView) findViewById(R.id.i1);
        t2 = (TextView) findViewById(R.id.t1);
        sa2 = (TextView) findViewById(R.id.sa1);
        sb2 = (TextView) findViewById(R.id.sb1);
        ca2 = (TextView) findViewById(R.id.c1);
        co2 = (TextView) findViewById(R.id.co1);
        co2.setText(bp3);




        btnAtras = (Button) findViewById(R.id.btnPopUpAtras);
        btnComfirmarLista = (Button) findViewById(R.id.btnPopUpListo);




        if(mBD.equals(mSC)){
            mochilaC.setText("OK");
            m2.setText(mBD);
        }else{
            mochilaC.setText("ERROR");
            m2.setText(mSC);
            rbmc.setEnabled(true);
            moc.setEnabled(true);
            rbmd.setEnabled(true);
            rbmp.setEnabled(true);

        }
        if(iBD.equals(iSC)){
            interfoniaC.setText("OK");
            i2.setText(iBD);
        }else{
            interfoniaC.setText("ERROR");
            i2.setText(iSC);
            rbic.setEnabled(true);
            inc.setEnabled(true);
            rbid.setEnabled(true);
            rbip.setEnabled(true);

        }
        if(cBD.equals(cSC)){
            cableC.setText("OK");
            ca2.setText(cBD);
        }else{
            cableC.setText("ERROR");
            ca2.setText(cSC);
            rbcc.setEnabled(true);
            cac.setEnabled(true);
            rbcd.setEnabled(true);
            rbcp.setEnabled(true);

        }
        if(tBD.equals(tSC)){
            tabletC.setText("OK");
            t2.setText(tBD);
        }else{
            tabletC.setText("ERROR");
            t2.setText(tSC);
            rbtc.setEnabled(true);
            tac.setEnabled(true);
            rbtd.setEnabled(true);
            rbtp.setEnabled(true);

        }
        if((saBD.equals(saSC) && sbBD.equals(sbSC))|| (saBD.equals(sbSC) && sbBD.equals(saSC))){
            senaC.setText("OK");
            sa2.setText(saBD);
            senbC.setText("OK");
            sb2.setText(sbBD);
        }else{
            senaC.setText("ERROR");
            sa2.setText(saSC);
            rbsac.setEnabled(true);
            sac.setEnabled(true);
            rbsad.setEnabled(true);
            rbsap.setEnabled(true);
            senbC.setText("ERROR");
            sb2.setText(sbSC);
            rbsbc.setEnabled(true);
            sbc.setEnabled(true);
            rbsbd.setEnabled(true);
            rbsbp.setEnabled(true);

        }

    }

    public void volverAtras(View vi){

        Intent i = new Intent(this,menuRecibo.class);
        i.putExtra("usuario", bo2.getText().toString());
        startActivity(i);
        finish();

    }



    public void volveraMP(View v) {

        conexionBD();

        String fechaR = dateFormat.format(date);
        String horaR = hourFormat.format(date);
        String mochila = m2.getText().toString();


        String bpCOT = co2.getText().toString();
        String elemento1 = m2.getText().toString();
        String elemento2 = i2.getText().toString();
        String elemento3 = t2.getText().toString();
        String funcionarioBO = bo2.getText().toString();
        String elemento4 = ca2.getText().toString();
        String elemento5 = sa2.getText().toString();
        String elemento6 = sb2.getText().toString();


        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("UPDATE [dbo].[mochilasArmadas2]" +
                    "SET [id_cotActual] ='0',[id_tablet]='POR ASIGNAR',[fechaDevolucion] ='"+fechaR+"', [horaDevolucion] ='"+horaR+"', [estado]='DISPONIBLE' WHERE [id_mochila]='"+mochila+"';");
            Toast.makeText(this,"Devolucion Correcta",Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }



        try{
            for (int i=1; i<7;i++){
                String elemento = "";
                if(i==1){
                    elemento = elemento1;
                }if(i==2){
                    elemento = elemento2;
                }if(i==3){
                    elemento = elemento3;
                }if(i==4){
                    elemento = elemento4;
                }if(i==5){
                    elemento = elemento5;
                }if(i==6){
                    elemento = elemento6;
                }
                PreparedStatement pstM = conexionBD().prepareStatement("insert into movimiento values (?,?,?,?,?,?,?,?,?)");
                pstM.setString(1,elemento);
                pstM.setString(2,fechaR);
                pstM.setString(3,"DEVOLUCION");
                pstM.setString(4,elemento1);
                pstM.setString(5,bpCOT);
                pstM.setString(6,horaR);
                pstM.setString(7,"DISPONIBLE");
                pstM.setString(8,"OPERATIVA");
                pstM.setString(9,"SIN OBSERVACIONES");
                pstM.executeUpdate();

                Toast.makeText(this,"Lista Actualizada",Toast.LENGTH_SHORT).show();


            }

        }catch (Exception e){

        }

        try{

            Statement st5 = conexionBD().createStatement();
            st5.executeQuery("UPDATE [dbo].[tabletsCot]" +
                    "SET [observaciones] ='CARGANDO' WHERE [estado]='"+elemento3+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try{

            Statement st5a = conexionBD().createStatement();
            st5a.executeQuery("UPDATE [dbo].[tabletsCot]" +
                    "SET [condicion] ='DISPONIBLE' WHERE [estado]='"+elemento3+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try{

            Statement st6 = conexionBD().createStatement();
            st6.executeQuery("UPDATE [dbo].[interfoniaCot]" +
                    "SET [observaciones] ='"+elemento1+"' WHERE [estado]='"+elemento2+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{

            Statement st6a = conexionBD().createStatement();
            st6a.executeQuery("UPDATE [dbo].[interfoniaCot]" +
                    "SET [condicion] ='DISPONIBLE' WHERE [estado]='"+elemento2+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try{

            Statement st7 = conexionBD().createStatement();
            st7.executeQuery("UPDATE [dbo].[cablesCot]" +
                    "SET [observaciones] ='"+elemento1+"' WHERE [estado]='"+elemento4+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{

            Statement st7a = conexionBD().createStatement();
            st7a.executeQuery("UPDATE [dbo].[cablesCot]" +
                    "SET [condicion] ='DISPONIBLE' WHERE [estado]='"+elemento4+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{

            Statement st8 = conexionBD().createStatement();
            st8.executeQuery("UPDATE [dbo].[senalerasCot]" +
                    "SET [observaciones] ='"+elemento1+"' WHERE [estado]='"+elemento5+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{

            Statement st8a = conexionBD().createStatement();
            st8a.executeQuery("UPDATE [dbo].[senalerasCot]" +
                    "SET [condicion] ='DISPONIBLE' WHERE [estado]='"+elemento5+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        try{

            Statement st9 = conexionBD().createStatement();
            st9.executeQuery("UPDATE [dbo].[senalerasCot]" +
                    "SET [observaciones] ='"+elemento1+"' WHERE [estado]='"+elemento6+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        try{

            Statement st9a = conexionBD().createStatement();
            st9a.executeQuery("UPDATE [dbo].[senalerasCot]" +
                    "SET [condicion] ='DISPONIBLE' WHERE [estado]='"+elemento6+"';");


        }catch(Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }





        Intent i = new Intent(this, menuPrincipal.class);
        i.putExtra("usuario", bo2.getText().toString());
        startActivity(i);
        finish();

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

        return conexion;
    }


}
