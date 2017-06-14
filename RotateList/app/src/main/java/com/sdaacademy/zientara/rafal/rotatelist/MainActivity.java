package com.sdaacademy.zientara.rafal.rotatelist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.rotatelist.models.User;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends BaseActivity {

    private ListView listView;
    private String[] androidVersions;
    private ArrayList<String> stringList;
    private Random random;
    private ArrayAdapter adapter;
    private Button button;
    private TextView emptyListTextView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        emptyListTextView = (TextView) findViewById(R.id.emptyListText);
        listView = (ListView) findViewById(R.id.listView);
        androidVersions = getResources().getStringArray(R.array.wersje_androida);
        stringList = new ArrayList<>();
        random = new Random();
        initUser();
        initAdapter();
        refreshList();
    }

    private void initUser() {
        user = new User();
        user.setName(androidVersions[0]);
    }

    private void initAdapter() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);
    }

    public void randomVersion(View view) {
        String randomString = getRandomVersion();
        user.setName(randomString);
        stringList.add(randomString);
        refreshList();
    }

    private void refreshList() {
        adapter.notifyDataSetChanged();
        if (adapter.getCount() == 0)
            showEmptyText();
        else
            showListView();

        Toast.makeText(this, "Siema jestem " + user.getName(), Toast.LENGTH_SHORT).show();
    }

    private void showEmptyText() {
        emptyListTextView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    private void showListView() {
        emptyListTextView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    private String getRandomVersion() {
        int randomIndex = random.nextInt(androidVersions.length);
        return androidVersions[randomIndex];
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("lista", stringList);
        outState.putParcelable("koles", user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ArrayList<String> savedList = savedInstanceState.getStringArrayList("lista");
        if (savedList != null)
            stringList.addAll(savedList);

        user = savedInstanceState.getParcelable("koles");
        refreshList();
    }
}
