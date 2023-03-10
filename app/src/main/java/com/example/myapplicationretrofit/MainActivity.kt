package com.example.myapplicationretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationretrofit.adapter.RcAdapter
import com.example.myapplicationretrofit.databinding.ActivityMainBinding
import com.example.myapplicationretrofit.databinding.ActivityMainFirstBinding
import com.example.myapplicationretrofit.retrofit.ProductApi
import com.example.myapplicationretrofit.model.level2.Result
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainFirstBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var number = 1

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val retrofit = Retrofit.Builder().baseUrl("https://rickandmortyapi.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val productApi = retrofit.create(ProductApi::class.java)

//        binding.getInfo.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                //val product = productApi.getProductById(number)
//                val product = productApi.getCharacter(number)
//
//
//                runOnUiThread {
//                    //number++
//                    Log.d("MyTag","result = $product")
//                    binding.apply {
//                        tvName.text = product.name
//                        Picasso.get().load(product.image).into(imViewPhoto)
//
//                    }
//
//                }
//            }
//        }
        recyclerView = binding.rcViewMain
        adapter = RcAdapter()
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val product = productApi.getCharacter()

            runOnUiThread {
                adapter.setList(product.results)


            }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if (!recyclerView.canScrollVertically(1)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        number++
                        val page = productApi.getPage(number)

                        runOnUiThread {
                            adapter.addList(page.results)

                        }
                    }
                }
            }
        })


    }
}