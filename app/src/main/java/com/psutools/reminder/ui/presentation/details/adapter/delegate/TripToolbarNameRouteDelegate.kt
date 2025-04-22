//package com.psutools.reminder.ui.presentation.details.adapter.delegate
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
//import com.psutools.reminder.base.delegates.BaseListItem
//import com.psutools.reminder.databinding.CustomDetailsToolbarBinding
//import com.psutools.reminder.databinding.ItemWayNBinding
//import com.psutools.reminder.ui.presentation.details.adapter.delegate.TripToolbarNameRouteDelegate.ViewHolder
//
//
//class TripToolbarNameRouteDelegate: BaseItemAdapterDelegate<TripToolbarNameRoute, ViewHolder>() {
//    override fun isForViewType(item: BaseListItem): Boolean = item is PointN
//
//    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
//        val viewBinding = CustomDetailsToolbarBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return ViewHolder(viewBinding)
//    }
//
//    override fun onBind(item: TripToolbarNameRoute, holder: ViewHolder, payloads: List<Any>) {
//        with(holder.viewBinding) {
//            titleText.text = item.routeName
//        }
//    }
//
//    class ViewHolder(
//        val viewBinding: CustomDetailsToolbarBinding
//    ) : RecyclerView.ViewHolder(viewBinding.root)
//}