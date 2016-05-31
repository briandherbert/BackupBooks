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
            new HandHandFingersThumb(),
            new MarshallAndSkye(),
            new PawPatrolRescueTeam(),
            new ThomasGoTrainGo(),
            new WinniePoohHoneyTree(),
            new TMNTFollowNinja(),
            new WebpExample()
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
                            "", // 1
                            "", // 2
                            "", // 3
                            "", // 4
                            "", // 5
                            "", // 6
                            "", // 7
                            "", // 8
                            "", // 9
                            "", // 10
                            "", // 11
                            "", // 12
                            "", // 13
                    };
                case text:
                    return new String[]{
                            "", // 1
                            "", // 2
                            "", // 3
                            "", // 4
                            "", // 5
                            "", // 6
                            "", // 7
                            "", // 8
                            "", // 9
                            "", // 10
                            "", // 11
                            "", // 12
                            "", // 13
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "";
        }
    }




    public static class WebpExample implements IBook {
        @Override
        public String getTitle() {
            return "webp";
        }

        @Override
        public String getCoverUrl() {
            return "http://www.gstatic.com/webp/gallery/1.webp";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://www.gstatic.com/webp/gallery/2.webp",
                            "http://www.gstatic.com/webp/gallery/3.webp"
                    };
                case text:
                    return new String[]{
                            "one",
                            "two"
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "";
        }
    }

    public static class TMNTFollowNinja implements IBook {
        @Override
        public String getTitle() {
            return "Teenage Mutant Ninja Turtles: Follow the Ninja";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/L9sdHdW.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/ttUVGvJ.jpg",   // 1
                            "http://i.imgur.com/ZIW55oh.jpg",   // 2
                            "http://i.imgur.com/GdvBvZE.jpg", // 3
                            "http://i.imgur.com/aQSHWZE.jpg", // 4
                            "http://i.imgur.com/AGpjiRI.jpg", // 5
                            "http://i.imgur.com/GaP9QT9.jpg", // 6
                            "http://i.imgur.com/1slwuy3.jpg", // 7
                            "http://i.imgur.com/KnzlFaU.jpg", // 8
                            "http://i.imgur.com/qvGK9hu.jpg", // 9
                            "http://i.imgur.com/NOFxLcd.jpg", // 10
                            "http://i.imgur.com/WjAxge9.jpg", // 11
                            "http://i.imgur.com/FP2M3yW.jpg", // 12
                    };
                case text:
                    return new String[]{
                            "The Teenage Mutant Ninja Turtles were on patrol. They had been looking for mutagen containers all night, but they hadn’t found any. \"I’m so bored,\" Mikey whined.",
                            "\"Let’s take a break and do something fun,\" suggested Leo, the Turtles’ leader. \"Awesome!\" his brothers cheered. \"It’s time for a training session!\" Leo exclaimed. \"Awww,\" Raph, Donnie, and Mikey moaned. Ninja exercises didn’t sound like fun to them.",    // 2
                            "Leo had an idea to make training exciting. \"We'll play King of the Mountain,\" he said, jumping to the top of Dragon Gate. \"I'll stand opp here, and you try to get past me using ninja skills.\" \n" +
                                    "\"Sounds great,\" Raph said. He whispered a secret plan to Mikey and Donnie.", // 3
                            "Mikey went first. He put` on his headphones, then flipped, spun, and danced right past Leo. \"Ninjas don't do that!\" Leo protested. Donnie calculated a sneaky way to throw his ninja starts. They bounced and skipped off buildings— right toward Leo. Leo ducked, and when he looked up, Donnie was behind him.", // 4
                            "Raph threw his sai straight at Leo. As Leo dodged it, Raph jumped past him. \"That's not fair!\" Leo shouted. Leo was really mad. \"You guys never take my orders seriously.\"\n" +
                                    "\"Well, you always want us to fight just like you,\" Raph replied as he, Donnie, and Mikey marched away.", // 5
                            "Back at the lair, Leo spoke to his teacher, Splinter. \"Maybe I'm not cut out to be a leader,\" he said.\n" +
                                    "\"A true ninja must be unpredictable,\" Splinter said. \"And a true leader doesn't always tell his followers what to do.", // 6
                            "The next night, the Turtles went out again. Suddenly, Karai jumped from the shadows. She was a very dangerous ninja— and she wasn't alone. An army of ninja robots stodd behind her! \"My Footbots are programmed to know every ninja move,\" she said. \"You can't beat them!\"", // 7
                            "Karai commanded the Footbots to capture the Turtles. The bots charged, and the battle began. The Footbots ducked the Turtles' punches. They blocked the Turtles' kicks. The Turtles couldn't stop them.", // 8
                            "Leo was sure the Turtles would lose this fight... until he remembered Splinter's words: A true ninja must be unpredictable.\n" +
                                    "\"You can't program a ninja,\" Leo said. Then he yelled to his brothers. \"Do you remember Kind of the Mountain? Show these bots your original ninja moves!\"", // 9
                            "The Footbots weren't programmed to deal with Mikey's dancing. Donnie was too sneaky for them. And Raph's power put the bots on the run!", // 10
                            "\"That's the most fun I've ever had following your orders!\" Raph exclaimed.\n" +
                                    "\"That's the most fun I've had giving them,\" Leo replied.\n" +
                                    "Mikey threw a smoke bomb, and the Turtles vanished into a pruple cloud.", // 11
                            "Back at their lair, the Turtles were ready to relax. \"Who wants to play King of the Mountain?\" Leo joked.\n" +
                                    "\"I'd rather play Follow the Leader,\" Raph said with a smile.", // 12
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "nickelodeon";
        }
    }



    public static class ThomasGoTrainGo implements IBook {
        @Override
        public String getTitle() {
            return "Thomas' Go, Train, Go!";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/UoBfRsP.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/1Lv6T90.jpg",   // 0
                            "http://i.imgur.com/fZbsfU5.jpg",   // 1
                            "http://i.imgur.com/jFNEwXg.jpg",   // 2
                            "http://i.imgur.com/hiJuQyX.jpg",   // 3
                            "http://i.imgur.com/yOwFj8z.jpg",   // 4
                            "http://i.imgur.com/rFFw9wr.jpg",   // 5
                            "http://i.imgur.com/NFaaoME.jpg",   // 6
                            "http://i.imgur.com/aTMfZdC.jpg",   // 7
                            "http://i.imgur.com/VLwUBRI.jpg",   // 8
                            "http://i.imgur.com/OQBfNAb.jpg",   // 9
                            "http://i.imgur.com/T92u2Kh.jpg",   // 10
                            "http://i.imgur.com/tgvJF7B.jpg",   // 11
                            "http://i.imgur.com/4TvANFU.jpg",   // 12
                            "http://i.imgur.com/DMQla5Z.jpg",   // 13
                            "http://i.imgur.com/KVQJDui.jpg",   // 14
                            "http://i.imgur.com/JqVV7Tk.jpg",   // 15
                            "http://i.imgur.com/Dw5IBZP.jpg",   // 16
                            "http://i.imgur.com/fJh6ORU.jpg",   // 17
                            "http://i.imgur.com/VgLyPQ9.jpg",   // 18
                            "http://i.imgur.com/A7tgoeJ.jpg",   // 19
                    };
                case text:
                    return new String[]{
                            "Here comes the judge in her big red hat. She has come to see the train show. Who will take the judge to the train show? " +
                                    "Thomas will! Thomas will go. Thomas will take the judge to the show.", // 0
                            "\"Hurry, Thomas! Take me to the show. Take me there fast. Go, train go!\" Clickety-clack, clickety-clack, up up the hill, " +
                                    "Thomas the Tank Engine goes faster than fast.", // 1
                            "Screech! go the brakes. Thomas goes so slow. Slow, slow, slower than slow he goes. " +
                                    "\"Hurry Thomas! Why do you go so slow? Take me to the train show. Go, train, go!\"",   // 2
                            "But Thomas cannot go. Thomas sees a goat. The goat is on the track. Peep! Peep! goes Thomas. Baaaa! The goat jumps back.", // 3
                            "Clickety-clack, clickety-clack, down down the hill, Thomas the Tank Engine goes faster than fast.",    // 4
                            "Screech! go the brakes. Thomas goes so slow. Slow, slow, slower than slow he goes. " +
                                    "\"Hurry Thomas! Why do you go so slow? Take me to the train show. Go, train, go!\"",   // 5
                            "The tunnel is so dark. Slow, slow, slow he goes into the dark, dark, dark tunnel... ... and out the other side!",  //6
                            "Clickety-clack, clickety-clack, over a bridge. He was going so fast. He was going so fast, the judge lost her hat!",   // 7
                            "Screech! go the brakes. Thomas goes so slow. Slow, slow, slower than slow he goes. " +
                                    "\"Hurry Thomas! We're running late, you know. Take me to the train show. Go, train, go!\"",   // 8
                            "But Thomas must go slow. There is a cow on the track. Moo! Moo! goes the cow. Peep! Peep! goes Thomas. " +
                                    "The cow moves back. Clickety-clack, clickety-clack, Thomas the Tank Engine moves faster than fast!",   // 9
                            "Screech! go the brakes. Thomas goes so slow. Slow, slow, slower than slow he goes. " +
                                    "\"Don't stop, Thomas. Go, train, go! Don't stop now. I'm late for the show!",   // 10
                            "But Thomas must go slow. There are logs on the track. The cran engine clears the logs. " +
                                    "Clickety-clack, clickety-clack, around the logs goes Thomas faster than fast.",    // 11
                            "There is mud up ahead! The judge wants to go slow. \"Slow, little engine. Slow, slow, slow. " +
                                    "Watch out for the mud! Whoa, train, whoa!\"",  // 12
                            "But Thomas cannot go slow. Thomas goes faster than fast. Into the mud... Splash! Splash!", // 13
                            "Thomas goes fast. Past a town, fast, fast. Past a dog, faster still. Fast at last!",   // 14
                            "Nothing can stop him, nothing at all. No goat. No dark. No cow. No log.",  // 15
                            "No crane No mud. No town. No dog. The is the fastest that Thomas can go!", // 16
                            "Screech! go the brakes. \"Good job, Thomas! We made it here at last! You are a little engine, but you go so fast!\"",  // 17
                            "Here comes the judge! The train show begins. There are red trains and blue trains and old trains and new trains. " +
                                    "And a little blue engine covered in mud. What will the judge say?",    // 18
                            "\"I like all the trains. You all are such fun. But the muddy little blue train is my favorite one.\""  // 19
                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "Thomas and Friends";
        }
    }

    public static class WinniePoohHoneyTree implements IBook {
        @Override
        public String getTitle() {
            return "Winnie the Pooh: The Honey Tree";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/kHar68B.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/GhCiyMc.jpg",  // 1
                            "http://i.imgur.com/nNI47pi.jpg",   // 2
                            "http://i.imgur.com/nhj1YES.jpg",   // 3
                            "http://i.imgur.com/3hVhYe9.jpg",   // 4
                            "http://i.imgur.com/xs8xIjb.jpg",   // 5
                            "http://i.imgur.com/4x86nOu.jpg",   // 6
                            "http://i.imgur.com/8zogbiY.jpg",   // 7
                            "http://i.imgur.com/tLkT8k0.jpg",   // 8
                            "http://i.imgur.com/i8yqvTZ.jpg",   // 9
                            "http://i.imgur.com/cb1OtgT.jpg",   // 10
                            "http://i.imgur.com/XOGejvX.jpg",   // 11
                            "http://i.imgur.com/AO8Scwk.jpg",   // 12
                    };
                case text:
                    return new String[]{
                            "Once upon a time, a very long time ago now, about last Friday, Winnie-the-Pooh lived in a forest all by himself under the name of Sanders. " +
                                    "That means that he had the name over the door in gold letters, and lived under it",    // 1
                            "One day when he was out walking, he came to an open place in the middle of the forest, and in the middle of this place was a large oak tree, and, from the top of the tree, there came a loud buzzing noise. " +
                                    "Winnie the Pooh sat down at the foot of the tree, put his head between his paws, and began to think. First of all he said to himself: " +
                                    "\"That buzzing noise means something. You don't get a buzzing noise like that, just buzzing and buzzing, without it's meaning something. If there's a buzzing noise, somebody's making a buzzing noise, " +
                                    "and the only reason for making a buzzing noise that I know of is because you're a bee.\" Then he thought another long time, and said: \"And the only reason for being a bee that I know of is making honey.\" " +
                                    "And then he got up, and said: \"And the only reason for making honey is so I can eat it.\" So he began to climb the tree.",    // 2
                            "He climbed and he climbed and he climbed, and as he climbed he sang a little song to himself. It went like this: Isn't it funny, how a bear likes honey? Buzz! Buzz! Buzz! I wonder why he does? " +
                                    "Then he climbed a little further, and a little further, and then just a little further. By that time he had thought of another song. " +
                                    "It's a very funny thought that, if bears were bees, they'd build their nests at the bottom of trees. And that being so (if the bees were bears), we shouldn't have to climb up all these stairs. " +
                                    "He was getting rather tired by this time, so that is why he sang a complaining song. He was nearly there now, and if he just stood on that branch... " +
                                    "Crack! \"Oh, help!\" said Pooh, as he dropped ten feet on the branch below him. \"If only I hadn't— \" he said, as he bounced twenty feet on to the next branch.", // 3
                            "\"You see, what I meant to do,\" he explained, as he turned head-over-heels, and crashed on to another branch thirty feet below, \"what I meant to do— , Of course, it was rather— \" he admitted, as he slithered very quickly through the next six branches. " +
                                    "\"It all comes, I suppose,\" he decided, as he said goodbye to the last branch, spun round three times, and flew gracefully into a gorse bush, \"it all comes of liking honey so much. Oh, help!\" " +
                                    "He crawled out of the gorse bush, brushed the prickles from his nose, and began to think again. And the first person he thought of was Christopher Robin.",    // 4
                            "So Winnie-the-Pooh went round to his friend Christopher Robin, who lived behind a green door in another part of the forest. \"Good morning, Christopher Robin,\" he said. \"Good morning, Winnie-the-Pooh,\" said Christopher Robin. \"I wonder if you've got such a thing as a balloon about you?\" " +
                                    "\"A balloon?\" \"Yes, I just said to myself coming along: \"I wonder if Christopher Robin has such a thing as a balloon about him?' I just said it to myself, thinking of balloons, and wondering.\" \"What do you want a balloon for?\" said Christopher Robin. " +
                                    "Winnie-the-Pooh looked round to see that nobody was listening, put his paw to his mouth, and said in a deep whisper: \"Honey!\" \"But you don't get honey with balloons!\" \"I do,\" said Pooh. Well it just happened that Christopher Robin had been to a party the day before at the house of his friend Piglet, and they had balloons at the party. " +
                                    "Christopher Robin had had a big green balloon; and one of Rabbit's relations had had a big blue one, and had left it behind, being really too young to go to a party at all; and so Christopher Robin had brought the green one and the blue one home with him.",     // 5
                            "\"Which one would you like?\" he asked Pooh. Pooh put his head between his paws and thought very carefully. \"It's like this,\" he said. \"When you go after honey with a balloon, the great thing is not to let the bees know you're coming. " +
                                    "Now, if you have a green balloon, they might think you were only part of the tree, and not notice you, and if you have a blue balloon, they might think you were only part of the sky, and not notice you, and the question is: Which is most likely?\" " +
                                    "\"Wouldn't they notice you underneath the balloon?\" asked Christopher Robin. \"They might or they might not,\" said Winnie-the-Pooh. \"You never can tell with bees.\" He thought for a moment and said: \"I shall try to look like a small black cloud. That will deceive them.\" " +
                                    "\"Then you had better have the blue balloon,\" said Christopher Robin; and so it was decided. ",   // 6
                            "Well, they both went out with the blue balloon, and Christopher Robin took his gun with him, just in case, as he always did,. and Winnie-the-Pooh went to a very muddy place that he knew of, and rolled and rolled until he was black all over; and then, when the balloon was blown up as big as big, and Christopher Robin and Pooh were both holding on to the string, Christopher Robin let go suddenly, and Pooh Bear floated gracefully up into the sky, and stayed there-level with the top of the tree and about twenty feet away from it. " +
                                    "\"Hooray!\" shouted Christopher Robin. \"Isn't that fine?\" shouted Winnie-the-Pooh down to him. \"What do I look like?\" \"You look like a Bear holding on to a balloon,\" said Christopher Robin. \"Not,\" said Pooh anxiously, \"-not like a small black cloud in a blue sky?\" \"Not very much.\" " +
                                    "\"Ah, well, perhaps from up here it looks different. And, as I say, you never can tell with bees.\" ", // 7
                            "There was no wind to blow him nearer to the tree, so there he stayed. He could see the honey, he could smell the honey, but he couldn't quite reach the honey. After a little while he called down. \"Christopher Robin!\" he said in loud whisper. \"Hallo!\" \"I think the bees suspect something!\" \"What sort of thing?\" \"I don't know. But something tells me that they're suspicious!\" " +
                                    "\"Perhpas they think that you're after their honey.\" \"It may be that. You never can tell with bees.\" There was another little silence, and then he called down again, \"Christopher Robin!\" \"Yes?\" \"Have you an umbrella in your house?\" \"I think so.\" \"I wish you would bring it out here, and walk up and down with it, and look up at me every now and then, and say 'Tut-tut, it looks like rain.' " +
                                    "I think, if you did that, it would help the deception which we are practising on these bees.\" Well, Christoper Robin laughed to himself, \"Silly old Bear!\" but he didn't say it aloud because he was so fond of Pooh, and he went home for his umbrella. ",  // 8
                            "\"Oh, there you are!\" called down Winnie-the-Pooh, as soon as Christopher Robin got back to the tree. \"I was beginning to get anxious. I have discovered that the bees are now definitely Suspicious.\" \"Shall I put my umbrella up?\" said Christopher Robin. \"Yes, but wait a moment. We must be practical. The important bee to deceive is the Queen bee. Can you see which is the Queen Bee from down there?\" \"No.\" " +
                                    "\"A pity. Well, now, if you walk up and down with your umbrella, saying, 'Tut-tut, it looks like rain,' I shall do what I can by singing a little Cloud Song, such as a cloud might sing ... Go!\" So, while Christopher Robin walked up and down and wondered if it would rain, Winnie-the-Pooh sang this song: How sweet to be a cloud Floating in the Blue! Every little cloud Always sings aloud. " +
                                    "\"How sweet to be a Cloud Floating in the Blue!\" It makes him very proud To be a little cloud. ", // 9
                            "The bees were still buzzing as suspiciously as ever. Some of them, indeed, left their nest and flew all round the cloud as it began the second verse of this song, and one bee sat down on the nose of the cloud for a moment, and then got up again. \"Christopher-ow!-Robin,\" called out the cloud. \"Yes?\" \"I have just been thinking, and I have come to a very important decision. These are the wrong sort of bees.\" " +
                                    "\"Are they?\" \"Quite the wrong sort. So I should think they would make the wrong sort of honey, shouldn't you?\" \"Would they?\" \"Yes. So I think I shall come down.\" \"How?\" asked Christopher Robin. Winnie-the-Pooh hadn't thought about this. If he let go of the string, he would fall-bump-and he didn't like the idea of that. So he thought for along time, and then he said, ",   // 10
                            "\"Christopher Robin, you must shoot the balloon with your gun. Have you got your gun?\" \"Of course I have,\" said Christopher Robin. \"But if I do that, it will spoil the balloon.\" \"But if you don't,\" said Pooh, \"I shall have to let go, and that would spoil me.\" When he put it like this, Christopher Robin saw how it was, and he aimed very carefully at the balloon, and fired. " +
                                    "\"Ow!\" said Pooh. \"Did I miss?\" asked Christopher Robin. \"You didn't exactly miss,\" said Pooh, \"but you missed the balloon.\" \"I'm so sorry,\" said Christopher Robin, and he fired again, and this time he hit the balloon, and the air came slowly out, and Winnie-the-Pooh floated down to the ground.", // 11
                            "But his arms were so stiff from holding on to the string of the balloon all that time that they stayed up straight in the air for more than a week, and whenever a fly came and settled on his nose he had to blow it off. And I think-but I am not sure-that that is why he was always called Pooh."  // 12

                    };

                default:
                    return null;
            }
        }

        public String getAuthor() {
            return "A. A. Milne";
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


    public static class MarshallAndSkye implements IBook {
        @Override
        public String getTitle() {
            return "Marshall and Skye";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/hgzqlWR.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/mVqdYVR.jpg",
                            "http://i.imgur.com/Ta0TV9K.jpg",
                            "http://i.imgur.com/EZNWzB9.jpg",
                            "http://i.imgur.com/8yFpEjg.jpg",
                            "http://i.imgur.com/ijfg7FH.jpg"
                    };

                case text:
                    return new String[]{
                            "It's the day of Adventure Bay's big balloon race. Ryder will help Mayor Goodway fly her balloon. " +
                                    "The pups get the balloon ready. Marshall checks the air tanks. Rocky patches a hole.",
                            "Oh, no! Mayor Goodway's balloon accidentally lifts off. Marshall tries to stop it and gets pulled along for a ride!",
                            "Skye soars to the rescue! \"This pup's got to fly!\" she says. Nice catch, Ryder!",
                            "Now the team must help the mayor. Skye gives Ryder a lift! She carries him to the runaway balloon.",
                            "Ryder helps the mayor pilot the balloon. The mayor wins the race! She gives her trophy to the PAW Patrol. " +
                                    "\"Thanks,\" says Ryder. \"Whenever you're in trouble, just yelp for help!\""
                    };

                default:
                    return null;
            }
        }


        public String getAuthor() {
            return "nickelodeon";
        }
    }

    public static class PawPatrolRescueTeam implements IBook {
        @Override
        public String getTitle() {
            return "PAW Patrol Rescue Team!";
        }

        @Override
        public String getCoverUrl() {
            return "http://i.imgur.com/lYJpXFk.jpg";
        }

        @Override
        public String[] getPages(BookPageMediaType mediaType) {
            switch (mediaType) {
                case image:
                    return new String[]{
                            "http://i.imgur.com/xiTzzo3.jpg",
                            "http://i.imgur.com/OaPplc5.jpg",
                            "http://i.imgur.com/8TZEBMj.jpg",
                            "http://i.imgur.com/nrgVWpw.jpg",
                            "http://i.imgur.com/woahwhv.jpg"
                    };
                case text:
                    return new String[]{
                            "The PAW Patrol pups are best friends, who love working and playing together.",
                            "When Ryder calls them to the Lookout, Marshall, Rubble, Chase, Rocky, Skye, and Zuma leap into action to help Adventure Bay.",
                            "\"We have to save that kitten!\" Rubble exclaims. \"Don't worry! We've got you!\" Ryder says.",
                            "When Farmer Yumi needs help collecting fruit before a big storm, the PAW Patrol is there to help. " +
                                    "\"Great work, team,\" says Chase.",
                            "The PAW Patrol finishes a busy Adventure Bay day with pup treats and sleep in front of the Lookout. " +
                                    "Good friends make a great team!"
                    };

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
