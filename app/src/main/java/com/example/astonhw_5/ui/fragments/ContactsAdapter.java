package com.example.astonhw_5.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.astonhw_5.R;
import com.example.astonhw_5.model.ContactPhone;

import java.util.List;

public class ContactsAdapter  extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    interface OnContactClickListener{
        void onContactClick(ContactPhone contact, int position);
    }

    private final OnContactClickListener onClickListener;

    private final LayoutInflater inflater;

    private final List<ContactPhone> contacts;

    public ContactsAdapter(Context context, List<ContactPhone> contacts, OnContactClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactPhone contact = contacts.get(position);
        holder.numberView.setText(contact.getNumber());
        holder.nameFirstView.setText(contact.getFirstName());
        holder.nameLastView.setText(contact.getLastName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onContactClick(contact, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameFirstView, nameLastView, numberView;
        ViewHolder(View view){
            super(view);
            nameFirstView = view.findViewById(R.id.firstName);
            nameLastView = view.findViewById(R.id.lastName);
            numberView = view.findViewById(R.id.number);
        }
    }
}