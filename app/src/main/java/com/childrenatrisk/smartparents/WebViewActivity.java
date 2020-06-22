package com.childrenatrisk.smartparents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String passedURL=intent.getStringExtra("passedURL");
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); //if page uses Javascript

        webView.loadUrl(passedURL); //way to change depending on where it is called?
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_view_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_web_view:
                webView.reload();
                return true;
            case R.id.close_web_view:
//                Remove webView from parent view?
//                mWebContainer.removeAllViews();
                webView.clearHistory();
                webView.onPause();
                webView.removeAllViews();
                webView.destroyDrawingCache();
                webView.destroy();
                webView = null;
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}