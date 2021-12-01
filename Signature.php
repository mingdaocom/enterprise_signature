<?php

function getSignature($appKey, $secretKey, $timestamp) {
    $tempdata = array(
        "AppKey" => $appKey,
        "SecretKey" => $secretKey,
        "Timestamp" => strval($timestamp)
    );
    $signstr = '';
    $keys = array_keys($tempdata);
    sort($keys);
    foreach ($keys as $value) {
        $signstr = $signstr . '&' . $value . '=' . $tempdata[$value];
    }
    $signstr = substr($signstr, 1);
    return base64_encode(hash("sha256", $signstr));
}

?> 
