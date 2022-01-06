package com.example.townboard2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.townboard2.adapters.CityAdapter
import com.example.townboard2.datatypes.City
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var cityRecyclerView : RecyclerView
    private lateinit var cityList : ArrayList<City>
    private lateinit var adapter : CityAdapter
    private lateinit var auth : FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        cityList = arrayListOf<City>()

        cityRecyclerView = findViewById(R.id.cityRecyclerView)
        adapter = CityAdapter(this, cityList)


        //GETS CITY COLLECTION AND PUTS INTO CHAT LIST FOR THE RECYCLER VIEW
       db.collection("city")
            .get()
            .addOnSuccessListener {  result ->
                for (document in result.documents) {
                    document.data?.let {
                        (it["name"] as String?)?.let {
                            cityList.add(City(it))
                        }
                    }
                adapter.notifyDataSetChanged()

                }
            }.addOnFailureListener(){
                    exception ->  Log.w(TAG, "Error getting documents.", exception)
            }

        cityRecyclerView.layoutManager = LinearLayoutManager(this)
        cityRecyclerView.adapter = adapter

    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            auth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            finish()
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
