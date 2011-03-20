package com.androidside.telephonydemoa2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class TelephonyDemoA2 extends BroadcastReceiver {
    Context context; 

    MyPhoneStateListener phoneStateListener;
    TelephonyManager telephony;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        phoneStateListener = new MyPhoneStateListener();
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);
    }

    class MyPhoneStateListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                Log.d("telephony", "CALL_STATE_RINGING - " + incomingNumber);
                Toast.makeText(context, "incomingName " + getName(incomingNumber),
                        Toast.LENGTH_LONG).show();
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.d("telephony", "CALL_STATE_OFFHOOK - " + incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                Log.d("telephony", "CALL_STATE_IDLE - " + incomingNumber);
                break;
            }
            telephony.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
        } 

        public String getName(String incomingNumber) {
            String incomingName = "";
            
            //������ ������
            String[] projection = new String[] { PhoneLookup.DISPLAY_NAME };

            Uri uri = Uri.withAppendedPath(
                    PhoneLookup.CONTENT_FILTER_URI, Uri.encode(incomingNumber));
            
            //������ uri���� ������ projection�� �������� Ŀ���� ���Ѵ�.
            Cursor cursor = context.getContentResolver().query(uri, projection,
                    null, null, null); 

            //Ŀ���� ��ġ�� ù ��°�� �̵��Ѵ�.
            if (cursor.moveToFirst()) {
                //Ŀ������ �ּҷϿ� ��ϵ� �̸��� ��´�.
                int nameIdx = cursor
                        .getColumnIndex(PhoneLookup.DISPLAY_NAME);
                incomingName = cursor.getString(nameIdx);                
            } else {
                incomingName = "NONE";
            }

            Log.d("telephony", "incomingName " + incomingName);
            
            return incomingName;
        }
    }

}