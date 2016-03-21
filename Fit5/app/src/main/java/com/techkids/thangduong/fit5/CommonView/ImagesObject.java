package com.techkids.thangduong.fit5.CommonView;

/**
 * Created by ThangDuong on 04-Mar-16.
 */
public class ImagesObject {
    int imageView1;
    int imageView2;
    boolean isAvailable;

    public int getImageView1() {
        return imageView1;
    }

    public void setImageView1(int imgViewSrc1) {
        imageView1=imgViewSrc1;
    }

    public int getImageView2() {
        return imageView2;
    }

    public void setImageView2(int imgViewSrc2) {
        imageView2=imgViewSrc2;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public ImagesObject() {
        isAvailable = true;
    }

    public ImagesObject(int imgViewSrc1,int imgViewSrc2) {
        this.imageView1=imgViewSrc1;
        this.imageView2=imgViewSrc2;
        isAvailable = true;
    }
}
