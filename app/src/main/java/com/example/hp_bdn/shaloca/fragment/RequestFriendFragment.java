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

public class RequestFriendFragment extends Fragment {
    public static RequestFriendFragment newInstance(){
        RequestFriendFragment requestFriendFragment = new RequestFriendFragment();
        return  requestFriendFragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requestfriend_fragment ,container ,false );

        return  view;
    }
}
