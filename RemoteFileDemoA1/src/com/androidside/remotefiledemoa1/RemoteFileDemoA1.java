package com.androidside.remotefiledemoa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RemoteFileDemoA1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //�־��� URL�� �����͸� ������ EditText�� ��´�.
        final EditText text = (EditText) findViewById(R.id.viewarea);        
        
        //����ڰ� ��ư�� ������ ��, �����͸� �б� ������ �����ʸ� ����Ѵ�.
        Button button = (Button) findViewById(R.id.geturl);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                try {
                    //�־��� URL(http://www.androidside.com)�� �����͸� ��´�.
                    String string = getStringFromUrl("http://www.androidside.com");
                    
                    text.setText(string);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        });
    }
    
    //�־��� URL�� �������� ���ڿ��� ��´�.
    public String getStringFromUrl(String url) throws UnsupportedEncodingException {
        //�� �½�Ʈ���� "euc-kr"�� ����ؼ� ���� ��, �̸� ����ؼ� ���� ������ �����͸� ���� �� �ִ� BufferedReader�� �����Ѵ�.
        BufferedReader br = new BufferedReader(new InputStreamReader(getInputStreamFromUrl(url), "euc-kr"));
        
        //���� �����͸� ������ StringBuffer�� �����Ѵ�.
        StringBuffer sb = new StringBuffer();        
        
        try {
            //���� ������ ���� �����͸� �ӽ� ������ ���ڿ� ���� 
            String line = null;
            
            //���� ������ �����͸� �о StringBuffer�� �����Ѵ�.
            while ( (line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    
    //�־��� URL�� ���� �Է� ��Ʈ��(InputStream)�� ��´�.
    public static InputStream getInputStreamFromUrl(String url) {   
        InputStream contentStream = null;   
        
        try {
          //HttpClient�� ����ؼ� �־��� URL�� ���� �Է� ��Ʈ���� ��´�.
          HttpClient httpclient = new DefaultHttpClient();
          HttpResponse response = httpclient.execute(new HttpGet(url));
          contentStream = response.getEntity().getContent();          
        } catch (Exception e) {   
            e.printStackTrace();
        }
        
        return contentStream;   
    }
}
