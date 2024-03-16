package com.rkm.shortsstories.viewmodel

import androidx.lifecycle.ViewModel
import com.rkm.shortsstories.repository.repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(val repo: repo) : ViewModel() {




    val downloadLiveData get() = repo.downloadLiveData

        fun opendownloadedbook(fileName: String){

            CoroutineScope(Dispatchers.IO).launch {
                repo.opendownloadedbook(

                    fileName = fileName
                )


            }


        }

    fun downloadFile(url: String, fileName: String) {



            CoroutineScope(Dispatchers.IO).launch {
                repo.downloadpdf(
                    url = url,
                    fileName = fileName
                )


        }


    }
}