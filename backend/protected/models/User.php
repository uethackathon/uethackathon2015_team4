<?php

Yii::import('application.models._base.BaseUser');

class User extends BaseUser {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function register($attr) {
        $check = User::model()->findByAttributes(array('email' => $attr['email']));
        if ($check) {
            return 'USER_EXIST';
        } else {
            $model = new User;
            $model->setAttributes($attr);
            $model->password = md5($attr['password']);
            if ($model->save(FALSE)) {
                return 'SUCCESS';
            }
            return 'SERVER_ERROR';
        }
    }
    
    public function login($attr)
    {
         $check = User::model()->findByAttributes(array('email' => $attr['email']));
         if($check)
         {
             if($check->password == md5($attr['password']))
             {
                 return $check;
             } else {
                 return FALSE;
             }
         }
         return FALSE;
    }

}
