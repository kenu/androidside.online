package com.androidside.popupwindowdemo1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class PopupWindowDemo1 extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow1();
            }
        });
    }

    private void showPopupWindow1() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = 
                (LinearLayout) vi.inflate(R.layout.logindialog, null);
        
        final PopupWindow popup = new PopupWindow(loginLayout, 300, 170, true);
        
        final EditText id = (EditText) loginLayout.findViewById(R.id.id);
        final EditText pw = (EditText) loginLayout.findViewById(R.id.pw);
        
        Button login = (Button) loginLayout.findViewById(R.id.login);        
        Button cancel = (Button) loginLayout.findViewById(R.id.cancel);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        PopupWindowDemo1.this,
                        "ID : " + id.getText().toString() + 
                        "\nPW : " + pw.getText().toString(),
                        Toast.LENGTH_LONG).show();
    
                popup.dismiss();
            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                
            }
        });
        
        popup.showAtLocation(loginLayout, Gravity.CENTER, 0, 0);        
    }
}