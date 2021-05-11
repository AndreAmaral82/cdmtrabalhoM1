package com.claudio.apptreino;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterTreino extends BaseAdapter {

    private List<Treino> treinoList;
    private Context context;
    private LayoutInflater inflater;


    public AdapterTreino(Context context, List<Treino> listaTreino){
        this.treinoList = listaTreino;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return treinoList.size();
    }

    @Override
    public Object getItem(int i) {
        return treinoList.get(i).id;
    }

    @Override
    public long getItemId(int i) {
        return treinoList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ItemSuporte item;
        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);
            item = new ItemSuporte();
            item.tvDia = convertView.findViewById(R.id.tvListaDia);
            item.tvExercicio = convertView.findViewById(R.id.tvListaExercicio);
            item.tvRepeticao = convertView.findViewById(R.id.tvListaRepeticao);
            item.tvSeries = convertView.findViewById(R.id.tvListaSeries);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );

        }else{
            item = (ItemSuporte) convertView.getTag();
        }
        Treino treino = treinoList.get(i);
        item.tvDia.setText( String.valueOf( treino.dia));
        item.tvExercicio.setText(treino.exercicio);
        item.tvRepeticao.setText(treino.repeticao);
        item.tvSeries.setText( treino.series );

        if(i % 2 == 0){
            item.layout.setBackgroundColor(Color.rgb(230,230,230));
        }else{
            item.layout.setBackgroundColor( Color.WHITE );
        }


        return convertView;
    }
    private class ItemSuporte{
        TextView tvDia, tvExercicio, tvRepeticao, tvSeries;
        LinearLayout layout;
    }
}
