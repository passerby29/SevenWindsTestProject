package dev.passerby.seven_winds_test.presentation

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("662168a7-6423-4269-b7ad-01094dcbbd42")
    }
}