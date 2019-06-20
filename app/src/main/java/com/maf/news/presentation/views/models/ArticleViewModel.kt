package com.maf.news.presentation.views.models

/**
 * Data class to hold all view details epoxy models gonna need
 * */
data class ArticleViewModel(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val date: String
)