package uz.abdurashidov.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.databinding.ActivityMainBinding
import uz.abdurashidov.covid19.utils.Status
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    private val viewModel: NetworkViewModel by viewModels()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launch {
            viewModel.getData().collect {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d("@@@", "onCreate: ${it.message}")
                    }

                    Status.SUCCESS -> {
                        if (it.data!=null){
                            Log.d("@@@", "onCreate: ${it.data}")
                        }
                    }

                    Status.ERROR -> {
                        Log.d("@@@", "onCreate: ${it.message}")
                    }
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}