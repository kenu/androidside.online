package com.androidside.webviewdemob1;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class WebViewDemoB1 extends Activity {    
    String HOME_URL ="http://m.androidside.com";
    
    WebView webView;
    ProgressBar progressBar;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.main);        
               
        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);        
                
        webView.setWebViewClient(new SmartWebViewClient());
        webView.setWebChromeClient(new SmartWebChromeClient());
        
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);             
        webView.getSettings().setDefaultFontSize(12);
        
        webView.loadUrl(HOME_URL);
        
        setListener();
    }
    
    //ȭ�� ��尡 ����Ǿ��� �� ���� ������ ����Ͽ� ��Ƽ��Ƽ�� �ٽ� �������� �ʴ´�.
    @Override
    public void onConfigurationChanged(Configuration newConfig){      
        super.onConfigurationChanged(newConfig);
    }
    
    //�ϴܿ� ��ġ�Ǵ� ��ư�� ���� �����ʸ� �����Ѵ�.
    public void setListener() {
        ImageButton leftButton = (ImageButton) findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                webView.goBack();                
            }
        });
        
        ImageButton rightButton = (ImageButton) findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });
        
        ImageButton refreshButton = (ImageButton) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });
        
        ImageButton homeButton = (ImageButton) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                webView.loadUrl(HOME_URL);                
            }
        });
    }

    
    private class SmartWebViewClient extends WebViewClient {
        //URL�� ���ϴµ��� ó���ϰ� ���� �� �����ϴ� �޼ҵ�
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        
        //�� ������ �ε��� ������ �� ȣ��Ǵ� �޼ҵ�
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
        
        //�� �������� �ε��� �� ȣ��Ǵ� �޼ҵ�
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            
            progressBar.setVisibility(View.VISIBLE);
        }        
    }
        
    private class SmartWebChromeClient extends WebChromeClient {     
        //���α׷������� ������¸� ǥ���ϴ� �޼ҵ�
        public void onProgressChanged(WebView view, int progress) {
            progressBar.setProgress(progress); 
        }
    }    
     
    //�� ��ư�� Ŭ���Ǿ��� �� ȣ��Ǵ� �޼ҵ�
    public void onBackPressed() {
        confirmExit();
    }
    
    //���ø����̼� ���Ḧ Ȯ���ϴ� �޼ҵ�
    public void confirmExit() {        
        new AlertDialog.Builder(this)        
        .setTitle("�ȵ���̵���̵�")
        .setMessage("�����Ͻðڽ��ϱ�?")
        .setPositiveButton("��", new DialogInterface.OnClickListener() {            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
        .setNegativeButton("�ƴϿ�", null)
        .show();
    }    
}
