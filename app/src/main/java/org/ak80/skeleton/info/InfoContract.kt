package org.ak80.skeleton.info

import org.ak80.skeleton.BasePresenter
import org.ak80.skeleton.BaseView

/**
 * This specifies the contract between the view and the presenter.
 */
interface InfoContract {

    interface View : BaseView {

        fun setInfoText(id: Int)

    }

    interface Presenter : BasePresenter<View>

}
