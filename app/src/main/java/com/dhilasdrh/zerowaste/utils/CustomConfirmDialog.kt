package com.dhilasdrh.zerowaste.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.DialogConfirmBinding

class CustomConfirmDialog(context: Context?, private val title: String, private val message: String, private val isCancelable: Boolean = true, private var btnPositiveText: String ="Yes", private var btnNegativeText: String = "No", private val yesAction: () -> Unit): Dialog(
    context!!
){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_confirm)

        setCanceledOnTouchOutside(isCancelable)

        val binding = DialogConfirmBinding.inflate(LayoutInflater.from(context))

        binding.tvTitle.text = title
        binding.tvMessage.text = message
        binding.btnNo.text = btnNegativeText
        binding.btnYes.text = btnPositiveText
        binding.btnNo.setOnClickListener { dismiss() }
        binding.btnYes.setOnClickListener {
            yesAction()
            dismiss()
        }
    }
}