package com.elegion.recyclertest.mock;

public class Mock {

    private String mName;

    private int mValue;

    public Mock(String name, int value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getValue() {
        return String.valueOf(mValue);
    }

    public void setValue(int value) {
        mValue = value;
    }
}
