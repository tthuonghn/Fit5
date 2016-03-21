package com.techkids.thangduong.fit5.CommonView;

/**
 * Created by ThangDuong on 05-Mar-16.
 */
public class WorkoutTypeObject {
    String name;
    int warmupTime;
    int normalTimeEachSet;
    int rushTimeEachSet;
    int repeat;
    int imgID;
    int normalIntensity=0;
    int rushIntensity=0;

    public WorkoutTypeObject(String name, int warmupTime, int normalTimeEachSet, int rushTimeEachSet, int repeat, int imgID) {
        this.name = name;
        this.warmupTime = warmupTime;
        this.normalTimeEachSet = normalTimeEachSet;
        this.rushTimeEachSet = rushTimeEachSet;
        this.repeat = repeat;
        this.imgID = imgID;
    }
    public WorkoutTypeObject(String name, int warmupTime, int normalTimeEachSet
            , int rushTimeEachSet, int repeat,int normalIntensity,int rushIntensity) {
        this.name = name;
        this.warmupTime = warmupTime;
        this.normalTimeEachSet = normalTimeEachSet;
        this.rushTimeEachSet = rushTimeEachSet;
        this.repeat = repeat;
        this.normalIntensity=normalIntensity;
        this.rushIntensity=rushIntensity;
    }

    public int getNormalIntensity() {
        return normalIntensity;
    }

    public void setNormalIntensity(int normalIntensity) {
        this.normalIntensity = normalIntensity;
    }

    public int getRushIntensity() {
        return rushIntensity;
    }

    public void setRushIntensity(int rushIntensity) {
        this.rushIntensity = rushIntensity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarmupTime() {
        return warmupTime;
    }

    public void setWarmupTime(int warmupTime) {
        this.warmupTime = warmupTime;
    }

    public int getNormalTimeEachSet() {
        return normalTimeEachSet;
    }

    public void setNormalTimeEachSet(int normalTimeEachSet) {
        this.normalTimeEachSet = normalTimeEachSet;
    }

    public int getRushTimeEachSet() {
        return rushTimeEachSet;
    }

    public void setRushTimeEachSet(int rushTimeEachSet) {
        this.rushTimeEachSet = rushTimeEachSet;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
