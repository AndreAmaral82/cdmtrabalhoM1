package com.claudio.apptreino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {
    private EditText etExercicio,etRepeticao,etSerie;
    private Spinner spDia;
    private Button btnSalvar;
    private String acao;
    private Treino treino;

    public Formulario() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etExercicio = findViewById( R.id.etExercicio );
        etRepeticao = findViewById( R.id.etRepeticao );
        etSerie = findViewById( R.id.etSerie );
        spDia = findViewById( R.id.spDia );
        btnSalvar = findViewById( R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
        int idTreino = getIntent().getIntExtra("idTreino", 0);
        if( idTreino != 0) {
            treino = TreinoDAO.getTreinoById(this, idTreino);

            etExercicio.setText( treino.exercicio );
            etRepeticao.setText( treino.repeticao );
            etSerie.setText( treino.series );


            String[] arraySemana = getResources().getStringArray(R.array.arraySemana);
            for(int i = 1; i < arraySemana.length ; i++){
                if( ( arraySemana[i] ) == treino.dia){
                    spDia.setSelection( i );
                }
            }
        }
    }
    private void salvar() {
        if (spDia.getSelectedItemPosition() == 0 || etExercicio.getText().toString().isEmpty() ||
                etRepeticao.getText().toString().isEmpty() || etSerie.getText().toString().isEmpty()) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        } else {

            if (acao.equals("novo")) {
                treino = new Treino();
            }

            treino.exercicio = etExercicio.getText().toString();
            treino.repeticao = etRepeticao.getText().toString();
            treino.series = etSerie.getText().toString();
            treino.dia = (spDia.getSelectedItem().toString());

            if (acao.equals("editar")) {
                TreinoDAO.editar(treino, this);
                finish();
            } else {
                TreinoDAO.inserir(treino, this);
                etExercicio.setText("");
                etRepeticao.setText("");
                etSerie.setText("");
                spDia.setSelection(0);
            }
        }
    }}