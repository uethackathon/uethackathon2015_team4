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
            $itemArr = $this->findSubjectById($item->subject_id);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function findSubjectById($subject_id) {
        $subject = Subject::model()->findByPk($subject_id);
        $returnArr = array();
        foreach ($subject as $item) {
            $itemArr = array();
            $itemArr['subject_id'] = $item->subject_id;
            $itemArr['subject_group_id'] = $item->subject_group_id;
            $itemArr['title'] = $item->title;
            $itemArr['description'] = $item->description;
            $itemArr['subject_group_name'] = $this->findSubjectGroupBySubject($item->subject_id);
            //var_dump($this->findSubjectGroupBySubject($item->subject_id)); die;
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function findSubjectGroupById($subject_group_id) {
        $subject_group = SubjectGroup::model()->findByPk($subject_group_id);
        return $subject_group->name;
    }

    public function findSubjectGroupBySubject($subject_id) {
        $subject = Subject::model()->findByAttributes(array('subject_id' => $subject_id));
        $name = SubjectGroup::model()->findByAttributes(array('subject_group_id' => $subject->subject_group_id));
        return $name->name;
        // return 'tt';
    }

    public function findSubjectBySubjectGroup($subject_group_id) {
        $subject = Subject::model()->findAllByAttributes(array('subject_group_id' => $subject_group_id));
        $returnArr = array();
        foreach ($subject as $item) {
            $itemArr = array();
            $itemArr['subject_id'] = $item->subject_id;
            $itemArr['subject_group_id'] = $item->subject_group_id;
            $itemArr['title'] = $item->title;
            $itemArr['description'] = $item->description;
            $itemArr['subject_group_name'] = $this->findSubjectGroupBySubject($item->subject_id);
            //var_dump($this->findSubjectGroupBySubject($item->subject_id)); die;
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

    public function getAllSubject() {
        $subject_group = SubjectGroup::model()->findAll();
        $returnArr = array();
        foreach ($subject_group as $item) {
            $itemArr = array();
            $itemArr = $this->findSubjectBySubjectGroup($item->subject_group_id);
            $returnArr[] = $itemArr;
        }
        return $returnArr;
    }

//
}
