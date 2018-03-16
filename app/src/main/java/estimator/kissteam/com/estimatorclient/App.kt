package estimator.kissteam.com.estimatorclient

import android.app.Application

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class App : Application() {

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}