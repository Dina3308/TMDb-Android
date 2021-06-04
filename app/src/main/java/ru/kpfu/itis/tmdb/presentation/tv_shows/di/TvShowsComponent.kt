package ru.kpfu.itis.tmdb.presentation.tv_shows.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope
import ru.kpfu.itis.tmdb.presentation.tv_shows.TvShowsFragment

@Subcomponent(
    modules = [
        TvShowsModule::class
    ]
)
@ScreenScope
interface TvShowsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): TvShowsComponent
    }

    fun inject(fragment: TvShowsFragment)
}