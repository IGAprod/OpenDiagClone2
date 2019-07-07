package com.example.opendiagclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.opendiagclone.R;
import com.example.opendiagclone.models.Information;
import com.example.opendiagclone.models.Parameters;

import java.util.ArrayList;

public class ParametersListAdapter extends BaseAdapter {


    private ArrayList<Parameters> list;
    private LayoutInflater layoutInflater;

    public ParametersListAdapter(Context context, ArrayList<Parameters> objects){
        this.list = objects;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private  Parameters getParametrsModel(int i){
        return (Parameters) getItem(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.parameters_layout,viewGroup,false);
        }
        Parameters parameters = getParametrsModel(i);

        TextView textView = (TextView) view.findViewById(R.id.parametersTextView1);
        TextView textView2 = (TextView) view.findViewById(R.id.parametersTextView2);
        textView.setText(parameters.getParameters());
        textView2.setText(parameters.getValue());

        return view;
    }

}
