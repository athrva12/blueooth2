package com.example.athrv.blueooth2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView listView1;
    Set<BluetoothDevice>bluetoothDeviceSet;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1=(ListView)findViewById(R.id.list_item);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

    }
    public void on(View view)
    {
        if(bluetoothAdapter.isEnabled()==false)
        {
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
            Toast.makeText(this,"Bluetooth On.",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this,"Bluetooth already on.",Toast.LENGTH_LONG).show();
        }
    }
    public void discoverable(View view)
    {
        Intent intent =new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent,0);

        Toast.makeText(this,"Bluetooth discoverable.",Toast.LENGTH_LONG).show();
    }
    public  void  list(View view)
    {
        Toast.makeText(this,"discovering devices.",Toast.LENGTH_LONG).show();
        bluetoothAdapter.startDiscovery();
        Toast.makeText(this,"showing list.",Toast.LENGTH_LONG).show();
        bluetoothDeviceSet=bluetoothAdapter.getBondedDevices();
        ArrayList arrayList= new ArrayList();
        for(BluetoothDevice bdevice:bluetoothDeviceSet)
        {
            String deviceName=bdevice.getName();
            arrayList.add(deviceName);

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView1.setAdapter(arrayAdapter);
    }
    public void off(View view)
    {
        if(bluetoothAdapter.isEnabled()==true)
        {
            bluetoothAdapter.disable();
            Toast.makeText(this,"Bluetooth off.",Toast.LENGTH_LONG).show();}
        else
        {
            Toast.makeText(this,"Bluetooth is already off.",Toast.LENGTH_LONG).show();
        }
    }
}
