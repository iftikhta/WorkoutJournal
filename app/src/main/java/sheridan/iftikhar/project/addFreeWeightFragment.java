package sheridan.iftikhar.project;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import sheridan.iftikhar.project.Room.ExerciseViewModel;
import sheridan.iftikhar.project.Room.FreeWeight;


/**
 * A simple {@link Fragment} subclass.
 */
public class addFreeWeightFragment extends Fragment {

    Button btnBack, btnAdd;
    NavController mNavController;
    EditText edtRepetitions, edtPounds;
    TextView edtDate;
    ExerciseViewModel mExerciseViewModel;


    DatePickerDialog mDatePickerDialog;
    DatePickerDialog.OnDateSetListener mOnDateSetListener;

    public addFreeWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_free_weight, container, false);

        btnBack = view.findViewById(R.id.btnBackFreeWeight);
        btnAdd = view.findViewById(R.id.btnAddFreeWeight);

        edtDate = view.findViewById(R.id.edtFreeWeightDate);
        edtPounds = view.findViewById(R.id.edtFreeWeightPounds);
        edtRepetitions = view.findViewById(R.id.edtFreeWeightRepetitions);

        btnAdd.setOnClickListener(v->AddFreeWeight());
        btnBack.setOnClickListener(v->GoBack());
        edtDate.setOnClickListener(v->onClickDate());

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month +=1;
                edtDate.setText(year+ "/" + month + "/" + day);
            }
        };


        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);
        return view;
    }

    void onClickDate(){
        Calendar call = Calendar.getInstance();
        //he did: int year cal.get(Calendar.YEAR);
        int year = call.get(Calendar.YEAR);
        int month = call.get(Calendar.MONTH);
        int day = call.get(Calendar.DAY_OF_MONTH);

        mDatePickerDialog = new DatePickerDialog(this.getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth
                ,mOnDateSetListener, year,month,day);

        mDatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDatePickerDialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    void AddFreeWeight(){
        if(edtDate.getText().toString().isEmpty() || edtDate.getText().toString().matches(" +")
        || edtPounds.getText().toString().isEmpty() || edtPounds.getText().toString().matches(" +")
        || edtRepetitions.getText().toString().isEmpty() || edtRepetitions.getText().toString().matches(" +")){
            Toast.makeText(this.getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
        }
        else{
            String date = edtDate.getText().toString();
            int repetitions = Integer.parseInt(edtRepetitions.getText().toString());
            int pounds = Integer.parseInt(edtPounds.getText().toString());
            mExerciseViewModel.insert(new FreeWeight(date,repetitions,pounds));

            Toast.makeText(this.getContext(), "Free weight exercise added!", Toast.LENGTH_SHORT).show();
        }

    }

    void GoBack(){
        mNavController.navigate(R.id.action_addFreeWeightFragment_to_showFreeWeightFragment);

    }
}
