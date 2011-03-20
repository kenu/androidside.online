package com.androidside.htmlcleanerdemo1;

import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HtmlCleanerDemo1 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView text = (TextView) findViewById(R.id.text);
        
        HtmlCleaner cleaner = new HtmlCleaner(); 
        try {            
            //TagNode node = cleaner.clean(new URL("http://www.androidside.com"), "euc-kr");
            TagNode node = cleaner.clean(new URL("http://m.news.naver.com/home.nhn"));
            //TagNode node = cleaner.clean("<html><head><title>aaa</title></head></html>");
            //Object[] objects = node.evaluateXPath("//title");
            Object[] objects = node.evaluateXPath("//ul[@class='li1']/li/a/span[@class='if']");
            //Object[] objects = node.getElementsByName("title", true);
            Log.d("tag", "objects " + objects.length);
            
            for (Object obj : objects) {
                TagNode t = (TagNode) obj;
                Log.d("tag", t.getText().toString());
                text.append(t.getText().toString()+"\n");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

/*<div id="ct">
<div class="h5">
<h2>�� �ð� �ֿ� ����</h2>
</div>
<!--NEI=a:hom.head-->
<ul class="li1">
<li>
<a href="http://m.news.naver.com/read.nhn?mode=LSD&sid1=001&oid=001&aid=0003290695" class="ct">
[��ġ] 찴���� &quot;�� ����ħ���� �ﰢ ������ �ߵ�&quot;
<span class="if">���մ���</span>
</a><!--NP=r:1,i:880000D8_000000000000000003290695-->
</li>
<li>
<a href="http://m.news.naver.com/read.nhn?mode=LSD&sid1=001&oid=008&aid=0002334485" class="ct">
�� ���� ����,���� �����ߴܡ����������� ��ҿ
<span class="if">�Ӵ�������</span>
</a><!--NP=r:2,i:880000C2_000000000000000002334485-->
</li>
<li>*/