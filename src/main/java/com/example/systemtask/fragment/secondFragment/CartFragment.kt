package com.example.systemtask.fragment.secondFragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.systemtask.R
import com.example.systemtask.activity.MainActivity
import com.example.systemtask.adapter.RvAdapter
import com.example.systemtask.base.BaseFragment
import com.example.systemtask.dialog.CustomDialog
import com.example.systemtask.fragment.MenuUIModel
import com.example.systemtask.fragment.MenuViewModel
import com.example.systemtask.repo.pojo.Data
import kotlinx.android.synthetic.main.content_scrolling.rvItems
import kotlinx.android.synthetic.main.content_scrolling_cart.*
import kotlinx.android.synthetic.main.facts_layout_fragment.toolbar
import kotlinx.android.synthetic.main.fragment_cart.toolbar_layout
import kotlinx.android.synthetic.main.toolbar_layout.*
import javax.inject.Inject


class CartFragment @Inject constructor() : BaseFragment() {


    //Function that can be called without having a class instance
    companion object {
        fun newInstance() = CartFragment()
    }

    private val viewModel: MenuViewModel by injectActivityVIewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun setup(view: View) {
        fetchFacts()
    }

    private fun fetchFacts() {
        viewModel.commonViewStateLiveData.removeObservers(this)
        viewModel.commonViewStateLiveData.observe(this, Observer(this::handleCommonViewState))

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolBar)
            toolbar.setNavigationIcon(R.drawable.ic_arrow_white)
            toolbar_layout.isTitleEnabled = false
            toolbar.setNavigationOnClickListener {
                (activity as MainActivity).supportFragmentManager.popBackStack()
            }
            toolbar.title = resources.getString(R.string.my_cart)
            toolbar.setTitleTextColor(resources.getColor(android.R.color.white))

        }

        chkDinIn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                chkTakeAway.isChecked = false
            }
        }

        chkTakeAway.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                chkDinIn.isChecked = false
            }
        }

        val content = SpannableString(resources.getString(R.string.show_more))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvShowMore.text = content

        //checking viewModel for data present

        checkData()

    }

    private fun checkData() {
        if (viewModel.data != null) {
            updateAdapter(viewModel.data ?: ArrayList<Data>())
        } else {
            viewModel.fetchData()
        }
    }


    private fun updateAdapter(arrayList: ArrayList<Data>) {

        val factsAdapter = RvAdapter(arrayList, "from") {
            updateCount(arrayList)
        }
        rvItems.adapter = factsAdapter
        rvItems.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        factsAdapter.notifyDataSetChanged()


        tvShowMore.setOnClickListener {
            factsAdapter.showMore()
        }

    }

    private fun updateCount(arrayList: ArrayList<Data>) {

        viewModel.updateCount(arrayList)
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

        }

    }

}