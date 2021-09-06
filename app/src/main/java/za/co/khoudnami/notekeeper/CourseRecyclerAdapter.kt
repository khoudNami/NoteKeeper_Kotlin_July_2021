package za.co.khoudnami.notekeeper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class CourseRecyclerAdapter(private val context: Context, private val courses: List<CourseInfo>) :
    RecyclerView.Adapter<CourseRecyclerAdapter.CustomViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val course = courses[position]
        holder.textCourse.text = course.title
        holder.coursePosition = position
    }

    override fun getItemCount() = courses.size

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCourse = itemView.findViewById<TextView>(R.id.textCourse)
        var coursePosition = 0

        init {
            itemView.setOnClickListener {
                Snackbar.make(
                    itemView,
                    "Course at position $coursePosition is ${courses[coursePosition]}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}