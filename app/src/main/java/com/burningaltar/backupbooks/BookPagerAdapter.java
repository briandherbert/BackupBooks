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
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bherbert on 5/9/16.
 */
public class BookPagerAdapter extends PagerAdapter {
    static final String TAG = BookPagerAdapter.class.getSimpleName();

    Picasso mPicasso;

    List<String> mUrls;

    LayoutInflater mInflater;

    final Point mScreenSize = new Point();

    public BookPagerAdapter(Activity context, List<String> urls) {
        mPicasso = Picasso.with(context);
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
//        ViewGroup layout = (ViewGroup) mInflater.inflate(R.layout.book_page_item, parent, false);
//        parent.addView(layout);
//
//        final ImageView img = (ImageView) layout.findViewById(R.id.img_page);
//
//        Target target = new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                Log.v(TAG, "loaded bitmap, size " + bitmap.getWidth() + " by " + bitmap.getHeight());
//
//                img.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//                img.setImageDrawable(errorDrawable);
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        };
//
//        img.setTag(target);
//
//        mPicasso.load(mUrls.get(position))
//                .resize(mScreenSize.x, mScreenSize.y)
//                .centerInside()
//                .onlyScaleDown()
//                .error(new ColorDrawable(Color.RED))
//                .into(target);


        ViewGroup layout = (ViewGroup) mInflater.inflate(R.layout.book_page_item, parent, false);
        parent.addView(layout);

        ImageLoaderLib.loadImage(layout, parent.getContext(), mUrls.get(position), mScreenSize.x, mScreenSize.y);

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
