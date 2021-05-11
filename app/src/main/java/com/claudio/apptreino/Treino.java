package com.claudio.apptreino;

public class Treino {
    public int id;
    public String exercicio, repeticao, series, dia;
    //public int  repeticao, series;

    public Treino(){

    }

    public Treino(String exercicio, String repeticao, String series, String dia) {
        this.exercicio = exercicio;
        this.repeticao = repeticao;
        this.series = series;
        this.dia = dia;
    }

    public Treino(int id, String exercicio, String repeticao, String series, String dia) {
        this.id = id;
        this.exercicio = exercicio;
        this.repeticao = repeticao;
        this.series = series;
        this.dia = dia;
    }

    @Override
    public String toString() {
        return  " Dia= " + dia + '\'' +
                ", Exercício='" + exercicio + '\'' +
                ", Repetições=" + repeticao + '\'' +
                ", Séries=" + series;
                }
}
