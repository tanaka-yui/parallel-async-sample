/**
 * callback関数になっているライブラリをasync/awaitにしたい場合の例
 */

package sample.kotlin

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    println("[kotlin] main start")

    // JavaScriptでいうと、awaitっぽい感じ。なの通りでrunBlocking内の処理が終わるまで待ってくれる
    runBlocking {
        val func = async { testFunc() }
        val result = func.await()
        println(result)
    }
    println("[kotlin] main end")
}

/**
 * JavaScriptでいうところのnew Promise()と同じ様な効果。
 * suspendCoroutineでcallbackをasync/awaitで実行できるように、callbackで帰ってくるAPIを包んでます
 */
suspend fun testFunc(): Int {
    println("testFunc method")
    return suspendCoroutine { cont ->
        callbackMethod {
            cont.resume(it)
        }
    }
}

// callbackになっているライブラリの変わり
fun callbackMethod(ret: (Int) -> Unit) {
    ret(456)
}
