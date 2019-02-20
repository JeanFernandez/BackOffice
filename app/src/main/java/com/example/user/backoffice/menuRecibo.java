package com.example.user.backoffice;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class menuRecibo extends AppCompatActivity {

    private static final String[] BPs = new String[]{
            "1","5482","34807","1384354","2924981","3631351","3892096","6395","9827","21103","80781","527350","603952","664965","677644","1167565","1209376","1316114","1393310","1414110","1476708","1493437","2186648","2196920","2273093","2348427","2362815","2478891","2548049","2595884","2631015","2670606","2824737","2835973","2868593","3011313","3050086","3079246","3095489","3133781","3173721","3176541","3230740","3248483","3365128","3420114","3522802","3598348","3631356","3668667","3669762","3708269","3742139","3747861","3762724","3764912","3765130","3773991","3774151","3777354","3777365","3777368","3777494","3778939","3778958","3840280","3841598","3844478","3849679","3852175","3852185","3852205","3854797","3856314","3856369","3887090","3891425","3891478","3892091","3892094","3943168","3943174","3964676","3972231","3973543","3974485","3974488","3976708","3976729","3976735","3977518","3994746","4003166","4003826","4006185","4008982","4014273","4028507","4028508","4028511","4056365","4220401","4238253","4238255","4238256","4238259","4238279","4238281","4242767","4246635","4253792","4258331","4258373","4270869","4270871","4270872","4270875","4270877","4292588","4292591","4292592","4292594","4292595","4292597"

    };



    public static EditText mochilaScaneada,interfoniaScaneada,tabletScaneada,senaleraAscaneada,senaleraBScaneada,cableScaneado,bp,mochila, inter,tablet,fecha,elementomochila,elementointerfonia,elementotablet,elementoCable,elementoSenaleraA,elementoSenaleraB;
    public static TextView bo;
    Button scannerMochila, scannerInterfonia,scannerTablet,scannerCable,scannerSenaA,scannerSenaB,confirmar,verBP;
    Date date = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Dialog popUp ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recibo);
        Bundle  extras = getIntent().getExtras();

        AutoCompleteTextView editText = findViewById(R.id.actvR);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,BPs);
        editText.setAdapter(adapter);



        String BackOffice = extras.getString("usuario");
        mochilaScaneada = (EditText) findViewById(R.id.mochilaScanner);
        interfoniaScaneada = (EditText) findViewById(R.id.interfoniaScanner);
        tabletScaneada = (EditText) findViewById(R.id.tabletScanner);
        senaleraAscaneada = (EditText) findViewById(R.id.senaleraAScanner);
        senaleraBScaneada = (EditText) findViewById(R.id.senaleraBScanner);
        cableScaneado = (EditText) findViewById(R.id.cableScanner);

        bp = (AutoCompleteTextView) findViewById(R.id.actvR);
        mochila = (EditText) findViewById(R.id.txtMenuEntregaMochila);
        bo = (TextView) findViewById(R.id.txtBORecibo);
        fecha = (EditText) findViewById(R.id.txtConfEntregaFecha);
        elementomochila = (EditText) findViewById(R.id.txtMochila);
        elementointerfonia = (EditText) findViewById(R.id.txtInterfonia);
        elementotablet = (EditText) findViewById(R.id.txtTablet);
        elementoCable = (EditText) findViewById(R.id.txtCableRecibo);
        elementoSenaleraA = (EditText) findViewById(R.id.txtSenaleraA);
        elementoSenaleraB = (EditText) findViewById(R.id.txtSenaleraB);
        verBP = (Button) findViewById(R.id.btnRecibirBP);
        scannerMochila = (Button) findViewById(R.id.btnScannerMochila);
        scannerInterfonia = (Button) findViewById(R.id.btnScannerInter);
        scannerTablet = (Button) findViewById(R.id.btnScannerTablet);
        scannerSenaA = (Button) findViewById(R.id.btnScannerSenA);
        scannerSenaB = (Button) findViewById(R.id.btnScannerSenB);
        scannerCable = (Button) findViewById(R.id.btnScannerCable);
        confirmar = (Button) findViewById(R.id.btnMenuReciboCofirmar);
        popUp = new Dialog(this);
        bo.setText(BackOffice);


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

    public void verificarElementos (View view){

        if(bp.getText().toString().equals("")){

            Toast.makeText(this,"Ingrese BP",Toast.LENGTH_LONG).show();
        }

        else {

            String cot = bp.getText().toString();
            String codm,codi,codt,codc,codSena,codSenB,boe,cote,fecE,horE,nombreCot,apellidoCot;

            try{
                Statement st = conexionBD().createStatement();
                ResultSet rs = st.executeQuery("SELECT [id_Mochila]\n" +
                        "      ,[id_interfonia]\n" +
                        "      ,[id_cable]\n" +
                        "      ,[id_tablet]\n" +
                        "      ,[id_senaleraA]\n" +
                        "      ,[id_senaleraB]\n" +
                        "      ,[id_cotActual]\n" +
                        "      ,[fechaEntrega]\n" +
                        "      ,[horaEntrega]\n" +
                        "      ,[nombre]\n" +
                        "      ,[apellido]\n" +
                        "  FROM [dbo].[mochilasArmadas2] join cots on mochilasArmadas2.id_cotActual = cots.bp\n" +
                        "  WHERE[id_cotActual] = '"+cot+"'");

                if(rs.next()){
                    codm = rs.getString("id_mochila");
                    codi = rs.getString("id_interfonia");
                    codc = rs.getString("id_cable");
                    codSena = rs.getString("id_senaleraA");
                    codSenB = rs.getString("id_senaleraB");
                    codt = rs.getString("id_tablet");
                    cote = rs.getString("id_cotActual");
                    fecE = rs.getString("fechaEntrega");
                    horE = rs.getString("horaEntrega");
                    nombreCot = rs.getString("nombre");
                    apellidoCot = rs.getString("apellido");

                    elementomochila.setText(codm);
                    mochilaScaneada.setText(codm);
                    elementointerfonia.setText(codi);
                    interfoniaScaneada.setText(codi);
                    elementotablet.setText(codt);
                    tabletScaneada.setText(codt);
                    elementoCable.setText(codc);
                    cableScaneado.setText(codc);
                    elementoSenaleraA.setText(codSena);
                    senaleraAscaneada.setText(codSena);
                    elementoSenaleraB.setText(codSenB);
                    senaleraBScaneada.setText(codSenB);
                    fecha.setText(" SE ENTREGO A "+nombreCot+" "+apellidoCot);

                }else{

                    Toast.makeText(this,"no hay registro",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void scannerMochilaRec (View view1) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","MochilaRec");
        startActivity(i1);
    }
    public void scannerInterRec (View view2) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","InterfoniaRec");
        startActivity(i1);
    }
    public void scanner1TabletRec (View view3) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","TabletRec");
        startActivity(i1);
    }

    public void scannerSenaARec (View view4) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","SenaleraARec");
        startActivity(i1);
    }
    public void scannerSenaBRec (View view5) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","SenaleraBRec");
        startActivity(i1);
    }
    public void scannerCableRec (View view6) {
        Intent i1 = new Intent(this, scanner.class);
        i1.putExtra("elemento1","CableRec");
        startActivity(i1);
    }


    public void volverMP(View vi){

        String funcionario4 = bo.getText().toString();
        Intent i = new Intent(this,menuPrincipal.class);
        i.putExtra("usuario",funcionario4);
        startActivity(i);
        finish();
    }


    public void irAconfirmarRecibo (View view){


        Intent i = new Intent(this,popUpRecibo.class);

        String fecha = dateFormat.format(date);
        String hora = hourFormat.format(date);
        String mochilabd =elementomochila.getText().toString();
        String tabletbd = elementotablet.getText().toString();
        String interfoniabd = elementointerfonia.getText().toString();
        String senaleraAbd = elementoSenaleraA.getText().toString();
        String senaleraBbd = elementoSenaleraB.getText().toString();
        String cablebd = elementoCable.getText().toString();

        String mochilasc =mochilaScaneada.getText().toString();
        String tabletsc = tabletScaneada.getText().toString();
        String interfoniasc = interfoniaScaneada.getText().toString();
        String senaleraAsc = senaleraAscaneada.getText().toString();
        String senaleraBsc = senaleraBScaneada.getText().toString();
        String cablesc = cableScaneado.getText().toString();

        String bp2 = bp.getText().toString();
        String funcionario2 = bo.getText().toString();

        i.putExtra("MochilaBD",mochilabd);
        i.putExtra("InterfoniaBD",interfoniabd);
        i.putExtra("TabletBD",tabletbd);
        i.putExtra("CableBD",cablebd);
        i.putExtra("SenaleraABD",senaleraAbd);
        i.putExtra("SenaleraBBD",senaleraBbd);

        i.putExtra("MochilaSC",mochilasc);
        i.putExtra("InterfoniaSC",interfoniasc);
        i.putExtra("TabletSC",tabletsc);
        i.putExtra("CableSC",cablesc);
        i.putExtra("SenaleraASC",senaleraAsc);
        i.putExtra("SenaleraBSC",senaleraBsc);

        i.putExtra("BP",bp2);
        i.putExtra("Fecha",fecha);
        i.putExtra("Hora",hora);
        i.putExtra("usuario",funcionario2);

        startActivity(i);
        finish();
    }




}
