package com.developer.nikhil.fillbelly.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.developer.nikhil.fillbelly.R;
import com.developer.nikhil.fillbelly.activity.BaseActivity;
import com.developer.nikhil.fillbelly.common.Preferences;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.Random;


public class GraphActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(generateData());
        /*LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
        new DataPoint[] {
                new DataPoint(0, 1),
//                new DataPoint(1, 5)
        });*/
        Log.e("Graph", "::>> series" + series);
        graph.addSeries(series);
        series.setAnimated(true);
//        graph.addSeries(new LineGraphSeries(generateData()));

    }

    private DataPoint[] generateData() {
        int count = 0;
        try {
            count = Preferences.getGraphlist().size();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double y = 0;

        DataPoint[] values = new DataPoint[count];
        try {
            for (int i = 0; i < Preferences.getGraphlist().size(); i++) {
                double x = i+1;
                y = Double.parseDouble(Preferences.getGraphlist().get(i).getY());
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}
