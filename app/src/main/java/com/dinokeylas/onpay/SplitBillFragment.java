package com.dinokeylas.onpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_split_bill, container, false);
        return view;
    }
}
