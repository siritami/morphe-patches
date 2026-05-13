package app.siritami.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.siritami.patches.shared.Constants.COMPATIBILITY_FACEBOOK

@Suppress("unused")
val disableAdBreaksPatch = bytecodePatch(
    name = "Disable video and reel ads",
    description = "Disables instream banner ads, reels banner ads, reels floating CTA pills, " +
        "and game ad requests. Ported from NexAlloy Xposed hooks.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    execute {
        // Block instream banner ad eligibility → return false
        InstreamBannerEligibilityFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )

        // Block reels floating CTA indicator pill → return false
        IndicatorPillAdEligibilityFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )

        // Block ReelsBannerAdsComponent render → return null
        ReelsBannerAdsComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Block ReelsBannerAdsNativeComponent render → return null
        ReelsBannerAdsNativeComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Block game ad requests → return-void
        listOf(
            GameAdInterstitialRequestFingerprint,
            GameAdRewardedVideoRequestFingerprint,
            GameAdRewardedInterstitialRequestFingerprint,
            GameAdLoadRequestFingerprint,
            GameAdShowRequestFingerprint,
        ).forEach { fingerprint ->
            fingerprint.method.addInstructions(
                0,
                """
                    return-void
                """
            )
        }
    }
}
