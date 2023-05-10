package uz.abdurashidov.covid19.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abdurashidov.covid19.databinding.PrevRvItemBinding
import uz.abdurashidov.covid19.models.covidnewsmodel.Article

class ArticleAdapter(val list: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.Vh>() {

    inner class Vh(val prevRvItemBinding: PrevRvItemBinding) :
        RecyclerView.ViewHolder(prevRvItemBinding.root) {
        fun onBind(article: Article) {
            Picasso.get().load(article.urlToImage).into(prevRvItemBinding.image)
            prevRvItemBinding.title.text = article.title
            prevRvItemBinding.info.text = article.description.subSequence(0, 30).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(PrevRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}