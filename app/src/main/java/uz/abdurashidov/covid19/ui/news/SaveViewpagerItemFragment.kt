package uz.abdurashidov.covid19.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.database.entity.Article
import uz.abdurashidov.covid19.databinding.FragmentSaveViewpagerItemBinding
import uz.abdurashidov.covid19.ui.adapter.ArticleAdapter
import uz.abdurashidov.covid19.viewmodel.DatabaseViewModel
import kotlin.coroutines.CoroutineContext

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class SaveViewpagerItemFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentSaveViewpagerItemBinding.inflate(layoutInflater) }
    private val databaseViewModel: DatabaseViewModel by viewModels()
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var list: ArrayList<Article>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        list = ArrayList()
        articleAdapter =
            ArticleAdapter(list as ArrayList<uz.abdurashidov.covid19.models.covidnewsmodel.Article>)
        binding.myRv.adapter = articleAdapter
        launch(Dispatchers.Main) {
            databaseViewModel.getAllUsers().collectLatest {
                list.addAll(it.data ?: emptyList())
                articleAdapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}