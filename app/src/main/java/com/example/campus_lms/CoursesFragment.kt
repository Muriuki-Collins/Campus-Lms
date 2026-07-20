package com.example.campus_lms

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CoursesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        
        val listView = view.findViewById<ListView>(R.id.courses_list)
        
        val courses = arrayOf(
            "BSc. in Information Technology",
            "BSc. in Software Engineering",
            "BSc. in Computer Science",
            "Bachelor of Commerce (Accounting)",
            "Diploma in Accounting & Finance",
            "Professional Accounting (CPA/ACCA)",
            "Certificate in Cyber Security",
            "Diploma in Data Science"
        )

        val descriptions = mapOf(
            "BSc. in Information Technology" to "Focuses on the use of computers and software to manage information. Includes networking, database management, and web development.",
            "BSc. in Software Engineering" to "Covers the complete software development lifecycle, including requirements, design, testing, and maintenance of software systems.",
            "BSc. in Computer Science" to "A deep dive into computational theory, algorithms, and the architecture of computer systems.",
            "Bachelor of Commerce (Accounting)" to "Prepares students for careers in financial management, auditing, and tax consulting.",
            "Diploma in Accounting & Finance" to "A practical program covering the fundamentals of bookkeeping, financial reporting, and investment principles.",
            "Professional Accounting (CPA/ACCA)" to "Global professional qualifications for accountants, focusing on strategic management and high-level auditing.",
            "Certificate in Cyber Security" to "Teaches the essentials of protecting networks, devices, and data from unauthorized access or criminal use.",
            "Diploma in Data Science" to "Explores data analysis, statistical modeling, and machine learning to drive business insights."
        )
        
        val adapter = object : ArrayAdapter<String>(requireContext(), R.layout.item_course, R.id.course_name, courses) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView = super.getView(position, convertView, parent)
                val courseName = itemView.findViewById<TextView>(R.id.course_name)
                courseName.text = getItem(position)
                
                val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_up)
                animation.startOffset = (position * 100).toLong()
                itemView.startAnimation(animation)
                
                return itemView
            }
        }
        
        listView.adapter = adapter

        val btnBackToRegister = view.findViewById<Button>(R.id.btn_courses_to_register)
        btnBackToRegister.setOnClickListener {
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.selectedItemId = R.id.navigation_register
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val course = courses[position]
            val description = descriptions[course] ?: "No description available."
            
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(course)
                .setMessage(description)
                .setPositiveButton("Close", null)
                .show()
        }
        
        return view
    }
}