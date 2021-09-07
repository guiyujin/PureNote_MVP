package com.guiyujin.purenote_mvp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insertNotes(Note ... notes);

    @Update
    void updateNotes(Note ... notes);

    @Delete
    void delete(Note ... notes);

    @Query("DELETE FROM NOTE")
    void deleteAllNotes();

    @Query("SELECT * FROM Note ORDER BY ID DESC")
    List<Note> getAllNotes();
}
