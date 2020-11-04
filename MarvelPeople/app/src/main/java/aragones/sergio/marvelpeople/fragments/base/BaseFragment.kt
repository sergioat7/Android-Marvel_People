/*
 * Copyright (c) 2020 Sergio Aragonés. All rights reserved.
 * Created by Sergio Aragonés on 3/11/2020
 */

package aragones.sergio.marvelpeople.fragments.base

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import aragones.sergio.marvelpeople.fragments.popups.PopupErrorDialogFragment
import aragones.sergio.marvelpeople.models.ErrorResponse
import java.io.Serializable

open class BaseFragment: Fragment() {

    fun manageError(errorResponse: ErrorResponse) {

        val error = StringBuilder()
        if (errorResponse.message.isNotEmpty()) {
            error.append(errorResponse.message)
        } else {
            error.append(resources.getString(errorResponse.errorKey))
        }
        showPopupDialog(error.toString())
    }

    fun showPopupDialog(message: String) {

        val ft: FragmentTransaction = activity?.supportFragmentManager?.beginTransaction() ?: return
        val prev = activity?.supportFragmentManager?.findFragmentByTag("popupDialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val dialogFragment = PopupErrorDialogFragment(message)
        dialogFragment.show(ft, "popupDialog")
    }

    fun <T> launchActivityWithExtras(activity: Class<T>, params: Map<String, Serializable>) {

        val intent = Intent(context, activity).apply {}
        for (param in params) {
            intent.putExtra(param.key, param.value)
        }
        startActivity(intent)
    }
}