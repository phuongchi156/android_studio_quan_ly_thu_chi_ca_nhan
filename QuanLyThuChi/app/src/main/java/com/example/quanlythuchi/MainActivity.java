package com.example.quanlythuchi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database database;
    Button btnSignUp, btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(MainActivity.this);
        //database.QueryData("INSERT INTO User VALUES (null, 'Chi', '0097654321', '123')");
       // database.QueryData("INSERT INTO Thu VALUES (null, 'cho', '100', '12/06/2020')");
       // Cursor datat = database.GetData("SELECT * FROM Thu");
       // while (datat.moveToNext()){
       //     String hum = datat.getString(2);
         //   String him = datat.getString(3);
          //  Toast.makeText(MainActivity.this, hum, Toast.LENGTH_SHORT).show();
          //  Toast.makeText(MainActivity.this, him, Toast.LENGTH_SHORT).show();
       // }
        addControl();
        addEvent();
    }

    public void addControl(){
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

    }

    public void addEvent(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(MainActivity.this, SignIn.class);
                startActivity(signin);

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, SignUp.class);
                startActivity(signup);
            }
        });
    }
}
