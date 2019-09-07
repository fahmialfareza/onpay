package com.dinokeylas.onpay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PayForMeFragment extends Fragment {

    public PayForMeFragment() {
        // Required empty public constructor
    }

    public static PayForMeFragment newInstance(String param1, String param2) {
        return new PayForMeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_for_me, container, false);
        return view;
    }
}
