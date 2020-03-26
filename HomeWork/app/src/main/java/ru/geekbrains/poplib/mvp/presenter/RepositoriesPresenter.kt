package ru.geekbrains.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.poplib.mvp.view.RepositoriesView
import ru.geekbrains.poplib.mvp.view.list.RepositoryItemView
import ru.geekbrains.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber

@InjectViewState
class RepositoriesPresenter(
    val repositoriesRepo: GithubRepositoriesRepo,
    val router: Router,
    val mainThreadScheduler: Scheduler
) : MvpPresenter<RepositoriesView>() {

    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setTitle(repository.name)
        }
    }

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()

        repositoryListPresenter.itemClickListener = { itemView ->
            val repository = repositoryListPresenter.repositories[itemView.pos]

            //Практическое задание
            router.navigateTo(Screens.RepositoryScreen(repository))

        }
    }

    fun loadRepos() {
        repositoriesRepo.getRepos()
            .observeOn(Schedulers.computation())
            .subscribeOn(mainThreadScheduler)
            .subscribe(
                { repos ->
                    repositoryListPresenter.repositories.clear()
                    repositoryListPresenter.repositories.addAll(repos)
                    viewState.updateList()
                },
                { e ->
                    Timber.e(e)
                })
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}
