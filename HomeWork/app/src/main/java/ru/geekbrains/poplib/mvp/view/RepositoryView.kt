package ru.geekbrains.poplib.mvp.view

import android.content.Intent
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun init()
    fun setId(id: String?)
    fun setName(name: String?)
    fun setForksCounts(forksCount: Int?)
}