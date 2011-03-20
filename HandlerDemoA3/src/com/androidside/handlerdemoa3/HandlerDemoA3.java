package com.androidside.handlerdemoa3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//����� ȭ���� ������ �ʰ� ������
public class HandlerDemoA3 extends Activity {
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        text = (TextView) findViewById(R.id.text);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doit();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HandlerDemoA3.this, "�׽�Ʈ ��ư�� Ŭ���Ǿ����ϴ�.",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void doit() {
        new Thread() {
            @Override
            public void run() {                
                loop();
            }
        }.start();
    }
    
    int i = 0;
    private void loop() {
        // 0.1�ʾ� ����ϸ鼭 100�� ī��Ʈ(�� 10�� ���� �����)
        for (i = 1; i <= 100; i++) {
            try {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("" + i);            
                    }         
                });
                
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
    
    Handler handler = new Handler();
}