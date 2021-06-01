package main

import (
	"crypto/sha256"
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"time"
)

func main()  {
	ti := time.Now().UnixNano() / 1000000
	sign := GetSignature("appKey", "secretKey", ti)
	fmt.Println(sign)
}

func GetSignature(appKey, secretKey string, ti int64) string {
	//sha256
	key := fmt.Sprintf("AppKey=%v&SecretKey=%v&Timestamp=%v", appKey, secretKey, ti)
	h := sha256.New()
	h.Write([]byte(key))
	shaKey := h.Sum(nil)

	//hex 转string
	hexKey := hex.EncodeToString(shaKey)
	fmt.Println(hexKey)

	//base64
	return base64.StdEncoding.EncodeToString([]byte(hexKey))
}