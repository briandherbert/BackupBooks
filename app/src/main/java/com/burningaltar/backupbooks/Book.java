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
        String getTitle();

        String getCoverUrl();

        String[] getPages(BookPageMediaType mediaType);
    }

    public static class RockyAndRubble implements IBook {
        @Override
        public String getTitle() {
            return "Rock and Rubble";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/w4tmmyA.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/o5W4w3l.jpg",
                            "http://i.imgur.com/HWAX1B4.jpg",
                            "http://i.imgur.com/bDHe5Ro.jpg",
                            "http://i.imgur.com/q5wOGYn.jpg",
                            "http://i.imgur.com/Fd4PqRT.jpg"};

                case text:
                    return new String[]{"Ryder gets a call that a train is stuck on the tracks. He alerts the PAW Patrol!",
                            "Rocky and Rubble are ready to help.",
                            "Rocky will find spare parts to fix the train. \"Don't lose it- reuse it!\" he shouts.",
                            "\"I can dig it,\" Rubble says as he moves a boulder off the tracks.",
                            "\"Great work, Rubble and Rocky,\" says Ryder. \"You make a pup-tacular team!\""};

                default:
                    return null;
            }
        }
    }

    public static class ThomasAbc implements IBook {
        @Override
        public String getTitle() {
            return "Thomas' ABC";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/9ZayF1Q.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/yxxDohE.jpg",
                            "http://i.imgur.com/L4j2sWq.jpg",
                            "http://i.imgur.com/QB9l999.jpg",
                            "http://i.imgur.com/B5d4J1m.jpg",
                            "http://i.imgur.com/dBYfkBd.jpg",
                            "http://i.imgur.com/QREAgLu.jpg",
                            "http://i.imgur.com/tiPPvfK.jpg"};

                default:
                    return null;
            }
        }
    }
}
