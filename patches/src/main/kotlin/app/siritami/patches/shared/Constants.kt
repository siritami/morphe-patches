package app.siritami.patches.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_FACEBOOK = Compatibility(
        name = "Facebook",
        packageName = "com.facebook.katana",
        apkFileType = ApkFileType.APK,
        appIconColor = 0x1877F2, // Icon color in Morphe Manager
        targets = listOf(
            AppTarget(
                version = "559.1.0.52.72"
            ),
        )
    )
}
