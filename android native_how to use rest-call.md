Q) how to use rest call (network call) ?

ANSWER:

To make network calls in Android, one of the most popular libraries is **Retrofit**. Retrofit is a type-safe HTTP client for Android and Java, developed by Square. It simplifies the process of making network requests, handling responses, and parsing JSON data. Here's a comprehensive guide on how to use Retrofit in Android.

### 1. Add Retrofit to Your Project

First, add the necessary dependencies to your `build.gradle` file.

```groovy
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```

- **`retrofit:2.9.0`**: The core Retrofit library.
- **`converter-gson:2.9.0`**: A converter to automatically parse JSON responses using Gson.

### 2. Define the API Endpoints

Create an interface that defines the endpoints of your API. This is where you specify the HTTP methods (GET, POST, etc.), endpoint paths, and expected parameters.

```java
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
```

In this example, `listRepos` will request a list of repositories for a given user from the GitHub API.

### 3. Create the Retrofit Instance

Set up a singleton instance of Retrofit in your application. This is where you configure the base URL, converters, and other settings.

```java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
```

### 4. Define Data Models

Create Java classes that represent the structure of your JSON data. Retrofit, with the help of Gson, will automatically map JSON responses to these models.

```java
public class Repo {
    private String name;
    private String description;
    private String language;
    // Getters and Setters
}
```

### 5. Make the Network Call

Now, you can use the `ApiService` interface to make network requests.

```java
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        ApiService apiService = RetrofitClient.getClient("https://api.github.com/").create(ApiService.class);
        Call<List<Repo>> call = apiService.listRepos("octocat");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    for (Repo repo : repos) {
                        textView.append(repo.getName() + "\n");
                    }
                } else {
                    textView.setText("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                textView.setText("Network request failed: " + t.getMessage());
            }
        });
    }
}
```

### 6. Running Asynchronous vs. Synchronous Calls

- **Asynchronous Call**: The `enqueue()` method is used for making asynchronous calls. The request runs on a background thread, and the response or failure is delivered on the main thread, making it safe to update UI components.

- **Synchronous Call**: The `execute()` method can be used for synchronous requests, which run on the calling thread and should not be used on the main thread to avoid blocking the UI.

### 7. Error Handling

Handle errors properly by checking the response code and handling exceptions.

```java
@Override
public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
    if (response.isSuccessful()) {
        // Handle successful response
    } else {
        // Handle error response
        int statusCode = response.code();
        // Example: 404 Not Found
        textView.setText("Error: " + statusCode);
    }
}

@Override
public void onFailure(Call<List<Repo>> call, Throwable t) {
    // Handle network failure
    textView.setText("Network error: " + t.getMessage());
}
```

### 8. Why Use Retrofit?

- **Type Safety**: Retrofit provides compile-time safety for REST API calls.
- **Automatic Parsing**: It automatically parses JSON to Java objects using converters like Gson or Moshi.
- **Error Handling**: Retrofit provides a straightforward way to handle API errors and network failures.
- **Flexible**: Retrofit allows for a wide variety of customization options, including interceptors, custom converters, and more.

### 9. When to Use Retrofit?

- When building Android apps that require network communication, especially REST APIs.
- When you need to work with JSON data, and you want a simple and efficient way to parse it.
- When you need a robust and maintainable solution for handling API requests.

### Conclusion

Retrofit is a powerful tool for making network calls in Android. It simplifies the process of interacting with APIs by providing a clean and type-safe interface for making HTTP requests. With Retrofit, you can handle everything from basic API calls to more complex tasks such as authentication, error handling, and request/response transformation.



********************************************************************************

Q). what is retrofit how to use ,why use where use ?

********************************************************************************