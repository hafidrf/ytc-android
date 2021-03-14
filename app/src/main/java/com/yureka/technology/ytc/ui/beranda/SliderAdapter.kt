package com.yureka.technology.ytc.ui.beranda

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.viewpager.widget.PagerAdapter
import com.yureka.technology.ytc.R
import com.yureka.technology.ytc.ui.beranda.materi.MateriActivity
import com.yureka.technology.ytc.ui.beranda.model.SliderModel
import kotlinx.android.synthetic.main.card_item_home.view.*

class SliderAdapter(val context: Context, val mSliderModel: ArrayList<SliderModel>, val navController: NavController) :
    PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mSliderModel.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item_home, container, false)

        val model = mSliderModel[position]

        view.ll_home_week.setImageResource(model.image_week)
//        if (position == 0 || position == 1 || position == 2 || position == 3) {
//            if (position == 0) {
//                view.tv_title_home_week.setText(mSliderModel.get(0).title_week)
//            } else if (position == 1) {
//                view.tv_title_home_week.setText("Practice 2")
//            }else if (position == 2) {
//                view.tv_title_home_week.setText("Practice 3")
//            } else if (position == 3) {
//                view.tv_title_home_week.setText("Practice 4")
//            }
//        }

        view.btn_home_week.setOnClickListener {
            var intent = Intent()
//            if (position == 0 || position == 1 || position == 2 || position == 3) {
//                if (position == ) {
//                    intent = Intent(context, MateriActivity::class.java)
//                } else if (position == 1) {
//                    intent = Intent(context, Practice2Activity::class.java)
//                }else if (position == 2) {
//                    intent = Intent(context, Practice3Activity::class.java)
//                } else if (position == 3) {
//                    intent = Intent(context, PracticeActivity::class.java)
//                }
            if(position <= 4){
//                intent = Intent(context, MateriActivity::class.java)
//                startActivity(context, intent, null )
                navController.navigate(R.id.materiActivity)
            }
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

