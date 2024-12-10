package online.fakedevelopers.anywebsiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText textView;
    private Button btn ;

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize WebView
        myWebView = (WebView) findViewById(R.id.webview);


        textView= findViewById(R.id.url);
        String reqURL = textView.getText().toString();

        Log.e("url","---------------------    textView     ----------------->"+textView);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "you clicked a button", Toast.LENGTH_SHORT).show();

                if (myWebView != null) {
                    myWebView.setWebViewClient(new WebViewClient());  // Ensure URLs open within the WebView
                    myWebView.getSettings().setJavaScriptEnabled(true);  // Enable JavaScript if needed

                    Log.d("main", "WebView is calling javatpoint url");

                   //  myWebView.loadUrl("https://razorpay.me/@learning7063");

                    myWebView.loadUrl("https://tsctup.com/");

//                    myWebView.loadUrl("https://www.javatpoint.com");


                    Log.d("main", "myWebView is finished!.. " + myWebView);
                } else {
                    Log.e("main", "WebView is null!");
                }

            }
        });
    }
}