package com.example.projatecpomodoro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    private ListView listaUsers;
    private UsuarioDAO objUsuarioDAO;
    private MenuItem icCadastrar, moAlterar, moExcluir, icSair;
    private SearchView icPesquisar;
    private List<Usuario> todosUsuarios;
    private List<Usuario> alunosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listaUsers = findViewById(R.id.listaUsers);
        objUsuarioDAO = new UsuarioDAO(this);

        todosUsuarios = objUsuarioDAO.listarTodosOsUsuarios();
        alunosFiltrados.addAll(todosUsuarios);

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,alunosFiltrados);
        listaUsers.setAdapter(adaptador);

        registerForContextMenu(listaUsers);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_superior,menu);

        icCadastrar = menu.findItem(R.id.icCadastrar);
        icPesquisar = (SearchView) menu.findItem(R.id.icPesquisar).getActionView();


        //icSair = menu.findItem(R.id.icSair);

        //icSair.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            //@Override
           // public boolean onMenuItemClick(MenuItem menuItem) {
               // startActivity(new Intent(ListarActivity.this,MainActivity.class));
               // finish();
                //return false;
            //}
       // });


        icCadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(ListarActivity.this,CadastroActivity.class));
                finish();
                return false;
            }
        });
        icPesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                pesquisarAlunoPorNome(s);

                return false;
            }
        });
        return true;
    }

    public void pesquisarAlunoPorNome(String nome){
        alunosFiltrados.clear();
        for(int i = 0; i < todosUsuarios.size(); i++){
            if(todosUsuarios.get(i).getNome().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(todosUsuarios.get(i));
            }
        }
        listaUsers.invalidateViews();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_ud,menu);

        moAlterar = menu.findItem(R.id.moAlterar);
        moExcluir = menu.findItem(R.id.moExcluir);

        moExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo posicaoAluno = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Usuario objUsuarioAExcluir = alunosFiltrados.get(posicaoAluno.position);


                AlertDialog confirmacao = new AlertDialog.Builder(ListarActivity.this)
                        .setTitle("Atenção!")
                        .setMessage("Deseja excluir o(a) aluno(a) "+ objUsuarioAExcluir.getNome())
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                todosUsuarios.remove(objUsuarioAExcluir);
                                alunosFiltrados.remove(objUsuarioAExcluir);
                                objUsuarioDAO.excluirUsuario(objUsuarioAExcluir);

                                listaUsers.invalidateViews();
                            }
                        }).create();
                confirmacao.show();

                return false;
            }
        });

        moAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo posicaoDoAluno = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

                Usuario objUsuarioAAlterar = alunosFiltrados.get(posicaoDoAluno.position);

                Intent i = new Intent(ListarActivity.this, CadastroActivity.class);
                i.putExtra("aluno", objUsuarioAAlterar);
                startActivity(i);
                return false;
            }
        });

    }
}