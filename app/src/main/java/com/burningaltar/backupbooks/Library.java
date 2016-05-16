package com.burningaltar.backupbooks;

/**
 * Created by bherbert on 5/8/16.
 */
public class Library {
    static final String DEFAULT_IMAGE_DOMAIN = "http://imgur.com/";


    private static final String TITLE_COVER_DELIMETER = "__";

    private static final String[][] FORBIDDEN_TO_REPLACEMENT = new String[][] {
            {".", "*DOT*"},
            {"http://", "*HTTP*"},
            {"/", "*SLASH*"}

    };

    /**
     * Munge the title and cover url, and replace any chars not supported by Firebase
     * @param book
     * @return
     */
    public static String getBookId(Library.IBook book) {
        String bookId = book.getTitle() + TITLE_COVER_DELIMETER + book.getCoverUrl();

        for (int i = 0; i < FORBIDDEN_TO_REPLACEMENT.length; i++) {
            bookId = bookId.replace(FORBIDDEN_TO_REPLACEMENT[i][0], FORBIDDEN_TO_REPLACEMENT[i][1]);
        }

        return bookId;
    }

    /** Convert a book id back into its title and cover url */
    public static String[] getBookTitleAndCoverUrl(String bookId) {
        for (int i = 0; i < FORBIDDEN_TO_REPLACEMENT.length; i++) {
            bookId = bookId.replace(FORBIDDEN_TO_REPLACEMENT[i][1], FORBIDDEN_TO_REPLACEMENT[i][0]);
        }

        return bookId.split(TITLE_COVER_DELIMETER);
    }

    static final IBook[] BOOKS = {
            new RockyAndRubble(),
            new ThomasAbc(),
            new PajamaTime(),
            new HowDoDinosaursCountToTen(),
            new APorcupineNamedFluffy(),
            new HandHandFingersThumb()
    };

    public static IBook getBook(String bookId) {
        for (IBook book : BOOKS) {
            if (getBookId(book).equals(bookId)) return book;
        }

        return null;
    }

    public enum BookPageMediaType {
        image,
        text,
        audio
    }

    public interface IBook {
        String getTitle();

        String getCoverUrl();

        String[] getPages(BookPageMediaType mediaType);

        String getAuthor();
    }

    public static class Template implements IBook {
        @Override
        public String getTitle() {
            return "";
        }

        @Override
        public String getCoverUrl() {
            return "";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            ""
                    };
                case text:
                    return new String[]{
                            "",
                            ""
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "";
        }
    }

    public static class PajamaTime implements IBook {
        @Override
        public String getTitle() {
            return "PAJAMA TIME!";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/Q0nkmwh.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/awPFZVG.jpg",
                            "http://i.imgur.com/iRq8lG8.jpg",
                            "http://i.imgur.com/xvtkLMV.jpg",
                            "http://i.imgur.com/DMyWyWR.jpg",
                            "http://i.imgur.com/2cSZvId.jpg",
                            "http://i.imgur.com/Kgr4dQ6.jpg",
                            "http://i.imgur.com/bd6ngvf.jpg",
                            "http://i.imgur.com/jSLFji6.jpg",
                            "http://i.imgur.com/zoUkMA8.jpg",
                            "http://i.imgur.com/sp4b6HQ.jpg"
                    };
                case text:
                    return new String[]{
                            "The moon is up. It's getting late. Let's get ready to celebrate. It's Pajama Time!",
                            "Pull on the bottoms. Put on the top. Get yourself ready to pajama-dee-bop. It's Pajama Time!",
                            "Now, some are old and some are new. Some are red and some are blue.",
                            "Some are fuzzy. Some are not.",
                            "But we can all pajammy in whatever we've got. It's Pajama Time! Ooooo yes, it's Pajama Time!",
                            "Some are pink and some are green. Some are the ugliest you've ever seen.",
                            "They might be stripey, or polka dot. But we can all pajammy in whatever we've got. It's Pajama Time!",
                            "Pajammy to the left. Pajammy to the right. Jamma jamma jamma jamma P! J!" +
                                    "Everybody's wearing them for dancing tonight. Jamma jamma jamma jamma P! J!",
                            "Now all around the room in one big line, wearing our pajamas and looking so fine. It's Pajama Time!",
                            "Hop into bed. Turn out the light. You can have a party in your dreams tonight. " +
                                    "IT'S PAJAMA TIME! Hush. Hush. It's Pajama Time! Hush. Hush. It's Pajama Time! Shhhhhh. good night. sleep tight."
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "Sandra Boynton";
        }
    }

