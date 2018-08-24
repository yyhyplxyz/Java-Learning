package org.blackwalnutlabs.hzy.aicar.bth.scan;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Looper;

import org.blackwalnutlabs.hzy.aicar.bth.bluetooth.BleBluetooth;


public abstract class PeriodScanCallback implements BluetoothAdapter.LeScanCallback {

    private Handler handler = new Handler(Looper.getMainLooper());
    private long timeoutMillis = 10000;
    BleBluetooth bleBluetooth;

    PeriodScanCallback(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public abstract void onScanTimeout();

    public abstract void onScanCancel();

    public void notifyScanStarted() {
        if (timeoutMillis > 0) {
            removeHandlerMsg();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bleBluetooth.stopScan(PeriodScanCallback.this);
                    onScanTimeout();
                }
            }, timeoutMillis);
        }
    }

    public void notifyScanCancel() {
        bleBluetooth.stopScan(PeriodScanCallback.this);
        onScanCancel();
    }

    public void removeHandlerMsg() {
        handler.removeCallbacksAndMessages(null);
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public PeriodScanCallback setTimeoutMillis(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
        return this;
    }

    public BleBluetooth getBleBluetooth() {
        return bleBluetooth;
    }

    public PeriodScanCallback setBleBluetooth(BleBluetooth bluetooth) {
        this.bleBluetooth = bluetooth;
        return this;
    }
}
