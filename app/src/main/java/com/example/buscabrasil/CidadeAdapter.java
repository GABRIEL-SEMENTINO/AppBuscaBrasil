package com.example.buscabrasil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.List;


public class CidadeAdapter extends BaseAdapter {

    private List<Cidade> cidades;
    private WeakReference<Context> weakReference;
    private LayoutInflater inflater;

    public CidadeAdapter(List<Cidade> cidades, WeakReference<Context> weakReference) {
        this.cidades = cidades;
        this.weakReference = weakReference;

        this.inflater = LayoutInflater.from(weakReference.get());
    }

    public <Cidade> CidadeAdapter(List<Cidade> body, WeakReference<Context> weakReference) {
    }

    @Override
    public int getCount() {
        return cidades.size();
    }

    @Override
    public Object getItem(int i) {
        return cidades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return cidades.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        Cidade cidade = (Cidade) getItem(i);

        if (view == null) {
            view = inflater.inflate(R.layout.activity_tela3, null);

            viewHolder = new ViewHolder();


            viewHolder.tvId = (TextView) view.findViewById(R.id.BotaoListar);
            viewHolder.tvNome = (TextView) view.findViewById(R.id.BuscaEstado);

            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvId.setText(String.valueOf(cidade.getId()));
        viewHolder.tvNome.setText(cidade.getNome());

        return view;
    }

    static class ViewHolder {

        TextView tvId;
        TextView tvNome;
    }
}