package org.ak80.skeleton

interface BasePresenter<in T> {

    fun takeView(view: T)

    fun dropView()

}