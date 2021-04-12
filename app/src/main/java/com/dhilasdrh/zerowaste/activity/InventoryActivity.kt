package com.dhilasdrh.zerowaste.activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.adapter.TodoAdapter
import com.dhilasdrh.zerowaste.databinding.ActivityInventoryBinding
import com.dhilasdrh.zerowaste.databinding.FragmentTodoBinding
import com.dhilasdrh.zerowaste.model.Todo
import com.dhilasdrh.zerowaste.util.AlarmReceiver
import com.dhilasdrh.zerowaste.util.CustomConfirmDialog
import com.dhilasdrh.zerowaste.util.FormDialog
import com.dhilasdrh.zerowaste.utils.Commons
import com.dhilasdrh.zerowaste.viewmodel.TodoViewModel

class InventoryActivity : AppCompatActivity() {
    companion object{
        var isSortByDateCreated = true
    }

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var binding: ActivityInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInventoryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

       /* val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager

        todoAdapter = TodoAdapter(this){ todo, _ ->
            val options = resources.getStringArray(R.array.options_todo)
            Commons.showSelector(this, "Select action", options) { _, i ->
                when (i) {
                    0 -> showDetailsDialog(todo)
                    1 -> showEditDialog(todo)
                    2 -> showDeleteDialog(todo)
                }
            }
        }

        binding.recyclerview.adapter = todoAdapter*/

        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

      /*  binding.swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        binding.fab.setOnClickListener {
            showInsertDialog()
        }*/

