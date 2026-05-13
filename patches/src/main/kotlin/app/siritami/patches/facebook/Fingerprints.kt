package app.siritami.patches.facebook

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

// ─── Audience Network activities ────────────────────────────────────────────

object AudienceNetworkExportedActivityOnCreateFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    filters = listOf(
        methodCall(
            definingClass = "Lcom/facebook/ads/internal/dynamicloading/DynamicLoaderFactory;",
            name = "makeLoader",
        ),
    ),
    custom = { _, classDef ->
        classDef.type == "Lcom/facebook/ads/internal/ipc/AudienceNetworkExportedActivity;"
    }
)

object AudienceNetworkRemoteActivityOnCreateFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    custom = { _, classDef ->
        classDef.type == "Lcom/facebook/ads/internal/ipc/AudienceNetworkRemoteActivity;"
    }
)

// ─── Sponsored pool (feed ads) ──────────────────────────────────────────────
// The SponsoredPoolContainerAdapter.add() method adds sponsored stories to the pool.
// Returning false prevents ads from entering the feed.

object SponsoredPoolAddFingerprint : Fingerprint(
    returnType = "Z",
    filters = listOf(
        string("SponsoredPoolContainerAdapter"),
    ),
)

// ─── Sponsored story holder ─────────────────────────────────────────────────
// Returns the next sponsored FeedUnitEdge; returning null = no sponsored story.

object SponsoredStoryNextFingerprint : Fingerprint(
    filters = listOf(
        string("FeedSponsoredStoryHolder.onPositionReset"),
    ),
)

// ─── Instream banner eligibility ────────────────────────────────────────────
// Returns whether instream banner ads should display; return false to block.

object InstreamBannerEligibilityFingerprint : Fingerprint(
    returnType = "Z",
    filters = listOf(
        string("InstreamAdIdleWithBannerState"),
    ),
)

// ─── Reels indicator pill ───────────────────────────────────────────────────
// Controls floating CTA pills on reels; return false to hide.

object IndicatorPillAdEligibilityFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.STATIC),
    returnType = "Z",
    filters = listOf(
        string("ReelsAdsFloatingCtaPlugin"),
    ),
)

// ─── Reels banner render ────────────────────────────────────────────────────
// The Litho component that renders banner ads on reels; return null to hide.

object ReelsBannerAdsComponentFingerprint : Fingerprint(
    filters = listOf(
        string("ReelsBannerAdsComponent"),
    ),
)

object ReelsBannerAdsNativeComponentFingerprint : Fingerprint(
    filters = listOf(
        string("ReelsBannerAdsNativeComponent"),
    ),
)

// ─── Story ads in-disc (insertion trigger) ──────────────────────────────────
// The method that triggers ad insertion into stories; no-op to block.

object StoryAdsInsertionTriggerFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("ads_insertion"),
    ),
)

// ─── Story ads deletion / provider ──────────────────────────────────────────
// The ads_deletion method in the story ad provider class.

object StoryAdsDeletionFingerprint : Fingerprint(
    filters = listOf(
        string("ads_deletion"),
    ),
)

// ─── Game ad request methods ────────────────────────────────────────────────
// Methods that handle game ad async requests; return-void to block.

object GameAdInterstitialRequestFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("Invalid JSON content received by onGetInterstitialAdAsync: "),
    ),
)

object GameAdRewardedVideoRequestFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("Invalid JSON content received by onRewardedVideoAsync: "),
    ),
)

object GameAdRewardedInterstitialRequestFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("Invalid JSON content received by onGetRewardedInterstitialAsync: "),
    ),
)

object GameAdLoadRequestFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("Invalid JSON content received by onLoadAdAsync: "),
    ),
)

object GameAdShowRequestFingerprint : Fingerprint(
    returnType = "V",
    filters = listOf(
        string("Invalid JSON content received by onShowAdAsync: "),
    ),
)

// ─── Reels list builder ─────────────────────────────────────────────────────
// The method that appends stories to the reels list; contains ad filtering logic.

object ReelsListBuilderFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.STATIC),
    returnType = "V",
    filters = listOf(
        string("Non ads story fall into ads rendering logic, StoryType=%s, StoryId=%s"),
    ),
)
