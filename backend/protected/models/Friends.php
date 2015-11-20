<?php

Yii::import('application.models._base.BaseFriends');

class Friends extends BaseFriends
{
	public static function model($className=__CLASS__) {
		return parent::model($className);
	}
}