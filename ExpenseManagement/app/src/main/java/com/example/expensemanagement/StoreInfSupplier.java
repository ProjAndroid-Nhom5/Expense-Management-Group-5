package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreInfSupplier#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreInfSupplier extends Fragment {
    ListStoreSupplierAdapter listAdapter;
    ArrayList<ListStoreSupplierData> dataArrayList = new ArrayList<>();
    ListStoreSupplierData listData;
    ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreInfSupplier() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreInfSupplier.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreInfSupplier newInstance(String param1, String param2) {
        StoreInfSupplier fragment = new StoreInfSupplier();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_inf_supplier, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listview);

        String[] SupplierID = {"0", "1", "2", "3"};
        String[] SupplierName = {"Pasta", "Maggi", "Pasta", "Maggi"};
        String[] StoreName = {"0", "0", "0", "0"};

        ArrayList<ListStoreSupplierData> dataArrayList = new ArrayList<>();
        ListStoreSupplierData listData;

        for (int i = 0; i < SupplierID.length; i++) {
            listData = new ListStoreSupplierData(
                    SupplierID[i],
                    SupplierName[i],
                    StoreName[i]
            );
            dataArrayList.add(listData);
        }

        listAdapter = new ListStoreSupplierAdapter(getActivity(), dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListStoreSupplierData selectedItem = (ListStoreSupplierData) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), StoreDetailSupplier.class);
                intent.putExtra("supplierName", selectedItem.getSupplierName());
                intent.putExtra("storeName", selectedItem.getStoreName());
                startActivity(intent);
            }
        });

        // Chuyển đến trang AddEcommerce khi nhấn vào add_button
        ImageView addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreAddSupplier.class);
                startActivity(intent);
            }
        });

        ListView listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), StoreDetailSupplier.class);
                startActivity(intent);
            }
        });
    }
}