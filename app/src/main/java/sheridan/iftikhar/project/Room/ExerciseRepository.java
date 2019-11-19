package sheridan.iftikhar.project.Room;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseRepository {

    private FreeWeightDao mFreeWeightDao;
    private JogDao mJogDao;

    private LiveData<List<Jog>> mJogList;
    private LiveData<List<FreeWeight>> mFreeWeightList;



    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ExerciseRepository(Application application) {
        ExerciseRoomDatabase db = ExerciseRoomDatabase.getDatabase(application);
        mFreeWeightDao = db.freeWeightDao();
        mJogDao = db.jogDao();

        mFreeWeightList = mFreeWeightDao.getFreeWeightsByDate();
        mJogList = mJogDao.getJogsByDate();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Jog>> getJogList() {
        return mJogList;
    }

    LiveData<List<FreeWeight>> getFreeWeightList() {
        return mFreeWeightList;
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Jog jog) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mJogDao.insert(jog);
        });
    }
    void insert(FreeWeight freeWeight) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFreeWeightDao.insert(freeWeight);
        });
    }

    void delete(Jog jog) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mJogDao.delete(jog);
        });
    }
    void delete(FreeWeight freeWeight) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFreeWeightDao.delete(freeWeight);
        });
    }

    void update(Jog jog) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mJogDao.update(jog);
        });
    }
    void update(FreeWeight freeWeight) {
        ExerciseRoomDatabase.databaseWriteExecutor.execute(() -> {
            mFreeWeightDao.update(freeWeight);
        });
    }


}
