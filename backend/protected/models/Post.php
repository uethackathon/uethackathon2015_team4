<?php

Yii::import('application.models._base.BasePost');

class Post extends BasePost
{
	public static function model($className=__CLASS__) {
		return parent::model($className);
	}
        
        public function add($attr)
        {
            $model = new Post;
            $model->setAttributes($attr);
            if($model->save(FALSE))
            {
                return TRUE;
            }
            return FALSE;
        }
}