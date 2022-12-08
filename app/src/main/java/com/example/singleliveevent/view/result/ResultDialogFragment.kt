package com.example.singleliveevent.view.result

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.singleliveevent.R
import com.example.singleliveevent.databinding.FragmentDialogResultBinding
import com.example.singleliveevent.util.navigate

class ResultDialogFragment : DialogFragment(R.layout.fragment_dialog_result) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentDialogResultBinding.bind(view)
        val args: ResultDialogFragmentArgs by navArgs()
        binding.dialogTextView.apply {
            text = "$text ${args.result}"
        }

        binding.dialogButton.setOnClickListener {
            navigate(ResultDialogFragmentDirections.actionResultFragmentDialogToFirstFragment())
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onDismiss(dialog: DialogInterface) {
        navigate(ResultDialogFragmentDirections.actionResultFragmentDialogToFirstFragment())
    }
}