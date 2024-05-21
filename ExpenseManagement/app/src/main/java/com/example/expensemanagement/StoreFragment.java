package com.example.expensemanagement;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StoreFragment extends Fragment {

    MaterialCardView cardview_1, cardview_2, cardview_3, cardview_4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Xu ly code
        cardview_1 = view.findViewById(R.id.cardview_1);

        cardview_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreInformation.class);
                intent.putExtra("position", 1);
                startActivity(intent);
            }
        });

        cardview_2 = view.findViewById(R.id.cardview_2);

        cardview_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreInformation.class);
                intent.putExtra("position", 2);
                startActivity(intent);
            }
        });

        cardview_3 = view.findViewById(R.id.cardview_3);

        cardview_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreInformation.class);
                intent.putExtra("position", 3);
                startActivity(intent);
            }
        });

        cardview_4 = view.findViewById(R.id.cardview_4);

        cardview_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreInformation.class);
                intent.putExtra("position", 4);
                startActivity(intent);
            }
        });
    }
}