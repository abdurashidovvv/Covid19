package uz.abdurashidov.covid19.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.database.entity.Article
import uz.abdurashidov.covid19.repository.DatabaseRepository
import uz.abdurashidov.covid19.utils.Resource
import uz.abdurashidov.covid19.utils.Status
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : ViewModel() {

    private val stateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource(Status.LOADING, null, "Loading"))

    fun getAllUsers(): MutableStateFlow<Resource<List<Article>>> {
        viewModelScope.launch {
            stateFlow.value = Resource(Status.SUCCESS, databaseRepository.getArticles(), "Success")
        }
        return stateFlow
    }

    fun addArticle(article: Article) {
        viewModelScope.launch {
            databaseRepository.addArticle(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            databaseRepository.deleteArticle(article)
        }
    }


}
