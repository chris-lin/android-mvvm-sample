package paging.android.example.com.pagingsample.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import paging.android.example.com.pagingsample.R

object FoodBindingAdapters {

    @BindingAdapter("app:selectorColor")
    @JvmStatic
    fun selectorColor(view: TextView, number: Int) {
        println("selectorColor number: $number")

        val color = when (number) {
            1 -> R.color.listItemBg
            else -> android.R.color.white
        }

        view.setBackgroundResource(color)

//        view.setImageDrawable(getDrawablePopularity(popularity, view.context))
//        val color = getAssociatedColor(popularity, view.context)
//        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color))
//        view.setImageDrawable(getDrawablePopularity(popularity, view.context))
//        view.setImageDrawable(getDrawablePopularity(popularity, view.context))
    }
}