package com.example.opendiagclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.opendiagclone.R;
import com.example.opendiagclone.models.Device;
import com.example.opendiagclone.models.Information;

import java.util.ArrayList;

public class BluetoothListAdapter extends BaseAdapter {

    private ArrayList<Device> list;
    private LayoutInflater layoutInflater;

    public BluetoothListAdapter(Context context, ArrayList<Device> objects){
        this.list = objects;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Device> getList() {
        return list;
    }

    public void setList(ArrayList<Device> list) {
        this.list = list;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void addDevice(Device device){
        list.add(device);
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

    private  Device getInformationModel(int i){
        return (Device) getItem(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.bluetooth_layout,viewGroup,false);
        }
        Device device = getInformationModel(i);

        TextView textView = (TextView) view.findViewById(R.id.deviceName);
        TextView textView2 = (TextView) view.findViewById(R.id.deviceAddress);
        textView.setText(device.getDeviceName());
        textView2.setText(device.getDeviceAddress());

        return view;
    }

}
