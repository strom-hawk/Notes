package com.demoapps.notes.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.demoapps.notes.R;

public class CommonUtils {

    public static void showAlertDialog(Context context, String dialogTitle, String dialogMessage, String buttonText){
        if(dialogMessage.equalsIgnoreCase(ApplicationConstants.EMPTY_STRING)){
            dialogMessage = context.getResources().getString(R.string.something_went_wrong);
        }

        final Dialog dialog = CommonUtils.showDialog(context, R.layout.alert_dialog_layout);
        TextView title = dialog.findViewById(R.id.dialog_title);
        TextView subTitle = dialog.findViewById(R.id.dialog_message);
        Button okButton = dialog.findViewById(R.id.ok_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);

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

        if(null != context && !((Activity)context).isFinishing()){
            dialog.show();
        }
    }

    public static void showAlertDialogWithFinishActivity(final Context context, String dialogTitle, String dialogMessage, String buttonText){
        if(dialogMessage.equalsIgnoreCase(ApplicationConstants.EMPTY_STRING)){
            dialogMessage = context.getResources().getString(R.string.something_went_wrong);
        }

        final Dialog dialog = CommonUtils.showDialog(context, R.layout.alert_dialog_layout);
        TextView title = dialog.findViewById(R.id.dialog_title);
        TextView subTitle = dialog.findViewById(R.id.dialog_message);
        Button okButton = dialog.findViewById(R.id.ok_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);

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

        if(null != context && !((Activity)context).isFinishing()){
            dialog.show();
        }
    }

    public static Dialog showDialog(Context context, int layout){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(layout);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        return dialog;
    }
}
