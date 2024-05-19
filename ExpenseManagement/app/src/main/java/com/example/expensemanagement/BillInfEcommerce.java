package com.example.expensemanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillInfEcommerce#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillInfEcommerce extends Fragment {
    ListBillEcommerceAdapter listAdapter;
    ArrayList<ListBillEcommerceData> dataArrayList = new ArrayList<>();
    ListBillEcommerceData listData;
    ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillInfEcommerce() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillInfEcommerce.
     */
    // TODO: Rename and change types and number of parameters
    public static BillInfEcommerce newInstance(String param1, String param2) {
        BillInfEcommerce fragment = new BillInfEcommerce();
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
        return inflater.inflate(R.layout.fragment_bill_inf_ecommerce, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listview);

        String[] id = {"Pasta", "Maggi"};
        String[] nameEcommerce = {"Pasta", "Maggi"};
        String[] date = {"30 mins", "2 mins"};
        String[] nameCustomer = {"30 mins", "2 mins"};
        String[] phone = {"Pasta", "Maggi"};
        String[] address = {"30 mins", "2 mins"};
        String[] paymentTime = {"Pasta", "Maggi"};
        Float[] productCost = {2323.3f,2323.3f};
        Float[] shipCost = {2323.3f,2323.3f};
        Float[] totalPayment = {2323.3f,2323.3f};
        for (int i = 0; i < id.length; i++){
            listData = new ListBillEcommerceData(id[i],
                    nameEcommerce[i],
                    date[i],
                    nameCustomer[i],
                    phone[i],
                    address[i],
                    paymentTime[i],
                    productCost[i],
                    shipCost[i],
                    totalPayment[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListBillEcommerceAdapter(getActivity(), dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
    }
}