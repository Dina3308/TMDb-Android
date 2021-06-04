package ru.kpfu.itis.tmdb.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.kpfu.itis.tmdb.data.api.TmdbService
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun bindTmdbService(retrofit: Retrofit): TmdbService = retrofit.create(
        TmdbService::class.java
    )
}