package com.qbrainx.model

final case class TrainingMember(id: String, fName: String, lName: String)

object TrainingMember {

  def apply(tupleParam: (String, String, String)): TrainingMember =
    tupleParam match {
      case (id, fName, lName) => apply(id, fName, lName)
    }
}
