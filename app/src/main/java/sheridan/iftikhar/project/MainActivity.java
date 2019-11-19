package sheridan.iftikhar.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;

import java.util.List;

import sheridan.iftikhar.project.Room.ExerciseViewModel;
import sheridan.iftikhar.project.Room.Jog;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_JOG_ACTIVITY_REQUEST_CODE = 1;

    private ExerciseViewModel mExerciseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.jogList);
        final JogListAdapter adapter = new JogListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExerciseViewModel =  new ExerciseViewModel((Application) getApplicationContext()); //new ViewModelProvider().get(ExerciseViewModel.class);

        mExerciseViewModel.getAllJogs().observe(this, new Observer<List<Jog>>() {
            @Override
            public void onChanged(@Nullable final List<Jog> jog) {
                // Update the cached copy of the words in the adapter.
                adapter.setJogs(jog);
            }
        });

    }

}
