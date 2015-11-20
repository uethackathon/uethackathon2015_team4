<?php

Yii::import('application.models._base.BasePost');

class Post extends BasePost {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function add($attr) {
        $model = new Post;
        $model->setAttributes($attr);
        $model->date = time();

        if ($model->save(FALSE)) {
            $subject_arr = json_decode($attr['subject'], true);
            foreach ($subject_arr as $item) {
                $subject_post = new PostSubject;
                $subject_post->post_id = $model->post_id;
                $subject_post->subject_id = $item;
                $subject_post->save(FALSE);
            }
            return TRUE;
        }
        return FALSE;
    }

    public function getPostBySubjectUser($user_id) {
        $returnArr = array();
        $subject_arr = array();
        $subject = UserSubject::model()->findAllByAttributes(array('user_id' => $user_id));
        foreach ($subject as $item) {
            $itemArr = array();
            $itemArr = $item->subject_id;
            $subject_arr[] = $itemArr;
        }
        $criteria = new CDbCriteria;
        $criteria->addInCondition('subject_id', $subject_arr);
        $criteria->order = 'post_id';
        $post = PostSubject::model()->findAll($criteria);
        foreach ($post as $item) {
            $itemArr = array();
            $itemArr = $this->getPostById($item->post_id);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostNearBy($lat, $lng, $user_id) {
        $returnArr = array();
        $criteria = new CDbCriteria;
        $criteria->select = "t.*, ( 3959 * acos( cos( radians($lat) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians($lng) ) + sin( radians($lat) ) * sin( radians( lat ) ) ) ) as
            distance";
        $criteria->having = 'distance < 4';
        $criteria->group = 't.location_id';
        $data = Location::model()->findAll($criteria);
        foreach ($data as $item) {
            $itemArr = array();
            $itemArr = $this->getPostByLocation($item->location_id);
            foreach ($itemArr as $arr) {
                $returnArr[] = $arr;
            }
        }
        return $returnArr;
    }

    public function getPostById($id) {
        $returnArr = array();
        $data = Post::model()->findByPk($id);
        foreach ($data as $item) {
            $returnArr['user'] = User::model()->findByPk($item->user_id);
            $returnArr['location'] = Location::model()->findByPk($item->location_id);
            $returnArr['post_id'] = $item->post_id;
            $returnArr['content'] = $item->content;
            $returnArr['date'] = $item->date;
            // $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostByLocation($location_id) {
        $data = Post::model()->findAllByAttributes(array('location_id' => $location_id));
        $returnArr = array();
        foreach ($data as $item) {
            $itemArr = array();
            $itemArr = $this->getPostById($item->post_id);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

}
