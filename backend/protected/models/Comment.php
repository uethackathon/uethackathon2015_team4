<?php

Yii::import('application.models._base.BaseComment');

class Comment extends BaseComment {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function add($post_id, $user_id, $content) {
        $post = Post::model()->findByPk($post_id);
        $model = new Comment;
        $model->post_id = $post_id;
        $model->user_comment_id = $user_id;
        $model->content = $content;
        $model->date = time();
        if ($model->save(FALSE)) {
            $post->post_comment_count = intval($post->post_comment_count) + 1;
            $post->save(FALSE);
            return TRUE;
        }
        return FALSE;
    }

}
