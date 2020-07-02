package com.childrenatrisk.smartparents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotesFragment extends Fragment {

    public NotesFragment() {}

    public static NotesFragment newInstance() {
        NotesFragment fragment=new NotesFragment();
        Bundle args=new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //TODO retain notes somehow (save to file with snackbar or toast to confirm save?)
    //TODO better switch between notes and other fragments
    //TODO dismiss keyboard on navigate away
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Notes");
    }
}