package com.androidside.jsondemoa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JSONDemoA1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(getJsonText());
    }
    

    //JSON �����͸� �д´�.
    public String getJsonText() {
        StringBuffer sb = new StringBuffer();
        try {
            String line = getStringFromUrl("http://www.androidside.com/book/data.json");
            
            //���ݿ��� �о�� �����ͷ� JSON ��ü ����
            JSONObject object = new JSONObject(line);            
            
            sb.append("�̸�:").append(object.getString("�̸�")).append("\n");
            sb.append("����:").append(object.getInt("����")).append("\n");
            sb.append("����:").append(object.getString("����")).append("\n");
            sb.append("��ȥ:").append(object.getBoolean("��ȥ")).append("\n");
            
            //��̴� �迭�� �����Ǿ� �����Ƿ� JSON �迭 ����
            sb.append("���:");
            JSONArray hobbyArray = new JSONArray(object.getString("���"));
            
            for (int i = 0; i < hobbyArray.length(); i++) {
                sb.append(hobbyArray.getString(i)).append(",");
            }
            sb.append("\n");
           
            sb.append("�ּ�:").append(object.getString("�ּ�")).append("\n");
            
            //���� �����ʹ� ���� JSON�̹Ƿ� JSON ��ü ����
            sb.append("����:");
            JSONObject familyObject = new JSONObject(object.getString("����"));
            sb.append(familyObject.getString("�ƹ���")).append(",");
            sb.append(familyObject.getString("��Ӵ�"));
            
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // �־��� URL�� �������� ���ڿ��� ��´�.
    public String getStringFromUrl(String url)
            throws UnsupportedEncodingException {
        // �� �½�Ʈ���� "euc-kr"�� ����ؼ� ���� ��, �̸� ����ؼ� ���� ������ �����͸� ���� �� �ִ�
        // BufferedReader�� �����Ѵ�.
        BufferedReader br = new BufferedReader(new InputStreamReader(
                getInputStreamFromUrl(url), "euc-kr"));

        // ���� �����͸� ������ StringBuffer�� �����Ѵ�.
        StringBuffer sb = new StringBuffer();

        try {
            // ���� ������ ���� �����͸� �ӽ� ������ ���ڿ� ����
            String line = null;

            // ���� ������ �����͸� �о StringBuffer�� �����Ѵ�.
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    // �־��� URL�� ���� �Է� ��Ʈ��(InputStream)�� ��´�.
    public static InputStream getInputStreamFromUrl(String url) {
        InputStream contentStream = null;

        try {
            // HttpClient�� ����ؼ� �־��� URL�� ���� �Է� ��Ʈ���� ��´�.
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            contentStream = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentStream;
    }
}