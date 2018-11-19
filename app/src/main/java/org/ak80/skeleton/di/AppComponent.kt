package org.ak80.skeleton.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.ak80.skeleton.SkeletonApplication

import javax.inject.Singleton

/**
 * The Dagger component for [SkeletonApplication] add new activities in [AndroidSupportInjectionModule]
 */
@Singleton
@Component(
    modules = [ApplicationModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<SkeletonApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}