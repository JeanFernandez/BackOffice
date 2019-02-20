package com.example.user.backoffice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TabHost;
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

public class menuEntrega extends AppCompatActivity {

    private static final String[] BPs = new String[]{
            "1","5482","34807","1384354","2924981","3631351","3892096","6395","9827","21103","80781","527350","603952","664965","677644","1167565","1209376","1316114","1393310","1414110","1476708","1493437","2186648","2196920","2273093","2348427","2362815","2478891","2548049","2595884","2631015","2670606","2824737","2835973","2868593","3011313","3050086","3079246","3095489","3133781","3173721","3176541","3230740","3248483","3365128","3420114","3522802","3598348","3631356","3668667","3669762","3708269","3742139","3747861","3762724","3764912","3765130","3773991","3774151","3777354","3777365","3777368","3777494","3778939","3778958","3840280","3841598","3844478","3849679","3852175","3852185","3852205","3854797","3856314","3856369","3887090","3891425","3891478","3892091","3892094","3943168","3943174","3964676","3972231","3973543","3974485","3974488","3976708","3976729","3976735","3977518","3994746","4003166","4003826","4006185","4008982","4014273","4028507","4028508","4028511","4056365","4220401","4238253","4238255","4238256","4238259","4238279","4238281","4242767","4246635","4253792","4258331","4258373","4270869","4270871","4270872","4270875","4270877","4292588","4292591","4292592","4292594","4292595","4292597"

    };


    public static EditText mochila,bp;

