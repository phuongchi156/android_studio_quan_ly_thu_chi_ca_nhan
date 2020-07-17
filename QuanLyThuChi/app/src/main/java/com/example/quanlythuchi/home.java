package com.example.quanlythuchi;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class home extends AppCompatActivity {

    ImageButton btnThu, btnChi, btnBieudo, btnMuctieu;
    TextView txtten, txtphone;
    //private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Báo Cáo");
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String value1 = bundle.getString("loginten", "");
            int value2 = bundle.getInt("login", 0);
            //String sdt = bundle.getString("sodienthoai", "");
            Toast.makeText(home.this,"Chào mừng "+value1+" đăng nhập thành công", Toast.LENGTH_SHORT).show();
        }

        addControl();
        addEvent();
        //addten();



       // FloatingActionButton fab = findViewById(R.id.fab);
      //  fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
          //  public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //               .setAction("Action", null).show();
         //  }
       // });
       // DrawerLayout drawer = findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        //        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.addDrawerListener(toggle);
       // toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
       // View header = navigationView.getHeaderView(0);
       // navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) home.this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       // mAppBarConfiguration = new AppBarConfiguration.Builder(
            //    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
              //  .setDrawerLayout(drawer)
               // .build();
    }

 //   @SuppressLint("WrongViewCast")
    public void addControl(){

        btnThu = (ImageButton) findViewById(R.id.btnthu);
        btnChi = (ImageButton) findViewById(R.id.btnchi);
        btnBieudo = (ImageButton) findViewById(R.id.btnbieudo);
        btnMuctieu = (ImageButton) findViewById(R.id.btnmuctieu);
        txtten = (TextView)findViewById(R.id.txttenuser);

    }
    public void addEvent(){
        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thu = new Intent(home.this, thu.class);
                startActivity(thu);
                finish();
            }
        });

        btnChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chi = new Intent(home.this, chi.class);
                startActivity(chi);
                finish();
            }
        });

        btnBieudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bieudo = new Intent(home.this, bieudo.class);
                startActivity(bieudo);
                finish();
            }
        });
    }

    public void addten(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String value1 = bundle.getString("loginten", "");
            int value2 = bundle.getInt("login", 0);
            String sdt = bundle.getString("sodienthoai", "");
            Toast.makeText(home.this,value1, Toast.LENGTH_SHORT).show();

            txtphone.setText(sdt);
            //txtten.setText(value1);
            //boolean value3 = bundle.getBoolean("Key_3", false);
        }
    }



}
