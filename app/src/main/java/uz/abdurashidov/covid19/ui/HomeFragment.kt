package uz.abdurashidov.covid19.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import uz.abdurashidov.covid19.R
import uz.abdurashidov.covid19.databinding.FragmentHomeBinding
import uz.abdurashidov.covid19.models.PrevModel
import uz.abdurashidov.covid19.models.covidnewsmodel.Article
import uz.abdurashidov.covid19.network.NewsViewModel
import uz.abdurashidov.covid19.ui.adapter.ArticleAdapter
import uz.abdurashidov.covid19.ui.adapter.PreventationAdapter
import uz.abdurashidov.covid19.utils.Status
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<PrevModel>
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var preventationAdapter: PreventationAdapter
    private lateinit var articleList: ArrayList<Article>
    private val networkViewModel: NetworkViewModel by viewModels()
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = ArrayList()
        articleList = ArrayList()
        loadData()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        launch(Dispatchers.Main) {
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

        //News
        launch(Dispatchers.Main) {
            newsViewModel.getData().collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            val list = ArrayList<Article>()
                            list.addAll(it.data.articles)
                            articleAdapter.list = list
                            articleAdapter.notifyDataSetChanged()
                        }
                        Log.d("@homeFragment", "onCreateView: ${it.data?.articles}")
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

        binding.menu.setOnClickListener {
            binding.drawer.open()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preventationAdapter = PreventationAdapter(list)
        binding.preventionRv.adapter = preventationAdapter

        articleAdapter = ArticleAdapter(articleList, object : ArticleAdapter.RvItemClick{
            override fun onClick(article: Article) {
                TODO("Not yet implemented")
            }
        })
        binding.newsRv.adapter = articleAdapter

        binding.articleRv.adapter = articleAdapter

    }


    override val coroutineContext: CoroutineContext
        get() = Job()


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