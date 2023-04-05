package com.example.astonhw_5.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.astonhw_5.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentItem extends Fragment {
    ArrayList<String> itemsInfo = new ArrayList<>();


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";


    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;


    public static FragmentItem newInstance(String param1,
                                           String param2,
                                           String param3,
                                           String param4,
                                           String param5) {
        FragmentItem fragment = new FragmentItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editContact();
    }

    public void editContact() {
        Button btnEdit = getView().findViewById(R.id.btnEdit);
        TextView firstName = getView().findViewById(R.id.firstNameItem);
        TextView lastName = getView().findViewById(R.id.lastNameItem);
        TextView numberPhone = getView().findViewById(R.id.numberItem);
        TextView iD = getView().findViewById(R.id.itemId);
        ImageView ivItem = getView().findViewById(R.id.ivItem);
        Picasso.Builder builder = new Picasso.Builder(requireContext());
        builder.build().load(mParam5).into(ivItem);
        firstName.setText(mParam2);
        lastName.setText(mParam3);
        numberPhone.setText(mParam1);
        iD.setText(mParam4);
        EditText edFirstName = getView().findViewById(R.id.edFirstName);
        EditText edLastName = getView().findViewById(R.id.edLastName);
        EditText edPhoneNumber = getView().findViewById(R.id.edPhoneNumber);
        Button btnSave = getView().findViewById(R.id.btnSave);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setVisibility(View.GONE);
                lastName.setVisibility(View.GONE);
                numberPhone.setVisibility(View.GONE);
                iD.setVisibility(View.GONE);
                ivItem.setVisibility(View.GONE);
                btnEdit.setVisibility(View.GONE);
                edFirstName.setVisibility(View.VISIBLE);
                edLastName.setVisibility(View.VISIBLE);
                edPhoneNumber.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                edFirstName.setText(mParam2);
                edLastName.setText(mParam3);
                edPhoneNumber.setText(mParam1);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsInfo.add(edPhoneNumber.getText().toString());
                itemsInfo.add(edFirstName.getText().toString());
                itemsInfo.add(edLastName.getText().toString());
                itemsInfo.add(mParam4);
                Bundle result = new Bundle();
                result.putStringArrayList("bundleKey", itemsInfo);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });
    }
}