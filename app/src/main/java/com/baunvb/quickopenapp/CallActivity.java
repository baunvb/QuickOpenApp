package com.baunvb.quickopenapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Baunvb on 12/27/2016.
 */

public class CallActivity extends Activity implements View.OnClickListener {
    private Button btNum1, btNum2, btNum3, btNum4, btNum5, btNum6, btNum7, btNum8, btNum9, btNum0, btSao, btThang, btCall, btRemove;
    private TextView tvPhoneNumber;
    private StringBuilder phoneNumber = new StringBuilder("");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        initView();
    }

    private void initView() {
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone_number);

        btCall = (Button) findViewById(R.id.bt_call);
        btSao = (Button) findViewById(R.id.bt_sao);
        btThang = (Button) findViewById(R.id.bt_thang);
        btRemove = (Button) findViewById(R.id.bt_remove);
        btNum0 = (Button) findViewById(R.id.bt_0);
        btNum1 = (Button) findViewById(R.id.bt_1);
        btNum2 = (Button) findViewById(R.id.bt_2);
        btNum3 = (Button) findViewById(R.id.bt_3);
        btNum4 = (Button) findViewById(R.id.bt_4);
        btNum5 = (Button) findViewById(R.id.bt_5);
        btNum6 = (Button) findViewById(R.id.bt_6);
        btNum7 = (Button) findViewById(R.id.bt_7);
        btNum8 = (Button) findViewById(R.id.bt_8);
        btNum9 = (Button) findViewById(R.id.bt_9);

        btNum0.setOnClickListener(this);
        btNum1.setOnClickListener(this);
        btNum2.setOnClickListener(this);
        btNum3.setOnClickListener(this);
        btNum4.setOnClickListener(this);
        btNum5.setOnClickListener(this);
        btNum6.setOnClickListener(this);
        btNum7.setOnClickListener(this);
        btNum8.setOnClickListener(this);
        btNum9.setOnClickListener(this);
        btSao.setOnClickListener(this);
        btThang.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btCall.setOnClickListener(this);
    }

    private StringBuilder typeNumber(Button bt){
        return phoneNumber.append(bt.getText().toString());
    }

    private void callPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_0:
                tvPhoneNumber.setText(""+typeNumber(btNum0).toString());
                break;
            case R.id.bt_1:
                tvPhoneNumber.setText(""+typeNumber(btNum1).toString());
                break;
            case R.id.bt_2:
                tvPhoneNumber.setText(""+typeNumber(btNum2).toString());
                break;
            case R.id.bt_3:
                tvPhoneNumber.setText(""+typeNumber(btNum3).toString());
                break;
            case R.id.bt_4:
                tvPhoneNumber.setText(""+typeNumber(btNum4).toString());
                break;
            case R.id.bt_5:
                tvPhoneNumber.setText(""+typeNumber(btNum5).toString());
                break;
            case R.id.bt_6:
                tvPhoneNumber.setText(""+typeNumber(btNum6).toString());
                break;
            case R.id.bt_7:
                tvPhoneNumber.setText(""+typeNumber(btNum7).toString());
                break;
            case R.id.bt_8:
                tvPhoneNumber.setText(""+typeNumber(btNum8).toString());
                break;
            case R.id.bt_9:
                tvPhoneNumber.setText(""+typeNumber(btNum9).toString());
                break;
            case R.id.bt_remove:
                int length = phoneNumber.length();
                if (length > 0){
                    phoneNumber.deleteCharAt(phoneNumber.length() - 1);
                }
                tvPhoneNumber.setText(""+phoneNumber);
                break;
            case R.id.bt_sao:
                tvPhoneNumber.setText(""+typeNumber(btSao).toString());
                break;
            case R.id.bt_thang:
                tvPhoneNumber.setText(""+typeNumber(btThang).toString());
                break;
            case R.id.bt_call:
                String phone = tvPhoneNumber.getText().toString();
                callPhone(phone);
                break;
            default:
                break;

        }

    }
}
