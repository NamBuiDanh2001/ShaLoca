package com.example.hp_bdn.shaloca.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hp_bdn.shaloca.constant.EntityConstant;
import com.example.hp_bdn.shaloca.fragment.FriendFrangment;
import com.example.hp_bdn.shaloca.fragment.MapFragment;
import com.example.hp_bdn.shaloca.fragment.MessengerFragment;

import java.util.ArrayList;


/**
 * Created by bui on 15-Jul-17.
 */

public class FragmentMainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>() ;
    public FragmentMainAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments ;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }
    @Override
    public int getCount() {
        return EntityConstant.NUMBER_FRAGMENT;
    }
}
