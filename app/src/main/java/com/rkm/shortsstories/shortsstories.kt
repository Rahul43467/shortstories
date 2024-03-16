package com.rkm.shortsstories

import android.app.Application
import android.view.WindowManager
import com.google.firebase.database.FirebaseDatabase
import com.rkm.shortsstories.utils.networkcheck

class shortsstories (): Application() {


    override fun onCreate() {

        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)




    }
}