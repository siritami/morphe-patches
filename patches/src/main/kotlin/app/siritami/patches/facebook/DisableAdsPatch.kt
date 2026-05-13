package app.siritami.patches.facebook

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.siritami.patches.shared.Constants.COMPATIBILITY_FACEBOOK

@Suppress("unused")
val disableAudienceNetworkAdsPatch = bytecodePatch(
    name = "Disable Audience Network ads",
    description = "Prevents Audience Network ads from loading by finishing ad activities immediately.",
    default = true
) {
    compatibleWith(COMPATIBILITY_FACEBOOK)

    execute {
        // Patch AudienceNetworkExportedActivity.onCreate() to finish immediately.
        // This prevents Audience Network ads (interstitial, banner, native) from displaying.
        AudienceNetworkExportedActivityOnCreateFingerprint.method.addInstructions(
            0,
            """
                invoke-virtual {p0}, Landroid/app/Activity;->finish()V
                return-void
            """
        )

        // Patch AudienceNetworkRemoteActivity.onCreate() to finish immediately.
        // This prevents remote ad loading via the Audience Network SDK.
        AudienceNetworkRemoteActivityOnCreateFingerprint.method.addInstructions(
            0,
            """
                invoke-virtual {p0}, Landroid/app/Activity;->finish()V
                return-void
            """
        )
    }
}
