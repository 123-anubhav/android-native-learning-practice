Creating a media player app in Android involves several steps, from setting up the project to implementing playback controls for audio and video. Here’s a step-by-step guide to help you build a basic media player app.

### 1. **Set Up the Project**

Start by creating a new Android project in Android Studio:

1. Open Android Studio.
2. Choose **File > New > New Project**.
3. Select **Empty Activity** and click **Next**.
4. Name your project (e.g., "MediaPlayerApp").
5. Set the minimum API level and click **Finish**.

### 2. **Add Required Permissions**

To play media files from the device's storage or the internet, you need to add certain permissions to your `AndroidManifest.xml`:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.mediaplayerapp">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.MediaPlayerApp">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

### 3. **Design the UI**

In your `activity_main.xml` layout file, design the user interface for your media player. This might include play, pause, stop buttons, and a `VideoView` for video playback.

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <VideoView
        android:id="@+id/video_view"
        android:layout_width="match_parent"
        android:layout_height="250dp" />

    <Button
        android:id="@+id/play_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Play" />

    <Button
        android:id="@+id/pause_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Pause" />

    <Button
        android:id="@+id/stop_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Stop" />
</LinearLayout>
```

### 4. **Implement Media Playback in Java/Kotlin**

In your `MainActivity.java` or `MainActivity.kt` file, set up the media player functionality:

**Java Example:**

```java
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video_view);
        Button playButton = findViewById(R.id.play_button);
        Button pauseButton = findViewById(R.id.pause_button);
        Button stopButton = findViewById(R.id.stop_button);

        // Set video URI (from local storage or internet)
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video));

        // Set up play button
        playButton.setOnClickListener(v -> {
            videoView.start();
        });

        // Set up pause button
        pauseButton.setOnClickListener(v -> {
            videoView.pause();
        });

        // Set up stop button
        stopButton.setOnClickListener(v -> {
            videoView.stopPlayback();
            videoView.resume(); // Reset video view
        });
    }
}
```

**Kotlin Example:**

```kotlin
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.video_view)
        val playButton: Button = findViewById(R.id.play_button)
        val pauseButton: Button = findViewById(R.id.pause_button)
        val stopButton: Button = findViewById(R.id.stop_button)

        // Set video URI (from local storage or internet)
        videoView.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.sample_video}"))

        // Set up play button
        playButton.setOnClickListener {
            videoView.start()
        }

        // Set up pause button
        pauseButton.setOnClickListener {
            videoView.pause()
        }

        // Set up stop button
        stopButton.setOnClickListener {
            videoView.stopPlayback()
            videoView.resume() // Reset video view
        }
    }
}
```

### 5. **Add Audio Playback (Optional)**

To play audio files, you can use the `MediaPlayer` class. Here’s how you might add it to the above example:

```java
mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);

playButton.setOnClickListener(v -> {
    if (!mediaPlayer.isPlaying()) {
        mediaPlayer.start();
    }
});

pauseButton.setOnClickListener(v -> {
    if (mediaPlayer.isPlaying()) {
        mediaPlayer.pause();
    }
});

stopButton.setOnClickListener(v -> {
    if (mediaPlayer.isPlaying()) {
        mediaPlayer.stop();
        mediaPlayer.prepareAsync(); // Prepare for replay
    }
});
```

### 6. **Handle Lifecycle Events**

Media playback should be paused or stopped during certain lifecycle events to prevent resource leaks:

```java
@Override
protected void onPause() {
    super.onPause();
    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
        mediaPlayer.pause();
    }
}

@Override
protected void onDestroy() {
    super.onDestroy();
    if (mediaPlayer != null) {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
```

### 7. **Test and Deploy**

Finally, test your media player on different devices and scenarios (e.g., network connectivity for streaming). Once satisfied, you can deploy it to the Google Play Store.

### Summary of What to Do:
1. **Set Up the Project:** Create a new Android project in Android Studio.
2. **Design the UI:** Add `VideoView` and control buttons (`Play`, `Pause`, `Stop`).
3. **Handle Media Playback:** Use `MediaPlayer` and `VideoView` for playing audio and video.
4. **Implement Click Events:** Handle play, pause, and stop actions using `setOnClickListener`.
5. **Lifecycle Management:** Manage resources in lifecycle methods like `onPause` and `onDestroy`.
6. **Testing:** Test thoroughly across different devices and conditions.

This guide covers the basics. You can expand it by adding features like playlists, streaming from the internet, and custom UI designs for the media player.