package com.example.javabuttonstest;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class HomeActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize WebView
        myWebView = (WebView) findViewById(R.id.webview);

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity
               /* Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);*/

                Log.d("main", "main btn is clicked");
                Toast.makeText(HomeActivity.this, "submit button clicked", Toast.LENGTH_SHORT).show();

                // Ensure WebView is not null
                if (myWebView != null) {
                    myWebView.setWebViewClient(new WebViewClient());  // Ensure URLs open within the WebView
                    myWebView.getSettings().setJavaScriptEnabled(true);  // Enable JavaScript if needed

                    Log.d("main", "WebView is calling javatpoint url");

                    myWebView.loadUrl("https://razorpay.me/@learning7063");

//                    myWebView.loadUrl("https://www.javatpoint.com");


                    Log.d("main", "myWebView is finished!.. " + myWebView);
                } else {
                    Log.e("main", "WebView is null!");
                }
            }
        });
    }
}

/*
public class HomeActivity extends AppCompatActivity {

    private TextView text;
    private WebView myWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
*/
/*
        Intent intent = getIntent();
        String data = intent.getStringExtra("value");
        System.out.println("data :: "+data);*//*


        Button buttonHome = findViewById(R.id.buttonHome);
        */
/* text = findViewById(R.id.editTextNumber);
         text.setText("hi anubhav");
*//*

        myWebView = (WebView) findViewById(R.id.webview);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                Log.d("main","main btn is clicked");
                Toast.makeText(HomeActivity.this, "submit button clicked", Toast.LENGTH_SHORT).show();

                Log.d("main","intent is executing!..");


                myWebView.setWebViewClient(new WebViewClient());  // Ensures URLs open within the WebView
                myWebView.getSettings().setJavaScriptEnabled(true);  // Enable JavaScript if needed

                Log.d("main","WebView is calling javatppoint url");
                myWebView.loadUrl("https://www.javatpoint.com");
                Log.d("main","myWebView is finished!.."+myWebView);
            }
        });
    }
}
*/
