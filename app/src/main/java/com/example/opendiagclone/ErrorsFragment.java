package com.example.opendiagclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opendiagclone.adapter.ErrorsListAdapter;
import com.example.opendiagclone.adapter.ParametersListAdapter;
import com.example.opendiagclone.models.Errors;
import com.example.opendiagclone.models.Parameters;

import java.util.ArrayList;

public class ErrorsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_errors,container,false);


        ListView mListView = view.findViewById(R.id.errorListView);

        Errors efficiencyMain = new Errors("P0422",
                "T C","Эффективность основного нейтрализатора(Блок 1) ниже порога");
        Errors efficiencyHeated = new Errors("P0423",
                "T C","Эффективность нагретого нейтрализатора(Блок 1) ниже порога");
        Errors air = new Errors("PO102",
                "C","Датчик расхода воздуха,низкий уровень выходного сигнала");

        ArrayList<Errors> errorsArrayList = new ArrayList<>();

        errorsArrayList.add(efficiencyMain);
        errorsArrayList.add(efficiencyHeated);
        errorsArrayList.add(air);



        ErrorsListAdapter adapter = new ErrorsListAdapter(getActivity(), errorsArrayList);
        mListView.setAdapter(adapter);

        return view;

    }

}
