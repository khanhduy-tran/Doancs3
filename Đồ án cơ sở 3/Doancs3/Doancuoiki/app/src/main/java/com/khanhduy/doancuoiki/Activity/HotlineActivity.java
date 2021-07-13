package com.khanhduy.doancuoiki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.khanhduy.doancuoiki.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HotlineActivity extends AppCompatActivity {
    Toolbar toolbarHotLine;
    Button buttonHotline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);
        AnhXa();
        ActionToolBar();
        Call();
    }

    private void Call() {
        buttonHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent();
                view.setAction(Intent.ACTION_DIAL);
                view.setData(Uri.parse("tel:0329334542"));
                startActivity(view);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarHotLine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarHotLine.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarHotLine = (Toolbar) findViewById(R.id.toolBarHotLine);
        buttonHotline = (Button) findViewById(R.id.buttonHotline);
    }
}