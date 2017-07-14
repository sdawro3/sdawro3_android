package com.sdacademy.zientara.rafal.todolist.eventBusMessages;

import com.sdacademy.zientara.rafal.todolist.models.Note;

/**
 * Created by Evil on 14.07.2017.
 */

public class SaveNoteMessage {
    private Note note;
    private int position;

    public SaveNoteMessage(Note note, int position) {
        this.note = note;
        this.position = position;
    }

    public Note getNote() {
        return note;
    }

    public int getPosition() {
        return position;
    }
}
