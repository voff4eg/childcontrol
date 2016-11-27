package com.example.childcontrol.childcontrol;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class UserType {

    public static final String PARENT_USER_TYPE = "parent";
    public static final String CHILD_USER_TYPE = "child";

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String type;

    @Generated(hash = 757177013)
    public UserType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @Generated(hash = 2007591099)
    public UserType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
