package org.ak80.skeleton

import android.support.annotation.VisibleForTesting
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.ak80.skeleton.di.DaggerAppComponent
import org.ak80.skeleton.repository.EntryRepository
import javax.inject.Inject


class SkeletonApplication : DaggerApplication() {

    @Inject
    internal lateinit var entryRepository: EntryRepository

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    @VisibleForTesting
    fun getEntryRepository(): EntryRepository {
        return entryRepository
    }

}
