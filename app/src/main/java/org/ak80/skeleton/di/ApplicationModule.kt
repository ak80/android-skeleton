package org.ak80.skeleton.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Dagger module to bind Application class as a Context in the [AppComponent].
 */
@Module
abstract class ApplicationModule {

    @Binds
    internal abstract fun bindContext(application: Application): Context
}