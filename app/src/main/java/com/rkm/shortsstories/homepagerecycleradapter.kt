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
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkm.shortsstories.databinding.BookitemBinding
import com.rkm.shortsstories.databinding.HrcboditemBinding
import com.rkm.shortsstories.databinding.Hrcbook1Binding
import com.rkm.shortsstories.models.bookmodel
import com.rkm.shortsstories.models.homedata
import com.rkm.shortsstories.utils.springscroll

class homerecylceradapter(var datalist: ArrayList<homedata>, var context: Context, var navController: NavController) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class itemViewHolder(var binding: HrcboditemBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel: homedata, context:Context, navController: NavController){
            val sharedPool = RecyclerView.RecycledViewPool()

            binding.apply{

                mCategoryTitle.text = homeModel.category
                mSeeAllBtn.setOnClickListener {

                    binding.apply {
                        mCategoryTitle.text=homeModel.category
                    }

                    val intent = Intent(context, bookcatogery::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist)
                    val option = ActivityOptionsCompat.makeSceneTransitionAnimation(context as AppCompatActivity,
                         Pair.create(hrcbodnested,"bookse"))


                    context.startActivity(intent,option.toBundle())






                }




                var list= arrayListOf<bookmodel>()

                if(homeModel.booklist!=null){
                    list= homeModel.booklist
                }
                val hrcnestedadapter=hrcnestedadapter(list,context)
                hrcbodnested.adapter=hrcnestedadapter
                hrcbodnested.setRecycledViewPool(sharedPool)
                springscroll().attachToRecyclerView(hrcbodnested)



            }
        }

    }
    class specialListViewHolder1(var binding: Hrcbook1Binding):RecyclerView.ViewHolder(binding.root){
        fun specialbind(homeModel: homedata, context:Context, navController: NavController){

            binding.apply{

                textView.text=homeModel.category
                Glide.with(context).load(homeModel.booklist?.get(0)?.image).thumbnail(0.5f).into(imageView)

                mReadBookBtn.setOnClickListener { val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, android.util.Pair.create(imageView,"bookse"))
                    context.startActivity(intent,options.toBundle()) }


                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, android.util.Pair.create(imageView,"bookse"))
                    context.startActivity(intent,options.toBundle())
                }

            }


        }

    }
    class specialListViewHolder(var binding: BookitemBinding):RecyclerView.ViewHolder(binding.root){
        fun specialbind(homeModel: homedata, context:Context, navController: NavController){

            binding.apply{

                Glide.with(context).load(homeModel.booklist?.get(0)?.image).thumbnail(0.5f).into(imageView)
                textview.text=homeModel.category

                imageView.setOnClickListener { val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, android.util.Pair.create(imageView,"bookse"))
                    context.startActivity(intent,options.toBundle()) }

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescription::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, android.util.Pair.create(imageView,"bookse"))
                    context.startActivity(intent,options.toBundle())
                }

            }


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            1 -> {
                itemViewHolder(
                    HrcboditemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            2 -> {
                specialListViewHolder1(
                    Hrcbook1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                specialListViewHolder(
                    BookitemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

        }
    }



    override fun getItemViewType(position:Int):Int{
        return datalist.get(position).type
    }



    override fun getItemCount(): Int {
        return datalist.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentdata=datalist.get(position)
        when(currentdata.type){

            1->{(holder as itemViewHolder).itembind(currentdata,context,navController)}
            2->{(holder as specialListViewHolder1).specialbind(currentdata,context,navController)}
            else->{(holder as specialListViewHolder).specialbind(currentdata,context,navController)}

        }
    }}