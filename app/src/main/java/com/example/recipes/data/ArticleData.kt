package com.example.recipes.data

import com.example.recipes.R
import com.example.recipes.model.ArticlesModel

object ArticleData {
    val articlesData = listOf(
        ArticlesModel(
            name = R.string.healthy_eating_tips_name,
            img1 = R.drawable.healthy_eating_tips_img,
            desc = R.string.healthy_eating_tips_desc,
            content = R.string.healthy_eating_tips_content
        ),

        ArticlesModel(
            name = R.string.meal_prep_guide_name,
            img1 = R.drawable.meal_prep_guide_img,
            desc = R.string.meal_prep_guide_desc,
            content = R.string.meal_prep_guide_content
        ),

        ArticlesModel(
            name = R.string.vegan_diet_benefits_name,
            img1 = R.drawable.vegan_diet_benefits_img,
            desc = R.string.vegan_diet_benefits_desc,
            content = R.string.vegan_diet_benefits_content
        ),

        ArticlesModel(
            name = R.string.quick_snack_ideas_name,
            img1 = R.drawable.quick_snack_ideas_img,
            desc = R.string.quick_snack_ideas_desc,
            content = R.string.quick_snack_ideas_content
        ),

        ArticlesModel(
            name = R.string.cooking_with_spices_name,
            img1 = R.drawable.cooking_with_spices_img,
            desc = R.string.cooking_with_spices_desc,
            content = R.string.cooking_with_spices_content
        )
    )
    fun getArticles(): List<ArticlesModel> = articlesData

}