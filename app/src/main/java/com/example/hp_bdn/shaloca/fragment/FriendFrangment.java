package com.example.hp_bdn.shaloca.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.adapter.FriendRecyclerViewAdapter;
import com.example.hp_bdn.shaloca.model.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bui on 15-Jul-17.
 */

public class FriendFrangment extends Fragment implements FriendRecyclerViewAdapter.FriendRecyclerListener {
    private RecyclerView recyclerViewFriend ;
    private List<Friend> friends ;
    private FriendRecyclerViewAdapter friendRecyclerViewAdapter ;
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
        recyclerViewFriend = (RecyclerView) view.findViewById(R.id.recyclerFriend);
        initRecyclerView();
        return  view ;
    }

    private void initRecyclerView() {
        getListFriend();
        friendRecyclerViewAdapter = new FriendRecyclerViewAdapter(getContext() , friends , this);
        recyclerViewFriend.setAdapter(friendRecyclerViewAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerViewFriend.setLayoutManager(mLayoutManager);
        recyclerViewFriend.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFriend.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

    }

    public void getListFriend() {
        friends = new ArrayList<>();
        friends.add(new Friend("123" , "An Cong" ,"https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-0/s480x480/20292783_1346500892132189_8283157758875551946_n.jpg?oh=d6795a4e1c55266e1fbbb239e5b44af7&oe=59FA0392",
                null ,true));
        friends.add(new Friend("1234" , "Bui Danh Nam" ,"https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-1/p160x160/19437457_1872150149703949_8821183849301406587_n.jpg?oh=3451ddb7b3d75e2b196f3315f2fd79ae&oe=5A0CD659",
                null ,true));
        friends.add(new Friend("344" , "Mỹ Mỹ " , "https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-1/c0.19.160.160/p160x160/18835952_810860239082547_6405339634409304949_n.jpg?oh=37536275bd61244edf477caec6d71505&oe=59FA2CC5",
                null ,false));
        friends.add(new Friend("2343" , "No Name" ,"https://scontent.fhan2-2.fna.fbcdn.net/v/t1.0-1/p160x160/18813366_1086689294809251_7998092218316391926_n.jpg?oh=d46913c06008356fee3c0668626d03ff&oe=59FB18BE",
                null , false));
    }

    @Override
    public void onIconSendMessegerClick(int pos) {
//        Toast.makeText(getContext(), "click icon sendmesseng" + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIconMoreClick(int pos) {
        Toast.makeText(getContext(), "click icon more" + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickListener(int pos) {
        Toast.makeText(getContext(),friends.get(pos).getName()+" "+ friends.get(pos).getId() , Toast.LENGTH_SHORT).show();
    }
}
