package online.fakedevelopers.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import online.fakedevelopers.retrofitexample.entity.Repo;
import online.fakedevelopers.retrofitexample.rest.ApiService;
import online.fakedevelopers.retrofitexample.rest.RetrofitClient;
import online.fakedevelopers.retrofitexample.util.ImageUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn ;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tableLayout = findViewById(R.id.tableLayout);
        btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"btn clicked",Toast.LENGTH_SHORT).show();

                ApiService apiService = RetrofitClient.getClient("https://fakestoreapi.com/").create(ApiService.class);
                Call<Repo> call = apiService.listRepos();

                call.enqueue(new Callback<Repo>() {
                    @Override
                    public void onResponse(Call<Repo> call, Response<Repo> response) {
                        Toast.makeText(MainActivity.this,"response from rest api "+response,Toast.LENGTH_LONG).show();
                        if (response.isSuccessful()) {
                            Repo product = response.body();
                            displayProductData(product);

                            //addImageRow(product.getImage());

                        } else {
                            // textView.setText("Request failed with code: " + response.code());
                            Toast.makeText(MainActivity.this, "inside fail else  api call", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Repo> call, Throwable t) {
                        //  textView.setText("Network request failed: " + t.getMessage());

                        Log.e("error", String.valueOf(t));
                        Toast.makeText(MainActivity.this, "run time error from rst api", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

    private void addImageRow(String imageUrl) {
        TableRow row = new TableRow(this);
        ImageView imageView = new ImageView(this);

        // Load image using Glide or Picasso
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        imageView.setLayoutParams(params);
        row.addView(imageView);

        TextView txtView = new TextView(this);
        txtView.setText("Image");
        row.addView(txtView);
        tableLayout.addView(row);
    }



    private void displayProductData(Repo product) {
        // Clear any existing rows
        tableLayout.removeAllViews();

        // Add table rows with the product data
        addTableRow("ID", product.getId().toString());
        addTableRow("Title", product.getTitle());
        addTableRow("Price", "$" + product.getPrice().toString());
        addTableRow("Category", product.getCategory());
        addTableRow("Rating", product.getRating().getRate().toString());
        addTableRow("Rating Count", product.getRating().getCount().toString());
        addImageRow(product.getImage());
    }

    private void addTableRow(String key, String value) {
        TableRow row = new TableRow(this);
        TextView keyView = new TextView(this);
        TextView valueView = new TextView(this);

        keyView.setText(key);
        valueView.setText(value);

        row.addView(keyView);
        row.addView(valueView);

        tableLayout.addView(row);
    }
}