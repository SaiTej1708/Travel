package com.tummoc.travel.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tummoc.travel.R
import com.tummoc.travel.databinding.FragmentRouteDialogBinding

class RouteDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_route_dialog, container, false)
    }

    private lateinit var binding: FragmentRouteDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = FragmentRouteDialogBinding.inflate(LayoutInflater.from(requireContext()),null,false)


        val dialog = AlertDialog.Builder(requireActivity()).setView(binding.root).setNegativeButton("Close",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })

        return dialog.show()
    }



    companion object {

    }
}