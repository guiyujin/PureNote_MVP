package com.guiyujin.purenote_mvp.room;

import android.content.Context;

import java.util.List;

public class DBengine {
    private NoteDAO noteDAO;

    public DBengine(Context context){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(context);
        noteDAO = noteDatabase.getNoteDao();
    }

    public void insert(Note ... notes){
        noteDAO.insertNotes(notes);
    }

    public void updateNotes(Note ... notes){
        noteDAO.updateNotes(notes);
    }

    public void delete(Note ... notes){
        noteDAO.delete(notes);
    }

    public void deleteAllNotes(){
        noteDAO.deleteAllNotes();
    }

    public List<Note> getAllNotes(){
        return noteDAO.getAllNotes();
    }

}
