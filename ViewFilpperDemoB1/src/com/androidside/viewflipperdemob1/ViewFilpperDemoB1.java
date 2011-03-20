package com.androidside.viewflipperdemob1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewFilpperDemoB1 extends Activity implements View.OnClickListener {
    Button start;
    Button stop;
    ViewFlipper flipper;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        
        //�ø��� ������ 1�ʷ� �Ѵ�.
        flipper.setFlipInterval(1000);
    }

    @Override
    public void onClick(View v) {
        //start ��ư�� Ŭ���Ǿ��� �� �ø����� �����Ѵ�.
        if (v == start) {
            flipper.startFlipping();
            
        //stop ��ư�� Ŭ���Ǿ��� �� �ø����� �����.
        } else if (v == stop) {
            flipper.stopFlipping();
        }        
    }
}