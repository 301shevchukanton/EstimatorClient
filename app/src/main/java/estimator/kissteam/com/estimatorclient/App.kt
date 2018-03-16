package estimator.kissteam.com.estimatorclient

import android.app.Application
import com.github.salomonbrys.kodein.Kodein

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class App : Application() {

	companion object {
		lateinit var INSTANCE: App
			private set

		lateinit var KODEIN: Kodein
			private set
	}

	override fun onCreate() {
		super.onCreate()
		INSTANCE = this

		KODEIN = KodeinFactory().create(this)
	}
}