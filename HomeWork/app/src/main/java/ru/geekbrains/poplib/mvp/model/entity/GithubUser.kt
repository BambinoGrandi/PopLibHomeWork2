package ru.geekbrains.poplib.mvp.model.entity

import com.google.gson.annotations.Expose

class GithubUser (
    @Expose val login: String,
    @Expose val avatarUrl: String,
    @Expose val reposUrl: String
){
}