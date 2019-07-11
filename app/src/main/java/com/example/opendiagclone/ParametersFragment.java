package com.example.opendiagclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opendiagclone.adapter.InformationListAdapter;
import com.example.opendiagclone.adapter.ParametersListAdapter;
import com.example.opendiagclone.models.Information;
import com.example.opendiagclone.models.Parameters;

import java.util.ArrayList;

public class ParametersFragment extends Fragment {

    ParametersListAdapter adapter;

    public void add(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            adapter.addEntry(0);
                            adapter.notifyDataSetChanged();
                        }

                    });
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }



   /* @Override
    public void onResume(){
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            adapter.addEntry(0);
                            adapter.notifyDataSetChanged();
                        }

                    });
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    } */



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_parameters,container,false);


        ListView mListView = view.findViewById(R.id.parametersListView);

        Parameters valueYOZ = new Parameters("Величина отскока УОЗ при детонации(ПКВ)",
                "0,0");
        Parameters load = new Parameters("Расчетная нагрузка(%)",
                "19,50");
        Parameters factor = new Parameters("Фактор высотной коррекции",
                "1,02");

        ArrayList<Parameters> parametersArrayList = new ArrayList<>();

        parametersArrayList.add(valueYOZ);
        parametersArrayList.add(load);
        parametersArrayList.add(factor);



        adapter = new ParametersListAdapter(getActivity(), parametersArrayList);
        mListView.setAdapter(adapter);

        add();

        return view;

    }

}
