package ru.geekbrains.poplib.di.modules


import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.cache.image.IImageCache
import ru.geekbrains.poplib.mvp.model.network.NetworkStatus
import ru.geekbrains.poplib.ui.image.GlideImageLoader
import javax.inject.Singleton

//Реализовать модуль и внедрение всего, что касается картинок. Кэш тоже сюда.
@Module(
    includes = [
        ApiModule::class,
        CacheModule::class
    ]
)
class ImageModule {

    @Singleton
    @Provides
    fun glideImageLoader(cache: IImageCache, networkStatus: NetworkStatus): GlideImageLoader {
        return GlideImageLoader(cache, networkStatus)
    }
}