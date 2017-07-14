package com.sdacademy.zientara.rafal.todolist.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sdacademy.zientara.rafal.todolist.R;
import com.sdacademy.zientara.rafal.todolist.eventBusMessages.SaveNoteMessage;
import com.sdacademy.zientara.rafal.todolist.models.Note;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Evil on 14.07.2017.
 */

public class EditNoteDialog extends DialogFragment {
    private static final String NOTE_INDEX_KEY = "note_index";
    private static final String NOTE_KEY = "note";
    @BindView(R.id.editNote_nameEditText)
    EditText nameEditText;

    @BindView(R.id.editNote_saveButton)
    Button saveButton;
    private Note note;
    private int noteIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_note, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static EditNoteDialog newInstance(Note note, int position) {
        EditNoteDialog dialog = new EditNoteDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NOTE_KEY, note);
        bundle.putInt(NOTE_INDEX_KEY, position);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            note = arguments.getParcelable(NOTE_KEY);
            noteIndex = arguments.getInt(NOTE_INDEX_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateView();
    }

    private void updateView() {
        if (note != null) {
            nameEditText.setText(note.getName());
        } else
            dismiss();
    }

    @OnClick(R.id.editNote_saveButton)
    public void onSaveButtonClicked() {
        note.setName(nameEditText.getText().toString().trim());
        EventBus.getDefault().post(new SaveNoteMessage(note, noteIndex));
        dismiss();
    }
}
