package sample.kotlin

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    println("[kotlin] main start")

    // doAllはsuspendなので、runBlockingをして中の処理がすべて完了するまで待つ
    runBlocking {
        val result = doAll()
        println(result[0])
        println(result[1])
    }

    println("[kotlin] main end")
}

/**
 * JavaScriptPromise.all処理結果と似た様な実装
 * JavaでいうとExecutorService Callable
 */
suspend fun doAll(): Array<Int> = coroutineScope {
    val ret1 = async { test1() }
    val ret2 = async { test2() }

    // Promise.all風
    // メソッドの実行結果をarrayにいれて返してみると、promise.allっぽくなる
    arrayOf(ret1.await(), ret2.await())
}

suspend fun test1(): Int {
    println("test1 method")
    delay(2000) // APIとかを呼び出していると仮定
    return 123
}

suspend fun test2(): Int {
    println("test2 method")
    delay(1000) // APIとかを呼び出していると仮定
    return 456
}
