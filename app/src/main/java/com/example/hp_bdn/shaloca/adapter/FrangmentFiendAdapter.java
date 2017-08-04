package com.example.hp_bdn.shaloca.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hp_bdn.shaloca.constant.EntityConstant;

import java.util.ArrayList;

/**
 * Created by HP_BDN on 01-Aug-17.
 */

public class FrangmentFiendAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>() ;
    public FrangmentFiendAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
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
        return fragments.size();
    }

}
