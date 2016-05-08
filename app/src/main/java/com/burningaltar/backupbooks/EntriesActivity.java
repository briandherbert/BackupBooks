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

    static final String FIREBASE_ROOT = "https://crackling-fire-4753.firebaseio.com/";
    static final String FIREBASE_REF_BOOKLIST = "backupbooks";

    static final String CHILD_BOOK_DIRECTORY = "directory";
    static final String CHILD_BOOKS = "books";

    Firebase mFirebaseBooklistRef = new Firebase(FIREBASE_ROOT + FIREBASE_REF_BOOKLIST);

    static final String TITLE_COVER_DELIMETER = "__";

    private static final Map<String, String> FORBIDDEN_TO_REPLACEMENT = new HashMap<>();
    static {
        FORBIDDEN_TO_REPLACEMENT.put(".", "*DOT*");
        FORBIDDEN_TO_REPLACEMENT.put("http://", "*HTTP*");
        FORBIDDEN_TO_REPLACEMENT.put("/", "*SLASH*");
    }

    Button mBtnSubmit;
    TextView mLblInfo;

    // SUPER DUPER IMPORTANT
    final Book.IBook book = new Book.ThomasAbc();

    final String bookId = getBookId(book);

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

        mLblInfo.setText("Submitting book: " + book.getTitle() + "\nPages:\n" + book.getPages(Book.BookPageMediaType.image));
    }

    /**
     * Submit the book as a list entry in the root node. Success kicks of submitting additional
     * info like page urls, text, and audio
     */
    public void submit() {
        mFirebaseBooklistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "successfully added new book");

                Log.v(TAG, "data snapshot" + dataSnapshot.getValue());

                submitBookDetails();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "failed to add new book");
            }
        });

        Log.v(TAG, "Submitting book " + bookId);
        mFirebaseBooklistRef.child(CHILD_BOOK_DIRECTORY).push().setValue(bookId);
    }

    public void submitBookDetails() {
        Firebase bookDetailsRef = mFirebaseBooklistRef.child(CHILD_BOOKS).child(bookId);
        bookDetailsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "successfully added book details");

                Log.v(TAG, "data snapshot" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v(TAG, "failed to add new book");
            }
        });

        for (Book.BookPageMediaType mediaType : Book.BookPageMediaType.values()) {
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

    public static String getBookId(Book.IBook book) {
        String bookId = book.getTitle() + TITLE_COVER_DELIMETER + book.getCoverUrl();

        for (String forbidden : FORBIDDEN_TO_REPLACEMENT.keySet()) {
            bookId = bookId.replace(forbidden, FORBIDDEN_TO_REPLACEMENT.get(forbidden));
        }

        return bookId;
    }
}
