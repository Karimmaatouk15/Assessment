package com.app.assessment.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_signup.*
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.app.assessment.R
import android.telephony.PhoneNumberFormattingTextWatcher


class SignUpFragment : BaseFragment() {
    private var checkBoxMode = 0
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var builder: AlertDialog.Builder

    companion object {
        val tag = SignUpFragment::class.java.simpleName ?: String()

        @JvmStatic
        fun newInstance() = SignUpFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        builder = AlertDialog.Builder(context!!, android.R.style.Theme_Material_Dialog_Alert)
        terms_checkbox?.apply {
            setOnClickListener {
                if (checkBoxMode == 0) {
                    setImageDrawable(resources.getDrawable(R.drawable.checked_checkbox))
                    checkBoxMode = 1
                } else {
                    setImageDrawable(resources.getDrawable(R.drawable.unchecked_checkbox))
                    checkBoxMode = 0
                }
            }
        }

        editText_phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        login_text.setOnClickListener {
            onBackPressed()
        }

        back_button.setOnClickListener {
            onBackPressed()
        }
        btn_signup?.setOnClickListener {
            if (editText_userName.text.trim().length >= 3) {
                displayPopUpmessage(getString(R.string.well_done), true)
            } else {
                displayPopUpmessage(getString(R.string.username_must_contain), false)
            }
        }

        ArrayAdapter.createFromResource(
            context,
            R.array.countryCodes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            country_codes.adapter = adapter
        }


    }

    fun displayPopUpmessage(message: String, shouldGoToLoginScreen: Boolean) {
        builder.setMessage(message)
        if (shouldGoToLoginScreen) {
            builder
                .setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        listener?.goToOnBoarding()
                    }
                })

        } else {
            builder.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which -> })
        }

        builder.show()
    }

    override fun onBackPressed(): Boolean {
        listener?.goToOnBoarding()
        return true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToOnBoarding()
    }
}