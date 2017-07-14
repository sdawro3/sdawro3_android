package com.sdacademy.zientara.rafal.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.sdacademy.zientara.rafal.todolist.adapters.NotesRecyclerAdapter;
import com.sdacademy.zientara.rafal.todolist.dialogs.EditNoteDialog;
import com.sdacademy.zientara.rafal.todolist.eventBusMessages.SaveNoteMessage;
import com.sdacademy.zientara.rafal.todolist.models.Note;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteClicked {

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

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void prepareNotesData() {
        noteList = new ArrayList<>();
        noteList.add(new Note("First note"));
    }

    private void prepareRecylerAdapter() {
        adapter = new NotesRecyclerAdapter(noteList, this, this);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteClicked(int position) {
        noteList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onUpClicked(int position) {
        if (position > 0) {
            Note note = noteList.get(position);
            noteList.remove(position);
            noteList.add(position - 1, note);
            adapter.notifyItemMoved(position, position - 1);
            linearLayoutManager.scrollToPosition(position - 1);
        }
    }

    @Override
    public void onDownClicked(int position) {
        if (position < noteList.size() - 1) {
            Note note = noteList.get(position);
            noteList.remove(position);
            noteList.add(position + 1, note);
            adapter.notifyItemMoved(position, position + 1);
            linearLayoutManager.scrollToPosition(position + 1);
        }
    }

    @Override
    public void onEditClicked(int position) {
        Note note = noteList.get(position);
        //// TODO: 14.07.2017 edit note!
        EditNoteDialog dialog = EditNoteDialog.newInstance(note, position);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void saveNoteFromDialog(SaveNoteMessage message) {
        int position = message.getPosition();
        Note note = message.getNote();
        //// TODO: 14.07.2017 update list and notify adapter
        noteList.remove(position);
        noteList.add(position, note);
        adapter.notifyItemChanged(position);
    }
}
