package com.rkm.shortsstories


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController


import com.rkm.shortsstories.databinding.FragmentSplashscreenBinding
import kotlinx.coroutines.Runnable
import androidx.navigation.fragment.findNavController as findNavController1


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [splashscreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class splashscreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSplashscreenBinding
    private var mediaPlayer: MediaPlayer? = null
    private var mediaFlag:Boolean= false
    private var charIndex: Int = 0
    private var textToType = "अनसुनी कहानियाँ"


    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)







        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //function to create Typing Effect.

        if (mediaFlag) {
        startTypingEffect()}


    }

    val handler = Handler(Looper.getMainLooper())


    private fun startTypingEffect() {
        //function to create Typing Effect.

        val delay: Long = 350 // Adjust the delay between each character

        handler.postDelayed(object : Runnable {
            override fun run() {

                if (charIndex<=textToType.length){

                    if (charIndex <= textToType.length-2) {
                        //Initialize MediaPlayer with the click sound
                        mediaPlayer = MediaPlayer.create(context, R.raw.typwriter_sound)
                        mediaPlayer?.start()
                    }

                    binding.splashText.text=textToType.substring(0,charIndex)

                    charIndex++

                    handler.postDelayed(this,delay)


                } else {



                    handler.postDelayed(object : Runnable {


                        override fun run() {

                            val sharedPref = context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
                            val retrievedName = sharedPref?.getString("onboard", "notdone")
                            if(retrievedName=="notdone"){

                                findNavController1().navigate(R.id.action_splashscreen_to_fragmentone)

                            }

                            else{
                                findNavController1().navigate(R.id.action_splashscreen_to_homeFragment)
                            }



                        }
                    }, 1000)


                }

            }
        }, delay)



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        // Start typing effect when the fragment view is created

        binding = FragmentSplashscreenBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.also {
            if (it.isPlaying) {
                it.stop()
                it.release()
                mediaPlayer = null
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaFlag=true
        handler.removeCallbacksAndMessages(null)




    }

    override fun onResume() {
        super.onResume()
        startTypingEffect()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment splashscreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            splashscreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

            }
    }
}

