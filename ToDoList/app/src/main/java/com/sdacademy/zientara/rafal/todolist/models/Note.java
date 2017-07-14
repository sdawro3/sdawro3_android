package com.sdacademy.zientara.rafal.todolist.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Evil on 13.07.2017.
 */

public class Note implements Parcelable {
    private String name;
    private String url;

    public Note() {
    }

    public Note(String name) {
        this.name = name;
    }

    protected Note(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
