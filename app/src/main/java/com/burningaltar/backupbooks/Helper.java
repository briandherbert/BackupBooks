package com.burningaltar.backupbooks;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by bherbert on 5/9/16.
 */
public class Helper {
    static final String FIREBASE_ROOT = "https://crackling-fire-4753.firebaseio.com/";
    static final String FIREBASE_REF_BOOKS_APP = "backupbooks";

    static final String CHILD_BOOK_DIRECTORY = "directory";
    static final String CHILD_BOOKS = "books";

    static final String TITLE_COVER_DELIMETER = "__";

    private static final String[][] FORBIDDEN_TO_REPLACEMENT = new String[][] {
            {".", "*DOT*"},
            {"http://", "*HTTP*"},
            {"/", "*SLASH*"}

    };

    private static Helper mInstance = new Helper();

    private Helper() {

    }

    public static Helper getInstance() {
        return mInstance;
    }

    /**
     * Munge the title and cover url, and replace any chars not supported by Firebase
     * @param book
     * @return
     */
    public static String getBookId(Book.IBook book) {
        String bookId = book.getTitle() + TITLE_COVER_DELIMETER + book.getCoverUrl();

        for (int i = 0; i < FORBIDDEN_TO_REPLACEMENT.length; i++) {
            bookId = bookId.replace(FORBIDDEN_TO_REPLACEMENT[i][0], FORBIDDEN_TO_REPLACEMENT[i][1]);
        }

        return bookId;
    }

    public static String[] getBookTitleAndCoverUrl(String bookId) {
        for (int i = 0; i < FORBIDDEN_TO_REPLACEMENT.length; i++) {
            bookId = bookId.replace(FORBIDDEN_TO_REPLACEMENT[i][1], FORBIDDEN_TO_REPLACEMENT[i][0]);
        }

        return bookId.split(TITLE_COVER_DELIMETER);
    }

    public static Firebase getAppRef() {
        return new Firebase(Helper.FIREBASE_ROOT + Helper.FIREBASE_REF_BOOKS_APP);
    }
}
