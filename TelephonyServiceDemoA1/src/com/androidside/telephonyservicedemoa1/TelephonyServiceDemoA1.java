package com.androidside.telephonyservicedemoa1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelephonyServiceDemoA1 extends Activity {
    int state = -1;
    TelephonyManager tm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //PhoneStateListener ��ü�� TelephonyManager�� ����Ѵ�. 
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(psl, PhoneStateListener.LISTEN_SERVICE_STATE); 
        
        final TextView text = (TextView) findViewById(R.id.text);
        
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                text.setText(getServiceState(state));
            }
        });
    }
    
    //�� ���¸� �����ϴ� ������ ��ü ����
    PhoneStateListener psl = new PhoneStateListener() {
        //��ȭ ���� ���°� ����Ǿ��� �� ȣ��Ǵ� �޼ҵ�
        public void onServiceStateChanged(ServiceState serviceState) {
            state = serviceState.getState();
        }
    };
    
    
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        
        tm.listen(psl, PhoneStateListener.LISTEN_NONE);
    }
    
    //���� ���¸� ���ڿ��� ��ȯ�Ѵ�.
    private String getServiceState(int state) {
        String serviceText = "";
                
        switch(state) {        
        case ServiceState.STATE_IN_SERVICE : 
            serviceText = "���� ����"; break;
        case ServiceState.STATE_OUT_OF_SERVICE : 
            serviceText = "���� �Ұ�"; break;
        case ServiceState.STATE_EMERGENCY_ONLY : 
            serviceText = "��� ��ȭ�� ����"; break;
        case ServiceState.STATE_POWER_OFF : 
            serviceText = "��ȭ ���� ���� ����"; break;
        } 
        
        return serviceText;
    }
}