package com.psutools.reminder.base.delegates

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.psutools.reminder.R

//абстрактный базовый класс для делегатов который упрощает создание делегатов для работы с RecyclerView
abstract class BaseItemAdapterDelegate<I : BaseListItem, VH : RecyclerView.ViewHolder> : AbsListItemAdapterDelegate<I, BaseListItem, VH>() {
    //метод определяет, может ли делегат обрабатывать конкретный элемент
    override fun isForViewType(item: BaseListItem, items: MutableList<BaseListItem>,
                               position: Int): Boolean {
        return isForViewType(item)
    }
    //метод привязывает данные к ViewHolder
    override fun onBindViewHolder(item: I, holder: VH, payloads: MutableList<Any>) {
        holder.itemView.setTag(R.id.base_item_tag_key_id, item)
        onBind(item, holder, payloads)
    }
    //метод возвращает элемент связанный с ViewHolder
    fun getItemForViewHolder(holder: VH): I = getItemFromItemView(holder.itemView)

    //метод возвращает элемент сохраненный в tag корневой view
    fun getItemFromItemView(itemView: View): I {
        return itemView.getTag(R.id.base_item_tag_key_id) as? I
            ?: throw IllegalStateException("Item is null or has a wrong type")
    }

    abstract fun isForViewType(item: BaseListItem): Boolean

    abstract fun onBind(item: I, holder: VH, payloads: List<Any>)
}
