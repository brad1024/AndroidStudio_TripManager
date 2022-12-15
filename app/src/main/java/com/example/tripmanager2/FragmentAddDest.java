package com.example.tripmanager2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.HashMap;


public class FragmentAddDest extends Fragment implements NumberPicker.OnValueChangeListener {

    View mainView;
    MainActivity mainActivity;

    EditText etName;
    NumberPicker npStart, npEnd;
    Button btnCheck, btnCancel;
    int begin, end;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_add_dest, container, false);

        etName = mainView.findViewById(R.id.etDestName);

        npStart = mainView.findViewById(R.id.npDestStart);
        InitNP(npStart);

        npEnd = mainView.findViewById(R.id.npDestEnd);
        InitNP(npEnd);

        btnCheck = mainView.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check();
            }
        });

        btnCancel = mainView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });


        return mainView;
    }

    void InitNP(NumberPicker np){
        np.setMinValue(0);
        np.setMaxValue(23);
        np.setOnValueChangedListener(this);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        if(numberPicker==npStart){
            begin = i1;
            npEnd.setMinValue(i1);
        }
        if(numberPicker==npEnd){
            end = i1;
            npStart.setMaxValue(i1);
        }
    }

    void Check() {

        BlockBase.BlockType type = BlockBase.BlockType.dest;
        String name = etName.getText().toString();
        if(name.isEmpty()){
            Dialog dialog = new Dialog("Alert", "請輸入地點");
            dialog.show(getActivity().getSupportFragmentManager(), null);
            return;
        }
        if(begin==end) {
            Dialog dialog = new Dialog("Alert", "請輸入正確時間");
            dialog.show(getActivity().getSupportFragmentManager(), null);
            return;
        }
        int id = View.generateViewId();
        HashMap<String, Float> coord = GetCoordinate(name);
        BlockDest block = new BlockDest(type, name, id, begin, end, coord);

        etName.setText("");
        begin = 0;
        end = 0;

        mainActivity.blockManager.AddBlock(block);
        mainActivity.ChangeFragment(mainActivity.tripFrag);

    }


    HashMap<String, Float> GetCoordinate(String name){
        float coordX=0;
        float coordY=0;
        HashMap<String, Float> coord = new HashMap<>();
        coord.put("x", coordX);
        coord.put("y", coordY);
        return coord;
    }


    void Cancel() {
        Log.v("debug", "cancel");
        mainActivity.ChangeFragment(mainActivity.tripFrag);
    }
}