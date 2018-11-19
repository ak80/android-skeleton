package org.ak80.skeleton.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Lazy

/**
 * Provides methods to help Activities load their UI.
 */
object ActivityUtils {

    /**
     * Load fragment by `fragmentId` from the `supportFragmentManager` or get it from the `provider`
     */
    fun loadFragment(fragmentId: Int, provider: Lazy<out Fragment>, supportFragmentManager: FragmentManager) {
        var fragment: Fragment? = supportFragmentManager.findFragmentById(fragmentId)
        if (fragment == null) {
            fragment = provider.get()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, fragmentId)
        }

    }

    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    private fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

}
