import androidx.compose.ui.window.ComposeUIViewController
import com.cerebus.tutube.TutubeAppNavigation
import com.cerebus.tutube.di.initKoin
import com.cerebus.tutube.di.modules
import platform.UIKit.UINavigationController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    initKoin(modules)
    val composeController = ComposeUIViewController { TutubeAppNavigation() }
    return composeController
}