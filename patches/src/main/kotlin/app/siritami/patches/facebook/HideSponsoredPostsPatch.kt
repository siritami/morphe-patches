package app.siritami.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.siritami.patches.shared.Constants.COMPATIBILITY_FACEBOOK

@Suppress("unused")
val hideSponsoredPostsPatch = bytecodePatch(
    name = "Hide sponsored posts",
    description = "Hides sponsored/promoted posts from the Facebook home feed. " +
        "Blocks the sponsored story pool and sponsored story holder.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    execute {
        SponsoredPoolAddFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return v0
            """
        )

        // Block the sponsored story holder from returning the next sponsored story.
        // FeedSponsoredStoryHolder → return null
        SponsoredStoryNextFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Block story ad insertion trigger.
        // The method that schedules ad insertion into stories → return-void
        StoryAdsInsertionTriggerFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )
    }
}
