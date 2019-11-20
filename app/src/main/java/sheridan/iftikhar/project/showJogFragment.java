package sheridan.iftikhar.project;


import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import sheridan.iftikhar.project.Room.ExerciseViewModel;
import sheridan.iftikhar.project.Room.Jog;


/**
 * A simple {@link Fragment} subclass.
 */
public class showJogFragment extends Fragment {

    ExerciseViewModel mExerciseViewModel;
    Button mAddJog;
     NavController mNavController;
    public static final int NEW_JOG_ACTIVITY_REQUEST_CODE = 1;

    public showJogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_show_jog, container, false);
        mAddJog = view.findViewById(R.id.btnAddJog);


        RecyclerView recyclerView = view.findViewById(R.id.jogList);
        final JogListAdapter adapter = new JogListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);  // new ExerciseViewModel((Application) getContext().getApplicationContext());
        mExerciseViewModel.getAllJogs().observe(this, new Observer<List<Jog>>() {
            @Override
            public void onChanged(@Nullable final List<Jog> jog) {
                // Update the cached copy of the words in the adapter.
                adapter.setJogs(jog);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

       mAddJog.setOnClickListener(v-> mNavController.navigate(R.id.action_showJogFragment_to_editJogFragment));
    }

//    static void GoEdit(JogListAdapter.JogListViewHolder holder, int position){
//        Bundle mBundle = new Bundle();
//        mBundle.putInt("position", position);
//        mNavController.navigate(R.id.action_showJogFragment_to_editJogFragment, mBundle);
//
//    }
}
