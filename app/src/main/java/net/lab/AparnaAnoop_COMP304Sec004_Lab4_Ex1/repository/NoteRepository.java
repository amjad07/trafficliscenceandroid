package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.Dao.TestDao;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.database.ApplicantDatabase;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

import java.util.List;
public class NoteRepository {
    private TestDao noteDao;
    private LiveData<List<TestTrafficSetters>> allNotes;
    public NoteRepository(Application application) {
        ApplicantDatabase database = ApplicantDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }
    public void insert(TestTrafficSetters note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(TestTrafficSetters note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(TestTrafficSetters note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<TestTrafficSetters>> getAllNotes() {
        return allNotes;
    }
    private static class InsertNoteAsyncTask extends AsyncTask<TestTrafficSetters, Void, Void> {
        private TestDao noteDao;
        private InsertNoteAsyncTask(TestDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(TestTrafficSetters... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<TestTrafficSetters, Void, Void> {
        private TestDao noteDao;
        private UpdateNoteAsyncTask(TestDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(TestTrafficSetters... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<TestTrafficSetters, Void, Void> {
        private TestDao noteDao;
        private DeleteNoteAsyncTask(TestDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(TestTrafficSetters... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private TestDao noteDao;
        private DeleteAllNotesAsyncTask(TestDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}