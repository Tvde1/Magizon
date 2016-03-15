package nl.broekhin.magizon.layout.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();


    //super.onCreate(savedInstanceState);
    private static final int COUNT = 5;
    private static String Les[] = {"Biologie - NIJ", "Scheikunde - BEV", "Nederlands - VRR", "Tussenuur", "Engels - ALW", "Wiskunde B - HOM"};

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    //if (DummyContent.ITEMS.isEmpty())
    public static void addTheItems() {
        //DummyContent.addItem(new DummyItem("0","Biologie - NIJ", "Les 1"));
        //DummyContent.addItem(new DummyItem("1","Nedrlands - VRR", "Les 2"));
        //DummyContent.addItem(new DummyItem("2", "3", "Les 3"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        //return new DummyItem(String.valueOf(position), "Les " + position, makeDetails(position));
        return new DummyItem(String.valueOf(position), Les[position], "Niks");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}