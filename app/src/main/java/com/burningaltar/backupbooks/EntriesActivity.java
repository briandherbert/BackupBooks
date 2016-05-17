package com.burningaltar.backupbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EntriesActivity extends AppCompatActivity {
    public static final String TAG = EntriesActivity.class.getSimpleName();

    Firebase mFirebaseCatalogRef = new Firebase(FirebaseHelper.FIREBASE_ROOT + FirebaseHelper.FIREBASE_REF_BOOKS_APP);

    Button mBtnSubmit;
    TextView mLblInfo;

    // SUPER DUPER IMPORTANT
    final Library.IBook book = new Library.ThomasGoTrainGo();

    final String bookId = Library.getBookId(book);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);

        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mLblInfo = (TextView) findViewById(R.id.lbl_info);

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        mLblInfo.setText("Submitting book: " + book.getTitle() + "\nPages: " + book.getPages(Library.BookPageMediaType.image).length);
    }

    /**
     * Submit the book as a list entry in the root node. Success kicks of submitting additional
     * info like page urls, text, and audio
     */
    public void submit() {
        mFirebaseCatalogRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "successfully added new book\nData snapshot:" + dataSnapshot.getValue());

                submitBookDetails();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "failed to add new book");
            }
        });

        Log.v(TAG, "Submitting book " + bookId);
        mFirebaseCatalogRef.child(FirebaseHelper.CHILD_BOOK_DIRECTORY).push().setValue(bookId);
    }

    public void submitBookDetails() {
        Firebase bookDetailsRef = mFirebaseCatalogRef.child(FirebaseHelper.CHILD_BOOKS).child(bookId);
        bookDetailsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "successfully added book details\nData snapshot: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "failed to add new book");
            }
        });

        bookDetailsRef.child(FirebaseHelper.CHILD_AUTHOR).setValue(book.getAuthor());

        for (Library.BookPageMediaType mediaType : Library.BookPageMediaType.values()) {
            String[] values = book.getPages(mediaType);

            if (values != null && values.length > 0) {
                Map<String, Object> pageIdxToValue = new HashMap<String, Object>();
                for (int i = 0; i < values.length; i++) {
                    pageIdxToValue.put(String.valueOf(i), values[i]);
                }

                bookDetailsRef.child(mediaType.toString()).setValue(pageIdxToValue);
            }
        }
    }

}
