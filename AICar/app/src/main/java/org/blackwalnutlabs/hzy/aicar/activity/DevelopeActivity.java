package org.blackwalnutlabs.hzy.aicar.activity;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.blackwalnutlabs.hzy.aicar.R;
import org.blackwalnutlabs.hzy.aicar.bth.BluetoothService;
import org.blackwalnutlabs.hzy.aicar.bth.conn.BleCharacterCallback;
import org.blackwalnutlabs.hzy.aicar.bth.exception.BleException;
import org.blackwalnutlabs.hzy.aicar.bth.utils.HexUtil;


import java.util.Timer;
import java.util.TimerTask;

public class DevelopeActivity extends AppCompatActivity {

    public int mode;
    public int leftSpeed;
    public int rightSpeed;

    private String back="";
    private TextView backtxt,LPWM,RPWM,RSpeed,LSpeed;
    private Button pubbt;
    private EditText p1,p2,p3;

    private int PWML=0,PWMR=0,SpeedL=0,SpeedR=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_developer);
        mode=0;
        leftSpeed=0;
        rightSpeed=0;
        backtxt=(TextView)findViewById(R.id.txt_read);
        LPWM=(TextView)findViewById(R.id.LPWM);
        RPWM=(TextView)findViewById(R.id.RPWM);
        RSpeed=(TextView)findViewById(R.id.RSpeed);
        LSpeed=(TextView)findViewById(R.id.LSpeed);
        p1=(EditText)findViewById(R.id.p1);
        p2=(EditText)findViewById(R.id.p2);
        p3=(EditText)findViewById(R.id.p3);
        pubbt=(Button)findViewById(R.id.write_bt);
        initBlueTooth();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mode=0;leftSpeed=0;rightSpeed=0;
        writer(mode,leftSpeed,rightSpeed);
        unbindService();
    }

    /**
     * 协议
     */
    private void analysisMSG(String msg){
        try{
            PWML =Integer.parseInt(msg.substring(msg.indexOf("a")+1, msg.indexOf("b")));
            PWMR =Integer.parseInt(msg.substring(msg.indexOf("b")+1, msg.indexOf("c")));
            SpeedL =Integer.parseInt(msg.substring(msg.indexOf("c")+1, msg.indexOf("d")));
            SpeedR =Integer.parseInt(msg.substring(msg.indexOf("d")+1));
        }catch (Exception e){
            return;
        }
    }
    public String protocol(int protocol_data) { //自定义协议
        String protocol_msg = "";
        if (protocol_data >= 0 && 100 > protocol_data) {
            protocol_msg = Integer.toHexString(30) + Integer.toHexString(protocol_data + 30);
        } else if (protocol_data < 10000 && protocol_data >= 100) {
            protocol_msg = Integer.toHexString(protocol_data / 100 + 30) + Integer.toHexString(protocol_data % 100 + 30);
        }
        return protocol_msg;
    }

    /**
     * BlueTooth
     */

    private BluetoothService mBluetoothService;
    private BluetoothGatt gatt;
    private BluetoothGattService service;

    private void initBlueTooth() {
        bindService();
    }

    private Handler bthHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                getService();
                BLE_start_listener();
            } else if (msg.what == 2) {
                BLE_start_writer();
            }
            else if (msg.what == 3) {
                writer(mode,leftSpeed,rightSpeed);
            }
            super.handleMessage(msg);
        }
    };


    private void writer(int controlMode, int targetL, int targetR) { //仅仅用于发送小车动作控制命令
        final BluetoothGattCharacteristic characteristic = mBluetoothService.getCharacteristic();
        mBluetoothService.write(
                characteristic.getService().getUuid().toString(),
                characteristic.getUuid().toString(),
                String.valueOf(protocol(controlMode) + protocol(targetL) + protocol(targetR) + protocol(0) + protocol(0) + protocol(0) + protocol(0) + protocol(0) + protocol(0) + protocol(4321)),//发送10进制比特
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(final BluetoothGattCharacteristic characteristic) {
                        //成功写入操作
                        StartWriteing(200);
                    }

                    @Override
                    public void onFailure(final BleException exception) {
                        StartBLEWriterAfter(150);
                    }

                    @Override
                    public void onInitiatedResult(boolean result) {

                    }
                });
    }

    private void StartBLEListenerAfter(int time) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                bthHandler.sendMessage(msg);
            }
        };
        timer.schedule(task, time);
    }

    private void StartBLEWriterAfter(int time) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Message msg = new Message();
                msg.what = 2;
                bthHandler.sendMessage(msg);
            }
        };
        timer.schedule(task, time);
    }

    private void StartWriteing(int time ) {
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                Message msg=new Message();
                msg.what=3;
                bthHandler.sendMessage(msg);
            }
        };
        timer.schedule(task, time);
    }

    private void getService() {
        gatt = mBluetoothService.getGatt();
        mBluetoothService.setService(gatt.getServices().get(gatt.getServices().size() - 1));
    }

    private void BLE_start_writer() {
        service = mBluetoothService.getService();
        mBluetoothService.setCharacteristic((service.getCharacteristics().get(service.getCharacteristics().size() - 2)));
        mBluetoothService.setCharaProp(1);
        Message msg = new Message();
        msg.what = 3;
        bthHandler.sendMessage(msg);

        pubbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode=Integer.parseInt(p1.getText().toString());
                leftSpeed=Integer.parseInt(p2.getText().toString());
                rightSpeed=Integer.parseInt(p3.getText().toString());
            }
        });
    }

    private void BLE_start_listener() {
        service = mBluetoothService.getService();
        mBluetoothService.setCharacteristic((service.getCharacteristics().get(service.getCharacteristics().size() - 1)));
        mBluetoothService.setCharaProp(2);
        final BluetoothGattCharacteristic characteristic = mBluetoothService.getCharacteristic();
        mBluetoothService.notify(
                characteristic.getService().getUuid().toString(),
                characteristic.getUuid().toString(),
                new BleCharacterCallback() {

                    @Override
                    public void onSuccess(final BluetoothGattCharacteristic characteristic) {
                        DevelopeActivity.this.runOnUiThread(() -> {

                            String hexEncode=(String.valueOf(HexUtil.encodeHex(characteristic.getValue())));//得到16进制字符串
                            byte[] bytes= HexUtil.hexStringToBytes(hexEncode);//转为byte类型
                            back=(""+new String(bytes));//解出字符串
                            analysisMSG(back);
                            backtxt.setText("原数据:"+back);
                            LPWM.setText("Lpwm:"+PWML);
                            RPWM.setText("Rpwm:"+PWMR);
                            LSpeed.setText("Lspeed:"+SpeedL);
                            RSpeed.setText("Rspeed:"+SpeedR);
                        });
                    }

                    @Override
                    public void onFailure(final BleException exception) {
                        DevelopeActivity.this.runOnUiThread(() -> {
                            StartBLEListenerAfter(100);//重新开始监听
                        });
                    }

                    @Override
                    public void onInitiatedResult(boolean result) {

                    }
                });
    }

    private void bindService() {
        Intent bindIntent = new Intent(this, BluetoothService.class);
        this.bindService(bindIntent, mFhrSCon, Context.BIND_AUTO_CREATE);
        StartBLEListenerAfter(100);
        StartBLEWriterAfter(150);
    }

    private ServiceConnection mFhrSCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBluetoothService = ((BluetoothService.BluetoothBinder) service).getService();
            mBluetoothService.setConnectCallback(callback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBluetoothService = null;
        }
    };

    private BluetoothService.Callback2 callback = this::finish;

    private void unbindService() {
        this.unbindService(mFhrSCon);
    }


}
