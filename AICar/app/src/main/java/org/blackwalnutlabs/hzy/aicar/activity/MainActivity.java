package org.blackwalnutlabs.hzy.aicar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.blackwalnutlabs.hzy.aicar.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button bluetooth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public void onClick(View v) {
        if(v==bluetooth){
            startActivity(new Intent(MainActivity.this, AnyScanActivity.class));
        }


    }

    private void initView() {


        bluetooth = (Button) findViewById(R.id.bluetooth);
        bluetooth.setOnClickListener(this);



    }
}

