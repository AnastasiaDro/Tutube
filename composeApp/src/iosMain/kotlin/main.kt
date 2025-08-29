import androidx.compose.ui.window.ComposeUIViewController
import com.cerebus.tutube.TutubeAppNavigation
import com.cerebus.tutube.di.initKoin
import com.cerebus.tutube.di.modules
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    initKoin(modules)
    return ComposeUIViewController { TutubeAppNavigation() }
}
