package com.childrenatrisk.smartparents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChildSafetyFragment extends Fragment {

    public ChildSafetyFragment() {}

    public static ChildSafetyFragment newInstance() {
        ChildSafetyFragment fragment = new ChildSafetyFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_child_safety, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Child Safety");
    }

    public void childSafetyButton1Click(View view) {}
    public void childSafetyButton2Click(View view) {}
    public void childSafetyButton3Click(View view) {}
}