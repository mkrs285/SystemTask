package com.example.systemtask.fragment

import com.example.systemtask.base.BaseInterface
import com.example.systemtask.repo.pojo.Data

interface MenuInterface {
    fun getFacts(
            responseListener: BaseInterface.IResponseListener<Data>
    )

    fun getTotalCount(
            arrayList: ArrayList<Data>, responseListener: BaseInterface.getTotalCount
    )
}