package com.example.hp_bdn.shaloca.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.adapter.FrangmentFiendAdapter;
import com.example.hp_bdn.shaloca.adapter.FriendRecyclerViewAdapter;
import com.example.hp_bdn.shaloca.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bui on 15-Jul-17.
 */

public class FriendFrangment extends Fragment{
    private FrangmentFiendAdapter frangmentFiendAdapter ;
    private  ArrayList<Fragment> fragments ;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    // contructor
    public static FriendFrangment newInstance() {
        FriendFrangment fragmentFirst = new FriendFrangment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_frangment, container, false);
        innitFragmentFriendAdapter();
        tabLayout = (TabLayout) view.findViewById(R.id.tablayoutFriend);
        viewPager = (ViewPager) view.findViewById(R.id.viewpagerFriend);
        viewPager.setAdapter(frangmentFiendAdapter);
        tabLayout.setupWithViewPager(viewPager);
        createTablayoutIcon();
        return  view ;
    }

    private void createTablayoutIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_fiend);
        tabLayout.getTabAt(0).setText("ABC");
        tabLayout.getTabAt(1).setIcon(R.drawable.iconapp);

        tabLayout.getTabAt(2).setIcon(R.drawable.ic_person);

    }

    private void innitFragmentFriendAdapter() {
        fragments = new ArrayList<>();
        fragments.add(AllFriendFrament.newInstance());
        fragments.add(RequestFriendFragment.newInstance());
        fragments.add(AnserFriendFragment.newInstance());

        frangmentFiendAdapter = new FrangmentFiendAdapter(getFragmentManager(),fragments);
    }


}
