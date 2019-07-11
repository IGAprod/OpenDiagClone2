package com.example.opendiagclone;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opendiagclone.adapter.InformationListAdapter;
import com.example.opendiagclone.models.Information;

import java.util.ArrayList;

public class InfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_info,container,false);


        ListView mListView = view.findViewById(R.id.listView);

        Information code = new Information("Код программного обеспечения",
                "NOTSUPPORT");
        Information nameOfDvigatel = new Information("Наименование системы или двигателя",
                "LADA - 1.6");
        Information codeForReserveParts = new Information("Код для запасных частей",
                "1337");

        ArrayList<Information> informationArrayList = new ArrayList<>();

        informationArrayList.add(code);
        informationArrayList.add(nameOfDvigatel);
        informationArrayList.add(codeForReserveParts);



        InformationListAdapter adapter = new InformationListAdapter(getActivity(), informationArrayList);
        mListView.setAdapter(adapter);

        return view;
    }

}
