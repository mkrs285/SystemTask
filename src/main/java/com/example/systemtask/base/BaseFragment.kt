package com.example.systemtask.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    //creating Instance
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            mActivity = context
        }
        injectDependencies(mActivity)
    }


    protected abstract fun injectDependencies(baseActivity: BaseActivity?)


    //ktx co-routine to accessing viewModels
    protected inline fun <reified VM : ViewModel>
            injectActivityVIewModels(): Lazy<VM> = activityViewModels { viewModelFactory }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
    }

    protected abstract fun setup(view: View)
}