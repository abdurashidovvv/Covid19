package uz.abdurashidov.covid19.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import uz.abdurashidov.covid19.repository.NetworkRepository
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {
}