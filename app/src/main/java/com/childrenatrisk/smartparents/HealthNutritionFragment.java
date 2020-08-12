package com.childrenatrisk.smartparents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HealthNutritionFragment extends Fragment {

    public HealthNutritionFragment() {}

    public static HealthNutritionFragment newInstance() {
        HealthNutritionFragment fragment = new HealthNutritionFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_health_nutrition, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Health and Nutrition");
    }

    public void healthButton1Click(View view) {}
    public void healthButton2Click(View view) {}
    public void nutritionButton1Click(View view) {}
    public void nutritionButton2Click(View view) {}
    public void nutritionButton3Click(View view) {}
}