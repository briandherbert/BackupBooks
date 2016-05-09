package com.burningaltar.backupbooks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by bherbert on 5/9/16.
 */
public class LibraryActivity extends AppCompatActivity {
    public static final String TAG = LibraryActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    CoversAdapter mAdapter;

    Firebase mFirebaseCatalogRef = new Firebase(Helper.FIREBASE_ROOT + Helper.FIREBASE_REF_BOOKS_APP);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mAdapter = new CoversAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_covers);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        getDirectory();
    }

    public void getDirectory() {
        Log.v(TAG, "Get all books");
        mFirebaseCatalogRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "Books list:\nData snapshot:" + dataSnapshot.getValue() + " type " + dataSnapshot.getValue().getClass());
                HashMap<String, HashMap<String, String>> map = (HashMap) dataSnapshot.getValue();

                Log.v(TAG, "has directory " + map.get(Helper.CHILD_BOOK_DIRECTORY));

                HashMap<String, String> bookIdsMap = map.get(Helper.CHILD_BOOK_DIRECTORY);
                mAdapter.add(bookIdsMap.values());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "failed to add new book");
            }
        });
    }
}
