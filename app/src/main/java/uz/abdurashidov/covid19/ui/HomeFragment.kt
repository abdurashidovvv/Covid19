package uz.abdurashidov.covid19.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentHomeBinding
import uz.abdurashidov.covid19.utils.Status
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val networkViewModel: NetworkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        launch {
            networkViewModel.getData().collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("@homeFragment", "onCreateView: ${it.data?.rawData}")
                    }

                    Status.ERROR -> {
                        Log.d("@homeFragment", "onCreateView: ${it.message}")
                    }

                    Status.LOADING -> {
                        Log.d("@homeFragment", "onCreateView: ${it.message}")
                    }
                }
            }
        }

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}