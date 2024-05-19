package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreInfEcommerce#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreInfEcommerce extends Fragment {

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

        // Chuyển đến trang StoreDetail khi nhấn vào CardView
        CardView ecommerceCardView1 = view.findViewById(R.id.ecommerce1);
        ecommerceCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                startActivity(intent);
            }
        });

        CardView ecommerceCardView2 = view.findViewById(R.id.ecommerce2);
        ecommerceCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                startActivity(intent);
            }
        });

        CardView ecommerceCardView3 = view.findViewById(R.id.ecommerce3);
        ecommerceCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                startActivity(intent);
            }
        });

        CardView ecommerceCardView4 = view.findViewById(R.id.ecommerce4);
        ecommerceCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                startActivity(intent);
            }
        });

        CardView ecommerceCardView5 = view.findViewById(R.id.ecommerce5);
        ecommerceCardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreDetail.class);
                startActivity(intent);
            }
        });

        // Chuyển đến trang AddEcommerce khi nhấn vào add_button
        ImageView addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreAdd.class);
                startActivity(intent);
            }
        });
    }
}