    public static TextView cable, interfonia,tablet,senaleraA, senaleraB,funcionario;
    Button confirmar;
    Date date = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ProgressBar prgE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_entrega);

        funcionario = (TextView) findViewById(R.id.txtMenuEntregaUsuario);
        Bundle  extras = getIntent().getExtras();
        String usuario= extras.getString("usuario");
        funcionario.setText(usuario);

       AutoCompleteTextView editText = findViewById(R.id.actv);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BPs);
       editText.setAdapter(adapter);

       bp = (AutoCompleteTextView) findViewById(R.id.actv);
       mochila = (EditText) findViewById(R.id.txtMenuEntregaMochila);
        mochila.setEnabled(false);
        interfonia = (TextView) findViewById(R.id.txtEntregaInter);
        cable = (TextView)findViewById(R.id.txtEntregaCable);
        tablet = (TextView) findViewById(R.id.txtEntregaTablet);
        senaleraA = (TextView) findViewById(R.id.txtEntregaSenA);
        senaleraB = (TextView) findViewById(R.id.txtEntregaSenB);
        confirmar = (Button) findViewById(R.id.btnMenuEntregaCofirmar);
        prgE = (ProgressBar) findViewById(R.id.progressBarEntrega);

        confirmar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(!bp.getText().toString().equals("") &&
                        !mochila.getText().toString().equals("")&&
                                !tablet.getText().toString().equals("POR ASIGNAR")){

                    Intent i = new Intent(getApplicationContext(), confirmarEntrega.class);

                    String fecha1 = dateFormat.format(date);
                    String hora1 = hourFormat.format(date);
                    String tablet1 = tablet.getText().toString();
                    String interfonia1 = interfonia.getText().toString();
                    String cable1 = cable.getText().toString();
                    String mochila1 = mochila.getText().toString();
                    String senaleraA1 = senaleraA.getText().toString();
                    String senaleraB1 = senaleraB.getText().toString();
                    String bp1 = bp.getText().toString();
                    String funcionario1 = funcionario.getText().toString();

                    i.putExtra("Tablet",tablet1);
                    i.putExtra("Interfonia",interfonia1);
                    i.putExtra("Mochila",mochila1);
                    i.putExtra("SenaleraA",senaleraA1);
                    i.putExtra("SenaleraB",senaleraB1);
                    i.putExtra("BP",bp1);
                    i.putExtra("Fecha",fecha1);
                    i.putExtra("Hora",hora1);
                    i.putExtra("usuario",funcionario1);
                    i.putExtra("Cable",cable1);


                    conexionBD();

                    String bpCOT = bp1;
                    String elemento1 = mochila1;
                    String elemento2 = interfonia1;
                    String elemento3 = tablet1;
                    String elemento4 = cable1;
                    String elemento5 = senaleraA1;
                    String elemento6 = senaleraB1;

                    try{
                        for (int i1=1; i1<7;i1++){
                            String elemento = "";
                            if(i1==1){
                                elemento = elemento1;
                            }if(i1==2){
                                elemento = elemento2;
                            }if(i1==3){
                                elemento = elemento3;
                            }if(i1==4){
                                elemento = elemento4;
                            }if(i1==5){
                                elemento = elemento5;
                            }if(i1==6){
                                elemento = elemento6;
                            }
                            PreparedStatement pstM = conexionBD().prepareStatement("insert into movimiento values (?,?,?,?,?,?,?,?,?)");
                            pstM.setString(1,elemento);
                            pstM.setString(2,fecha1);
                            pstM.setString(3,"ENTREGA");
                            pstM.setString(4,elemento1);
                            pstM.setString(5,bpCOT);
                            pstM.setString(6,hora1);
                            pstM.setString(7,"EN USO");
                            pstM.setString(8,"OPERATIVA");
                            pstM.setString(9,"SIN OBSERVACIONES");
                            pstM.executeUpdate();
                        }


                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    try{

                        Statement st4 = conexionBD().createStatement();
                        st4.executeQuery("UPDATE [dbo].[mochilasArmadas2]" +
                                "SET [id_cotActual] ='"+bp1+"', [id_interfonia] ='"+elemento2+"',[id_tablet]='"+elemento3+"', [id_senaleraA]='"+elemento5+"',[id_senaleraB]='"+elemento6+"',[id_cable]='"+elemento4+"',[horaEntrega] ='"+hora1+"',[fechaEntrega] ='"+fecha1+"', [estado] ='EN USO' WHERE [id_mochila]='"+elemento1+"';");


                    }catch(Exception e){

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    try{

                        Statement st5 = conexionBD().createStatement();
                        st5.executeQuery("UPDATE [dbo].[tabletsCot]" +
                                "SET [observaciones] ='"+elemento1+"' WHERE [estado]='"+elemento3+"';");


                    }catch(Exception e){

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    try{

                        Statement st5a = conexionBD().createStatement();
                        st5a.executeQuery("UPDATE [dbo].[tabletsCot]" +
                                "SET [condicion] ='EN USO' WHERE [estado]='"+elemento3+"';");


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
                                "SET [condicion] ='EN USO' WHERE [estado]='"+elemento2+"';");


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
                                "SET [condicion] ='EN USO' WHERE [estado]='"+elemento4+"';");


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
                                "SET [condicion] ='EN USO' WHERE [estado]='"+elemento5+"';");


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
                                "SET [condicion] ='EN USO' WHERE [estado]='"+elemento6+"';");


                    }catch(Exception e){

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }



                    startActivity(i);
                    finish();

                }else {

                    Toast.makeText(getApplicationContext() ,"FALTAN ELEMENTOS",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void scannerMochila (View view1) {



        if(!bp.getText().toString().equals("")) {

            Intent i1 = new Intent(menuEntrega.this, scanner.class);
            i1.putExtra("elemento1", "MochilaEntrega");
            startActivity(i1);
        }else{

            Toast.makeText(this,"Ingrese BP",Toast.LENGTH_SHORT).show();
        }


    }
    public void scannerInterfonia (View view2) {

            Intent i1 = new Intent(menuEntrega.this, scanner.class);
            i1.putExtra("elemento1", "InterfoniaCambio");
            startActivity(i1);
    }
    public void scannerCable (View view3) {

        Intent i1 = new Intent(menuEntrega.this, scanner.class);
        i1.putExtra("elemento1", "CableCambio");
        startActivity(i1);
    }
    public void scannerTablet (View view4) {

        Intent i1 = new Intent(menuEntrega.this, scanner.class);
        i1.putExtra("elemento1", "TabletCambio");
        startActivity(i1);
    }
    public void scannerSenaleraA (View view5) {

        Intent i1 = new Intent(menuEntrega.this, scanner.class);
        i1.putExtra("elemento1", "SenaleraACambio");
        startActivity(i1);
    }
    public void scannerSenaleraB (View view6) {

        Intent i1 = new Intent(menuEntrega.this, scanner.class);
        i1.putExtra("elemento1", "SenaleraBCambio");
        startActivity(i1);
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



    public void volverMPEntrega(View view){

        String funcionario2 = funcionario.getText().toString();
        Intent i = new Intent(this,menuPrincipal.class);
        i.putExtra("usuario",funcionario2);
        startActivity(i);
        finish();

    }
}
