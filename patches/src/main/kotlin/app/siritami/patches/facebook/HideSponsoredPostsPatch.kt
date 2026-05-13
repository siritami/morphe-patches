package app.siritami.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.siritami.patches.shared.Constants.COMPATIBILITY_FACEBOOK

@Suppress("unused")
val hideSponsoredPostsPatch = bytecodePatch(
    name = "Hide sponsored posts",
    description = "Hides sponsored/promoted posts from the Facebook feed by preventing " +
        "sponsored Litho components from rendering.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    execute {
        // Prevent the SearchResultsSponsoredMultiStorySection from rendering.
        // This section displays groups of sponsored stories in feed/search results.
        // By returning null from onCreateLayout, the component renders nothing.
        SponsoredMultiStorySectionFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Prevent the SearchResultsSponsoredStoryVideoComponent from rendering.
        // This component displays individual sponsored story videos.
        SponsoredStoryVideoComponentFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """
        )

        // Prevent the BauToMultiAdsSplitViewActivity from loading.
        // This activity handles multi-ad split view display.
        MultiAdsSplitViewActivityFingerprint.method.addInstructions(
            0,
            """
                invoke-virtual {p0}, Landroid/app/Activity;->finish()V
                return-void
            """
        )
    }
}
