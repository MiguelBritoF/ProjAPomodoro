package com.example.projatecpomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome,edtSenha;
    private Button btnEntrar;
    private TextView txtCadastrar;
    private UsuarioDAO objUsuarioDAO;
    private List<Usuario> todosUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        objUsuarioDAO = new UsuarioDAO(MainActivity.this);

        edtNome = findViewById(R.id.edtUsuarioE);
        edtSenha = findViewById(R.id.edtSenha);
        txtCadastrar = findViewById(R.id.txtCadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);

        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CadastroActivity.class);
                startActivity(i);


            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todosUsuarios = objUsuarioDAO.listarTodosOsUsuarios();

                boolean naoEncontrou = true;

                for(int i = 0; i < todosUsuarios.size() && naoEncontrou; i++){
                    if (todosUsuarios.get(i).getUsuario().equals(edtNome.getText().toString()) && todosUsuarios.get(i).getSenha().equals(edtSenha.getText().toString())){
                        startActivity(new Intent(MainActivity.this,Pomodoro.class));
                        naoEncontrou = false;
                    }
                }
                if (naoEncontrou){
                    Toast.makeText(MainActivity.this,"Usuário ou senha incorreto!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}