package com.example.systemtask.base

import com.example.systemtask.repo.pojo.Data

interface BaseInterface {


    interface IResponseListener<T> {

        fun onProgress()

        fun onSuccess(resp: ArrayList<Data>)

        fun onFailure(t: Throwable)

    }

    interface getTotalCount {

        fun getCount(count:Int)


    }
}