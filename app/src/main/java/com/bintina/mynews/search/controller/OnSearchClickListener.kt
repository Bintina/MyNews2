package com.bintina.mynews.search.controller

import android.text.Editable
import android.view.View
import android.widget.CheckBox
import java.util.Date

interface OnSearchClickListener {

    fun getQuery(typedQuery: Editable): String

    fun getSearchStartDate(startDate: Editable)
    fun getSearchEndDate(endDate: Editable)

    fun boxChecked(view: CheckBox): Boolean

    fun openResults()



}