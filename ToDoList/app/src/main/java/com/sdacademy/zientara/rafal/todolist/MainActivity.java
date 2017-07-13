package com.sdacademy.zientara.rafal.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.sdacademy.zientara.rafal.todolist.adapters.NotesRecyclerAdapter;
import com.sdacademy.zientara.rafal.todolist.models.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_noteEditText)
    EditText noteEditText;

    @BindView(R.id.main_addButton)
    AppCompatButton addButton;

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    List<Note> noteList;
    private NotesRecyclerAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareNotesData();
        prepareRecylerAdapter();
    }

    private void prepareNotesData() {
        noteList = new ArrayList<>();
        noteList.add(new Note("First note"));
    }

    private void prepareRecylerAdapter() {
        adapter = new NotesRecyclerAdapter(noteList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.main_addButton)
    public void addNote() {
        Note note = new Note();
        note.setName(noteEditText.getText().toString().trim());
        noteList.add(0, note);
        adapter.notifyItemInserted(0);
        linearLayoutManager.scrollToPosition(0);
    }
}
