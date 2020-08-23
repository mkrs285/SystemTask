package com.example.systemtask.activity

import android.os.Bundle
import com.example.systemtask.R
import com.example.systemtask.base.BaseActivity
import com.example.systemtask.dialog.CustomDialog
import com.example.systemtask.fragment.MenuFragment

class MainActivity : BaseActivity() {

    lateinit var dialog: androidx.appcompat.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Progress bar instance
        dialog =
                CustomDialog.getProgressDialog(
                        this,
                        resources.getString(R.string.loader)
                )

        //Adding Fragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MenuFragment.newInstance())
                .addToBackStack(MenuFragment::class.java.name)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}