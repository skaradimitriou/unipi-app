package com.stathis.unipiapp.di.gson

import com.stathis.unipiapp.ui.contact.ContactViewModel
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import com.stathis.unipiapp.ui.dashboard.lessons.MyLessonsViewModel
import com.stathis.unipiapp.ui.dashboard.syllabus.SyllabusViewModel
import com.stathis.unipiapp.ui.department.DepartmentViewModel
import com.stathis.unipiapp.ui.intro.MainActivity
import com.stathis.unipiapp.ui.login.LoginActivity
import com.stathis.unipiapp.ui.professors.ProfessorsViewModel
import com.stathis.unipiapp.ui.students.StudentsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GsonModule::class])
interface GsonComponent {

    /**
     * Activities
     */

    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(dashboardActivity: DashboardActivity)

    /**
     * ViewModels
     */

    fun inject(deptViewModel: DepartmentViewModel)
    fun inject(studentsViewModel: StudentsViewModel)
    fun inject(professorsViewModel: ProfessorsViewModel)
    fun inject(syllabusViewModel : SyllabusViewModel)
    fun inject(myLessonsViewModel: MyLessonsViewModel)
    fun inject(contactViewModel : ContactViewModel)
}