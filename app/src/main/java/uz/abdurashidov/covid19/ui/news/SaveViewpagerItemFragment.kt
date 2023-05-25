package uz.abdurashidov.covid19.ui.news

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
import uz.abdurashidov.covid19.databinding.FragmentSaveViewpagerItemBinding
import uz.abdurashidov.covid19.models.covidnewsmodel.Article
import uz.abdurashidov.covid19.models.covidnewsmodel.Source
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
            ArticleAdapter(list,object : ArticleAdapter.RvItemClick{
                override fun onClick(article: Article) {
                    TODO("Not yet implemented")
                }
            })
        binding.myRv.adapter = articleAdapter
        launch(Dispatchers.Main) {
            databaseViewModel.getAllUsers().collectLatest {
                if (it.data!=null){
                    it.data.forEach { article->
                        list.add(
                            Article(
                                author = article.author,
                                content = article.content,
                                description = article.description,
                                publishedAt = article.publishedAt,
                                source = Source(id = "1", name = "name"),
                                title = article.title,
                                url = article.url,
                                urlToImage = article.urlToImage
                            )
                        )
                    }
                }
                list.addAll(list)
                Log.d("@saveViewpagerFragment", "onCreateView: $list")
                articleAdapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}