package com.habileducation.themovie.util

import com.habileducation.themovie.AppKey
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import io.ktor.utils.io.*


/*internal fun buildOkHttpClient(): HttpClient {

    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()
}*/

class FakeApiService() {
    fun buildApiService(movieType: String): HttpClient {
        //val file = this::class.java.classLoader!!.getResourceAsStream(jsonFile(movieType)).bufferedReader().use { it.readText() }
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(getMovies(movieType)),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        return HttpClient(mockEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                    }
                )
            }
        }
    }

    private fun getMovies(movieType: String): String{
        return when (movieType){
            AppKey.POPULAR_MOVIES -> popularMovies
            AppKey.TOP_RATED_MOVIES -> topRatedMovies
            AppKey.UPCOMING_MOVIES -> upcomingMovies
            AppKey.NOW_PLAYING_MOVIES -> nowPlayingMovies
            "movieDetail"-> movieDetail
            "movieReview" -> movieReview
            else -> ""
        }
    }
}

val movieReview = "{\n" +
        "  \"id\": 19404,\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"author\": \"MohamedElsharkawy\",\n" +
        "      \"author_details\": {\n" +
        "        \"name\": \"\",\n" +
        "        \"username\": \"MohamedElsharkawy\",\n" +
        "        \"avatar_path\": null,\n" +
        "        \"rating\": null\n" +
        "      },\n" +
        "      \"content\": \"The Dilwale Dulhania Le Jayenge is a film considered by most to be one of the greatest ever made. From The American Film Institute to as voted by users on the Internet Movie Database (IMDB) it is consider to be one of the best.\",\n" +
        "      \"created_at\": \"2017-10-21T12:27:46.145Z\",\n" +
        "      \"id\": \"59eb3d42925141565100e901\",\n" +
        "      \"updated_at\": \"2021-06-23T15:58:01.939Z\",\n" +
        "      \"url\": \"https://www.themoviedb.org/review/59eb3d42925141565100e901\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 1,\n" +
        "  \"total_results\": 1\n" +
        "}"

val movieDetail = "{\n" +
        "  \"adult\": false,\n" +
        "  \"backdrop_path\": \"/5hNcsnMkwU2LknLoru73c76el3z.jpg\",\n" +
        "  \"belongs_to_collection\": null,\n" +
        "  \"budget\": 13200000,\n" +
        "  \"genres\": [\n" +
        "    {\n" +
        "      \"id\": 35,\n" +
        "      \"name\": \"Comedy\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 18,\n" +
        "      \"name\": \"Drama\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 10749,\n" +
        "      \"name\": \"Romance\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"homepage\": \"\",\n" +
        "  \"id\": 19404,\n" +
        "  \"imdb_id\": \"tt0112870\",\n" +
        "  \"original_language\": \"hi\",\n" +
        "  \"original_title\": \"दिलवाले दुल्हनिया ले जायेंगे\",\n" +
        "  \"overview\": \"Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.\",\n" +
        "  \"popularity\": 24.592,\n" +
        "  \"poster_path\": \"/2CAL2433ZeIihfX1Hb2139CX0pW.jpg\",\n" +
        "  \"production_companies\": [\n" +
        "    {\n" +
        "      \"id\": 1569,\n" +
        "      \"logo_path\": \"/lvzN86o3jrP44DIvn4SMBLOl9PF.png\",\n" +
        "      \"name\": \"Yash Raj Films\",\n" +
        "      \"origin_country\": \"IN\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"production_countries\": [\n" +
        "    {\n" +
        "      \"iso_3166_1\": \"IN\",\n" +
        "      \"name\": \"India\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"release_date\": \"1995-10-20\",\n" +
        "  \"revenue\": 100000000,\n" +
        "  \"runtime\": 190,\n" +
        "  \"spoken_languages\": [\n" +
        "    {\n" +
        "      \"english_name\": \"Hindi\",\n" +
        "      \"iso_639_1\": \"hi\",\n" +
        "      \"name\": \"हिन्दी\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"status\": \"Released\",\n" +
        "  \"tagline\": \"Come Fall In love, All Over Again..\",\n" +
        "  \"title\": \"Dilwale Dulhania Le Jayenge\",\n" +
        "  \"video\": false,\n" +
        "  \"vote_average\": 8.7,\n" +
        "  \"vote_count\": 3248\n" +
        "}"

