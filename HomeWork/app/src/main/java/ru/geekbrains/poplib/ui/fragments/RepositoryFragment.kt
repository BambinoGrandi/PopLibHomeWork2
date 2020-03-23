package ru.geekbrains.poplib.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.presenter.RepositoryPresenter
import ru.geekbrains.poplib.mvp.view.RepositoryView
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.BackButtonListener

class RepositoryFragment() : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private val PUT_EXTRA = RepositoryFragment::class.java.name + "extra.repository"

        fun newInstance(repository: GithubRepository): RepositoryFragment {
            val fragment = RepositoryFragment()
            val args = Bundle()
            args.putParcelable(PUT_EXTRA, repository)
            fragment.arguments = args
            return fragment
        }

    }

    @InjectPresenter
    lateinit var presenter: RepositoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_repository, null)

    override fun init() {
        tv_id_value_repository.text
        tv_name_value_repository.text
        tv_forksCount_value_repository.text
    }

    override fun setId(id: String?) {
        tv_name_value_repository.text = id
    }

    override fun setName(name: String?) {
        tv_name_value_repository.text = name
    }

    override fun setForksCounts(forksCount: Int?) {
        tv_forksCount_value_repository.text = forksCount.toString()
    }

    private var repository: GithubRepository? = null

    @ProvidePresenter
    fun providePresenter() : RepositoryPresenter{
        val args = arguments
        repository = args?.getParcelable(PUT_EXTRA)
        return RepositoryPresenter(repository, App.instance.getRouter())
    }

    override fun backClicked() = presenter.backClicked()
}