package com.excilys.computerdatabase.gatling.simulation

import com.excilys.computerdatabase.gatling.process._
import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by Cédric Cousseran on 29/03/16.
  * Launch the gatling test for the computer database webapp with Spring Security enabled.
  */
class ComputerDatabaseSimulationSecurity extends Simulation {
  before {
    println("The computer database simulation is about to to start!")
  }

  val httpConf = http
    .baseURL(ConfigFactory.load().getString("application.baseUrl")) // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> "application/x-www-form-urlencoded") // Note the headers specific to a given request


  val users = scenario("Users").exec(new Authenticate("data/user.csv").authenticate, Browse.browse,Search.search)
  val admins = scenario("Admins").exec(
    new Authenticate("data/admin.csv").authenticate,
    Browse.browse,
    Search.search,
    AddSecurity.add,
    EditSecurity.edit,
    DeleteSecurity.delete
  )
  var maxUsers = if (System.getProperty("stresstest.maxUsers") != null && System.getProperty("stresstest.maxUsers") != "") System.getProperty("stresstest.maxUsers").toInt else 1000
  var maxAdmins = if (System.getProperty("stresstest.maxAdmins") != null && System.getProperty("stresstest.maxAdmins") != "") System.getProperty("stresstest.maxAdmins").toInt else 10
  var periodTime = 30 seconds

  setUp(
    users.inject(rampUsers(maxUsers) over periodTime),
    admins.inject(rampUsers(maxAdmins) over periodTime)
  ).protocols(httpConf)

  after {
    println("The simulation is finished!")
  }

}
