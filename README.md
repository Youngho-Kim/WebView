# WebView
[전체코드보기](https://github.com/Youngho-Kim/WebView/blob/master/app/src/main/java/com/android/kwave/runtimepermission/MainActivity.java)
        
        ## 기본적인 WebView 세팅
        
        -----------------기본적인 WebView 세팅----------------------------------------
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
        
        
        ## 앞으로 가기 뒤로가기
        //--------------------앞으로 가기 뒤로가기-------------------------------------
        // 뒤로 가기
         if(webView.canGoBack()){   // 이전 URL 기록이 있다면 
            webView.goBack();       // 브라우저 URL 기록 가운데 이전 주소로 나아간다.
          }
          // 앞으로 가기
          if(webView.canGoForward()){  // 다음 URL 기록이 있다면 
             webView.goForward();   // 브라우저 URL 기록 가운데 다음 주소로 나아간다.
          }
          //---------------------------------------------------------------------------
          
          
          ## url 호출하기
          // url 호출하기
             webView.loadUrl(url);메소드를 사용해 WebView에 URL을 넘겨주고 해당하는 페이지의 가져와 표시하도록 하는 방법
          * loadData() 메소드에는 HTML을 직접 전달한다.
