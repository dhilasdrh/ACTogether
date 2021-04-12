package com.dhilasdrh.zerowaste.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.activity.InventoryActivity
import com.dhilasdrh.zerowaste.databinding.ItemEmptyBinding
import com.dhilasdrh.zerowaste.databinding.ItemRowTodoBinding
import com.dhilasdrh.zerowaste.model.Todo
import com.dhilasdrh.zerowaste.utils.Commons
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(private val context: Context, private val listener: (Todo, Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {
    private val VIEW_TYPE_EMPTY = 0
    private val VIEW_TYPE_TODO = 1

    private var todoList = listOf<Todo>()
    private var todoFilteredList = listOf<Todo>()

    fun setTodoList(todoList: List<Todo>) {
        this.todoList = todoList
        todoFilteredList = todoList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keywords = constraint.toString()
                if (keywords.isEmpty())
                    todoFilteredList = todoList
                else{
                    val filteredList = ArrayList<Todo>()
                    for (todo in todoList) {
                        if (todo.toString().toLowerCase(Locale.ROOT).contains(
                                keywords.toLowerCase(
                                    Locale.ROOT
                                )
                            ))
                            filteredList.add(todo)
                    }
                    todoFilteredList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = todoFilteredList
                return  filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                todoFilteredList = results?.values as List<Todo>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (todoFilteredList.isEmpty())
            VIEW_TYPE_EMPTY
        else
            VIEW_TYPE_TODO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
      /*  val itemRowTodoBinding = ItemRowTodoBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        val viewItemEmpty = ItemEmptyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return when (viewType) {
            VIEW_TYPE_TODO -> TodoViewHolder(itemRowTodoBinding)
            VIEW_TYPE_EMPTY -> EmptyViewHolder(viewItemEmpty)
            else -> throw throw IllegalArgumentException("Undefined view type")
        }*/

        return when (viewType) {
            VIEW_TYPE_TODO -> TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_todo, parent, false))
            VIEW_TYPE_EMPTY -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false))
            else -> throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = if (todoFilteredList.isEmpty()) 1 else todoFilteredList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_EMPTY -> {
                val emptyHolder = holder as EmptyViewHolder
                emptyHolder.bindItem(context)
            }
            VIEW_TYPE_TODO -> {
                val todoHolder = holder as TodoViewHolder
                val sortedList = todoFilteredList.sortedWith(
                    if (InventoryActivity.isSortByDateCreated)
                        compareBy({ it.dateCreated }, { it.dateUpdated })
                    else {
                        compareBy({ it.dueDate }, { it.dueTime })
                    }
                )
                todoHolder.bindItem(context, sortedList[position], listener)
            }
        }
    }

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var binding: ItemRowTodoBinding

        fun bindItem(context: Context, todo: Todo, listener: (Todo, Int) -> Unit) {
            binding = ItemRowTodoBinding.inflate(LayoutInflater.from(context))

            val parsedDateCreated = SimpleDateFormat("dd/MM/yy", Locale.US).parse(todo.dateCreated) as Date
            val dateCreated = Commons.formatDate(parsedDateCreated, "dd MMM yyyy")

            val parsedDateUpdated = SimpleDateFormat("dd/MM/yy", Locale.US).parse(todo.dateCreated) as Date
            val dateUpdated = Commons.formatDate(parsedDateUpdated, "dd MMM yyyy")

            val date = if (todo.dateUpdated != todo.dateCreated) "Updated at $dateUpdated" else "Created at $dateCreated"

            val parsedDate = SimpleDateFormat("dd/MM/yy", Locale.US).parse(todo.dueDate) as Date
            val dueDate = Commons.formatDate(parsedDate, "dd MMM yyyy")

            val dueDateTime = "Due ${dueDate}, ${todo.dueTime}"
           // setSideIndicatorColor(context, todo.dueDate, todo.dueTime)

            binding.tvTitle.text = todo.title
            binding.tvQuantity.text = todo.quantity
            binding.tvDueDate.text = dueDateTime

            binding.root.setOnClickListener{
                listener(todo, layoutPosition)
            }
        }

        /*
        private fun setSideIndicatorColor(context: Context, dueDate: String, dueTime: String) {
            val dueDateTime = "$dueDate $dueTime"
            val parsedDate = SimpleDateFormat("dd/MM/yy HH:mm", Locale.US).parse(dueDateTime) as Date
            val parsedDateNoTime = SimpleDateFormat("dd/MM/yy", Locale.US).parse(dueDateTime) as Date

            val currentDate = SimpleDateFormat("dd/MM/yy HH:mm", Locale.US).format(Commons.getCurrentDateTime())
            val parsedCurrentDate = SimpleDateFormat("dd/MM/yy HH:mm", Locale.US).parse(currentDate) as Date
            val parsedCurrentDateNoTime = SimpleDateFormat("dd/MM/yy", Locale.US).parse(currentDate) as Date

            when {
                parsedDateNoTime == parsedCurrentDateNoTime -> binding.sideIndicator.setBackgroundColor(
                    ContextCompat.getColor(context!!, R.color.colorAccent)
                )
                parsedDate.before(parsedCurrentDate) -> binding.sideIndicator.setBackgroundColor(
                    ContextCompat.getColor(context!!, R.color.red)
                )
                else -> binding.sideIndicator.setBackgroundColor(
                    ContextCompat.getColor(context!!, R.color.colorPrimary)
                )
            }
        }*/
    }

    class EmptyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var binding: ItemEmptyBinding

        fun bindItem(context: Context){
            binding = ItemEmptyBinding.inflate(LayoutInflater.from(context))
            binding.tvEmpty.text = context.resources.getString(R.string.no_data_found)
        }
    }

}
