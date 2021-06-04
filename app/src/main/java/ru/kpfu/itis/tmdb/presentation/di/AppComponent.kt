package ru.kpfu.itis.tmdb.presentation.di

import android.content.Context
import com.bumptech.glide.Glide
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.kpfu.itis.tmdb.data.di.NetworkModule
import ru.kpfu.itis.tmdb.data.di.ServiceModule
import ru.kpfu.itis.tmdb.presentation.details.movie.di.DetailsMovieComponent
import ru.kpfu.itis.tmdb.presentation.details.tv.di.DetailsTvComponent
import ru.kpfu.itis.tmdb.presentation.movies.di.MoviesComponent
import ru.kpfu.itis.tmdb.presentation.multi.di.DetailsMultiComponent
import ru.kpfu.itis.tmdb.presentation.search.SearchFragment
import ru.kpfu.itis.tmdb.presentation.search.di.SearchComponent
import ru.kpfu.itis.tmdb.presentation.tv_shows.TvShowsFragment
import ru.kpfu.itis.tmdb.presentation.tv_shows.di.TvShowsComponent
import ru.kpfu.itis.tmdb.utils.BindingComponent
import ru.kpfu.itis.tmdb.utils.BindingModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ServiceModule::class,
        SubcomponentModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }

    fun detailsMovieComponentFactory(): DetailsMovieComponent.Factory

    fun detailsTvComponentFactory(): DetailsTvComponent.Factory

    fun moviesComponentFactory(): MoviesComponent.Factory

    fun detailsMultiComponentFactory(): DetailsMultiComponent.Factory

    fun searchComponentFactory(): SearchComponent.Factory

    fun tvShowsComponentFactory(): TvShowsComponent.Factory

    fun dataBindingComponent(): BindingComponent.Factory
}

@Module(
    subcomponents = [
        BindingComponent::class
    ]
)
object SubcomponentModule