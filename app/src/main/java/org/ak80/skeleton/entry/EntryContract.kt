package org.ak80.skeleton.entry

import org.ak80.skeleton.BasePresenter
import org.ak80.skeleton.BaseView
import org.ak80.skeleton.model.Entry

/**
 * This specifies the contract between the view and the presenter.
 */
interface EntryContract {

    interface View : BaseView {

        fun showEntries(entries: List<Entry>)

        fun showEntryComplete()

        fun showEntryActive()

        fun showAbout(id: Int)

        fun showMessage(id: Int)

    }

    interface Presenter : BasePresenter<View> {

        fun showEntries()

        fun completeEntry(completedEntry: Entry)

        fun activateEntry(activeEntry: Entry)

        fun clearEntries()

        fun about()

        fun addNewEntry()

    }
}
