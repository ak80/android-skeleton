package org.ak80.skeleton.entry

import org.ak80.skeleton.R
import org.ak80.skeleton.di.ActivityScoped
import org.ak80.skeleton.model.Entry
import javax.inject.Inject

/**
 * Presents [Entry] s and listens to events from the [EntryFragment]
 */
@ActivityScoped
class EntryPresenter @Inject constructor(private var entryRepository: EntryGateway) : EntryContract.Presenter {

    private var entryView: EntryContract.View? = null

    override fun takeView(view: EntryContract.View) {
        entryView = view
        showEntries()
    }

    override fun dropView() {
        entryView = null
    }

    override fun showEntries() {
        entryRepository.loadEntries { entries -> entryView?.showEntries(entries) }
    }

    override fun completeEntry(completedEntry: Entry) {
        completedEntry.passed = true
        entryView?.showEntryComplete()
    }

    override fun activateEntry(activeEntry: Entry) {
        activeEntry.passed = false
        entryView?.showEntryActive()
    }

    override fun clearEntries() {
        showEntries()
    }

    override fun about() {
        entryView?.showAbout(R.string.about)
    }

    override fun addNewEntry() {
        entryView?.showMessage(R.string.add_new_entry)
    }

}
