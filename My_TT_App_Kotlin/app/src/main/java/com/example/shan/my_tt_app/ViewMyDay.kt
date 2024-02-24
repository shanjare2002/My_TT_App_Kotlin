package com.example.shan.my_tt_app

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import java.lang.Boolean
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.Int
import kotlin.String
import kotlin.arrayOf
import kotlin.intArrayOf
import kotlin.plus

class ViewMyDay : AppCompatActivity() {
    var db: DBManager? = null
    var intRecord1 = 0
    var intRecord2 = 0
    var intRecord3 = 0
    var intRecord4 = 0
    var intRecord5 = 0
    var intRecord6 = 0
    var intRecord7 = 0
    var intRecord8 = 0
    var intRecord9 = 0
    var intRecord10 = 0
    var i = 0
    var intDayId = 0
    lateinit var strToday: String
    var strPeriod: String? = null
    private var mDateSetListener: OnDateSetListener? = null
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_day)
        //set tooldbar

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2B1C27")));

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_view_my_day, null);

        TextView actionbar_title = (TextView)v.findViewById(R.id.textTitle);
        actionbar_title.setText("Hiiiiii");

        actionBar.setCustomView(v);

*/


        //trying to  understand form wehre view my day is opened
        val intent = intent
        val extras = intent.extras
        val strActivity = extras!!.getString("key")
        Log.d("show origin", strActivity!!)

        //set current date time
        val calendar = Calendar.getInstance()
        val df = SimpleDateFormat("EEE, dd MMM yyyy")
        val strDateTime = df.format(calendar.time)
        val textViewDate = findViewById<TextView>(R.id.textShowDate)


        //storing th day slected
        if (strActivity == "frommain") {
            Log.d("show origin", "inside if")
            val sdfDay = SimpleDateFormat("EEE")
            textViewDate.text = strDateTime
            strToday = sdfDay.format(calendar.time)
        } else {
            val strday = extras.getString("day")
            when (strday) {
                "Monday" -> {
                    strToday = "Mon"
                    textViewDate.text = "Monday - Timetable"
                }

                "Tuesday" -> {
                    strToday = "Tue"
                    textViewDate.text = "Tuesday - Timetable"
                }

                "Wednesday" -> {
                    strToday = "Wed"
                    textViewDate.text = "Wednesday - Timetable"
                }

                "Thursday" -> {
                    strToday = "Thu"
                    textViewDate.text = "Thursday - Timetable"
                }

                "Friday" -> {
                    strToday = "Fri"
                    textViewDate.text = "Friday - Timetable"
                }

                "Saturday" -> {
                    strToday = "Sat"
                    textViewDate.text = "Saturday - Timetable"
                }

                "Sunday" -> {
                    strToday = "Sun"
                    textViewDate.text = "Sunday - Timetable"
                }
            }
        }
        Log.d("Date Picker", strToday!!)
        //setting date and Datepicker
        textViewDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                this@ViewMyDay,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
        mDateSetListener = OnDateSetListener { datePicker, year, month, day -> // month = month + 1;
            val tempCal = Calendar.getInstance()
            tempCal[year, month] = day
            val sdf = SimpleDateFormat("EEE, dd MMM yyyy")
            val strDate = sdf.format(tempCal.time)
            textViewDate.text = strDate
            Log.d("Date Picker", strDate)
            //storing th day slected
            val tempCalDay = Calendar.getInstance()
            tempCalDay[year, month] = day
            val sdfDay = SimpleDateFormat("EEE")
            strToday = sdfDay.format(tempCalDay.time)
            Log.d("Date Picker Clicked", strToday)
            ShowData(strToday)
        }


        //show data
        ShowData(strToday)
    }

    fun ShowData(strToday: String?) {
        db = DBManager(this)

        //populate the upcoming events
        //assigning data to the next 3 events\


        //populate the upcoming events
        //get today's day id
        Log.d("before switch", strToday!!)
        when (strToday) {
            "Mon" -> intDayId = 1
            "Tue" -> intDayId = 2
            "Wed" -> intDayId = 3
            "Thu" -> intDayId = 4
            "Fri" -> intDayId = 5
            "Sat" -> intDayId = 6
            "Sun" -> intDayId = 7
        }
        Log.d("After switch", Integer.toString(intDayId))
        val res = db!!.getAllData(intDayId)
        val events = arrayOf(
            "", "", "", "", "", "", "", "", "", ""
        )
        val recordNumber = intArrayOf(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        )
        val listEvents = findViewById<ListView>(R.id.list_upcoming_events)
        if (res.count == 0) {


            // Create a List from String Array elements
            //final List<String> event_list = new ArrayList<String>(Arrays.asList(events));
            recordNumber[0] = 0
            recordNumber[1] = 0
            recordNumber[2] = 0
            recordNumber[3] = 0
            recordNumber[4] = 0
            recordNumber[5] = 0
            recordNumber[6] = 0
            recordNumber[7] = 0
            recordNumber[8] = 0
            recordNumber[9] = 0
            res.close()
            val builder = AlertDialog.Builder(this@ViewMyDay)
            builder.setTitle("No periods set!")
            builder.setMessage("No periods set for this day, please set your time table first or select another day!")
            Log.d("Record value", Integer.toString(recordNumber[0]))
            // add a button
            builder.setPositiveButton("OK", null)

            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
            // Create an ArrayAdapter from List
            val arrayAdapter: ArrayAdapter<String?> =
                object : ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, events) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {
                        /// Get the Item from ListView
                        val view = super.getView(position, convertView, parent)
                        val tv = view.findViewById<View>(android.R.id.text1) as TextView

                        // Set the text size 25 dip for ListView each item
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                        tv.setTextColor(Color.parseColor("#303f9f"))
                        tv.setTypeface(Typeface.DEFAULT_BOLD)
                        // Return the view
                        return view
                    }
                }
            // DataBind ListView with items from ArrayAdapter
            Log.d("Record value again", Integer.toString(recordNumber[0]))
            listEvents.adapter = arrayAdapter
        }
        if (res.count > 0) {
            Log.d("ViewMyDay", "got data")
            // Initializing a new String Array
            val buffer = StringBuffer()
            var strMyData: String
            i = 1
            while (i <= res.count) {
                var strSubject: String
                var intPeriod: Int
                intPeriod = res.getInt(1)
                strSubject = res.getString(3)
                strPeriod = if (intPeriod < 10) {
                    "#0" + Integer.toString(intPeriod)
                } else {
                    "#" + Integer.toString(intPeriod)
                }
                Log.d("check class", res.getString(4))
                buffer.append(
                    strPeriod + "-" + res.getString(2) + " : " + strSubject + " : " + res.getString(
                        4
                    ) + " : " + res.getString(5)
                )
                strMyData = buffer.toString()
                events[i - 1] = strMyData
                recordNumber[i - 1] = res.getInt(0)
                res.moveToNext()
                buffer.delete(0, buffer.length)
                i++
            }


            // Create a List from String Array elements
            //final List<String> event_list = new ArrayList<String>(Arrays.asList(events));

            // Create an ArrayAdapter from List
            val arrayAdapter: ArrayAdapter<String?> =
                object : ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, events) {
                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {
                        /// Get the Item from ListView
                        val view = super.getView(position, convertView, parent)
                        val tv = view.findViewById<View>(android.R.id.text1) as TextView

                        // Set the text size 25 dip for ListView each item
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                        tv.setTextColor(Color.parseColor("#303f9f"))
                        tv.setTypeface(Typeface.DEFAULT_BOLD)
                        // Return the view
                        return view
                    }
                }


            // DataBind ListView with items from ArrayAdapter
            Log.d("Record again2", Integer.toString(recordNumber[0]))
            listEvents.adapter = arrayAdapter
            Log.d("Record again3", Integer.toString(recordNumber[0]))
        }
        listEvents.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Log.d("Record again4", Integer.toString(recordNumber[0]))
            val CheckBoxUp = findViewById<View>(R.id.checkBoxUpdate) as CheckBox
            if (position == 0 && recordNumber[0] > 0) {
                Log.d("View my day", "List box first row clicked")
                Log.d("View my day", Integer.toString(recordNumber[0]))
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    Log.d("View my day", "checkbox clicked")
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    Log.d("View my day", Integer.toString(recordNumber[0]))
                    myIntent.putExtra(recordId, recordNumber[0])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    Log.d("View my day", "going to TTentry")
                    Log.d("View my day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[0])
                    Log.d("View my day", Integer.toString(recordNumber[0]))
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 1 && recordNumber[1] > 0) {
                Log.d("Event Click", Integer.toString(recordNumber[1]))
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    Log.d("View my day", "checkbox clicked")
                    Log.d("View my day", strPeriod!!)
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    myIntent.putExtra(recordId, recordNumber[1])
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[1])
                    Log.d("View my day", Integer.toString(recordNumber[0]))
                    myIntent.putExtra(recordId, recordNumber[1])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 2 && recordNumber[2] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[2])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[2])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 3 && recordNumber[3] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[3])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[3])
                    Log.d("Event Click", Integer.toString(recordNumber[3]))
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 4 && recordNumber[4] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[4])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[4])
                    Log.d("Event Click", Integer.toString(recordNumber[4]))
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 5 && recordNumber[5] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[5])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[5])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 6 && recordNumber[6] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[6])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[6])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 7 && recordNumber[7] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[7])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[7])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 8 && recordNumber[8] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[8])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[8])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
            if (position == 9 && recordNumber[9] > 0) {
                if (CheckBoxUp.isChecked == Boolean.TRUE) {
                    val myIntent = Intent(view.context, TimeTableEntry::class.java)
                    myIntent.putExtra(recordId, recordNumber[9])
                    myIntent.putExtra("key", "viewDay")
                    myIntent.putExtra("day", strToday)
                    startActivityForResult(myIntent, 0)
                } else {
                    val myIntent = Intent(view.context, Event_Detail_Activity::class.java)
                    myIntent.putExtra(recordId, recordNumber[9])
                    myIntent.putExtra("key", "viewDay")
                    startActivityForResult(myIntent, 0)
                }
            }
        }
    }

    companion object {
        const val recordId = "recordId"
    }
}
