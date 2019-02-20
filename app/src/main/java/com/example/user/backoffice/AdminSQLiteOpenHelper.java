package com.example.user.backoffice;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        BaseDeDatos.execSQL("create table entregasyrecibos (bpCot text primary key , nombreBO text, fecha text, hora text, mochila text, interfonia text, tablet text,fecharec text,horarec text,nombreBOrec text,estadorecibo text) ");
        BaseDeDatos.execSQL("create table mochilas (codigo text primary key, condiciongeneral text,ultimoUsuario text, estado text,fechaEntrega text, horaEntrega text, fechaRecibo text, horaRecibo text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
