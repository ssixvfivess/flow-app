package com.psutools.reminder.ui.presentation.details.adapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemDetailsRouteLineBinding
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.ui.presentation.details.adapter.delegate.ItemDetailsABDelegate.ViewHolder

class ItemDetailsABDelegate : BaseItemAdapterDelegate<ItemDetailsAB, ItemDetailsABDelegate.ViewHolder>() {
    override fun isForViewType(item: BaseListItem): Boolean = item is ItemDetailsAB

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemDetailsRouteLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: ItemDetailsAB, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            pointA.text = item.nameA
            pointAAddress.text = item.addressA
            timeOfRoute.text = item.displayRouteTime
            pointB.text = item.nameB
            pointBAddress.text = item.addressB
            timeForPointA.text = item.startTime
            timeForPointB.text = item.endTime

            holder.updateTransportIconsVisibility(item.transportType)
        }
    }

    class ViewHolder(
        val viewBinding: ItemDetailsRouteLineBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun updateTransportIconsVisibility(transportTypes: List<TransportData>) {
            hideAllTransportIcons()
            transportTypes.forEach { transportType ->
                val transportView = mapTransportToView(transportType)
                transportView?.visibility = View.VISIBLE
            }
        }

        private fun hideAllTransportIcons() {
            with(viewBinding) {
                car.visibility = View.GONE
                taxi.visibility = View.GONE
                walk.visibility = View.GONE
                bicycle.visibility = View.GONE
                scooter.visibility = View.GONE
                publicTransport.visibility = View.GONE
            }
        }

        private fun mapTransportToView(transportType: TransportData): View? {
            return when (transportType) {
                TransportData.CAR -> viewBinding.car
                TransportData.TAXI -> viewBinding.taxi
                TransportData.WALK -> viewBinding.walk
                TransportData.BICYCLE -> viewBinding.bicycle
                TransportData.SCOOTER -> viewBinding.scooter
                TransportData.PUBLIC_TRANSPORT -> viewBinding.publicTransport
                TransportData.UNKNOWN -> viewBinding.walk
            }
        }
    }
}