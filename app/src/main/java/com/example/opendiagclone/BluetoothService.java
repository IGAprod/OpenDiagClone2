package com.example.opendiagclone;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolCommand;
import com.github.pires.obd.commands.protocol.TimeoutCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;
import com.github.pires.obd.enums.ObdProtocols;

import java.io.IOException;
import java.util.UUID;

public class BluetoothService {

    private static final String TAG = "BluetoothService";

    private static final UUID MY_UUID_SECURE =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    private String name = "";
    private String address = "";
    private BluetoothAdapter btAdapter;
    private BluetoothDevice device;
    private BluetoothSocket socket;

    public void createConnection(){
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        device = btAdapter.getRemoteDevice(address);
        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID_SECURE);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeCommands(){

        try {
            new EchoOffCommand().run(socket.getInputStream(), socket.getOutputStream());
            new LineFeedOffCommand().run(socket.getInputStream(), socket.getOutputStream());
            new TimeoutCommand(125).run(socket.getInputStream(), socket.getOutputStream());
            new SelectProtocolCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
            new AmbientAirTemperatureCommand().run(socket.getInputStream(), socket.getOutputStream());


        } catch (Exception e) {
            // handle errors
        }

    }

    public BluetoothService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public BluetoothService() {
    }

    public BluetoothService(String address) {
        this.address = address;
    }

}
