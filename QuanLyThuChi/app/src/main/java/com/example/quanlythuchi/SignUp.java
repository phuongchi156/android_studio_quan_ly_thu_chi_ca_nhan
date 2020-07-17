package com.example.quanlythuchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button btnsignUp;
    EditText edtten, edtpass, edtphone;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = new Database(SignUp.this);

        addControl();
        addEvent();
    }

    public void addControl(){
        btnsignUp = (Button)findViewById(R.id.btnSignUp);
        edtpass = (EditText)findViewById(R.id.edtpass);
        edtphone = (EditText)findViewById(R.id.edtphone);
        edtten = (EditText)findViewById(R.id.edtname);
    }

    public void addEvent(){

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtten.getText().toString();
                String phone = edtphone.getText().toString();
                String pass = edtpass.getText().toString();
                if(ten.equals("") || phone.equals("")|| pass.equals("")){
                    Toast.makeText(SignUp.this," vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.QueryData("INSERT INTO User VALUES (null, '"+ ten +"', '"+ phone +"', '"+ pass +"')");
                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    //edtten.setText("");
                    //edtpass.setText("");
                    //edtphone.setText("");

                    Intent home = new Intent(SignUp.this, home.class );
                    startActivity(home);
                    finish();
                }
            }
        });

    }
}
