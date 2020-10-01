package com.example.systemtask.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.systemtask.R
import com.example.systemtask.activity.MainActivity
import com.example.systemtask.adapter.RvAdapter
import com.example.systemtask.base.BaseActivity
import com.example.systemtask.base.BaseFragment
import com.example.systemtask.dialog.CustomDialog
import com.example.systemtask.fragment.secondFragment.CartFragment
import com.example.systemtask.repo.pojo.Data
import com.example.systemtask.utils.visible
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.facts_layout_fragment.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import javax.inject.Inject
import kotlin.math.abs


class MenuFragment @Inject constructor() : BaseFragment() {

    //Function that can be called without having a class instance
    companion object {
        fun newInstance() = MenuFragment()
    }

    // Reference to the ViewModel scoped to its Activity
    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val viewModel: MenuViewModel by injectActivityVIewModels()

    //TAG
    private val className = MenuFragment::class.simpleName


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.facts_layout_fragment, container, false)
    }

    override fun injectDependencies(baseActivity: BaseActivity?) {
        baseActivity?.createMenuComponent()
        baseActivity?.getMenuComponent()?.inject(this)

    }


    override fun setup(view: View) {
        fetchFacts()

    }


    private fun fetchFacts() {
        viewModel.commonViewStateLiveData.removeObservers(this)
        viewModel.commonViewStateLiveData.observe(this, Observer(this::handleCommonViewState))

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolBar)
            toolbar_layout.isTitleEnabled = false
            toolbar.setNavigationIcon(R.drawable.ic_arrow_white)
            toolbar.setNavigationOnClickListener {
            }
            toolbar.inflateMenu(R.menu.menu)
        }


        //checking viewModel for data present

        checkData()

        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            run {
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    //  Collapsed
                    toolbar.title = resources.getString(R.string.inka_restaurant)

                } else {
                    //Expanded
                    toolbar.title = ""
                    cardView.visible()

                }
            }
        })
        viewCart.setOnClickListener {

            (activity as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CartFragment.newInstance())
                    .addToBackStack(MenuFragment::class.java.name)
                    .commit()
        }

    }

    private fun checkData() {
        if (viewModel.data != null) {
            updateAdapter(viewModel.data ?: ArrayList<Data>())
        } else {
            viewModel.fetchData()
        }
    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(activity)
        builder.setMessage("No Internet Connection")
        builder.setCancelable(false)

        builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
            checkData()
            dialog.dismiss()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, _ ->
            activity?.finish()
            dialog.dismiss()
        }

        builder.show()
    }


    private fun handleCommonViewState(factsUIModel: MenuUIModel) {
        //observing data from viewModel using liveData
        when (factsUIModel) {
            is MenuUIModel.ShowProgress -> {
                if (factsUIModel.flag) {
                    CustomDialog.updateText("")
                    (activity as MainActivity).dialog.show()
                } else {
                    (activity as MainActivity).dialog.dismiss()
                }

            }
            is MenuUIModel.FactsData -> {
                factsUIModel.packs?.let { updateAdapter(it) }
            }

            is MenuUIModel.ShowError -> {
                //Displaying error message
                //Using view extensions to Display Views

            }

            is MenuUIModel.UpdateCount -> {
                tvTotalCount.text = "(${factsUIModel.count} ITEMS)"
            }

        }

    }

    private fun updateAdapter(arrayList: ArrayList<Data>) {

        val factsAdapter = RvAdapter(arrayList,"") {
            updateCount(arrayList)
        }
        rvItems.adapter = factsAdapter
        rvItems.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        factsAdapter.notifyDataSetChanged()
    }

    private fun updateCount(arrayList: ArrayList<Data>) {

        viewModel.updateCount(arrayList)
    }


}