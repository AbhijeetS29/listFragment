package com.abhijeet.newproject

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.ListFragment
import com.abhijeet.newproject.databinding.DialogBinding
import com.abhijeet.newproject.databinding.FragmentArraylistfragmentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Arraylistfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Arraylistfragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentArraylistfragmentBinding
    var arraylist:ArrayList<String> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             binding=FragmentArraylistfragmentBinding.inflate(layoutInflater)

        var adapter =ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,arraylist)
       arraylist.add("hello")
       arraylist.add("hey")
       arraylist.add("bye")
       arraylist.add("bye")

        binding.lwitem.adapter=adapter

        binding.fabbutton.setOnClickListener {
            var dialogBinding=DialogBinding.inflate(layoutInflater)
            var dialog =Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout( WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
             dialog.setCancelable(false)

            dialogBinding.btnok.setOnClickListener {
                if (dialogBinding.etadd.text.toString().isNullOrEmpty()){
                    dialogBinding.etadd.error="Enter a Name"
                } else{
                    arraylist.add(dialogBinding.etadd.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding.lwitem.setOnItemClickListener { adapterView, view,  position, l ->
           var dialogBinding =DialogBinding.inflate(layoutInflater)
            var dialog = Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout( WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
             dialog.setCancelable(false)
            dialogBinding.tvadd.setText("Update Item")
            dialogBinding.btnok.setText("Update Item")
            dialogBinding.etadd.setText(arraylist[position])
            dialogBinding.btnok.setOnClickListener {
                if (dialogBinding.etadd.text.toString().isNullOrEmpty()){
                    dialogBinding.tvadd.error="Enter an item"
                }else{
                    arraylist.removeAt(position)
                    arraylist.add(position,dialogBinding.etadd.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }


        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Arraylistfragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Arraylistfragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}