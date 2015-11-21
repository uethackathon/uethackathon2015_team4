<?php

Yii::import('application.models._base.BasePostLike');

class PostLike extends BasePostLike {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function like($post_id, $user_id) {
        $post = Post::model()->findByPk($post_id);
        if ($post) {
            $check = PostLike::model()->findByAttributes(array('post_id' => $post_id, 'user_id' => $user_id));
            if ($check) {
                $post->post_like_count = intval($post->post_like_count) - 1;
                $check->delete();
            } else {
                $model = new PostLike;
                $model->post_id = $post_id;
                $model->user_id = $user_id;
                if ($model->save(FALSE)) {
                    $post->post_like_count = intval($post->post_like_count) + 1;
                    $post->save(FALSE);
                    return TRUE;
                }
            }
        }
        return FALSE;
    }

    public function checkLike($post_id, $user_id) {
        $check = PostLike::model()->findByAttributes(array('post_id' => $post_id, 'user_id' => $user_id));
        if ($check) {
            return TRUE;
        }
        return FALSE;
    }

}
