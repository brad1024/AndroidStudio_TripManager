package com.example.tripmanager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    public FragmentTripList tripFrag;
    public FragmentAddDest destFrag;
    public FragmentAddTrans transFrag;

    public BlockManager blockManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        tripFrag = new FragmentTripList();
        destFrag = new FragmentAddDest();
        transFrag = new FragmentAddTrans();
        blockManager = new BlockManager();

        ChangeFragment(tripFrag);
    }
    public void ChangeFragment(Fragment to){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flFragment, to);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save
    }
}