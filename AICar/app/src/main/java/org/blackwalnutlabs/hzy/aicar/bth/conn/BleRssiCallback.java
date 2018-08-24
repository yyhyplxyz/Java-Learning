package org.blackwalnutlabs.hzy.aicar.bth.conn;


public abstract class BleRssiCallback extends BleCallback {
    public abstract void onSuccess(int rssi);
}