val popularMovies = "{\n" +
        "    \"page\": 1,\n" +
        "    \"results\": [\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/cinER0ESG0eJ49kXlExM0MEWGxW.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                28,\n" +
        "                12,\n" +
        "                14\n" +
        "            ],\n" +
        "            \"id\": 566525,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "            \"overview\": \"Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.\",\n" +
        "            \"popularity\": 6665.737,\n" +
        "            \"poster_path\": \"/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg\",\n" +
        "            \"release_date\": \"2021-09-01\",\n" +
        "            \"title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 7.9,\n" +
        "            \"vote_count\": 3379\n" +
        "        },\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/dK12GIdhGP6NPGFssK2Fh265jyr.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                28,\n" +
        "                35,\n" +
        "                80,\n" +
        "                53\n" +
        "            ],\n" +
        "            \"id\": 512195,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Red Notice\",\n" +
        "            \"overview\": \"An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.\",\n" +
        "            \"popularity\": 6263.951,\n" +
        "            \"poster_path\": \"/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg\",\n" +
        "            \"release_date\": \"2021-11-04\",\n" +
        "            \"title\": \"Red Notice\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 6.9,\n" +
        "            \"vote_count\": 1200\n" +
        "        },\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/70nxSw3mFBsGmtkvcs91PbjerwD.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                878,\n" +
        "                28,\n" +
        "                12\n" +
        "            ],\n" +
        "            \"id\": 580489,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Venom: Let There Be Carnage\",\n" +
        "            \"overview\": \"After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.\",\n" +
        "            \"popularity\": 4141.331,\n" +
        "            \"poster_path\": \"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg\",\n" +
        "            \"release_date\": \"2021-09-30\",\n" +
        "            \"title\": \"Venom: Let There Be Carnage\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 6.8,\n" +
        "            \"vote_count\": 2039\n" +
        "        },\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/zBkHCpLmHjW2uVURs5uZkaVmgKR.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                16,\n" +
        "                35,\n" +
        "                10751\n" +
        "            ],\n" +
        "            \"id\": 585245,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Clifford the Big Red Dog\",\n" +
        "            \"overview\": \"As Emily struggles to fit in at home and at school, she discovers a small red puppy who is destined to become her best friend. When Clifford magically undergoes one heck of a growth spurt, becomes a gigantic dog and attracts the attention of a genetics company, Emily and her Uncle Casey have to fight the forces of greed as they go on the run across New York City. Along the way, Clifford affects the lives of everyone around him and teaches Emily and her uncle the true meaning of acceptance and unconditional love.\",\n" +
        "            \"popularity\": 3269.139,\n" +
        "            \"poster_path\": \"/tY5jylU5wWaqRs8fmwkJBNzoyXB.jpg\",\n" +
        "            \"release_date\": \"2021-11-10\",\n" +
        "            \"title\": \"Clifford the Big Red Dog\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 7.8,\n" +
        "            \"vote_count\": 170\n" +
        "        },\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/u5Fp9GBy9W8fqkuGfLBuuoJf57Z.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                12,\n" +
        "                28,\n" +
        "                53\n" +
        "            ],\n" +
        "            \"id\": 370172,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"No Time to Die\",\n" +
        "            \"overview\": \"Bond has left active service and is enjoying a tranquil life in Jamaica. His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, leading Bond onto the trail of a mysterious villain armed with dangerous new technology.\",\n" +
        "            \"popularity\": 3491.344,\n" +
        "            \"poster_path\": \"/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg\",\n" +
        "            \"release_date\": \"2021-09-29\",\n" +
        "            \"title\": \"No Time to Die\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 7.6,\n" +
        "            \"vote_count\": 2033\n" +
        "        }\n" +
        "    ],\n" +
        "    \"total_pages\": 500,\n" +
        "    \"total_results\": 10000\n" +
        "}"

