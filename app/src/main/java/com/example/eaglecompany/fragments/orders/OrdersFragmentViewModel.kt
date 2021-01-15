package com.example.eaglecompany.fragments.orders

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.eaglecompany.models.MData
import com.example.eaglecompany.models.Worker
import com.example.eaglecompany.models.WorkerApi
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrdersFragmentViewModel:ViewModel() {
    lateinit var worker: Worker
    var list = mutableListOf<MData>()
    private lateinit var messages: Call<Worker>
    fun getData(adapter: NewOrderAdapter,type:String,noInfoView:TextView,progressBar: ProgressBar,context:Context){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/DanilKarabinovskyi/database/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val messagesApi: WorkerApi = retrofit.create(WorkerApi::class.java)
        if(type == "carrier"){
            messages = messagesApi.carrier()!!
        }else if(type == "porter"){
            messages = messagesApi.porter()!!
        }else if(type == "worker"){
            messages = messagesApi.worker()!!
        }else{
        }


        messages!!.enqueue(object : Callback<Worker> {
            override fun onResponse(
                call: Call<Worker?>?,
                response: Response<Worker?>
            ) {
                if (response.isSuccessful) {
                    Log.i("response " + response.body(),"response ${response.body()}")
                    worker = response.body()!!
                    list = mutableListOf<MData>()
                    for (element in worker.data){
                        list.add(MData(false,element))
                    }
                    progressBar.visibility = View.INVISIBLE
                    adapter.submitList(list)
                    if(worker.data.isEmpty()){
                        noInfoView.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(context, "Oops. something wrong, please check your internet connection", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<Worker?>?,
                t: Throwable
            ) {
                Toast.makeText(context, "Oops. something wrong, please check your internet connection", Toast.LENGTH_LONG).show()
            }
        })

    }
}