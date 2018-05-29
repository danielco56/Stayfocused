package com.example.danielco56.stayfocused;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PersonalAdapter extends ArrayAdapter<Inregistrare> {
    private Context mContext;
    private int mResource;

    public PersonalAdapter(@NonNull Context context, int resource, @NonNull List<Inregistrare> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String data = getItem(position).getData();
        String alcolemie = getItem(position).getAlcolemie();
        int nrBauturi = getItem(position).getNrBauturi();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView dataT = (TextView) convertView.findViewById(R.id.text1);
        TextView alcolemieT = (TextView) convertView.findViewById(R.id.text2);
        TextView nrBauturiT = (TextView) convertView.findViewById(R.id.text3);

        dataT.setText(data);
        alcolemieT.setText(alcolemie);
        nrBauturiT.setText(String.valueOf(nrBauturi));

        return convertView;
    }
}