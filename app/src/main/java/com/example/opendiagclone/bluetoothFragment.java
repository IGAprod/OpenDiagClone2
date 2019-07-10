package com.example.opendiagclone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.opendiagclone.adapter.BluetoothListAdapter;
import com.example.opendiagclone.models.Device;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;


public class bluetoothFragment extends Fragment {

    private static final String TAG = "bluetoothFragment";
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    public static String EXTRA_DEVICE_NAME = "device_name";
    private static final int PERMISSION_REQUEST_CODE = 0;
    private Set<BluetoothDevice> pairedDevices;
    private static final int REQUEST_ENABLE_BT = 0;
    private ArrayList<Device> deviceArrayList = new ArrayList<>();
    private BluetoothListAdapter listAdapter;
    private BluetoothAdapter mBtAdapter;

    private final BroadcastReceiver mReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            String action= intent.getAction();
            Log.d(TAG, action);
            Toast.makeText(getActivity(),
                    action, Toast.LENGTH_LONG).show();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                Log.d(TAG, "ACTION_FOUND");
                BluetoothDevice device= intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Device nearDevice = new Device(device.getName(), device.getAddress());
                    Toast.makeText(getActivity(),
                            nearDevice.toString(), Toast.LENGTH_LONG).show();

                 //   deviceArrayList.add(nearDevice);
                    listAdapter.addDevice(nearDevice);
                    listAdapter.notifyDataSetChanged();
                }
            }else {
                if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                    Log.d(TAG, "ACTION_DISCOVERY_FINISHED");
                    if (listAdapter.getCount() == 0) {
                        Log.d(TAG, "DEVICE_NOT_FOUND");
                        String noDevices = getResources().getText(R.string.none_found).toString();
                        Toast.makeText(getActivity(),
                                noDevices, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

    private void doDiscovery() {

        Log.d(TAG, "doDiscovery()");

        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        mBtAdapter.startDiscovery();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        Objects.requireNonNull(getActivity()).unregisterReceiver(mReceiver);

    }

    private void bluetoothConnection() {

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBtAdapter == null) {
            Toast.makeText(getActivity(),
                    "Ваше устройство не поддерживает bluetooth", Toast.LENGTH_LONG).show();
            return;
        }

        if (!mBtAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
        }

        //Переделать
        long startTime =  java.lang.System.currentTimeMillis();
        while (!mBtAdapter.isEnabled() || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                if(  java.lang.System.currentTimeMillis() - startTime > 4000){
                    return;
                }
        }

        if(mBtAdapter.isEnabled() && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            IntentFilter bluetoothFilter = new IntentFilter();
            bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            bluetoothFilter.addAction(BluetoothDevice.ACTION_FOUND);
            bluetoothFilter.addAction(BluetoothDevice.ACTION_NAME_CHANGED);
            bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            getActivity().registerReceiver(mReceiver, bluetoothFilter);

            String status;
            @SuppressLint("HardwareIds") String myDeviceAddress = mBtAdapter.getAddress();
            String myDeviceName = mBtAdapter.getName();
            status = myDeviceName + ": " + myDeviceAddress;
            Device myDevice = new Device(myDeviceName, myDeviceAddress);
            // deviceArrayList.add(myDevice);

            Toast.makeText(getActivity(), status, Toast.LENGTH_LONG).show();

            pairedDevices = mBtAdapter.getBondedDevices();


            doDiscovery();

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.bluetooth_connection,container,false);
        ListView mListView = view.findViewById(R.id.bluetoothListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(TAG, "itemClick: position = " + position + ", id = "
                        + id);
                Device device = (Device) listAdapter.getItem(position);

                Intent intent = new Intent();

                intent.putExtra(EXTRA_DEVICE_ADDRESS, device.getDeviceAddress());
                intent.putExtra(EXTRA_DEVICE_NAME,device.getDeviceName());

                // Set result and finish this Activity
                getActivity().setResult(Activity.RESULT_OK, intent);
                Fragment fragment = new ParametersFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
            }
        });
        bluetoothConnection();

        listAdapter = new BluetoothListAdapter(getActivity(), deviceArrayList);
        mListView.setAdapter(listAdapter);



        return view;
    }
}
