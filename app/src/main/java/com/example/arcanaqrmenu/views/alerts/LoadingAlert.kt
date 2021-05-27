package com.example.arcanaqrmenu.views.alerts

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.example.arcanaqrmenu.R

class LoadingAlert(private val activity: Activity) {
    private lateinit var alertDialog: AlertDialog

    fun start() {
        val builder = AlertDialog.Builder(activity)
        builder.setView(activity.layoutInflater.inflate(R.layout.loading_alert, null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun stop() = alertDialog.dismiss()
}