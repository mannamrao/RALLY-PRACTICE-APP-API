package database

import play.api.libs.json.Json
import slick.jdbc.JdbcType
import slick.jdbc.PostgresProfile.api._

object MappedColumnBasedImplicits {
//  implicit val arrayString: JdbcType[Array[String]] = MappedColumnType.base[Array[String], String](
//        values => Json.stringify(Json.toJson(values)),
//        Json.parse(_).as[Array[String]]
//      )
}
