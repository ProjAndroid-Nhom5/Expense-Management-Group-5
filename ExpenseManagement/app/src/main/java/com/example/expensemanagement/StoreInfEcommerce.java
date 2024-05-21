package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreInfEcommerce#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreInfEcommerce extends Fragment {
    ListStoreEcommerceAdapter listAdapter;
    ArrayList<ListStoreEcommerceData> dataArrayList = new ArrayList<>();
    ListStoreEcommerceData listData;
    ListView listView;

    private ConstraintLayout itemEcommerce;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreInfEcommerce() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreInfEcommerce.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreInfEcommerce newInstance(String param1, String param2) {
        StoreInfEcommerce fragment = new StoreInfEcommerce();
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_inf_ecommerce, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listview);

        Integer[] EcommerceID = {0, 1, 2, 3};
        String[] nameEcommerce = {"Pasta", "Maggi", "Pasta", "Maggi"};
        String[] nameStore = {"Lazada", "Shopee", "Amazon", "Tiki"};
        Integer[] PaymentFee = {0, 0 ,0, 0};
        Integer[] FixedFee = {0, 0 ,0, 0};
        Integer[] ServiceFee = {0, 0 ,0, 0};
        Float[] FreightSurcharge = {0.0f, 0.0f ,0.0f, 0.0f};
        Float[] PersonalIncomeTaxVAT = {0.0f, 0.0f ,0.0f, 0.0f};
        Integer[] Receivables = {0, 0 ,0, 0};

        ArrayList<ListStoreEcommerceData> dataArrayList = new ArrayList<>();
        ListStoreEcommerceData listData;

        for (int i = 0; i < EcommerceID.length; i++) {
            listData = new ListStoreEcommerceData(
                    EcommerceID[i],
                    PaymentFee[i],
                    FixedFee[i],
                    ServiceFee[i],
                    Receivables[i],
                    nameStore[i],
                    nameEcommerce[i],
                    FreightSurcharge[i],
                    PersonalIncomeTaxVAT[i]
            );
            dataArrayList.add(listData);
        }

        listAdapter = new ListStoreEcommerceAdapter(getActivity(), dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListStoreEcommerceData selectedItem = (ListStoreEcommerceData) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), StoreDetailEcommerce.class);
                intent.putExtra("ecommerceName", selectedItem.getEcommerceName());
                intent.putExtra("storeName", selectedItem.getNameStore());
                startActivity(intent);
            }
        });

        // Chuyển đến trang AddEcommerce khi nhấn vào add_button
        ImageView addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreAddEcommerce.class);
                startActivity(intent);
            }
        });

        ListView listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), StoreDetailEcommerce.class);
                startActivity(intent);
            }
        });
    }
}