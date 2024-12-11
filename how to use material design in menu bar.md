To create a menu bar with three buttons (Home, About, and Cart) using Material Design in a native Android application, you can use a `BottomNavigationView`. Below is the step-by-step guide and the code to implement this:

### Step 1: Add the Material Components dependency
Ensure that you have the Material Components library in your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.google.android.material:material:1.9.0'
}
```

### Step 2: Update the XML Layout File
Create or update your `activity_main.xml` layout file with a `BottomNavigationView`.

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- Your content layout -->
    <FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@id/bottom_navigation" />

    <!-- Bottom Navigation View -->
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:menu="@menu/bottom_nav_menu"
        app:labelVisibilityMode="labeled"
        app:itemIconTint="@color/bottom_nav_item_color"
        app:itemTextColor="@color/bottom_nav_item_color" />

</RelativeLayout>
```

### Step 3: Create the Bottom Navigation Menu
Create a new XML file in the `res/menu` directory named `bottom_nav_menu.xml`.

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/ic_home"
        android:title="Home" />
    <item
        android:id="@+id/navigation_about"
        android:icon="@drawable/ic_info"
        android:title="About" />
    <item
        android:id="@+id/navigation_cart"
        android:icon="@drawable/ic_cart"
        android:title="Cart" />
</menu>
```

### Step 4: Handle Menu Item Selection in MainActivity
In your `MainActivity.java`, handle the navigation item selection to switch between fragments or update the UI.

```java
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.navigation_about:
                        selectedFragment = new AboutFragment();
                        break;
                    case R.id.navigation_cart:
                        selectedFragment = new CartFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                }
                return true;
            }
        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
}
```

### Step 5: Create Fragments
Create the `HomeFragment`, `AboutFragment`, and `CartFragment` classes to manage the content for each tab.

Example for `HomeFragment.java`:
```java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
```

Create similar classes for `AboutFragment` and `CartFragment`.

### Step 6: Create Layouts for Fragments
Create XML layout files for each fragment in the `res/layout` directory, such as `fragment_home.xml`, `fragment_about.xml`, and `fragment_cart.xml`.

Example for `fragment_home.xml`:
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Fragment"
        android:textSize="24sp" />
</LinearLayout>
```

Repeat for the `AboutFragment` and `CartFragment` layouts.

### Step 7: Add Icons
Add icons for `Home`, `About`, and `Cart` in the `res/drawable` directory. You can use vector assets provided by Android Studio.

This setup will create a menu bar with three buttons (Home, About, and Cart) using Material Design. Each button will load a different fragment when selected.