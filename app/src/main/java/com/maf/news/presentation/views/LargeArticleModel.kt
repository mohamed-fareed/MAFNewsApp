package com.maf.news.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.maf.news.R
import com.maf.news.presentation.loadUrl
import com.maf.news.presentation.views.models.ArticleViewModel
import kotlinx.android.synthetic.main.layout_large_article.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LargeArticleModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var clickListener: OnClickListener? = null
        @CallbackProp set

    init {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_large_article, this, true)

        container_article.setOnClickListener {
            clickListener?.onClick(it)
        }
    }

    @ModelProp
    fun setArticleData(article: ArticleViewModel) {
        img_article.loadUrl(article.imageUrl)
        tv_article_title.text = article.title
        tv_article_date.text = article.date
    }
}