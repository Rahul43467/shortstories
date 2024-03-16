package com.rkm.shortsstories

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.rkm.shortsstories.databinding.ItemCategoryBinding
import com.rkm.shortsstories.models.bookmodel
import com.rkm.shortsstories.models.homedata

class catogeryadapter(var list : ArrayList<bookmodel>, var context: Context, var navController: NavController): RecyclerView.Adapter<catogeryadapter.catogeryviewholder>() {

    class catogeryviewholder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){

        fun itembind(homeModel: bookmodel, context: Context, navController: NavController){

            binding.apply {
                Glide.with(context).load(homeModel.image).thumbnail(0.5f).into(mBookImage)
                mBookTitle.text=homeModel.name
                mBookDesc.text=homeModel.description
                mAuthorName.text=homeModel.speak


                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel)
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity,android.util.Pair.create(mBookImage,"bookse"))
                    context.startActivity(intent,options.toBundle())





                }

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catogeryviewholder {

        var binding= ItemCategoryBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return catogeryviewholder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: catogeryviewholder, position: Int) {
        var currentdata = list.get(position)
        return holder.itembind(currentdata,context,navController)
    }
}