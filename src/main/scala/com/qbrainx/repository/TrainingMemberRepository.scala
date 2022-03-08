package com.qbrainx.repository

import scala.concurrent.Future

import com.qbrainx.model.TrainingMember

trait TrainingMemberRepository {

  def insert(trainingMember: TrainingMember): Future[Int]

//  def getTrainingMember(id: String): Future[Option[TrainingMember]]
//
//  def getAllMembers: Future[Seq[TrainingMember]]
//
//  def updateTrainingMemberDetail(id: String,
//                                 fName: Option[String],
//                                 lName: Option[String]): Future[Int]
//
//  def deleteTrainingMember(id: String): Future[Int]
}
