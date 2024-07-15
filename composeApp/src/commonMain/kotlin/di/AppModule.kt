package di

import org.koin.dsl.module

val appModule = module {
    includes(
        viewModelModule,
        repositoryModule
    )
}