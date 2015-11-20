<?php

Yii::import('application.models._base.BaseUserSubject');

class UserSubject extends BaseUserSubject
{
	public static function model($className=__CLASS__) {
		return parent::model($className);
	}
}