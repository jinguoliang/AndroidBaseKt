package com.empty.jinux.baselibaray.view.about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import com.empty.jinux.baselibaray.R
import com.empty.jinux.baselibaray.view.recycleview.Item
import com.empty.jinux.baselibaray.view.recycleview.withItems
import kotlinx.android.synthetic.main.activity_abs_about.*


abstract class AbsAboutActivity : AppCompatActivity() {

    protected abstract fun onCreateHeader(icon: ImageView, slogan: TextView, version: TextView)
    protected abstract fun onItemsCreated(items: MutableList<Item>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_about)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = ""

        main_textview_title.setText(R.string.app_name)
        startAlphaAnimation(main_textview_title, 0, View.INVISIBLE)

        appBar.addOnOffsetChangedListener { appBarLayout, offset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = Math.abs(offset).toFloat() / maxScroll.toFloat()

            handleToolbarTitleVisibility(percentage)
        }

        onCreateHeader(appIcon, appSlogan, appVersion)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.withItems {
            onItemsCreated(this)
        }
    }

    private var mIsTheTitleVisible = false


    private fun handleToolbarTitleVisibility(percentage: Float) {
        if (percentage >= 1) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(main_textview_title, ALPHA_ANIMATIONS_DURATION, View.VISIBLE)
                mIsTheTitleVisible = true
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(main_textview_title, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE)
                mIsTheTitleVisible = false
            }
        }
    }

    private fun startAlphaAnimation(v: View, duration: Long, visibility: Int) {
        val alphaAnimation = if (visibility == View.VISIBLE)
            AlphaAnimation(0f, 1f)
        else
            AlphaAnimation(1f, 0f)

        alphaAnimation.duration = duration
        alphaAnimation.fillAfter = true
        v.startAnimation(alphaAnimation)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val ALPHA_ANIMATIONS_DURATION = 200L

    }


}