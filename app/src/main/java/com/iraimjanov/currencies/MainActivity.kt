package com.iraimjanov.currencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iraimjanov.currencies.adapters.RvAdapter
import com.iraimjanov.currencies.databinding.ActivityMainBinding
import com.iraimjanov.currencies.model.Money

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
    }

    private fun loadData() {
        requestQueue = Volley.newRequestQueue(this)
        if (listMoney.isEmpty()) {
            loadArray()
        } else {
            buildRv()
        }
    }

    private fun loadArray() {
        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET,
                "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/",
                null,
                { response ->
                    val str = response.toString()
                    val type = object : TypeToken<ArrayList<Money>>() {}.type
                    listMoney = Gson().fromJson(str, type)
                    buildRv()
                }
            ) { Toast.makeText(this, "Internet error", Toast.LENGTH_SHORT).show() }
        requestQueue.add(jsonArrayRequest)
    }

    private fun buildRv(){
        rvAdapter = RvAdapter(listMoney)
        binding.recyclerView.adapter = rvAdapter
    }

    companion object {
        var listMoney = ArrayList<Money>()
    }

}