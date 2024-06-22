package com.example.expensemanagement.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expensemanagement.R;
import com.example.expensemanagement.Store.Store_Detail;
import com.google.firebase.auth.FirebaseAuth;

public class AdminFragment extends Fragment {

    private CardView information;

    private CardView information_store;

    private  CardView change_password;

    private  CardView ins_and_regu;

    private  CardView log_out;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Xu ly code o day
        information = view.findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Admin_Detail.class);
                startActivity(intent);
            }
        });

        information_store = view.findViewById(R.id.information_store);
        information_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Store_Detail.class);
                startActivity(intent);
            }
        });

        change_password = view.findViewById(R.id.change_password);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Admin_Change_password.class);
                startActivity(intent);
            }
        });

        ins_and_regu = view.findViewById(R.id.ins_and_regu);

        ins_and_regu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Instructions_and_regulations.class);
                startActivity(intent);
            }
        });

        log_out = view.findViewById(R.id.log_out);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}