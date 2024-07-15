package data.repository

import kotlinx.coroutines.flow.StateFlow

interface RoutingRepository {
    val routingStack: StateFlow<List<Routing>>
    
    fun navigateTo(routing: Routing)
    
    fun popBackStack()
    
    fun clearBackStack()
}

sealed interface Routing {
    data object Home : Routing
    data object SelectTask : Routing
}