package sheridan.iftikhar.project;


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
import sheridan.iftikhar.project.Room.FreeWeight;


/**
 * A simple {@link Fragment} subclass.
 */
public class showFreeWeightFragment extends Fragment {

    Button mAddFreeWeight, mGoBack;
    ExerciseViewModel mExerciseViewModel;
    NavController mNavController;

    public showFreeWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_free_weight, container, false);
        mGoBack = view.findViewById(R.id.btnBack);
        mAddFreeWeight = view.findViewById(R.id.btnAddFreeWeight);

        RecyclerView recyclerView = view.findViewById(R.id.freeWeightList);
        final FreeWeightListAdapter adapter = new FreeWeightListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);
        mExerciseViewModel.getAllFreeWeights().observe(this, new Observer<List<FreeWeight>>() {
            @Override
            public void onChanged(List<FreeWeight> freeWeights) {
                adapter.setFreeWeights(freeWeights);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        mAddFreeWeight.setOnClickListener(v->mNavController.navigate(R.id.action_showFreeWeightFragment_to_addFreeWeightFragment));
        mGoBack.setOnClickListener(v->mNavController.navigate(R.id.action_showFreeWeightFragment_to_homeFragment));

    }
}
