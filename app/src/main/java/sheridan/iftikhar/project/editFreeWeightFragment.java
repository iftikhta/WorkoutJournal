package sheridan.iftikhar.project;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sheridan.iftikhar.project.Room.ExerciseViewModel;
import sheridan.iftikhar.project.Room.FreeWeight;


/**
 * A simple {@link Fragment} subclass.
 */
public class editFreeWeightFragment extends Fragment {
    NavController mNavController;
    Button btnBack,btnEdit, btnDelete;
    EditText edtDate, edtRepetitions, edtPounds;
    ExerciseViewModel mExerciseViewModel;
    FreeWeight currFreeWeight;

    public editFreeWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_free_weight, container, false);

        btnBack = view.findViewById(R.id.btnBackFreeWeight);
        btnDelete = view.findViewById(R.id.btnDeleteFreeWeight);
        btnEdit = view.findViewById(R.id.btnSaveFreeWeight);

        edtDate = view.findViewById(R.id.edtFreeWeightDate);
        edtRepetitions = view.findViewById(R.id.edtFreeWeightRepetitions);
        edtPounds = view.findViewById(R.id.edtFreeWeightPounds);

        btnEdit.setOnClickListener(v->SaveFreeWeight());
        btnDelete.setOnClickListener(v->DeleteFreeWeight());
        btnBack.setOnClickListener(v->BackFreeWeight());

        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);
        return view;
    }

    void SaveFreeWeight(){

        if (edtDate.getText().toString().isEmpty() || edtDate.getText().toString().matches(" +")
        || edtPounds.getText().toString().isEmpty() || edtPounds.getText().toString().matches(" +")
        || edtRepetitions.getText().toString().isEmpty() || edtRepetitions.getText().toString().matches(" +")){
            Toast.makeText(this.getContext(), "Invalid input!", Toast.LENGTH_SHORT).show();
        }
        else{
            currFreeWeight.setFreeWeightDate(edtDate.getText().toString());
            currFreeWeight.setFreeWeightPounds(Integer.parseInt(edtPounds.getText().toString()));
            currFreeWeight.setFreeWeightRepitions(Integer.parseInt(edtRepetitions.getText().toString()));
            mExerciseViewModel.update(currFreeWeight);
            Toast.makeText(this.getContext(), "Free weight updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void DeleteFreeWeight(){
        mExerciseViewModel.delete(currFreeWeight);
    }

    void BackFreeWeight(){
        mNavController.navigate(R.id.action_editFreeWeightFragment_to_showFreeWeightFragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        populateFields();
    }

    void populateFields(){
        int idxFreeWeight = getArguments().getInt("p");
        currFreeWeight = mExerciseViewModel.getAllFreeWeights().getValue().get(idxFreeWeight);
        edtPounds.setText(Integer.toString(currFreeWeight.getFreeWeightPounds()));
        edtRepetitions.setText(Integer.toString(currFreeWeight.getFreeWeightRepitions()));
        edtDate.setText(currFreeWeight.getFreeWeightDate());
    }
}
