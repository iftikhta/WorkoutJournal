package sheridan.iftikhar.project.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            ,ExerciseRoomDatabase.class, "exercise_room_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                JogDao dao = INSTANCE.jogDao();
                dao.deleteAll();
                Jog jog = new Jog("September first", 5,2);
                dao.insert(jog);
                jog = new Jog("October", 2,1);
                dao.insert(jog);
                jog = new Jog("October", 2,1);
                dao.insert(jog);
                jog = new Jog("October", 2,1);
                dao.insert(jog);
                jog = new Jog("October", 2,1);
                dao.insert(jog);
                jog = new Jog("October", 2,1);
                dao.insert(jog);
            });
        }
    };

}
