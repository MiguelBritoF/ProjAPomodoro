package com.example.projatecpomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {


    private ArrayList<String > itens;
    private ArrayAdapter<String> itensAdapter;
    private TextView txtVoltar2;
    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_acitivity);
        getSupportActionBar().hide();

        txtVoltar2 = findViewById(R.id.txtVoltar2);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        txtVoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToDoList.this, Pomodoro.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        itens = new ArrayList<>();
        itensAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itens);
        listView.setAdapter(itensAdapter);
        setUplistViewListener();
    }

    private void setUplistViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Item removido", Toast.LENGTH_LONG).show();

                itens.remove(i);
                itensAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText edtItens = findViewById(R.id.edtItens);
        String itemText = edtItens.getText().toString();

        if(!(itemText.equals(""))){
            itensAdapter.add(itemText);
            edtItens.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Por favor, adicione algo...", Toast.LENGTH_LONG).show();
        }
    }
}