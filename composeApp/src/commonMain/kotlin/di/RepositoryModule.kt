package di

import data.repository.RoutingRepository
import data.repository.RoutingRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::RoutingRepositoryImpl) { bind<RoutingRepository>() }
}