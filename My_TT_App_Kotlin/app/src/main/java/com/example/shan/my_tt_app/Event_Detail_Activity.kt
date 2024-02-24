package com.example.shan.my_tt_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar

class Event_Detail_Activity : AppCompatActivity() {
    var db: DBManager? = null
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event__detail_)
        Log.d("EventDetailActivty", "in event details acitivty")

        //setting the action bar


        //------------------
        val intent = intent


        //  if(strIntent.equals(""))
        val extras = intent.extras
        val strActivity = extras!!.getString("key")
        Log.d("Event Activity Trace", "coming here")
        var RecordId = 0
        if (strActivity == "frommain") {
            RecordId = intent.getIntExtra(MainActivity.recordId, 0)
            Log.d("Main to  event detail", Integer.toString(RecordId))
        } else {
            RecordId = intent.getIntExtra(ViewMyDay.recordId, 0)
            Log.d("In event detail", Integer.toString(RecordId))
        }
        Log.d(
            "Event Details Record",
            Integer.toString(intent.getIntExtra(MainActivity.recordId, 0))
        )
        db = DBManager(this)

        //get event data from database
        //assigning data to the next 3 events
        val res = db!!.getEventData(RecordId)
        if (res.count == 0) {
            val txtSubject = findViewById<TextView>(R.id.textSubJect)
            txtSubject.text = "noData"
        }
        if (res.count > 0) {
            //set current date time
            val calendar = Calendar.getInstance()
            val df = SimpleDateFormat("EEE, dd MMM yyyy hh:mm aaa")
            val strDateTime = df.format(calendar.time)
            val textDateTime = findViewById<TextView>(R.id.textDateTime)
            textDateTime.text = strDateTime
            //----------get other data
            var strMyData: String
            val buffer = StringBuffer()
            //show period data
            buffer.append(res.getString(1))
            strMyData = "Period #$buffer"
            val txtPeriod = findViewById<TextView>(R.id.textPeriod)
            txtPeriod.text = strMyData
            buffer.delete(0, buffer.length)
            // show subject data
            buffer.append(res.getString(4))
            strMyData = buffer.toString()
            val txtSubject = findViewById<TextView>(R.id.textSubJect)
            txtSubject.text = strMyData
            buffer.delete(0, buffer.length)
            //show class data
            buffer.append(res.getString(5))
            strMyData = buffer.toString()
            val txtClass = findViewById<TextView>(R.id.textClass)
            txtClass.text = strMyData
            buffer.delete(0, buffer.length)
            //show Teacher data
            buffer.append(res.getString(6) + " " + res.getString(7))
            strMyData = buffer.toString()
            val txtTeacher = findViewById<TextView>(R.id.textTeacher)
            txtTeacher.text = strMyData
            buffer.delete(0, buffer.length)
            //show FromTime
            buffer.append(res.getString(2))
            strMyData = buffer.toString()
            val txtFromTime = findViewById<TextView>(R.id.textFromTime)
            txtFromTime.text = strMyData
            buffer.delete(0, buffer.length)
            //show Totime
            buffer.append(res.getString(3))
            strMyData = buffer.toString()
            val txtToTime = findViewById<TextView>(R.id.textToTime)
            txtToTime.text = strMyData
            buffer.delete(0, buffer.length)
            //show HomewrokDetails
            buffer.append(res.getString(5))
            strMyData = buffer.toString()
        }

        //
        // } Button btnHome = (Button) findViewById(R.id.buttonHome);
        // btnHome.setOnClickListener(new View.OnClickListener() {
        //   public void onClick (View v) {
        //    Intent returnBtn = new Intent("android.intent.action.MAIN");
        //  Log.d("EventDetailActivty", "returniunghome");
        //  startActivity(returnBtn);
        //  }
        //   }
        //  }
    }
}