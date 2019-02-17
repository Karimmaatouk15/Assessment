package com.app.assessment.Fragments

import android.content.Intent
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    open fun onBackPressed(): Boolean {
        return false
    }

    fun getNestedFragment(): Fragment? {
        return childFragmentManager.fragments.firstOrNull { it != null && it.isVisible }
    }



}