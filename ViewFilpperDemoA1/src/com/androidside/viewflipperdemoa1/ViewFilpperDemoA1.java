package com.androidside.viewflipperdemoa1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ViewFilpperDemoA1 extends Activity implements View.OnClickListener {
    Button prev;
    Button next;
    ViewFlipper flipper;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        
        prev.setOnClickListener(this);
        next.setOnClickListener(this);        
    }

    @Override
    public void onClick(View v) {
        //prev ��ư�� Ŭ���Ǿ��� ��
        if (v == prev) {
            flipper.showPrevious();
            
        //next ��ư�� Ŭ���Ǿ��� ��
        } else if (v == next) {
            flipper.showNext();
        }        
    }
}