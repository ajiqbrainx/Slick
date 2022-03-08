package com.qbrainx.config

import com.typesafe.config.{Config, ConfigFactory}

object AppConfig {

  val config: Config = ConfigFactory.load().getConfig("slick-training")
}
