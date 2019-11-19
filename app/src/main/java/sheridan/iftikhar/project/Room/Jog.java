package sheridan.iftikhar.project.Room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//assumptions made: Constructor does not need to generate the key?

@Entity(tableName = "Jog_Table")
public class Jog {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int jogID;

    @NonNull
    private String jogDate; //start date

    @NonNull
    private int jogDuration; //in mintutes

    @NonNull
    private int jogIntensity; //0 low, 1 medium, 2 high

    public Jog(@NonNull String jogDate, @NonNull int jogDuration, @NonNull int jogIntensity) {
        this.jogDate = jogDate;
        this.jogDuration = jogDuration;
        this.jogIntensity = jogIntensity;
    }

    public int getJogID() {
        return jogID;
    }

    public void setJogID(int jogID) {
        this.jogID = jogID;
    }

    @NonNull
    public String getJogDate() {
        return jogDate;
    }

    public void setJogDate(@NonNull String jogDate) {
        this.jogDate = jogDate;
    }

    public int getJogDuration() {
        return jogDuration;
    }

    public void setJogDuration(int jogDuration) {
        this.jogDuration = jogDuration;
    }

    public int getJogIntensity() {
        return jogIntensity;
    }

    public void setJogIntensity(int jogIntensity) {
        this.jogIntensity = jogIntensity;
    }
}
