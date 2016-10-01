package com.example.cristian.certamen2;

import com.example.cristian.certamen2.lista;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cristian on 30-09-2016.
 */

public class listaadapter extends RecyclerView.Adapter<listaadapter.ViewHolder> {

    private List<lista> Datos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mGeneroView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.titulo);
            mGeneroView = (TextView) v.findViewById(R.id.descripcion);
        }
    }

    public listaadapter(List<lista> myDataset) {
        Datos = myDataset;
    }


    @Override
    public listaadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mostrar, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(listaadapter.ViewHolder holder, int position) {
        lista libro = Datos.get(position);

        holder.mTextView.setText(libro.getTitulo());
        holder.mGeneroView.setText(libro.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
