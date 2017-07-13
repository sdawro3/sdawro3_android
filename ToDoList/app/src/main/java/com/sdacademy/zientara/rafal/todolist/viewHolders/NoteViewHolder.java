package com.sdacademy.zientara.rafal.todolist.viewHolders;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 13.07.2017.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.noteItem_noteName)
    public
    TextView nameTextView;

    public NoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
