package com.ari.coins.ui.views.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ari.coins.databinding.DialogGenericBinding
import com.ari.coins.ui.uiModels.DialogData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Ari Valencia
 * @file GenericBottomSheet
 * @description Generic Dialog for display any view with DialogData object
 */

class GenericBottomSheet(
    private var dialogData: DialogData,
    private var onClickBack: (() -> Unit)?,
    private var onClickOk: (() -> Unit)?
) : BottomSheetDialogFragment() {

    private var _binding: DialogGenericBinding? = null
    private val binding: DialogGenericBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogGenericBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogData = dialogData
        isCancelable = dialogData.isCancellable

        binding.btnOk.setOnClickListener {
            dismiss()
            onClickOk?.invoke()
        }

        binding.btnBack.setOnClickListener {
            dismiss()
            onClickBack?.invoke()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
