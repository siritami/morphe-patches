package app.siritami.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.siritami.patches.shared.Constants.COMPATIBILITY_FACEBOOK

@Suppress("unused")
val disableAdBreaksPatch = bytecodePatch(
    name = "Disable video ad breaks",
    description = "Disables mid-roll and post-roll ad breaks in videos and reels by preventing " +
        "ad break Litho components and video ad CTA buttons from rendering.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    execute {
        // Prevent the AdBreakPostRollEndingScreenComponent from rendering.
        // This component shows the post-roll ad ending screen after video ads.
        // Original class: X.C2022mJh (AdBreakPostRollEndingScreenComponent)
        AdBreakPostRollComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Prevent the AdBreakInPlayerAnimatedSingleImageComponent from rendering.
        // This component shows the in-player animated ad image during ad breaks.
        // Original class: X.C2021mJg (AdBreakInPlayerAnimatedSingleImageComponent)
        AdBreakInPlayerImageComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Prevent the VideoAdsCallToActionAttachmentActionButtonComponent from rendering.
        // This component shows the CTA button overlay on video/reel ads.
        // Original class: X.mIN (VideoAdsCallToActionAttachmentActionButtonComponent)
        VideoAdsCtaButtonComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )
    }
}
