package com.project.versus.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.versus.R;
import com.project.versus.activity.ImagenCanchaActivity;
import com.project.versus.adaptador.AdaptadorAgregarJugador;
import com.project.versus.modelo.EnumCalidad;
import com.project.versus.modelo.Jugador;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CargaJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CargaJugadorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CargaJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CargaJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CargaJugadorFragment newInstance(String param1, String param2) {
        CargaJugadorFragment fragment = new CargaJugadorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<Jugador> jugadores;
    EditText editNomJugador;
    Button btnAgregar;
    Button btnArmarEquipo;
    RecyclerView recyclerView;
    Spinner spinner;
    ArrayList<Jugador> equipo1 = new ArrayList();
    ArrayList<Jugador> equipo2 = new ArrayList();
    boolean[] repetido;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carga_jugador, container, false);
        editNomJugador = view.findViewById(R.id.editJugador);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnArmarEquipo = view.findViewById(R.id.btnArmar);
        jugadores = new ArrayList();
        spinner = view.findViewById(R.id.spinner);
        List<EnumCalidad> calidad = Arrays.asList(EnumCalidad.EXCELENTE,EnumCalidad.BUENO,EnumCalidad.MALO);
        ArrayAdapter<EnumCalidad> adapterSpinner = new ArrayAdapter<EnumCalidad>(view.getContext(),
                android.R.layout.simple_spinner_item,calidad);
        spinner.setAdapter(adapterSpinner);
        recyclerView = view.findViewById(R.id.recyclerJugadores);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        final AdaptadorAgregarJugador adapter = new AdaptadorAgregarJugador(jugadores);
        recyclerView.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador jugador = new Jugador(editNomJugador.getText().toString(), (EnumCalidad) spinner.getSelectedItem());
                jugadores.add(jugador);
                recyclerView.setAdapter(adapter);
/*
                if(jugadores.size()==10){
                    alertEquipos();
                }
*/
                editNomJugador.setText("");
            }
        });

        btnArmarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(getContext(), ImagenCanchaActivity.class);
                startActivity(intent);
                 */
                alertEquipos();
            }
        });



        return view;
    }


    private void alertEquipos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("COMO FORMAR");
        builder.setMessage("Generar de forma ALEATORIA, o seleccione CALIDAD para equipos parejos");
        builder.setNegativeButton("ALEATORIO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                formaAleatoria();
                Intent intent = new Intent(getContext(), ImagenCanchaActivity.class);
                startActivity(intent);
/*
                Toast.makeText(getContext(),
                        "\n"+equipo1.get(0).getNombre()+
                                "\n"+equipo1.get(1).getNombre()+
                                "\n"+equipo1.get(2).getNombre()+
                                "\n"+equipo1.get(3).getNombre()+
                                "\n"+equipo1.get(4).getNombre()+
                                "\nVERSUS\n"+
                                "\n"+equipo2.get(0).getNombre()+
                                "\n"+equipo2.get(1).getNombre()+
                                "\n"+equipo2.get(2).getNombre()+
                                "\n"+equipo2.get(3).getNombre()+
                                "\n"+equipo2.get(4).getNombre(),Toast.LENGTH_LONG).show();

 */
            }
        }).setPositiveButton("CALIDAD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"por calidad",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void formaAleatoria() {
        boolean[] repetido = {false,false,false,false,false,
                              false,false,false,false,false};
        Random random = new Random();
        int[] pos = {0,1,2,3,4,5,6,7,8,9};
        int contador = 0;

        while(contador!=10){
            int valor = random.nextInt(pos.length);

            if(repetido[valor] == false && contador%2==0){
                repetido[valor] =true;
                equipo2.add(jugadores.get(valor));
                contador++;
            }else{
                if(repetido[valor] == false && contador%2!=0){
                    repetido[valor] =true;
                    equipo1.add(jugadores.get(valor));
                    contador++;
                }
            }
        }

    }
}
