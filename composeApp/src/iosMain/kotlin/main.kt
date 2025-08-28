import androidx.compose.ui.window.ComposeUIViewController
import com.cerebus.tutube.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
