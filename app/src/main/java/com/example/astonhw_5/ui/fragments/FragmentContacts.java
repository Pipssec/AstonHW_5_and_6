package com.example.astonhw_5.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.astonhw_5.R;
import com.example.astonhw_5.model.ContactPhone;

import java.util.ArrayList;

public class FragmentContacts extends Fragment {
    ArrayList<ContactPhone> listContacts = new ArrayList<ContactPhone>();
    Boolean isInit = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            setInitialData();
            isInit = true;
        }
        RecyclerView recyclerView = view.findViewById(R.id.list);
        ContactsAdapter.OnContactClickListener onContactClickListener = new ContactsAdapter.OnContactClickListener(){
            @Override
            public void onContactClick(ContactPhone contact, int position) {
                FragmentItem contactFragment = FragmentItem.newInstance(contact.getNumber(),
                        contact.getFirstName(), contact.getLastName() );
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, contactFragment)
                        .addToBackStack("contacts")
                        .commit();
            }
        };
        ContactsAdapter adapter = new ContactsAdapter(requireActivity(), listContacts, onContactClickListener);
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){

        listContacts.add(new ContactPhone ("375291111111", "Человек", "Паук"));
        listContacts.add(new ContactPhone ("375291111112", "Робот", "Коп"));
        listContacts.add(new ContactPhone ("375291111113", "Человек", "Железный"));
        listContacts.add(new ContactPhone ("375291111114", "Пятый", "Элемент"));
        listContacts.add(new ContactPhone ("375291111115", "Омерзительная", "Восьмерка"));
    }

}
