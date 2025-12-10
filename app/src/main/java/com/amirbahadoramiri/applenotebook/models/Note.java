package com.amirbahadoramiri.applenotebook.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    Long id;
    String title, note;
    Long date;

    public Note(String title, String note, Long date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.date);
        dest.writeString(this.title);
        dest.writeString(this.note);
    }

    protected Note(Parcel in) {
        this.id = in.readLong();
        this.date = in.readLong();
        this.title = in.readString();
        this.note = in.readString();
    }

    // *** این متد برای ساختن آرایه از این آبجکت هست (به ندرت استفاده می‌شه) ***
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

}
