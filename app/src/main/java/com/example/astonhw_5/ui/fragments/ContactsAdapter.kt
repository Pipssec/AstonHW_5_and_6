package com.example.astonhw_5.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonhw_5.R
import com.example.astonhw_5.model.ContactPhone
import com.squareup.picasso.Picasso


class ContactsAdapter(
    private val contacts: List<ContactPhone>,
    private val onClickListener: OnContactClickListener,
) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameFirstView: TextView
        val nameLastView: TextView
        val numberView: TextView
        val idView: TextView
        val imageViewList: ImageView

        init {
            nameFirstView = view.findViewById(R.id.firstName)
            nameLastView = view.findViewById(R.id.lastName)
            numberView = view.findViewById(R.id.number)
            idView = view.findViewById(R.id.tvIdContacts)
            imageViewList = view.findViewById(R.id.ImageViewContacts)
        }
    }
    interface OnContactClickListener {
        fun onContactClick(contact: ContactPhone?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val contact = contacts[position]
        holder.numberView.text = contact.number
        holder.nameFirstView.text = contact.firstName
        holder.nameLastView.text = contact.lastName
        holder.idView.text = contact.id
        holder.itemView.setOnClickListener { onClickListener.onContactClick(contact, position) }
        Picasso.with(holder.itemView.context)
            .load(contact.photo)
            .fit()
            .placeholder(R.drawable.baseline_contact_page_24)
            .error(R.drawable.baseline_contact_page_24)
            .into(holder.imageViewList)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

}