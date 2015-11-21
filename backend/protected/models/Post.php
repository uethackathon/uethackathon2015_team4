<?php

Yii::import('application.models._base.BasePost');

class Post extends BasePost {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function add($attr, $image) {
        $model = new Post;
        $model->setAttributes($attr);
        $model->date = time();
        $model->post_comment_count = 0;
        $model->post_like_count = 0;
        $image_url = null;
        if (isset($image)) {
            $image_url = UploadHelper::getUrlUploadSingleImage($image, $attr['user_id']);
        }
        $location = new Location;
        $location->longitude = $attr['lng'];
        $location->latitude = $attr['lat'];
        $location->name = $attr['name'];
        $location->save(FALSE);
        $model->image = $image_url;
        $model->location_id = $location->location_id;
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

    public function getPostBySubjectUser($user_id, $limit, $offset) {
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
        $criteria->limit = $limit;
        $criteria->offset = $offset;
        $post = PostSubject::model()->findAll($criteria);
        foreach ($post as $item) {
            $itemArr = array();
            $itemArr = $this->getPostById($item->post_id);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostNearBy($lat, $lng, $user_id, $limit, $offset) {
        $returnArr = array();
        $user_subject_sql = "SELECT subject_id FROM user_subject WHERE user_id = $user_id";
        $user_subject_arr = Yii::app()->db->createCommand($user_subject_sql)->queryAll();
        $data = array();
        foreach ($user_subject_arr as $key => $value) {
            foreach ($value as $item) {
                array_push($data, $item);
            }
        }
        $user_subject = '(' . implode(', ', $data) . ')';
        $sql = "SELECT p.post_id, ( 3959 * acos( cos( radians($lat) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians($lng) ) + sin( radians($lat) ) * sin( radians( latitude ) ) ) ) as distance FROM post p JOIN location l ON p.location_id = l.location_id "
                . "JOIN post_subject s ON p.post_id = s.post_id WHERE s.subject_id IN $user_subject GROUP BY l.location_id HAVING distance < 4 LIMIT $offset, $limit";
        $data = Yii::app()->db->createCommand($sql)->queryAll();
        foreach ($data as $key => $item) {
            $itemArr = array();
            $itemArr = $this->getPostById($item['post_id'], $user_id, 1);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostById($id, $user_id, $flag) {
        $returnArr = array();
        $item = Post::model()->findByPk($id);
        // var_dump($data); die;

        $returnArr['user'] = User::model()->getUserInfoById($item->user_id);
        $returnArr['location'] = Location::model()->findByPk($item->location_id);
        $returnArr['post_id'] = $item->post_id;
        $returnArr['content'] = $item->content;
        $returnArr['date'] = $item->date;
        $returnArr['post_comment_count'] = $item->post_comment_count;
        $returnArr['post_like_count'] = $item->post_like_count;
        $returnArr['is_like'] = PostLike::model()->checkLike($id, $user_id);
        $returnArr['subjects'] = $this->getSubjectByPost($item->post_id);
        if ($flag != 1) {
            $returnArr['comments'] = $this->getCommentByPost($id);
            // $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostByLocation($user_id, $location_id, $limit, $offset) {
        $criteria = new CDbCriteria;
        $criteria->limit = $limit;
        $criteria->offset = $offset;
        $criteria->condition = "location_id = $location_id";
        $data = Post::model()->findAll($criteria);
        $returnArr = array();
        foreach ($data as $item) {
            $itemArr = array();
            $itemArr = $this->getPostById($user_id, $item->post_id, 1);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getPostByUser($user_id, $limit, $offset) {
        $criteria = new CDbCriteria;
        $criteria->limit = $limit;
        $criteria->offset = $offset;
        $criteria->condition = "user_id = $user_id";
        $posts = Post::model()->findAll($criteria);
        $returnArr = array();
        foreach ($posts as $post) {
            $itemArr = array();
            $itemArr = $this->getPostById($user_id, $post->post_id, 1);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getCommentByPost($post_id) {
        $returnArr = array();
        $data = Comment::model()->findAllByAttributes(array('post_id' => $post_id));
        foreach ($data as $item) {
            $itemArr = array();
            $itemArr['user'] = User::model()->getUserInfoById($item->user_comment_id);
            $itemArr['content'] = $item->content;
            $itemArr['date'] = $item->date;
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getSubjectByPost($post_id) {
        $returnArr = array();
        $subject = PostSubject::model()->findAllByAttributes(array('post_id' => $post_id));
        foreach ($subject as $item) {
            $subject_name = Subject::model()->findByPk($item->subject_id)->title;
            $returnArr[] = $subject_name;
        }
        return $returnArr;
    }

}
