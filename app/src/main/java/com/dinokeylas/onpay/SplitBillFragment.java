package com.dinokeylas.onpay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dinokeylas.onpay.util.Preferences;

public class SplitBillFragment extends Fragment {

    public SplitBillFragment() {
        // Required empty public constructor
    }

    public static SplitBillFragment newInstance(String param1, String param2) {
        return new SplitBillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_split_bill, container, false);

        Button okSplit = view.findViewById(R.id.btn_ok_split);
        TextView tvNominal = view.findViewById(R.id.tv_nominal);

        Preferences preferences = new Preferences();
        int nominal = preferences.getNominalPreferences(this.getContext());
        tvNominal.setText(String.valueOf(nominal));

        okSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SplitBillActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
