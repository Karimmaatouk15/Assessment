package com.app.assessment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.assessment.Fragments.BaseFragment
import com.app.assessment.Fragments.OnBoardingFragment
import com.app.assessment.Fragments.SignUpFragment

class MainActivity : AppCompatActivity(), OnBoardingFragment.OnFragmentInteractionListener,
    SignUpFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToOnBoarding()
    }

    enum class Mode(val type: Int) {
        OnBoarding(0),
        SignUp(1)
    }

    private fun loadFragment(fragment: Fragment, tag: String, isAddToBackStack: Boolean) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)

        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }

        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val currentFragment = getCurrentFragment()
        if (currentFragment != null) {
            val isBackpressHandled = (currentFragment as BaseFragment).onBackPressed()
            if (!isBackpressHandled) {
                sendApptoBackground()
            }
        }
    }

    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.fragments.firstOrNull { it != null && it.isVisible }
    }

    private fun sendApptoBackground() {
        val background = Intent()
        background.action = Intent.ACTION_MAIN
        background.addCategory(Intent.CATEGORY_HOME)
        startActivity(background)
    }

    override fun onSignUpClick() {
        loadFragment(SignUpFragment.newInstance(), SignUpFragment.tag, false)
    }

    override fun goToOnBoarding() {
        loadFragment(OnBoardingFragment.newInstance(), OnBoardingFragment.tag, false)
    }
}