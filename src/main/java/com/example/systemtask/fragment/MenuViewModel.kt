package com.example.systemtask.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.systemtask.base.BaseInterface
import com.example.systemtask.base.BaseViewModel
import com.example.systemtask.repo.pojo.Data
import com.example.systemtask.repository.MenuRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MenuViewModel @Inject constructor(private val factsRepository: MenuRepository) :
        BaseViewModel() {


    private val commonViewStateMutableLiveData: MutableLiveData<MenuUIModel> = MutableLiveData()
    val commonViewStateLiveData: LiveData<MenuUIModel>
        get() = commonViewStateMutableLiveData

    var data: ArrayList<Data> ?=null


    fun fetchData() {
        this.viewModelScope.launch {
            factsRepository.getFacts(object : BaseInterface.IResponseListener<Data> {
                override fun onProgress() {
                    commonViewStateMutableLiveData.value = MenuUIModel.ShowProgress(true)
                }

                override fun onSuccess(resp: ArrayList<Data>) {
                    commonViewStateMutableLiveData.value = MenuUIModel.ShowProgress(false)
                    data = resp
                    commonViewStateMutableLiveData.value = MenuUIModel.FactsData(resp)
                }

                override fun onFailure(t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }

    }

    fun updateCount(arrayList: ArrayList<Data>) {
        data = arrayList
        this.viewModelScope.launch {
            factsRepository.getTotalCount(data!!,object : BaseInterface.getTotalCount {
                override fun getCount(count: Int) {
                   commonViewStateMutableLiveData.value = MenuUIModel.UpdateCount(count.toString())
                }
            })
        }
    }

}