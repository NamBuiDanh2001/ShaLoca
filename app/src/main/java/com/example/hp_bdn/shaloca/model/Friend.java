package com.example.hp_bdn.shaloca.model;

import java.util.Date;

/**
 * Created by HP_BDN on 26-Jul-17.
 */

public class Friend {
    private String id ;
    private String name ;
    private String image ;
    private Date birthday ;
    private boolean isOnline ;

    public Friend() {
    }

    public Friend(String id, String name, String image, Date birthday, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.birthday = birthday;
        this.isOnline = isOnline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
