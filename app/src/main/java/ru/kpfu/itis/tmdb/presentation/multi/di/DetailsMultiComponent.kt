package ru.kpfu.itis.tmdb.presentation.multi.di

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.di.ScreenScope
import ru.kpfu.itis.tmdb.presentation.multi.DetailsMultiFragment

@Subcomponent(
    modules = [
        DetailsMultiModule::class
    ]
)
@ScreenScope
interface DetailsMultiComponent {
    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): DetailsMultiComponent
    }

    fun inject(fragment: DetailsMultiFragment)
}