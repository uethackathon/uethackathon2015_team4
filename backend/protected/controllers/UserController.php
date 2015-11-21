<?php

class UserController extends Controller {

    public function actionIndex() {
        $this->render('index');
    }

    public function loginWithFacebook() {
        //$attr = StringHelper::filterArrayString($_POST);
    }

    public function actionRegister() {
        $attr = StringHelper::filterArrayString($_POST);
        $result = User::model()->register($attr);
        switch ($result) {
            case 'USER_EXIST':
                ResponseHelper::JsonReturnError('', 'user exist');
                break;
            case 'SERVER_ERROR':
                ResponseHelper::JsonReturnError('', 'server error');
                break;
            case 'SUCCESS':
                ResponseHelper::JsonReturnSuccess('', 'success');
                break;
        }
    }

    public function actionLogin() {
        $attr = StringHelper::filterArrayString($_POST);
        $data = User::model()->login($attr);
        if ($data) {
            ResponseHelper::JsonReturnSuccess($data, 'success');
        } else {
            ResponseHelper::JsonReturnError('', 'server error');
        }
    }

    public function actionUpdateProfile() {
        $attr = StringHelper::filterArrayString($_POST);
        $obj_file = null;
        if (isset($_FILES['avatar'])) {
            $obj_file = $_FILES['avatar'];
        }
        $message = User::model()->updateProfile($attr, $obj_file);
        ResponseHelper::JsonReturnSuccess('', $message);
    }
    
    public function actionGetUserProfile()
    {
        $user_id = Yii::app()->request->getQuery('user_id');
        $data = User::model()->findByPk($user_id);
        ResponseHelper::JsonReturnSuccess($data, 'success');
    }

    // Uncomment the following methods and override them if needed
    /*
      public function filters()
      {
      // return the filter configuration for this controller, e.g.:
      return array(
      'inlineFilterName',
      array(
      'class'=>'path.to.FilterClass',
      'propertyName'=>'propertyValue',
      ),
      );
      }

      public function actions()
      {
      // return external action classes, e.g.:
      return array(
      'action1'=>'path.to.ActionClass',
      'action2'=>array(
      'class'=>'path.to.AnotherActionClass',
      'propertyName'=>'propertyValue',
      ),
      );
      }
     */
}
