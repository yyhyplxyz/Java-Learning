package org.blackwalnutlabs.hzy.aicar.bth.exception.hanlder;

import org.blackwalnutlabs.hzy.aicar.bth.exception.BlueToothNotEnableException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.ConnectException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.GattException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.NotFoundDeviceException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.OtherException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.ScanFailedException;
import org.blackwalnutlabs.hzy.aicar.bth.exception.TimeoutException;
import org.blackwalnutlabs.hzy.aicar.bth.utils.BleLog;

public class DefaultBleExceptionHandler extends BleExceptionHandler {

    private static final String TAG = "BleExceptionHandler";

    public DefaultBleExceptionHandler() {

    }

    @Override
    protected void onConnectException(ConnectException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onGattException(GattException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onTimeoutException(TimeoutException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onNotFoundDeviceException(NotFoundDeviceException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onBlueToothNotEnableException(BlueToothNotEnableException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onScanFailedException(ScanFailedException e) {
        BleLog.e(TAG, e.getDescription());
    }

    @Override
    protected void onOtherException(OtherException e) {
        BleLog.e(TAG, e.getDescription());
    }
}
