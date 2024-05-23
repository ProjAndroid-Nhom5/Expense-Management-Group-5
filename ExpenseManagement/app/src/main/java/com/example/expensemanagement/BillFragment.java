package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;


public class BillFragment extends Fragment {

    MaterialCardView cardview_1,cardview_2,cardview_3,cardview_4,cardview_5,cardview_6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Xu ly code
        cardview_1 = view.findViewById(R.id.cardview_1);
        cardview_2 = view.findViewById(R.id.cardview_2);
        cardview_3 = view.findViewById(R.id.cardview_3);
        cardview_4 = view.findViewById(R.id.cardview_4);
        cardview_5 = view.findViewById(R.id.cardview_5);
        cardview_6 = view.findViewById(R.id.cardview_6);

        cardview_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",1);
                startActivity(intent);
            }
        });

        cardview_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",2);
                startActivity(intent);
            }
        });

        cardview_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",3);
                startActivity(intent);
            }
        });

        cardview_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",4);
                startActivity(intent);
            }
        });
        cardview_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",5);
                startActivity(intent);
            }
        });
        cardview_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillInformation.class);
                intent.putExtra("potision",6);
                startActivity(intent);
            }
        });
    }
}