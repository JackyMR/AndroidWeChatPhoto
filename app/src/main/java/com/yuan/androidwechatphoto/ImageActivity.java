package com.yuan.androidwechatphoto;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import ooo.oxo.library.widget.PullBackLayout;

/**
 * Created by Yuan on 15/12/19.
 */
public class ImageActivity extends AppCompatActivity implements PullBackLayout.Callback{


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_image);
    }


    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {

    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {

    }

    private void fadein(){

    }

    private void fadeout(){

    }
}
