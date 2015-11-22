<?php

Yii::import('application.models._base.BaseUser');

class User extends BaseUser {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function register($attr, $image) {

        $check = User::model()->findByAttributes(array('email' => $attr['email']));
        if ($check) {
            return 'USER_EXIST';
        } else {

            $model = new User;
            $model->setAttributes($attr);
            $model->password = md5($attr['password']);
            if ($model->save(FALSE)) {
                $image_url = NULL;
                if (isset($image)) {
                    $image_url = $image;
                }
                $model->avatar = $image_url;
                $model->save(FALSE);
                $subjects = Subject::model()->findAll();
                foreach($subjects as $subject)
                {
                    $user_subject = new UserSubject;
                    $user_subject->subject_id = $subject->subject_id;
                    $user_subject->user_id = $model->userid;
                    $user_subject->save(FALSE);
                }
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

    public function getUserInfoById($id) {
        $returnArr = array();
        $data = User::model()->findByPk($id);
        $returnArr['username'] = $data->first_name . " " . $data->last_name;
        $returnArr['user_id'] = $data->userid;
        $returnArr['avatar'] = $data->avatar;
        return $returnArr;
    }

    public function updateProfile($attr, $obj_files) {
        $check = User::model()->findByPk($attr['user_id']);
        if ($check) {
            $check->setAttributes($attr);
            if (isset($obj_files)) {
                $avatar = UploadHelper::getUrlUploadSingleImage($obj_files, $attr['user_id']);
                $check->avatar = $avatar;
            }
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
