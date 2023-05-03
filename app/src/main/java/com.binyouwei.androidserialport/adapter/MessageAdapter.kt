package com.binyouwei.androidserialport.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder

/**
 * @author binjx
 * @date 2023/4/27 16:58
 * @purpose：
 **/
class MessageAdapter : BaseQuickAdapter<String, QuickViewHolder>() {

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): QuickViewHolder {
        return QuickViewHolder(android.R.layout.simple_selectable_list_item, parent)
    }

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: String?) {
        holder.getView<TextView>(android.R.id.text1).text = item
    }

}