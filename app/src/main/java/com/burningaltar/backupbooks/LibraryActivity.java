package com.burningaltar.backupbooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by bherbert on 5/9/16.
 */
public class LibraryActivity extends AppCompatActivity implements CoversAdapter.Listener, FirebaseHelper.LibraryFetcherListener {
    public static final String TAG = LibraryActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    CoversAdapter mAdapter;

    ArrayList<String> mBookIds = new ArrayList<>();

    static final int NUM_COLS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mAdapter = new CoversAdapter(this, this, NUM_COLS);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_covers);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, NUM_COLS);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if (Constants.FETCH_FROM_FIREBASE) {
            FirebaseHelper.fetchBookIds(this);
        } else {
            for (Library.IBook book : Library.BOOKS) {
                mBookIds.add(Library.getBookId(book));
            }

            onGotBookIds(mBookIds);
        }
    }

    @Override
    public void onItemClicked(View v) {
        int idx = mRecyclerView.getChildLayoutPosition(v);
        Log.v(TAG, "clicked " + mBookIds.get(idx));
        startActivity(BookActivity.createIntent(this, mBookIds.get(idx)));
    }

    @Override
    public void onGotBookIds(Collection<String> bookIds) {
        mBookIds.addAll(bookIds);
        mAdapter.add(mBookIds);
    }
}
