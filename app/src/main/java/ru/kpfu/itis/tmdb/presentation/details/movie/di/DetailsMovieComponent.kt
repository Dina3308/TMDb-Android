package ru.kpfu.itis.tmdb.presentation.details.movie.di

import  androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.details.movie.DetailsMovieFragment
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope

@Subcomponent(
    modules = [
        DetailsMovieModule::class
    ]
)
@ScreenScope
interface DetailsMovieComponent {
    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): DetailsMovieComponent
    }

    fun inject(fragment: DetailsMovieFragment)
}