package sheridan.iftikhar.project.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private ExerciseRepository mRepository;

    //initializing all lists
     LiveData<List<FreeWeight>> mAllFreeWeights;
     LiveData<List<Jog>> mAllJogs;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ExerciseRepository(application);
        mAllFreeWeights = mRepository.getFreeWeightList();
        mAllJogs = mRepository.getJogList();
    }

    public LiveData<List<Jog>> getAllJogs(){
        return mAllJogs;
    }

    public LiveData<List<FreeWeight>> getAllFreeWeights(){
        return mAllFreeWeights;
    }

    public void insert(Jog jog){
        mRepository.insert(jog);
    }

    public void insert(FreeWeight freeWeight){
        mRepository.insert(freeWeight);
    }

    public void delete(Jog jog){
        mRepository.delete(jog);
    }

    public void delete(FreeWeight freeWeight){
        mRepository.delete(freeWeight);
    }

    public void update(Jog jog){
        mRepository.update(jog);
    }

    public void update(FreeWeight freeWeight){
        mRepository.update(freeWeight);
    }

}
