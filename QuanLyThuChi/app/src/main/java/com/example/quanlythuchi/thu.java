package com.example.quanlythuchi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlythuchi.model.Thu;
import com.example.quanlythuchi.model.ThuAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class thu extends AppCompatActivity {

    Database database;
    TabHost tabHost;
    private ListView lvThu;
    private ThuAdapter adapter;
    private ArrayList<Thu> arrThu;
    EditText edtsotien, edtnoidung;
    ImageButton btnDate, btnTime, btnsave, btnxoa, btnsua, btnback;
    TextView txtdate, txttime, txttongthu, txtngay, txtTen, txtsotien;
    Button  btnxem;
    Calendar calendarDate = Calendar.getInstance();
    Calendar calendarTime = Calendar.getInstance();
    SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu);
       // database.QueryData("CREATE TABLE IF NOT EXISTS thu( Id INTEGER PRIMARY KEY AUTOINCREMENT, Tenkhoanthu VARCHAR(50), sotien VARCHAR (20), thoigian VARCHAR (11)");

        database = new Database(thu.this);
        addControl();
        addEvent();

    }
    public void addControl(){
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec
        spec = tabHost.newTabSpec("t1");
        spec.setContent(R.id.themmoi);
        spec.setIndicator("Thêm mới");
        tabHost.addTab(spec);


        TabHost.TabSpec
        spec2 = tabHost.newTabSpec("t2");
        spec2.setContent(R.id.dathu);
        spec2.setIndicator("Đã Thu");
        tabHost.addTab(spec2);

        txtdate = (TextView)findViewById(R.id.txtdate);
        txttime = (TextView)findViewById(R.id.txttime);
        txtngay = (TextView)findViewById(R.id.txtthang);
        txttongthu = (TextView)findViewById(R.id.txttongthu);
        btnTime = (ImageButton)findViewById(R.id.btntime);
        btnDate = (ImageButton)findViewById(R.id.btndate);
        txtTen = (TextView)findViewById(R.id.txttenkhoanthu);
        txtsotien = (TextView)findViewById(R.id.txtsotien);
        lvThu = (ListView)findViewById(R.id.lvthu);
        arrThu = new ArrayList<>();
        adapter = new ThuAdapter(thu.this, R.layout.item, arrThu);
        lvThu.setAdapter(adapter);
        btnxem = (Button)findViewById(R.id.btnxem);
        btnsave = (ImageButton)findViewById(R.id.btnsave);
        edtnoidung = (EditText)findViewById(R.id.edtdiengiai);
        edtsotien = (EditText)findViewById(R.id.edtsotien);
        btnback = (ImageButton)findViewById(R.id.btnback);
       // btnsua = (ImageButton)findViewById(R.id.btnsua);
    }
    public void addEvent(){


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarDate.set(Calendar.YEAR,year);
                        calendarDate.set(Calendar.MONTH,month);
                        calendarDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        txtdate.setText(sdfDate.format(calendarDate.getTime()));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(thu.this,callback, calendarDate.get(Calendar.YEAR), calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendarTime.set(Calendar.HOUR,hourOfDay);
                        calendarTime.set(Calendar.MINUTE,minute);
                        txttime.setText(sdfTime.format(calendarTime.getTime()));
                    }
                };
                TimePickerDialog dialog = new TimePickerDialog(thu.this, callback, calendarTime.get(Calendar.HOUR), calendarTime.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtnoidung.getText().toString();
                String tien = edtsotien.getText().toString();
                String thoigian = txtdate.getText().toString();
                if(ten.equals("") || tien.equals("")){
                    Toast.makeText(thu.this," vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.QueryData("INSERT INTO Thu VALUES (null, '"+ ten +"', '"+ tien +"', '"+ thoigian +"')");
                    Toast.makeText(thu.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                    edtnoidung.setText("");
                    edtsotien.setText("");
                    txtdate.setText("");
                    txttime.setText("");
                }
            }
        });
        btnxem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                capnhat();
                //Toast.makeText(thu.this, "đã chạy được đến đây", Toast.LENGTH_SHORT).show();
            }
        });
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Cursor dataThu = database.GetData("SELECT SoTien FROM Thu");
                float t = 0;
                while (dataThu.moveToNext() ){
                    String tien = dataThu.getString(0);
                    t =t+ Float.parseFloat(tien);
                    // Toast.makeText(thu.this, tien, Toast.LENGTH_SHORT).show();

                }
                txttongthu.setText(""+t);

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(thu.this, home.class );
                startActivity(home);
                finish();
            }
        });

    }

    public void capnhat(){

        Cursor dataTh = database.GetData("SELECT * FROM Thu");
        arrThu.clear();
        while (dataTh.moveToNext() ){
            String ten = dataTh.getString(1);
            String tien = dataTh.getString(2);
            String ngay = dataTh.getString(3);
            int id = dataTh.getInt(0);
            arrThu.add(new Thu(id, ten, tien, ngay));
           // arrThu.add(new Thu(ten,tien));
            //arrThu.add(new Thu("humm", "100"));
           // Toast.makeText(thu.this, ten, Toast.LENGTH_SHORT).show();
           // Toast.makeText(thu.this, tien, Toast.LENGTH_SHORT).show();

        }
        adapter.notifyDataSetChanged();
    }


    public void DialogXoa(String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(thu.this);
        dialogXoa.setMessage("Bạn có chắc muốn xóa" +ten+ "?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                database.QueryData("DELETE FROM Thu WHERE Id = '" + id + " '");
                Toast.makeText(thu.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                capnhat();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

    public void DialogSua(final String ten, String tien, final int id){
        final Dialog dialog = new Dialog(thu.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua);

        final EditText edtsuaten = (EditText) dialog.findViewById(R.id.edtsuaten);
        final EditText edtsuatien = (EditText) dialog.findViewById(R.id.edtsuatien);
        Button btnluu = (Button) dialog.findViewById(R.id.btnluu);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);
        edtsuaten.setText(ten);
        edtsuatien.setText(tien);

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = edtsuaten.getText().toString().trim();
                String tienmoi = edtsuatien.getText().toString().trim();
                database.QueryData("UPDATE Thu SET TenKhoanThu = '"+ tenmoi + " ', SoTien ='"+ tienmoi + " '  WHERE Id = ' " + id+ "' ");
                Toast.makeText(thu.this, "Đã sửa thành công ", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                capnhat();

            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
}
