package com.sdaacademy.zientara.rafal.rotatelist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends BaseActivity {

    private ListView listView;
    private String[] androidVersions;
    private ArrayList<String> stringList;
    private Random random;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        androidVersions = getResources().getStringArray(R.array.wersje_androida);
        stringList = new ArrayList<>();
        random = new Random();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);
    }

    public void randomVersion(View view) {
        String randomString = getRandomVersion();
        stringList.add(randomString);
        refreshList();
    }

    private void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private String getRandomVersion() {
        int randomIndex = random.nextInt(androidVersions.length);
        return androidVersions[randomIndex];
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("lista", stringList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> savedList = savedInstanceState.getStringArrayList("lista");
        if (savedList != null)
            stringList.addAll(savedList);
        refreshList();
    }
}
