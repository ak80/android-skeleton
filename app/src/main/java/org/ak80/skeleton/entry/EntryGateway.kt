package org.ak80.skeleton.entry

import org.ak80.skeleton.model.Entry

/**
 * Repository for [Entry]s
 */
interface EntryGateway {

    fun loadEntries(callback: (List<Entry>) -> Unit)

}