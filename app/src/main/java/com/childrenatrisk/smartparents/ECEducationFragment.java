package com.childrenatrisk.smartparents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ECEducationFragment extends Fragment {

    public ECEducationFragment() {}

    public static ECEducationFragment newInstance() {
        ECEducationFragment fragment = new ECEducationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_eceducation, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Early Childhood Education");
    }

    public void ecEducationButton1Click(View view) {}
    public void ecEducationButton2Click(View view) {}
    public void ecEducationButton3Click(View view) {}
}