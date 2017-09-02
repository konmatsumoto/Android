package com.example.android6969.listatimes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by android6969 on 02/09/17.
 */

public class DetalhesProvaFragment extends Fragment {
    private Prova prova;

    private TextView materia;
    private TextView data;
    private ListView topicos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);
        if(getArguments() != null){
            this.prova = (Prova) getArguments().getSerializable("prova");
        }
        buscaComponentes(layout);
        populaCamposComDados(prova);
        return layout;
    }

    private void buscaComponentes(View layout) {
        this.materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        this.data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        this.topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaCamposComDados(Prova prova) {
        if(prova != null) {
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
            this.topicos.setAdapter(adapter);
        }
    }
}
