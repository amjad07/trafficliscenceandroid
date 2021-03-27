package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.repository.NoteRepository;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

import java.util.List;
public class TestViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<TestTrafficSetters>> allNotes;
    public TestViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(TestTrafficSetters note) {
        repository.insert(note);
    }
    public void update(TestTrafficSetters note) {
        repository.update(note);
    }
    public void delete(TestTrafficSetters note) {
        repository.delete(note);
    }
    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }
    public LiveData<List<TestTrafficSetters>> getAllNotes() {
        return allNotes;
    }
}