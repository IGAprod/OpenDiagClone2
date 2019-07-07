package com.example.opendiagclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.opendiagclone.R;
import com.example.opendiagclone.models.Information;

import java.util.ArrayList;

public class InformationListAdapter extends BaseAdapter {


    private ArrayList<Information> list;
    private LayoutInflater layoutInflater;

    public InformationListAdapter(Context context, ArrayList<Information> objects){
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

    private  Information getInformationModel(int i){
        return (Information) getItem(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_layout,viewGroup,false);
        }
        Information information = getInformationModel(i);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        textView.setText(information.getParametrs());
        textView2.setText(information.getValue());

        return view;
    }
}
