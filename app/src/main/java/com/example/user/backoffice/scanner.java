package com.example.user.backoffice;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView scannerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        Toast.makeText(this, "Diriga la camara a Codigo  QR", Toast.LENGTH_LONG).show();


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


    @Override

    public void handleResult(Result result) {

        Bundle  extras = getIntent().getExtras();
        String d1= extras.getString("elemento1");

        if(d1.equals("MochilaEntrega")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOMOC")){

                Toast.makeText(this,"Ingrese un codigo de mochila",Toast.LENGTH_SHORT).show();

            }else{

            String moc = result.getText().toString();
                String codi,codt,codc,sena,senb;

                try{
                    Statement st = conexionBD().createStatement();
                    ResultSet rs = st.executeQuery("SELECT [id_mochila]\n" +
                            "      ,[id_cotActual]\n" +
                            "      ,[id_interfonia]\n" +
                            "      ,[id_cable]\n" +
                            "      ,[id_tablet]\n" +
                            "      ,[id_senaleraA]\n" +
                            "      ,[id_senaleraB]\n" +
                            "      ,[fechaEntrega]\n" +
                            "      ,[horaEntrega]\n" +
                            "  FROM [dbo].[mochilasArmadas2] \n" +
                            "  WHERE[id_mochila] = '"+moc+"'");

                    if(rs.next()){
                        codi = rs.getString("id_interfonia");
                        menuEntrega.interfonia.setText(codi);
                        codt = rs.getString("id_tablet");
                        menuEntrega.tablet.setText(codt);
                        sena = rs.getString("id_senaleraA");
                        menuEntrega.senaleraA.setText(sena);
                        senb = rs.getString("id_senaleraB");
                        menuEntrega.senaleraB.setText(senb);
                        codc = rs.getString("id_cable");
                        menuEntrega.cable.setText(codc);

                    }else{

                        Toast.makeText(this,"Mochila no existe",Toast.LENGTH_SHORT).show(); //aca ver que mochila no este asignada, o que este en estados distintos al de operativa
                    }




                }catch (Exception e){
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }


            menuEntrega.mochila.setText(result.getText());

            onBackPressed();}
        }

        if(d1.equals("InterfoniaCambio")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOINT")){

                Toast.makeText(this,"Ingrese un codigo de interfonia",Toast.LENGTH_SHORT).show();
            }else{
                menuEntrega.interfonia.setText(result.getText());
                onBackPressed();}
        }

        if(d1.equals("CableCambio")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOEXT")){

                Toast.makeText(this,"Ingrese un codigo de Extensiones",Toast.LENGTH_SHORT).show();
            }else{
                menuEntrega.cable.setText(result.getText());
                onBackPressed();}
        }

        if(d1.equals("TabletCambio")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOTAB")){

                Toast.makeText(this,"Ingrese un codigo de Tablet",Toast.LENGTH_SHORT).show();
            }else{
                menuEntrega.tablet.setText(result.getText());
                onBackPressed();}
        }

        if(d1.equals("SenaleraACambio")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{
                menuEntrega.senaleraA.setText(result.getText());
                onBackPressed();}
        }

        if(d1.equals("SenaleraBCambio")){

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{
                menuEntrega.senaleraB.setText(result.getText());
                onBackPressed();}
        }

        if(d1.equals("MochilaRec")){
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOMOC")){

                Toast.makeText(this,"Ingrese un codigo de mochila",Toast.LENGTH_SHORT).show();
            }else{
                menuRecibo.mochilaScaneada.setText(result.getText());
                onBackPressed();
            }

        }


        if(d1.equals("InterfoniaRec")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOINT")){

                Toast.makeText(this,"Ingrese un codigo de interfonia",Toast.LENGTH_SHORT).show();
            }else{

                menuRecibo.interfoniaScaneada.setText(result.getText());
                onBackPressed();
            }


        }


        if(d1.equals("TabletRec")) {

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOTAB")){

                Toast.makeText(this,"Ingrese un codigo de Tablet",Toast.LENGTH_SHORT).show();
            }else {

                menuRecibo.tabletScaneada.setText(result.getText());
                onBackPressed();
            }
        }
        if(d1.equals("SenaleraARec")) {

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{

                menuRecibo.senaleraAscaneada.setText(result.getText());
            onBackPressed();

            }
        }
        if(d1.equals("SenaleraBRec")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{


                menuRecibo.senaleraBScaneada.setText(result.getText());
            onBackPressed();}
        }
        if(d1.equals("CableRec")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOEXT")){

                Toast.makeText(this,"Ingrese un codigo de Extension",Toast.LENGTH_SHORT).show();
            }else{

                menuRecibo.cableScaneado.setText(result.getText());
            onBackPressed();}
        }


        if(d1.equals("MochilaCreacion")) {

            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOMOC")){

                Toast.makeText(this,"Ingrese un codigo de mochila",Toast.LENGTH_SHORT).show();
            }else{
            armadoMochilas.mochilaC.setText(result.getText());
            onBackPressed();}
        }
        if(d1.equals("InterfoniaCreacion")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOINT")){

                Toast.makeText(this,"Ingrese un codigo de interfonia",Toast.LENGTH_SHORT).show();
            }else{

                armadoMochilas.interfoniaC.setText(result.getText());
            onBackPressed();}
        }
        if(d1.equals("CableCreacion")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOEXT")){

                Toast.makeText(this,"Ingrese un codigo de Extension",Toast.LENGTH_SHORT).show();
            }else{

                armadoMochilas.cableC.setText(result.getText());
            onBackPressed();}
        }

        if(d1.equals("TabletCreacion")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOTAB")){

                Toast.makeText(this,"Ingrese un codigo de Tablet",Toast.LENGTH_SHORT).show();
            }else {

                armadoMochilas.tabletC.setText(result.getText());
            onBackPressed();}
        }
        if(d1.equals("SenaleraACreacion")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{

                armadoMochilas.senaA.setText(result.getText());
            onBackPressed();}
        }
        if(d1.equals("SenaleraBCreacion")) {
            String tipoElemento = result.getText().toString().substring(0,5);
            if(!tipoElemento.equals("BOSEN")){

                Toast.makeText(this,"Ingrese un codigo de Señalera",Toast.LENGTH_SHORT).show();
            }else{

                armadoMochilas.senaB.setText(result.getText());
            onBackPressed();}
        }








    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }


}
