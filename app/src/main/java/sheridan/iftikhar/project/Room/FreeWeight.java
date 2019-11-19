package sheridan.iftikhar.project.Room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "FreeWeight_Table")
public class FreeWeight {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int freeWeightID;

    @NonNull
    private String freeWeightDate; //date to do the weights

    @NonNull
    private int freeWeightRepitions; //how many repetions

    @NonNull
    private int freeWeightPounds; ///how heavy the weight is

    public FreeWeight(@NonNull String freeWeightDate, @NonNull int freeWeightRepitions, @NonNull int freeWeightPounds) {
        this.freeWeightDate = freeWeightDate;
        this.freeWeightRepitions = freeWeightRepitions;
        this.freeWeightPounds = freeWeightPounds;
    }

    @NonNull
    public String getFreeWeightDate() {
        return freeWeightDate;
    }

    public void setFreeWeightDate(@NonNull String freeWeightDate) {
        this.freeWeightDate = freeWeightDate;
    }

    public int getFreeWeightRepitions() {
        return freeWeightRepitions;
    }

    public void setFreeWeightRepitions(int freeWeightRepitions) {
        this.freeWeightRepitions = freeWeightRepitions;
    }

    public int getFreeWeightPounds() {
        return freeWeightPounds;
    }

    public void setFreeWeightPounds(int freeWeightPounds) {
        this.freeWeightPounds = freeWeightPounds;
    }

    public int getFreeWeightID() {
        return freeWeightID;
    }

    public void setFreeWeightID(int freeWeightID) {
        this.freeWeightID = freeWeightID;
    }

}
