package com.rkm.shortsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.rkm.shortsstories.databinding.ActivityBookcatogeryBinding
import com.rkm.shortsstories.models.bookmodel
import com.rkm.shortsstories.models.homedata
import com.rkm.shortsstories.repository.MainRepo
import com.rkm.shortsstories.utils.springscroll
import com.rkm.shortsstories.viewmodel.MainViewModel
import com.rkm.shortsstories.viewmodel.MainViewModelFactory

class bookcatogery : AppCompatActivity() {
    private lateinit var binding: ActivityBookcatogeryBinding

    val activity = this

    private val repo = MainRepo(this)
    private val viewModel by lazy {

        ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookcatogeryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var list = arrayListOf<bookmodel>()

        var categoryadapter = catogeryadapter(list, this, NavController(this))
        binding.mRvCategory.adapter = categoryadapter

        val homedata = intent.getSerializableExtra("bookdata") as ArrayList<bookmodel>
        homedata.forEach {
            list.add(it)

            categoryadapter.notifyDataSetChanged()






            springscroll().attachToRecyclerView(binding.mRvCategory)

        }


    }

    override fun onBackPressed() {
        finish()
        window.sharedElementEnterTransition = null
        window.sharedElementReenterTransition = null
        window.sharedElementReturnTransition = null
        window.sharedElementExitTransition = null

        super.onBackPressed()
    }

}