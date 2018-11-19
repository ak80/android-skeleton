package org.ak80.skeleton.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.android.support.DaggerFragment
import org.ak80.skeleton.R
import org.ak80.skeleton.di.ActivityScoped
import javax.inject.Inject


/**
 * Display list of items
 */
@ActivityScoped
class InfoFragment @Inject constructor() : DaggerFragment(), InfoContract.View {

    @Inject
    lateinit var presenter: InfoContract.Presenter

    private var infoView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.info_frag, container, false)
        infoView = root.findViewById(R.id.info)

        presenter.takeView(this)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun setInfoText(id: Int) {
        infoView!!.text = getString(id)
    }
}