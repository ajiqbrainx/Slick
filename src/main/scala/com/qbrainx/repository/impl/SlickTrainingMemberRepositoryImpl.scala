//package com.qbrainx.repository.impl
//
//import scala.concurrent.{Await, ExecutionContext}
//import scala.concurrent.duration.Duration
//import scala.util.Success
//
//import com.qbrainx.config.SlickConfig
//import com.qbrainx.model.{SlickTrainingMember, TrainingMember}
//import com.qbrainx.repository.SlickTrainingMemberRepository
//import slick.lifted.{ForeignKeyQuery, ProvenShape}
//
//object SlickTrainingMemberRepositoryImpl {
//
//  val tableName = "SlickTrainingMember"
//
//  import SlickConfig.jdbcProfile.api._
//
//  private[impl] final class Schema(tag: Tag)
//      extends Table[SlickTrainingMember](tag, tableName) {
//
//    def id: Rep[String] = column[String]("Id", O.PrimaryKey)
//    def trainingMemberId: Rep[String] = column[String]("TrainingMemberId")
//
//    def trainingMember
//        : ForeignKeyQuery[TrainingMemberRepositoryImpl.Schema, TrainingMember] =
//      foreignKey(
//        "TrainingMemberId",
//        trainingMemberId,
//        TrainingMemberRepositoryImpl.query)(_.id)
//
//    override def * : ProvenShape[SlickTrainingMember] = (
//      id,
//      trainingMemberId) <> (SlickTrainingMember.apply, SlickTrainingMember.unapply)
//  }
//
//  private[impl] val query: TableQuery[Schema] = TableQuery(new Schema(_))
//}
//
//final class SlickTrainingMemberRepositoryImpl
//    extends SlickTrainingMemberRepository {
//
//  import SlickConfig.jdbcProfile.api._
//  import SlickTrainingMemberRepositoryImpl._
//  import TrainingMemberRepositoryImpl.{query => traingMemberQuery}
//
//  Await
//    .ready(
//      SlickConfig.db.run(
//        traingMemberQuery.join(query).on(_.id === _.trainingMemberId).result),
//      Duration.Inf)
//    .onComplete{
//      case Success(value) =>
//        value.foreach{
//          case f@(TrainingMember(id, fName, lName), SlickTrainingMember(idi, trainingMemberId)) =>
//          println(f)
//        }
//    }(ExecutionContext.global)
//}
