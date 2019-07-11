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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class ParametersListAdapter extends BaseAdapter {


    private ArrayList<Parameters> list;
    private LayoutInflater layoutInflater;
    private ArrayList<LineGraphSeries<DataPoint>> series = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private int lastX = 0;

    public void addEntry(int i){


        series.get(i).appendData(new DataPoint(lastX++,RANDOM.nextDouble() * 10d),true,10);
    }

    public ParametersListAdapter(Context context, ArrayList<Parameters> objects){
        this.list = objects;
        for(int i = 0; i < objects.size(); i++){
            series.add(new LineGraphSeries<DataPoint>());
        }
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

        GraphView graphView = (GraphView) view.findViewById(R.id.graph);

        graphView.addSeries(series.get(i));
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxY(10);
        graphView.getViewport().setScrollable(true);
        

        return view;
    }

}
