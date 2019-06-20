package com.maf.news.presentation.views

import android.content.Context
import android.widget.ProgressBar
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EpoxyProgressBar(context: Context) : ProgressBar(context)