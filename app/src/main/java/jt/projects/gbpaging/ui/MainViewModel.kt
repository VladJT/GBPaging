package jt.projects.gbpaging.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import jt.projects.gbpaging.intercators.NewsInteractor
import jt.projects.gbpaging.model.News
import jt.projects.gbpaging.utils.DB_NEWS_MAX_COUNT
import jt.projects.gbpaging.utils.LOG_TAG
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: NewsInteractor) : ViewModel() {

    private var job: Job? = null

    private val _resultRecycler = MutableStateFlow<List<News>>(listOf())
    val resultRecycler get() = _resultRecycler.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    lateinit var newsFlow: Flow<PagingData<News>>


    init {
        fillLocalCache()
        loadPagedNews()
    }

    // заполнение локальной базы данных из популярных постов с Reddit
    private fun fillLocalCache() {
        _isLoading.tryEmit(true)
        launchOrError(
            action = {
                if (interactor.getNewsFromLocalSource().size < DB_NEWS_MAX_COUNT) {
                    //   interactor.clearLocalSource()
                    interactor.fillLocalDataSourceFromRemote()
                }
                _isLoading.tryEmit(false)
            },
            error = {
                _isLoading.tryEmit(false)
            }
        )
    }

    // загрузка данных порциями
    private fun loadPagedNews() {
        newsFlow = interactor.getPagedNews().cachedIn(viewModelScope)
    }


    // загрузка сразу всех данных
    private fun loadData() {
        _isLoading.tryEmit(true)

        launchOrError(
            action = {
                val data = interactor.getNewsFromLocalSource()
                _resultRecycler.tryEmit(data)
                _isLoading.tryEmit(false)
            },
            error = {
                _resultRecycler.tryEmit(listOf())
                _isLoading.tryEmit(false)
            }
        )
    }

    private fun launchOrError(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend () -> Unit,
        error: (Exception) -> Unit
    ) {
        job?.cancel()
        job = viewModelScope.launch(dispatcher) {
            try {
                action.invoke()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(LOG_TAG, "$e")
                error.invoke(e)
            }
        }
    }

}