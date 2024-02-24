package com.example.shan.my_tt_app

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    var db: DBManager? = null
    private var btnSetTT: Button? = null
    private var btnVieMyDay: Button? = null
    var intRecord1 = 0
    var intRecord2 = 0
    var intRecord3 = 0
    var arrayList = ArrayList<Int>(3)
    var count = 0
    var i = 0
    var intDayId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //database instantiate and insert dummy data
        db = DBManager(this)


        /* Boolean insert = db.insert();
        if(insert==true) { Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
        }
        if(insert==false) { Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }*/
        //DB entry ends

        //set the welcome message

        //  TextView txtWelcome = findViewById(R.id.text_welcome);
        // txtWelcome.setText("Welcome To Time Table Manager");

        //set current date time
        val calendar = Calendar.getInstance()
        val df = SimpleDateFormat("EEE, dd MMM yyyy hh:mm aaa")
        val strDateTime = df.format(calendar.time)
        val textViewDate = findViewById<TextView>(R.id.text_view_date)
        textViewDate.text = strDateTime
        PopulateEvents()

        //clicking the listbox
        val listEvents = findViewById<ListView>(R.id.list_upcoming_events)
        listEvents.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            if (position == 0 && arrayList.size > 0) {
                Log.d("MainActivty", "List box first row in create")
                Log.d("MainActivty", "going in Create")
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                Log.d("checking arraylist", Integer.toString(arrayList[0]))
                myIntent.putExtra(recordId, arrayList[0])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
            if (position == 1 && arrayList.size > 1) {
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                myIntent.putExtra(recordId, arrayList[1])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
            if (position == 2 && arrayList.size > 2) {
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                myIntent.putExtra(recordId, arrayList[2])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
        }


        ///timetable button
        btnSetTT = findViewById<View>(R.id.buttonSetMyTT) as Button
        btnSetTT!!.setOnClickListener { v ->
            Log.d("TT Entry", "button clicked")
            val myIntent = Intent(v.context, TimeTableEntry::class.java)
            myIntent.putExtra("key", "frommain")
            myIntent.putExtra("day", "")
            startActivity(myIntent)
        }

        ///view my day button
        btnVieMyDay = findViewById<View>(R.id.buttonViewMyDay) as Button
        btnVieMyDay!!.setOnClickListener {
            Log.d("ViewMyDay", "button clicked")
            openActivityViewMyDay()
        }
    }

    override fun onRestart() {
        super.onRestart()
        setContentView(R.layout.activity_main)
        Log.d("resume", "resuming")

        //set current date time
        val calendar = Calendar.getInstance()
        val df = SimpleDateFormat("EEE, dd MMM yyyy hh:mm aaa")
        val strDateTime = df.format(calendar.time)
        val textViewDate = findViewById<TextView>(R.id.text_view_date)
        textViewDate.text = strDateTime
        PopulateEvents()
        //  PopulateEvents();

        ///timetable button
        btnSetTT = findViewById<View>(R.id.buttonSetMyTT) as Button
        btnSetTT!!.setOnClickListener { v ->
            val myIntent = Intent(v.context, TimeTableEntry::class.java)
            Log.d("TT Entry", "buton clicked in Restrt")
            myIntent.putExtra("key", "frommain")
            myIntent.putExtra("day", "")
            startActivity(myIntent)
        }

        ///view my day button
        btnVieMyDay = findViewById<View>(R.id.buttonViewMyDay) as Button
        btnVieMyDay!!.setOnClickListener {
            Log.d("ViewMyDay", "button clicked")
            openActivityViewMyDay()
        }

        //clicking the listbox
        val listEvents = findViewById<ListView>(R.id.list_upcoming_events)
        listEvents.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            if (position == 0 && arrayList.size > 0) {
                Log.d("MainActivty", "List box first row clicked")
                Log.d("MainActivty", "going in restart")
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                myIntent.putExtra(recordId, arrayList[0])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
            if (position == 1 && arrayList.size > 1) {
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                myIntent.putExtra(recordId, arrayList[1])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
            if (position == 2 && arrayList.size > 2) {
                val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                myIntent.putExtra(recordId, arrayList[2])
                myIntent.putExtra("key", "frommain")
                startActivityForResult(myIntent, 0)
            }
        }
    }

    fun openActivityTimetbaleEntry() {
        val intent = Intent(this, TimeTableEntry::class.java)
        startActivity(intent)
    }

    fun openActivityViewMyDay() {
        val intent = Intent(this, ViewMyDay::class.java)
        intent.putExtra("key", "frommain")
        startActivity(intent)
    }

    fun PopulateEvents() {

        //populate the upcoming events
        //get today's day id
        val calendar = Calendar.getInstance()
        val df1 = SimpleDateFormat("EEE")
        val strToday = df1.format(calendar.time)
        count = 0
        when (strToday) {
            "Mon" -> intDayId = 1
            "Tue" -> intDayId = 2
            "Wed" -> intDayId = 3
            "Thu" -> intDayId = 4
            "Fri" -> intDayId = 5
            "Sat" -> intDayId = 6
            "Sun" -> intDayId = 7
        }

        //assigning data to the next 3 events
        val res = db!!.getAllData(intDayId)

        // Initializing a new String Array
        val events = arrayOf(
            "",
            "",
            ""
        )

        ///checking the current time
        val df2 = SimpleDateFormat("HHmm")
        val strCurrentTime = df2.format(calendar.time)

        //strCurrentTime="830";
        val intTimeCompare: Int
        intTimeCompare = strCurrentTime.toInt()
        if (res.count == 0) {
            val textViewDate = findViewById<TextView>(R.id.text_view_date)
            textViewDate.text = "TimeTable not set for the day!"
            arrayList.clear()
        }
        if (res.count > 0) {
            arrayList.clear()
            val buffer = StringBuffer()
            i = 0
            while (i < res.count) {
                Log.d("DB", "reaching there$i")
                Log.d("DBChecking", res.getString(2))
                val intDBFromTime = res.getString(2).toInt()
                Log.d("DBChecking", "got from time")
                if (intTimeCompare <= intDBFromTime) {
                    var strPeriod: String
                    var intPeriod: Int
                    intPeriod = res.getInt(1)
                    strPeriod = if (intPeriod < 9) {
                        "#0" + Integer.toString(intPeriod)
                    } else {
                        "#" + Integer.toString(intPeriod)
                    }
                    buffer.append(
                        strPeriod + " : " + res.getString(2) + "-" + res.getString(3) + " : " + res.getString(
                            4
                        ) + " : " + res.getString(5)
                    )
                    val strMyData1 = buffer.toString()


                    // updating String Array
                    events[count] = strMyData1
                    arrayList.add(res.getInt(0))
                    Log.d("DB", "added to array")
                    count = count + 1
                    if (count == 3) {
                        break
                    }
                    buffer.delete(0, buffer.length)
                }
                res.moveToNext()
                i++
            }
            if (count == 0) {
                events[0] = "No more periods remaining for the day!"
            }
        }
        val listEvents = findViewById<ListView>(R.id.list_upcoming_events)


        // Create a List from String Array elements
        //final List<String> event_list = new ArrayList<String>(Arrays.asList(events));

        // Create an ArrayAdapter from List
        val arrayAdapter: ArrayAdapter<String?> =
            object : ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, events) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    /// Get the Item from ListView
                    val view = super.getView(position, convertView, parent)
                    val tv = view.findViewById<View>(android.R.id.text1) as TextView

                    // Set the text size 25 dip for ListView each item
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22f)
                    tv.setTextColor(Color.parseColor("#303f9f"))
                    tv.setTypeface(Typeface.DEFAULT_BOLD)
                    // Return the view
                    return view
                }
            }

        // DataBind ListView with items from ArrayAdapter
        listEvents.adapter = arrayAdapter
    }

    companion object {
        const val recordId = "recordId"
    }
}
