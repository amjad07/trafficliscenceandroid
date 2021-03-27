package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.Dao;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

import java.util.List;
@Dao
public interface TestDao {
    @Insert
    void insert(TestTrafficSetters note);
    @Update
    void update(TestTrafficSetters note);
    @Delete
    void delete(TestTrafficSetters note);
    @Query("DELETE FROM test_table")
    void deleteAllNotes();
    @Query("SELECT * FROM test_table ORDER BY id DESC")
    LiveData<List<TestTrafficSetters>> getAllNotes();
}