package com.stathis.unipiapp.ui.dashboard.grades

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.grading.SemesterInfoDto
import com.stathis.unipiapp.models.grading.StudentGradesCardDto
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.ui.dashboard.grades.adapter.GradesAdapter

class GradesViewModel : ViewModel(),UnipiCallback {

    val adapter = GradesAdapter(this)

    init {
        getData()
    }

     fun getData(): StudentGradesCardDto {
         //FIXME: Remove this json. this is used for dummy purposes
        val json = "{\"cookies\":{\"token\":\"guest-account-token\"},\"student\":{\"grades\":{\"semesters\":[{\"courses\":[{\"examPeriod\":\"ΦΕΒΡ 2020\",\"grade\":\"4\",\"id\":\"UNI-001\",\"name\":\"ΑΝΤΙΚΕΙΜΕΝΟΣΤΡΕΦΗΣ ΠΡΟΓΡΑΜΜΑΤΙΣΜΟΣ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΣΕΠΤ 2020\",\"grade\":\"10\",\"id\":\"UNI-002\",\"name\":\"ΔΙΑΚΡΙΤΑ ΜΑΘΗΜΑΤΙΚΑ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΦΕΒΡ 2020\",\"grade\":\"6\",\"id\":\"UNI-003\",\"name\":\"ΕΙΣΑΓΩΓΗ ΣΤΟ ΜΑΡΚΕΤΙΝΓΚ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΦΕΒΡ 2020\",\"grade\":\"1\",\"id\":\"UNI-004\",\"name\":\"ΒΑΣΙΚΕΣ ΑΡΧΕΣ ΧΡΩΜΑΤΟΣ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΣΕΠΤ 2020\",\"grade\":\"5\",\"id\":\"UNI-005\",\"name\":\"ΑΛΓΟΡΙΘΜΟΙ\",\"type\":\"ΥΠ\"}],\"ects\":\"15\",\"gradeAverage\":\"7\",\"id\":1,\"passedCourses\":3},{\"courses\":[{\"examPeriod\":\"ΙΟΥΝ 2020\",\"grade\":\"3\",\"id\":\"UNI-006\",\"name\":\"ΒΑΣΙΚΟ ΣΧΕΔΙΟ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΣΕΠΤ 2020\",\"grade\":\"7\",\"id\":\"UNI-007\",\"name\":\"ΣΤΑΤΙΣΤΙΚΗ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΙΟΥΝ 2020\",\"grade\":\"5\",\"id\":\"UNI-008\",\"name\":\"ΑΡΧΕΣ ΓΡΑΦΙΣΤΙΚΗΣ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΙΟΥΝ 2020\",\"grade\":\"10\",\"id\":\"UNI-009\",\"name\":\"ΟΙΚΟΝΟΜΕΤΡΙΑ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"ΣΕΠΤ 2020\",\"grade\":\"8\",\"id\":\"UNI-010\",\"name\":\"ΤΕΧΝΗΤΗ ΝΟΗΜΟΣΥΝΗ\",\"type\":\"ΥΠ\"}],\"ects\":\"20\",\"gradeAverage\":\"7.5\",\"id\":2,\"passedCourses\":4},{\"courses\":[{\"examPeriod\":\"-\",\"grade\":\"-\",\"id\":\"UNI-011\",\"name\":\"ΨΗΦΙΑΚΗ ΣΧΕΔΙΑΣΗ ΕΝΤΥΠΟΥ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"-\",\"grade\":\"-\",\"id\":\"UNI-012\",\"name\":\"ΔΟΜΕΣ ΔΕΔΟΜΕΝΩΝ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"-\",\"grade\":\"-\",\"id\":\"UNI-013\",\"name\":\"ΛΕΙΤΟΥΡΓΙΚΑ ΣΥΣΤΗΜΑΤΑ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"-\",\"grade\":\"-\",\"id\":\"UNI-014\",\"name\":\"ΓΡΑΦΙΣΤΙΚΗ ΚΑΙ ΕΙΚΟΝΟΓΡΑΦΗΣΗ\",\"type\":\"ΥΠ\"},{\"examPeriod\":\"-\",\"grade\":\"-\",\"id\":\"UNI-015\",\"name\":\"ΧΩΡΟΤΑΞΙΑ\",\"type\":\"ΥΠ\"}],\"ects\":\"-\",\"gradeAverage\":\"-\",\"id\":3,\"passedCourses\":0}],\"totalAverageGrade\":\"7.28\",\"totalEcts\":\"35\",\"totalPassedCourses\":\"7\"},\"info\":{\"aem\":\"uni2020\",\"department\":\"ΤΜΗΜΑ UNISTUDENTS\",\"firstName\":\"Guest\",\"lastName\":\"\",\"registrationYear\":\"2020\",\"semester\":\"3\"}}}"

         val data = Gson().fromJson(json,StudentsResponseDto::class.java)
         val myData = data.student.grades

         adapter.submitList(myData.semesters)
         return myData
     }

    override fun onItemTap(view: View) {
        //
    }
}