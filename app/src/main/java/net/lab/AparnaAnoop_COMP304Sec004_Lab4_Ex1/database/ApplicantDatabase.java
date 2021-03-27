package net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.Dao.TestDao;
import net.lab.AparnaAnoop_COMP304Sec004_Lab4_Ex1.setters.TestTrafficSetters;

@Database(entities = {TestTrafficSetters.class}, version = 1)
public abstract class ApplicantDatabase extends RoomDatabase {
    private static ApplicantDatabase instance;
    public abstract TestDao noteDao();
    public static synchronized ApplicantDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ApplicantDatabase.class, "applicant_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}