val topRatedMovies = "{\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/5hNcsnMkwU2LknLoru73c76el3z.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        35,\n" +
        "        18,\n" +
        "        10749\n" +
        "      ],\n" +
        "      \"id\": 19404,\n" +
        "      \"original_language\": \"hi\",\n" +
        "      \"original_title\": \"दिलवाले दुल्हनिया ले जायेंगे\",\n" +
        "      \"overview\": \"Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.\",\n" +
        "      \"popularity\": 24.592,\n" +
        "      \"poster_path\": \"/2CAL2433ZeIihfX1Hb2139CX0pW.jpg\",\n" +
        "      \"release_date\": \"1995-10-20\",\n" +
        "      \"title\": \"Dilwale Dulhania Le Jayenge\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.7,\n" +
        "      \"vote_count\": 3248\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        18,\n" +
        "        80\n" +
        "      ],\n" +
        "      \"id\": 278,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"The Shawshank Redemption\",\n" +
        "      \"overview\": \"Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.\",\n" +
        "      \"popularity\": 57.788,\n" +
        "      \"poster_path\": \"/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg\",\n" +
        "      \"release_date\": \"1994-09-23\",\n" +
        "      \"title\": \"The Shawshank Redemption\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.7,\n" +
        "      \"vote_count\": 20160\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        18,\n" +
        "        80\n" +
        "      ],\n" +
        "      \"id\": 238,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"The Godfather\",\n" +
        "      \"overview\": \"Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.\",\n" +
        "      \"popularity\": 55.455,\n" +
        "      \"poster_path\": \"/eEslKSwcqmiNS6va24Pbxf2UKmJ.jpg\",\n" +
        "      \"release_date\": \"1972-03-14\",\n" +
        "      \"title\": \"The Godfather\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.7,\n" +
        "      \"vote_count\": 15102\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        10749\n" +
        "      ],\n" +
        "      \"id\": 724089,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Gabriel's Inferno Part II\",\n" +
        "      \"overview\": \"Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?\",\n" +
        "      \"popularity\": 9.454,\n" +
        "      \"poster_path\": \"/x5o8cLZfEXMoZczTYWLrUo1P7UJ.jpg\",\n" +
        "      \"release_date\": \"2020-07-31\",\n" +
        "      \"title\": \"Gabriel's Inferno Part II\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.7,\n" +
        "      \"vote_count\": 1328\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/fQq1FWp1rC89xDrRMuyFJdFUdMd.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        10749,\n" +
        "        35\n" +
        "      ],\n" +
        "      \"id\": 761053,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Gabriel's Inferno Part III\",\n" +
        "      \"overview\": \"The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.\",\n" +
        "      \"popularity\": 27.769,\n" +
        "      \"poster_path\": \"/qtX2Fg9MTmrbgN1UUvGoCsImTM8.jpg\",\n" +
        "      \"release_date\": \"2020-11-19\",\n" +
        "      \"title\": \"Gabriel's Inferno Part III\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.6,\n" +
        "      \"vote_count\": 896\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/loRmRzQXZeqG78TqZuyvSlEQfZb.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        18,\n" +
        "        36,\n" +
        "        10752\n" +
        "      ],\n" +
        "      \"id\": 424,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Schindler's List\",\n" +
        "      \"overview\": \"The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.\",\n" +
        "      \"popularity\": 38.542,\n" +
        "      \"poster_path\": \"/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg\",\n" +
        "      \"release_date\": \"1993-11-30\",\n" +
        "      \"title\": \"Schindler's List\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 8.6,\n" +
        "      \"vote_count\": 12058\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 468,\n" +
        "  \"total_results\": 9350\n" +
        "}"

