package com.qbrainx.app
import scala.concurrent.ExecutionContext.Implicits.global
import com.qbrainx.model.TrainingMember
import com.qbrainx.repository.TrainingMemberRepository
import com.qbrainx.repository.impl.TrainingMemberRepositoryImpl

import scala.util.{Failure, Success}

object App extends scala.App {

  val ssrun: TrainingMemberRepository = new TrainingMemberRepositoryImpl

  ssrun.insert(TrainingMember("1","Aji","AShok")).onComplete({
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  })

Thread.sleep(500000)
}
