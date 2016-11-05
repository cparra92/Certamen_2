package com.example.cristian.certamen2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.cristian.certamen2.Views.ItemClickListener;
import com.example.cristian.certamen2.Models.Lista;
import com.example.cristian.certamen2.R;

import java.util.ArrayList;

/**
 * Created by cristian on 16-10-2016.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {
    private Context mcontext;
    private View.OnClickListener listener;
    private ArrayList<Lista> datos;
    private ItemClickListener clickListener;


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;

        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.Titulo);
            mTextView2 = (TextView) v.findViewById(R.id.Descripcion);
            mTextView3 = (TextView) v.findViewById(R.id.Actualizacion);
        }


        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
    public ListaAdapter(Context context, ArrayList<Lista> adatos) {
        datos = adatos;
        mcontext = context;
    }

    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListaAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(datos.get(position).getTitulo());
        holder.mTextView2.setText(datos.get(position).getDescripcion());
        holder.mTextView3.setText(datos.get(position).getActualizacion());

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}