package com.android.kwave.runtimepermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WebView.OnClickListener{
WebView webView;
    Button previous, netx, go;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = (WebView) findViewById(R.id.webView);
        previous = (Button) findViewById(R.id.front);
        netx = (Button) findViewById(R.id.back);
        go = (Button) findViewById(R.id.go);
        url = (EditText) findViewById(R.id.url);

        previous.setOnClickListener(this);
        netx.setOnClickListener(this);
        go.setOnClickListener(this);


        // --------------기본적인 WebView 세팅----------------------------------------
        // script 사용 설정 (필수) - 페이지내의javascript가 동작되도록 한다.
        webView.getSettings().setJavaScriptEnabled(true);
        // 1. 웹뷰 클라이언트를 지정...(안하면 내장 웹브라우저가 팝업된다)
        // http://.... 할때 사용
        webView.setWebViewClient(new WebViewClient());
        // 2. 둘다 세팅할 것 : 프로토콜에 따라 클라이언트가 선택되는 것으로 파악됨
        // https://.... 할때 사용
        webView.setWebChromeClient(new WebChromeClient());
        // * 클라이언트가 지정되지 않으면 내장 웹앱이 실행된다.
        //-----------------------------------------------------------------------------


    }

    private void loadUrl(String url){
        // 문자열의 앞에 프로토콜인 http 문자열이 없다면 붙여준다.
        if(!(url.startsWith("http"))){
            url = "http://"+url;
        }
        // url 호출하기
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 뒤로 가기
            case R.id.front :
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;
            // 앞으로 가기
            case R.id.back :
                if(webView.canGoForward()){
                    webView.goForward();
                }
                break;
            //url 이동
            case R.id.go :
                String urlGo = url.getText().toString();
                if(!"".equals(urlGo)) { // 공백이 아닐경우 처리
                       // 문자열이 url 패턴일때만
                     if(urlGo.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        loadUrl(urlGo);
                        }else{
                            Toast.makeText(this, "url이 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                        }



                // 아래는 개발자가 처리  >> 정규식으로 url 패턴이 아닐때만 처리
                    /**
                     * 자주쓰는 정규식 표현

                     * 전자우편 주소:
                     /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/

                     URL:
                     /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/

                     HTML 태그 - HTML tags:
                     /\<(/?[^\>]+)\>/

                     전화 번호 - 예, 123-123-2344 혹은 123-1234-1234:
                     /(\d{3}).*(\d{3}).*(\d{4})/

                     날짜 - 예, 3/28/2007 혹은 3/28/07:
                     /^\d{1,2}\/\d{1,2}\/\d{2,4}$/

                     jpg, gif 또는 png 확장자를 가진 그림 파일명:
                     /([^\s]+(?=\.(jpg|gif|png))\.\2)/

                     1부터 50 사이의 번호 - 1과 50 포함:
                     /^[1-9]{1}$|^[1-4]{1}[0-9]{1}$|^50$/

                     16 진수로 된 색깔 번호:
                     /#?([A-Fa-f0-9]){3}(([A-Fa-f0-9]){3})?/

                     적어도 소문자 하나, 대문자 하나, 숫자 하나가 포함되어 있는 문자열(8글자 이상 15글자 이하) - 올바른 암호 형식을 확인할 때 사용될 수 있음:
                     /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}/
                     */

                }
                break;
        }
    }
}
