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
class RepositoryPresenter(val fragment: Fragment, val router: Router) : MvpPresenter<RepositoryView>() {

    private val PUT_EXTRA = RepositoryFragment::class.java.name + "extra.repository"

     private var repository: GithubRepository? = null

    fun loadData(){
         val args = fragment.arguments
        repository = args?.getParcelable(PUT_EXTRA)
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

