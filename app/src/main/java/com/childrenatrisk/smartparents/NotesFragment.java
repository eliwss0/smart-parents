package com.childrenatrisk.smartparents;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NotesFragment extends Fragment {

    public NotesFragment() {
    }

    public static NotesFragment newInstance() {
        NotesFragment fragment=new NotesFragment();
        Bundle args=new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //TODO load notes
    //TODO better switch between notes and other fragments
    //TODO dismiss keyboard on outside tap or other input
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notes, container, false);
        FloatingActionButton fabSave=view.findViewById(R.id.save_notes_fab);
        final EditText notesField = view.findViewById(R.id.notes_input);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!notesField.getText().toString().isEmpty()) {
                    try {
                        writeNotesFile(getActivity(), notesField.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (fileExists(getActivity(),"notes.txt")) {
                        Snackbar.make(view, "Saved notes", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Notes");
    }

    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        if (file != null && file.exists()) {
            return true;
        }
        return false;
    }

    public void writeNotesFile(Context context,String fileContents) throws IOException {
        String fileName="notes.txt";
        FileOutputStream fileOut=context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOut.write(fileContents.getBytes());
    }
}