package uz.abdurashidov.covid19.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentHomeBinding
import uz.abdurashidov.covid19.models.PrevModel
import uz.abdurashidov.covid19.ui.adapter.PreventationAdapter
import uz.abdurashidov.covid19.utils.Status
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope, NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<PrevModel>
    private lateinit var preventationAdapter: PreventationAdapter
    private val networkViewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        preventationAdapter = PreventationAdapter(list)
        binding.preventionRv.adapter = preventationAdapter

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
        binding.navView.setNavigationItemSelectedListener(this)
        binding.menu.setOnClickListener {
            binding.drawer.open()
        }

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                Toast.makeText(context, "Home !", Toast.LENGTH_SHORT).show()
            }

            R.id.statistics -> {
                Toast.makeText(context, "Statistics !", Toast.LENGTH_SHORT).show()
            }

            R.id.symptoms -> {
                Toast.makeText(context, "Symptoms !", Toast.LENGTH_SHORT).show()
            }

            R.id.preventation -> {
                Toast.makeText(context, "Preventation !", Toast.LENGTH_SHORT).show()
            }

            R.id.article -> {
                Toast.makeText(context, "Article !", Toast.LENGTH_SHORT).show()
            }

            R.id.news -> {
                Toast.makeText(context, "News !", Toast.LENGTH_SHORT).show()
            }

            R.id.help -> {
                Toast.makeText(context, "Help !", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private fun loadData() {
        list.add(PrevModel(R.drawable.prev_mask, "Use mask", "Lorem ipsum dolor sit amet"))
        list.add(PrevModel(R.drawable.prev_hand, "Wash your hand", "Lorem ipsum dolor sit amet"))
        list.add(
            PrevModel(
                R.drawable.prev_close,
                "Avoid close contact",
                "Lorem ipsum dolor sit amet"
            )
        )
    }
}