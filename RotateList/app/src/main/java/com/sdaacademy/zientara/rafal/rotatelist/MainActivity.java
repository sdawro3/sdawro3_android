package com.sdaacademy.zientara.rafal.rotatelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        String[] stringArray = getResources().getStringArray(R.array.wersje_androida);
        //String[] stringList = new String[] {"jeden", "dwa"};
        List<String> stringList = new ArrayList<>();//new String[] {"jeden", "dwa"};

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);

        listView.setAdapter(adapter);
    }
}
