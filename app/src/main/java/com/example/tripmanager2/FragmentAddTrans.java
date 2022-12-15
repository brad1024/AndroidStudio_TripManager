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

public class FragmentAddTrans extends Fragment {

    MainActivity mainActivity;
    View mainView;
    BlockDest from;
    BlockDest to;
    Button btnExample;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)context; //拿mainActivity
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_add_trans, container, false);
        btnExample = mainView.findViewById(R.id.btnExample);
        btnExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseExample();
            }
        });
        return mainView;
    }

    public void SetData(BlockDest _from, BlockDest _to){
        from = _from;
        to = _to;
    }

    void ChooseExample(){
        BlockBase.BlockType type = BlockBase.BlockType.trans;
        String name = "交通";
        int id = View.generateViewId();
        String transType = "公車"; //交通方式
        BlockTrans transBlock = new BlockTrans(type, name, id, from.endTime-1, from.endTime, transType , from.name,to.name); //建構transBlock
        mainActivity.blockManager.AddBlock(transBlock); //把transBlock加進blockList
        mainActivity.ChangeFragment(mainActivity.tripFrag); //切回trip fragment
    }
}