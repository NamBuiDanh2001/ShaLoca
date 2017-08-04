package com.example.hp_bdn.shaloca.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp_bdn.shaloca.R;

/**
 * Created by HP_BDN on 01-Aug-17.
 */

public class AnserFriendFragment extends Fragment {
    public static AnserFriendFragment newInstance(){
        AnserFriendFragment anserFriendFragment = new AnserFriendFragment();
        return  anserFriendFragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.anserfriend_fragment ,container ,false );

        return  view;
    }
}
