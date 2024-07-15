package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import viewmodels.MainViewModel
import viewmodels.MainRouterViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::MainRouterViewModel)
}