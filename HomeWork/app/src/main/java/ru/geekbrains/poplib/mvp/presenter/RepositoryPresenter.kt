package ru.geekbrains.poplib.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.RepositoryView
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoryPresenter(val repository: GithubRepository?, val router: Router) : MvpPresenter<RepositoryView>() {

    fun loadData(){

        viewState.setId(repository?.id)
        viewState.setName(repository?.name)
        viewState.setForksCounts(repository?.forksCount)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }


}

