<?php

class PostController extends Controller {

    public function actionIndex() {
        $this->render('index');
    }

    public function actionAdd() {
        $attr = StringHelper::filterArrayString($_POST);
        if (Post::model()->add($attr)) {
            ResponseHelper::JsonReturnSuccess('', 'success');
        } else {
            ResponseHelper::JsonReturnError('', 'server error');
        }
    }

    public function actionGetPostNearBy() {
        $request = Yii::app()->request;
        try {
            $lat = StringHelper::filterString($request->getQuery('lat'));
            $lng = StringHelper::filterString($request->getQuery('lng'));
            $user_id = StringHelper::filterString($request->getQuery('user_id'));
            $data = Post::model()->getPostNearBy($lat, $lng, $user_id);
            ResponseHelper::JsonReturnSuccess($data, 'success');
        } catch (Exception $ex) {
            var_dump($ex->getMessage());
        }
    }

    public function actionGetPostByUserSubject() {
        $request = Yii::app()->request;
        try {
            $user_id = StringHelper::filterString($request->getQuery('user_id'));
            $data = Post::model()->getPostBySubjectUser($user_id);
            ResponseHelper::JsonReturnSuccess($data, 'success');
        } catch (Exception $ex) {
            var_dump($ex->getMessage());
        }
    }

    public function actionUploadImage() {
        $user_id = Yii::app()->request->getPost('user_id');
        if (empty($user_id)) {
            $user_id = 1;
        }
        $url = UploadHelper::getUrlUploadSingleImage($_FILES['image'], $user_id);
        ResponseHelper::JsonReturnSuccess($url, 'Success');
    }

    public function actionGetPost() {
        $request = Yii::app()->request;
        try {
            $post_id = StringHelper::filterString($request->getQuery('post_id'));
            $data = Post::model()->getPostById($post_id);
            ResponseHelper::JsonReturnSuccess($data, 'success');
        } catch (Exception $ex) {
            var_dump($ex->getMessage());
        }
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
