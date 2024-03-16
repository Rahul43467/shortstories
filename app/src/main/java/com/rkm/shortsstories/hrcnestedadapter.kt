package com.rkm.shortsstories

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkm.shortsstories.databinding.HomenesteditemBinding
import com.rkm.shortsstories.models.bookmodel
import com.rkm.shortsstories.models.homedata

class hrcnestedadapter(var list: ArrayList<bookmodel>, var context: Context): RecyclerView.Adapter<hrcnestedadapter.nestedviewholder>() {


    class nestedviewholder(var binding: HomenesteditemBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel: bookmodel, context:Context){

            binding.apply{

                Glide.with(context).load(homeModel.image).thumbnail(0.3f).into(imageView)

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel)
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, android.util.Pair.create(imageView,"bookse"))
                    context.startActivity(intent,options.toBundle())
                }


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nestedviewholder {
        var binding= HomenesteditemBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return nestedviewholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: nestedviewholder, position: Int) {
        val currentdata=list.get(position)
        return holder.itembind(currentdata,context)
    }
}