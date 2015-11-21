<?php

class NotificationController extends Controller {

    public function actionIndex() {
        $this->render('index');
    }

    public function actionSendNotification() {
        $request = Yii::app()->request;
        try {
            $message = StringHelper::filterString($request->getPost('message'));
            $device_id = StringHelper::filterString($request->getPost('device_id'));
            $result = GcmHelper::sendNotification($device_id, $message);
            ResponseHelper::JsonReturnSuccess($result, 'success');
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
