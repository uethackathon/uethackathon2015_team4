<?php

Yii::import('application.models._base.BaseSubject');

class Subject extends BaseSubject {

    public static function model($className = __CLASS__) {
        return parent::model($className);
    }

    public function getSubjectByUser($user_id) {
        $data = UserSubject::model()->findAllByAttributes(array('user_id' => $user_id));
        $returnArr = array();
        foreach ($data as $item) {
            $itemArr = array();
            $subject_group_name = $this->findSubjectGroupBySubject($item->subject_id);
            $itemArr[$subject_group_name] = array($this->findSubjectById($item->subject_id));
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function findSubjectById($subject_id) {
        $subject = Subject::model()->findByPk($subject_id);
        return $subject;
    }

    public function findSubjectGroupById($subject_group_id) {
        $subject_group = SubjectGroup::model()->findByPk($subject_group_id);
        return $subject_group->name;
    }

    public function findSubjectGroupBySubject($subject_id) {
        $subject_group = Subject::model()->findByAttributes(array('subject_id' => $subject_id));
        return $subject_group->subject_group_id;
    }
    
   //

}
