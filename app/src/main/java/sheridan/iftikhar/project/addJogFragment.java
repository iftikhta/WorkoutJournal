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

import android.text.format.DateUtils;
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
import sheridan.iftikhar.project.Room.Jog;


/**
 * A simple {@link Fragment} subclass.
 */
public class addJogFragment extends Fragment {

    TextView mEditDate;

    DatePickerDialog mDatePickerDialog;
    DatePickerDialog.OnDateSetListener mOnDateSetListener;

    EditText mEditDuration, mEditIntensity;
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
        mEditDate.setOnClickListener(v->onTvDateClick());

        mExerciseViewModel = new ViewModelProvider(this.getActivity()).get(ExerciseViewModel.class);

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month +=1;
                mEditDate.setText(year+ "/" + month + "/" + day);
            }
        };


        return view;
    }
    void onTvDateClick(){
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

    void GoBack(){
        mNavController.navigate(R.id.action_addJogFragment_to_showJogFragment);
    }

    void AddJog(){
        if(mEditDate.getText().toString().equals("Click to set a date") || mEditDate.getText().toString().isEmpty() || mEditDate.getText().toString().matches(" +")
        || mEditIntensity.getText().toString().isEmpty() || mEditIntensity.getText().toString().matches(" +")
        || mEditDuration.getText().toString().isEmpty() || mEditDuration.getText().toString().matches(" +")){
            Toast.makeText(this.getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
        }
        else{
            String date = mEditDate.getText().toString();
            int duration = Integer.parseInt(mEditDuration.getText().toString());
            int intensity = Integer.parseInt(mEditIntensity.getText().toString());
            mExerciseViewModel.insert(new Jog(date,duration,intensity));

            Toast.makeText(this.getContext(), "Jog Added", Toast.LENGTH_SHORT).show();
        }
    }
}
