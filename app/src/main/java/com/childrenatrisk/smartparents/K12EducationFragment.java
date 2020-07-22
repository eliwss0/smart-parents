package com.childrenatrisk.smartparents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class K12EducationFragment extends Fragment {

    public K12EducationFragment() {}

    public static K12EducationFragment newInstance() {
        K12EducationFragment fragment = new K12EducationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_k12education, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("K-12 Education");
    }

    public void k12EducationButton1Click(View view) {}
    public void k12EducationButton2Click(View view) {}
    public void k12EducationButton3Click(View view) {}
    public void k12EducationButton4Click(View view) {}
}