package com.example.campus_lms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        
        // Entrance Animation
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_up)
        view.startAnimation(animation)
        
        val btnRegister = view.findViewById<Button>(R.id.btn_register_student)
        val btnBackToHome = view.findViewById<Button>(R.id.btn_register_to_home)
        val etName = view.findViewById<EditText>(R.id.reg_full_name)
        val etCourse = view.findViewById<AutoCompleteTextView>(R.id.reg_course)
        val etAge = view.findViewById<EditText>(R.id.reg_age)

        // Populate Dropdown
        val courses = arrayOf(
            "BSc. in Information Technology",
            "BSc. in Software Engineering",
            "BSc. in Computer Science",
            "Bachelor of Commerce (Accounting)",
            "Diploma in Accounting & Finance",
            "Certificate in Cyber Security",
            "Diploma in Data Science",
            "Professional Accounting (CPA/ACCA)"
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, courses)
        etCourse.setAdapter(adapter)

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val course = etCourse.text.toString()
            val age = etAge.text.toString()

            if (name.isNotEmpty() && course.isNotEmpty() && age.isNotEmpty()) {
                Toast.makeText(context, "Student $name enrolled in $course!", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etCourse.text.clear()
                etAge.text.clear()
            } else {
                Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackToHome.setOnClickListener {
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.selectedItemId = R.id.navigation_home
        }
        
        return view
    }
}