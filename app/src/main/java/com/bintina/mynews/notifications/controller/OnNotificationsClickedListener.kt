package com.bintina.mynews.notifications.controller

import android.content.Intent
import android.os.Bundle

interface OnNotificationsClickedListener {
    fun onNotificationsClick(intent: Intent)


}