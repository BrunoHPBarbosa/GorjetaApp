package com.example.gorjetaapp

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: CircleAdapter
    private lateinit var viewpagerS:ViewPager2
    private lateinit var adapter2 : AdapterRect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tips)

        val circles = listOf(
            CircleData(R.drawable.centercircle, "€3"),
            CircleData(R.drawable.centercircle, "€1"),
            CircleData(R.drawable.centercircle, "€2"),
            CircleData(R.drawable.centercircle, "€4"),
            CircleData(R.drawable.centercircle, "€5"),
            CircleData(R.drawable.centercircle, "€6"),
            CircleData(R.drawable.centercircle, "€7")

        )
val rect = listOf(
    CircleData(R.drawable.at1,"Carlos"),
    CircleData(R.drawable.at2,"Roberto"),
    CircleData(R.drawable.at3,"Vania"),
    CircleData(R.drawable.at2,"Eri"),
    CircleData(R.drawable.at1,"Bruno")
)
        viewpagerS = findViewById(R.id.vp_atendente)
        adapter2 = AdapterRect(this,rect)

        viewPager = findViewById(R.id.vp_circle)
        adapter = CircleAdapter(this, circles)

        viewpagerS.adapter = adapter2

        viewPager.adapter = adapter

        setupCarousel()
        val initialPosition = circles.size / 2

        setupCarousel2()
        val initialPosition2 = rect.size/2
         viewpagerS.setCurrentItem(initialPosition2,true)

// Definir o item inicial no ViewPager2
        viewPager.setCurrentItem(initialPosition, true)
    }

    private fun setupCarousel() {
        viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        val pageTransformer =
            ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.35f * kotlin.math.abs(position))
                page.scaleX = 1 - (0.35f * kotlin.math.abs(position))
                page.alpha = 0.20f + (1 - kotlin.math.abs(position))
            }
        viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)


    }
    private fun setupCarousel2() {
        viewpagerS.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible2)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin2)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        val pageTransformer =
            ViewPager2.PageTransformer{page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1- (0.35f * kotlin.math.abs(position))
                page.scaleX = 1- (0.35f * kotlin.math.abs(position))
                page.alpha = 0.20f +(1 - kotlin.math.abs(position))
            }
          viewpagerS.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,R.dimen.viewpager_current_item_horizontal_margin2
        )
        viewpagerS.addItemDecoration(itemDecoration)
    }

    class HorizontalMarginItemDecoration(
        context: Context,
        horizontalMarginInPx: Int
    ) : RecyclerView.ItemDecoration() {

        private val horizontalMarginInDp: Int =
            context.resources.getDimension(horizontalMarginInPx).toInt()

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right = horizontalMarginInDp
            outRect.left = horizontalMarginInDp
        }
    }
}