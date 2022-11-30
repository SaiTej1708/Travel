package com.tummoc.travel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.travel.R
import com.tummoc.travel.databinding.FragmentDetailBinding
import com.tummoc.travel.databinding.TransportCardBinding
import com.tummoc.travel.mvvm.DetailViewModel
import com.tummoc.travel.travel.Route


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private var route : String = "Bus"

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]

        binding.showDialog.setOnClickListener {
            val fragment = RouteDialogFragment()
            val manager = parentFragmentManager
            val transaction = manager.beginTransaction()
            transaction.add(fragment,"Dialog")
            transaction.commit()
        }

        return view
    }

    companion object {
         fun getInstance() : Fragment {
             return DetailFragment()
         }
    }

//    inner class DetailAdapter(routes: List<Route>) : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(){
//
//        var list : List<Route> = routes
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
//
//        }
//
//        override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
//
//        }
//
//        override fun getItemCount(): Int {
//
//        }
//        inner class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view){
//             var transportCardBinding = TransportCardBinding.bind(view)
//        }
//    }

}