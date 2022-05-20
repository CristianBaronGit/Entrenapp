package com.example.entrenapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrenapp.R;
import com.example.entrenapp.entidades.Dietas;
import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

public class ListaDietasAdapter extends RecyclerView.Adapter<ListaDietasAdapter.DietaViewHolder> {

    ArrayList<Dietas> listaDietas;

    public ListaDietasAdapter(ArrayList<Dietas> listaDietas){
        this.listaDietas = listaDietas;
    }

    @NonNull
    @Override
    public DietaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_dieta,null,false);
        return new ListaDietasAdapter.DietaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDietasAdapter.DietaViewHolder holder, int position) {
        holder.viewIdlistaitemdieta.setText(listaDietas.get(position).getId_dieta());
        holder.viewFechalistaitemdieta.setText(listaDietas.get(position).getFecha_registro_dieta());
    }

    @Override
    public int getItemCount() {
        return listaDietas.size();
    }

    public class DietaViewHolder extends RecyclerView.ViewHolder {

        TextView viewIdlistaitemdieta, viewFechalistaitemdieta;

        public DietaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewIdlistaitemdieta = itemView.findViewById(R.id.viewIdlistaitemdieta);
            viewFechalistaitemdieta = itemView.findViewById(R.id.viewFechalistaitemdieta);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaRutinas.get(getAdapterPosition()).getId_registro());
                    context.startActivity(intent);
                }
            });*/
        }
    }
}


