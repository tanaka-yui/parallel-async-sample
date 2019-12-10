
const main = async () => {
    console.log('[JavaScript] main start')
    // 処理を非同期で複数投げ、Promise.allで全ての結果が帰ってくるまで待つ
    const result = await Promise.all([
        test1(),
        test2(),
    ])
    console.log(result[0])
    console.log(result[1])
    console.log('[JavaScript] main end')
}

const test1 = async () => {
    console.log("test1 method")
    await sleep(2000) // APIとかを呼び出していると仮定
    return 123
}

const test2 = async () => {
    console.log("test2 method")
    await sleep(1000) // APIとかを呼び出していると仮定
    return 456
}

// JavaScriptは便利なThread.sleep的なものがないので近いものを再現します
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// main関数を実行
main()
