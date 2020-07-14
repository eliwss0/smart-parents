package com.childrenatrisk.smartparents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String passedURL=intent.getStringExtra("passedURL");
        setContentView(R.layout.activity_web_view);
        //TODO use setActionBarTitle depending on link?

        webView=findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); //if page uses Javascript

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + "Smart Parents" + "</font>"));   //workaround to get white text in webview

        webView.loadUrl(passedURL);
    }

    public class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + view.getTitle() + "</font>"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_web_view:
                webView.reload();
                return true;
            case R.id.open_in_web_browser:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
                startActivity(browserIntent);
                return true;
            case R.id.web_back:
                webView.goBack();
                return true;
            case R.id.web_forward:
                webView.goForward();
                return true;
            case android.R.id.home:
//                mWebContainer.removeAllViews();   Remove webView from parent view
                webView.clearHistory();
                webView.onPause();
                webView.removeAllViews();
                webView = null;
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
