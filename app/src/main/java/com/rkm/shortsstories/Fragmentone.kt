package com.rkm.shortsstories

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rkm.shortsstories.databinding.FragmentFragmentoneBinding
import java.util.logging.Handler

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragmentone.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmentone : Fragment() {

    private lateinit var binding: FragmentFragmentoneBinding
    private lateinit var handler: Handler
    private var index = 0

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf(Fragmenttwo(), Fragmentthree(), Fragmentfour())
        val onboardViewPagerAdapter =
            onboardViewPagerAdapter(list, parentFragmentManager, lifecycle)
        binding.viewPager.adapter = onboardViewPagerAdapter

        val sharedPref = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val springDotsIndicator = binding.springDotsIndicator
        val viewPager = binding.viewPager
        springDotsIndicator.attachTo(viewPager)

        binding.materialButton.setOnClickListener {

            if (index == list.size - 2) {
                binding.viewPager.currentItem = ++index
                binding.materialButton.text = "Finish"
            } else if (index == list.size - 1) {

                if (sharedPref != null) {
                    sharedPref.edit().putString("onboard", "done").apply()
                }



                findNavController().navigate(R.id.action_fragmentone_to_homeFragment)
            } else {
                binding.viewPager.currentItem = ++index
            }

        }}

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment

            binding= FragmentFragmentoneBinding.inflate(layoutInflater,container,false)
            return  binding.root
        }

        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment Fragmentone.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                Fragmentone().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
    }

