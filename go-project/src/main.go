package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("[go] main start")

	result := doAll()
	fmt.Println(result[0])
	fmt.Println(result[1])

	fmt.Println("[go] main end")
}

func doAll() []int {
	resultArray := make([]int, 0, 2)
	chanel1 := make(chan int, 1)
	chanel2 := make(chan int, 1)

	// goroutineで非同期処理
	go Test1(chanel1)
	go Test2(chanel2)

	// Promise.all風実行結果を配列に詰め込んでいます。
	// <- は、jsやkotlinでいうawaitキーワードみたいなものです。Javaだとfuture.get()
	// 全ての実行結果が買ってくるまで、awaitしてる感じです。
	resultArray = append(resultArray, <-chanel1)
	resultArray = append(resultArray, <-chanel2)

	close(chanel1)
	close(chanel2)
	return resultArray
}

func Test1(c chan int) {
	fmt.Println("test1 method")
	time.Sleep(time.Second * 2) // APIとかを呼び出していると仮定
	c <- 123
}

func Test2(c chan int) {
	fmt.Println("test2 method")
	time.Sleep(time.Second * 1) // APIとかを呼び出していると仮定
	c <- 456
}
