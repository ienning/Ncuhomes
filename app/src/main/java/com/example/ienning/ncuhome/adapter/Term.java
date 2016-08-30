package com.example.ienning.ncuhome.adapter;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by ienning on 16-8-14.
 */
public class Term implements IPickerViewData{
    private long id;
    private String name;
    private String term;
    public Term(long id, String name, String term) {
        this.id = id;
        this.name = name;
        this.term = term;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
