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

import com.example.astonhw_5.R;
import com.example.astonhw_5.model.ContactPhone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.astonhw_5.ui.fragments.ContactsAdapter;

public class FragmentContacts extends Fragment {
    Map<String, ContactPhone> mContacts = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createListContact();
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (key, bundle) -> {
            ArrayList<String> result = bundle.getStringArrayList("bundleKey");
            String id = result.get(3);
            mContacts.get(id).setFirstName(result.get(1));
            mContacts.get(id).setLastName(result.get(2));
            mContacts.get(id).setNumber(result.get(0));
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (Serializable) mContacts);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mContacts = (Map<String, ContactPhone>) savedInstanceState.getSerializable("list");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ContactPhone> listCont = new ArrayList<>(mContacts.values());
        Collections.sort(listCont);
        loadAdapter(view, listCont);

    }

    private void loadAdapter(View view, List<ContactPhone> listCont) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        ContactsAdapter.OnContactClickListener onContactClickListener = (contact, position) -> {
            FragmentItem contactFragment = FragmentItem.newInstance(contact.getNumber(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getId(),
                    contact.getPhoto());
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, contactFragment)
                    .addToBackStack("contacts")
                    .commit();
        };
        ContactsAdapter adapter = new ContactsAdapter(listCont, onContactClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void createListContact() {
        for (int i = 1, j = 120; i < j; i++) {
            mContacts.put(String.valueOf(i),
                    new ContactPhone("37529111111" + i,
                            "User" + i,
                            "Name" + i,
                            String.valueOf(i),
                            "https://picsum.photos/id/" + i + "/200/300"));

        }
    }
}
