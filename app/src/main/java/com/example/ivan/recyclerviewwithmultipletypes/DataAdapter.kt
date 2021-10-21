package com.example.ivan.recyclerviewwithmultipletypes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DataAdapter : RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>() {

    private val adapterData = mutableListOf<DataModel>()

    //--------onCreateViewHolder: inflate layout with view holder-------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {

        val layout = when (viewType) {
            TYPE_FAMILY -> R.layout.item_family
            TYPE_FRIEND -> R.layout.item_friend
            TYPE_COLLEAGUE -> R.layout.item_colleague
            TYPE_HEADER -> R.layout.item_header
            else -> throw IllegalArgumentException("Invalid view type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return DataAdapterViewHolder(view)
    }


    //-----------onBindViewHolder: bind view with data model---------
    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.Family -> TYPE_FAMILY
            is DataModel.Friend -> TYPE_FRIEND
            is DataModel.Colleague -> TYPE_COLLEAGUE
            else -> TYPE_HEADER
        }
    }

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    companion object {
        private const val TYPE_FAMILY = 0
        private const val TYPE_FRIEND = 1
        private const val TYPE_COLLEAGUE = 2
        private const val TYPE_HEADER = 3
    }

    class DataAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private fun bindFamily(item: DataModel.Family) {
            //Do your view assignment here from the data model

            itemView.findViewById<AppCompatTextView>(R.id.tvName)?.text = item.name
            itemView.findViewById<AppCompatTextView>(R.id.tvRelationship)?.text = item.relationship
        }

        private fun bindFriend(item: DataModel.Friend) {
            //Do your view assignment here from the data model
            itemView.findViewById<AppCompatTextView>(R.id.tvName)?.text = item.name
            itemView.findViewById<AppCompatTextView>(R.id.tvGender)?.text = item.gender
        }

        private fun bindColleague(item: DataModel.Colleague) {
            //Do your view assignment here from the data model
            itemView.findViewById<AppCompatTextView>(R.id.tvName)?.text = item.name
            itemView.findViewById<AppCompatTextView>(R.id.tvOrganization)?.text = item.organization
            itemView.findViewById<AppCompatTextView>(R.id.tvDesignation)?.text = item.designation
        }

        private fun bindHeader(item: DataModel.Header) {
            //Do your view assignment here from the data model
            itemView.findViewById<ConstraintLayout>(R.id.clRoot)?.setBackgroundColor(item.bgColor)
            itemView.findViewById<AppCompatTextView>(R.id.tvNameLabel)?.text = item.title
        }

        fun bind(dataModel: DataModel) {
            when (dataModel) {
                is DataModel.Family -> bindFamily(dataModel)
                is DataModel.Friend -> bindFriend(dataModel)
                is DataModel.Colleague -> bindColleague(dataModel)
                is DataModel.Header -> bindHeader(dataModel)
            }
        }
    }
}