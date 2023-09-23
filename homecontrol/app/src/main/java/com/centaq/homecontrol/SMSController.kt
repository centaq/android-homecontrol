package com.centaq.homecontrol

import android.content.Context
import android.telephony.SmsManager

public final class SMSController() {
    companion object {
        @JvmStatic
        fun sendSMS(context: Context, cmd : String) {
            val smsManager = context.getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(
                context.resources.getString(R.string.alarm_sms_number),
                null,
                cmd,
                null,
                null)

        }
    }
}