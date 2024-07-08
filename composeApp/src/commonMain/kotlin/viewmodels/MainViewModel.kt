package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.WorkHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import ui.extension.toDisplayDurationString

data class UiState(
    val workTitle: String = "sample",
    val currentDateTimeString: String = "",
    val inProgress: Boolean = false,
    val elapsedTimeString: String = "00:00",
    val workHistryList: List<WorkHistory> = emptyList()
)

class MainViewModel : ViewModel(), MainUiEvent {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val currentDateTime: MutableStateFlow<LocalDateTime> =
        MutableStateFlow(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))

    private var startDateTime: LocalDateTime? = null

    init {
        viewModelScope.launch {
            launch {
                currentDateTime.collect {
                    updateCurrentDateTime()
                }
            }
        }
    }

    override fun onClickStartOrStop() {
        if (uiState.value.inProgress) {
            onStopProgress()
        } else {
            onStartProgress()
        }
    }

    private fun onStartProgress() {
        startDateTime = currentDateTime.value
        _uiState.update {
            it.copy(
                inProgress = true,
                elapsedTimeString = "00:00"
            )
        }
    }

    private fun onStopProgress() {
        val newWorkHistory: WorkHistory = WorkHistory.create(
            title = uiState.value.workTitle,
            startDateTime = startDateTime ?: currentDateTime.value,
            endDateTime = currentDateTime.value
        )
        startDateTime = null
        _uiState.update {
            it.copy(
                inProgress = false,
                elapsedTimeString = "00:00",
                workHistryList = it.workHistryList + newWorkHistory
            )
        }
    }

    override fun onClickTaskDropDown() {
        TODO("Not yet implemented")
    }

    override fun onLoadCurrentDateTime() {
        currentDateTime.update {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }

    override fun onClickClear() {
        _uiState.update {
            uiState.value.copy(
                workHistryList = emptyList()
            )
        }
    }

    @OptIn(FormatStringsInDatetimeFormats::class)
    private fun updateCurrentDateTime() {
        val format: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
            byUnicodePattern("yyyy/MM/dd HH:mm")
        }
        val newDateTimeString: String = currentDateTime.value.format(format)
        if (uiState.value.currentDateTimeString != newDateTimeString) {
            _uiState.update {
                uiState.value.copy(
                    currentDateTimeString = newDateTimeString,
                    elapsedTimeString = if (it.inProgress) {
                        val workHistory = WorkHistory.create(
                            title = "sample",
                            startDateTime = startDateTime ?: currentDateTime.value,
                            endDateTime = currentDateTime.value
                        )
                        workHistory.toDisplayDurationString()
                    } else {
                        ""
                    }
                )
            }
        }
    }

    companion object {
        // FIXME: KMPでのInjectionやViewModelProviderの扱いがDesktopの場合で分からなかったので苦肉の策
        private var instance: MainViewModel? = null
        fun getInstance(): MainViewModel {
            if (instance == null) {
                instance = MainViewModel()
            }
            return instance!!
        }
    }
}

interface MainUiEvent {
    fun onClickStartOrStop()
    fun onClickTaskDropDown()
    fun onLoadCurrentDateTime()
    fun onClickClear()
}