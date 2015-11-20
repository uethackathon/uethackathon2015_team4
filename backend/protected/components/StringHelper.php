<?php

class StringHelper {

    public static function generateToken($minLength, $maxLength) {
        $token = base_convert(sha1(uniqid(mt_rand(), true)), $minLength, $maxLength);
        return $token;
    }

    public static function stripSpace($string) {
        $nonSpaceString = str_replace(' ', '', $string);
        return $nonSpaceString;
    }

    public static function strip_crlf($string) {
        return str_replace("\r\n", "\n", $string);
    }

    public static function generateRandomString($length) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, strlen($characters) - 1)];
        }
        return $randomString;
    }

    public static function generateRandomStringCode($length) {
        $characters = '0123456789';
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, strlen($characters) - 1)];
        }
        return $randomString;
    }

    public static function filterString($string) {
        $string = strip_tags($string);
        $string = stripcslashes($string);
        $string = htmlspecialchars($string);
        $string = addslashes($string);
        $string = str_replace("\r\n", "\n", $string);
        $string = trim($string);
        return $string;
    }

    public static function makeUrlString($str) {
        //Lower case everything
        $unicode = array(
            'a' => 'á|à|ả|ã|ạ|ă|ắ|ặ|ằ|ẳ|ẵ|â|ấ|ầ|ẩ|ẫ|ậ',
            'd' => 'đ',
            'e' => 'é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ',
            'i' => 'í|ì|ỉ|ĩ|ị',
            'o' => 'ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ',
            'u' => 'ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự',
            'y' => 'ý|ỳ|ỷ|ỹ|ỵ',
            'A' => 'Á|À|Ả|Ã|Ạ|Ă|Ắ|Ặ|Ằ|Ẳ|Ẵ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ',
            'D' => 'Đ',
            'E' => 'É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ',
            'I' => 'Í|Ì|Ỉ|Ĩ|Ị',
            'O' => 'Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ',
            'U' => 'Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự',
            'Y' => 'Ý|Ỳ|Ỷ|Ỹ|Ỵ',
        );
        foreach ($unicode as $nonUnicode => $uni) {
            $str = preg_replace("/($uni)/i", $nonUnicode, $str);
        }
        $str = preg_replace("/[\s-]+/", " ", $str);
        $str = preg_replace("![^a-z0-9]+!i", "-", $str);
        //Convert whitespaces and underscore to dash
        $str = preg_replace('/-{2,}/', '-', $str);
        $str = preg_replace("/[\s_]/", "-", $str);
        $str = preg_replace('/[^A-Za-z0-9\-]/', '', $str);
        $str = preg_replace("![^a-z0-9]+!i", "-", $str);
        $str = strtolower($str);
        return $str;
    }

    public static function unicode_str_filter($str) {
        $unicode = array(
            'a' => 'á|à|ả|ã|ạ|ă|ắ|ặ|ằ|ẳ|ẵ|â|ấ|ầ|ẩ|ẫ|ậ',
            'd' => 'đ',
            'e' => 'é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ',
            'i' => 'í|ì|ỉ|ĩ|ị',
            'o' => 'ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ',
            'u' => 'ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự',
            'y' => 'ý|ỳ|ỷ|ỹ|ỵ',
            'A' => 'Á|À|Ả|Ã|Ạ|Ă|Ắ|Ặ|Ằ|Ẳ|Ẵ|Â|Ấ|Ầ|Ẩ|Ẫ|Ậ',
            'D' => 'Đ',
            'E' => 'É|È|Ẻ|Ẽ|Ẹ|Ê|Ế|Ề|Ể|Ễ|Ệ',
            'I' => 'Í|Ì|Ỉ|Ĩ|Ị',
            'O' => 'Ó|Ò|Ỏ|Õ|Ọ|Ô|Ố|Ồ|Ổ|Ỗ|Ộ|Ơ|Ớ|Ờ|Ở|Ỡ|Ợ',
            'U' => 'Ú|Ù|Ủ|Ũ|Ụ|Ư|Ứ|Ừ|Ử|Ữ|Ự',
            'Y' => 'Ý|Ỳ|Ỷ|Ỹ|Ỵ',
        );
        foreach ($unicode as $nonUnicode => $uni) {
            $str = preg_replace("/($uni)/i", $nonUnicode, $str);
        }
        return $str;
    }

    public static function filterArrayString($str) {

        if (is_array($str)) {
            foreach ($str AS $k => $v)
                $str[$k] = self::filterString($v);
            return $str;
        }
    }

    public static function returnClassForMultipleImages($count) {
        $class = NULL;
        switch ($count) {
            case 1:
                $class = 'has-one-image';
                break;
            case 2:
                $class = 'hasTwoImages';
                break;
            case 3:
                $class = 'hasThreeImages';
                break;
            case 4:
                $class = 'hasFourImages';
                break;
            case 5:
                $class = 'hasFiveImages';
                break;
        }
        return $class;
    }
    
    public static function returnCategoryTypeName($type)
    {
        $class = NULL;
        switch ($type) {
            case 1:
                $class = 'Thời trang nam';
                break;
            case 2:
                $class = 'Thời trang nữ';
                break;
            case 0:
                $class = 'Loại khác';
                break;
        }
        return $class;
    }
    
    public static function returnCategoryNameById($cat_id)
    {
        $cat = Categories::model()->findByPk($cat_id);
        return $cat->cat_name;
    }

}
