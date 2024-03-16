package com.rkm.shortsstories

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.rkm.shortsstories.databinding.ActivityBookdescriptionBinding
import com.rkm.shortsstories.databinding.LayoutProgressBinding
import com.rkm.shortsstories.models.bookmodel
import com.rkm.shortsstories.repository.myresponse
import com.rkm.shortsstories.repository.repo
import com.rkm.shortsstories.utils.networkcheck
import com.rkm.shortsstories.viewmodel.BookViewModel
import com.rkm.shortsstories.viewmodel.BookViewModelFactory

class bookdescription : AppCompatActivity() {

    private lateinit var binding: ActivityBookdescriptionBinding
    val activity = this
    private val repo=repo(activity)
    private val viewModel by lazy {
        ViewModelProvider(activity,BookViewModelFactory(repo))[BookViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookdescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val homedata = intent.getSerializableExtra("bookdata") as bookmodel

        binding.apply {
            Glide.with(this@bookdescription).load(homedata.image).thumbnail(0.5f).into(mBookImage)
            mBookTitle.text=homedata.name
            mBookDesc.text=homedata.description
            mAuthorName.text=homedata.speak
        }

        binding.mReadBookBtn.setOnClickListener {


            viewModel.downloadFile(homedata.bookpdflink,"${homedata.name}.pdf")
            val dialogBinding = LayoutProgressBinding.inflate(layoutInflater)
            val dialog= Dialog(activity).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
                this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                this.window!!.setLayout( ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            }

            viewModel.downloadLiveData.observe(activity){
                when(it){
                    is myresponse.Error -> dialog.dismiss()
                    is myresponse.Loading -> {
                        dialogBinding.mProgress.text = "${it.progress}%"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            dialogBinding.mProgressBar.setProgress(it.progress, true)
                        } else {
                            dialogBinding.mProgressBar.progress = it.progress

                        }
                        dialog.show()
                    }

                    is myresponse.Success -> {
                        dialog.dismiss()

                        val intent= Intent(this,pdfscreen::class.java)
                        intent.putExtra("book.pdf", it.data?.filePath)
                        startActivity(intent)
                    }

                }
            }
        }


    }



}