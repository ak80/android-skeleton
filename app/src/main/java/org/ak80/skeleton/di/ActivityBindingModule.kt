package org.ak80.skeleton.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.ak80.skeleton.entry.EntryActivity
import org.ak80.skeleton.entry.EntryModule
import org.ak80.skeleton.info.InfoActivity
import org.ak80.skeleton.info.InfoModule

/**
 * Define Activities here, and refer to their specific module
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [EntryModule::class])
    internal abstract fun entryActivity(): EntryActivity


    @ActivityScoped
    @ContributesAndroidInjector(modules = [InfoModule::class])
    internal abstract fun infoActivity(): InfoActivity

}