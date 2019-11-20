package sheridan.iftikhar.project;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class editJogFragment extends Fragment {



    EditText mEditDate;
    public editJogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_edit_jog, container, false);
        mEditDate = view.findViewById(R.id.edtJogDate);

        mEditDate.setText(Integer.toString(getArguments().getInt("position")));
        return view;
    }

}
