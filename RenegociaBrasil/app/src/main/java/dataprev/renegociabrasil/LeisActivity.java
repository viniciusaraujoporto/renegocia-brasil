package dataprev.renegociabrasil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import dataprev.renegociabrasil.service.DetalheActivity;

public class LeisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leis);

        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());

        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        Button verCreditosLeiButton = (Button) findViewById(R.id.lei_button);
        verCreditosLeiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarCreditos();
            }
        });
    }

    private void listarCreditos(){
        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet2.add(v1e2);
        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet3.add(v1e3);



        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Valor Total da Dívida");
        barDataSet1.setColor(Color.rgb(255, 0, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Valor Parcelado à Vista");
        barDataSet2.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Valor Parcelado em 60 meses");
        barDataSet3.setColor(Color.rgb(0, 0, 255));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Divida Atual");

        return xAxis;
    }

    private ArrayList<String> getYAxisValues() {
        ArrayList<String> yAxis = new ArrayList<>();
        yAxis.add("Valor (R$)");

        return yAxis;
    }


}
