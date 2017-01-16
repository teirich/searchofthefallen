package com.sotf.server.constants;

import com.sotf.server.model.Book;
import com.sotf.server.model.Novel;

import javax.json.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thad on 12/10/16.
 */

public enum Novels {
    GOTM(new Novel("GotM", "Gardens of the Moon", 1,
            Book.PROLOGUE,
            new Book(1, "Pale", 1,4),
            new Book(2, "Darujhistan", 5, 7),
            new Book(3, "The Mission", 8, 10),
            new Book(4, "Assassins", 11, 13),
            new Book(5, "The Gadrobi Hills", 14, 16),
            new Book(6, "The City of Blue Fire", 17, 19),
            new Book(7, "The Fete", 20, 24),
            Book.EPILOGUE
    )),
    DG(new Novel("DG", "Deadhouse Gates", 2,
            Book.PROLOGUE,
            new Book(1, "Raraku", 1, 5),
            new Book(2, "Whirlwind", 6, 10),
            new Book(3, "Chain of Dogs", 11, 15),
            new Book(4, "Deadhouse Gates", 16, 24),
            Book.EPILOGUE
    )),
    MOI(new Novel("MoI", "Memories of Ice", 3,
            Book.PROLOGUE,
            new Book(1, "The Spark And The Ashes", 1, 6),
            new Book(2, "Hearthstone", 7, 13),
            new Book(3, "Capustan", 14, 20),
            new Book(4, "Memories of Ice", 21, 25),
            Book.EPILOGUE
    )),
    HOC(new Novel("HoC", "House of Chains", 4,
            Book.PROLOGUE,
            new Book(1, "Faces in the Rock", 1, 4),
            new Book(2, "Cold Iron", 5, 12),
            new Book(3, "Something Breathes", 13, 18),
            new Book(4, "House of Chains", 19, 26),
            Book.EPILOGUE
    )),
    MT(new Novel("MT", "Midnight Tides", 5,
            Book.PROLOGUE,
            new Book(1, "Frozen Blood", 1, 5),
            new Book(2, "Prows of the Day", 6, 11),
            new Book(3, "All That Lies Unseen", 12, 20),
            new Book(4, "Midnight Tides", 21, 25),
            Book.EPILOGUE
    )),
    BH(new Novel("BH", "The Bonehunters", 6,
            Book.PROLOGUE,
            new Book(1, "The Thousand-Fingered God", 1, 6),
            new Book(2, "Beneath This Name", 7, 11),
            new Book(3, "Shadows of the King", 12, 16),
            new Book(4, "The Bonehunters", 17, 24),
            Book.EPILOGUE
    )),
    RG(new Novel("RG", "Reaper's Gale", 7,
            Book.PROLOGUE,
            new Book(1, "The Emperor in Gold", 1, 6),
            new Book(2, "Layers of the Dead", 7, 12),
            new Book(3, "Knuckles of the Soul", 13, 18),
            new Book(4, "Reaper's Gale", 19, 24),
            Book.EPILOGUE
    )),
    TTH(new Novel("TtH", "Toll the Hounds", 8,
            Book.PROLOGUE,
            new Book(1, "Vow to the Sun", 1, 6),
            new Book(2, "Cold-Eyed Virtues", 7, 12),
            new Book(3, "To Die in the Now", 13, 18),
            new Book(4, "Toll the Hounds", 19, 24),
            Book.EPILOGUE
    )),
    DOD(new Novel("DoD", "Dust of Dreams", 9,
            Book.PROLOGUE,
            new Book(1, "The Sea Does Not Dream of You", 1, 6),
            new Book(2, "Eaters of Diamonds and Gems", 7, 12),
            new Book(3, "Only the Dust Will Dance", 13, 18),
            new Book(4, "The Path Forever Walked", 19, 24),
            Book.EPILOGUE
    )),
    TCG(new Novel("tCG", "The Crippled God", 10,
            new Book(1, "He was a soldier", 1, 4),
            new Book(2, "All the takers of my days", 5, 7),
            new Book(3, "To charge the spear", 8, 10),
            new Book(4, "The fists of the world", 11, 13),
            new Book(5, "A hand upon the fates", 14, 16),
            new Book(6, "To one in chains", 17, 20),
            new Book(7, "Your private shore", 21, 24),
            Book.EPILOGUE,
            new Book(101, "Epilogue II", 101, 101)
    ));

    /**
     * Returns all Novel that came before
     * @param vol Novel Abbreviation
     * @return
     */
    public static List<Novels> before(String vol){
        List<Novels> novelsList = new ArrayList<>();
        for(Novels curVol : Novels.values()){
            if(curVol.novel().getAbbreviation().equalsIgnoreCase(vol)){
                return novelsList;
            }
            novelsList.add(curVol);
        }
        throw new IllegalArgumentException("unknown novel name");
    }

    public Novel novel() {
        return this.novel;
    }

    private Novel novel;

    Novels(Novel novel) {
        this.novel = novel;
    }

    private JsonObject toJson() {
        Novel n= this.novel;
        List<Book> books = n.getContents();
        JsonArrayBuilder booksBuilder = Json.createArrayBuilder();
        for(Book b : books) {
            JsonObjectBuilder bookBuilder = Json.createObjectBuilder();
            bookBuilder.add("title", b.title());
            bookBuilder.add("start", b.getStartChapter());
            bookBuilder.add("end", b.getEndChapter());
            bookBuilder.add("number", b.getBookNumber());
            booksBuilder.add(bookBuilder);
        }

        return Json.createObjectBuilder()
                .add("name", n.getTitle())
                .add("abbreviation", n.getAbbreviation())
                .add("order", n.getReadingOrder())
                .add("books", booksBuilder)
                .build();
    }

    public static JsonArray allToJson() {
        List<Novels> novelList = Arrays.asList(Novels.values());
        JsonArrayBuilder b = Json.createArrayBuilder();
        for(Novels novel : novelList) {
            b.add(novel.toJson());
        }
        return b.build();
    }

}
