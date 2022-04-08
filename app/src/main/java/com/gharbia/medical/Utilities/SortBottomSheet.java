package com.gharbia.medical.Utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;

import com.gharbia.medical.Interfaces.SheetClick;
import com.gharbia.medical.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SortBottomSheet extends BottomSheetDialogFragment {
    Activity activity;
    int line;
    SheetClick sheetClick;

    public SortBottomSheet(Activity activity,int line,SheetClick sheetClick) {
        this.activity = activity;
        this.line = line;
        this.sheetClick = sheetClick;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(activity, R.layout.bottom_sheet_sort, null);
        dialog.setContentView(contentView);

        dialog.setOnShowListener(dialog1 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog1;

            FrameLayout bottomSheet = (FrameLayout) d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        });


        AppCompatImageView name = dialog.findViewById(R.id.name_radio);
        AppCompatImageView height_to_low = dialog.findViewById(R.id.high_to_low_radio);
        AppCompatImageView low_to_height = dialog.findViewById(R.id.low_to_hight_radio);
        AppCompatImageView degree = dialog.findViewById(R.id.degree_radio);

        RelativeLayout name_lay = dialog.findViewById(R.id.line_name);
        RelativeLayout height_to_low_lay = dialog.findViewById(R.id.line_hight_to_low);
        RelativeLayout low_to_height_lay = dialog.findViewById(R.id.line_low_to_high);
        RelativeLayout degree_lay = dialog.findViewById(R.id.line_degree);

        if (line==1)
            name.setImageResource(R.drawable.radio_full);
        else if (line==2)
            height_to_low.setImageResource(R.drawable.radio_full);
        else if (line==3)
            low_to_height.setImageResource(R.drawable.radio_full);
        else if (line==4)
            degree.setImageResource(R.drawable.radio_full);


        name_lay.setOnClickListener(view -> sheetClick.sheetClick(1));
        height_to_low_lay.setOnClickListener(view -> sheetClick.sheetClick(2));
        low_to_height_lay.setOnClickListener(view -> sheetClick.sheetClick(3));
        degree_lay.setOnClickListener(view -> sheetClick.sheetClick(4));

    }
}