    public static class HowDoDinosaursCountToTen implements IBook {
        @Override
        public String getTitle() {
            return "How Do Dinosaurs Count to Ten?";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/OKeDCHP.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/gl4sNtM.jpg",
                            "http://i.imgur.com/on9rtqN.jpg",
                            "http://i.imgur.com/FyqRTgo.jpg",
                            "http://i.imgur.com/BMACeUB.jpg",
                            "http://i.imgur.com/45pY4lw.jpg",
                            "http://i.imgur.com/8Yx3DRq.jpg"
                    };
                case text:
                    return new String[]{
                            "Dinosaur counting starts with one. One tattered teddy bear just for fun.",
                            "Two big balloons tied to the bed, three toy trucks painted blue, green and red.",
                            "Four balls that bounce, five big letter blocks.",
                            "and under the bed, six dirty socks. A track, an engine, and seven cars.",
                            "an easel with eight full paint jars. Nine pictures hanging on the wall.",
                            "ten books to read— and that is all. Now that he's counted to from one to ten, " +
                                    "how does a dinosaur count again? AGAIN!"
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "Jane Yolen & Mark Teague";
        }
    }

    public static class APorcupineNamedFluffy implements IBook {
        @Override
        public String getTitle() {
            return "A Porcupine Named Fluffy";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/hfKR3iK.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/3WDXaeO.jpg",   // 0
                            "http://i.imgur.com/HqAUoEH.jpg",   // 1
                            "http://i.imgur.com/aCEjlos.jpg",   // 2
                            "http://i.imgur.com/rryAZTY.jpg",   // 3
                            "http://i.imgur.com/dWUkQb4.jpg",   // 4
                            "http://i.imgur.com/X1XJ3v6.jpg",   // 5
                            "http://i.imgur.com/PSZoyEL.jpg",   // 6
                            "http://i.imgur.com/gAJ6AQ2.jpg",   // 7
                            "http://i.imgur.com/gKIfsib.jpg",   // 8
                            "http://i.imgur.com/uxezYiC.jpg",   // 9
                            "http://i.imgur.com/PbPlo1y.jpg",   // 10
                            "http://i.imgur.com/WK2aSnI.jpg",   // 11
                            "http://i.imgur.com/EnQmjIq.jpg",   // 12
                            "http://i.imgur.com/02TfHla.jpg",   // 13
                            "http://i.imgur.com/yJeshhv.jpg",   // 14
                            "http://i.imgur.com/YfGsbdu.jpg",   // 15
                    };
                case text:
                    return new String[]{
                            "When Mr. and Mrs. Porcupine had their first child, they were delighted. Now he needed a name. ",    // 0
                            "Should they call him Spike? No, Spike was too common. " +
                                    "Should they call him Lance? No, Lance sounded too fierce. " +
                                    "Should they call him Needleroozer? No, Needleroozer was too long. " +
                                    "Prickles? Pokey? Quillian? Then together they had an idea." +
                                    "\"Let's call him Fluffy. It's such a pretty name. Fluffy!\"",  // 1
                            "But soon there came a time when Fluffy began to doubt that he was fluffy. " +
                                    "He first became suspicious when he backed into a door and stuck fast. " +
                                    "That was not a fluffy thing to do.",   // 2
                            "He was even more convinced when he accidentally slept on his back and poked holes in the mattress. " +
                                    "A very unfluffy thing to do. " +
                                    "When he tried to carry an umbrella he knew the truth without a doubt.", // 3
                            "So he decided to become fluffier. \"Clouds are fluffy,\" he thought. \"I'll be a cloud.\" " +
                                    "But he couldn't stay up.",  // 4
                            "\"I know. Pillows are fluffy!\" he said. \"I'll be a pillow.\" " +
                                    "But when his mother sat on him, she was not pleased. " +
                                    "He triend soaking in a bubble bath for forty-five minutes, but he did not become fluffy. " +
                                    "He became soggy. ", // 5
                            "He tried whipped cream. He put a little on each quill. It was not easy, " +
                                    "and it took more than half a day. " +
                                    "But this did not make Fluffy fluffy. " +
                                    "\"They should have named me Gooey,\" he sighed.", // 6
                            "He ate a lot of fluffy marshmallows. He rolled in shaving cream and feathers. " +
                                    "He even tried to become a bunny. " +
                                    "But the truch remained. Fluffy wasn't.",   // 7
                            "One afternoon Fluffy set out for a walk, trying to think of ways to become fluffy. " +
                                    "Before long he met a very large rhinoceros.",  // 8
                            "\"Grrrr!,\" said the rhinoceros. \"I'm going to give you a rough time.\" " +
                                    "Fluffy didn't know what a rough time was, but he didn't like the sound of it at all. " +
                                    "\"What is your name, small prickly thing?\" " +
                                    "asked the rhinoceros unkindly. \"Fluffy,\" said Fluffy.",   // 9
                            "The rhinoceros smiled. He giggled. Then he laughed out loud. He rolled on the ground. " +
                                    "He jiggled and slapped his knees. He roared with laughter. " +
                                    "\"A porcupine named Fluffy!\" howled the rhinoceros.", // 10
                            "Fluffy was embarassed, but he tried to be polite. " +
                                    "\"And what is your name?\" he inquired. " +
                                    "\"H... I can't say it,\" giggled the rhinoceros. " +
                                    "\"Hubert?\" suggested Fluffy.",    // 11
                            "\"H...H...H... oh help, I just can't say it, I'm laughing so hard,\" said the rhinoceros. " +
                                    "\"Harold? Or maybe Herman?\" asked Fluffy. " +
                                    "\"No,\" gasped the rhinoceros. \"It's H... H... H... HIPPO.\"", // 12
                            "Hippo. A rhinoceros named Hippo. Fluffy smiled. He giggled. Then he laughed out loud. " +
                                    "He jiggled and slapped his knees. He howled with laughter. " +
                                    "\"A rhinoceros named Hippo!\" Fluffy cried.",  // 13
                            "A porcupine named Fluffy. A rhinoceros named Hippo. It was almost more than they could bear. " +
                                    "Hippo and Fluffy rolled on the ground giggling and laughing until tears cam to their eyes. " +
                                    "At last they lay exhausted on the ground. From that time on they were the best of friends.",   // 14
                            "And Fluffy didn't mind being Fluffy anymore— even though he wasn't."   // 15
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "Helen Lester, illustrator Lynn Munsinger";
        }
    }

