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
public interface JogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Jog jog);

    @Delete
    void delete(Jog jog);

    @Update
    void update(Jog jog);

    @Query("DELETE FROM jog_table")
    void deleteAll();

    @Query("SELECT * FROM jog_table ORDER BY jogDate ASC")
    LiveData<List<Jog>> getJogsByDate();
}
