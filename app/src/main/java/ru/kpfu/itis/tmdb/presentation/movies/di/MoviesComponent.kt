package ru.kpfu.itis.tmdb.presentation.movies.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope
import ru.kpfu.itis.tmdb.presentation.movies.MoviesFragment

@Subcomponent(
    modules = [
        MoviesModule::class
    ]
)
@ScreenScope
interface MoviesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): MoviesComponent
    }

    fun inject(fragment: MoviesFragment)
}