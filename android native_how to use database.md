Q. how to use database in android like a crud

ANSWER:

To use a database in native Android development, 
you typically work with **SQLite** or **Room** (which is built on top of SQLite and provides a more modern, object-oriented interface).
 Below are the steps to use both methods:

### 1. Using SQLite Directly

#### Step 1: Create a Database Helper Class

Create a class that extends `SQLiteOpenHelper`, which helps in managing database creation and version management.

```java
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "example.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data
    public long addUser(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        return db.insert(TABLE_NAME, null, values);
    }

    // Retrieve data
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Update data
    public int updateUser(int id, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Delete data
    public int deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
```

#### Step 2: Using the Database Helper

You can now use this helper class in your activities or fragments to perform database operations.

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);

// Insert a user
long id = dbHelper.addUser("John Doe", 25);

// Retrieve all users
Cursor cursor = dbHelper.getAllUsers();
if (cursor.moveToFirst()) {
    do {
        int userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String userName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int userAge = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
        // Use the data
    } while (cursor.moveToNext());
}
cursor.close();

// Update a user
dbHelper.updateUser(id, "John Smith", 30);

// Delete a user
dbHelper.deleteUser(id);
```

### 2. Using Room (Recommended)

Room is a part of Android's Jetpack suite and provides an abstraction layer over SQLite, making database interactions more efficient and easy to maintain.

#### Step 1: Add Dependencies

Add the Room dependencies in your `build.gradle` file:

```groovy
dependencies {
    implementation 'androidx.room:room-runtime:2.5.0'
    annotationProcessor 'androidx.room:room-compiler:2.5.0'
    // or for Kotlin
    kapt 'androidx.room:room-compiler:2.5.0'
}
```

#### Step 2: Define an Entity

An entity represents a table in the database.

```java
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public int age;
}
```

#### Step 3: Create a DAO (Data Access Object)

A DAO provides the methods that your app uses to interact with the data in your database.

```java
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
```

#### Step 4: Create the Database

The database class should extend `RoomDatabase` and serve as the main access point for the underlying SQLite database.

```java
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
```

#### Step 5: Using Room in Your Activity

Now, you can create an instance of your database and perform operations.

```java
AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "example.db").build();

UserDao userDao = db.userDao();

// Insert a user
User user = new User();
user.name = "John Doe";
user.age = 25;
userDao.insert(user);

// Retrieve all users
List<User> users = userDao.getAllUsers();
for (User u : users) {
    // Use the data
}

// Update a user
user.name = "John Smith";
userDao.update(user);

// Delete a user
userDao.delete(user);
```

### Why Use Room over SQLite Directly?

- **Type Safety:** Room uses compile-time validation of SQL queries, ensuring that your queries are correct.
- **Less Boilerplate Code:** Room abstracts a lot of boilerplate code that you would typically write with SQLite.
- **LiveData and Flow Support:** Room integrates with LiveData and Kotlin Flow, making it easier to work with asynchronous data streams.
- **Migrations:** Room provides an easier way to handle database migrations compared to SQLite.

### Conclusion

- **Use SQLite** when you need low-level control over your database and are comfortable managing raw SQL queries and database schemas.
- **Use Room** when you want a more modern, object-oriented approach with less boilerplate code and better type safety. 
Room is generally recommended for most Android apps due to its simplicity and robustness.



*******************************************************************