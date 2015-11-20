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
