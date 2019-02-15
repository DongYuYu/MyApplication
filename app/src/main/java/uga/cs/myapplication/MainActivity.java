package uga.cs.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button saveButton;
Button showButton;
EditText name;
EditText password;
EditText email;
MyDBHelper myDBHelper;
SQLiteDatabase sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.saveButtn);
        showButton = findViewById(R.id.showButton);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        myDBHelper = new MyDBHelper(this);

        sqlDb = myDBHelper.getWritableDatabase();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(myDBHelper.NAME, name.getText().toString());
                values.put(myDBHelper.PASSWRD, password.getText().toString());
                values.put(myDBHelper.EMAIL, email.getText().toString());

                sqlDb.insert(myDBHelper.TABLE_NAME, null, values);
                name.setText("");
                password.setText("");
                email.setText("");
            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = sqlDb.rawQuery("SELECT * FROM " + myDBHelper.TABLE_NAME, null);
                cursor.moveToFirst();
                do {

                    String name = cursor.getString(cursor.getColumnIndex(myDBHelper.NAME));
                    String password = cursor.getString(cursor.getColumnIndex(myDBHelper.PASSWRD));

                    String email = cursor.getString(cursor.getColumnIndex(myDBHelper.EMAIL));
                    Toast.makeText(MainActivity.this, name + password + email, Toast.LENGTH_LONG).show();
                } while(cursor.moveToNext());

            }
        });
    }
}
