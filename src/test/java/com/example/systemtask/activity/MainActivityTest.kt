package com.example.systemtask.activity

import android.os.Bundle
import com.example.systemtask.R
import com.example.systemtask.fragment.MenuFragment
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
//checking unit test cases in different sdk
@Config(sdk = [21, 22])
class MainActivityTest  {

    var activityController: ActivityController<MainActivity>? = null
    var mainActivity: MainActivity? = null


    @Before
    fun setup() {
        //Activity created
        activityController =
            Robolectric.buildActivity(MainActivity::class.java).create()
        //Checking not null for fragmentInjector,dialog
        assertNotNull((activityController as ActivityController<MainActivity>?)?.get()?.fragmentInjector)


        (activityController as ActivityController<MainActivity>?)?.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container,MenuFragment.newInstance())?.commit()


    }


    @Test
    fun reCreatesActivity() {
        val bundle = Bundle()
        assertNotNull(activityController)
        //destroying current activity
        activityController?.saveInstanceState(bundle)?.pause()?.stop()?.destroy()

        //recreating activity
        activityController =
            Robolectric.buildActivity(MainActivity::class.java).create(bundle).start()
                .restoreInstanceState(bundle)
                .resume()
                .visible()
        //getting mainActivity from controller
        mainActivity = (activityController as ActivityController<MainActivity>?)?.get()

        assertNotNull(mainActivity)

    }

}