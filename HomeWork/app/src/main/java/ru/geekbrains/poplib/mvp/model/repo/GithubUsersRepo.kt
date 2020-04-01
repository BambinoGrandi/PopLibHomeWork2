package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource

class GithubUsersRepo(val api: IDataSource) {
    fun getUser(userName: String) = api.getUser(userName).subscribeOn(Schedulers.io())
}