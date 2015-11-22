<?php

class SubjectController extends Controller {

    public function actionIndex() {
        $this->render('index');
    }

    public function actionGetAllSubject() {
        try {
            $data = Subject::model()->getAllSubject();
            ResponseHelper::JsonReturnSuccess($data, 'Success');
        } catch (Exception $ex) {
            var_dump($ex->getMessage());
        }
    }

    public function actionGetSubjectByUser() {
        $request = Yii::app()->request;
        try {
            $user_id = StringHelper::filterString($request->getQuery('user_id'));
            $data = Subject::model()->getSubjectByUser($user_id);
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