val upcomingMovies = "{\n" +
        "  \"dates\": {\n" +
        "    \"maximum\": \"2021-12-17\",\n" +
        "    \"minimum\": \"2021-11-22\"\n" +
        "  },\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/cinER0ESG0eJ49kXlExM0MEWGxW.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        12,\n" +
        "        14\n" +
        "      ],\n" +
        "      \"id\": 566525,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "      \"overview\": \"Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.\",\n" +
        "      \"popularity\": 6665.737,\n" +
        "      \"poster_path\": \"/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg\",\n" +
        "      \"release_date\": \"2021-09-01\",\n" +
        "      \"title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.9,\n" +
        "      \"vote_count\": 3379\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/70nxSw3mFBsGmtkvcs91PbjerwD.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        878,\n" +
        "        28,\n" +
        "        12\n" +
        "      ],\n" +
        "      \"id\": 580489,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Venom: Let There Be Carnage\",\n" +
        "      \"overview\": \"After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.\",\n" +
        "      \"popularity\": 4141.331,\n" +
        "      \"poster_path\": \"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg\",\n" +
        "      \"release_date\": \"2021-09-30\",\n" +
        "      \"title\": \"Venom: Let There Be Carnage\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 6.8,\n" +
        "      \"vote_count\": 2039\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/zBkHCpLmHjW2uVURs5uZkaVmgKR.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        16,\n" +
        "        35,\n" +
        "        10751\n" +
        "      ],\n" +
        "      \"id\": 585245,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Clifford the Big Red Dog\",\n" +
        "      \"overview\": \"As Emily struggles to fit in at home and at school, she discovers a small red puppy who is destined to become her best friend. When Clifford magically undergoes one heck of a growth spurt, becomes a gigantic dog and attracts the attention of a genetics company, Emily and her Uncle Casey have to fight the forces of greed as they go on the run across New York City. Along the way, Clifford affects the lives of everyone around him and teaches Emily and her uncle the true meaning of acceptance and unconditional love.\",\n" +
        "      \"popularity\": 3269.139,\n" +
        "      \"poster_path\": \"/tY5jylU5wWaqRs8fmwkJBNzoyXB.jpg\",\n" +
        "      \"release_date\": \"2021-11-10\",\n" +
        "      \"title\": \"Clifford the Big Red Dog\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.8,\n" +
        "      \"vote_count\": 170\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/u5Fp9GBy9W8fqkuGfLBuuoJf57Z.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        12,\n" +
        "        28,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 370172,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"No Time to Die\",\n" +
        "      \"overview\": \"Bond has left active service and is enjoying a tranquil life in Jamaica. His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, leading Bond onto the trail of a mysterious villain armed with dangerous new technology.\",\n" +
        "      \"popularity\": 3491.344,\n" +
        "      \"poster_path\": \"/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg\",\n" +
        "      \"release_date\": \"2021-09-29\",\n" +
        "      \"title\": \"No Time to Die\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.6,\n" +
        "      \"vote_count\": 2033\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/4gKxQIW91hOTELjY5lzjMbLoGxB.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        53,\n" +
        "        878\n" +
        "      ],\n" +
        "      \"id\": 763164,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Apex\",\n" +
        "      \"overview\": \"Ex-cop James Malone is serving a life sentence for a crime he didn’t commit. He is offered a chance at freedom if he can survive a deadly game of Apex, in which six hunters pay for the pleasure of hunting another human on a remote island. He accepts, and once he arrives, all hell breaks loose.\",\n" +
        "      \"popularity\": 1863.698,\n" +
        "      \"poster_path\": \"/chTkFGToW5bsyw3hgLAe4S5Gt3.jpg\",\n" +
        "      \"release_date\": \"2021-11-12\",\n" +
        "      \"title\": \"Apex\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 5.7,\n" +
        "      \"vote_count\": 183\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 33,\n" +
        "  \"total_results\": 650\n" +
        "}"

