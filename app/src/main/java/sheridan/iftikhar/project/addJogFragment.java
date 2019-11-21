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
import sheridan.iftikhar.project.Room.Jog;


/**
 * A simple {@link Fragment} subclass.
 */
public class addJogFragment extends Fragment {

    EditText mEditDate, mEditDuration, mEditIntensity;
    NavController mNavController;
    Button btnAdd, btnBack;
    ExerciseViewModel mExerciseViewModel;

    public addJogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_jog, container, false);
        btnAdd = view.findViewById(R.id.btnAddJog);
        btnBack = view.findViewById(R.id.btnBackJog);

        mEditDate = view.findViewById(R.id.edtJogDate);
        mEditDuration = view.findViewById(R.id.edtJogDuration);
        mEditIntensity = view.findViewById(R.id.edtJogIntensity);

        btnAdd.setOnClickListener(v->AddJog());
        btnBack.setOnClickListener(v->GoBack());

        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    void GoBack(){
        mNavController.navigate(R.id.action_addJogFragment_to_showJogFragment);
    }

    void AddJog(){
        String date = mEditDate.getText().toString();
        int duration = Integer.parseInt(mEditDuration.getText().toString());
        int intensity = Integer.parseInt(mEditIntensity.getText().toString());
        mExerciseViewModel.insert(new Jog(date,duration,intensity));

        Toast.makeText(this.getContext(), "Jog Added", Toast.LENGTH_SHORT).show();
    }
}
