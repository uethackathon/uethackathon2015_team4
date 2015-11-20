<?php

Yii::import('application.models._base.BaseLocation');

class Location extends BaseLocation {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function getLocationById($location_id) {
        $data = Location::model()->findByPk($location_id);
        return $data->name;
    }

}
