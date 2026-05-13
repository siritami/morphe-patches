package app.siritami.extension.extension;

/**
 * Extension utility for sponsored post filtering.
 * Available for future patches that need complex runtime filtering logic.
 * Current patches use direct smali injection without extension calls.
 */
@SuppressWarnings("unused")
public class SponsoredPostFilter {

    public static boolean shouldHidePost(Object storyObject) {
        return true;
    }

    public static boolean isSponsoredContent(String identifier) {
        if (identifier == null) return false;
        String lower = identifier.toLowerCase();
        return lower.contains("sponsored") ||
               lower.contains("promoted") ||
               lower.contains("suggested_for_you");
    }
}
