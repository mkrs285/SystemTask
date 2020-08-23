package com.example.systemtask.fragment

import com.example.systemtask.repo.pojo.Data


sealed class MenuUIModel {
    class ShowProgress(val flag: Boolean) : MenuUIModel()
    class FactsData(val packs: ArrayList<Data>?) : MenuUIModel()
    class ShowError(val status: String) : MenuUIModel()
    class UpdateCount(val count: String) : MenuUIModel()
}