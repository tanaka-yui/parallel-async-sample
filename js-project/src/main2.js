/**
 * callback関数になっているライブラリをasync/awaitにしたい場合の例
 */
const main = async () => {
    console.log('[JavaScript] main start')
    const result = await testFunc()
    console.log(result)
    console.log('[JavaScript] main end')
}

// promiseでcallbackをasync/awaitで実行できるように、callbackで帰ってくるAPIを包んでます
const testFunc = () => {
    return new Promise(resolve => {
        console.log("testFunc method")
        callbackMethod(ret => {
            resolve(ret)
        })
    })
}

// callbackになっているライブラリの変わり
const callbackMethod = callback => {
    callback(456)
}

main()