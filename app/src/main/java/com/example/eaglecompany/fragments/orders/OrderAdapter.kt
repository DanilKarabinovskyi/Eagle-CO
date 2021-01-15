package com.example.eaglecompany.fragments.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eaglecompany.databinding.OrderAfterLayoutBinding
import com.example.eaglecompany.databinding.OrderBeforeLayoutBinding
import com.example.eaglecompany.models.MData
import com.squareup.picasso.Picasso

private val ITEM_VIEW_BEFORE = 0
private val ITEM_VIEW_AFTER = 1

class NewOrderAdapter(val clickListener: OrdersListener):
        ListAdapter<MData, RecyclerView.ViewHolder>(DeadlinesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_BEFORE -> BeforeViewHolder.from(parent)
            ITEM_VIEW_AFTER -> AfterViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BeforeViewHolder -> {
                val nightItem = getItem(position)
                holder.bind(nightItem, clickListener)
            }
            is AfterViewHolder -> {
                val nightItem = getItem(position)
                holder.bind(nightItem, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).clicked) {
            false -> ITEM_VIEW_BEFORE
            true -> ITEM_VIEW_AFTER
        }
    }

    class BeforeViewHolder private constructor( val binding: OrderBeforeLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): BeforeViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = OrderBeforeLayoutBinding.inflate(layoutInflater, parent, false)

                return BeforeViewHolder(binding)
            }
        }


        fun bind(item: MData, clickListener: OrdersListener) {
            var daysWork = ""
            binding.mData = item
            binding.name.text = item.data.name
            binding.ratingPunctuality.rating = item.data.ratingPunctuality.toFloat()
            binding.ratingPunctualityDouble.text = item.data.ratingPunctuality.toString()
            binding.ratingSpeed.rating = item.data.ratingSpeed.toFloat()
            binding.ratingSpeedDouble.text = item.data.ratingSpeed.toString()
            binding.amountOrders.text = "${item.data.numberOfOrders} з ${item.data.numberOfOrdersComplete}"
            binding.timeWork.text = "${item.data.workSchedule.startTime} - ${item.data.workSchedule.endTime}"
            for(i in item.data.workSchedule.dayOfWeek){
                when (i) {
                    0 -> daysWork = "Пн"
                    1 -> daysWork = "$daysWork Вт"
                    2 -> daysWork = "$daysWork Ср"
                    3 -> daysWork = "$daysWork Чт"
                    4 -> daysWork = "$daysWork Пт"
                    5 -> daysWork = "$daysWork Сб"
                    6 -> daysWork = "$daysWork Нд"
                }
            }
            binding.daysWork.text = daysWork
            Picasso
                    .get()
                    .load(item.data.avatar)
                    .fit()
                    .centerInside()
                    .into(binding.profileImage)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    class AfterViewHolder private constructor( val binding: OrderAfterLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): AfterViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = OrderAfterLayoutBinding.inflate(layoutInflater, parent, false)

                return AfterViewHolder(binding)
            }
        }

        fun bind(item: MData, clickListener: OrdersListener) {
            var daysWork = ""
            binding.mData = item
            binding.name.text = item.data.name
            binding.ratingPunctuality.rating = item.data.ratingPunctuality.toFloat()
            binding.ratingPunctualityDouble.text = item.data.ratingPunctuality.toString()
            binding.ratingSpeed.rating = item.data.ratingSpeed.toFloat()
            binding.ratingSpeedDouble.text = item.data.ratingSpeed.toString()
            binding.amountOrders.text = "${item.data.numberOfOrdersComplete} з ${item.data.numberOfOrders}"
            binding.timeWork.text = "${item.data.workSchedule.startTime} - ${item.data.workSchedule.endTime}"
            for(i in item.data.workSchedule.dayOfWeek){
                when (i) {
                    0 -> daysWork = "Пн"
                    1 -> daysWork = "$daysWork Вт"
                    2 -> daysWork = "$daysWork Ср"
                    3 -> daysWork = "$daysWork Чт"
                    4 -> daysWork = "$daysWork Пт"
                    5 -> daysWork = "$daysWork Сб"
                    6 -> daysWork = "$daysWork Нд"
                }
            }
            binding.daysWork.text = daysWork
            binding.hourPayment.text = item.data.services[0].number.toString()
            binding.kilometerPayment.text = item.data.services[1].number.toString()
            Picasso
                    .get()
                    .load(item.data.avatar)
                    .fit()
                    .centerInside()
                    .into(binding.profileImage)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}
class DeadlinesDiffCallback: DiffUtil.ItemCallback<MData>(){
    override fun areItemsTheSame(oldItem: MData, newItem: MData): Boolean {
        return oldItem.data == newItem.data
    }

    override fun areContentsTheSame(oldItem: MData, newItem: MData): Boolean {
        return oldItem == newItem
    }

}

class OrdersListener(val clickListener: (mData: MData) -> Unit) {

    fun onClick(data: MData) = clickListener(data)

}