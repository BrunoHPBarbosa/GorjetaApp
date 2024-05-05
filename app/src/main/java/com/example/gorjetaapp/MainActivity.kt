package com.example.gorjetaapp

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.gorjetaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCliqueAqui.setOnClickListener {
            val bottomSheetDialogFragment = DielogCalculo()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }

        val circles = listOf(
            CircleData(R.drawable.centercircle, "€3",false),
            CircleData(R.drawable.centercircle, "€1", false),
            CircleData(R.drawable.centercircle, "€2",false),
            CircleData(R.drawable.centercircle, "€4",false),
            CircleData(R.drawable.centercircle, "€5",false),
            CircleData(R.drawable.centercircle, "€6",false),
            CircleData(R.drawable.centercircle, "€7",false)

        )
val rect = listOf(
    CircleData(R.drawable.at1,"Carlos",false),
    CircleData(R.drawable.at2,"Roberto",false),
    CircleData(R.drawable.at3,"Vania",false),
    CircleData(R.drawable.at2,"Eri",false),
    CircleData(R.drawable.at1,"Bruno",false)
)
        binding.vpAtendente.adapter = AdapterRect(this,rect)


        binding.vpCircle.adapter = CircleAdapter(this,circles)

        setupCarousel()
        val initialPosition = circles.size / 2

        setupCarousel2()
        val initialPosition2 = rect.size/2
        binding.vpAtendente.setCurrentItem(initialPosition2,true)

// Definir o item inicial no ViewPager2
        binding.vpCircle.setCurrentItem(initialPosition, true)
    }

    private fun setupCarousel() {
        binding.vpCircle.offscreenPageLimit = 1

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
        binding.vpCircle.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.vpCircle.addItemDecoration(itemDecoration)


    }
    private fun setupCarousel2() {
        binding.vpAtendente.offscreenPageLimit = 1

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
          binding.vpAtendente.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,R.dimen.viewpager_current_item_horizontal_margin2
        )
       binding.vpAtendente.addItemDecoration(itemDecoration)
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