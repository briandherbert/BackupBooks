package com.burningaltar.backupbooks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bherbert on 4/29/16.
 */
public class CoversAdapter extends RecyclerView.Adapter<CoversAdapter.CoverHolder> {
    static final String TAG = CoversAdapter.class.getSimpleName();

    ArrayList<String> mUrls = new ArrayList<>();
    ArrayList<String> mTitles = new ArrayList<>();

    Picasso mPicasso;

    Listener mListener;

    final Point mScreenSize = new Point();

    int mItemSize;

    LayoutInflater mInflater;

    public interface Listener {
        void onItemClicked(View v);
    }

    public CoversAdapter(Activity context, Listener listener, int numCols) {
        mPicasso = Picasso.with(context);
        mPicasso.setIndicatorsEnabled(false);
        mPicasso.setLoggingEnabled(true);

        mInflater = LayoutInflater.from(context);

        mListener = listener;

        Display display = context.getWindowManager().getDefaultDisplay();
        display.getSize(mScreenSize);

        // figure out item size; lame

        mItemSize = mScreenSize.x / numCols;
        Log.v(TAG, "Screen width is " + mScreenSize.x + " item size " + mItemSize);
    }

    @Override
    public CoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cover_item, parent, false);
        view.getLayoutParams().width = mItemSize;
        view.getLayoutParams().height = mItemSize;

        CoverHolder holder = new CoverHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onItemClicked(v);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final CoverHolder holder, int position) {
        Target target = new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Log.v(TAG, "loaded bitmap, size " + bitmap.getWidth() + " by " + bitmap.getHeight());

                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        holder.img.setImageDrawable(errorDrawable);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                };

        holder.img.setTag(target);

        mPicasso.load(mUrls.get(position))
                .resize(mItemSize, mItemSize)
                .centerInside()
                .onlyScaleDown()
                .error(new ColorDrawable(Color.RED))
                .into(target);
    }


    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    public static class CoverHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public CoverHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img_cover);
        }
    }

    public void add(Collection<String> bookIds) {
        for (String bookId : bookIds) {
            String[] parts = Library.getBookTitleAndCoverUrl(bookId);
            Log.v(TAG, "adding book " + parts[0] + " " + parts[1]);

            mTitles.add(parts[0]);
            mUrls.add(parts[1]);
        }

        notifyItemRangeInserted(mUrls.size(), mUrls.size() + bookIds.size());
    }

    public void clear() {
        mUrls.clear();
        notifyDataSetChanged();
    }
}
