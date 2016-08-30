package com.example.ienning.ncuhome.db;

/**
 * Created by ienning on 16-7-16.
 */
public class ItemArticle {
    private int source;
    private int index;
    private String name;
    public ItemArticle(int index, String name, int source) {
        this.index = index;
        this.name=  name;
        this.source = source;
    }

    public int getIndex() {
        return index;
    }

    public int getSource() {
        return source;
    }

    public String getName() {
        return name;
    }
}
