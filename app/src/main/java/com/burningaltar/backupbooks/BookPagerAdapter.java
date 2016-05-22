package com.burningaltar.backupbooks;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by bherbert on 5/9/16.
 */
public class BookPagerAdapter extends PagerAdapter {
    static final String TAG = BookPagerAdapter.class.getSimpleName();

    List<String> mUrls;

    LayoutInflater mInflater;

    final Point mScreenSize = new Point();

    public BookPagerAdapter(Activity context, List<String> urls) {
        this.mUrls = urls;

        mInflater = LayoutInflater.from(context);

        Display display = context.getWindowManager().getDefaultDisplay();
        display.getSize(mScreenSize);

        Log.v(TAG, "screen size " + mScreenSize);
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup parent, final int position) {
        ViewGroup layout = (ViewGroup) mInflater.inflate(R.layout.book_page_item2, parent, false);
        parent.addView(layout);

        ImageLoaderLib.loadImage(layout, mUrls.get(position), mScreenSize.x, mScreenSize.y);

        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
