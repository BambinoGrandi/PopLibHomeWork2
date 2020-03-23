package ru.geekbrains.poplib.mvp.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.RepositoryView
import ru.geekbrains.poplib.navigation.Screens
import ru.geekbrains.poplib.ui.fragments.RepositoryFragment
import ru.terrakok.cicerone.Router

@InjectViewState
class RepositoryPresenter(val repository: GithubRepository?, val router: Router) : MvpPresenter<RepositoryView>() {

    fun loadData(){

        viewState.setText(repository?.id, repository?.name, repository?.forksCount)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun backClicked(): Boolean {
        router.replaceScreen(Screens.RepositoriesScreen())
        return true
    }


}

