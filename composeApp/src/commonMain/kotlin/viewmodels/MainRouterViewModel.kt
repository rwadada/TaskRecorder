package viewmodels

import androidx.lifecycle.ViewModel
import data.repository.Routing
import data.repository.RoutingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainRouterViewModel(routingRepository: RoutingRepository) : ViewModel() {
    val navigationEvent: Flow<Routing?> = routingRepository.routingStack.map { it.lastOrNull() }
}