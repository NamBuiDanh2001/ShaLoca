package com.example.hp_bdn.shaloca.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.model.Friend;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HP_BDN on 26-Jul-17.
 */

public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHolder> {
    private Context context ;
    private List<Friend> friends ;
    private FriendRecyclerListener friendRecyclerListener ;

    public FriendRecyclerViewAdapter(Context context, List<Friend> friends , FriendRecyclerListener listener) {
        this.context = context;
        this.friends = friends;
        this.friendRecyclerListener = listener ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_friend , parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
              Friend friend = friends.get(position);
              holder.tvName.setText(friend.getName());
              Glide.with(context).load(friend.getImage()).centerCrop().error(android.R.drawable.ic_delete)
                      .into(holder.circleAvata);
              if(friend.isOnline()){
                  holder.imgviewIsOnline.setVisibility(View.GONE);
              }
              else {
                  holder.imgviewIsOnline.setVisibility(View.VISIBLE);
              }
              holder.imageViewMore.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      friendRecyclerListener.onIconMoreClick(position);
                  }
              });
//              moreOnclicklistener(holder , position);
              sendMessengerOnclicklistener(holder , position);

    }

    private void sendMessengerOnclicklistener(MyViewHolder holder, final int position) {
        holder.imgiewSendmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendRecyclerListener.onIconSendMessegerClick(position);
                Toast.makeText(context, "click icon sendmesseng" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       public ImageView imgviewIsOnline , imgiewSendmess , imageViewMore ;
       public TextView tvName ;
        public CircleImageView circleAvata ;

        public MyViewHolder(View View) {
            super(View);

            tvName = (TextView) View.findViewById(R.id.tvUserName);
            imgviewIsOnline = (ImageView) View.findViewById(R.id.imageOnline);
            imgiewSendmess = (ImageView) View.findViewById(R.id.ImagesendMess);
            imageViewMore = (ImageView) View.findViewById(R.id.imagemore);
            circleAvata = (CircleImageView) View.findViewById(R.id.image_avata);
            View.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
             friendRecyclerListener.onItemClickListener(getAdapterPosition());
        }
    }
    public interface  FriendRecyclerListener {
        void onIconSendMessegerClick(int pos);
        void onIconMoreClick(int pos);
        void onItemClickListener(int pos);
    }
}
