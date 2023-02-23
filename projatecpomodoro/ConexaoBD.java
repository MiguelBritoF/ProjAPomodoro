package com.example.projatecpomodoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ConexaoBD extends SQLiteOpenHelper {
    private static final String NAME = "bdPomodoro";
    private static final int VERSION = 1;


    public ConexaoBD(Context contexto) {
        super(contexto, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bdPomodoro) {
        bdPomodoro.execSQL("create table tb_usuario(" +
                "id integer primary key autoincrement," +
                "nome varchar(100)," +
                "usuario varchar(50)," +
                "senha vachar(16))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bdPomodoro, int i, int i1) {

    }
}
