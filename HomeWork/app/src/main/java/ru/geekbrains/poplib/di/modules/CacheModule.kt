package ru.geekbrains.poplib.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.cache.IGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.IGithubUsersCache
import ru.geekbrains.poplib.mvp.model.cache.image.IImageCache
import ru.geekbrains.poplib.mvp.model.cache.image.room.RoomImageCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.poplib.mvp.model.cache.room.RoomGithubUsersCache
import ru.geekbrains.poplib.mvp.model.entity.room.MIGRATION_1_2
import ru.geekbrains.poplib.mvp.model.entity.room.MIGRATION_2_3
import ru.geekbrains.poplib.mvp.model.entity.room.db.Database
import ru.geekbrains.poplib.ui.App
import java.io.File
import java.nio.file.Path
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {

    @Named("pathFile")
    @Provides
    fun pathFile(): File {
        val path = (
                App.instance.externalCacheDir?.path
                    ?: App.instance.getExternalFilesDir(null)?.path
                    ?: App.instance.filesDir.path
                ) + File.separator + "image_cache"
        return File(path)
    }

    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    fun usersCache(database: Database): IGithubUsersCache {
        return RoomGithubUsersCache(database)
    }

    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache(database)
    }

    @Provides
    fun imageCache(database: Database, @Named("pathFile") pathFile: File): IImageCache {
        return RoomImageCache(database, pathFile)
    }


}