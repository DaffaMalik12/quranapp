import com.example.quranapp.data.model.Surah
import com.example.quranapp.data.model.SurahResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("surahs")
    suspend fun getSurahs(): List<SurahResponse>

    @GET("surahs/{id}")
    suspend fun getSurah(@Path("id") id: Int): Surah

    companion object {
        private const val BASE_URL = "https://quran-api-id.vercel.app/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}