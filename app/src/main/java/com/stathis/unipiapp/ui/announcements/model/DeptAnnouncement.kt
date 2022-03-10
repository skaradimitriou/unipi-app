package com.stathis.unipiapp.ui.announcements.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
@Parcelize
@Entity(tableName = "Announcements")
data class DeptAnnouncement (

    @field: Element(name = "title")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    var title: String = "",

    @field: Element(name = "description", required = false)
    @ColumnInfo(name = "description")
    var description: String = "",

    @field: Element(name = "link")
    @ColumnInfo(name = "link")
    var link: String = "",

    @field: Element(name = "author")
    @ColumnInfo(name = "author")
    var author: String = "",

    @field: Element(name = "pubDate")
    @ColumnInfo(name = "pubDate")
    var pubDate: String = ""

) : Parcelable,LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is DeptAnnouncement -> title == obj.title
        else -> false
    }
}