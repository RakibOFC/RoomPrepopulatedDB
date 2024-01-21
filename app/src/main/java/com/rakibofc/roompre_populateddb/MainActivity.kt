package com.rakibofc.roompre_populateddb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {

            val dao = AppDatabase.getInstance(applicationContext).appDao()

            /*// Insert data
            for (i in 1..10) {
                dao.insertUser(UserEntity(i, "rakib$i", "rakib$i@gmail.com"))
            }*/

            dao.getUsers().forEach {
                Log.e("TAG", "User: ${it.email}")
            }
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(applicationContext, "Button click toast", Toast.LENGTH_SHORT).show()
        }
    }
}