val nowPlayingMovies = "{\n" +
        "  \"dates\": {\n" +
        "    \"maximum\": \"2021-11-21\",\n" +
        "    \"minimum\": \"2021-10-04\"\n" +
        "  },\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/cinER0ESG0eJ49kXlExM0MEWGxW.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        12,\n" +
        "        14\n" +
        "      ],\n" +
        "      \"id\": 566525,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "      \"overview\": \"Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.\",\n" +
        "      \"popularity\": 6665.737,\n" +
        "      \"poster_path\": \"/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg\",\n" +
        "      \"release_date\": \"2021-09-01\",\n" +
        "      \"title\": \"Shang-Chi and the Legend of the Ten Rings\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.9,\n" +
        "      \"vote_count\": 3379\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/dK12GIdhGP6NPGFssK2Fh265jyr.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        35,\n" +
        "        80,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 512195,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Red Notice\",\n" +
        "      \"overview\": \"An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.\",\n" +
        "      \"popularity\": 6263.951,\n" +
        "      \"poster_path\": \"/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg\",\n" +
        "      \"release_date\": \"2021-11-04\",\n" +
        "      \"title\": \"Red Notice\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 6.9,\n" +
        "      \"vote_count\": 1200\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/70nxSw3mFBsGmtkvcs91PbjerwD.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        878,\n" +
        "        28,\n" +
        "        12\n" +
        "      ],\n" +
        "      \"id\": 580489,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Venom: Let There Be Carnage\",\n" +
        "      \"overview\": \"After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.\",\n" +
        "      \"popularity\": 4141.331,\n" +
        "      \"poster_path\": \"/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg\",\n" +
        "      \"release_date\": \"2021-09-30\",\n" +
        "      \"title\": \"Venom: Let There Be Carnage\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 6.8,\n" +
        "      \"vote_count\": 2039\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/zBkHCpLmHjW2uVURs5uZkaVmgKR.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        16,\n" +
        "        35,\n" +
        "        10751\n" +
        "      ],\n" +
        "      \"id\": 585245,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Clifford the Big Red Dog\",\n" +
        "      \"overview\": \"As Emily struggles to fit in at home and at school, she discovers a small red puppy who is destined to become her best friend. When Clifford magically undergoes one heck of a growth spurt, becomes a gigantic dog and attracts the attention of a genetics company, Emily and her Uncle Casey have to fight the forces of greed as they go on the run across New York City. Along the way, Clifford affects the lives of everyone around him and teaches Emily and her uncle the true meaning of acceptance and unconditional love.\",\n" +
        "      \"popularity\": 3269.139,\n" +
        "      \"poster_path\": \"/tY5jylU5wWaqRs8fmwkJBNzoyXB.jpg\",\n" +
        "      \"release_date\": \"2021-11-10\",\n" +
        "      \"title\": \"Clifford the Big Red Dog\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.8,\n" +
        "      \"vote_count\": 170\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/u5Fp9GBy9W8fqkuGfLBuuoJf57Z.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        12,\n" +
        "        28,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 370172,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"No Time to Die\",\n" +
        "      \"overview\": \"Bond has left active service and is enjoying a tranquil life in Jamaica. His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, leading Bond onto the trail of a mysterious villain armed with dangerous new technology.\",\n" +
        "      \"popularity\": 3491.344,\n" +
        "      \"poster_path\": \"/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg\",\n" +
        "      \"release_date\": \"2021-09-29\",\n" +
        "      \"title\": \"No Time to Die\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.6,\n" +
        "      \"vote_count\": 2033\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 134,\n" +
        "  \"total_results\": 2680\n" +
        "}"