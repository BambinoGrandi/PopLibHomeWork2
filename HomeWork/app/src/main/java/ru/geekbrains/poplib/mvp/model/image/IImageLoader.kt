package ru.geekbrains.poplib.mvp.model.image

interface IImageLoader<T> {
    fun loadImage(url: String, container: T)
}