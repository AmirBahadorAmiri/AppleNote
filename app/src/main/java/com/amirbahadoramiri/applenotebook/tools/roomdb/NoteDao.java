package com.amirbahadoramiri.applenotebook.tools.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.amirbahadoramiri.applenotebook.models.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NoteDao {

    @Insert()
    Single<Long> insert(Note note);

    @Delete
    Completable delete(Note note);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(Note note);

    @Query("delete from notes")
    Completable deleteAll();

    @Query("select * from notes")
    Single<List<Note>> selectAllNote();

    @Query("select * from notes where title like '%' || :s || '%'")
    Single<List<Note>> search(String s);

}
