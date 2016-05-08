package com.burningaltar.backupbooks;

/**
 * Created by bherbert on 5/8/16.
 */
public class Book {
    static final String DEFAULT_IMAGE_DOMAIN = "http://imgur.com/";

    public enum BookPageMediaType {
        image,
        text,
        audio
    }

    public interface IBook {
        public String getTitle();

        public String getCoverUrl();

        public String[] getPages(BookPageMediaType mediaType);
    }

    public static class ThomasAbc implements IBook {

        @Override
        public String getTitle() {
            return "Thomas' ABC";
        }

        @Override
        public String getCoverUrl() {
            return "http://imgur.com/9ZayF1Q";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://imgur.com/yxxDohE",
                            "http://imgur.com/L4j2sWq",
                            "http://imgur.com/QB9l999",
                            "http://imgur.com/B5d4J1m",
                            "http://imgur.com/dBYfkBd",
                            "http://imgur.com/QREAgLu",
                            "http://imgur.com/tiPPvfK"};

                default:
                    return null;

            }
        }
    }
}
