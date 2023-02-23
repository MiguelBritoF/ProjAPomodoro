package com.example.projatecpomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroActivity extends AppCompatActivity {
    private TextView txtListarUsuarios;
    private EditText edtNome,edtUsuario, edtSenha;
    private Button btnCadastrarU, btnVoltar;
    private UsuarioDAO objUsuarioDAO;
    private Usuario objUsuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();


        objUsuarioDAO = new UsuarioDAO(getApplicationContext());


        edtNome = findViewById(R.id.edtNome);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        btnCadastrarU = findViewById(R.id.btnCadastrarU);
        txtListarUsuarios = findViewById(R.id.txtListarUsuarios);

        Intent i = getIntent();
        if (i.hasExtra("usuario")){
            objUsuario = (Usuario) i.getSerializableExtra("usuario");
            edtNome.setText(objUsuario.getNome());
            edtUsuario.setText(objUsuario.getUsuario());
            edtSenha.setText(objUsuario.getSenha());
            btnCadastrarU.setText("Alterar");

        }else if(i.hasExtra("novo_usuario")){
            txtListarUsuarios.setVisibility(View.INVISIBLE);
        }



        btnCadastrarU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objUsuario == null){
                    objUsuario = new Usuario();
                    objUsuario.setNome(edtNome.getText().toString());
                    objUsuario.setUsuario(edtUsuario.getText().toString());
                    objUsuario.setSenha(edtSenha.getText().toString());
                    objUsuarioDAO.cadastrarUsuario(objUsuario);
                    limparCampos();
                }else{
                    objUsuario.setNome(edtNome.getText().toString());
                    objUsuario.setUsuario(edtUsuario.getText().toString());
                    objUsuario.setSenha(edtSenha.getText().toString());

                    objUsuarioDAO.alterarUsuario(objUsuario);
                    limparCampos();
                    startActivity(new Intent(CadastroActivity.this,ListarActivity.class));
                    finish();
                }
            }
        });

        txtListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ListarActivity.class));
            }
        });


        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               Intent i = new Intent(CadastroActivity.this, MainActivity.class);
               startActivity(i);
               finish();
           }
        });
    }

    public void limparCampos(){
        edtNome.setText("");
        edtUsuario.setText("");
        edtSenha.setText("");
        edtNome.requestFocus();
    }

}

//Sor quero que veja nosso código e diga aonde que erramos para o CRUD não estar funcionando
// (Não sei se vai ser só no nosso Pc), ficamos pelo menos algumas horas testando e testando código
//e nada de funcionar.