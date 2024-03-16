package com.rkm.shortsstories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rkm.shortsstories.databinding.FragmentHomeBinding
import com.rkm.shortsstories.models.homedata
import com.rkm.shortsstories.repository.MainRepo
import com.rkm.shortsstories.repository.myresponse
import com.rkm.shortsstories.viewmodel.MainViewModel
import com.rkm.shortsstories.viewmodel.MainViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class homeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    var list= arrayListOf<homedata>()
    private lateinit var viewModel: MainViewModel
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

        val repo = MainRepo(requireActivity())
        viewModel= ViewModelProvider(requireActivity(), MainViewModelFactory(repo))[MainViewModel::class.java]

        viewModel.getHomeData()

        var homerecylceradapter= homerecylceradapter(list,requireContext(),findNavController())
        viewModel.homeLiveData.observe(requireActivity(), Observer {

            when(it){
                is myresponse.Error -> {}
                is myresponse.Loading -> {}
                is myresponse.Success -> {

                    list.clear()
                    val tempList = it.data
                    tempList?.forEach {
                        list.add(it)
                    }

                    homerecylceradapter.notifyDataSetChanged()
                }
            }
        })


        binding.HomeRecycler.adapter=homerecylceradapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}