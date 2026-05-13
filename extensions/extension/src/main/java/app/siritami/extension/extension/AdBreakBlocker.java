package app.siritami.extension.extension;

/**
 * Extension class for the Disable Video Ad Breaks patch.
 * Provides logic to block mid-roll and post-roll ad breaks in videos and reels.
 *
 * Once the full Facebook APK is decompiled (all dex files), this class will be called
 * from patched video player and reel playback code to skip ad breaks.
 */
@SuppressWarnings("unused")
public class AdBreakBlocker {

    /**
     * Determines whether an ad break should be shown during video playback.
     *
     * @return true to show the ad break (normal behavior), false to skip it.
     */
    public static boolean shouldShowAdBreak() {
        return false;
    }

    /**
     * Determines whether a reel ad should be inserted between reel clips.
     *
     * @return true to show the reel ad, false to skip it.
     */
    public static boolean shouldShowReelAd() {
        return false;
    }

    /**
     * Determines whether an interstitial ad should be shown.
     *
     * @return true to show the interstitial ad, false to skip it.
     */
    public static boolean shouldShowInterstitialAd() {
        return false;
    }
}
