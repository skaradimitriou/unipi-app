package com.stathis.unipiapp.network

import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.util.BASE_URL
import org.jsoup.Jsoup

object JsoupModule {

    /*
     * Get UoP announcements from cs.unipi.gr
     */

    fun getAnnouncements(data : MutableLiveData<List<Announcement>>, error: MutableLiveData<Boolean>) {
        val announcements = arrayListOf<Announcement>()

        try {
            val url = "https://www.cs.unipi.gr/index.php?option=com_k2&view=itemlist&layout=category&task=category&id=16&Itemid=673&lang=el"
            val doc = Jsoup.connect(url).timeout(60000).validateTLSCertificates(false).get()
            for (i in 0..9) {
                val title = doc.select(".catItemView").select(".catItemHeader").select("h3").select("a").eq(i).text()
                val date = doc.select(".catItemView").select(".blog-item-meta").select(".catItemDateCreated").eq(i).text()
                val url = doc.select(".catItemView").select(".catItemHeader").select("h3").select("a").attr("href")

                announcements.add(Announcement(title, date, url))
            }

            data.postValue(announcements)
            error.postValue(false)
        } catch (e: Exception) {
            error.postValue(true)
        }
    }

    /*
     * Get UoP services from cs.unipi.gr
     */

    fun getUnipiServices(data : MutableLiveData<List<UnipiService>>,error : MutableLiveData<Boolean>){
        val list = mutableListOf<UnipiService>()

        try {
            val url = "https://www.cs.unipi.gr/index.php?option=com_k2&view=itemlist&layout=category&task=category&id=38&Itemid=567&lang=el"
            val doc = Jsoup.connect(url).timeout(60000).validateTLSCertificates(false).get()

            for (i in 0..8) {
                val title = doc.select(".itemList").select(".itemContainer").select(".catItemView").select(".catItemBody").select(".catItemImageBlock").select("span.catItemImage").select("a").eq(i).attr("title")
                val url = doc.select(".itemList").select(".itemContainer").select(".catItemView").select(".catItemBody").select(".catItemImageBlock").select("span.catItemImage").select("a").eq(i).attr("href")
                val path = doc.select(".itemList").select(".itemContainer").select(".catItemView").select(".catItemBody").select(".catItemImageBlock").select("span.catItemImage").select("a").select("img").eq(i).attr("src")
                val image = "$BASE_URL$path"

                list.add(UnipiService(title,image,url))
            }

            data.postValue(list)
            error.postValue(false)
        } catch (e: Exception) {
            error.postValue(true)
        }
    }
}