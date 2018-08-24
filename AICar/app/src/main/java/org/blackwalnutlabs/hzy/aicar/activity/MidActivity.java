package org.blackwalnutlabs.hzy.aicar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.blackwalnutlabs.hzy.aicar.R;


public class MidActivity extends AppCompatActivity  implements View.OnClickListener {



    private Button developer_mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);
        initView();
    }

    @Override
    public void onClick(View v) {


        if(v==developer_mode){
            startActivity(new Intent(MidActivity.this, DevelopeActivity.class));
        }


    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        developer_mode = (Button) findViewById(R.id.developer_mode);
        developer_mode.setOnClickListener(this);
    }
}

