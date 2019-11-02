package com.example.buscomida.about;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buscomida.R;

import java.util.ArrayList;


public class AboutFragment extends Fragment {

    ArrayList<Student> listStudents;

    public AboutFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        RecyclerView recyclerViewAbout = view.findViewById(R.id.rv_about);
        recyclerViewAbout.setLayoutManager(new LinearLayoutManager(getContext()));

        listStudents = new ArrayList<>();

        fillRecyclerView();
        AdapterRecyclerViewAbout adapter = new AdapterRecyclerViewAbout(listStudents);
        recyclerViewAbout.setAdapter(adapter);

        return view;
    }

    private void fillRecyclerView(){

        listStudents.add(new Student(getResources().getString(R.string.william), getResources().getString(R.string.williamId)));
        listStudents.add(new Student(getResources().getString(R.string.ricardo), getResources().getString(R.string.ricardoId)));
        listStudents.add(new Student(getResources().getString(R.string.aaron), getResources().getString(R.string.aaronId)));
        listStudents.add(new Student(getResources().getString(R.string.emerson), getResources().getString(R.string.emersonId)));
        listStudents.add(new Student(getResources().getString(R.string.manuel), getResources().getString(R.string.manueId)));
        listStudents.add(new Student(getResources().getString(R.string.jesus), getResources().getString(R.string.jesusId)));


    }

}
