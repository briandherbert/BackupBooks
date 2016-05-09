package com.burningaltar.backupbooks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

;

/**
 * Created by bherbert on 4/29/16.
 */
public class CoversAdapter extends RecyclerView.Adapter<CoversAdapter.GifHolder> {
    static final String TAG = CoversAdapter.class.getSimpleName();

    ArrayList<String> mUrls = new ArrayList<>();
    ArrayList<String> mTitles = new ArrayList<>();

    Picasso mPicasso;

    public CoversAdapter(Context context) {
        mPicasso = Picasso.with(context);
    }

    @Override
    public GifHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GifHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_item, parent, false));
    }

    @Override
    public void onBindViewHolder(GifHolder holder, int position) {
        mPicasso.load(mUrls.get(position)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    public static class GifHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public GifHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img_cover);
        }
    }

    public void add(Collection<String> bookIds) {
        for (String bookId : bookIds) {
            String[] parts = Helper.getBookTitleAndCoverUrl(bookId);
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
