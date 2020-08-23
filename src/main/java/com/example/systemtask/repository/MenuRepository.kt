package com.example.systemtask.repository

import com.example.systemtask.R
import com.example.systemtask.base.BaseInterface
import com.example.systemtask.fragment.MenuInterface
import com.example.systemtask.network.ApiHelper
import com.example.systemtask.repo.pojo.Data
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepository @Inject constructor(
        private val apiHelper: ApiHelper
) : MenuInterface {


    override fun getFacts(responseListener: BaseInterface.IResponseListener<Data>) {

        try {


            responseListener.onProgress()

            val arrayList = ArrayList<Data>()

            val data1 = Data(R.string.guac_de_la_costa, R.string.guac_de_la_costa, "2", 0)

            val data2 = Data(R.string.guac_de_la_costa, R.string.guac_de_la_costa, "1", 0)
            val data3 = Data(R.string.guac_de_la_costa, R.string.guac_de_la_costa, "2", 0)

            arrayList.add(data1)
            arrayList.add(data2)
            arrayList.add(data3)

            responseListener.onSuccess(arrayList)
        } catch (e: Exception) {
            responseListener.onFailure(e)
        }

    }

    override fun getTotalCount(arrayList: ArrayList<Data>, responseListener: BaseInterface.getTotalCount) {
        var totalCount = 0
        for (data in arrayList) {
            totalCount += data.count
        }
        responseListener.getCount(totalCount)
    }
}