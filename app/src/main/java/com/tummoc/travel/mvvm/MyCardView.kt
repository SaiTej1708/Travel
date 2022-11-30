package com.tummoc.travel.mvvm

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.tummoc.travel.R
import com.tummoc.travel.travel.BusRouteDetails
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.Trail
import com.tummoc.travel.travel.TravelDataItem


class MyCardView(context: Context) {

    private val context : Context

    init {
        this.context = context
    }

    fun getBusCard(travelDataItem: TravelDataItem) : View {

        val routes : List<Route> = travelDataItem.routes

        if (routes.size == 4){
            val walk = routes[0]
            val busAndMetro = routes[1]
            val busOrMetro = routes[2]
            val walkAgain = routes[3]
            val totalDistance = walk.distance + busAndMetro.distance + (busAndMetro.trails?.last()?.distance
                ?: 0.0) + busOrMetro.distance + walkAgain.distance

            val layout = LinearLayout(context) ; layout.orientation = LinearLayout.VERTICAL ; val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(dpToPx(5)) ; layout.layoutParams = layoutParams

            val parent = LinearLayout(context) ; parent.orientation = LinearLayout.HORIZONTAL
            parent.weightSum = totalDistance.toFloat(); val params = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            parent.layoutParams = params

            val walkView = getIndicator(walk.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            val busView = getIndicator(busAndMetro.distance.toFloat(),R.drawable.bus,R.color.teal_200)
            val metroView = getIndicator((busAndMetro.trails?.last()?.distance
                ?: 0.0).toFloat(),R.drawable.metro, R.color.purple_200)
            val busAgain = getIndicator(busOrMetro.distance.toFloat(),R.drawable.bus, R.color.teal_200)
            val walk2View = getIndicator(walkAgain.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            parent.addView(walkView)
            parent.addView(busView)
            parent.addView(metroView)
            parent.addView(busAgain)
            parent.addView(walk2View)

            layout.addView(parent)
            val info = additionalInfo(travelDataItem)
            layout.addView(info)
            return layout
        }else {
            val walk = routes[0]
            val busOrMetro = routes[1]
            val walkAgain = routes[2]
            val totalDistance = walk.distance + busOrMetro.distance + walkAgain.distance

            val layout = LinearLayout(context) ; layout.orientation = LinearLayout.VERTICAL ; val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(dpToPx(5)) ; layout.layoutParams = layoutParams

            val parent = LinearLayout(context) ; parent.orientation = LinearLayout.HORIZONTAL
            parent.weightSum = totalDistance.toFloat(); val params = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            parent.layoutParams = params

            val walkView = getIndicator(walk.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            val busView = getIndicator(busOrMetro.distance.toFloat(),R.drawable.bus,R.color.teal_200)
            val walk2View = getIndicator(walkAgain.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            parent.addView(walkView)
            parent.addView(busView)
            parent.addView(walk2View)

            layout.addView(parent)
            val info = additionalInfo(travelDataItem)
            layout.addView(info)
            return layout
        }
    }

    private fun additionalInfo(travelDataItem: TravelDataItem) : View {

        val parent = LinearLayout(context) ; parent.orientation = LinearLayout.HORIZONTAL; parent.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        parent.weightSum = 100f

        val info1 = getTextInfo("Route",travelDataItem.routeTitle)
        val info2 = getTextInfo("Distance",travelDataItem.totalDistance.toString())
        val info3 = getTextInfo("Duration",travelDataItem.totalDuration.toString())
        val info4 = getTextInfo("Fare", travelDataItem.totalFare.toString())
        parent.addView(info1)
        parent.addView(info2)
        parent.addView(info3)
        parent.addView(info4)
        return parent
    }

    private fun getTextInfo(Heading: String, subHeading: String) : LinearLayout {
        val parent = LinearLayout(context) ; parent.orientation = LinearLayout.VERTICAL ; val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 25f
        parent.layoutParams = layoutParams

        val text = TextView(context) ; val textParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        text.layoutParams = textParams
        text.textSize = dpToPx(6).toFloat()
        text.text = Heading
        val text2 = TextView(context) ; val text2Params = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        text2.layoutParams = text2Params
        text2.textSize = dpToPx(5).toFloat()
        text2.text = subHeading
        parent.addView(text)
        parent.addView(text2)
        return parent
    }

    fun getMetroCard(travelDataItem: TravelDataItem) : View {

        val routes : List<Route> = travelDataItem.routes

        if (routes.size == 4) {
            val walk = routes[0]
            val busAndMetro = routes[1]
            val busOrMetro = routes[2]
            val walkAgain = routes[3]

            val totalDistance =
                walk.distance + busAndMetro.distance + (busAndMetro.trails?.last()?.distance
                    ?: 0.0) + (busOrMetro.trails?.last()?.distance ?: 0.0) + walkAgain.distance

            val layout = LinearLayout(context); layout.orientation = LinearLayout.VERTICAL;
            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(dpToPx(5)); layout.layoutParams = layoutParams

            val parent = LinearLayout(context); parent.orientation = LinearLayout.HORIZONTAL

            parent.weightSum = totalDistance.toFloat();
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            parent.layoutParams = params

            val walkView =
                getIndicator(walk.distance.toFloat(), R.drawable.walk, R.color.purple_700)
            val busView =
                getIndicator(busAndMetro.distance.toFloat(), R.drawable.bus, R.color.teal_200)
            val metro1 = getIndicator(
                (busAndMetro.trails?.last()?.distance
                    ?: 0.0).toFloat(), R.drawable.metro, R.color.purple_200
            )
            val metro2 = getIndicator(
                (busOrMetro.trails?.last()?.distance ?: 0.0).toFloat(), R.drawable.metro,
                R.color.purple_200
            )
            val walk2View =
                getIndicator(walkAgain.distance.toFloat(), R.drawable.walk, R.color.purple_700)
            parent.addView(walkView)
            parent.addView(busView)
            parent.addView(metro1)
            parent.addView(metro2)
            parent.addView(walk2View)

            layout.addView(parent)
            val info = additionalInfo(travelDataItem)
            layout.addView(info)

            return layout
        }else{
            val walk = routes[0]
            val busOrMetro = routes[1]
            val walkAgain = routes[2]

            val totalDistance = walk.distance + (busOrMetro.trails?.last()?.distance ?: 0.0) + walkAgain.distance

            val layout = LinearLayout(context) ; layout.orientation = LinearLayout.VERTICAL ; val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(dpToPx(5)) ; layout.layoutParams = layoutParams

            val parent = LinearLayout(context) ; parent.orientation = LinearLayout.HORIZONTAL

            parent.weightSum = totalDistance.toFloat(); val params = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
            parent.layoutParams = params

            val walkView = getIndicator(walk.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            val metro2 = getIndicator((busOrMetro.trails?.last()?.distance ?: 0.0).toFloat(),R.drawable.metro, R.color.purple_200)
            val walk2View = getIndicator(walkAgain.distance.toFloat(),R.drawable.walk, R.color.purple_700)
            parent.addView(walkView)
            parent.addView(metro2)
            parent.addView(walk2View)

            layout.addView(parent)
            val info = additionalInfo(travelDataItem)
            layout.addView(info)
            return layout
        }
    }


     private fun getIndicator(distance: Float, image: Int, backgroundColor: Int) : LinearLayout {
         val walkIndicator = LinearLayout(context) ; walkIndicator.orientation = LinearLayout.VERTICAL
         val walkParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,distance)
         walkIndicator.layoutParams = walkParams

         val indicator = LinearLayout(context) ; indicator.orientation = LinearLayout.HORIZONTAL
         indicator.background = ContextCompat.getDrawable(context,backgroundColor)
         val indicatorParams = LayoutParams(LayoutParams.MATCH_PARENT,dpToPx(10))
         indicator.layoutParams = indicatorParams
         val imageView = ImageView(context)
         val imageParams = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
         imageView.layoutParams = imageParams
         imageView.background = ContextCompat.getDrawable(context,R.color.white)
         imageView.setImageDrawable(ContextCompat.getDrawable(context,image))

         walkIndicator.addView(indicator)
         walkIndicator.addView(imageView)
         return walkIndicator
     }


    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }


}