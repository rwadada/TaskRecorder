package viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlin.time.Duration

data class UiState(
    val currentDateTimeString: String = "",
    val inProgress: Boolean = false,
    val elapsedTimeString: String = "00:00"
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
            startDateTime = null
            _uiState.update {
                it.copy(
                    inProgress = false,
                    elapsedTimeString = "00:00"
                )
            }
        } else {
            startDateTime = currentDateTime.value
            _uiState.update {
                it.copy(
                    inProgress = true,
                    elapsedTimeString = "00:00"
                )
            }
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

    @OptIn(FormatStringsInDatetimeFormats::class)
    private fun updateCurrentDateTime() {
        val format: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
            byUnicodePattern("yyyy/MM/dd HH:mm")
        }
        val newDateTimeString = currentDateTime.value.format(format)
        if (uiState.value.currentDateTimeString != newDateTimeString) {
            _uiState.update {
                uiState.value.copy(
                    currentDateTimeString = newDateTimeString,
                    elapsedTimeString = if (it.inProgress) {
                        val currentInstant: Instant = currentDateTime.value.toInstant(TimeZone.currentSystemDefault())
                        val startInstant: Instant = startDateTime!!.toInstant(TimeZone.currentSystemDefault())
                        val duration: Duration = currentInstant - startInstant
                        val totalMinutes: Long = duration.inWholeMinutes
                        val hours: Long = totalMinutes / 60
                        val minutes: Long = totalMinutes % 60

                        val formattedDifference =
                            "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}"
                        formattedDifference
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
}