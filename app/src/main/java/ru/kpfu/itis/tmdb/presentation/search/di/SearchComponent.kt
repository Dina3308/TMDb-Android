package ru.kpfu.itis.tmdb.presentation.search.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope
import ru.kpfu.itis.tmdb.presentation.search.SearchFragment

@Subcomponent(
    modules = [
        SearchModule::class
    ]
)
@ScreenScope
interface SearchComponent {
    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}