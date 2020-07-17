package com.example.quanlythuchi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class bieudo extends AppCompatActivity implements OnChartValueSelectedListener {
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieudo);

        database = new Database(bieudo.this);
        Cursor dataThu = database.GetData("SELECT SoTien FROM Thu");
        float t = 0;
        Cursor datachi = database.GetData("SELECT SoTien FROM Chi");
        float c= 0;
        while (dataThu.moveToNext() && datachi.moveToNext() ){
            String tien = dataThu.getString(0);
            String tchi = datachi.getString(0);
            t =t+ Float.parseFloat(tien);
            c = c+ Float.parseFloat(tchi);
            // Toast.makeText(thu.this, tien, Toast.LENGTH_SHORT).show();

        }
        float kq = (float)t -c;
        //txttongthu.setText(""+t);

        PieChart mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Tình hình thu chi");
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);

        addDataSet(mChart,t,c,kq);

        mChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(bieudo.this, "Giá trị: " + e.getY(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {


    }

    private static void addDataSet(PieChart pieChart, float t, float c, float kq) {


        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = { t, c, kq };
        String[] xData = { "Thu", "Chi", "Số dư" };

        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"Thu Chi");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();

        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}