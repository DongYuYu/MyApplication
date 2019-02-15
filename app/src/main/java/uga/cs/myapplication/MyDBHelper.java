package uga.cs.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mydb";
    public static final String TABLE_NAME = "mytable";
    public static final String NAME = "name";
    public static final String PASSWRD = "password";
    public static final String EMAIL = "email";
    public static final String ID = "id";
    public static final int version = 1;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + PASSWRD + " TEXT,"
                + EMAIL + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String myquery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(myquery);
        onCreate(db);

    }
}
