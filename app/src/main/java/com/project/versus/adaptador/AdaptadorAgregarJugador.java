package com.project.versus.adaptador;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.versus.R;
import com.project.versus.modelo.EnumCalidad;
import com.project.versus.modelo.Jugador;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorAgregarJugador extends RecyclerView.Adapter<AdaptadorAgregarJugador.ViewHolderJugador> {

    ArrayList<Jugador> jugadores;

    public AdaptadorAgregarJugador(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @NonNull
    @Override
    public ViewHolderJugador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador,
                null,false);

        return new ViewHolderJugador(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJugador holder, int position) {
        holder.editNombre.setText(jugadores.get(position).getNombre());
        if(jugadores.get(position).getCalidad() == EnumCalidad.EXCELENTE){
            holder.txtCalidad1.setBackgroundColor(Color.GREEN);
            holder.txtCalidad2.setBackgroundColor(Color.GREEN);
            holder.txtCalidad3.setBackgroundColor(Color.GREEN);
        }
        if(jugadores.get(position).getCalidad() == EnumCalidad.BUENO){
            holder.txtCalidad1.setBackgroundColor(Color.YELLOW);
            holder.txtCalidad2.setBackgroundColor(Color.YELLOW);
        }
        if(jugadores.get(position).getCalidad() == EnumCalidad.MALO){
            holder.txtCalidad1.setBackgroundColor(Color.RED);
        }
        int num = position+1;
        holder.textNum.setText(""+num);
    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public class ViewHolderJugador extends RecyclerView.ViewHolder {

        TextView textNum,editNombre;
        ImageView txtCalidad1,txtCalidad2,txtCalidad3;
        public ViewHolderJugador(@NonNull View itemView) {
            super(itemView);
            textNum = itemView.findViewById(R.id.txtNum);
            editNombre = itemView.findViewById(R.id.etiEditNombre);
            txtCalidad1 = itemView.findViewById(R.id.etiCalidad1);
            txtCalidad2 = itemView.findViewById(R.id.etiCalidad2);
            txtCalidad3 = itemView.findViewById(R.id.etiCalidad3);
        }
    }
}
