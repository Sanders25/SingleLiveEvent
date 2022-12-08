package com.example.singleliveevent.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _performActionFlow = MutableStateFlow<Unit?>(null)
    val performActionFlow = _performActionFlow.asStateFlow()

    // todo on dialog back pressed
    val dataFlow = performActionFlow.filterNotNull().flatMapLatest {
        flow {
            delay(2.seconds)
            emit(Random.nextInt(100))
        }.onCompletion { _performActionFlow.value = null }
    }.shareIn(
        viewModelScope,
        SharingStarted.Lazily,
        1
    )

    fun startAction(): Boolean {
        return _performActionFlow.compareAndSet(null, Unit)
    }

/*    fun actionDone(): Boolean {
        return _performActionFlow.compareAndSet(Unit, null)
    }*/
}