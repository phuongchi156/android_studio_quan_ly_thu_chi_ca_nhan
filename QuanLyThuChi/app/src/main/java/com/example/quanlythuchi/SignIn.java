package com.example.quanlythuchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythuchi.model.User;

public class SignIn extends AppCompatActivity {

    Button btnSignIn;
    EditText edtPhone, edtPass;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        database = new Database(SignIn.this);
        addControl();
        addEvent();
        //SQLiteDatabase db = db.getI

    }
    public void addControl(){
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        edtPass = (EditText)findViewById(R.id.edtpass);
        edtPhone = (EditText)findViewById(R.id.edtphone);
    }
    public void addEvent(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                //Intent home = new Intent(SignIn.this, thu.class );
                // startActivity(home);
                // finish();
                Cursor dataphone = database.GetData("SELECT * FROM User");
                // Cursor datapass = database.GetData("SELECT Pass FROM NguoiDung");
                  while (dataphone.moveToNext()){
                      int id = dataphone.getInt(0);
                      String ten = dataphone.getString(1);
                    String phone = dataphone.getString(2);
                    String pass = dataphone.getString(3);
                    if (pass.equals(edtPass.getText().toString()) && phone.equals(edtPhone.getText().toString())){
                        Intent home = new Intent(SignIn.this, home.class );
                        Bundle bundle = new Bundle();
                        bundle.putInt("login",id);
                        bundle.putString("loginten", ten);
                        bundle.putString("sodienthoai", phone);
                        //bundle.putString("loginphone", phone);
                        home.putExtras(bundle);
                        startActivity(home);
                        finish();
                        Toast.makeText(SignIn.this, "đăng nhập thành công!! Chào mừng "+ten, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignIn.this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
                    }
                 }
                

                }
        });
    }
}
