import java.lang.Runtime.Version

object BuildGradle{

}

object Deps{
    val appCompat by lazy{"androidx.core:core-ktx:${Versions.appCompat}"}
    val coreKtx by lazy{"androidx.core:core-ktx:${Versions.coreKtx}"}
    val materialDesign by lazy {"com.google.android.material:material:${Versions.material}"}
    val constraintLayout by lazy {"androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"}
    val legacy by lazy {"androidx.legacy:legacy-support-v4:${Versions.legacy}"}
    val hilt by lazy {"com.google.dagger:hilt-android:${Versions.daggerHilt}"}
    val hiltAndroidCompiler by lazy {"com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"}
    val hiltCompiler by lazy {"androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"}
    val hiltNavigation by lazy{"androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}"}
    val navigationFragment by lazy {"androidx.navigation:navigation-fragment-ktx:${Versions.fragmentNav}"}
    val navigationUi by lazy {"androidx.navigation:navigation-ui-ktx:${Versions.fragmentNav}"}
    val room by lazy {"androidx.room:room-runtime:${Versions.room}"}
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}"}
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}"}
    val livedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"}
    val coroutineCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"}
    val coroutinesAndroid by lazy {"org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"}
    val coroutinesPlay by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}" }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"}
    val glide by lazy {"com.github.bumptech.glide:glide:${Versions.glide}"}
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}"}
    val liveEvent by lazy {"com.github.hadilq.liveevent:liveevent:${Versions.liveEvent}"}
    val timber by lazy {"com.jakewharton.timber:timber:${Versions.timber}"}
    val retrofit by lazy {"com.squareup.retrofit2:retrofit:${Versions.retrofit}"}
    val moshi by lazy {"com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"}
    val coroutinesAdapter by lazy {"com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitAdapter}"}
    val canary by lazy {"com.squareup.leakcanary:leakcanary-android:${Versions.canary}"}
    val chuckerLib by lazy {"com.github.chuckerteam.chucker:library:${Versions.chucker}"}
    val chuckerNoOp by lazy {"com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"}

    //Tests
    val jupiterEngine by lazy {"org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}"}
    val jupiterApi by lazy {"org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}"}
    val jupiterParams by lazy {"org.junit.jupiter:junit-jupiter-params:${Versions.jupiter}"}
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"}
    val junitTest by lazy {"junit:junit:${Versions.junit}"}
    val coreTest by lazy {"androidx.arch.core:core-testing:${Versions.core}"}
    val vintage by lazy { "org.junit.vintage:junit-vintage-engine:${Versions.jupiter}" }


}