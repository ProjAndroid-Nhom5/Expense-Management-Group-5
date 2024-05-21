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
 * Use the {@link StoreInfEmployee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreInfEmployee extends Fragment {
    ListStoreEmployeeAdapter listAdapter;
    ArrayList<ListStoreEmployeeData> dataArrayList = new ArrayList<>();
    ListStoreEmployeeData listData;
    ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreInfEmployee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreInfEmployee.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreInfEmployee newInstance(String param1, String param2) {
        StoreInfEmployee fragment = new StoreInfEmployee();
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
        return inflater.inflate(R.layout.fragment_store_inf_employee, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.listview);

        String[] EmployeeID = {"0", "0", "0", "0"};
        String[] EmployeeName = {"Pasta", "Maggi", "Pasta", "Maggi"};
        String[] StoreName = {"Lazada", "Shopee", "Amazon", "Tiki"};
        String[] Email = {"0", "0", "0", "0"};
        String[] Phone = {"0", "0", "0", "0"};
        String[] Address = {"0", "0", "0", "0"};
        String[] Position = {"0", "0", "0", "0"};
        String[] DayOfWork = {"0", "0", "0", "0"};
        Integer[] BaseSalary = {0, 0, 0, 0};
        Integer[] BonusSalary = {0, 0, 0, 0};

        ArrayList<ListStoreEmployeeData> dataArrayList = new ArrayList<>();

        for (int i = 0; i < EmployeeID.length; i++) {
            ListStoreEmployeeData listData = new ListStoreEmployeeData(
                    EmployeeID[i],
                    EmployeeName[i],
                    StoreName[i],
                    Email[i],
                    Phone[i],
                    Address[i],
                    Position[i],
                    DayOfWork[i],
                    BaseSalary[i],
                    BonusSalary[i]
            );
            dataArrayList.add(listData);
        }

        listAdapter = new ListStoreEmployeeAdapter(getActivity(), dataArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListStoreEmployeeData selectedItem = (ListStoreEmployeeData) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), StoreDetailEmployee.class);
                intent.putExtra("EmployeeName", selectedItem.getEmployeeName());
                intent.putExtra("storeName", selectedItem.getStoreName());
                startActivity(intent);
            }
        });

        // Chuyển đến trang AddEcommerce khi nhấn vào add_button
        ImageView addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreAddEmployee.class);
                startActivity(intent);
            }
        });
    }
}