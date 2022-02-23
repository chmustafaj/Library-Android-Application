package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {
    private WebView linkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        linkedin = findViewById(R.id.linkedin);
        linkedin.loadUrl("https://www.linkedin.com/in/chaudhry-mustafa-jawad-643649205/");
        //My linkedin will load inside the application
        linkedin.setWebViewClient(new WebViewClient());
        //Enabling javascript componenents for a better experience
        linkedin.getSettings().setJavaScriptEnabled(true);
    }

    //If the user decides to explore in the webView, then pressing the back button will cause him
    //to move back in the webview, rather than out of it
    @Override
    public void onBackPressed() {
        if (linkedin.canGoBack()) {
            linkedin.goBack();
        } else {
            super.onBackPressed();
        }
    }
}