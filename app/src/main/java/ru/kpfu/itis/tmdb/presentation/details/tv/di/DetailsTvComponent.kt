package ru.kpfu.itis.tmdb.presentation.details.tv.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.details.tv.DetailsTvFragment
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope

@Subcomponent(
    modules = [
        DetailsTvModule::class
    ]
)
@ScreenScope
interface DetailsTvComponent {
    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): DetailsTvComponent
    }

    fun inject(fragment: DetailsTvFragment)
}