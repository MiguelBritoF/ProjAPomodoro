package com.example.projatecpomodoro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private ConexaoBD conexaoBD;
    private SQLiteDatabase bdPomodoro;


    public UsuarioDAO(Context contexto) {
        this.conexaoBD = new ConexaoBD(contexto);
        this.bdPomodoro = this.conexaoBD.getWritableDatabase();
    }
    public void cadastrarUsuario(Usuario objUsuario){
       ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("nome", objUsuario.getNome());
        valoresCampos.put("usuario", objUsuario.getUsuario());
        valoresCampos.put("senha", objUsuario.getSenha());

        this.bdPomodoro.insert("tb_usuario",null,valoresCampos);


    }

    public List<Usuario> listarTodosOsUsuarios(){

        List<Usuario> todosOsUsuarios = new ArrayList<>();

        String [] campos = {"id","nome","usuario","senha"};

        Cursor cursor = bdPomodoro.query("tb_usuario",campos,null,null,null,null, null);

        while (cursor.moveToNext()){
            Usuario objUsuario = new Usuario();

            objUsuario.setId(cursor.getInt(0));
            objUsuario.setNome(cursor.getString(1));
            objUsuario.setUsuario(cursor.getString(2));
            objUsuario.setSenha(cursor.getString(3));

            todosOsUsuarios.add(objUsuario);
        }
        return todosOsUsuarios;
    }

    public void excluirUsuario(Usuario objUsuario){
        String [] id = {String.valueOf(objUsuario.getId())};
        this.bdPomodoro.delete("tb_usuario","id = ?",id);
    }
    public void alterarUsuario(Usuario objUsuario){
        ContentValues valoresCampos = new ContentValues();

        valoresCampos.put("nome", objUsuario.getNome());
        valoresCampos.put("usuario", objUsuario.getUsuario());
        valoresCampos.put("senha", objUsuario.getSenha());

        String [] id = {String.valueOf(objUsuario.getId())};
        this.bdPomodoro.update("tb_usuario",valoresCampos,"id = ?",id);
    }


}
