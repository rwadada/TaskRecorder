import androidx.compose.runtime.Composable
import androidx.compose.ui.window.application

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

@Composable
actual fun finishApp() {
    // do nothing
    // 到達することは無い想定のため問題なし
}