package otus.homework.reactivecats

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class LocalCatFactsGenerator(
    private val context: Context
) {

    /**
     * Реализуйте функцию otus.homework.reactivecats.LocalCatFactsGenerator#generateCatFact так,
     * чтобы она возвращала Fact со случайной строкой  из массива строк R.array.local_cat_facts
     * обернутую в подходящий стрим(Flowable/Single/Observable и т.п)
     */
    fun generateCatFact(): Single<Fact> {
        return Single.just(getRandomFact())
    }

    /**
     * Реализуйте функцию otus.homework.reactivecats.LocalCatFactsGenerator#generateCatFactPeriodically так,
     * чтобы она эмитила Fact со случайной строкой из массива строк R.array.local_cat_facts каждые 2000 миллисекунд.
     * Если вновь заэмиченный Fact совпадает с предыдущим - пропускаем элемент.
     */
    fun generateCatFactPeriodically(): Observable<Fact> {
        return Observable
            .interval(2000, TimeUnit.MILLISECONDS)
            .map { getRandomFact() }
            .distinctUntilChanged { fact -> fact.text }
    }

    private fun getRandomFact(): Fact {
        val array = context.resources.getStringArray(R.array.local_cat_facts)
        val randomIndex = Random.nextInt(array.lastIndex)
        return Fact(array[randomIndex])
    }
}