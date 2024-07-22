package wordentry;

import java.util.ArrayList;

public class WordList {
    public final String name, id;

    public final ArrayList<WordEntryPair> entries = new ArrayList<>();

    public WordList(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "WordList[id=" + id + ", name=" + name + ", entries=" + entries + "]";
    }
}
