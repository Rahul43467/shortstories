package com.rkm.shortsstories.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rkm.shortsstories.models.homedata

class MainRepo(val context: Context) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseRef = firebaseDatabase.getReference("AppData").child("Home")
    private val homeLD = MutableLiveData<myresponse<ArrayList<homedata>>>()

    val homeLiveData get() = homeLD

    suspend fun getHomeData() {
        homeLiveData.postValue(myresponse.Loading())
        val TAG = "MainActivity"
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshots: DataSnapshot) {
                Log.i(TAG, "onDataChange: Value Changed")
                if (!snapshots.exists()) {
                    homeLiveData.postValue(myresponse.Error("Data snapshot not exists"))
                    return
                }
                val tempList = ArrayList<homedata>()
                for (snapshot in snapshots.children) {
                    val homeModel = snapshot.getValue(homedata::class.java)
                    homeModel?.let {
                        tempList.add(homeModel)
                    }
                }

                homeLiveData.postValue(myresponse.Success(tempList))




            }

            override fun onCancelled(error: DatabaseError) {
                homeLiveData.postValue(myresponse.Error("Something Went Wrong with Database."))
            }

        })

    }

}