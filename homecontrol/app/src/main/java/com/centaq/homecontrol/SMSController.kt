package com.centaq.homecontrol

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat

public final class SMSController() {
    companion object {
        @JvmStatic
        fun sendSMS(context: Context, activity: Activity?, cmd : String) {
            if (activity == null) {
                Toast.makeText(context, "Not able to execute without activity", Toast.LENGTH_LONG)
            }
            if (ActivityCompat.checkSelfPermission(context,
                    android.Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {

                if (activity != null) {
                    ActivityCompat.requestPermissions(activity,
                        arrayOf(android.Manifest.permission.SEND_SMS), 1)
                }
            } else {
                val smsManager = context.getSystemService(SmsManager::class.java)
                smsManager.sendTextMessage(
                    context.resources.getString(R.string.alarm_sms_number),
                    null,
                    "*$cmd*",
                    null,
                    null
                )
            }
        }
    }
}