        alarmReceiver = AlarmReceiver()
    }

    /*override fun onResume() {
        super.onResume()
        observeData()
    }

    private fun observeData(){
        todoViewModel.getTodos()?.observe(this, Observer {
            todoAdapter.setTodoList(it)
           // setProgressbarVisibility(false)
        })
    }

    private fun refreshData(){
       // setProgressbarVisibility(true)
        observeData()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showInsertDialog(){
        val view = FragmentTodoBinding.inflate(LayoutInflater.from(this))
        var reminderTimeCategory = 0

        view.inputDueDate.setOnClickListener {
            Commons.showDatePickerDialog(this, view.inputDueDate)
        }

        view.inputTime.setOnClickListener {
            Commons.showTimePickerDialog(this, view.inputTime)
        }

        view.inputRemindTime.setOnClickListener {
            val options = resources.getStringArray(R.array.options_reminder)
            Commons.showSelector(this, "Select reminder time", options) { _, i ->
                view.inputRemindTime.setText(options[i])
                reminderTimeCategory = i
            }
        }

        view.inputRemindMe.setOnClickListener {
            view.inputRemindTime.visibility = if (view.inputRemindMe.isChecked) View.VISIBLE else View.GONE
        }

        if (view.inputRemindMe.isChecked)
            view.inputRemindTime.visibility = View.VISIBLE

        val dialogTitle = "Add Food Stock"
        val toastMessage = "Data has been added successfully"
        val failAlertMessage = "Please fill all the required fields"

        FormDialog(this, dialogTitle, view){
            val title = view.inputTitle.text.toString().trim()
            val date = view.inputDueDate.text.toString().trim()
            val time = view.inputTime.text.toString().trim()
            val quantity = view.inputQuantity.text.toString()

            val remindMe = view.inputRemindMe.isChecked

            if (title == "" || date == "" || time == "") {
                AlertDialog.Builder(this).setMessage(failAlertMessage).setCancelable(false)
                    .setPositiveButton("OK") { dialogInterface, _ ->
                        dialogInterface.cancel()
                    }.create().show()
            } else {
                val parsedDate = Commons.convertStringToDate("dd/MM/yy",date)
                val dueDate = Commons.formatDate(parsedDate, "dd/MM/yy")

                val currentDate = Commons.getCurrentTime()
                val dateCreated =Commons.formatDate(currentDate, "dd/MM/yy HH:mm:ss")

                val todo = Todo(
                    title = title,
                    quantity = quantity,
                    dateCreated = dateCreated,
                    dateUpdated = dateCreated,
                    dueDate = dueDate,
                    dueTime = time,
                    remindMe = remindMe,
                    remindTime = reminderTimeCategory
                )

                todoViewModel.insertTodo(todo)

                if (remindMe)
                 setReminder(reminderTimeCategory, title, dueDate, time)

                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showEditDialog(todo: Todo) {
        val view = FragmentTodoBinding.inflate(LayoutInflater.from(this))
        var reminderTimeCategory = todo.remindTime

        view.inputDueDate.setOnClickListener {
            Commons.showDatePickerDialog(this, view.inputDueDate)
        }

        view.inputTime.setOnClickListener {
            Commons.showTimePickerDialog(this, view.inputTime)
        }

        view.inputRemindTime.setOnClickListener {
            val options = resources.getStringArray(R.array.options_reminder)
            Commons.showSelector(this, "Select reminder time", options) { _, i ->
                view.inputRemindTime.setText(options[i])
                reminderTimeCategory = i
            }
        }

        view.inputRemindMe.setOnClickListener {
            view.inputRemindTime.visibility = if (view.inputRemindMe.isChecked) View.VISIBLE else View.GONE
        }

        if (view.inputRemindMe.isChecked)
            view.inputRemindTime.visibility = View.VISIBLE

        view.inputTitle.setText(todo.title)
        view.inputQuantity.setText(todo.quantity)
        view.inputDueDate.setText(todo.dueDate)
        view.inputTime.setText(todo.dueTime)
        view.inputRemindMe.isChecked = todo.remindMe

        val reminderTime = Commons.getReminderTimeFromCategory(reminderTimeCategory)
        view.inputRemindTime.setText(reminderTime)

        val dialogTitle = "Edit data"
        val toastMessage = "Data has been updated successfully"
        val failAlertMessage = "Please fill all the required fields"

        FormDialog(this, dialogTitle, view){
            val title = view.inputTitle.text.toString().trim()
            val date = view.inputDueDate.text.toString().trim()
            val time = view.inputTime.text.toString().trim()
            val quantity = view.inputQuantity.text.toString()

            val dateCreated = todo.dateCreated
            val remindMe = view.inputRemindMe.isChecked
            val prevDueTime = todo.dueTime

            if (title == "" || date == "" || time == "") {
                AlertDialog.Builder(this).setMessage(failAlertMessage).setCancelable(false)
                    .setPositiveButton("OK") { dialogInterface, _ ->
                        dialogInterface.cancel()
                    }.create().show()
            } else {
                val parsedDate = Commons.convertStringToDate("dd/MM/yy",date)
                val dueDate = Commons.formatDate(parsedDate, "dd/MM/yy")

                val currentDate = Commons.getCurrentTime()
                val dateUpdated =Commons.formatDate(currentDate, "dd/MM/yy HH:mm:ss")

                todo.title = title
                todo.quantity = quantity
                todo.dateCreated = dateCreated
                todo.dateUpdated = dateUpdated
                todo.dueDate = dueDate
                todo.dueTime = time
                todo.remindMe = remindMe
                todo.remindTime = reminderTimeCategory

                todoViewModel.updateTodo(todo)

                if (remindMe && prevDueTime!=time)
                    setReminder(reminderTimeCategory, title, dueDate, time)
                else if (!remindMe)
                    alarmReceiver.cancelAlarm(this)

                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun showDeleteDialog(todo: Todo) {
        val dialogTitle = "Delete"
        val dialogMessage = "Are you sure want to delete this data?"
        val toastMessage = "Data has been deleted successfully"
        CustomConfirmDialog(this, dialogTitle, dialogMessage) {
            todoViewModel.deleteTodo(todo)
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }.show()
    }

    private fun showDetailsDialog(todo: Todo) {
        val title = "Title: ${todo.title}"
        val dueDate = "Due date : ${todo.dueDate}, ${todo.dueTime}"
        val quantity = "Quantity: ${todo.quantity}"
        val dateCreated = "Date created: ${todo.dateCreated}"
        val dateUpdated = "Date updated: ${todo.dateUpdated}"

        val strReminder = if(todo.remindMe) "Enabled" else "Disabled"
        val remindMe = "Reminder: $strReminder"

        val strMessage = "$title\n$dueDate\n$quantity\n\n$dateCreated\n$dateUpdated\n$remindMe"

        AlertDialog.Builder(this).setMessage(strMessage).setCancelable(false)
            .setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.cancel()
            }.create().show()
    }

    private fun setReminder(timeCategory: Int, title: String, dueDate: String, dueTime: String){
        when (timeCategory) {
            0 -> alarmReceiver.setReminderAlarm(this, timeCategory, dueDate, dueTime,"$title is due in 1 hour")
            1 -> alarmReceiver.setReminderAlarm(this, timeCategory, dueDate, dueTime,"$title is due in 6 hours")
            2 -> alarmReceiver.setReminderAlarm(this, timeCategory, dueDate, dueTime,"$title is due in 12 hours")
            3 -> alarmReceiver.setReminderAlarm(this, timeCategory, dueDate, dueTime,"$title is due tomorrow")
            4 -> alarmReceiver.setReminderAlarm(this, timeCategory, dueDate, dueTime,"$title is due in 2 days")
        }
    }

    private fun setProgressbarVisibility(state: Boolean) {
        if (state) binding.progressbar.visibility = View.VISIBLE
        else binding.progressbar.visibility = View.INVISIBLE
    }*/
    
}