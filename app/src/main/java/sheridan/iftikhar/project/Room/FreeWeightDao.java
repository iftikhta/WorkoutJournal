package sheridan.iftikhar.project.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FreeWeightDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FreeWeight freeWeight);

    @Delete
    void delete(FreeWeight freeWeight);

    @Update
    void update(FreeWeight freeWeight);

    //returns a list of all freeweight exersizes organized by date but
    // changing this list doesnt do anything to database
    @Query("SELECT * from FreeWeight_Table ORDER BY freeWeightDate ASC")
    LiveData<List<FreeWeight>> getFreeWeightsByDate();
}
