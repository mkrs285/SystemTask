package com.example.systemtask.fragment

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import com.airtel.xlabs.retailer.sl.utils.ViewModelUtil
import com.example.systemtask.R
import com.example.systemtask.adapter.RvAdapter
import com.example.systemtask.di.component.DaggerApplicationComponent
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
//checking unit test cases in different sdk
@Config(sdk = [21, 22])
class FactsFragmentTest {

    private lateinit var fragmentFactory: FragmentFactory

    private lateinit var fragmentScenario: FragmentScenario<MenuFragment>
    private val viewModel: MenuViewModel = mock()
    private val commonViewStateMutableLiveData: MutableLiveData<MenuUIModel> = MutableLiveData()

    var mockAdapter: RvAdapter = mock()


    @Before
    fun setup() {
        Mockito.`when`(viewModel.commonViewStateLiveData).thenReturn(commonViewStateMutableLiveData)
        val appComponent =
            DaggerApplicationComponent.builder().create(ApplicationProvider.getApplicationContext())
                .build()
        this.fragmentFactory = appComponent.fragmentFactory()

        fragmentScenario = launchFragmentInContainer(
            themeResId = R.style.AppTheme,
            instantiate = {
                MenuFragment.newInstance().apply {
                    viewModelFactory = ViewModelUtil.createFor(viewModel)
                }
            })
    }


    @Test
    @Throws(Exception::class)
    fun onViewCreated() {

        if (viewModel.factsResponse != null) {
            Mockito.verify(viewModel).fetchFacts()
        } else {
        }
    }


}