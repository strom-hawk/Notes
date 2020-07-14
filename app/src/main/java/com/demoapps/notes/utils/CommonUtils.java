package com.demoapps.notes.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.demoapps.notes.R;

public class CommonUtils {

    public static void showAlertDialog(Context context, String dialogTitle, String dialogMessage, String buttonText) {
        if (dialogMessage.equalsIgnoreCase(ApplicationConstants.EMPTY_STRING)) {
            dialogMessage = context.getResources().getString(R.string.something_went_wrong);
        }

        final Dialog dialog = CommonUtils.showDialog(context, R.layout.alert_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageView = dialog.findViewById(R.id.dialog_image);
        TextView title = dialog.findViewById(R.id.dialog_title);
        TextView subTitle = dialog.findViewById(R.id.dialog_message);
        Button okButton = dialog.findViewById(R.id.ok_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);
        if (dialogTitle.equalsIgnoreCase(context.getResources().getString(R.string.error_message))) {
            imageView.setImageResource(R.drawable.error);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setImageResource(R.drawable.success);
            imageView.setVisibility(View.VISIBLE);
        }


        title.setText(dialogTitle);
        subTitle.setText(dialogMessage);
        okButton.setText(buttonText);
        cancelButton.setVisibility(View.GONE);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (null != context && !((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public static void showAlertDialogWithFinishActivity(final Activity activity, String dialogTitle, String dialogMessage, String buttonText, boolean showImage) {
        if (dialogMessage.equalsIgnoreCase(ApplicationConstants.EMPTY_STRING)) {
            dialogMessage = activity.getResources().getString(R.string.something_went_wrong);
        }

        final Dialog dialog = CommonUtils.showDialog(activity, R.layout.alert_dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageView = dialog.findViewById(R.id.dialog_image);
        TextView title = dialog.findViewById(R.id.dialog_title);
        TextView subTitle = dialog.findViewById(R.id.dialog_message);
        Button okButton = dialog.findViewById(R.id.ok_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);

        if (showImage) {
            imageView.setImageResource(R.drawable.success);
            imageView.setVisibility(View.VISIBLE);
        }

        if (dialogTitle.equalsIgnoreCase(ApplicationConstants.HIDE_DIALOG_TITLE)) {
            title.setVisibility(View.GONE);
        }


        title.setText(dialogTitle);
        subTitle.setText(dialogMessage);
        okButton.setText(buttonText);
        cancelButton.setVisibility(View.GONE);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.finish();
            }
        });

        if (null != activity && !activity.isFinishing()) {
            dialog.show();
        }
    }

    public static Dialog showDialog(Context context, int layout) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(layout);

        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(width - 200, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        return dialog;
    }
}
