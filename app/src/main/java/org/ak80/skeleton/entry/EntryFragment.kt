package org.ak80.skeleton.entry

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.view.*
import android.widget.*
import dagger.android.support.DaggerFragment
import org.ak80.skeleton.R
import org.ak80.skeleton.di.ActivityScoped
import org.ak80.skeleton.model.Entry
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import javax.inject.Inject

/**
 * Display list of items
 */
@ActivityScoped
class EntryFragment @Inject constructor() : DaggerFragment(), EntryContract.View, AnkoLogger {

    @Inject
    lateinit var presenter: EntryContract.Presenter

    private var listAdapter: EntryAdapter? = null

    private var entryView: LinearLayout? = null

    private var entryViewLabel: TextView? = null

    private var entryListener: EntryListener = object : EntryListener {

        override fun onEntryClick(clickedEntry: Entry) {
            showMessage("clicked on $clickedEntry")
        }

        override fun onEntryLongClick(clickedEntry: Entry) {
            showMessage("long clicked on $clickedEntry")
        }

        override fun onCompleteEntryClick(completedEntry: Entry) {
            presenter.completeEntry(completedEntry)
        }

        override fun onActivateEntryClick(activatedEntry: Entry) {
            presenter.activateEntry(activatedEntry)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = EntryAdapter(ArrayList(0), entryListener)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        info("on result with requestCode=$requestCode, resultCode=$resultCode from intent=$data")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.entry_frag, container, false)
        setupEntryView(root)

        setupFloatingActionButton()
        setHasOptionsMenu(true)

        return root
    }


    private fun setupEntryView(root: View) {
        entryView = root.findViewById(R.id.entry_view)
        entryViewLabel = entryView!!.findViewById(R.id.entry_list_label)
        entryViewLabel!!.setText(R.string.list_title)
        val listView = root.findViewById<ListView>(R.id.entries_list)
        listView.adapter = listAdapter

    }

    private fun setupFloatingActionButton() {
        val fab = activity.findViewById<FloatingActionButton>(R.id.fab_add_entry)
        fab.setImageResource(R.drawable.ic_add)
        fab.setOnClickListener { presenter.addNewEntry() }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.entry_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_clear -> presenter.clearEntries()
            R.id.menu_about -> presenter.about()
        }
        return true
    }

    override fun showEntries(entries: List<Entry>) {
        listAdapter!!.replaceData(entries)
    }

    override fun showEntryComplete() {
        showMessage(getString(R.string.entry_marked_complete))
    }

    override fun showEntryActive() {
        showMessage(getString(R.string.entry_marked_active))
    }

    override fun showAbout(id: Int) {
        showMessage(getString(id))
    }

    override fun showMessage(id: Int) {
        showMessage(getString(id))
    }

    private fun showMessage(message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    interface EntryListener {

        fun onEntryClick(clickedEntry: Entry)

        fun onEntryLongClick(clickedEntry: Entry)

        fun onCompleteEntryClick(completedEntry: Entry)

        fun onActivateEntryClick(activatedEntry: Entry)

    }

    private class EntryAdapter(entries: List<Entry>, private val entryListener: EntryListener) : BaseAdapter() {

        private var entries: List<Entry> = mutableListOf()

        init {
            setList(entries)
        }

        fun replaceData(entries: List<Entry>) {
            setList(entries)
            notifyDataSetChanged()
        }

        private fun setList(entries: List<Entry>) {
            this.entries = entries
        }

        override fun getCount(): Int {
            return entries.size
        }

        override fun getItem(i: Int): Entry {
            return entries[i]
        }

        override fun getItemId(i: Int): Long {
            return i.toLong()
        }

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            val rowView: View? = getView(view, viewGroup)

            val entry = getItem(i)

            val titleTV = rowView!!.findViewById<TextView>(R.id.title)
            titleTV.text = entry.title

            val completeCB = rowView.findViewById<CheckBox>(R.id.complete)
            activateCheckBoxInView(completeCB, entry, rowView, viewGroup)
            setOnClickListenerForCheckBox(completeCB, entry)

            rowView.setOnClickListener { entryListener.onEntryClick(entry) }

            rowView.setOnLongClickListener {
                entryListener.onEntryLongClick(entry)
                true
            }
            return rowView
        }

        private fun setOnClickListenerForCheckBox(completeCB: CheckBox, entry: Entry) {
            completeCB.setOnClickListener {
                if (!entry.passed) {
                    entryListener.onCompleteEntryClick(entry)
                } else {
                    entryListener.onActivateEntryClick(entry)
                }
            }
        }

        private fun activateCheckBoxInView(completeCB: CheckBox, entry: Entry, rowView: View, viewGroup: ViewGroup) {
            completeCB.isChecked = entry.passed
            if (entry.passed) {
                rowView.background = ResourcesCompat.getDrawable(
                    viewGroup.context.resources,
                    R.drawable.list_completed_touch_feedback,
                    null
                )
            } else {
                rowView.background = ResourcesCompat.getDrawable(
                    viewGroup.context.resources,
                    R.drawable.touch_feedback, null
                )
            }
        }

        private fun getView(view: View?, viewGroup: ViewGroup): View? {
            var rowView: View? = view
            if (rowView == null) {
                val inflater = LayoutInflater.from(viewGroup.context)
                rowView = inflater.inflate(R.layout.entry_item, viewGroup, false)
            }
            return rowView
        }
    }

}