<?php

Yii::import('application.models._base.BasePostLike');

class PostLike extends BasePostLike
{
	public static function model($className=__CLASS__) {
		return parent::model($className);
	}
        
        public function like($post_id, $user_id)
        {
            $post = Post::model()->findByPk($post_id);
            $model = new PostLike;
            $model->post_id = $post_id;
            $model->user_id = $user_id;
            if($model->save(FALSE))
            {
                $post->post_like_count+=1;
                $post->save(FALSE);
                return TRUE;
            }
            return FALSE;
        }
}