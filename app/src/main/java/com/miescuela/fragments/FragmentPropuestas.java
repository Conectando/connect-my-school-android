package com.miescuela.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.fabtransitionactivity.SheetLayout;
import com.miescuela.NuevaPropuestaActivity;
import com.miescuela.R;

public class FragmentPropuestas extends Fragment implements SheetLayout.OnFabAnimationEndListener {

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;
    private static final int REQUEST_CODE = 1;
    ImageView imgClose;
    LinearLayout linearProp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propuestas, container, false);

        mSheetLayout = (SheetLayout) view.findViewById(R.id.bottom_sheet);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        imgClose = (ImageView) view.findViewById(R.id.img_close);
        linearProp = (LinearLayout) view.findViewById(R.id.alert_porpuesta);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearProp.setVisibility(View.GONE);
            }
        });

        return view;
    }

    @Override
    public void onFabAnimationEnd() {

        Intent intent = new Intent(getActivity(), NuevaPropuestaActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            mSheetLayout.contractFab();
        }
    }
}