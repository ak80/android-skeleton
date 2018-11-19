package org.ak80.skeleton.info

import org.ak80.skeleton.R
import org.ak80.skeleton.di.ActivityScoped
import javax.inject.Inject

/**
 * Presents the info screen
 */
@ActivityScoped
class InfoPresenter @Inject constructor() : InfoContract.Presenter {

    private var infoView: InfoContract.View? = null

    override fun takeView(view: InfoContract.View) {
        infoView = view
        infoView?.setInfoText(R.string.info_text)
    }

    override fun dropView() {
        infoView = null
    }

}
