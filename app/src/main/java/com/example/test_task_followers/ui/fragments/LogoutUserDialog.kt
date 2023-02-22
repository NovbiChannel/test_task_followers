package com.example.test_task_followers.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.test_task_followers.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LogoutUserDialog : DialogFragment() {

    private var yesListener:(() -> Unit)? = null

    fun setYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Выйти из аккаунта?")
            .setMessage("Вы действительно хотите выйти из аккаунта?")
            .setIcon(R.drawable.baseline_logout_24)
            .setPositiveButton("Да") { _, _ ->
                yesListener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton("Нет") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}