package com.lsieun.tank.logic;

import java.util.Date;

import com.lsieun.tank.action.Visible;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.util.UUIDGenerator;

public abstract class GameElement implements Visible {

    protected String id;
    protected String name;
    protected Point center;
    protected Group group;
    protected Date createTime;

    public GameElement(String name, Point center, Group group) {
        this.id = UUIDGenerator.getUUID();
        this.name = name;
        this.center = center;
        this.group = group;
        this.createTime = new Date();
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

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GameElement{" +
                "\n    id='" + id + '\'' +
                ", \n    name='" + name + '\'' +
                ", \n    center=" + center +
                ", \n    group=" + group +
                ", \n    createTime=" + createTime +
                "\n}\n\n";
    }
}
