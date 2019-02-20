package at.davidomi.vocabtrainer.api;

        import at.davidomi.vocabtrainer.entity.Languages;
        import at.davidomi.vocabtrainer.pojo.LanguagesResponse;
        import at.davidomi.vocabtrainer.pojo.WordResponse;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface APIService {

    @GET("/api/v1.5/tr.json/translate?key=trnsl.1.1.20190216T152301Z.1e3d45821dad6c4a.6b53b780b4b035542b09c5a69ab167321b574fa9")
    Call<WordResponse> doGetTranslation(@Query("text") String text,
                                        @Query("lang") String lang);

    @GET("api/v1.5/tr.json/getLangs?key=trnsl.1.1.20190216T152301Z.1e3d45821dad6c4a.6b53b780b4b035542b09c5a69ab167321b574fa9")
    Call<LanguagesResponse> doGetLanguages();
}
