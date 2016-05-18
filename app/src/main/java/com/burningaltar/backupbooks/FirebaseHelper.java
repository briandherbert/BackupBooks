package com.burningaltar.backupbooks;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bherbert on 5/9/16.
 */
public class FirebaseHelper {
    static final String TAG = FirebaseHelper.class.getSimpleName();

    static final String FIREBASE_ROOT = "https://crackling-fire-4753.firebaseio.com/";
    static final String FIREBASE_REF_BOOKS_APP = "backupbooks";

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();


    static final String CHILD_BOOK_DIRECTORY = "directory";
    static final String CHILD_BOOKS = "books";

    static final String CHILD_AUTHOR = "author";

    private static FirebaseHelper mInstance = new FirebaseHelper();

    public interface BookFetcherListener {
        void onGotBook(Library.IBook book);
    }

    public interface LibraryFetcherListener {
        void onGotBookIds(Collection<String> bookIds);
    }

    private FirebaseHelper() {

    }

    public static FirebaseHelper getInstance() {
        return mInstance;
    }

    private static class FetchedBook implements Library.IBook {
        public String title = "";
        public String coverUrl = "";
        List<String>[] pageMedia = new List[Library.BookPageMediaType.values().length];
        public String author = "";

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getCoverUrl() {
            return coverUrl;
        }

        @Override
        public String[] getPages(Library.BookPageMediaType mediaType) {
            return pageMedia[mediaType.ordinal()].toArray(new String[pageMedia[mediaType.ordinal()].size()]);
        }

        @Override
        public String getAuthor() {
            return null;
        }
    }

    public static DatabaseReference getRootRef() {
        return FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_ROOT + "/" + FIREBASE_REF_BOOKS_APP);
    }

    public static void fetchBook(final String bookId, @NonNull final BookFetcherListener listener) {
        Log.v(TAG, "Fetching book " + bookId);
        DatabaseReference bookRef = getRootRef().child(FirebaseHelper.CHILD_BOOKS).child(bookId);

        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "got book data " + dataSnapshot.getValue());

                FetchedBook book = new FetchedBook();
                String[] titleAndCoverUrl = Library.getBookTitleAndCoverUrl(bookId);
                book.title = titleAndCoverUrl[0];
                book.coverUrl = titleAndCoverUrl[1];

                HashMap<String, Object> map = (HashMap) dataSnapshot.getValue();

                for (Library.BookPageMediaType type : Library.BookPageMediaType.values()) {
                    if (map.containsKey(type.name())) {
                        book.pageMedia[type.ordinal()] = (List<String>) map.get(type.name());

                        Log.v(TAG, "media type " + type + " " + book.pageMedia[type.ordinal()]);
                    }
                }

                listener.onGotBook(book);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v(TAG, "failed to get book data");
                listener.onGotBook(null);
            }
        });
    }

    public static void fetchBookIds(@NonNull final LibraryFetcherListener listener) {
        Log.v(TAG, "Get all books");
        getRootRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v(TAG, "Books list:\nData snapshot:" + dataSnapshot.getValue() + " type " + dataSnapshot.getValue().getClass());
                HashMap<String, HashMap<String, String>> map = (HashMap) dataSnapshot.getValue();

                Log.v(TAG, "has directory " + map.get(FirebaseHelper.CHILD_BOOK_DIRECTORY));

                HashMap<String, String> bookIdsMap = map.get(FirebaseHelper.CHILD_BOOK_DIRECTORY);

                listener.onGotBookIds(bookIdsMap.values());
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                listener.onGotBookIds(null);
            }
        });
    }
}
