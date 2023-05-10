package uz.abdurashidov.covid19.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.models.covidmodels.ResponseCovidData
import uz.abdurashidov.covid19.models.covidnewsmodel.NewsResponse
import uz.abdurashidov.covid19.repository.NetworkRepository
import uz.abdurashidov.covid19.utils.Resource
import uz.abdurashidov.covid19.utils.Status
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {

    private val stateFlow =
        MutableStateFlow<Resource<NewsResponse>>(Resource(Status.LOADING, null, "Loading"))

    fun getData(): MutableStateFlow<Resource<NewsResponse>> {
        viewModelScope.launch {
            stateFlow.value = Resource(Status.LOADING, null, "Loading")
            networkRepository.getCovidNews().catch {
                stateFlow.value = Resource(Status.ERROR, null, "${it.message}")
            }.collectLatest {
                if (it.isSuccessful) {
                    stateFlow.value = Resource(Status.SUCCESS, it.body(), "Success")
                } else {
                    stateFlow.value = Resource(Status.ERROR, null, "Response Error")
                }
            }
        }
        return stateFlow
    }
}