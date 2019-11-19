package sheridan.iftikhar.project.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FreeWeight.class, Jog.class}, version = 1, exportSchema = false)
public abstract class ExerciseRoomDatabase extends RoomDatabase {

    public abstract FreeWeightDao freeWeightDao();
    public abstract JogDao jogDao();

    private static volatile ExerciseRoomDatabase INSTANCE;
    private static final int NUM_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);

    static ExerciseRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ExerciseRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            ,ExerciseRoomDatabase.class, "exercise_room_database").build();

                }
            }
        }
        return INSTANCE;
    }
}
