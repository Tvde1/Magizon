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
    private static String TimLes[] = {"Biologie - NIJ- 135", "Scheikunde - BEV- 435", "Nederlands - VRR - 716", "Tussenuur", "Engels - ALW - 421", "Wiskunde B - HOM - 316"};
    private static String TomLes[] = {"Tussenuur", "Tussenuur ", "Engels - DIE - 423", "Scheikunde - MAL - 434", "Lichamelijke Opvoeding - KOK,WEN - 603", "Wiskunde B - HOM - 316"};
    //private static String iemand;
    private static String a;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void refresh(){
        ITEMS.clear();
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }

    }

    public static void setPersoon(String persoon){
        a = persoon;
    }

    private static String getPersoon() {

        if (a == null) {
            a = "Tim";
        }

        return a;
    }

    private static DummyItem createDummyItem(int position) {
        DummyItem item;
        String iemand = getPersoon();


        if (iemand.equals("Tim")) {
            item = new DummyItem(String.valueOf(position), TimLes[position], "Niks");
        } else if (iemand.equals("Tom")) {
            item = new DummyItem(String.valueOf(position), TomLes[position], "Niks");
        } else {
            item = new DummyItem(String.valueOf(position), "error, geen persoon", "Niks");
        }

        return item;

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
