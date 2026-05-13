package app.siritami.patches.facebook

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

// Fingerprint for AudienceNetworkExportedActivity.onCreate()
// This activity handles Audience Network ad rendering for the Facebook app.
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

// Fingerprint for AudienceNetworkRemoteActivity.onCreate()
// This activity handles remote Audience Network ad display.
object AudienceNetworkRemoteActivityOnCreateFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    custom = { _, classDef ->
        classDef.type == "Lcom/facebook/ads/internal/ipc/AudienceNetworkRemoteActivity;"
    }
)

// Fingerprint for the AdBreakPostRollEndingScreenComponent.onCreateLayout (A1J method)
// This Litho component renders the post-roll ad break ending screen in videos.
// Original class: X.C2022mJh (renamed from X.mJh)
object AdBreakPostRollComponentFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "L",
    filters = listOf(
        string("ad_break_post_roll_ending_screen_appear_transition_key"),
    ),
)

// Fingerprint for the AdBreakInPlayerAnimatedSingleImageComponent.onCreateLayout (A1J method)
// This Litho component renders in-player ad break images during video playback.
// Original class: X.C2021mJg (renamed from X.mJg)
object AdBreakInPlayerImageComponentFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "L",
    filters = listOf(
        string("AdBreakInPlayerAnimatedSingleImageComponentSpec"),
    ),
)

// Fingerprint for the VideoAdsCallToActionAttachmentActionButtonComponent.onCreateLayout
// This Litho component renders the CTA button for video/reel ads.
// Original class: X.mIN
object VideoAdsCtaButtonComponentFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "L",
    filters = listOf(
        string("VideoAdsCallToActionAttachmentActionButtonComponent"),
    ),
)

// Fingerprint for the SearchResultsSponsoredMultiStorySection component
// This Litho section renders a group of sponsored stories in feed/search results.
// Original class: X.C2126mNh (renamed from X.mNh)
object SponsoredMultiStorySectionFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "L",
    filters = listOf(
        string("SearchResultsSponsoredMultiStorySection"),
    ),
)

// Fingerprint for the SearchResultsSponsoredStoryVideoComponent.onCreateLayout
// This Litho component renders individual sponsored story videos.
// Original class: X.mIO
object SponsoredStoryVideoComponentFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "L",
    filters = listOf(
        string("SearchResultsSponsoredStoryVideoComponent"),
    ),
)

// Fingerprint for the BauToMultiAdsSplitViewActivity
// This activity handles multi-ad split view display.
object MultiAdsSplitViewActivityFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    filters = listOf(
        string("multi_ads_unit_id"),
    ),
    custom = { _, classDef ->
        classDef.type == "Lcom/facebook/feedplugins/bautosplitview/activity/BauToMultiAdsSplitViewActivity;"
    }
)
