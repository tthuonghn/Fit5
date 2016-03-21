package com.techkids.thangduong.fit5.CommonView;


/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class ObjectForAdapter extends ImagesObject{
    String title;
    String description;
    String name = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public ObjectForAdapter() {
        super();
        title="";
        description="";
    }
    public ObjectForAdapter(String title, String description) {
        super();
        this.title=title;
        this.description=description;
    }

    public ObjectForAdapter(int imgViewSrc1, int imgViewSrc2, String title, String description) {
        super(imgViewSrc1, imgViewSrc2);
        this.title=title;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
