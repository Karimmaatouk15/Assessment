package com.app.assessment.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.app.assessment.R
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnBoardingFragment : BaseFragment() {
    private var loginButtonClickMode = 0
    private var listener: OnFragmentInteractionListener? = null

    companion object {
        val tag = OnBoardingFragment::class.java.simpleName ?: String()

        @JvmStatic
        fun newInstance() = OnBoardingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login?.apply {
            setOnClickListener {
                if (loginButtonClickMode == 0) {
                    setBackgroundResource(R.drawable.colar_cardview_border_color)
                    loginButtonClickMode++
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            }
        }

        btn_signup?.setOnClickListener {
            listener?.onSignUpClick()
        }
    }


    override fun onBackPressed(): Boolean {
        if (loginButtonClickMode != 0) {
            context?.let {
                btn_login.setBackgroundResource(R.drawable.white_cardview_border_color)
                loginButtonClickMode = 0
                btn_login.setTextColor(ContextCompat.getColor(it, R.color.colar))
                return true
            }
            return false
        } else {
            return false
        }
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
        fun onSignUpClick()
    }
}