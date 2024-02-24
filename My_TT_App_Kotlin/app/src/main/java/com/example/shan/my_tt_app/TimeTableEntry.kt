package com.example.shan.my_tt_app

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class TimeTableEntry : AppCompatActivity() {
    lateinit var timePickerDialog: TimePickerDialog
    lateinit var txtFromTime: TextView
    lateinit var txtToTime: TextView
    lateinit var db: DBManager
    private lateinit var btnSaveTT: Button
    private lateinit var btnFromTime: Button
    var i = 0
    private lateinit var fromtime: TextView
    private lateinit var totime: TextView
    private val mTimeSetListener: OnTimeSetListener? = null
    var RecordId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table_entry)
        //addingfromtestshow
        val txtView = findViewById<View>(R.id.textFromTimeShow) as TextView
        val txtTo = findViewById<View>(R.id.textToTimeShow) as TextView

        ////new code tio edit record
        Log.d("Timeentry from main", "rechinh 1")
        val intent = intent
        Log.d("Timeentry from main", "rechinh 2")
        val extras = intent.extras
        val strActivity = extras!!.getString("key")
        Log.d("Timeentry from main", "rechinh 3")
        Log.d("Timeentry from main", strActivity!!)
        val strToday = extras.getString("day")
        Log.d("Timeentry from main", strActivity)
        RecordId = 0
        if (strActivity == "frommain") {
            RecordId = intent.getIntExtra(MainActivity.recordId, 0)
            Log.d("In timeentry", "reached time enrtry")
            showInitialData()
        } else {
            //showInitialData();
            Log.d("In Time Entry", Integer.toString(RecordId))
            RecordId = intent.getIntExtra(ViewMyDay.recordId, 0)
            Log.d("In Time Entry", Integer.toString(RecordId))
            db = DBManager(this)

            //get event data from database
            //assigning data to the next 3 events
            val res = db!!.getEventData(RecordId)
            if (res.count == 0) {
                val txtDay = findViewById<TextView>(R.id.textDay)
                txtDay.text = "noData"
            }
            if (res.count > 0) {
                //set current date time

                //----------get other data\
                Log.d("In timeentry", "got data")

                //updating day spinner
                val spinner = findViewById<View>(R.id.spinnerDay) as Spinner
                // Create an ArrayAdapter using the string array and a default spinner layout
                val adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.day_array, android.R.layout.simple_spinner_item
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
                Log.d("In timeentry", strToday!!)
                when (strToday) {
                    "Mon" -> spinner.setSelection(0)
                    "Tue" -> spinner.setSelection(1)
                    "Wed" -> spinner.setSelection(2)
                    "Thu" -> spinner.setSelection(3)
                    "Fri" -> spinner.setSelection(4)
                    "Sat" -> spinner.setSelection(5)
                    "Sun" -> spinner.setSelection(6)
                }

                //updating period spinner
                val strPeriod = res.getString(1)
                Log.d("In timeentry", res.getString(1))
                ///adding periods to period spinner
                val spinnerPeriod = findViewById<View>(R.id.spinnerPeriod) as Spinner
                // Create an ArrayAdapter using the string array and a default spinner layout
                val adapterPeriod = ArrayAdapter.createFromResource(
                    this,
                    R.array.period_array, android.R.layout.simple_spinner_item
                )
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinnerPeriod.adapter = adapterPeriod
                Log.d("In timeentry", "period set")
                when (strPeriod) {
                    "1" -> spinnerPeriod.setSelection(0)
                    "2" -> spinnerPeriod.setSelection(1)
                    "3" -> spinnerPeriod.setSelection(2)
                    "4" -> spinnerPeriod.setSelection(3)
                    "5" -> spinnerPeriod.setSelection(4)
                    "6" -> spinnerPeriod.setSelection(5)
                    "7" -> spinnerPeriod.setSelection(6)
                    "8" -> spinnerPeriod.setSelection(7)
                    "9" -> spinnerPeriod.setSelection(8)
                    "10" -> spinnerPeriod.setSelection(9)
                }
                Log.d("In timeentry", "period set")

                //from time set
                txtFromTime = findViewById(R.id.textFromTimeShow)
                txtFromTime.setText(res.getString(2))
                // to time set set
                txtToTime = findViewById(R.id.textToTimeShow)
                txtToTime.setText(res.getString(3))


                //  showUpdateData();
                var strMyData: String?
                var strSpinner: String
                // Spinner Drop down elements
                val spinnerData: MutableList<String> = ArrayList()
                val spinnerTeacherData: MutableList<String> = ArrayList()
                val spinnerClassroomData: MutableList<String> = ArrayList()

                // show subject data
                strMyData = res.getString(4)
                Log.d("In timeentry", strMyData)
                val subid = db!!.getSubjectData(strMyData)
                Log.d("In timeentry", Integer.toString(subid))
                val spinnerSubject = findViewById<View>(R.id.spinnerSubject) as Spinner
                strSpinner = "subjects"
                val resSub = db!!.getSpinnerData(strSpinner)
                if (resSub.count == 0) {
                    Log.d("Spinners Data", "No Data Fetched")
                }
                Log.d("Spinners Data", Integer.toString(resSub.count))
                if (resSub.count > 0) {
                    i = 1
                    while (i <= resSub.count) {
                        spinnerData.add(resSub.getString(0))
                        Log.d("Spinners Data", resSub.getString(0))
                        Log.d("Spinners Data", Integer.toString(i))
                        resSub.moveToNext()
                        i++
                    }
                }
                // Creating adapter for spinner
                val spinnerSubadapter = ArrayAdapter(
                    this, android.R.layout.simple_spinner_item, spinnerData
                )


                // Drop down layout style - list view with radio button
                spinnerSubadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                // attaching data adapter to spinner
                spinnerSubject.adapter = spinnerSubadapter
                Log.d("sub Id", Integer.toString(subid))
                Log.d("In timeentry", "subject being set")
                val count = spinnerSubject.count
                Log.d("In timeentry", Integer.toString(count))
                spinnerSubject.setSelection(subid - 1)


                //show class data
                strMyData = res.getString(5)
                Log.d("classid", strMyData)
                val classid = db!!.getClassData(strMyData)
                Log.d("classid", Integer.toString(classid))
                val spinnerClassroom = findViewById<View>(R.id.spinnerClassroom) as Spinner
                strSpinner = "class"
                val resClass = db!!.getSpinnerData(strSpinner)
                if (resClass.count == 0) {
                    Log.d("Spinners Data", "No Data Fetched")
                }
                Log.d("Spinners Data", Integer.toString(resClass.count))
                if (resClass.count > 0) {
                    i = 1
                    while (i <= resClass.count) {
                        spinnerClassroomData.add(resClass.getString(0))
                        Log.d("Spinners Data", resClass.getString(0))
                        Log.d("Spinners Data", Integer.toString(i))
                        resClass.moveToNext()
                        i++
                    }
                }


                // Creating adapter for spinner
                val spinnerClassAdapter = ArrayAdapter(
                    this, android.R.layout.simple_spinner_item, spinnerClassroomData
                )

                // Drop down layout style - list view with radio button
                spinnerClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
                spinnerClassroom.adapter = spinnerClassAdapter
                Log.d("Classr Id", Integer.toString(classid))
                spinnerClassroom.setSelection(classid - 1)

                //show Teacher data
                strMyData = res.getString(7) + " " + res.getString(6)
                Log.d("TimeEntry", strMyData)
                val teacherId = db!!.getTeacherData(strMyData)
                Log.d("TimeEntry", Integer.toString(teacherId))
                val spinnerTeacher = findViewById<View>(R.id.spinnerTeacher) as Spinner
                strSpinner = "teacher"
                val resTeacher = db!!.getSpinnerData(strSpinner)
                if (resTeacher.count == 0) {
                    Log.d("Spinners Data", "No Data Fetched")
                }
                Log.d("Spinners Data", Integer.toString(res.count))
                if (resTeacher.count > 0) {
                    i = 1
                    while (i <= resTeacher.count) {
                        spinnerTeacherData.add(
                            resTeacher.getString(1) + " " + resTeacher.getString(
                                0
                            )
                        )
                        Log.d("Spinners Data", resTeacher.getString(1))
                        Log.d("Spinners Data", Integer.toString(i))
                        resTeacher.moveToNext()
                        i++
                    }
                }

                // Creating adapter for spinner
                val spinnerTeacherAdapter = ArrayAdapter(
                    this, android.R.layout.simple_spinner_item, spinnerTeacherData
                )

                // Drop down layout style - list view with radio button
                spinnerTeacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
                spinnerTeacher.adapter = spinnerTeacherAdapter
                spinnerTeacher.setSelection(teacherId - 1)
                btnSaveTT = findViewById<View>(R.id.buttonSaveTT) as Button
                btnSaveTT!!.text = "Update the Record"
            }
        }


        ////end edit


        //show Intitial data
        // showInitialData();

        ///show datetiempicker
        btnFromTime = findViewById<View>(R.id.btnFromTime) as Button
        btnFromTime!!.setOnClickListener { v ->
            Log.d("TT Entry", "From time button clicked")
            showTimePickerDialog(v)
        }

        //////showing timepicker
        txtView.setOnClickListener { view -> showTimePickerDialog(view) }
        txtTo.setOnClickListener { view -> showTimePickerDialog(view) }


        ///timetable button
        val spinner = findViewById<View>(R.id.spinnerDay) as Spinner
        btnSaveTT = findViewById<View>(R.id.buttonSaveTT) as Button
        btnSaveTT!!.setOnClickListener {
            Log.d("TT Entry", "TT Save button clicked")
            val spinnerPeriod = findViewById<View>(R.id.spinnerPeriod) as Spinner
            val strPeriod = spinnerPeriod.selectedItem.toString()
            Log.d("TT Entry", strPeriod)
            val strDay = spinner.selectedItem.toString()
            Log.d("TT Entry", strDay)
            fromtime = findViewById(R.id.textFromTimeShow)
            var strFromTime = fromtime.getText().toString()
            Log.d("TT Entry", strFromTime)
            totime = findViewById(R.id.textToTimeShow)
            var strToTime = totime.getText().toString()
            Log.d("TT Entry", strToTime)
            if (strFromTime === "Select Time ->") {
                Log.d("TT Entry", "inside worng time")
                strFromTime = "0000"
                strToTime = "0000"
            }
            if (strToTime === "Select Time ->") {
                Log.d("TT Entry", "inside worng time")
                strFromTime = "0000"
                strToTime = "0000"
            }
            if (strFromTime.toInt() > strToTime.toInt() || strFromTime.toInt() == strToTime.toInt()) {
                if (strFromTime.toInt() > strToTime.toInt()) {
                    val builder = AlertDialog.Builder(this@TimeTableEntry)
                    builder.setTitle("Check your timings!")
                    builder.setMessage("End time can not be less than Start Time!")

                    // add a button
                    builder.setNegativeButton("OK", null)

                    // create and show the alert dialog
                    val dialog = builder.create()
                    dialog.show()
                    showInitialData()
                }
                if (strFromTime.toInt() == strToTime.toInt()) {
                    val builder = AlertDialog.Builder(this@TimeTableEntry)
                    builder.setTitle("Check your timings!")
                    builder.setMessage("Please verify From and To time!")

                    // add a button
                    builder.setNegativeButton("OK", null)

                    // create and show the alert dialog
                    val dialog = builder.create()
                    dialog.show()
                    showInitialData()
                }
            } else {
                val spinnerSubject = findViewById<View>(R.id.spinnerSubject) as Spinner
                val strSubject = spinnerSubject.selectedItem.toString()
                Log.d("TT Entry", strSubject)
                val spinnerTeacher = findViewById<Spinner>(R.id.spinnerTeacher)
                val strTeacher = spinnerTeacher.selectedItem.toString()
                Log.d("TT Entry", strTeacher)
                val spinnerClassroom = findViewById<Spinner>(R.id.spinnerClassroom)
                val strClass = spinnerClassroom.selectedItem.toString()
                Log.d("TT Entry", strClass)
                val btnSaveText = "Save Time Table Entry"
                val tt: Boolean
                tt = if (btnSaveText == btnSaveTT!!.text) {
                    Log.d("TT Entry", "in insert")
                    db!!.insertTimeTable(
                        strPeriod,
                        strDay,
                        strFromTime,
                        strToTime,
                        strSubject,
                        strTeacher,
                        strClass,
                        this@TimeTableEntry
                    )
                } else {
                    Log.d("TT Entry", "in update")
                    Log.d("TT Entry", strPeriod)
                    db!!.UpdateTimeTable(
                        RecordId,
                        strPeriod,
                        strDay,
                        strFromTime,
                        strToTime,
                        strSubject,
                        strTeacher,
                        strClass,
                        this@TimeTableEntry
                    )
                }
                if (tt == true) {
                    Log.d("TT Entry", "insert success")
                    Toast.makeText(
                        applicationContext,
                        "Data Inserted/updated SuccessfullyI",
                        Toast.LENGTH_LONG
                    ).show()
                    if (btnSaveText == btnSaveTT!!.text) {
                        showInitialData()
                    } else {
                        openActivityViewMyDay(strDay)
                    }
                }
                if (tt == false) {
                    Log.d("TT Entry", "insert failed")
                    Toast.makeText(applicationContext, "Data insert failed!", Toast.LENGTH_LONG)
                        .show()
                    showInitialData()
                }
            }
        }
    }

    fun openActivityViewMyDay(day: String?) {
        val intent = Intent(this, ViewMyDay::class.java)
        intent.putExtra("key", "updateentry")
        intent.putExtra("day", day)
        startActivity(intent)
    }

    fun showTimePickerDialog(v: View) {
        // DialogFragment newFragment = new TimePickerFragment();
        // newFragment.show(getSupportFragmentManager(), "timePicker");
        timePickerDialog = TimePickerDialog(this@TimeTableEntry, { view, hourOfDay, minute ->
            var strHour: String
            strHour = Integer.toString(hourOfDay)
            var intLength = strHour.length
            if (intLength == 1) {
                strHour = "0$strHour"
            }
            var strMin: String
            strMin = Integer.toString(minute)
            intLength = strMin.length
            if (intLength == 1) {
                strMin = "0$strMin"
            }
            strHour = strHour + strMin
            Log.d("TimePicker", strHour)
            when (v.id) {
                R.id.textFromTimeShow -> {
                    txtFromTime = findViewById(R.id.textFromTimeShow)
                    txtFromTime.setText(strHour)
                    strHour = ""
                }

                R.id.textToTimeShow -> {
                    txtToTime = findViewById(R.id.textToTimeShow)
                    txtToTime.setText(strHour)
                    strHour = ""
                }

                R.id.btnFromTime -> {
                    txtFromTime = findViewById(R.id.textFromTimeShow)
                    txtFromTime.setText(strHour)
                    strHour = ""
                }

                R.id.btnToTime -> {
                    txtToTime = findViewById(R.id.textToTimeShow)
                    txtToTime.setText(strHour)
                    strHour = ""
                }
            }
        }, 0, 0, true)
        timePickerDialog!!.show()
    }

    fun showInitialData() {
        var strSpinner: String
        txtFromTime = findViewById(R.id.textFromTimeShow)
        txtFromTime.setText("Select Time ->")
        txtToTime = findViewById(R.id.textToTimeShow)
        txtToTime.setText("Select Time ->")
        ///adding Dayas to day spinner
        val spinner = findViewById<View>(R.id.spinnerDay) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.day_array, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter

        ///adding periods to period spinner
        val spinnerPeriod = findViewById<View>(R.id.spinnerPeriod) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapterPeriod = ArrayAdapter.createFromResource(
            this,
            R.array.period_array, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinnerPeriod.adapter = adapterPeriod

        // spinner.setPrompt("Select Day");
        //adding subject to Sujbject spinner
        db = DBManager(this)


        // Spinner Drop down elements
        val spinnerData: MutableList<String> = ArrayList()
        val spinnerTeacherData: MutableList<String> = ArrayList()
        val spinnerClassroomData: MutableList<String> = ArrayList()
        //assigning data to spinners


        //subjects
        val spinnerSubject = findViewById<View>(R.id.spinnerSubject) as Spinner
        strSpinner = "subjects"
        var res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerData.add(res.getString(0))
                Log.d("Spinners Data", res.getString(0))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }

        // Creating adapter for spinner
        val spinneradapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerData
        )

        // Drop down layout style - list view with radio button
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinnerSubject.adapter = spinneradapter


        //Teacherss
        val spinnerTeacher = findViewById<Spinner>(R.id.spinnerTeacher)
        strSpinner = "teacher"
        res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerTeacherData.add(res.getString(1) + " " + res.getString(0))
                Log.d("Spinners Data", res.getString(1))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }


        // Creating adapter for spinner
        val spinnerTeacherAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerTeacherData
        )

        // Drop down layout style - list view with radio button
        spinnerTeacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
        spinnerTeacher.adapter = spinnerTeacherAdapter

        //classrooms
        val spinnerClassroom = findViewById<Spinner>(R.id.spinnerClassroom)
        strSpinner = "class"
        res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerClassroomData.add(res.getString(0))
                Log.d("Spinners Data", res.getString(0))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }


        // Creating adapter for spinner
        val spinnerClassAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerClassroomData
        )

        // Drop down layout style - list view with radio button
        spinnerClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
        spinnerClassroom.adapter = spinnerClassAdapter
    }

    fun showUpdateData() {
        var strSpinner: String
        txtFromTime = findViewById(R.id.textFromTimeShow)
        txtFromTime.setText("Select Time ->")
        txtToTime = findViewById(R.id.textToTimeShow)
        txtToTime.setText("Select Time ->")
        ///adding Dayas to day spinner
        val spinner = findViewById<View>(R.id.spinnerDay) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.day_array, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter

        ///adding periods to period spinner
        val spinnerPeriod = findViewById<View>(R.id.spinnerPeriod) as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapterPeriod = ArrayAdapter.createFromResource(
            this,
            R.array.period_array, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinnerPeriod.adapter = adapterPeriod

        // spinner.setPrompt("Select Day");
        //adding subject to Sujbject spinner
        db = DBManager(this)


        // Spinner Drop down elements
        val spinnerData: MutableList<String> = ArrayList()
        val spinnerTeacherData: MutableList<String> = ArrayList()
        val spinnerClassroomData: MutableList<String> = ArrayList()
        //assigning data to spinners


        //subjects
        val spinnerSubject = findViewById<View>(R.id.spinnerSubject) as Spinner
        strSpinner = "subjects"
        var res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerData.add(res.getString(0))
                Log.d("Spinners Data", res.getString(0))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }

        // Creating adapter for spinner
        val spinneradapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerData
        )

        // Drop down layout style - list view with radio button
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        spinnerSubject.adapter = spinneradapter


        //Teacherss
        val spinnerTeacher = findViewById<Spinner>(R.id.spinnerTeacher)
        strSpinner = "teacher"
        res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerTeacherData.add(res.getString(1) + " " + res.getString(0))
                Log.d("Spinners Data", res.getString(1))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }


        // Creating adapter for spinner
        val spinnerTeacherAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerTeacherData
        )

        // Drop down layout style - list view with radio button
        spinnerTeacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
        spinnerTeacher.adapter = spinnerTeacherAdapter

        //classrooms
        val spinnerClassroom = findViewById<Spinner>(R.id.spinnerClassroom)
        strSpinner = "class"
        res = db!!.getSpinnerData(strSpinner)
        if (res.count == 0) {
            Log.d("Spinners Data", "No Data Fetched")
        }
        Log.d("Spinners Data", Integer.toString(res.count))
        if (res.count > 0) {
            i = 1
            while (i <= res.count) {
                spinnerClassroomData.add(res.getString(0))
                Log.d("Spinners Data", res.getString(0))
                Log.d("Spinners Data", Integer.toString(i))
                res.moveToNext()
                i++
            }
        }


        // Creating adapter for spinner
        val spinnerClassAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, spinnerClassroomData
        )

        // Drop down layout style - list view with radio button
        spinnerClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// attaching data adapter to spinner
        spinnerClassroom.adapter = spinnerClassAdapter
    }
}
