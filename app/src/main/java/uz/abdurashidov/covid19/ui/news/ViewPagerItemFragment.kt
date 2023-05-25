package uz.abdurashidov.covid19.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.covid19.databinding.FragmentViewPagerItemBinding
import uz.abdurashidov.covid19.models.covidnewsmodel.Article
import uz.abdurashidov.covid19.ui.adapter.ArticleAdapter
import uz.abdurashidov.covid19.viewmodel.DatabaseViewModel
import uz.abdurashidov.covid19.viewmodel.NetworkViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ViewPagerItemFragment : Fragment(), CoroutineScope, ArticleAdapter.RvItemClick {
    private val binding by lazy { FragmentViewPagerItemBinding.inflate(layoutInflater) }
    private val networkViewModel: NetworkViewModel by viewModels()
    private val databaseViewModel:DatabaseViewModel by viewModels()
    private lateinit var list: ArrayList<Article>
    private lateinit var articleAdapter: ArticleAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        list = ArrayList()
        articleAdapter = ArticleAdapter(list, this)
        binding.myProgress.visibility = View.VISIBLE
        launch(Dispatchers.Main) {
            networkViewModel.getAllNews().collectLatest {
                list.addAll(it.data?.articles ?: emptyList())
                Log.d("@ViewpagerItemFragment", "onCreateView: $list")
                articleAdapter.notifyDataSetChanged()
                binding.myRv.adapter = articleAdapter
                binding.myProgress.visibility = View.GONE
            }

        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()

    override fun onClick(article: Article) {

        val saveArticle=uz.abdurashidov.covid19.database.entity.Article(
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage
        )
        databaseViewModel.addArticle(saveArticle)
        Toast.makeText(context, "Save.!", Toast.LENGTH_SHORT).show()
    }
}