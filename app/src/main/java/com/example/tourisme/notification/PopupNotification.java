package com.example.tourisme.notification;

import android.app.ProgressDialog;
import android.content.Context;

public class PopupNotification {

    private ProgressDialog progressDialog;
    private Context context;

    public PopupNotification(Context context) {
        this.context = context;
    }

    public void showLoadingPopup() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Chargement en cours...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideLoadingPopup() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
