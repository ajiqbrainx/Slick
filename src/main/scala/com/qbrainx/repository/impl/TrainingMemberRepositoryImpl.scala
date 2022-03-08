package com.qbrainx.repository.impl

import scala.concurrent.Future

import com.qbrainx.config.SlickConfig
import com.qbrainx.model.TrainingMember
import com.qbrainx.repository.TrainingMemberRepository
import com.qbrainx.repository.impl.TrainingMemberRepositoryImpl.Schema
import slick.lifted.ProvenShape

object TrainingMemberRepositoryImpl {

  val tableName = "TrainingMembers"

  import SlickConfig.jdbcProfile.api._

  private[impl] final class Schema(tag: Tag)
      extends Table[TrainingMember](tag, tableName) {

    def id: Rep[String] = column[String]("Id", O.PrimaryKey)
    def fName: Rep[String] = column[String]("FName")
    def lName: Rep[String] = column[String]("LName")

    override def * : ProvenShape[TrainingMember] =
      (id, fName, lName) <> (TrainingMember.apply, TrainingMember.unapply)
  }

  private[impl] val query: TableQuery[Schema] = TableQuery(new Schema(_))
}

final class TrainingMemberRepositoryImpl extends TrainingMemberRepository {

  import SlickConfig.jdbcProfile.api._
  import TrainingMemberRepositoryImpl._

  private val db = SlickConfig.db

  override def insert(trainingMember: TrainingMember): Future[Int] =
    db.run(query += trainingMember)

//  override def getTrainingMember(id: String): Future[Option[TrainingMember]] =
//    db.run(query.filter(_.id === id).result.headOption)
//
//  override def getAllMembers: Future[Seq[TrainingMember]] =
//    db.run(query.result)
//
//  override def updateTrainingMemberDetail(
//      id: String,
//      fName: Option[String],
//      lName: Option[String]): Future[Int] = {
//    val filterQuery = query.filter(_.id === id)
//
//    ((fName, lName) match {
//      case (Some(fNme), Some(lNme)) =>
//        Some(filterQuery.map(f => (f.fName, f.lName)).update((fNme, lNme)))
//      case (Some(fNme), None) =>
//        Some(filterQuery.map(_.fName).update(fNme))
//      case (None, Some(lNme)) =>
//        Some(filterQuery.map(_.lName).update(lNme))
//      case (None, None) =>
//        None
//    }) match {
//      case Some(internalQuery) =>
//        db.run(internalQuery)
//      case None => Future.successful(0)
//    }
//  }
//
//  override def deleteTrainingMember(id: String): Future[Int] =
//    db.run(query.filter(_.id === id).delete)
}
