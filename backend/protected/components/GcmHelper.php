<?php

class GcmHelper {

    public static function sendNotification($device_id, $message) {
        //$this->gcm_key = Yii::app()->params['gcm_key'];

        $msg = array
            (
            'message' => $message,
            'title' => 'This is a title. title',
            'subtitle' => 'This is a subtitle. subtitle',
            'tickerText' => 'Ticker text here...Ticker text here...Ticker text here',
            'vibrate' => 1,
            'sound' => 1,
            'largeIcon' => '',
            'smallIcon' => ''
        );
        $fields = array
            (
            'registration_ids' => array($device_id),
            'data' => $msg
        );

        $headers = array
            (
            'Authorization: key=AIzaSyAwYgjm394TnBglT17O25_e0oCzZLbtWhQ',
            'Content-Type: application/json'
        );
        //die('tt');

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, 'http://android.googleapis.com/gcm/send');
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
        $result = curl_exec($ch);
        curl_close($ch);
        return $result;
        
    }

}



