package com.example.childcontrol.childcontrol;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserType {

    public static final String PARENT_USER_TYPE = "parent";
    public static final String CHILD_USER_TYPE = "child";

    private String type;

    @Generated(hash = 2116731890)
    public UserType(String type) {
        this.type = type;
    }

    @Generated(hash = 2007591099)
    public UserType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
