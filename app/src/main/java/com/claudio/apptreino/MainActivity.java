package com.claudio.apptreino;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTreino;
    private AdapterTreino adapter;
    private List<Treino> listaTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                       .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, Formulario.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        lvTreino = findViewById(R.id.lvTreino);
        carregarTreino();
        confListView();
    }

    private void confListView(){
        lvTreino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Treino treinoSelecionado = listaTreino.get(position);
                Intent intent = new Intent(MainActivity.this, Formulario.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idTreino", treinoSelecionado.id);
                startActivity( intent );
            }
        });

        lvTreino.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                Treino treinoSelecionado = listaTreino.get(position);
                excluirTreino( treinoSelecionado );
                return true;
            }
        });
    }

    private void excluirTreino(Treino treino){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage(R.string.textConfirma);
        alerta.setNeutralButton(R.string.textCancelar, null);
        alerta.setPositiveButton(R.string.textSim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TreinoDAO.excluir( treino.id, MainActivity.this);
                carregarTreino();
            }
        });
        alerta.show();
    }




    @Override
    protected void onRestart() {
        super.onRestart();
        carregarTreino();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarTreino(){
        listaTreino = TreinoDAO.getTreino(this);
        adapter = new AdapterTreino(this, listaTreino);
        lvTreino.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}