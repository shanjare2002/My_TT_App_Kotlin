package com.example.shan.my_tt_app

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBManager(context: Context?) : SQLiteOpenHelper(context, "SchoolDB10.db", null, 1) {
    var i = 0

    init {
        Log.d("CREATE", "SchoolDB10.0")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table user(email text primary key, password text)")
        Log.d("TESTDB", "Create table")

        //block table
        strTableSql =
            "CREATE TABLE " + Table_Block + "(" + COL_BlockId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_BLockName + " TEXT," + COL_BLockComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table Block Created")

        //class table
        strTableSql =
            "CREATE TABLE " + Table_Class + "(" + COL_ClassId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_ClassName + " TEXT," + COL_Class_BlockId + " INTEGER," + COL_ClassComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table Class Created")

        //Table Teacher
        strTableSql =
            "CREATE TABLE " + Table_Teacher + "(" + COL_TeacherId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TeacherShortName + " TEXT," + COL_FirstName + " Text," + COL_LastName + " Text," + COL_Email + " Text," + COL_TeacherComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table Teacher Created")

        //Table Subject
        strTableSql =
            "CREATE TABLE " + Table_Subject + "(" + COL_SubjectId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SubjectName + " TEXT," + COL_SubjectComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table Subject Created")
        //Table DayofWeek
        strTableSql =
            "CREATE TABLE " + Table_Day_of_the_week + "(" + COL_DayId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_DayName + " TEXT," + COL_DayComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table DayOfweek Created")
        //Table TimeTable
        strTableSql =
            "CREATE TABLE " + Table_TimeTable + "(" + COL_RecordId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PeriodNumber + " TEXT," + COL_TT_DayId + " TEXT," + COL_FromTime + " TEXT," + COL_ToTime + " TEXT," + COL_TT_SubjectId + " TEXT," + COL_TT_TeacherId + " TEXT," + COL_TT_ClassId + " TEXT," + COL_HomeworkBln + " INTEGER," + COL_HomeworkDetails + " TEXT," + COL_SplInstr + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table TimeTable Created")
        //Table Holiday
        strTableSql =
            "CREATE TABLE " + Table_Holiday + "(" + COL_HolidayId + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_HolidayName + " TEXT," + COL_HolidayDate + " TEXT," + COL_HolidayComments + " TEXT" + ")"
        db.execSQL(strTableSql)
        Log.d("DB", "Table HolidayCreated")
    }

    override fun onUpgrade(db: SQLiteDatabase, OldVersion: Int, NewVersion: Int) {
        Log.d("TESTDB", "Upgrading application database")
        db.execSQL("drop table if exists " + Table_Block)
        db.execSQL("drop table if exists " + Table_Class)
        db.execSQL("drop table if exists " + Table_Teacher)
        db.execSQL("drop table if exists " + Table_Subject)
        db.execSQL("drop table if exists " + Table_TimeTable)
        db.execSQL("drop table if exists " + Table_Day_of_the_week)
        db.execSQL("drop table if exists " + Table_Holiday)
    }

    fun insert(): Boolean {
        val db = this.readableDatabase
        //Block data insert
        val contentvalues = ContentValues()
        contentvalues.put(COL_BLockName, "A Block")
        contentvalues.put(COL_BLockComments, "This is A Block")
        ins = db.insert(Table_Block, null, contentvalues)
        if (ins == -1L) Log.d("DB", "A  data insert failed") else Log.d(
            "DB",
            "A Block data insert success"
        )
        contentvalues.clear()
        contentvalues.put(COL_BLockName, "B Block")
        contentvalues.put(COL_BLockComments, "This is B Block")
        ins = db.insert(Table_Block, null, contentvalues)
        if (ins == -1L) Log.d("DB", "B block data insert failed") else Log.d(
            "DB",
            "B Block data insert success"
        )
        contentvalues.clear()
        contentvalues.put(COL_BLockName, "C Block")
        contentvalues.put(COL_BLockComments, "This is C Block")
        ins = db.insert(Table_Block, null, contentvalues)
        if (ins == -1L) Log.d("DB", "c block data insert failed") else Log.d(
            "DB",
            "c Block data insert success"
        )
        contentvalues.clear()
        contentvalues.put(COL_BLockName, "D Block")
        contentvalues.put(COL_BLockComments, "This is D Block")
        ins = db.insert(Table_Block, null, contentvalues)
        if (ins == -1L) Log.d("DB", "D block data insert failed") else Log.d(
            "DB",
            "D Block data insert success"
        )
        contentvalues.clear()
        contentvalues.put(COL_BLockName, "E Block")
        contentvalues.put(COL_BLockComments, "This is E Block")
        ins = db.insert(Table_Block, null, contentvalues)
        if (ins == -1L) Log.d("DB", "E block data insert failed") else Log.d(
            "DB",
            "E Block data insert success"
        )
        //Class data insert
        contentvalues.clear()
        contentvalues.put(COL_ClassName, "D205")
        contentvalues.put(COL_Class_BlockId, "4")
        contentvalues.put(COL_ClassComments, "This is D205 class ")
        ins = db.insert(Table_Class, null, contentvalues)
        if (ins == -1L) Log.d("DB", "D205 class data insert failed") else Log.d(
            "DB",
            "D205 data insert success"
        )
        //Class data insert
        contentvalues.clear()
        contentvalues.put(COL_ClassName, "E103")
        contentvalues.put(COL_Class_BlockId, "5")
        contentvalues.put(COL_ClassComments, "This is E103 class ")
        ins = db.insert(Table_Class, null, contentvalues)
        if (ins == -1L) Log.d("DB", "E103 class data insert failed") else Log.d(
            "DB",
            "E103 data insert success"
        )
        //Class data insert
        contentvalues.clear()
        contentvalues.put(COL_ClassName, "D201")
        contentvalues.put(COL_Class_BlockId, "4")
        contentvalues.put(COL_ClassComments, "This is D201 class ")
        ins = db.insert(Table_Class, null, contentvalues)
        if (ins == -1L) Log.d("DB", "D201 class data insert failed") else Log.d(
            "DB",
            "D201 data insert success"
        )
        //Class data insert
        contentvalues.clear()
        contentvalues.put(COL_ClassName, "A207")
        contentvalues.put(COL_Class_BlockId, "1")
        contentvalues.put(COL_ClassComments, "This is A207 class ")
        ins = db.insert(Table_Class, null, contentvalues)
        if (ins == -1L) Log.d("DB", "A207 class data insert failed") else Log.d(
            "DB",
            "A207 data insert success"
        )
        //Teacher data insert
        contentvalues.clear()
        contentvalues.put(COL_TeacherShortName, "Haa")
        contentvalues.put(COL_FirstName, "Gloria")
        contentvalues.put(COL_LastName, "Huerta")
        contentvalues.put(COL_Email, "g.huerta@ishthehague.nl")
        contentvalues.put(COL_TeacherComments, "ISH teacher")
        ins = db.insert(Table_Teacher, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Teacher 1 data insert failed") else Log.d(
            "DB",
            "Teacher 1 data insert success"
        )
        //Teacher data insert
        contentvalues.clear()
        contentvalues.put(COL_TeacherShortName, "Gyc")
        contentvalues.put(COL_FirstName, "Christopher")
        contentvalues.put(COL_LastName, "Gray")
        contentvalues.put(COL_Email, "c.gray@ishthehague.nl")
        contentvalues.put(COL_TeacherComments, "ISH teacher")
        ins = db.insert(Table_Teacher, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Teacher 2 data insert failed") else Log.d(
            "DB",
            "Teacher 2 data insert success"
        )
        //Teacher data insert
        contentvalues.clear()
        contentvalues.put(COL_TeacherShortName, "Bsj")
        contentvalues.put(COL_FirstName, "Jane")
        contentvalues.put(COL_LastName, "Barnes")
        contentvalues.put(COL_Email, "j.barnes@ishthehague.nl")
        contentvalues.put(COL_TeacherComments, "ISH teacher")
        ins = db.insert(Table_Teacher, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Teacher 3 data insert failed") else Log.d(
            "DB",
            "Teacher 3 data insert success"
        )
        //Teacher data insert
        contentvalues.clear()
        contentvalues.put(COL_TeacherShortName, "Eco")
        contentvalues.put(COL_FirstName, "Economics")
        contentvalues.put(COL_LastName, "Teacher")
        contentvalues.put(COL_Email, "e.teacher@ishthehague.nl")
        contentvalues.put(COL_TeacherComments, "ISH teacher")
        ins = db.insert(Table_Teacher, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Teacher 4 data insert failed") else Log.d(
            "DB",
            "Teacher 4 data insert success"
        )
        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Maths(EC)")
        contentvalues.put(COL_SubjectComments, "Maths EC")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Maths sub data insert failed") else Log.d(
            "DB",
            "Maths insert success"
        )
        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Chemistry")
        contentvalues.put(COL_SubjectComments, "Chem ")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Chem sub data insert failed") else Log.d(
            "DB",
            "Chem insert success"
        )
        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Physics")
        contentvalues.put(COL_SubjectComments, "Physics")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Phy sub data insert failed") else Log.d(
            "DB",
            "Phy insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Digital Design")
        contentvalues.put(COL_SubjectComments, "Digital")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "D Des sub data insert failed") else Log.d(
            "DB",
            "D Des insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Dutch Phase1")
        contentvalues.put(COL_SubjectComments, "Dutch")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Dutch sub data insert failed") else Log.d(
            "DB",
            "Dutch insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "English Lang")
        contentvalues.put(COL_SubjectComments, "English Lang")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "English lang sub data insert failed") else Log.d(
            "DB",
            "English insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "Economics")
        contentvalues.put(COL_SubjectComments, "Economics")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Eco sub data insert failed") else Log.d(
            "DB",
            "Eco insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "History")
        contentvalues.put(COL_SubjectComments, "History")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "history sub data insert failed") else Log.d(
            "DB",
            "history insert success"
        )

        //Subject data insert
        contentvalues.clear()
        contentvalues.put(COL_SubjectName, "PHE")
        contentvalues.put(COL_SubjectComments, "Physical health Edu")
        ins = db.insert(Table_Subject, null, contentvalues)
        if (ins == -1L) Log.d("DB", "PHE sub data insert failed") else Log.d(
            "DB",
            "PHE insert success"
        )

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Monday")
        contentvalues.put(COL_DayComments, "Monday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Mon data insert failed") else Log.d("DB", "Mon insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Tuesday")
        contentvalues.put(COL_DayComments, "Tuesday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Tue data insert failed") else Log.d("DB", "Tue insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Wednesday")
        contentvalues.put(COL_DayComments, "Wednesday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Wed data insert failed") else Log.d("DB", "Wed insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Thursday")
        contentvalues.put(COL_DayComments, "Thursday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Thu data insert failed") else Log.d("DB", "Thu insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Friday")
        contentvalues.put(COL_DayComments, "Friday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Fri data insert failed") else Log.d("DB", "Fri insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Saturday")
        contentvalues.put(COL_DayComments, "Saturday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Sat data insert failed") else Log.d("DB", "Sat insert success")

        //Day data insert
        contentvalues.clear()
        contentvalues.put(COL_DayName, "Sunday")
        contentvalues.put(COL_DayComments, "Sunday")
        ins = db.insert(Table_Day_of_the_week, null, contentvalues)
        if (ins == -1L) Log.d("DB", "Sun data insert failed") else Log.d("DB", "Sun insert success")

        //Holiday data insert
        contentvalues.clear()
        contentvalues.put(COL_HolidayName, "Dummy")
        contentvalues.put(COL_HolidayDate, "31/10/2018")
        contentvalues.put(COL_HolidayComments, "Dummy Holiday")
        ins = db.insert(Table_Holiday, null, contentvalues)
        if (ins == -1L) Log.d("DB", "holiday data insert failed") else Log.d(
            "DB",
            "holiday insert success"
        )

        //Timetable data insert
        contentvalues.clear()
        contentvalues.put(COL_DayId, "1")
        contentvalues.put(COL_FromTime, "0915")
        contentvalues.put(COL_ToTime, "1105")
        contentvalues.put(COL_TT_SubjectId, "3")
        contentvalues.put(COL_TT_TeacherId, "1")
        contentvalues.put(COL_TT_ClassId, "1")
        contentvalues.put(COL_HomeworkBln, "1")
        contentvalues.put(COL_HomeworkDetails, "Motion Study")
        contentvalues.put(COL_SplInstr, "Get graph paper")
        //   ins = db.insert(Table_TimeTable, null, contentvalues);
        //   if(ins==-1) Log.d("DB","Timetable data insert failed");
        //   else Log.d("DB","TimeTable insert success");

        //Timetable data insert
        contentvalues.clear()
        contentvalues.put(COL_DayId, "1")
        contentvalues.put(COL_FromTime, "1150")
        contentvalues.put(COL_ToTime, "1235")
        contentvalues.put(COL_TT_SubjectId, "2")
        contentvalues.put(COL_TT_TeacherId, "2")
        contentvalues.put(COL_TT_ClassId, "2")
        //   ins = db.insert(Table_TimeTable, null, contentvalues);
        //   if(ins==-1) Log.d("DB","Timetable2 data insert failed");
        //   else Log.d("DB","TimeTable2 insert success");

        //Timetable data insert
        contentvalues.clear()
        contentvalues.put(COL_DayId, "1")
        contentvalues.put(COL_FromTime, "1320")
        contentvalues.put(COL_ToTime, "1505")
        contentvalues.put(COL_TT_SubjectId, "6")
        contentvalues.put(COL_TT_TeacherId, "3")
        contentvalues.put(COL_TT_ClassId, "3")
        contentvalues.put(COL_HomeworkBln, "0")
        contentvalues.put(COL_HomeworkDetails, "")
        contentvalues.put(COL_SplInstr, "complete rationale")
        //   ins = db.insert(Table_TimeTable, null, contentvalues);
        // if(ins==-1) Log.d("DB","Timetable2 data insert failed");
        //   else Log.d("DB","TimeTable3 insert success");
        return if (ins == -1L) false else true
    }

    //method for to get data fro three events
    fun getAllData(DayId: Int): Cursor {
        Log.d("TESTDB", "in getalldata")
        val db = this.readableDatabase
        val res = db.rawQuery(
            "Select RecordId,PeriodNbr,FromTime,ToTime, SubjectName, ClassName from TimeTable INNER JOIN Subject On Subject.SubjectId = TimeTable.SubjectId INNER JOIN Class ON Class.ClassId=TimeTable.ClassId where dayid=$DayId order by periodnbr",
            null
        )
        if (res.count > 0) {
            res.moveToNext()
            Log.d("TESTDB", " get data success")
            val log = res.getString(0)
            val log1 = res.getString(1)
            val log2 = res.getString(2)
            val log3 = res.getString(3)
            val log4 = res.getString(4)
            val log5 = res.getString(5)
            Log.d("TESTDB", log)
            Log.d("TESTDB", log1)
            Log.d("TESTDB", log2)
            Log.d("TESTDB", log3)
            Log.d("TESTDB", log4)
            Log.d("TESTDB", log4)
        }
        return res
    }

    // method to get event details
    fun getEventData(RecordId: Int): Cursor {
        val db = this.readableDatabase
        val res = db.rawQuery(
            "Select RecordId,Periodnbr,FromTime,ToTime,SubjectName,ClassName,FirstName,LastName,TeacherShortName from TimeTable INNER JOIN Subject On Subject.SubjectId = TimeTable.SubjectId INNER JOIN Class ON Class.ClassId=TimeTable.ClassId INNER JOIN Teacher ON Teacher.TeacherId=TimeTable.TeacherId where RecordId=$RecordId",
            null
        )
        if (res.count > 0) {
            res.moveToNext()
            val strLog = res.getString(0)
            Log.d("TESTDB", strLog)
        }
        Log.d("TESTDB", " returning res")
        return res
    }

    // method to get event details
    fun getSpinnerData(spinnerName: String): Cursor {
        val db = this.readableDatabase
        var res = db.rawQuery("Select subjectname from subject", null)
        if (spinnerName === "subjects") {
            res = db.rawQuery("Select subjectname from subject order by subjectname", null)
            Log.d("spinner data", " get subject data success")
            res.moveToNext()
        }
        if (spinnerName === "teacher") {
            res = db.rawQuery("Select firstname,lastname from teacher order by lastname", null)
            Log.d("spinner data", " get teachers data success")
            res.moveToNext()
        }
        if (spinnerName === "class") {
            res = db.rawQuery("Select classname from class order by classname", null)
            Log.d("spinner data", " get class data success")
            res.moveToNext()
        }
        return res
    }

    fun getSubjectData(strSubject: String): Int {
        var i: Int
        Log.d("DB Manager", " gIn get subject")
        val db = this.readableDatabase
        val res =
            db.rawQuery("Select subjectid,subjectname from subject order by subjectname", null)
        Log.d("DB Manager", " In get subject, data set")
        Log.d("DB Manager", Integer.toString(res.count))
        Log.d("DB Manager", strSubject)
        res.moveToNext()
        i = 1
        while (i <= res.count) {
            Log.d("DB Manager1", res.getString(1))
            Log.d("DB Manager", strSubject)
            if (strSubject == res.getString(1)) {
                Log.d("DB Manager", " Inside If")
                return i
            }
            res.moveToNext()
            i++
        }
        return 0
    }

    fun getTeacherData(strTeacher: String): Int {
        var i: Int
        Log.d("DB teacher", " Inside get teacher data")
        val db = this.readableDatabase
        Log.d("DB teacher", " Inside get teacher data")
        val res = db.rawQuery("Select firstname,lastname from teacher order by lastname", null)
        Log.d("DB teacher", " query successfull")
        Log.d("DB teacher", Integer.toString(res.count))
        res.moveToNext()
        i = 1
        while (i <= res.count) {
            Log.d("DB teacher", res.getString(1) + " " + res.getString(0))
            if (strTeacher == res.getString(1) + " " + res.getString(0)) {
                Log.d("DB teacher", " Inside If")
                return i
            }
            res.moveToNext()
            i++
        }
        return 0
    }

    fun getClassData(strClass: String): Int {
        var i: Int
        val db = this.readableDatabase
        val res = db.rawQuery("Select classid,classname from class order by classname", null)
        res.moveToNext()
        Log.d("DB Manager-Class", res.getString(1))
        Log.d("DB Manager-Class", strClass)
        i = 1
        while (i <= res.count) {
            if (strClass == res.getString(1)) {
                Log.d("DB Manager", "Inside if in calss")
                return i
            }
            res.moveToNext()
            i++
        }
        return 0
    }

    fun insertTimeTable(
        strPeriod: String,
        strday: String,
        fromtime: String,
        totime: String,
        subject: String,
        teacher: String,
        strclass: String,
        context: Context?
    ): Boolean {
        Log.d("TT data", strday)
        val db = this.readableDatabase
        Log.d("TT data", "got db")
        //get dayid from Day name from dayofweek table
        var resTT = db.rawQuery("Select dayid from dayofweek where dayname='$strday'", null)
        Log.d("TT data", "Data selected")
        resTT.moveToNext()
        Log.d("TT data", " Day id " + resTT.getString(0))
        val intDayId = resTT.getInt(0)
        resTT.close()

        //checking exisitng entry n sane time poeriod
        val resCheckDup = db.rawQuery("Select * from timetable where dayid='$intDayId'", null)
        Log.d("TT data", "Dup selected")
        resCheckDup.moveToNext()
        i = 1
        while (i <= resCheckDup.count) {
            val strFromTime = resCheckDup.getString(3)
            val strToTime = resCheckDup.getString(4)
            val intFromTime = strFromTime.toInt()
            val intToTime = strToTime.toInt()
            val fromtimeRcvd = fromtime.toInt()
            val totimeRcvd = totime.toInt()
            val PerNbr = resCheckDup.getString(1)

            //validating period number already created\\\
            Log.d("check dup again", strPeriod)
            Log.d("checkdup again ", PerNbr)
            if (PerNbr.toInt() == strPeriod.toInt()) {
                Log.d("TT data", "Period duplicate")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Entry")
                builder.setMessage("You already have same period number for this day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }

            //validating from time already within a period selected
            if (fromtimeRcvd >= intFromTime && fromtimeRcvd <= intToTime) {
                Log.d("TT data", "Dup entry")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Emtry")
                builder.setMessage("You already have a period added for this time and day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }
            //validating to time already within a period selected
            if (totimeRcvd >= intFromTime && totimeRcvd <= intToTime) {
                Log.d("TT data", "To time Dup entry")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Entry")
                builder.setMessage("You already have a period added for this time and day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }
            resCheckDup.moveToNext()
            i++
        }


        // int intDayId = resCheckDup.getInt(0);
        resCheckDup.close()

        //get Subjectid from subject name from Subject table
        resTT = db.rawQuery("Select subjectid from subject where subjectname='$subject'", null)
        resTT.moveToNext()
        Log.d("TT data", " Subject id " + resTT.getString(0))
        val intSubjectId = resTT.getInt(0)
        resTT.close()

        //get Teacherid from teacher name from teacher table
        // split the array using ':' as a delimiter
        val teachername = teacher.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        Log.d("TT Entry", teachername[0])
        Log.d("TT Entry", teachername[1])
        resTT = db.rawQuery(
            "Select teacherid from teacher where firstname='" + teachername[1] + "' and lastname='" + teachername[0] + "'",
            null
        )
        resTT.moveToNext()
        Log.d("TT data", "Teacher id " + resTT.getString(0))
        val intTeacherId = resTT.getInt(0)
        resTT.close()

        //get Classid from class name from class table
        resTT = db.rawQuery("Select classid from class where classname='$strclass'", null)
        resTT.moveToNext()
        Log.d("TT data", " Class id " + resTT.getString(0))
        val intClassId = resTT.getInt(0)
        resTT.close()


        //Block data insert
        val contentvalues = ContentValues()
        contentvalues.put(COL_PeriodNumber, strPeriod)
        contentvalues.put(COL_DayId, intDayId)
        contentvalues.put(COL_FromTime, fromtime)
        contentvalues.put(COL_ToTime, totime)
        contentvalues.put(COL_SubjectId, intSubjectId)
        contentvalues.put(COL_TeacherId, intTeacherId)
        contentvalues.put(COL_ClassId, intClassId)
        ins = db.insert(Table_TimeTable, null, contentvalues)
        if (ins == -1L) Log.d("DB", "A  data insert failed") else Log.d(
            "DB",
            "Timetable entry data insert success"
        )
        return if (ins == -1L) false else true
    }

    fun UpdateTimeTable(
        recordId: Int,
        strPeriod: String,
        strday: String,
        fromtime: String,
        totime: String,
        subject: String,
        teacher: String,
        strclass: String,
        context: Context?
    ): Boolean {
        Log.d("TT data", strday)
        val db = this.readableDatabase
        Log.d("TT data", "got db")
        //get dayid from Day name from dayofweek table
        var resTT = db.rawQuery("Select dayid from dayofweek where dayname='$strday'", null)
        Log.d("TT data", "Data selected")
        resTT.moveToNext()
        Log.d("TT data", " Day id " + resTT.getString(0))
        val intDayId = resTT.getInt(0)
        resTT.close()

        //checking exisitng entry n sane time poeriod
        Log.d("TT data", Integer.toString(recordId))
        val resCheckDup = db.rawQuery(
            "Select * from timetable where dayid='$intDayId' and recordid<>'$recordId'",
            null
        )
        Log.d("TT data", "Dup selected")
        Log.d("TT data", Integer.toString(resCheckDup.count))
        resCheckDup.moveToNext()
        i = 1
        while (i <= resCheckDup.count) {
            val strFromTime = resCheckDup.getString(3)
            val strToTime = resCheckDup.getString(4)
            val intFromTime = strFromTime.toInt()
            val intToTime = strToTime.toInt()
            val fromtimeRcvd = fromtime.toInt()
            val totimeRcvd = totime.toInt()
            val PerNbr = resCheckDup.getString(1)

            //validating period number already created\\\
            Log.d("check dup again", strPeriod)
            Log.d("checkdup again ", PerNbr)
            if (PerNbr.toInt() == strPeriod.toInt()) {
                Log.d("TT data", "Period duplicate")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Entry")
                builder.setMessage("You already have same period number for this day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }

            //validating from time already within a period selected
            if (fromtimeRcvd >= intFromTime && fromtimeRcvd <= intToTime) {
                Log.d("TT data", "Dup entry")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Emtry")
                builder.setMessage("You already have a period added for this time and day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }
            //validating to time already within a period selected
            if (totimeRcvd >= intFromTime && totimeRcvd <= intToTime) {
                Log.d("TT data", "To time Dup entry")
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Duplicate Period Entry")
                builder.setMessage("You already have a period added for this time and day!")

                // add a button
                builder.setPositiveButton("OK", null)

                // create and show the alert dialog
                val dialog = builder.create()
                dialog.show()
                return false
            }
            resCheckDup.moveToNext()
            i++
        }


        // int intDayId = resCheckDup.getInt(0);
        resCheckDup.close()

        //get Subjectid from subject name from Subject table
        resTT = db.rawQuery("Select subjectid from subject where subjectname='$subject'", null)
        resTT.moveToNext()
        Log.d("TT data", " Subject id " + resTT.getString(0))
        val intSubjectId = resTT.getInt(0)
        resTT.close()

        //get Teacherid from teacher name from teacher table
        // split the array using ':' as a delimiter
        val teachername = teacher.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        Log.d("TT Entry", teachername[0])
        Log.d("TT Entry", teachername[1])
        resTT = db.rawQuery(
            "Select teacherid from teacher where firstname='" + teachername[1] + "' and lastname='" + teachername[0] + "'",
            null
        )
        resTT.moveToNext()
        Log.d("TT data", "Teacher id " + resTT.getString(0))
        val intTeacherId = resTT.getInt(0)
        resTT.close()

        //get Classid from class name from class table
        resTT = db.rawQuery("Select classid from class where classname='$strclass'", null)
        resTT.moveToNext()
        Log.d("TT data", " Class id " + resTT.getString(0))
        val intClassId = resTT.getInt(0)
        resTT.close()


        //Block data insert
        val contentvalues = ContentValues()
        contentvalues.put(COL_PeriodNumber, strPeriod)
        contentvalues.put(COL_DayId, intDayId)
        contentvalues.put(COL_FromTime, fromtime)
        contentvalues.put(COL_ToTime, totime)
        contentvalues.put(COL_SubjectId, intSubjectId)
        contentvalues.put(COL_TeacherId, intTeacherId)
        contentvalues.put(COL_ClassId, intClassId)
        val strWhereCaluse = "recordid=$recordId"
        ins = db.update(Table_TimeTable, contentvalues, strWhereCaluse, null).toLong()
        if (ins == -1L) Log.d("DB", "A  data update failed") else Log.d(
            "DB",
            "Timetable entry data update success"
        )
        return if (ins == -1L) false else true
    }

    companion object {
        //Creating Table Block of the database
        const val Table_Block = "Block"
        const val COL_BlockId = "BlockId"
        const val COL_BLockName = "BlockName"
        const val COL_BLockComments = "BlockComments"

        //Creating Table Class of the database
        const val Table_Class = "Class"
        const val COL_ClassId = "ClassId"
        const val COL_ClassName = "ClassName"
        const val COL_Class_BlockId = "BlockId"
        const val COL_ClassComments = "ClassComments"

        // Creating Table Teacher of the database
        const val Table_Teacher = "Teacher"
        const val COL_TeacherId = "TeacherId"
        const val COL_TeacherShortName = "TeacherShortName"
        const val COL_FirstName = "FirstName"
        const val COL_LastName = "LastName"
        const val COL_Email = "EmailId"
        const val COL_TeacherComments = "TeacherComments"

        // Creating Table Subject of the database
        const val Table_Subject = "Subject"
        const val COL_SubjectId = "SubjectId"
        const val COL_SubjectName = "SubjectName"
        const val COL_SubjectComments = "SubjectComments"

        // Creating Table Day of the week
        const val Table_Day_of_the_week = "DayofWeek"
        const val COL_DayId = "DayId"
        const val COL_DayName = "DayName"
        const val COL_DayComments = "DayComments"

        // Creating Table TimeTable
        const val Table_TimeTable = "TimeTable"
        const val COL_RecordId = "RecordId"
        const val COL_PeriodNumber = "PeriodNbr"
        const val COL_TT_DayId = "DayId"
        const val COL_FromTime = "FromTime"
        const val COL_ToTime = "ToTime"
        const val COL_TT_SubjectId = "SubjectId"
        const val COL_TT_TeacherId = "TeacherId"
        const val COL_TT_ClassId = "ClassId"
        const val COL_HomeworkBln = "Homework"
        const val COL_HomeworkDetails = "HomeworkDetails"
        const val COL_SplInstr = "SpecialInstr"
        var strTableSql: String? = null

        // Creating Holiday
        const val Table_Holiday = "Holiday"
        const val COL_HolidayId = "HolidayId"
        const val COL_HolidayName = "HolidayName"
        const val COL_HolidayDate = "HolidayDate"
        const val COL_HolidayComments = "HolidayComments"
        var ins: Long = 0
    }
}
