package data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RoutingRepositoryImpl : RoutingRepository {
    private val _routingStack: MutableStateFlow<List<Routing>> = MutableStateFlow(listOf(Routing.Home))
    override val routingStack: StateFlow<List<Routing>> = _routingStack.asStateFlow()

    override fun navigateTo(routing: Routing) {
        _routingStack.update {
            if (it.last() == routing) {
                it
            } else {
                it + routing
            }
        }
    }

    override fun popBackStack() {
        _routingStack.update {
            it.dropLast(1)
        }
    }

    override fun clearBackStack() {
        _routingStack.update {
            listOf(Routing.Home)
        }
    }
}
