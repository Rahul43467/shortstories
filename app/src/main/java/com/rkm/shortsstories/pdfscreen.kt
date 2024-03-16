package com.rkm.shortsstories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.rkm.shortsstories.databinding.ActivityPdfscreenBinding

class pdfscreen : AppCompatActivity() {
    lateinit var binding : ActivityPdfscreenBinding
    lateinit var bookPdflink: String
    lateinit var bookId: String
    var activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfscreenBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE)

        setContentView(binding.root)

        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            bookPdflink = intent.getStringExtra("book.pdf").toString()

            pdfView.fromUri(bookPdflink.toUri())
                .swipeHorizontal(false)
                .scrollHandle(DefaultScrollHandle(activity))
                .enableSwipe(true)
                .load()
        }


    }


}