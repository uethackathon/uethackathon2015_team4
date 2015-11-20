<?php

Yii::import('application.models._base.BaseSubject');

class Subject extends BaseSubject
{
	public static function model($className=__CLASS__) {
		return parent::model($className);
	}
}