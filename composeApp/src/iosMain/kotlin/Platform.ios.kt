import androidx.compose.runtime.Composable
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@Composable
actual fun finishApp() {
    // do nothing
    // Appleのガイドラインではアプリケーションがユーザーによって終了されるべきであり、プログラムから終了することは推奨されてないため。
}