    public static class HandHandFingersThumb implements IBook {
        @Override
        public String getTitle() {
            return "HAND, HAND, FINGERS, THUMB";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/L0hhElq.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/7WTS1Lv.jpg",
                            "http://i.imgur.com/q00iFZM.jpg",
                            "http://i.imgur.com/Ekq8T3j.jpg",
                            "http://i.imgur.com/2TrlMER.jpg",
                            "http://i.imgur.com/wgKii2v.jpg",
                            "http://i.imgur.com/ax85jRv.jpg",
                            "http://i.imgur.com/JP3MbEW.jpg",
                            "http://i.imgur.com/SCKYGIo.jpg",
                            "http://i.imgur.com/8Nkuz6M.jpg",
                            "http://i.imgur.com/YXyQC5M.jpg"};
                case text:
                    return new String[]{"Hand Hand Fingers Thumb. One thumb one thumb drumming on a drum.",
                            "One hand two hands drumming on a drum. Dum ditty dum ditty dum dum dum",
                            "Rings on fingers. Rings on thumb. Drum drum drum drum drum drum drum.",
                            "Monkeys drum, and monkeys hum. Hum drum hum drum hum drum hum.",
                            "Hand picks an apple. Hand picks a plum. Dum ditty dum ditty dum dum dum",
                            "Hands play banjos, strum strum strum. Hands play fiddles, zum zum zum.",
                            "Dum ditty dum ditty dum dum dum, hand in hand more monkeys come.",
                            "Many more fingers, many more thumbs, many more monkeys, many more drums.",
                            "Millions of fingers, millions of thumbs, millions of monkeys, drumming on drums!",
                            "Dum ditty dum ditty dum dum dum."};

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "Al Perkins, illustrator Eric Gurney";
        }
    }

    public static class RockyAndRubble implements IBook {
        @Override
        public String getTitle() {
            return "Rocky and Rubble";
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


        public String getAuthor() {
            return "nickelodeon";
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
                case text:
                    return new String[]{
                            "A is for Annie. B is for Bertie. C is for Clarabel. D is for Diesel.",
                            "E is for engine shed. F is for Fat Controller. G is for Gordon.",
                            "H is for Harold. I is for ice cream. J is for James. K is for kite.",
                            "L is for luggage. M is for mountain. N is for newspaper. O is for ostrich.",
                            "P is for Percy. Q is for queue. R is for railway line. S is for signal.",
                            "T is for Thomas. U is for umbrella. V is for van.",
                            "W is for window. X is for Xmas. Y is for Yacht. Z is for zebra."
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "unknown";
        }
    }
}
