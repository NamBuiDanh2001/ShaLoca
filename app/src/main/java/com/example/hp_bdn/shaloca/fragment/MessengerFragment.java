package com.example.hp_bdn.shaloca.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp_bdn.shaloca.R;


/**
 * Created by bui on 15-Jul-17.
 */

public class MessengerFragment extends Fragment {
    // contructor
    public static MessengerFragment newInstance() {
        MessengerFragment messengerFragment = new MessengerFragment();
        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
        messengerFragment.setArguments(args);
        return messengerFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messenger_frangment, container, false);
        return  view ;
    }
}
