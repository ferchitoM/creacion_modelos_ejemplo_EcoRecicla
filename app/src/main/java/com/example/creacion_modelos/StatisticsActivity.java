package com.example.creacion_modelos;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.models.Material;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.slider.LabelFormatter;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private BarChart grafico;
    private BarChart grafico2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        grafico = findViewById(R.id.grafico);
        grafico2 = findViewById(R.id.grafico2);

        grafico.setDrawBarShadow(false); //Muestra la sombra de las barras
        grafico.setDrawValueAboveBar(true); //Muestra el valor encima de la barra
        grafico.getDescription().setEnabled(false); //Muestra la descripcion de la grafica
        grafico.setMaxVisibleValueCount(60); //Muestra solo hasta 60 barras
        grafico.setPinchZoom(true); //No se puede hacer zoom
        grafico.setDrawGridBackground(false); //No se muestra el fondo de la grafica


        grafico2.setDrawBarShadow(false); //Muestra la sombra de las barras
        grafico2.setDrawValueAboveBar(true); //Muestra el valor encima de la barra
        grafico2.getDescription().setEnabled(false); //Muestra la descripcion de la grafica
        grafico2.setMaxVisibleValueCount(60); //Muestra solo hasta 60 barras
        grafico2.setPinchZoom(true); //No se puede hacer zoom
        grafico2.setDrawGridBackground(false);

        Legend lenged = grafico.getLegend(); //Muestra la leyenda de la grafica
        lenged.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); //Alineacion vertical de la leyenda
        lenged.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); //Alineacion horizontal de la leyenda
        lenged.setOrientation(Legend.LegendOrientation.HORIZONTAL); //Orientacion de la leyenda
        lenged.setDrawInside(false); //No se muestra dentro de la grafica
        lenged.setForm(Legend.LegendForm.SQUARE); //Forma de la leyenda
        lenged.setFormSize(9f); //Tamaño de la forma de la leyenda
        lenged.setTextSize(14f); //Tamaño del texto de la leyenda
        lenged.setXEntrySpace(4f); //Espacio entre las barras

        Legend lenged2 = grafico2.getLegend(); //Muestra la leyenda de la grafica
        lenged2.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); //Alineacion vertical de la leyenda
        lenged2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); //Alineacion horizontal de la leyenda
        lenged2.setOrientation(Legend.LegendOrientation.HORIZONTAL); //Orientacion de la leyenda
        lenged2.setDrawInside(false); //No se muestra dentro de la grafica
        lenged2.setForm(Legend.LegendForm.SQUARE); //Forma de la leyenda
        lenged2.setFormSize(9f); //Tamaño de la forma de la leyenda
        lenged2.setTextSize(14f); //Tamaño del texto de la leyenda
        lenged2.setXEntrySpace(4f); //Espacio entre las

        graficarPesos();
        graficarGanancias();
    }

    private void graficarPesos() {

        User user = (User) getApplicationContext();
        user.calculateWeigthStats(); //Calcular las estadisticas de lo reciclajes del usuario
        Log.e("msg", "Stats: " + user.weigthStats);

        ArrayList<BarEntry> barras = new ArrayList<>();

        int count = 0;
        for (float promedio : user.weigthStats) {
            barras.add(new BarEntry(count, promedio));
            count++;
        }

        BarDataSet adapter = new BarDataSet(barras, "Estadísticas Julio 2024");
        adapter.setDrawIcons(false); //Mostrar iconos

        //adapter.setColors(new int[] { R.color.black, R.color.verde_claro, R.color.verde_transparente }, getApplicationContext());

        /*
        int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
        int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
        int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
        int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

        List<GradientColor> gradientFills = new ArrayList<>();
        gradientFills.add(new GradientColor(startColor1, endColor1));
        gradientFills.add(new GradientColor(startColor2, endColor2));
        gradientFills.add(new GradientColor(startColor3, endColor3));
        gradientFills.add(new GradientColor(startColor4, endColor4));
        gradientFills.add(new GradientColor(startColor5, endColor5));
        adapter.setGradientColors(gradientFills);*/

        adapter.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData config = new BarData(adapter); //Crear los datos de la grafica
        config.setBarWidth(0.9f); //Ancho de las barras
        config.setValueTextSize(14f); //Tamaño del texto de los valores

        grafico.setData(config); //Asignar los datos a la grafica
        grafico.setFitBars(false); //Ajuste automatico de las barras
        grafico.invalidate(); //Actualizar la grafica

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            ArrayList<Material> materialList = Recycling.getBaseMaterials();

            @Override
            public String getFormattedValue(float index, AxisBase axis) {
                return materialList.get((int) index).name;
            }
        };

        //Eje X
        String[] labelsEjeX = new String[Recycling.getBaseMaterials().size()];

        for (int i = 0; i < Recycling.getBaseMaterials().size(); i++) {
            labelsEjeX[i] = Recycling.getBaseMaterials().get(i).name;

            Log.e("msg", "Label eje x: " + labelsEjeX[i]);
        }

        XAxis ejeX = grafico.getXAxis(); //Obtenemos el eje x del gráfico

        ejeX.setGranularity(1f);
        ejeX.setGranularityEnabled(true);
        ejeX.setCenterAxisLabels(false); //Centra los labels
        ejeX.setDrawGridLines(false); //No se muestra la grilla de las barras
        ejeX.setPosition(XAxis.XAxisPosition.BOTTOM);
        ejeX.setLabelCount(labelsEjeX.length - 1);
        ejeX.setTextSize(12f);
        //ejeX.setLabelRotationAngle(-30f);

        ejeX.setValueFormatter(new IndexAxisValueFormatter(labelsEjeX));

        //EjeY
        grafico.getAxisRight().setEnabled(false); //No se muestra el eje derecho de la grafica
        YAxis ejeY = grafico.getAxisLeft(); //Obtenemos el eje Y del gráfico
        ejeY.setValueFormatter(new LargeValueFormatter()); //Formato de los valores del eje Y
        ejeY.setDrawGridLines(true); //Se muestra la grilla de las barras
        ejeY.setSpaceTop(10f); //Espacio entre la linea y el eje
        ejeY.setAxisMinimum(0f); //Valor minimo del eje Y

    }

    private void graficarGanancias() {

        User user = (User) getApplicationContext();
        user.calculateGainStats(); //Calcular las estadisticas de lo reciclajes del usuario
        Log.e("msg", "Stats: " + user.gainStats);

        ArrayList<BarEntry> barras = new ArrayList<>();

        int count = 0;
        for (float promedio : user.gainStats) {
            barras.add(new BarEntry(count, promedio));
            count++;
        }

        BarDataSet adapter = new BarDataSet(barras, "Estadísticas Julio 2024");
        adapter.setDrawIcons(false); //Mostrar iconos

        //adapter.setColors(new int[] { R.color.black, R.color.verde_claro, R.color.verde_transparente }, getApplicationContext());

        /*
        int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
        int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
        int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
        int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
        int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
        int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
        int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);

        List<GradientColor> gradientFills = new ArrayList<>();
        gradientFills.add(new GradientColor(startColor1, endColor1));
        gradientFills.add(new GradientColor(startColor2, endColor2));
        gradientFills.add(new GradientColor(startColor3, endColor3));
        gradientFills.add(new GradientColor(startColor4, endColor4));
        gradientFills.add(new GradientColor(startColor5, endColor5));
        adapter.setGradientColors(gradientFills);*/

        adapter.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData config = new BarData(adapter); //Crear los datos de la grafica
        config.setBarWidth(0.9f); //Ancho de las barras
        config.setValueTextSize(14f); //Tamaño del texto de los valores

        grafico2.setData(config); //Asignar los datos a la grafica
        grafico2.setFitBars(false); //Ajuste automatico de las barras
        grafico2.invalidate(); //Actualizar la grafica

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            ArrayList<Material> materialList = Recycling.getBaseMaterials();

            @Override
            public String getFormattedValue(float index, AxisBase axis) {
                return materialList.get((int) index).name;
            }
        };

        //Eje X
        String[] labelsEjeX = new String[Recycling.getBaseMaterials().size()];

        for (int i = 0; i < Recycling.getBaseMaterials().size(); i++) {
            labelsEjeX[i] = Recycling.getBaseMaterials().get(i).name;

            Log.e("msg", "Label eje x: " + labelsEjeX[i]);
        }

        XAxis ejeX = grafico2.getXAxis(); //Obtenemos el eje x del gráfico

        ejeX.setGranularity(1f);
        ejeX.setGranularityEnabled(true);
        ejeX.setCenterAxisLabels(false); //Centra los labels
        ejeX.setDrawGridLines(false); //No se muestra la grilla de las barras
        ejeX.setPosition(XAxis.XAxisPosition.BOTTOM);
        ejeX.setLabelCount(labelsEjeX.length - 1);
        ejeX.setTextSize(12f);
        //ejeX.setLabelRotationAngle(-30f);

        ejeX.setValueFormatter(new IndexAxisValueFormatter(labelsEjeX));

        //EjeY
        grafico2.getAxisRight().setEnabled(false); //No se muestra el eje derecho de la grafica
        YAxis ejeY = grafico2.getAxisLeft(); //Obtenemos el eje Y del gráfico
        ejeY.setValueFormatter(new LargeValueFormatter()); //Formato de los valores del eje Y
        ejeY.setDrawGridLines(true); //Se muestra la grilla de las barras
        ejeY.setSpaceTop(10f); //Espacio entre la linea y el eje
        ejeY.setAxisMinimum(0f); //Valor minimo del eje Y

    }
}