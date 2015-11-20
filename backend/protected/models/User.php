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

    public function login($attr) {
        $check = User::model()->findByAttributes(array('email' => $attr['email']));
        if ($check) {
            if ($check->password == md5($attr['password'])) {
                return $check;
            } else {
                return FALSE;
            }
        }
        return FALSE;
    }

    public function getUserById($id) {
        $data = User::model()->findByPk($id);
        return $data->first_name . " " . $data->last - name;
    }

    public function updateProfile($attr, $obj_files) {
        $check = User::model()->findByPk($attr['user_id']);
        if ($check) {
            $check->setAttributes($attr);
            $avatar = UploadHelper::getUrlUploadSingleImage($obj_files, $attr['user_id']);
            $check->avatar = $avatar;
            if ($check->save(FALSE)) {
                return 'success';
            } else {
                return 'server error';
            }
        } else {
            return 'User not exist';
        }
    }

}
