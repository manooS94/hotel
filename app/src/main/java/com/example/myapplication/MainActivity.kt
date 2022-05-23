package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build

import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNOTIFICATIONcHANNEL()
        PendingNotification()

        val btnSendMsgToNextActivity: Button = findViewById(R.id.bt1)
        btnSendMsgToNextActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val etUserMessage: Spinner = findViewById<Spinner?>(R.id.spinner);
            val message: String = etUserMessage.selectedItemId.toString();
            intent.putExtra("key", message)
            startActivity(intent)
        }
        var flag: String = "Amman Rotana "
        val spinnerVal: Spinner = findViewById(R.id.spinner)
        var options = arrayOf("Amman Rotana ",
            "W Amman Hotel",
            "The House Boutique Suites ",
            "Landmark Amman Hotel & Conference Center ",
            "Toledo Amman Hotel ",
            "ibis Amman",
            "New MerryLand Hotel ",
            "Grand Hotel Amman "
        )


        spinnerVal.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        spinnerVal.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var flag = options.get(p2);

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

                TODO("Not yet implemented")
            }
        }
    }

    private fun PendingNotification() {
        val intent = Intent(this, MainActivity2::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
    private fun createNOTIFICATIONcHANNEL() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "test_notification"
            val descriptionText = "Trying to test different types notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(" App_Channel.testNotification", name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

