package com.tvacstudio.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MyFragment extends Fragment {

    private static final String TAG = "MyFragment";

    private ConstraintLayout mCustomBottomSheet;

    private LockableBottomSheetBehavior mBottomSheetBehavior;

    private LinearLayout mHeaderLayout;

    private Button button;


    public MyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.custom_bottom_sheet, container, false);

        mCustomBottomSheet = view.findViewById(R.id.custom_bottom_sheet);

        mBottomSheetBehavior = (LockableBottomSheetBehavior) BottomSheetBehavior.from(mCustomBottomSheet);

        mHeaderLayout = view.findViewById(R.id.header_layout);

        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextFragment next = new NextFragment();

                getParentFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_right, R.anim.slide_left)
                        .add(R.id.bad_place, next)
                        .commit();

            }
        });

        mHeaderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }

            }
        });

        mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    if (mBottomSheetBehavior instanceof LockableBottomSheetBehavior) {
                        mBottomSheetBehavior.setLocked(true);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void openBottom() {

        if(mBottomSheetBehavior != null && mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            if(mBottomSheetBehavior != null) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }
    }

    public void backPress() {

        if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }
}
