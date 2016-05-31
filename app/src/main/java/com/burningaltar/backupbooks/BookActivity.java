package com.burningaltar.backupbooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Arrays;
/**
 * Created by bherbert on 5/9/16.
 */
public class BookActivity extends Activity implements FirebaseHelper.BookFetcherListener {
    public static final String TAG = BookActivity.class.getSimpleName();

    public static final String ARG_BOOK_ID = "ARG_BOOK_ID";

    ViewPager mPager;
    View mBtnRead;

    Library.IBook mBook = null;
    String[] mPageTexts;

    TextToSpeech tts = null;

    boolean mCanReadAloud = false;

    public static Intent createIntent(Context context, String bookId) {
        Intent intent = new Intent(context, BookActivity.class);
        intent.putExtra(ARG_BOOK_ID, bookId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        mPager = (ViewPager) findViewById(R.id.pager_book);
        mBtnRead = findViewById(R.id.btn_read);

        mBtnRead.setOnClickListener(mReadAloudListener);

        String bookId = getIntent().getStringExtra(ARG_BOOK_ID);
        Log.v(TAG, "book id is " + bookId);

        if (Constants.FETCH_FROM_FIREBASE) {
            FirebaseHelper.fetchBook(bookId, this);
        } else {
            onGotBook(Library.getBook(bookId));
        }
    }

    @Override
    public void onGotBook(Library.IBook book) {
        if (book == null) {
            Log.e(TAG, "No book!");
            return;
        }

        Log.v(TAG, "got book data " + book.getTitle());

        mBook = book;

        // Make the text easier to get at
        mPageTexts = book.getPages(Library.BookPageMediaType.text);

        mPager.setAdapter(new BookPagerAdapter(BookActivity.this, Arrays.asList(book.getPages(Library.BookPageMediaType.image))));

        String[] texts = book.getPages(Library.BookPageMediaType.text);
        if (texts != null && texts.length > 0) {
            mBtnRead.setVisibility(View.VISIBLE);

            tts = new TextToSpeech(BookActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    tts.setSpeechRate(0.8f);
                    mCanReadAloud = true;
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) tts.shutdown();
    }

    final View.OnClickListener mReadAloudListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(TAG, "Clicked to listen");
            int idx = mPager.getCurrentItem();

            String text = "";
            if (mPageTexts != null && mPageTexts.length > idx) {
                Log.v(TAG, "Listening");

                text = mPageTexts[idx];
            } else if (idx == mPageTexts.length) {
                text = getString(R.string.the_end);
            }

            if (tts != null && text != null && !text.isEmpty()) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    };
}
