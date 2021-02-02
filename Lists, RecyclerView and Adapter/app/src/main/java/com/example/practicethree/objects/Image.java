package com.example.practicethree.objects;

public class Image extends RecyclerObject {

    private int resId;

    public Image(int resId) {
        super();
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
