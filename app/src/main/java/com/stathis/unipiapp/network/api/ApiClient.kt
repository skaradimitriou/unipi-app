import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.network.api.Endpoints
import com.stathis.unipiapp.ui.eclassAnnouncements.model.Channel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncementResponse
import com.stathis.unipiapp.util.ECLASS_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException

object ApiClient {

    private val api = Retrofit.Builder()
            .baseUrl(ECLASS_URL)
            .client(getOkHttpBuilder().build())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

    fun getOkHttpBuilder(): OkHttpClient.Builder = getUnsafeOkHttpClient()

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    fun getLessonsAnnouncements(code : String, data: MutableLiveData<Channel>, error: MutableLiveData<Boolean>) {
        api.getLessonAnnouncements(code).enqueue(object : Callback<EclassAnnouncementResponse> {
            override fun onResponse(call: Call<EclassAnnouncementResponse>, response: Response<EclassAnnouncementResponse>) {
                Log.d("Request body", response.body().toString())

                error.postValue(false)

                response.body()?.let {
                    data.postValue(it.channel)
                }
            }

            override fun onFailure(call: Call<EclassAnnouncementResponse>, t: Throwable) {
                Log.d("Request body", t.message.toString())
                error.postValue(true)
            }
        })
    }
}