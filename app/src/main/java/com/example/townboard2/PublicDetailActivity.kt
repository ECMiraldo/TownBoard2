package com.example.townboard2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView


//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//xmlns:app="http://schemas.android.com/apk/res-auto"
//xmlns:tools="http://schemas.android.com/tools"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//tools:context=".PublicDetailActivity">
//
//
//<de.hdodenhof.circleimageview.CircleImageView
//android:id="@+id/iamgemEmpresa"
//android:layout_width="250dp"
//android:layout_height="250dp"
//android:layout_centerHorizontal="true"
//android:src="@color/black"
//android:layout_marginTop="20dp"/>
//
//<TextView
//android:id="@+id/nameDetail"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_centerHorizontal="true"
//android:layout_below="@id/iamgemEmpresa"
//android:layout_marginTop="10dp"
//android:textColor="@color/black"
//android:textSize="25sp"
//android:text="Exemplo"/>
//
//<TextView
//android:id="@+id/local"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_centerHorizontal="true"
//android:layout_below="@id/nameDetail"
//android:layout_marginTop="10dp"
//android:textColor="@color/black"
//android:textSize="15sp"
//android:text="EXEMPLO"
//android:textStyle="bold"/>
//
//<TextView
//android:id="@+id/descriptionDetail"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_below="@id/local"
//android:layout_marginStart="20sp"
//android:layout_marginTop="15dp"
//android:text="Isso e apenas um exemplos"
//android:textColor="@color/black" />
//
//</RelativeLayout>
//class PublicDetailActivity : AppCompatActivity() {
//    var name : String? = null
//    var description : String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_public_detail)
//
//        name = intent.getStringExtra("public_name")?:""
//        val localDetail   : String = intent.getStringExtra("public_local")?:""
//        description = intent.getStringExtra("public_description")?:""
//
//        val textViewName           = findViewById<TextView>(R.id.nameDetail)
//        val textViewLocal           =findViewById<TextView>(R.id.local)
//        val textViewDescription    = findViewById<TextView>(R.id.descriptionDetail)
//
//        textViewName.text = name
//        textViewLocal.text = localDetail
//        textViewDescription.text = description
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return true
//    }
//}