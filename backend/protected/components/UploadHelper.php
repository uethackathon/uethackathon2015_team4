<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class UploadHelper {

    public static function getUrlUploadSingleImage($obj, $user_id) {
        $ext_arr = array('png', 'jpg', 'jpeg', 'bmp');
        $name = StringHelper::filterString($obj['name']);
        $storeFolder = Yii::getPathOfAlias('webroot') . '/images/' . date('Y-m-d', time()) . '/' . $user_id . '/';
        $pathUrl = 'images/' . date('Y-m-d', time()) . '/' . $user_id . '/' . time() . $name;
        if (!file_exists($storeFolder)) {
            mkdir($storeFolder, 0777, true);
        }
        $tempFile = $obj['tmp_name'];
        $targetFile = $storeFolder . time() . $name;
        $ext = strtolower(pathinfo($name, PATHINFO_EXTENSION));
        if (in_array($ext, $ext_arr)) {
            if (move_uploaded_file($tempFile, $targetFile)) {
                return $pathUrl;
            } else {
                return NULL;
            }
        } else {
            return NULL;
        }
    }

    public static function getUrlUploadMultiImages($obj, $user_id) {
        $url_arr = array();
        foreach ($obj["tmp_name"] as $key => $tmp_name) {
            $ext_arr = array('png', 'jpg', 'jpeg', 'bmp');
            $name = StringHelper::filterString($obj['name'][$key]);
            $storeFolder = Yii::getPathOfAlias('webroot') . '/images/' . date('Y-m-d', time()) . '/' . $user_id . '/';
            $pathUrl = 'images/' . date('Y-m-d', time()) . '/' . $user_id . '/' . time() . $name;
            if (!file_exists($storeFolder)) {
                mkdir($storeFolder, 0777, true);
            }
            $tempFile = $obj['tmp_name'][$key];
            $targetFile = $storeFolder . time() . $name;
            $ext = strtolower(pathinfo($name, PATHINFO_EXTENSION));
            if (in_array($ext, $ext_arr)) {
                if (move_uploaded_file($tempFile, $targetFile)) {
                    array_push($url_arr, $pathUrl);
                } else {
                    return NULL;
                }
            } else {
                return NULL;
            }
        }
        return $url_arr;
    }

}
