package org.ak80.skeleton.entry

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.ak80.skeleton.di.FragmentScoped
import org.ak80.skeleton.repository.EntryRepository

/**
 * The Dagger module for entry
 */
@Module
abstract class EntryModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun entryFragment(): EntryFragment

    @Binds
    internal abstract fun entryPresenter(presenter: EntryPresenter): EntryContract.Presenter

    @Binds
    internal abstract fun entryRepository(entryRepository: EntryRepository): EntryGateway

}
