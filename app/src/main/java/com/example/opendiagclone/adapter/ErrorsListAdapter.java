package com.example.opendiagclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.opendiagclone.R;
import com.example.opendiagclone.models.Errors;
import com.example.opendiagclone.models.Parameters;

import java.util.ArrayList;

public class ErrorsListAdapter extends BaseAdapter {


    private ArrayList<Errors> list;
    private LayoutInflater layoutInflater;

    public ErrorsListAdapter(Context context, ArrayList<Errors> objects){
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

    private  Errors getErrorsModel(int i){
        return (Errors) getItem(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.errors_layout,viewGroup,false);
        }
        Errors errors = getErrorsModel(i);

        TextView codeTextView = (TextView) view.findViewById(R.id.codeTextView);
        TextView statusTextView = (TextView) view.findViewById(R.id.statusTextView);
        TextView errorTextView = (TextView) view.findViewById(R.id.errorTextView);
        codeTextView.setText(errors.getCode());
        statusTextView.setText(errors.getStatus());
        errorTextView.setText(errors.getError());

        return view;
    }

}
