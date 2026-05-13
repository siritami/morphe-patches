package app.siritami.extension.extension;

/**
 * Extension utility for Facebook ad-blocking patches.
 * Available for future patches that need complex runtime logic.
 * Currently, all patches use direct smali injection without extension calls.
 */
@SuppressWarnings("unused")
public class DisableAdsPatch {

    /**
     * Returns whether ads should be shown. Always returns false to block ads.
     * Can be called from patches that need conditional ad blocking logic.
     */
    public static boolean shouldShowAd() {
        return false;
    }
}
