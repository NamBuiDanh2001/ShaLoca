package com.example.hp_bdn.shaloca.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hp_bdn.shaloca.R;
import com.example.hp_bdn.shaloca.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP_BDN on 22-Jul-17.
 */

public class ListResultSearchPlaceAdapter extends ArrayAdapter<Place>{

    private ArrayList<Place> PlaceList = null ;
    private  Context context ;
    private  int res ;
    public ListResultSearchPlaceAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Place> objects) {
        super(context, resource, objects);
        this.PlaceList = objects ;
        this.context = context ;
        this.res = resource ;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHoder viewHoder = null ;
        if(convertView== null){
            viewHoder = new ViewHoder();
            convertView = LayoutInflater.from(context).inflate(res, parent , false);
            viewHoder.imageView = (ImageView) convertView.findViewById(R.id.image_item_search);
            viewHoder.textViewNoiDung = (TextView) convertView.findViewById(R.id.tv_name_place_search);
            convertView.setTag(viewHoder);
        }
        else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        Place place = new Place();
         place = PlaceList.get(position);

        viewHoder.textViewNoiDung.setText(place.getName_place());

        return convertView ;
    }

    class  ViewHoder{
        TextView textViewNoiDung ;
        ImageView imageView ;
    }



}


