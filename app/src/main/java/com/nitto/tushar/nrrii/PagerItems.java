package com.nitto.tushar.nrrii;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by masud on 2/5/2018.
 */

public class PagerItems <T extends PagerItem> extends ArrayList<T> {
    private final Context context;

    protected PagerItems(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}