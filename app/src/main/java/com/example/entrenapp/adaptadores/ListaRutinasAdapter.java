package com.example.entrenapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrenapp.R;
import com.example.entrenapp.entidades.Rutinas;

import java.util.ArrayList;

public class ListaRutinasAdapter extends RecyclerView.Adapter<ListaRutinasAdapter.RutinaViewHolder> {

    ArrayList<Rutinas> listaRutinas;

    public ListaRutinasAdapter(ArrayList<Rutinas> listaRutinas){
        this.listaRutinas = listaRutinas;
    }

    @NonNull
    @Override
    public RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_rutina,null,false);
        return new RutinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaViewHolder holder, int position) {
        //holder.viewIdlistaitemrutina.setText(listaRutinas.get(position).getId_registro());
        holder.viewFechalistaitemrutina.setText(listaRutinas.get(position).getFecha_entreno());
        holder.viewHoralistaitemrutina.setText(listaRutinas.get(position).getHora_entreno());
    }

    @Override
    public int getItemCount() {
       return listaRutinas.size();
    }

    public class RutinaViewHolder extends RecyclerView.ViewHolder {

        TextView viewIdlistaitemrutina, viewFechalistaitemrutina, viewHoralistaitemrutina;

        public RutinaViewHolder(@NonNull View itemView) {
            super(itemView);

           // viewIdlistaitemrutina = itemView.findViewById(R.id.viewIdlistaitemrutina);
            viewFechalistaitemrutina = itemView.findViewById(R.id.viewFechalistaitemrutina);
            viewHoralistaitemrutina = itemView.findViewById(R.id.viewHoralistaitemrutina);

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

