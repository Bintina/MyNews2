package com.bintina.mynews.notifications.controller

import android.content.Intent
import android.os.Bundle

/**
 * Interface defining a listener for notifications click events.
 */
interface OnNotificationsClickedListener {
    /**
     * Called when the user clicks on the notifications, triggering a relevant action.
     */
    fun onNotificationsClick()


}