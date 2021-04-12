package com.dhilasdrh.zerowaste.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.NonNull
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.databinding.DialogFormBinding
import com.dhilasdrh.zerowaste.databinding.FragmentTodoBinding

class FormDialog(context: Context?, private val title: String, private val formLayout: FragmentTodoBinding, private val saveAction: () -> Unit): Dialog(
    context!!
){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_form)

        val binding = DialogFormBinding.inflate(LayoutInflater.from(context))

        if (formLayout.root.parent != null) {
            (formLayout.root.parent as ViewGroup).removeView(formLayout.root)
        }

        binding.tvTitle.text = title
        binding.formContainer.addView(formLayout.root)
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnSave.setOnClickListener {
            saveAction()
            dismiss()
        }

    }

}