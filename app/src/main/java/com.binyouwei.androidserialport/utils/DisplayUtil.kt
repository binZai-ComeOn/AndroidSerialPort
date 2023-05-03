package com.binyouwei.androidserialport.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * 屏幕单位转换
 */
object DisplayUtil {

    /**
     * 获取屏幕的高度
     */
    fun getWindowHeight(activity: Activity): Int{
        val dm : DisplayMetrics = DisplayMetrics();
        activity.windowManager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels;
    }

    /**
     * 获取屏幕的宽度
     */
    fun getWindowWidth(activity: Activity): Int{
        val dm : DisplayMetrics = DisplayMetrics();
        activity.windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels;
    }

    /**
     * dp单位转换成px单位
     */
    fun dpToPx(context: Context, dp : Float): Int{
        val scale = context.resources.displayMetrics.density;
        return (dp * scale + 0.5f).toInt();
    }

    /**
     * dp单位转换为px单位
     */
    fun dpToPx(context: Context,dp : Int): Int{
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),context.resources.displayMetrics
        ).toInt();
    }

    /**
     * px单位转换为dip单位
     */
    fun pxToDip(context: Context,px : Float): Int{
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt();
    }

    /**
     * dip单位转换为px单位
     */
    fun dipToPx(context: Context,dip : Float): Int{
        val scale =context.resources.displayMetrics.density
        return (dip * scale + 0.5f).toInt();
    }

    /**
     * sp单位转换为px单位
     */
    fun spToPx(context: Context,sp : Float): Int{
        val scale = context.resources.displayMetrics.scaledDensity
        return (sp * scale + 0.5f).toInt();
    }

    /**
     * px单位转换为sp单位
     */
    fun pxToSp(context: Context,px : Float): Int{
        val scale = context.resources.displayMetrics.scaledDensity
        return (px / scale + 0.5f).toInt();
    }
}
