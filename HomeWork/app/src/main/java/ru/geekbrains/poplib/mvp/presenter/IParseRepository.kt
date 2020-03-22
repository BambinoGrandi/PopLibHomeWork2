package ru.geekbrains.poplib.mvp.presenter

import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.RepositoryView

interface IParseRepository {
    fun showData(view: RepositoryView)
}