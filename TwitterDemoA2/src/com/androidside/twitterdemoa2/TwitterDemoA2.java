package com.androidside.twitterdemoa2;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwitterDemoA2 extends Activity implements View.OnClickListener {    
    EditText idEdit;
    EditText pwEdit;
    EditText messageEdit;
    Button viewButton;
    
    Twitter twitter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        idEdit = (EditText) findViewById(R.id.id);
        pwEdit = (EditText) findViewById(R.id.pw);
        messageEdit = (EditText) findViewById(R.id.message);
        
        viewButton = (Button) findViewById(R.id.view);
        viewButton.setOnClickListener(this);
    }
    
    private boolean isLoginOK() {
        String id = idEdit.getText().toString();
        String pw = pwEdit.getText().toString();
        
        twitter = new TwitterFactory().getInstance(id, pw);
        
        try {
            twitter.verifyCredentials();
        } catch (TwitterException e1) {
            //�α��� ������ �ùٸ��� ���� ���, TwitterException.getStatusCode() == 401            
            return false;
        }
        
        return true;
    }

    @Override
    public void onClick(View v) {        
        String message = messageEdit.getText().toString();        
        
        if (!isLoginOK()) {
            Toast.makeText(this, "�α��� ������ �ùٸ��� �ʽ��ϴ�!!", Toast.LENGTH_LONG).show();
            return;
        }
        
        try {
            //�޽��� ������
            twitter.updateStatus(message);
            Toast.makeText(this, "�޽��� ������ �����Ͽ����ϴ�. - " + message, Toast.LENGTH_LONG).show();
        } catch (TwitterException e) {
            Toast.makeText(this, "�޽��� ������ �����Ͽ����ϴ�. - " + message, Toast.LENGTH_LONG).show();
        }
    }
}