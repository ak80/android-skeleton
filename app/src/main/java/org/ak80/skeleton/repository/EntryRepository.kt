package org.ak80.skeleton.repository

import org.ak80.skeleton.entry.EntryGateway
import org.ak80.skeleton.model.Entry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntryRepository @Inject constructor() : EntryGateway {

    var entries = listOf(
        Entry("Foo"),
        Entry("Bar"),
        Entry("Baz")
    )

    override fun loadEntries(callback: (List<Entry>) -> Unit) {
        callback.invoke(entries)
    }


}