package sheridan.iftikhar.project;


import android.app.Application;
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
import sheridan.iftikhar.project.Room.Jog;


/**
 * A simple {@link Fragment} subclass.
 */
public class editJogFragment extends Fragment {


    Button btnSave,btnDelete,btnBack;
    EditText mEditDate,mEditDuration,mEditIntensity;
    ExerciseViewModel exerciseViewModel;
    Jog currJog;
    NavController mNavController;

    public editJogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_edit_jog, container, false);

        mEditDate = view.findViewById(R.id.edtJogDate);
        mEditDuration = view.findViewById(R.id.edtJogDuration);
        mEditIntensity = view.findViewById(R.id.edtJogIntensity);

        btnSave = view.findViewById(R.id.btnSaveJog);
        btnBack = view.findViewById(R.id.btnBackJog);
        btnDelete = view.findViewById(R.id.btnDeleteJog);

        btnSave.setOnClickListener(V->SaveJog());
        btnBack.setOnClickListener(V->GoBack());
        btnDelete.setOnClickListener(V->DeleteJog());
       // mEditDate.setText(Integer.toString(getArguments().getInt("position")));

        exerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class); //new ExerciseViewModel(getActivity().getApplication());//new ExerciseViewModel(getActivity().getApplication());  //new ViewModelProvider(this.getActivity().get(ExerciseViewModel.class)); //this.getActivity()).get(PetViewModel.class
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);

        populateFields();
    }

    private void populateFields(){
        int idxJog = getArguments().getInt("p");
        currJog = exerciseViewModel.getAllJogs().getValue().get(idxJog);
        mEditDate.setText(currJog.getJogDate());
        mEditDuration.setText(Integer.toString(currJog.getJogDuration()));
        mEditIntensity.setText(Integer.toString(currJog.getJogIntensity()));
    }

    private void GoBack(){
        NavController  navController= Navigation.findNavController(getView());
        navController.navigate(R.id.action_editJogFragment_to_showJogFragment);
    }
    private void SaveJog() {
        //ExerciseViewModel exerciseViewModel = new ExerciseViewModel(getActivity().getApplication());
        if(mEditDuration.getText().toString().isEmpty() || mEditDuration.getText().toString().matches(" +")
        || mEditIntensity.getText().toString().isEmpty() || mEditIntensity.getText().toString().matches( " +")
        || mEditDate.getText().toString().isEmpty() || mEditDate.getText().toString().matches(" +")){
            Toast.makeText(this.getContext(), "Invalid input!", Toast.LENGTH_SHORT).show();

        }
        else{
            mEditDuration.setText(mEditDuration.getText().toString().replaceAll(",", ""));
            currJog.setJogDate(mEditDate.getText().toString());
            currJog.setJogDuration(Integer.parseInt(mEditDuration.getText().toString()));
            currJog.setJogIntensity(Integer.parseInt(mEditIntensity.getText().toString()));

            exerciseViewModel.update(currJog); //

            Toast.makeText(this.getContext(), "Changes saved!", Toast.LENGTH_SHORT).show();
        }

    }

    private void DeleteJog(){
        exerciseViewModel.delete(currJog);
        GoBack();
        Toast.makeText(this.getContext(), "Jog has been deleted", Toast.LENGTH_SHORT).show();

    }

}
