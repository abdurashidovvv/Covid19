package uz.abdurashidov.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abdurashidov.covid19.databinding.NewsRvItemBinding
import uz.abdurashidov.covid19.databinding.PrevRvItemBinding
import uz.abdurashidov.covid19.models.covidnewsmodel.Article

class ArticleAdapter(var list: ArrayList<Article>, val rvItemClick: RvItemClick) : RecyclerView.Adapter<ArticleAdapter.Vh>() {

    inner class Vh(val prevRvItemBinding: NewsRvItemBinding) :
        RecyclerView.ViewHolder(prevRvItemBinding.root) {
        fun onBind(article: Article) {
            Picasso.get().load(article.urlToImage).into(prevRvItemBinding.image)
            prevRvItemBinding.title.text = article.title
            prevRvItemBinding.info.text = article.description.subSequence(0, 30).toString()

            prevRvItemBinding.root.setOnLongClickListener {
                rvItemClick.onClick(article)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(NewsRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface RvItemClick{
        fun onClick(article: Article)
    }
}