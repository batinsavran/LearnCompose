package com.example.learncompose.lessons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learncompose.lessons.ui.theme.LearnComposeTheme

class Fundamentals : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Merhaba $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LearnComposeTheme {
        Greeting2("Android")
    }
}

fun main() {
    val question1 = Question("Quoth the raven ___", "nevermore", Difficulty.MEDIUM)
    val question2 = Question("Gökyüzü yeşildir. Doğru mu yanlış mı?", false, Difficulty.EASY)
    val question3 = Question("Dolunaylar arasında kaç gün var?", 28, Difficulty.HARD)
    println(question1.toString())
    println(question2.toString())
    println(question3.toString())

    val quiz = Quiz()
    quiz.printProgressBar()
}

// Soru sınıfı, generic yapıda farklı tiplerde cevap alabilir (örneğin String, Boolean, Int)
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY, MEDIUM, HARD
}

// Quiz sınıfı, ProgressPrintable arayüzünü (interface) uyguluyor
class Quiz : ProgressPrintable {

    // StudentProgress companion object, toplam soru sayısı ve cevaplananları tutuyor
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    // ProgressPrintable arayüzünden gelen progressText özelliğini uyguluyoruz
    override val progressText: String
        get() = "$answered of $total answered"

    // ProgressPrintable arayüzünden gelen printProgressBar fonksiyonunu uyguluyoruz
    override fun printProgressBar() {
        // Cevaplanan soruları dolu kutularla (▓), cevaplanmayanları boş kutularla (▒) gösteriyoruz
        repeat(answered) { print("▓") }
        repeat(total - answered) { print("▒") }
        println()
        println(progressText) // Progress bilgisini yazdırıyoruz
    }
}

// Progress verisini göstermek için bir arayüz tanımlıyoruz
interface ProgressPrintable {
    val progressText: String // Progress metni
    fun printProgressBar()    // Progress bar fonksiyonu
}


/*
Generics:
Question sınıfı, farklı cevap türlerini desteklemek için generics kullanıyor.
Böylece aynı sınıfı hem String hem Boolean hem de Int türlerinde cevaplarla kullanabiliyoruz.

Companion Object:
StudentProgress companion object, tekil bir yapı olarak quiz ilerleme verilerini (toplam soru ve cevaplanan soru sayısı) saklıyor.

Interface (Arayüz):
ProgressPrintable arayüzü, progressText ve printProgressBar özelliklerini içeren bir "sözleşme" tanımlıyor.
Bu sayede, herhangi bir sınıf bu arayüzü uyguladığında bu özellikleri de eklemiş oluyor.

Override:
Quiz sınıfında ProgressPrintable arayüzünden gelen özellik ve fonksiyonları override ile uyguluyoruz,
böylece Quiz sınıfında bu özelliklere özel bir davranış kazandırıyoruz.
*/